package com.qutu.talk.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.BaseBean;
import com.qutu.talk.bean.UserInfo;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class HelpRechargeActivity extends MyBaseArmActivity {

    @Inject
    CommonModel commonModel;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_userId)
    TextView tvUserId;
    @BindView(R.id.et_diamond)
    EditText etDiamond;
    @BindView(R.id.et_rewrite)
    EditText etRewrite;
    @BindView(R.id.tv_ok)
    TextView tvOk;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    private static UserInfo.DataBean mUserInfo;

    public static void open(Context context, UserInfo.DataBean userInfo) {
        mUserInfo = userInfo;
        context.startActivity(new Intent(context, HelpRechargeActivity.class));
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_help_recharge;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ArmsUtils.obtainAppComponentFromContext(this)
                .imageLoader()
                .loadImage(this, ImageConfigImpl
                        .builder()
                        .url(mUserInfo.getHeadimgurl())
                        .placeholder(R.mipmap.default_home)
                        .imageView(ivIcon)
                        .errorPic(R.mipmap.default_home)
                        .build());
        tvUsername.setText(mUserInfo.getNickname());
        tvUserId.setText(mUserInfo.getId()+"");

    }

    @OnClick(R.id.tv_ok)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_ok:
                if(TextUtils.isEmpty(etDiamond.getText().toString().trim())){
                    showToast("请输入代充能量豆");return;
                }
                if(!etDiamond.getText().toString().trim().equals(etRewrite.getText().toString().trim())){
                    showToast("两次输入不一致");return;
                }
                RxUtils.loading(commonModel.helpRecharge(
                        mUserInfo.getId()+"",
                        String.valueOf(UserManager.getUser().getUserId()),
                        etDiamond.getText().toString().trim()),
                        this)
                        .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                            @Override
                            public void onNext(BaseBean baseBean) {
                                showToast("代充成功");
                                finish();
                            }
                        });
                break;
        }
    }
}
