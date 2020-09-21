package com.qutu.talk.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jess.arms.di.component.AppComponent;
import com.qutu.talk.R;
import com.qutu.talk.adapter.RankPagerAdapter;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.fragment.MessageFriendFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyFriendActivity extends MyBaseArmActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private RankPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

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
        return R.layout.activity_my_friend;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mFragments.add(new MessageFriendFragment());
        titleList.add("我的好友");
        mAdapter = new RankPagerAdapter(getSupportFragmentManager(), mFragments, titleList);
        viewPager.setAdapter(mAdapter);
    }

    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
