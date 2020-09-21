package com.example.xiangmuyi.ui.home.fragments.zheshiwenzhang;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.adapter.homeadapter.zhishi.ChuanTongAdapter;
import com.example.xiangmuyi.base.BaseActivity;
import com.example.xiangmuyi.interfaces.home.IHome;
import com.example.xiangmuyi.persenter.home.wenzhang.TraDitionalPersenter;
import com.example.xiangmuyi.zhishibean.HomeTraDitionalBean;

import java.util.ArrayList;

import butterknife.BindView;

public class ChuanTong2Activity extends BaseActivity<IHome.TraDitionalPersenter> implements IHome.TraDitionalView {


    @BindView(R.id.rcy)
    RecyclerView rcy;
    private ArrayList<HomeTraDitionalBean.DataBean> list;
    private ChuanTongAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_chuan_tong2;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        rcy.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChuanTongAdapter(list, this);
        rcy.setAdapter(adapter);
    }

    @Override
    protected IHome.TraDitionalPersenter initPersenter() {
        return new TraDitionalPersenter();
    }

    @Override
    protected void initData() {
        persenter.getTraDitional();
    }

    @Override
    public void getTraDitional(HomeTraDitionalBean result) {
        list.addAll(result.getData());
        adapter.notifyDataSetChanged();
    }


}