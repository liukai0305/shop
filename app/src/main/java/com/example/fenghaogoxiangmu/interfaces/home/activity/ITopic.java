package com.example.fenghaogoxiangmu.interfaces.home.activity;

import com.example.fenghaogoxiangmu.adapter.home.activity.HomeTopicBean;
import com.example.fenghaogoxiangmu.bean.home.activitybean.TopicPingLBean;
import com.example.fenghaogoxiangmu.bean.home.activitybean.TopicTuiJBean;
import com.example.fenghaogoxiangmu.interfaces.IBasePersenter;
import com.example.fenghaogoxiangmu.interfaces.IBaseView;

import java.util.HashMap;

public interface ITopic {
    interface TView extends IBaseView{
        void getTopicBeanR(HomeTopicBean topicBean);
        void getTopicPingLR(TopicPingLBean pingLBean);
        void getTopicTuiJR(TopicTuiJBean topicTuiJBean);
    }
    interface TPresenter extends IBasePersenter<TView> {
        void getTopicBean(int id);
        void getTopicPingBean(HashMap<String,Integer> map);
        void getTopicTuiBean(int id);
    }
}
