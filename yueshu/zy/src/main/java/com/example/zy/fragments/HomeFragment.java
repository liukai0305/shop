package com.example.zy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.zy.R;
import com.example.zy.adapter.FragmentVpAdapter;
import com.example.zy.adapter.HomeAdapter;
import com.example.zy.base.BaseFragment;
import com.example.zy.bean.UsersBean;
import com.example.zy.fragments.Home.Fragment1;
import com.example.zy.fragments.Home.Fragment2;
import com.example.zy.fragments.Home.Fragment3;
import com.example.zy.fragments.Home.Fragment4;
import com.example.zy.net.IMainContract;
import com.example.zy.presenter.HomePresenterImpl;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;


public class HomeFragment extends BaseFragment<HomePresenterImpl> implements IMainContract.IFragmentView {



    @BindView(R.id.rcy_home)
    RecyclerView rcyHome;
    @BindView(R.id.tab_home)
    TabLayout tabHome;
    @BindView(R.id.vp)
    ViewPager vp;
    private ArrayList<UsersBean.DataBean.ActiondataBean> list;
    private HomeAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor

    }


  //  @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
//
//        return inflate;
//
//    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initPresenter() {
        mP=new HomePresenterImpl();

    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.HORIZONTAL);
        rcyHome.setLayoutManager(manager);
        list = new ArrayList<>();
        adapter = new HomeAdapter(list, getActivity());
        rcyHome.setAdapter(adapter);


        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        fragments.add(new Fragment4());
        FragmentVpAdapter fragmentVpAdapter = new FragmentVpAdapter(getChildFragmentManager(), 0, fragments);
        vp.setAdapter(fragmentVpAdapter);
        tabHome.setupWithViewPager(vp);
        tabHome.getTabAt(0).setText("热点");
        tabHome.getTabAt(1).setText("造妆");
        tabHome.getTabAt(2).setText("图赏");
        tabHome.getTabAt(3).setText("百科");
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
        list.addAll(usersBean.getData().getActiondata());
        adapter.notifyDataSetChanged();
    }
}