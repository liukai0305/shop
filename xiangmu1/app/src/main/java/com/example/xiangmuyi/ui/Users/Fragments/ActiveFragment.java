package com.example.xiangmuyi.ui.Users.Fragments;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.adapter.homeadapter.DongTaiAdapter;
import com.example.xiangmuyi.base.BaseFragment;
import com.example.xiangmuyi.bean.homebean.PersonalActivityBean;
import com.example.xiangmuyi.bean.homebean.PersonalArticleBean;
import com.example.xiangmuyi.bean.homebean.PersonalMsgBean;
import com.example.xiangmuyi.bean.homebean.PersonalPostBean;
import com.example.xiangmuyi.bean.homebean.UserInfoBean;
import com.example.xiangmuyi.interfaces.users.IUsers;
import com.example.xiangmuyi.persenter.users.PersonalPersenter;

import java.util.ArrayList;

import butterknife.BindView;


public class ActiveFragment extends BaseFragment<IUsers.Persenter> implements IUsers.View {


    @BindView(R.id.rcy)
    RecyclerView rcy;
    private ArrayList<PersonalActivityBean.DataBean.DynamicsBean> list;
    private DongTaiAdapter dongTaiAdapter;


    @Override
    protected int getLayout() {
        return R.layout.fragment_active;
    }

    @Override
    protected void initView() {
        rcy.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        dongTaiAdapter = new DongTaiAdapter(list, getActivity());
        rcy.setAdapter(dongTaiAdapter);
    }

    @Override
    protected IUsers.Persenter initPersenter() {
        return new PersonalPersenter();
    }

    @Override
    protected void initData() {
        persenter.getPersonalActivity();
    }

    @Override
    public void getPersonalReturn(UserInfoBean result) {

    }

    @Override
    public void getPersonalActivityReturn(PersonalActivityBean result) {
        list.addAll(result.getData().getDynamics());
        dongTaiAdapter.notifyDataSetChanged();
    }

    @Override
    public void getPersonalPostReturn(PersonalPostBean result) {

    }

    @Override
    public void getPersonalMsgReturn(PersonalMsgBean result) {

    }

    @Override
    public void getPersonalArticleReturn(PersonalArticleBean result) {

    }
}