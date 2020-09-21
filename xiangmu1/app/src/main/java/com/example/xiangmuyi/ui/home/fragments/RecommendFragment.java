package com.example.xiangmuyi.ui.home.fragments;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.adapter.homeadapter.HomeTopicAdapter;
import com.example.xiangmuyi.adapter.homeadapter.RecommendAdapter;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.base.BaseFragment;
import com.example.xiangmuyi.bean.homebean.HomeBannderBean;
import com.example.xiangmuyi.bean.homebean.HomeHotUserBean;
import com.example.xiangmuyi.bean.homebean.HomeRecommendBean;
import com.example.xiangmuyi.bean.homebean.HomeRewardBean;
import com.example.xiangmuyi.bean.homebean.HomeSquareBean;
import com.example.xiangmuyi.bean.homebean.HomeTopicDiscussedBean;
import com.example.xiangmuyi.common.Constants;
import com.example.xiangmuyi.interfaces.home.IHome;
import com.example.xiangmuyi.persenter.home.RecommendPersenter;
import com.example.xiangmuyi.ui.home.activity.RecommendActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;


public class RecommendFragment extends BaseFragment<IHome.RecommendPersenter> implements IHome.RecommendView {


    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rcy)
    RecyclerView rcy;
    @BindView(R.id.rcy_commend)
    RecyclerView rcyCommend;

    private ArrayList<HomeTopicDiscussedBean.DataBean> list;
    private HomeTopicAdapter homeTopicAdapter;
    private ArrayList<HomeRecommendBean.DataBean.PostDetailBean> postDetailBeans;
    private RecommendAdapter recommendAdapter;
    private ArrayList<HomeRecommendBean.DataBean.CommentsBean.AllCommentsBean> pinglun;


    @Override
    protected int getLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {
        //热门话题
        rcy.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        list = new ArrayList<>();
        homeTopicAdapter = new HomeTopicAdapter(list, getActivity());
        rcy.setAdapter(homeTopicAdapter);
        //推荐数据
        rcyCommend.setLayoutManager(new LinearLayoutManager(getContext()));
        rcyCommend.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        postDetailBeans = new ArrayList<>();
        recommendAdapter = new RecommendAdapter(postDetailBeans, getActivity());
        rcyCommend.setAdapter(recommendAdapter);
        //评论


        pinglun = new ArrayList<>();
        recommendAdapter.setOnItemClickLis(new BaseAdapter.OnItemClickLisf() {
            @Override
            public void click(int position) {
                Intent intent = new Intent(getActivity(), RecommendActivity.class);
                intent.putExtra("bean", postDetailBeans);
                intent.putExtra("ping", pinglun);
                startActivity(intent);
            }
        });
    }

    @Override
    protected IHome.RecommendPersenter initPersenter() {
        return new RecommendPersenter();
    }

    @Override
    protected void initData() {
        persenter.getBanner();

        persenter.getRecommend();

        persenter.getTopicDiscussed();

        persenter.getHotUser();
    }

    @Override
    public void getBannerReturn(HomeBannderBean result) {
        if (result.getStatus().getStatusCode() == 100) {
            banner.setImages(result.getData().getList())
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            RoundedCorners roundedCorners = new RoundedCorners(10);
                            RequestOptions override = RequestOptions.bitmapTransform(roundedCorners).override(100, 100);
                            path = result.getData().getList().get(0).getBanner();
                            Glide.with(context).load(path).apply(override).into(imageView);
                        }
                    })
                    .setDelayTime(2000)
                    .start();
        }
    }

    @Override
    public void getTopicDiscussedReturn(HomeTopicDiscussedBean result) {
        list.addAll(result.getData());
        homeTopicAdapter.notifyDataSetChanged();
    }

    @Override
    public void getRecommendReturn(HomeRecommendBean result) {
        if (result.getStatus().getStatusCode() == 100) {
            if (result.getData().getPostDetail() != null) {
                Constants.curClickPost = result.getData().getPostDetail();
                postDetailBeans.add(result.getData().getPostDetail());
                pinglun.addAll(result.getData().getComments().getAllComments());
                recommendAdapter.notifyDataSetChanged();
            }
        }

    }

    @Override
    public void getHotUserReturn(HomeHotUserBean result) {

    }

    @Override
    public void getSquareReturn(HomeSquareBean result) {

    }

    @Override
    public void getRewardReturn(HomeRewardBean result) {

    }
}