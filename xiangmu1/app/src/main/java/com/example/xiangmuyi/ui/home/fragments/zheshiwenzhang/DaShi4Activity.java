package com.example.xiangmuyi.ui.home.fragments.zheshiwenzhang;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.adapter.homeadapter.zhishi.DaShiAdapter;
import com.example.xiangmuyi.base.BaseActivity;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.common.TimeLineItemDecoration;
import com.example.xiangmuyi.interfaces.home.IHome;
import com.example.xiangmuyi.persenter.home.wenzhang.EventshanfuPersenter;
import com.example.xiangmuyi.zhishibean.HomeEventshanfuBean;

import java.util.ArrayList;

import butterknife.BindView;

public class DaShi4Activity extends BaseActivity<IHome.EventshanfuPersenter> implements IHome.EventshanfuView {


    @BindView(R.id.rcy)
    RecyclerView rcy;
    private ArrayList<HomeEventshanfuBean.DataBean> list;
    private DaShiAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_da_shi4;
    }

    @Override
    protected void initView() {
        rcy.setLayoutManager(new LinearLayoutManager(this));
        rcy.addItemDecoration(new TimeLineItemDecoration());
        list = new ArrayList<>();
        adapter = new DaShiAdapter(list, this);
        rcy.setAdapter(adapter);
        adapter.setOnItemClickLis(new BaseAdapter.OnItemClickLisf() {
            @Override
            public void click(int position) {
                HomeEventshanfuBean.DataBean dataBean = list.get(position);
                Intent intent = new Intent(DaShi4Activity.this,YanJinActivity.class);
                intent.putExtra("id",dataBean.getHanfuHistoryID());
                startActivity(intent);
            }
        });
    }

    @Override
    protected IHome.EventshanfuPersenter initPersenter() {
        return new EventshanfuPersenter();
    }

    @Override
    protected void initData() {
        persenter.getEventshanfu();
    }

    @Override
    public void getEventshanfu(HomeEventshanfuBean result) {
        list.addAll(result.getData());
        adapter.notifyDataSetChanged();
    }

}