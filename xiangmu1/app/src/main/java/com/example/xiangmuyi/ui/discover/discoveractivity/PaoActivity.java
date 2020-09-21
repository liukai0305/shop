package com.example.xiangmuyi.ui.discover.discoveractivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.adapter.discoveradapter.PaoNearbyAdapter;
import com.example.xiangmuyi.adapter.discoveradapter.PaoRecommendAdapter;
import com.example.xiangmuyi.base.BaseActivity;
import com.example.xiangmuyi.bean.discoverbean.paozibean.PaoNearbyBean;
import com.example.xiangmuyi.bean.discoverbean.paozibean.PaoRecommendBean;
import com.example.xiangmuyi.interfaces.discover.IDiscover;
import com.example.xiangmuyi.persenter.discover.pao.PaoPersenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaoActivity extends BaseActivity<IDiscover.PaoPersenter> implements IDiscover.PaoView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.rly)
    RelativeLayout rly;
    @BindView(R.id.rcy_pao_tui)
    RecyclerView rcyPaoTui;
    @BindView(R.id.v)
    View v;
    @BindView(R.id.rela)
    RelativeLayout rela;
    @BindView(R.id.rcy_pao_fu)
    RecyclerView rcyPaoFu;
    @BindView(R.id.huan)
    TextView huan;
    private ArrayList<PaoRecommendBean.DataBean> recommendlist;
    private ArrayList<PaoRecommendBean.DataBean> recommendlist1;
    private ArrayList<PaoRecommendBean.DataBean> recommendlist2;
    private ArrayList<PaoRecommendBean.DataBean> recommendlist3;
    private ArrayList<PaoNearbyBean.DataBean.ListBean> nearbylist;
    private PaoNearbyAdapter adapter;
    private PaoRecommendAdapter paoRecommendAdapter;
    private int count=0;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pao);
//    }

    @Override
    protected int getLayout() {
        return R.layout.activity_pao;
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.HORIZONTAL);
        rcyPaoTui.setLayoutManager(manager);
        recommendlist = new ArrayList<>();
        recommendlist1 = new ArrayList<>();
        recommendlist2 = new ArrayList<>();
        recommendlist3 = new ArrayList<>();

        paoRecommendAdapter = new PaoRecommendAdapter(recommendlist, this);
        rcyPaoTui.setAdapter(paoRecommendAdapter);

        rcyPaoFu.setLayoutManager(new LinearLayoutManager(this));
        nearbylist = new ArrayList<>();
        adapter = new PaoNearbyAdapter(nearbylist, this);
        rcyPaoFu.setAdapter(adapter);

    }

    @Override
    protected IDiscover.PaoPersenter initPersenter() {
        return new PaoPersenter();
    }

    @Override
    protected void initData() {
        persenter.getPaoNearby();
        persenter.getPaoRecommend();
    }

    @Override
    public void getPaoRecommend(PaoRecommendBean discover) {
        List<PaoRecommendBean.DataBean> data = discover.getData();
        for (int i = 0; i < data.size(); i++) {
            if (i >= 0 && i < 3) {
                recommendlist1.add(data.get(i));
            } else if (i >= 3 && i < 6) {
                recommendlist2.add(data.get(i));
            } else {
                recommendlist3.add(data.get(i));
            }
            recommendlist.addAll(recommendlist1);
            paoRecommendAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getPaoNearby(PaoNearbyBean discover) {
        nearbylist.addAll(discover.getData().getList());
        adapter.notifyDataSetChanged();
    }



    @OnClick(R.id.huan)
    public void onViewClicked() {
        recommendlist.clear();
        paoRecommendAdapter.notifyDataSetChanged();
        if (count == 0){
            recommendlist.addAll(recommendlist1);
            paoRecommendAdapter.notifyDataSetChanged();
            count++;
        }else if (count == 1){
            recommendlist.addAll(recommendlist2);
            paoRecommendAdapter.notifyDataSetChanged();
            count++;
        }else {
            recommendlist.addAll(recommendlist3);
            paoRecommendAdapter.notifyDataSetChanged();
            count = 0;
        }
    }
}