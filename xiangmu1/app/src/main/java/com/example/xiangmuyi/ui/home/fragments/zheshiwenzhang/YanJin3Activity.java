package com.example.xiangmuyi.ui.home.fragments.zheshiwenzhang;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.adapter.homeadapter.zhishi.YanJinAdapter;
import com.example.xiangmuyi.base.BaseActivity;
import com.example.xiangmuyi.common.TimeLineItemDecoration;
import com.example.xiangmuyi.interfaces.home.IHome;
import com.example.xiangmuyi.persenter.home.wenzhang.EvolutionPersenter;
import com.example.xiangmuyi.zhishibean.HomeEvolutionBean;

import java.util.ArrayList;

import butterknife.BindView;

public class YanJin3Activity extends BaseActivity<IHome.EvolutionPersenter> implements IHome.EvolutionView {


    @BindView(R.id.rcy)
    RecyclerView rcy;
    private ArrayList<HomeEvolutionBean.DataBean> list;
    private YanJinAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_yan_jin3;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        rcy.setLayoutManager(new LinearLayoutManager(this));
        rcy.addItemDecoration(new TimeLineItemDecoration());
        adapter = new YanJinAdapter(list, this);
        rcy.setAdapter(adapter);

    }
    @Override
    protected IHome.EvolutionPersenter initPersenter() {
        return new EvolutionPersenter();
    }

    @Override
    protected void initData() {
        persenter.getEvolution();
    }

    @Override
    public void getEvolution(HomeEvolutionBean result) {
        list.addAll(result.getData());
        adapter.notifyDataSetChanged();
    }
}