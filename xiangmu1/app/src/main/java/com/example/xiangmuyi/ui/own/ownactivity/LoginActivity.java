package com.example.xiangmuyi.ui.own.ownactivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.xiangmuyi.MainActivity;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.app.MyApp;
import com.example.xiangmuyi.base.BaseActivity;
import com.example.xiangmuyi.common.CustomVideoView;
import com.example.xiangmuyi.interfaces.IBasePersenter;
import com.umeng.qq.handler.UmengQQHandler;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private CustomVideoView video;
    private Button deng;
    private Button zhu;
    private ImageView qq;
    private ImageView weibo;
    private ImageView weixin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        video = (CustomVideoView) findViewById(R.id.video);
        video.setOnClickListener(this);
        deng = (Button) findViewById(R.id.deng);
        deng.setOnClickListener(this);
        zhu = (Button) findViewById(R.id.zhu);
        zhu.setOnClickListener(this);
        qq = (ImageView) findViewById(R.id.qq);
        qq.setOnClickListener(this);
        weibo = (ImageView) findViewById(R.id.weibo);
        weibo.setOnClickListener(this);
        weixin = (ImageView) findViewById(R.id.weixin);
        weixin.setOnClickListener(this);
        //背景视频播放
        video.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.topao));
        video.start();
            //循环播放
        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    video.start();
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
    protected IBasePersenter initPersenter() {
        return null;
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.deng:
                break;
            case R.id.zhu:
                Intent intent = new Intent(this, UserLoginActivity.class);
               startActivity(intent);
                finish();
                break;
            case R.id.qq:
                login( SHARE_MEDIA.QQ);
                break;
            case R.id.weibo:
                login( SHARE_MEDIA.SINA);
                break;
            case R.id.weixin:
                login( SHARE_MEDIA.WEIXIN);
                break;
        }
    }

    private void login(SHARE_MEDIA type) {
        UMShareAPI.get(this).getPlatformInfo(this,type, authListener);
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
        video.stopPlayback();
    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_SHORT).show();
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(LoginActivity.this, "失败：", Toast.LENGTH_SHORT).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}