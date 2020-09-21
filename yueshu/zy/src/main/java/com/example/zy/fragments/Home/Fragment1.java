package com.example.zy.fragments.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zy.R;
import com.example.zy.adapter.FragmentAdapter;
import com.example.zy.base.BaseFragment;
import com.example.zy.bean.UsersBean;
import com.example.zy.net.IMainContract;
import com.example.zy.presenter.HomePresenterImpl;

import java.util.ArrayList;

import butterknife.BindView;


public class Fragment1 extends BaseFragment<HomePresenterImpl>implements IMainContract.IFragmentView {


    @BindView(R.id.rcy1)
    RecyclerView rcy1;
    private ArrayList<UsersBean.DataBean.ListBean> list;
    private FragmentAdapter adapter;

    public Fragment1() {
        // Required empty public constructor
    }



    @Override
    protected int getLayout() {
        return R.layout.fragment_1;
    }

    @Override
    protected void initPresenter() {
        mP=new HomePresenterImpl();
    }

    @Override
    protected void initView() {
        rcy1.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcy1.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        list = new ArrayList<>();
        adapter = new FragmentAdapter(list, getActivity());
        rcy1.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        mP.getData();
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setData(UsersBean usersBean) {
        list.addAll(usersBean.getData().getList());
        adapter.notifyDataSetChanged();
    }
}