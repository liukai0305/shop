package com.qutu.talk.popup;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.tu.loadingdialog.LoadingDailog;
import com.qutu.talk.R;
import com.qutu.talk.activity.room.AdminHomeActivity;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.bean.BaseBean;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.Constant;
import com.qutu.talk.view.ShapeTextView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 抱人上麦dialog
 */
public class CountPKDialog extends Dialog {


    @BindView(R.id.btn_to_open)
    ShapeTextView mBtnToOpen;

    private AdminHomeActivity mContext;

    CommonModel mCommonModel;

    String mUId;

    RxErrorHandler mRxErrorHandler;

    String mOpenStatus = "";

    public CountPKDialog(@NonNull Activity context) {
        super(context, R.style.myChooseDialog);
        mContext = (AdminHomeActivity) context;
    }


    public void setInfo(CommonModel commonModel, String uId, RxErrorHandler rxErrorHandler,String openStatus) {
        mCommonModel = commonModel;
        mUId = uId;
        mRxErrorHandler = rxErrorHandler;
        this.mOpenStatus = openStatus;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_count_pk);

        ButterKnife.bind(this);

        setWidows();

        if(mOpenStatus.equals("1")){
            mBtnToOpen.setText("关闭数值玩法");
        } else {
            mBtnToOpen.setText("开启数值玩法");
        }

    }

    private void setWidows() {

        Display display = getWindow().getWindowManager().getDefaultDisplay();

        WindowManager.LayoutParams lp = getWindow().getAttributes();

        lp.width = (int) (display.getWidth());

        lp.alpha = 1.0f;

        lp.gravity = Gravity.BOTTOM;

        getWindow().setAttributes(lp);

        this.setCanceledOnTouchOutside(true);

    }

    @OnClick({R.id.btn_to_open})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_to_open:

                if(mOpenStatus.equals("1")){

                    MaterialDialog dialog = new MaterialDialog.Builder(mContext)
                            .customView(R.layout.dialog_confirm_close_pk,false)
                            .show();

                    TextView cancle = (TextView) dialog.findViewById(R.id.tv_cancel);

                    TextView confirm = (TextView) dialog.findViewById(R.id.tv_confirm);

                    cancle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            requestServer();
                        }
                    });

                } else {
                    requestServer();
                }

                break;
        }
    }

    private void requestServer(){
        String type = mOpenStatus.equals("1")?"off":"on";
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(mContext)
                .setMessage("加载中...")
                .setCancelable(true)
                .setCancelOutside(true);
        LoadingDailog dialog = loadBuilder.create();
        dialog.show();
        RxUtils.loading(mCommonModel.play_num_switch(mUId,
                type), mContext)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mRxErrorHandler) {
                    @Override
                    public void onNext(BaseBean roomUsersBean) {
                        dialog.dismiss();
                        EventBus.getDefault().post(new FirstEvent("指定发送", Constant.COUNT_PK));
                        mContext.toast(roomUsersBean.getMessage());
                        dismiss();
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        dialog.dismiss();
                    }
                });
    }


}
