package com.example.xiaogmulanxi;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.xiaogmulanxi.Fragments.CollFragment;
import com.example.xiaogmulanxi.Fragments.DistFragment;
import com.example.xiaogmulanxi.Fragments.HomeFragment;
import com.example.xiaogmulanxi.Fragments.SPatFragment;
import com.example.xiaogmulanxi.adapter.VpAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private ArrayList<Fragment> list;
    private VpAdapter vpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //setTitle("返回");
        //collapsingToolbar.setTitle("返回");
        collapsingToolbar.setExpandedTitleColor(Color.parseColor("#00ffffff"));
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);
        list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new CollFragment());
        list.add(new DistFragment());
        list.add(new SPatFragment());
        vpAdapter = new VpAdapter(getSupportFragmentManager(), 0, list);
        viewpager.setAdapter(vpAdapter);
        tablayout.setupWithViewPager(viewpager);
        tablayout.getTabAt(0).setText("首页");
        tablayout.getTabAt(1).setText("动态");
        tablayout.getTabAt(2).setText("下表");
        tablayout.getTabAt(3).setText("威胁");
        tablayout.setTabMode(TabLayout.MODE_FIXED);
    }


}