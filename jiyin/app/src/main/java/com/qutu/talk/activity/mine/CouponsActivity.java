package com.qutu.talk.activity.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.qutu.talk.R;
import com.qutu.talk.adapter.MyPagerAdapterTwo;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.fragment.MyCouponFragment;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.mytablayout.TabLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CouponsActivity extends MyBaseArmActivity {

    @Inject
    CommonModel commonModel;
    @BindView(R.id.coupons_tab)
    TabLayout couponsTab;
    @BindView(R.id.coupons_viewpager)
    ViewPager couponsViewpager;

    private List<String> titleList = new ArrayList<>(); //tabLayout的数据源
    private List<Fragment> mFragments = new ArrayList<>(); //fragment的数据源
    private MyPagerAdapterTwo mAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_coupons;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle("优惠券");
        initTabLayout();

        //添加TabLayout的数据
        titleList.add("未使用");
        titleList.add("已使用");
        titleList.add("已过期");

        //添加Fragment数据
        mFragments.add(MyCouponFragment.getInstance("1"));
        mFragments.add(MyCouponFragment.getInstance("2"));
        mFragments.add(MyCouponFragment.getInstance("3"));

        mAdapter = new MyPagerAdapterTwo(getSupportFragmentManager(), mFragments, titleList);
        couponsViewpager.setAdapter(mAdapter);
        couponsTab.setupWithViewPager(couponsViewpager);

        couponsViewpager.setCurrentItem(0);
        couponsTab.getTabAt(0).select();

    }

    private void initTabLayout() {
        couponsTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                initUpData(tab, true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                initUpData(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initUpData(TabLayout.Tab tab, boolean boo) {
        TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.hei_title_text_layout, null);
        if (boo) {
            textView.setSelected(true);
            textView.setText(tab.getText());
            textView.setTextSize(16);
            textView.setTextColor(getResources().getColor(R.color.font_ff3e6d));
            tab.setCustomView(textView);
        } else {
            tab.setCustomView(null);
        }
    }

}
