package com.example.kaohe;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kaohe.adapter.LeiBiaoAdapter;
import com.example.kaohe.adapter.ReMenAdapter;
import com.example.kaohe.base.BaseActivity;
import com.example.kaohe.bean.LieBiaoBean;
import com.example.kaohe.bean.ReMenBean;
import com.example.kaohe.bean.TuiJianBean;
import com.example.kaohe.interfaces.IHome;
import com.example.kaohe.persenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<IHome.IHomePersenter> implements IHome.IHomeView {


    @BindView(R.id.tv_remen)
    TextView tvRemen;
    @BindView(R.id.tv_huati)
    TextView tvHuati;
    @BindView(R.id.rcy_remen)
    RecyclerView rcyRemen;
    @BindView(R.id.tv_item)
    TextView tvItem;
    @BindView(R.id.rcy_liebiao)
    RecyclerView rcyLiebiao;
    private ArrayList<ReMenBean.DataBean> list;
    private ReMenAdapter adapter;
    private ArrayList<LieBiaoBean.DataBean.DynamicsBean> dynamicsBeans;
    private ArrayList<TuiJianBean.DataBean> dataBeans;
    private LeiBiaoAdapter leiBiaoAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        rcyRemen.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        list = new ArrayList<>();
        adapter = new ReMenAdapter(this, list);
        rcyRemen.setAdapter(adapter);

        dynamicsBeans = new ArrayList<>();
        dataBeans = new ArrayList<>();
        rcyLiebiao.setLayoutManager(new LinearLayoutManager(this));
        leiBiaoAdapter = new LeiBiaoAdapter( this);
        rcyLiebiao.setAdapter(leiBiaoAdapter);
    }

    @Override
    protected IHome.IHomePersenter initPersenter() {
        return new HomePresenter();
    }

    @Override
    protected void initData() {
        persenter.getLieBiao();
        persenter.getReMen();
        persenter.getTuiJian();
    }

    @Override
    public void getLieBiao(LieBiaoBean result) {
        List<LieBiaoBean.DataBean.DynamicsBean> dynamics = result.getData().getDynamics();
        leiBiaoAdapter.addListData(dynamics);
        leiBiaoAdapter.notifyDataSetChanged();
    }

    @Override
    public void getReMen(ReMenBean result) {
        list.addAll(result.getData());
        adapter.notifyDataSetChanged();
    }

    private static final String TAG = "MainActivity";
    @Override
    public void getTuiJian(TuiJianBean result) {
        Log.d(TAG, "getTuiJian: "+result.toString());
        leiBiaoAdapter.addTuiData(result.getData());
    }


}