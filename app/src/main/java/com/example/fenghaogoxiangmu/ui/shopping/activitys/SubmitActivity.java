package com.example.fenghaogoxiangmu.ui.shopping.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fenghaogoxiangmu.R;
import com.example.fenghaogoxiangmu.ui.own.acivity.SiteActivity;

public class SubmitActivity extends AppCompatActivity {

    private ImageView order_iv_finsh;
    private TextView order_tv_actually;
    private TextView order_gopayment;
    private TextView tv_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        initView();
    }

    private void initView() {
        order_iv_finsh = (ImageView) findViewById(R.id.order_iv_finsh);
        order_tv_actually = (TextView) findViewById(R.id.order_tv_actually);
        order_gopayment = (TextView) findViewById(R.id.order_gopayment);
        tv_name = (TextView) findViewById(R.id.tv_name);

    }
}