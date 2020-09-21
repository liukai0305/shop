package com.example.fenghaogoxiangmu.ui.own.auth.activity;

import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fenghaogoxiangmu.R;
import com.example.fenghaogoxiangmu.base.BaseActivity;
import com.example.fenghaogoxiangmu.bean.user.LoginBean;
import com.example.fenghaogoxiangmu.interfaces.auth.IAuth;
import com.example.fenghaogoxiangmu.persenter.Auth.LoginPersenter;
import com.example.fenghaogoxiangmu.utils.SpUtils;

import butterknife.BindView;

public class LoginActivity extends BaseActivity<IAuth.LoginPresenter> implements IAuth.LoginView {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.user_login)
    TextView userLogin;
    @BindView(R.id.et_other_phone)
    EditText etOtherPhone;
    @BindView(R.id.img_reset)
    ImageView imgReset;
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    @BindView(R.id.et_other_password)
    EditText etOtherPassword;
    @BindView(R.id.rl_2)
    RelativeLayout rl2;
    @BindView(R.id.tv_other_login)
    TextView tvOtherLogin;
    @BindView(R.id.tv_other_login2)
    TextView tvOtherLogin2;
    @BindView(R.id.rl_3)
    RelativeLayout rl3;
    @BindView(R.id.tv_forget)
    TextView tvForget;
    @BindView(R.id.btn_other_login)
    Button btnOtherLogin;
    @BindView(R.id.imv_pwd)
    ImageView imvPwd;
    private String name;
    private String pwd;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    protected void initView() {
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        btnOtherLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etOtherPhone.getText().toString();
                pwd = etOtherPassword.getText().toString();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd)) {
                    if (pwd.length() >= 6) {
                        persenter.getLogin(name, pwd);
                    } else {
                        Toast.makeText(LoginActivity.this, "大于六", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imvPwd.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                if (etOtherPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {//不可见
                    imvPwd.setImageResource(R.drawable.ic_baseline_mood_bad_24);
                    etOtherPassword.setKeyListener(DigitsKeyListener.getInstance("1234567890"));
                    etOtherPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                } else {
                    etOtherPassword.setKeyListener(DigitsKeyListener.getInstance("1234567890"));
                    imvPwd.setImageResource(R.drawable.ic_baseline_mood_24);
                    etOtherPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
            }
        });
    }
    //是否显示密码


    @Override
    protected IAuth.LoginPresenter initPersenter() {
        return new LoginPersenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void getLogin(LoginBean bean) {
        if (bean.getData().getCode() == 200) {
            SpUtils.getInstance().setValue("token", bean.getData().getToken());
            SpUtils.getInstance().setValue("username", bean.getData().getUserInfo().getNickname());
            SpUtils.getInstance().setValue("avater", bean.getData().getUserInfo().getAvatar());
            finish();
        } else {
            Toast.makeText(this, bean.getErrmsg(), Toast.LENGTH_SHORT).show();
        }
    }

}