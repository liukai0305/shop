package com.example.xiangmuyi.ui.home.fragments;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.adapter.homeadapter.SquareAdapter;
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
import cn.jzvd.JzvdStd;


public class SquareFragment extends BaseFragment<IHome.RecommendPersenter> implements IHome.RecommendView {


    @BindView(R.id.rcy)
    RecyclerView rcy;
    private ArrayList<HomeSquareBean.DataBean.DynamicsBean> list;
    private SquareAdapter squareAdapter;

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View inflate = inflater.inflate(R.layout.fragment_square, container, false);
//        return inflate;
//    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_square;
    }

    @Override
    protected void initView() {
        rcy.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        squareAdapter = new SquareAdapter(list, getActivity());
        rcy.setAdapter(squareAdapter);
    }

    @Override
    protected IHome.RecommendPersenter initPersenter() {
        return new RecommendPersenter();
    }

    @Override
    protected void initData() {
        persenter.getSquare();
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
        Log.d(TAG, "getSquareReturn: " + result.toString());
        list.addAll(result.getData().getDynamics());
        squareAdapter.notifyDataSetChanged();
    }

    @Override
    public void getRewardReturn(HomeRewardBean result) {

    }

    private static final String TAG = "SquareFragment";

    @Override
    public void onPause() {
        super.onPause();
        JzvdStd.releaseAllVideos();
    }

}