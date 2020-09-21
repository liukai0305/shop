package com.qutu.talk.popup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.qutu.talk.R;
import com.qutu.talk.activity.room.AdminHomeActivity;
import com.qutu.talk.adapter.ClearMlAdapter;
import com.qutu.talk.adapter.ClearMlHistoryAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.BaseBean;
import com.qutu.talk.bean.ClearMlHistory;
import com.qutu.talk.bean.Microphone;
import com.qutu.talk.bean.PullRefreshBean;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

//清理魅力值历史记录
public class ClearMlHistoryWindow extends PopupWindow {
    private AdminHomeActivity mContext;
    private RecyclerView recyclerView;
    private ImageView ivClose;
    private CommonModel commonModel;
    private RxErrorHandler mRxErrorHandler;
    private ClearMlHistoryAdapter mAdapter;
    private List<ClearMlHistory.DataBean> mDataList = new ArrayList<>();
    private PullRefreshBean mPullRefreshBean = new PullRefreshBean();
    private String uid;//房间的uid

    public ClearMlHistoryWindow(Activity context, ViewGroup view, CommonModel commonModel, RxErrorHandler rxErrorHandler, String uid) {
        super(context);
        this.uid=uid;
        this.mContext = (AdminHomeActivity) context;
        this.commonModel = commonModel;
        this.mRxErrorHandler = rxErrorHandler;
        View view1 = LayoutInflater.from(context).inflate(R.layout.clear_ml_history_window, null);
        recyclerView = view1.findViewById(R.id.recycler);
        ivClose = view1.findViewById(R.id.iv_close);
        ivClose.setOnClickListener((v)-> dismiss());
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

        mAdapter = new ClearMlHistoryAdapter(R.layout.item_clear_ml_history, mDataList);
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

    /**
     * 列表
     */
    private void loadVedioList() {
        RxUtils.loading(commonModel.getClearMlHistory(uid,UserManager.getUser().getUserId()+""), mContext)
                .subscribe(new ErrorHandleSubscriber<ClearMlHistory>(mRxErrorHandler) {
                    @Override
                    public void onNext(ClearMlHistory clearMlHistory) {
                        List<ClearMlHistory.DataBean> list = clearMlHistory.getData();
                        mAdapter.setNewData(list);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        mAdapter.setNewData(new ArrayList<>());
                    }
                });
    }
}
