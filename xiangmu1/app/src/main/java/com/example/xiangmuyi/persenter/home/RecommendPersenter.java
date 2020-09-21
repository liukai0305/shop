package com.example.xiangmuyi.persenter.home;


import android.util.Log;

import com.example.xiangmuyi.base.BasePersenter;
import com.example.xiangmuyi.bean.homebean.HomeBannderBean;
import com.example.xiangmuyi.bean.homebean.HomeHotUserBean;
import com.example.xiangmuyi.bean.homebean.HomeRecommendBean;
import com.example.xiangmuyi.bean.homebean.HomeRewardBean;
import com.example.xiangmuyi.bean.homebean.HomeSquareBean;
import com.example.xiangmuyi.bean.homebean.HomeTopicDiscussedBean;
import com.example.xiangmuyi.common.CommonSubscriber;
import com.example.xiangmuyi.interfaces.home.IHome;
import com.example.xiangmuyi.model.HttpManager;
import com.example.xiangmuyi.utils.RxUtils;

public class RecommendPersenter extends BasePersenter<IHome.RecommendView> implements IHome.RecommendPersenter {

    @Override
    public void getBanner() {
        addSubscribe(HttpManager.getInstance().getTpApi().getBanner()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<HomeBannderBean>(mView){
            @Override
            public void onNext(HomeBannderBean homeBannderBean) {
                mView.getBannerReturn(homeBannderBean);
                Log.i("tag", "onNext: "+homeBannderBean.toString());
            }
        }));
    }

    @Override
    public void getTopicDiscussed() {
        addSubscribe(HttpManager.getInstance().getTpApi().getTopicDiscussed()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HomeTopicDiscussedBean>(mView){

                    @Override
                    public void onNext(HomeTopicDiscussedBean topicDiscussedBean) {
                        mView.getTopicDiscussedReturn(topicDiscussedBean);
                    }
                }));
    }

    @Override
    public void getRecommend() {
        addSubscribe(HttpManager.getInstance().getTpApi().getRecommend()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HomeRecommendBean>(mView){

                    @Override
                    public void onNext(HomeRecommendBean homeRecommendBean) {
                        mView.getRecommendReturn(homeRecommendBean);
                    }
                }));
    }

    @Override
    public void getHotUser() {
        addSubscribe(HttpManager.getInstance().getTpApi().getHotUser()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HomeHotUserBean>(mView){

                    @Override
                    public void onNext(HomeHotUserBean homeHotUserBean) {
                        mView.getHotUserReturn(homeHotUserBean);
                    }
                }));
    }

    @Override
    public void getSquare() {
        addSubscribe(HttpManager.getInstance().getTpApi().getSquare()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HomeSquareBean>(mView){

                    @Override
                    public void onNext(HomeSquareBean homeSquareBean) {
                        mView.getSquareReturn(homeSquareBean);
                    }
                }));
    }
    @Override
    public void getReward() {
        addSubscribe(HttpManager.getInstance().getTpApi().getReward()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HomeRewardBean>(mView){

                    @Override
                    public void onNext(HomeRewardBean homeRewardBean) {
                        mView.getRewardReturn(homeRewardBean);
                    }
                }));
    }
}
