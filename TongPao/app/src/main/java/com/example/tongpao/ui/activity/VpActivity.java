package com.example.tongpao.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.tongpao.R;
import com.example.tongpao.adapter.VpAdapter;
import com.example.tongpao.base.BaseActivity;
import com.example.tongpao.bean.HomeRecommendBean;
import com.example.tongpao.common.Constants;
import com.example.tongpao.interfaces.IBasePresenter;

import java.util.ArrayList;
import java.util.List;

public class VpActivity extends BaseActivity {

    private ViewPager vp;
    private TextView vp_num;
    private PhotoViewPager photoviewpager;
    private int pos;

    @Override
    protected IBasePresenter initPresenter() {
        return null;
    }

    private static final String TAG = "VpActivity";
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        photoviewpager = findViewById(R.id.photoviewpager);
        vp_num = findViewById(R.id.vp_num);

        Intent intent = getIntent();
        final List<HomeRecommendBean.DataBean.PostDetailBean.ImagesBean> imagesBean = Constants.imagesBean;
        pos = intent.getIntExtra("pos", 0);
        VpAdapter vpAdapter = new VpAdapter(this, imagesBean);
        photoviewpager.setAdapter(vpAdapter);
        photoviewpager.setCurrentItem(pos);
        vp_num.setText(pos +1+"/"+imagesBean.size());
        photoviewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                pos = position;
                vp_num.setText(pos +1+"/"+imagesBean.size());
            }
        });

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_vp;
    }

}