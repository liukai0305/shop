package com.example.xiangmuyi.ui.own.ownactivity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiangmuyi.MainActivity;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseActivity;
import com.example.xiangmuyi.bean.OwnBean.LoginBean;
import com.example.xiangmuyi.interfaces.own.IOwn;
import com.example.xiangmuyi.persenter.own.LoginPersenter;
import com.example.xiangmuyi.utils.SpUtil;
import com.example.xiangmuyi.utils.SpUtils;
import com.example.xiangmuyi.utils.ToastUtil;
import com.hbb20.CountryCodePicker;

import butterknife.BindView;

public class UserLoginActivity extends BaseActivity<IOwn.LoginPersenter> implements IOwn.LoginView {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.user_login)
    TextView userLogin;
    @BindView(R.id.et_other_phone)
    EditText etOtherPhone;
    @BindView(R.id.ccp)
    CountryCodePicker ccp;
    @BindView(R.id.img_reset)
    ImageView imgReset;
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    @BindView(R.id.et_other_password)
    EditText etOtherPassword;
    @BindView(R.id.et_other_password2)
    EditText etOtherPassword2;
    @BindView(R.id.tv_version)
    TextView tvVersion;
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
    private String name;
    private String pwd;


    @Override
    protected int getLayout() {
        return R.layout.activity_user_login;
    }

    @Override
    protected void initView() {
        imgReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etOtherPhone.setText("x");
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserLoginActivity.this, RegisterActivity.class));
            }
        });
        btnOtherLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etOtherPhone.getText().toString();
                pwd = etOtherPassword.getText().toString();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd)) {
                    // SpUtil.getParam("username","");
                    persenter.getlogin(name, pwd);
                } else {
                    ToastUtil.showShort("账号密码不能为空");
                }

            }
        });

    }

    @Override
    protected IOwn.LoginPersenter initPersenter() {
        return new LoginPersenter();
    }

    @Override
    protected void initData() {

    }

    private static final String TAG = "UserLoginActivity";

    @Override
    public void getlogin(LoginBean login) {
        int err = login.getErr();
        Log.d(TAG, "getlogin: " + login.getErr());
        if (err == 200) {
            SpUtil.setParam("isok", true);
//            String username = login.getData().getUsername();
//            SharedPreferences data = getSharedPreferences("data", MODE_PRIVATE);
//            SharedPreferences.Editor edit = data.edit();
//            edit.putString(name, username);
//            edit.commit();

            //把登录成功以后的数据存入sp
            SpUtils.getInstance().setValue("token", login.getData().getToken());
            SpUtils.getInstance().setValue("username", login.getData().getUsername());
            SpUtils.getInstance().setValue("avater", login.getData().getAvater());
            SpUtils.getInstance().setValue("phone", login.getData().getPhone());
            SpUtils.getInstance().setValue("adress", login.getData().getAdress());
            SpUtils.getInstance().setValue("sex", login.getData().getSex());
            SpUtils.getInstance().setValue("age", login.getData().getAge());
            SpUtils.getInstance().setValue("birthday", login.getData().getBirthday());
            startActivity(new Intent(this,MainActivity.class));
            MainActivity.setVp();
            finish();
        } else {
            Toast.makeText(this, login.getErrmsg(), Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}