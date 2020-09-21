package com.example.tongpao.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tongpao.R;
import com.example.tongpao.base.BaseActivity;
import com.example.tongpao.interfaces.IBasePresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneLoginActivity extends BaseActivity implements View.OnClickListener {

    private int countSeconds = 60;//倒计时秒数
    private EditText et_phone_login;
    private EditText et_verification_code;
    private Button btn_verification_code;
    private Button login_btn;
    private String userinfomsg;
    private Handler mCountHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (countSeconds > 0) {
                --countSeconds;
                btn_verification_code.setText("(" + countSeconds + ")后获取验证码");
                mCountHandler.sendEmptyMessageDelayed(0, 1000);
            } else {
                countSeconds = 60;
                btn_verification_code.setText("请重新获取验证码");
            }
        }
    };
    @Override
    protected IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        et_phone_login = findViewById(R.id.et_phone_login);
        et_verification_code = findViewById(R.id.et_verification_code);
        btn_verification_code = findViewById(R.id.btn_verification_code);
        login_btn = findViewById(R.id.login_btn);
        btn_verification_code.setOnClickListener(this);
        login_btn.setOnClickListener(this);

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_phone_login;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_verification_code:
                    if (countSeconds==60){
                        String phone = et_phone_login.getText().toString();
                        getMobiile(phone);
                    }else {
                        Toast.makeText(PhoneLoginActivity.this, "不能重复发送验证码", Toast.LENGTH_SHORT).show();
                    }
                break;
            case R.id.login_btn:

                break;
        }
    }

    private void getMobiile(String phone) {
        if ("".equals(phone)) {
            Log.e("tag", "mobile=" + phone);
            new AlertDialog.Builder(this).setTitle("提示").setMessage("手机号码不能为空").setCancelable(true).show();
        } else if (isMobileNO(phone) == false) {
            new AlertDialog.Builder(this).setTitle("提示").setMessage("请输入正确的手机号码").setCancelable(true).show();
        } else {
            Log.e("tag", "输入了正确的手机号");
            requestVerifyCode(phone);
        }
    }

    private void requestVerifyCode(String phone) {

    }
    //使用正则表达式判断电话号码
    private boolean isMobileNO(String phone) {
        Pattern p = Pattern.compile("^(13[0-9]|15([0-3]|[5-9])|14[5,7,9]|17[1,3,5,6,7,8]|18[0-9])\\d{8}$");
        Matcher m = p.matcher(phone);
        System.out.println(m.matches() + "---");
        return m.matches();
    }
    //获取验证码信息,进行计时操作
//    private void startCountBack() {
//        (PhoneLoginActivity.this.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                et_phone_login.setText(countSeconds + "");
//                mCountHandler.sendEmptyMessage(0);
//            }
//        });
//    }
}