package com.example.tongpao.presenters.home;


import android.util.Log;

import com.example.tongpao.base.BasePersenter;
import com.example.tongpao.bean.HomeBannerBean;
import com.example.tongpao.bean.HomeHotUserBean;
import com.example.tongpao.bean.HomeRecommendBean;
import com.example.tongpao.bean.HomeTopicDiscussedBean;
import com.example.tongpao.common.CommonSubscriber;
import com.example.tongpao.interfaces.home.IHome;
import com.example.tongpao.model.HttpManager;
import com.example.tongpao.utils.RxUtils;

public class RecommendPersenter extends BasePersenter<IHome.RecommendView> implements IHome.RecommendPresenter {
    private static final String TAG = "RecommendPersenter";
    @Override
    public void getBanner() {
        addSubscribe(HttpManager.getInstance().getTpApi().getHomeBanner()
        .compose(RxUtils.<HomeBannerBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<HomeBannerBean>(mView) {
            @Override
            public void onNext(HomeBannerBean homeBannerBean) {
                Log.d(TAG, "onNext: "+homeBannerBean.toString());
                mView.getBannerReturn(homeBannerBean);
            }
        }));
    }

    @Override
    public void getTopicDiscussed() {
        addSubscribe(HttpManager.getInstance().getTpApi().getTopicDiscussed()
                .compose(RxUtils.<HomeTopicDiscussedBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<HomeTopicDiscussedBean>(mView) {
                    @Override
                    public void onNext(HomeTopicDiscussedBean homeTopicDiscussedBean) {
                        mView.getTopicDiscussedReturn(homeTopicDiscussedBean);
                    }
                }));
    }

    @Override
    public void getRecommend() {
        addSubscribe(HttpManager.getInstance().getTpApi().getRecommend()
                .compose(RxUtils.<HomeRecommendBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<HomeRecommendBean>(mView) {
                    @Override
                    public void onNext(HomeRecommendBean homeRecommendBean) {
                        mView.getRecommendReturn(homeRecommendBean);
                    }
                }));
    }

    @Override
    public void getHotUser() {
        addSubscribe(HttpManager.getInstance().getTpApi().getHotUser()
                .compose(RxUtils.<HomeHotUserBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<HomeHotUserBean>(mView) {
                    @Override
                    public void onNext(HomeHotUserBean homeHotUserBean) {
                        mView.getHotUserReturn(homeHotUserBean);
                    }
                }));
    }

}
