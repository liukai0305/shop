package com.qutu.talk.activity.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.activity.HelpRechargeActivity;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.UserInfo;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class DiamondHelpRechargeActivity extends MyBaseArmActivity {

    @BindView(R.id.tv_help_history)
    TextView tvHelpHistory;
    @Inject
    CommonModel commonModel;
    @BindView(R.id.et_user_id)
    EditText etUserId;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_diamond_help_recharge;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        tvHelpHistory.setVisibility(View.VISIBLE);

    }

    @OnClick({R.id.tv_help_history,R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_help_history:
                ArmsUtils.startActivity(HelpRechargeHistoryActivity.class);
                break;
            case R.id.tv_ok:
                if(TextUtils.isEmpty(etUserId.getText().toString().trim())){
                    showToast("请输入用户id");return;
                }
                RxUtils.loading(commonModel.getUserInfoById(etUserId.getText().toString().trim()), this)
                        .subscribe(new ErrorHandleSubscriber<UserInfo>(mErrorHandler) {
                            @Override
                            public void onNext(UserInfo userInfo) {
                                HelpRechargeActivity.open(DiamondHelpRechargeActivity.this,userInfo.getData());
                            }
                        });
                break;
        }
    }

}
