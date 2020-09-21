package com.example.xiangmuyi.ui.own.shezhi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.app.MyApp;
import com.example.xiangmuyi.ui.own.ownactivity.UserLoginActivity;
import com.example.xiangmuyi.utils.SpUtil;
import com.example.xiangmuyi.utils.SpUtils;

public class ShiZhiActivity extends AppCompatActivity implements View.OnClickListener {

    private Button tui;
    private TextView tvReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shi_zhi);
        initView();
    }

    private void initView() {
        tui = (Button) findViewById(R.id.tui);
        tvReturn = (TextView) findViewById(R.id.tv_return);

        tui.setOnClickListener(this);
        tvReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tui:
                SharedPreferences sp = MyApp.app.getSharedPreferences("viewpager",
                        Context.MODE_PRIVATE);
                sp.edit().clear().commit();
                SpUtil.setParam("isok",false);
                startActivity(new Intent(this, UserLoginActivity.class));
                finish();
                break;
            case R.id.tv_return:
                SharedPreferences preferences = getSharedPreferences("viewpager", MODE_PRIVATE);
                SharedPreferences.Editor edit = preferences.edit();
                edit.putBoolean("flag", false);
                edit.commit();
                System.exit(0);
                break;
        }
    }
}