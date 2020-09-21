package com.example.xiangmuyi.wxapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.xiangmuyi.R;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

public class WXEntryActivity extends WXCallbackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_w_x_entry);
    }
}