package com.example.tongpao.ui.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tongpao.R;
import com.example.tongpao.base.BaseActivity;
import com.example.tongpao.interfaces.IBasePresenter;
import com.example.tongpao.ui.view.CustomVideoView;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private CustomVideoView customvideo;
    private ImageView login_iv_logo;
    private TextView login_tv_text;
    private Button login_phone_login;
    private Button login_password_login;

    @Override
    protected IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    protected void initView() {
        customvideo = (CustomVideoView) findViewById(R.id.customvideo);
        login_iv_logo = (ImageView) findViewById(R.id.login_iv_logo);
        login_tv_text = (TextView) findViewById(R.id.login_tv_text);
        login_phone_login = (Button) findViewById(R.id.login_phone_login);
        login_password_login = (Button) findViewById(R.id.login_password_login);
        login_phone_login.setOnClickListener(this);;
        login_password_login.setOnClickListener(this);;
        //背景视频播放
        customvideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.qwer));
        customvideo.start();
        //循环播放
        customvideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                customvideo.start();
                mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                    @Override
                    public boolean onInfo(MediaPlayer mp, int what, int extra) {
                        return false;
                    }
                });
            }
        });


    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initView();
    }

    //防止锁屏或者切出的时候，音乐在播放
    @Override
    protected void onStop() {
        super.onStop();
        customvideo.stopPlayback();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_phone_login:
                Intent intent = new Intent(LoginActivity.this, PhoneLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.login_password_login:

            break;
        }
    }
}