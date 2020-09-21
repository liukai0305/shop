package com.example.tongpao.interfaces.home;

import android.widget.VideoView;

import com.example.tongpao.bean.HomeBannerBean;
import com.example.tongpao.bean.HomeHotUserBean;
import com.example.tongpao.bean.HomeRecommendBean;
import com.example.tongpao.bean.HomeTopicDiscussedBean;
import com.example.tongpao.bean.HomeVideoBean;
import com.example.tongpao.bean.PersonalActivityBean;
import com.example.tongpao.bean.PersonalArticleBean;
import com.example.tongpao.bean.PersonalMassBean;
import com.example.tongpao.bean.PersonalMsgBean;
import com.example.tongpao.bean.PersonalPostBean;
import com.example.tongpao.interfaces.IBasePresenter;
import com.example.tongpao.interfaces.IBaseView;


public interface IHome {
    interface View extends IBaseView {
    }

    interface Persenter extends IBasePresenter<View> {
    }

    interface RecommendView extends IBaseView{
        void getBannerReturn(HomeBannerBean result);
        //热门话题请求返回
        void getTopicDiscussedReturn(HomeTopicDiscussedBean result);

        //获取推荐的默认列表数据
        void getRecommendReturn(HomeRecommendBean result);

        //获取热门用户数据
        void getHotUserReturn(HomeHotUserBean result);

    }
    interface RecommendPresenter extends IBasePresenter<RecommendView>{
        void getBanner();

        //热门话题
        void getTopicDiscussed();

        //获取推荐数据
        void getRecommend();

        //热门用户
        void getHotUser();
    }



}
