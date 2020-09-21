package com.qutu.talk.popup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.qutu.talk.R;
import com.qutu.talk.activity.room.AdminHomeActivity;
import com.qutu.talk.adapter.BoxOpenRecordAdapter;
import com.qutu.talk.adapter.ClearMlAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.BaseBean;
import com.qutu.talk.bean.BoxOpenRecordBean;
import com.qutu.talk.bean.Microphone;
import com.qutu.talk.bean.PullRefreshBean;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.CountTimeUtils;
import com.qutu.talk.utils.DealRefreshHelper;
import com.qutu.talk.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

//清理魅力值
public class ClearMlWindow extends PopupWindow {
    private AdminHomeActivity mContext;
    private RecyclerView recyclerView;
    private ImageView ivClose;
    private CommonModel commonModel;
    private RxErrorHandler mRxErrorHandler;
    private ClearMlAdapter mAdapter;
    private List<Microphone.DataBean.MicrophoneBean> mDataList = new ArrayList<>();
    private PullRefreshBean mPullRefreshBean = new PullRefreshBean();
    private String uid;//房间的uid

    public ClearMlWindow(AdminHomeActivity context, ViewGroup view, CommonModel commonModel, RxErrorHandler rxErrorHandler, String uid) {
        super(context);
        this.uid=uid;
        this.mContext = context;
        this.commonModel = commonModel;
        this.mRxErrorHandler = rxErrorHandler;
        View view1 = LayoutInflater.from(context).inflate(R.layout.clear_ml_window, null);
        recyclerView = view1.findViewById(R.id.recycler);
        ivClose = view1.findViewById(R.id.iv_close);
        ivClose.setOnClickListener((v)-> dismiss());
        view1.findViewById(R.id.tv_history).setOnClickListener(v -> {
            dismiss();
            ClearMlHistoryWindow clearMlHistoryWindow=new ClearMlHistoryWindow(mContext, (ViewGroup) context.getWindow().getDecorView(), commonModel, rxErrorHandler,uid);
            clearMlHistoryWindow.showAtLocation(context.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        });
        // 设置SelectPicPopupWindow的View
        this.setContentView(view1);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
//        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x88000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        //设置透明
        WindowManager.LayoutParams params = context.getWindow().getAttributes();
        params.alpha = 0.7f;
        //this.setWidth(view.getMeasuredWidth() - MyUtil.dip2px(context, 100));
//        this.setHeight(MyUtil.dip2px(context, 300));
        this.setHeight((int) (view.getMeasuredHeight()*0.6));
        context.getWindow().setAttributes(params);

        mAdapter = new ClearMlAdapter(R.layout.item_clear_ml, mDataList);
        mAdapter.setOnClearClickListener(microphoneBean -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("确认删除？");
            builder.setCancelable(false);
            builder.setPositiveButton("是", (dialog, which) -> {
                String userId=microphoneBean.getUser_id();
                if(TextUtils.isEmpty(userId)){
                    ToastUtil.showToast(mContext,"用户不存在");
                    dialog.dismiss();
                }else{
                    clearMl(userId,dialog);
                }

            });
            builder.setNegativeButton("否", (dialog, which) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        });
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(mAdapter);
        loadVedioList();
    }

    public void dismiss() {
        super.dismiss();
        MyBaseArmActivity myBaseArmActivity = mContext;
        WindowManager.LayoutParams params = myBaseArmActivity.getWindow().getAttributes();
        params.alpha = 1f;
        myBaseArmActivity.getWindow().setAttributes(params);
    }

    private void clearMl(String b_user_id,DialogInterface dialog){
        RxUtils.loading(commonModel.clearMl(uid, UserManager.getUser().getUserId()+"",b_user_id), mContext)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mRxErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        dialog.dismiss();
                        ToastUtil.showToast(mContext,"清除成功");
                        loadVedioList();
                        mContext.loadVedioList();
                    }

                });
    }

    /**
     * 麦序列表
     */
    private void loadVedioList() {
        RxUtils.loading(commonModel.microphone_status(uid), mContext)
                .subscribe(new ErrorHandleSubscriber<Microphone>(mRxErrorHandler) {
                    @Override
                    public void onNext(Microphone enterRoom) {
                        List<Microphone.DataBean.MicrophoneBean> microphone = enterRoom.getData().getMicrophone();
                        mAdapter.setNewData(microphone);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        mAdapter.setNewData(new ArrayList<>());
                    }
                });
    }
}
