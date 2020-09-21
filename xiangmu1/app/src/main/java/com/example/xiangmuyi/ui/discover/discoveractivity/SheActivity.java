package com.example.xiangmuyi.ui.discover.discoveractivity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.adapter.discoveradapter.SheTuanAdapter;
import com.example.xiangmuyi.base.BaseActivity;
import com.example.xiangmuyi.bean.discoverbean.shetuan.SheTuanBean;
import com.example.xiangmuyi.interfaces.discover.IDiscover;
import com.example.xiangmuyi.persenter.discover.she.SheTuanPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SheActivity extends BaseActivity<IDiscover.SheTuanPersenter> implements IDiscover.SheTuanView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_she)
    TextView tvShe;
    @BindView(R.id.rcy_she)
    RecyclerView rcyShe;
    private ArrayList<SheTuanBean.DataBean.ListBean> list;
    private SheTuanAdapter adapter;


    @Override
    protected int getLayout() {
        return R.layout.activity_she;
    }

    @Override
    protected void initView() {
        rcyShe.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new SheTuanAdapter(list, this);
        rcyShe.setAdapter(adapter);
    }


    @Override
    protected IDiscover.SheTuanPersenter initPersenter() {
        return new SheTuanPresenter();
    }

    @Override
    protected void initData() {
        persenter.getShetuan();
    }

    @Override
    public void getSheTuan(SheTuanBean discover) {
        list.addAll(discover.getData().getList());
        adapter.notifyDataSetChanged();
    }
}