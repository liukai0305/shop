package com.example.xiangmuyi.ui.own.ownactivity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseActivity;
import com.example.xiangmuyi.bean.OwnBean.RegisterBean;
import com.example.xiangmuyi.interfaces.own.IOwn;
import com.example.xiangmuyi.persenter.own.OwnPersenter;
import com.example.xiangmuyi.utils.MD5Utils;

import butterknife.BindView;

public class RegisterActivity extends BaseActivity<IOwn.RegisterPersenter> implements IOwn.RegisterView {
    @BindView(R.id.et_register_name)
    EditText etRegisterName;
    @BindView(R.id.et_register_password)
    EditText etRegisterPassword;
    @BindView(R.id.bt_que_login)
    EditText btQueLogin;
    @BindView(R.id.btn_register_login)
    Button btnRegisterLogin;
    private String name;

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        btnRegisterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
            }
        });
    }

    @Override
    protected IOwn.RegisterPersenter initPersenter() {
        return new OwnPersenter();
    }

    @Override
    protected void initData() {
        name = etRegisterName.getText().toString();
        String pwd = etRegisterPassword.getText().toString();
        String quepwd = btQueLogin.getText().toString();
        if (!TextUtils.isEmpty(name)){
            if (!TextUtils.isEmpty(pwd)||!TextUtils.isEmpty(quepwd)){
                if (pwd.equals(quepwd)){
                    String digest = MD5Utils.digest(pwd);
                    Log.d("tag", "onViewClicked: " + pwd + "--" + quepwd);
                    persenter.getRegisterlogin(name,pwd);
                }else {
                    Toast.makeText(this, "两次密码不一样", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getRegisterlogin(RegisterBean login) {
        if (login.getErr()==200&&login.getData().equals("注册成功")){
            Toast.makeText(this, login.getData(), Toast.LENGTH_SHORT).show();
           // SpUtil.setParam("username",name);
            finish();
        }else {
            Toast.makeText(this, "注册失败" , Toast.LENGTH_SHORT).show();
        }
    }
}