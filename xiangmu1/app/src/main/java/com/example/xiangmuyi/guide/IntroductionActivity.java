package com.example.xiangmuyi.guide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.xiangmuyi.MainActivity;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.utils.SpUtil;

import java.util.ArrayList;

public class IntroductionActivity extends AppCompatActivity {

    private ViewPager vp;
    private ArrayList<View> views;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        sp = getSharedPreferences("viewpager", MODE_PRIVATE);
        if (sp.getBoolean("flag", false)) {
            //如果是第二次登录，直接跳转页面，并关闭本页main
            startActivity(new Intent(IntroductionActivity.this, MainActivity.class));
            finish();
        } else {
            initView();
        }
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        //创建集合，添加布局
        views = new ArrayList<View>();
        initData();
        //创建PagerAdapter适配器
        ViewPager_item viewPager_item = new ViewPager_item(views);
        //连接适配器
        vp.setAdapter(viewPager_item);

    }

    private void initData() {
        //加载布局
        LayoutInflater inflater = LayoutInflater.from(this);
        //添加布局到集合中
        views.add(inflater.inflate(R.layout.viewpager_item1, null));
        views.add(inflater.inflate(R.layout.viewpager_item2, null));
        View inflate1 = inflater.inflate(R.layout.viewpager_item3, null);
        views.add(inflate1);
        TextView tv = inflate1.findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //存放判断值
                SharedPreferences.Editor edit = sp.edit();
                edit.putBoolean("flag", true);
                edit.commit();
                startActivity(new Intent(IntroductionActivity.this, MainActivity.class));
                finish();
            }
        });
    }


//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }

}