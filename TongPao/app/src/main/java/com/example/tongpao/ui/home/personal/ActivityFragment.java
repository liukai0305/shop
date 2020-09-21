package com.example.tongpao.ui.home.personal;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tongpao.R;
import com.example.tongpao.adapter.PersonalActivityAdapter;
import com.example.tongpao.base.BaseFragment;
import com.example.tongpao.bean.PersonalActivityBean;
import com.example.tongpao.interfaces.home.IHomePersonal;
import com.example.tongpao.presenters.personal.PersonalActivityPresenter;

import java.util.ArrayList;
import java.util.List;


public class ActivityFragment extends BaseFragment<IHomePersonal.PersonalActivityPresenter> implements IHomePersonal.PersonalActivityView {

    private RecyclerView activaty_rv;
    private List<PersonalActivityBean.DataBean.DynamicsBean> dynamicsBeans;
    private PersonalActivityAdapter personalActivityAdapter;

    @Override
    protected IHomePersonal.PersonalActivityPresenter initPresenter() {
        return new PersonalActivityPresenter();
    }

    @Override
    protected void initData() {
        Presenter.getPersonalActivity();
    }

    @Override
    protected void initView() {
        activaty_rv = getActivity().findViewById(R.id.activaty_rv);
        activaty_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        dynamicsBeans = new ArrayList<>();
        personalActivityAdapter = new PersonalActivityAdapter(getActivity(), dynamicsBeans);
        activaty_rv.setAdapter(personalActivityAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_activity;
    }

    @Override
    public void getPersonalActivityReturn(PersonalActivityBean result) {
        List<PersonalActivityBean.DataBean.DynamicsBean> dynamics = result.getData().getDynamics();
        if (result.getStatus().getStatusCode()==100){
            dynamicsBeans.addAll(dynamics);
            personalActivityAdapter.notifyDataSetChanged();
        }
    }

}