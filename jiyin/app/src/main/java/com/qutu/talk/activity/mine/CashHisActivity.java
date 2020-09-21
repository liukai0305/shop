package com.qutu.talk.activity.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.jess.arms.di.component.AppComponent;
import com.qutu.talk.R;
import com.qutu.talk.adapter.RankPagerAdapter;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.fragment.CashHisFragment;
import com.qutu.talk.fragment.IncomeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 我的金币页面跳转过来的记录页面
 */
public class CashHisActivity extends MyBaseArmActivity {

    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private List<String> titleList = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    private RankPagerAdapter mAdapter;

    private int mType;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_cash_his;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mType = getIntent().getIntExtra("type", 0);

        titleList.add("礼物收入");
        titleList.add("打赏礼物");
        titleList.add("钻石兑换");
        titleList.add("钻石提现");
        IncomeFragment incomeFragment1 = IncomeFragment.getInstance(0);
        IncomeFragment incomeFragment2 = IncomeFragment.getInstance(1);
        CashHisFragment messageFansFragment1 = CashHisFragment.getInstance(0, mType);
        CashHisFragment messageFansFragment2 = CashHisFragment.getInstance(1, mType);
        mFragments.add(incomeFragment1);
        mFragments.add(incomeFragment2);
        mFragments.add(messageFansFragment1);
        mFragments.add(messageFansFragment2);

        mAdapter = new RankPagerAdapter(getSupportFragmentManager(), mFragments, titleList);

        viewPager.setAdapter(mAdapter);
        tabLayout.setViewPager(viewPager);
        tabLayout.setCurrentTab(0);
        tabLayout.setSnapOnTabClick(true);
        viewPager.setOffscreenPageLimit(mFragments.size());

    }

}
