package com.example.tongpao.model.api;

import com.example.tongpao.bean.HomeBannerBean;
import com.example.tongpao.bean.HomeHotUserBean;
import com.example.tongpao.bean.HomeRecommendBean;
import com.example.tongpao.bean.HomeTopicDiscussedBean;
import com.example.tongpao.bean.PersonalActivityBean;
import com.example.tongpao.bean.PersonalArticleBean;
import com.example.tongpao.bean.PersonalMsgBean;
import com.example.tongpao.bean.PersonalPostBean;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface TpApi {

    @GET("home/banner.json")
    Flowable<HomeBannerBean> getHomeBanner();

    @GET("home/topic_discussed.json")
    Flowable<HomeTopicDiscussedBean> getTopicDiscussed();

    @GET("home/recommend.json")
    Flowable<HomeRecommendBean> getRecommend();

    @GET("home/hot_user.json")
    Flowable<HomeHotUserBean> getHotUser();

    //个人信息
    @GET("home/personal.json")
    Flowable<PersonalMsgBean> getPersonalMsg();
    //个人信息动态
    @GET("home/personal_activity.json")
    Flowable<PersonalActivityBean> getPersonalActivity();
    //个人信息活动
    @GET("home/personal_post.json")
    Flowable<PersonalPostBean> getPersonalPost();
    //个人信息社团
    @GET("home/personal_msg.json")
    Flowable<PersonalMsgBean> getPersonalMass();
    //个人信息文章
    @GET("home/personal_article.json")
    Flowable<PersonalArticleBean> getPersonalArtice();
}
