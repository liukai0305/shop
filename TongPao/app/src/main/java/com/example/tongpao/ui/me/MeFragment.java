package com.example.tongpao.ui.me;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tongpao.R;
import com.example.tongpao.base.BaseFragment;
import com.example.tongpao.interfaces.IBasePresenter;
import com.example.tongpao.ui.activity.LoginActivity;

public class MeFragment extends BaseFragment {

    @Override
    protected IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayout() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        return R.layout.fragment_me;
    }
}