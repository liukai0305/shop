package com.example.fenghaogoxiangmu.persenter.home.activitypersneter;

import com.example.fenghaogoxiangmu.adapter.home.activity.HomeTopicBean;
import com.example.fenghaogoxiangmu.base.BasePersenter;
import com.example.fenghaogoxiangmu.bean.home.activitybean.BrandRBean;
import com.example.fenghaogoxiangmu.bean.home.activitybean.TopicPingLBean;
import com.example.fenghaogoxiangmu.bean.home.activitybean.TopicTuiJBean;
import com.example.fenghaogoxiangmu.common.CommonSubscriber;
import com.example.fenghaogoxiangmu.interfaces.home.activity.ITopic;
import com.example.fenghaogoxiangmu.modle.HttpManager;
import com.example.fenghaogoxiangmu.utils.RxUtils;

import java.util.HashMap;

public class Topicoersenter extends BasePersenter<ITopic.TView> implements ITopic.TPresenter {


    @Override
    public void getTopicBean(int id) {
        addSubscribe(HttpManager.getInstance().getFkApi().getTopic(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HomeTopicBean>(mView) {
                    @Override
                    public void onNext(HomeTopicBean homeTopicBean) {
                        mView.getTopicBeanR(homeTopicBean);
                    }
                }));
    }

    @Override
    public void getTopicPingBean(HashMap<String, Integer> map) {
        addSubscribe(HttpManager.getInstance().getFkApi().getTopicPingBean(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TopicPingLBean>(mView) {
                    @Override
                    public void onNext(TopicPingLBean topicPingLBean) {
                        mView.getTopicPingLR(topicPingLBean);
                    }
                }));
    }

    @Override
    public void getTopicTuiBean(int id) {
        addSubscribe(HttpManager.getInstance().getFkApi().getTopicTuiJBean(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TopicTuiJBean>(mView) {
                    @Override
                    public void onNext(TopicTuiJBean topicPingLBean) {
                        mView.getTopicTuiJR(topicPingLBean);
                    }
                }));
    }
}
