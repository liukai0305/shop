package com.qutu.talk.popup;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qutu.talk.R;
import com.qutu.talk.activity.room.AdminHomeActivity;
import com.qutu.talk.adapter.LocalMusciAdapter;
import com.qutu.talk.adapter.PaimaiAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.BaseBean;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.GiftListBean;
import com.qutu.talk.bean.MessageBean;
import com.qutu.talk.bean.Microphone;
import com.qutu.talk.bean.WaitList;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.BaseUtils;
import com.qutu.talk.utils.Constant;
import com.qutu.talk.utils.ToastUtil;
import com.qutu.talk.view.ShapeTextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


/**
 * 排麦,popupwindow和dialog不能嵌套viewpager使用，此处是一个巨坑，草特大爷
 */
@SuppressLint("ValidFragment")
public class PaimaiWindow extends PopupWindow {


    @BindView(R.id.textNum)
    TextView textNum;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.btn_ok)
    ShapeTextView btnOk;
    private String id = "";//礼物id
    private AdminHomeActivity context;
    private CommonModel commonModel;
    private String uid;
    private PaimaiAdapter paimaiAdapter;
    private String mType = "";//1 普通或者点单 2 试音
    private boolean mIsPaiDan = false;//是否派单厅
    public PaimaiWindow(AdminHomeActivity context,String uid,
                        CommonModel commonModel,String type,boolean isPaiDan) {
        super(context);
        this.context = context;
        this.commonModel = commonModel;
        this.uid = uid;
        this.mType = type;
        this.mIsPaiDan = isPaiDan;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_paimai, null);
        ButterKnife.bind(this, view);
        // 设置SelectPicPopupWindow的View
        this.setContentView(view);
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
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        //设置透明
        WindowManager.LayoutParams params = context.getWindow().getAttributes();
        params.alpha = 0.7f;
        context.getWindow().setAttributes(params);
        paimaiAdapter = new PaimaiAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(paimaiAdapter);
        loadData();
    }

    private void loadData() {
        RxUtils.loading(commonModel.waitList(uid,String.valueOf(UserManager.getUser().getUserId())))
                .subscribe(new ErrorHandleSubscriber<WaitList>(context.mErrorHandler) {
                    @Override
                    public void onNext(WaitList waitList) {

                        if(mType.equals("1")){
                            paimaiAdapter.setNewData(waitList.getData().getData());
                        } else {
                            paimaiAdapter.setNewData(waitList.getData().getShiyin_data());

                        }
                        if(!mIsPaiDan){
                            textNum.setText("当前麦序" + waitList.getData().getTotal() + "人");
                            btnOk.setText("取消排麦");
                        } else {
                            if(mType.equals("1")){
                                textNum.setText("当前点单" + waitList.getData().getTotal() + "人");
                                btnOk.setText("取消点单");
                            } else {
                                textNum.setText("当前试音" + waitList.getData().getTotal() + "人");
                                btnOk.setText("取消试音");
                            }

                        }
                    }
                });
    }


    @Override
    public void dismiss() {
        super.dismiss();
        MyBaseArmActivity myBaseArmActivity = (MyBaseArmActivity) context;
        WindowManager.LayoutParams params = myBaseArmActivity.getWindow().getAttributes();
        params.alpha = 1f;
        myBaseArmActivity.getWindow().setAttributes(params);
    }

    @OnClick({R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                RxUtils.loading(commonModel.delWait(String.valueOf(UserManager.getUser().getUserId())))
                        .subscribe(new ErrorHandleSubscriber<BaseBean>(context.mErrorHandler) {
                            @Override
                            public void onNext(BaseBean giftListBean) {
                                dismiss();
                                //发送广播，通知其他人刷新排麦
                                context.getMePaiMaiPosition();
                                context.sendChannelMessage(BaseUtils.getJson("17", "", "", ""));
                            }
                        });
                break;
        }
    }

}
