package com.example.xiangmuyi.ui.discover.discoverfragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.adapter.discoveradapter.HotSpotAdapter;
import com.example.xiangmuyi.base.BaseFragment;
import com.example.xiangmuyi.bean.discoverbean.DiscoverHotBean;
import com.example.xiangmuyi.bean.discoverbean.DiscoverHotspotBean;
import com.example.xiangmuyi.bean.discoverbean.DiscoverNavigationBean;
import com.example.xiangmuyi.interfaces.discover.IDiscover;
import com.example.xiangmuyi.persenter.discover.DiscoverPersenter;

import java.util.List;

import butterknife.BindView;


public class ChildFragment extends BaseFragment<IDiscover.HotPersenter> implements IDiscover.HotView {


    @BindView(R.id.rcy)
    RecyclerView rcy;
    private int type;
    private HotSpotAdapter hotSpotAdapter;

    public ChildFragment() {
        // Required empty public constructor
    }



    @Override
    protected int getLayout() {
        return R.layout.fragment_child;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        type = arguments.getInt("type");
        rcy.setLayoutManager(new LinearLayoutManager(context));
        rcy.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        hotSpotAdapter = new HotSpotAdapter(getActivity());
        rcy.setAdapter(hotSpotAdapter);
    }

    @Override
    protected IDiscover.HotPersenter initPersenter() {
        return new DiscoverPersenter();
    }

    @Override
    protected void initData() {
persenter.getHospot(type);
    }

    @Override
    public void getHot(DiscoverHotBean discover) {

    }

    @Override
    public void getNavigation(DiscoverNavigationBean discover) {

    }

    private static final String TAG = "ChildFragment";
    @Override
    public void getHospot(DiscoverHotspotBean discover) {
        Log.d(TAG, "getHospot: "+discover.toString());
        List<DiscoverHotspotBean.DataBean.ListBean> list = discover.getData().getList();
        hotSpotAdapter.addData(list);
    }
}