package com.example.xiangmuyi.ui.discover;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.adapter.discoveradapter.HotAdapter;
import com.example.xiangmuyi.adapter.homeadapter.FragmentsVpAdapter;
import com.example.xiangmuyi.base.BaseFragment;
import com.example.xiangmuyi.bean.discoverbean.DiscoverHotBean;
import com.example.xiangmuyi.bean.discoverbean.DiscoverHotspotBean;
import com.example.xiangmuyi.bean.discoverbean.DiscoverNavigationBean;
import com.example.xiangmuyi.interfaces.discover.IDiscover;
import com.example.xiangmuyi.persenter.discover.DiscoverPersenter;
import com.example.xiangmuyi.ui.discover.discoveractivity.PaiActivity;
import com.example.xiangmuyi.ui.discover.discoveractivity.PaoActivity;
import com.example.xiangmuyi.ui.discover.discoveractivity.SheActivity;
import com.example.xiangmuyi.ui.discover.discoverfragments.ChildFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class DiscoverFragment extends BaseFragment<IDiscover.HotPersenter> implements IDiscover.HotView {


    @BindView(R.id.ll_a)
    LinearLayout llA;
    @BindView(R.id.ll_b)
    LinearLayout llB;
    @BindView(R.id.ll_c)
    LinearLayout llC;
    @BindView(R.id.rl_a)
    RelativeLayout rlA;
    @BindView(R.id.rlv_discover_hot)
    RecyclerView rlvDiscoverHot;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.discover_toobar)
    Toolbar discoverToobar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.pao)
    ImageView pao;
    @BindView(R.id.pai)
    ImageView pai;
    @BindView(R.id.pao_nearby_she)
    ImageView paoNearbyShe;
    private ArrayList<DiscoverHotBean.DataBean> list;
    private HotAdapter adapter;

    private ArrayList<Fragment> data;

    @Override
    protected int getLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.HORIZONTAL);
        rlvDiscoverHot.setLayoutManager(manager);
        adapter = new HotAdapter(list, context);
        rlvDiscoverHot.setAdapter(adapter);
        pao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PaoActivity.class));
            }
        });

        paoNearbyShe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SheActivity.class));
            }
        });
        pai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PaiActivity.class));
            }
        });
    }

    @Override
    protected IDiscover.HotPersenter initPersenter() {
        return new DiscoverPersenter();
    }

    @Override
    protected void initData() {
        persenter.getHot();
        persenter.getNavigation();
    }

    @Override
    public void getHot(DiscoverHotBean discover) {
        list.addAll(discover.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getNavigation(DiscoverNavigationBean discover) {
        //导航
        if (discover.getStatus().getStatusCode() == 100) {
            List<DiscoverNavigationBean.DataBean> beans = discover.getData();
            initTab(beans);
        }
    }

    @Override
    public void getHospot(DiscoverHotspotBean discover) {

    }

    private void initTab(List<DiscoverNavigationBean.DataBean> beans) {
        data = new ArrayList<>();
        for (int i = 0; i < beans.size(); i++) {
            ChildFragment childFragment = new ChildFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("type", beans.get(i).getType());
            childFragment.setArguments(bundle);
            data.add(childFragment);

        }
        vp.setAdapter(new FragmentsVpAdapter(getChildFragmentManager(), 0, data));
        tabLayout.setupWithViewPager(vp);
        for (int i = 0; i < beans.size(); i++) {
            tabLayout.getTabAt(i).setText(beans.get(i).getName());
        }
    }
}