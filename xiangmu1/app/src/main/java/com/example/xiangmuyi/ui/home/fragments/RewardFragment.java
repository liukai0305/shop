package com.example.xiangmuyi.ui.home.fragments;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.adapter.homeadapter.RewardAdapter;
import com.example.xiangmuyi.base.BaseFragment;
import com.example.xiangmuyi.bean.homebean.HomeBannderBean;
import com.example.xiangmuyi.bean.homebean.HomeHotUserBean;
import com.example.xiangmuyi.bean.homebean.HomeRecommendBean;
import com.example.xiangmuyi.bean.homebean.HomeRewardBean;
import com.example.xiangmuyi.bean.homebean.HomeSquareBean;
import com.example.xiangmuyi.bean.homebean.HomeTopicDiscussedBean;
import com.example.xiangmuyi.interfaces.home.IHome;
import com.example.xiangmuyi.persenter.home.RecommendPersenter;

import java.util.ArrayList;

import butterknife.BindView;


public class RewardFragment extends BaseFragment<IHome.RecommendPersenter> implements IHome.RecommendView {


    @BindView(R.id.rcy)
    RecyclerView rcy;
    private ArrayList<HomeRewardBean.DataBean.ListBean> list;
    private RewardAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_reward;
    }

    @Override
    protected void initView() {
        rcy.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new RewardAdapter(list, getActivity());
        rcy.setAdapter(adapter);

    }

    @Override
    protected IHome.RecommendPersenter initPersenter() {
        return new RecommendPersenter();
    }

    @Override
    protected void initData() {
        persenter.getReward();
    }


    @Override
    public void getBannerReturn(HomeBannderBean result) {

    }

    @Override
    public void getTopicDiscussedReturn(HomeTopicDiscussedBean result) {

    }

    @Override
    public void getRecommendReturn(HomeRecommendBean result) {

    }

    @Override
    public void getHotUserReturn(HomeHotUserBean result) {

    }

    @Override
    public void getSquareReturn(HomeSquareBean result) {

    }

    @Override
    public void getRewardReturn(HomeRewardBean result) {
        list.addAll(result.getData().getList());
        adapter.notifyDataSetChanged();
    }
}