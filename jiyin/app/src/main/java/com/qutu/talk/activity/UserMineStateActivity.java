package com.qutu.talk.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jess.arms.di.component.AppComponent;
import com.qutu.talk.R;
import com.qutu.talk.base.MyBaseArmActivity;

/**
 * 个人主页 主人页
 */
public class UserMineStateActivity extends MyBaseArmActivity {


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_state_user;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

}
