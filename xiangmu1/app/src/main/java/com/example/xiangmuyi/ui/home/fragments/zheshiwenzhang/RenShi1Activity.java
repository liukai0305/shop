package com.example.xiangmuyi.ui.home.fragments.zheshiwenzhang;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.adapter.homeadapter.zhishi.KnowAdapter;
import com.example.xiangmuyi.base.BaseActivity;
import com.example.xiangmuyi.interfaces.home.IHome;
import com.example.xiangmuyi.persenter.home.wenzhang.KnowHanfuPersenter;
import com.example.xiangmuyi.zhishibean.HomeKnowHanfuBean;

import java.util.ArrayList;

import butterknife.BindView;

public class RenShi1Activity extends BaseActivity<IHome.KnowHanFuPersenter> implements IHome.KnowHanFuView {


    @BindView(R.id.rcy)
    RecyclerView rcy;
    private ArrayList<HomeKnowHanfuBean.DataBean> list;
    private KnowAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_ren_shi1;
    }

    @Override
    protected void initView() {
        rcy.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new KnowAdapter(list, this);
        rcy.setAdapter(adapter);
    }

    @Override
    protected IHome.KnowHanFuPersenter initPersenter() {
        return new KnowHanfuPersenter();
    }

    @Override
    protected void initData() {
        persenter.getKnow();
    }

    @Override
    public void getKnow(HomeKnowHanfuBean result) {
        list.addAll(result.getData());
        adapter.notifyDataSetChanged();
    }
}