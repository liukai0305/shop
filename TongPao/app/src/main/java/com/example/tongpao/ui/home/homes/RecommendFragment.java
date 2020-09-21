package com.example.tongpao.ui.home.homes;


import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.tongpao.R;
import com.example.tongpao.adapter.DiscussedAdapter;
import com.example.tongpao.adapter.RecommendAdapter;
import com.example.tongpao.base.BaseAdapter;
import com.example.tongpao.base.BaseFragment;
import com.example.tongpao.bean.HomeBannerBean;
import com.example.tongpao.bean.HomeHotUserBean;
import com.example.tongpao.bean.HomeRecommendBean;
import com.example.tongpao.bean.HomeTopicDiscussedBean;
import com.example.tongpao.common.Constants;
import com.example.tongpao.interfaces.home.IHome;
import com.example.tongpao.presenters.home.RecommendPersenter;
import com.example.tongpao.ui.activity.CommentActivity;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;


public class RecommendFragment extends BaseFragment<IHome.RecommendPresenter> implements IHome.RecommendView {

    private Banner banner;
    private RecyclerView recommend_rv;
    private RecyclerView recommend_rv1;
    private DiscussedAdapter discussedAdapter;
    private List<HomeTopicDiscussedBean.DataBean> dataBeans;
    private List<HomeRecommendBean.DataBean.PostDetailBean> postDetailBeans;
    private RecommendAdapter recommendAdapter;
    private HomeRecommendBean.DataBean.CommentsBean myBean;

    @Override
    protected IHome.RecommendPresenter initPresenter() {
        return new RecommendPersenter();
    }

    @Override
    protected void initData() {
        Presenter.getBanner();
        Presenter.getRecommend();
        Presenter.getTopicDiscussed();
        Presenter.getHotUser();
    }

    @Override
    protected void initView() {
        banner = getActivity().findViewById(R.id.banner);
        recommend_rv = getActivity().findViewById(R.id.recommend_rv);
        recommend_rv1 = getActivity().findViewById(R.id.recommend_rv1);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        //热门话题
        recommend_rv.setLayoutManager(linearLayoutManager);
        dataBeans = new ArrayList<>();
        discussedAdapter = new DiscussedAdapter(getActivity(), dataBeans);
        recommend_rv.setAdapter(discussedAdapter);
        //列表
        recommend_rv1.setLayoutManager(new LinearLayoutManager(getActivity()));
        postDetailBeans = new ArrayList<>();
        recommendAdapter = new RecommendAdapter(getActivity(), postDetailBeans);
        recommend_rv1.setAdapter(recommendAdapter);
        recommendAdapter.setClick(new BaseAdapter.IClick() {
            @Override
            public void click(int pos) {
                Intent intent = new Intent(getActivity(), CommentActivity.class);
                 Constants.commentsBean = myBean;
                 Constants.curClickPost=postDetailBeans.get(pos);
                startActivity(intent);
            }
        });

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_recommend;
    }

    private static final String TAG = "RecommendFragment";

    @Override
    public void getBannerReturn(HomeBannerBean result) {
        Log.d(TAG, "getBannerReturn: " + result.toString());
        if (result.getStatus().getStatusCode() == 100) {
            List<String> banners = new ArrayList<>();
            if (result.getData().getList().size() > 0) {
                for (int i = 0; i < result.getData().getList().size(); i++) {
                    banners.add(result.getData().getList().get(i).getBanner());
                }
            }
            banner.setAdapter(new BannerImageAdapter<String>(banners) {
                @Override
                public void onBindView(BannerImageHolder holder, String data, int position, int size) {
                    Glide.with(holder.itemView).load(data).apply(RequestOptions.bitmapTransform(new RoundedCorners(30))).into(holder.imageView);
                }
            })
            .addBannerLifecycleObserver(this)
            .setIndicator(new CircleIndicator(getActivity()));
        }
    }

    @Override
    public void getTopicDiscussedReturn(HomeTopicDiscussedBean result) {
        dataBeans.addAll(result.getData());
        discussedAdapter.notifyDataSetChanged();
    }

    @Override
    public void getRecommendReturn(HomeRecommendBean result) {
        HomeRecommendBean.DataBean.PostDetailBean postDetail = result.getData().getPostDetail();
        if (result.getStatus().getStatusCode()==100){
            if (result.getData().getPostDetail()!=null&&result.getData().getComments()!=null){
                myBean = result.getData().getComments();

            }
        }
        postDetailBeans.add(postDetail);
        recommendAdapter.notifyDataSetChanged();

    }

    @Override
    public void getHotUserReturn(HomeHotUserBean result) {

    }
}