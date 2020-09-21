package com.example.xiangmuyi.model.api;

import com.example.xiangmuyi.bean.discoverbean.DiscoverHotBean;
import com.example.xiangmuyi.bean.discoverbean.DiscoverHotspotBean;
import com.example.xiangmuyi.bean.discoverbean.DiscoverNavigationBean;
import com.example.xiangmuyi.bean.discoverbean.PaiHangBang.EsperienBean;
import com.example.xiangmuyi.bean.discoverbean.PaiHangBang.LocalBean;
import com.example.xiangmuyi.bean.discoverbean.PaiHangBang.QianDaoBean;
import com.example.xiangmuyi.bean.discoverbean.paozibean.PaoNearbyBean;
import com.example.xiangmuyi.bean.discoverbean.paozibean.PaoRecommendBean;
import com.example.xiangmuyi.bean.discoverbean.shetuan.SheTuanBean;
import com.example.xiangmuyi.bean.homebean.HomeArticleBean;
import com.example.xiangmuyi.bean.homebean.HomeBannderBean;
import com.example.xiangmuyi.bean.homebean.HomeHotUserBean;
import com.example.xiangmuyi.bean.homebean.HomePhtotBean;
import com.example.xiangmuyi.bean.homebean.HomeRecommendBean;
import com.example.xiangmuyi.bean.homebean.HomeRewardBean;
import com.example.xiangmuyi.bean.homebean.HomeTopicDiscussedBean;
import com.example.xiangmuyi.bean.homebean.HomeVideoBean;
import com.example.xiangmuyi.bean.homebean.PersonalActivityBean;
import com.example.xiangmuyi.bean.homebean.PersonalArticleBean;
import com.example.xiangmuyi.bean.homebean.PersonalMsgBean;
import com.example.xiangmuyi.bean.homebean.PersonalPostBean;
import com.example.xiangmuyi.bean.homebean.HomeSquareBean;
import com.example.xiangmuyi.bean.homebean.UserInfoBean;
import com.example.xiangmuyi.bean.homebean.WanZhangEventsBean;
import com.example.xiangmuyi.zhishibean.HomeEventshanfuBean;
import com.example.xiangmuyi.zhishibean.HomeEvolutionBean;
import com.example.xiangmuyi.zhishibean.HomeKnowHanfuBean;
import com.example.xiangmuyi.zhishibean.HomeTraDitionalBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TpApi {
    @GET("home/banner.json")
    Flowable<HomeBannderBean> getBanner();

    //获取热门话题
    @GET("home/topic_discussed.json")
    Flowable<HomeTopicDiscussedBean> getTopicDiscussed();

    //获取推荐数据
    @GET("home/recommend.json")
    Flowable<HomeRecommendBean> getRecommend();

    //获取热门用户
    @GET("home/hot_user.json")
    Flowable<HomeHotUserBean> getHotUser();
    //首页广场数据接口
    @GET("home/square.json")
    Flowable<HomeSquareBean> getSquare();

    @GET("home/personal.json")
    Flowable<UserInfoBean> getPersonal();


    @GET("home/personal_activity.json")
    Flowable<PersonalActivityBean> getPersonalActivity();


    @GET("home/personal_post.json")
    Flowable<PersonalPostBean> getPersonalPost();


    @GET("home/personal_msg.json")
    Flowable<PersonalMsgBean> getPersonalMsg();

    @GET("home/personal_article.json")
    Flowable<PersonalArticleBean> getPersonalArticle();
    //首页文章
    @GET("home/reward.json")
    Flowable<HomeRewardBean> getReward();

    //视频
    @GET("home/video.json")
    Flowable<HomeVideoBean> getVideo();

    //摄影
    @GET("home/photo.json")
    Flowable<HomePhtotBean> getPhtot();

    //知识文章
    @GET("home/article.json")
    Flowable<HomeArticleBean> getArticle();


    //知识文章 认识汉服
    @GET("home/knowhanfu.json")
    Flowable<HomeKnowHanfuBean> getKnow();

    //知识文章 传统礼仪
    @GET("home/traditional.json")
    Flowable<HomeTraDitionalBean> getTraDitional();
    //知识文章 汉服演进史
    @GET("home/evolution_history.json")
    Flowable<HomeEvolutionBean> getEvolution();


    //知识文章 汉服大事件
    @GET("home/eventshanfu.json")
    Flowable<HomeEventshanfuBean> getEventshanfu();

    @GET("home/events_{id}.json")
    Flowable<WanZhangEventsBean> getEvents(@Path("id") int cid );




    //发现————————————————————————————————————

    @GET("discover/hot_activity.json")
    Flowable<DiscoverHotBean> getHot( );

    @GET("discover/navigation.json ")
    Flowable<DiscoverNavigationBean> getNavigation( );

    @GET(" discover/news_{id}.json")
    Flowable<DiscoverHotspotBean> getHotspot(@Path("id") int cid );
    //发现的袍子
    @GET("discover/robe.json")
    Flowable<PaoRecommendBean> getPaoRecommend( );

    @GET("discover/robe_near.json")
    Flowable<PaoNearbyBean> getPaoNearby( );

    //排行榜  经验
    @GET("discover/rank_level.json")
    Flowable<EsperienBean> getEsperien();

    @GET("discover/rank_money.json")
    Flowable<LocalBean> getLocal();

    @GET("discover/rank_sign.json")
    Flowable<QianDaoBean> getQianDao();

    //社团
    @GET("discover/association.json")
    Flowable<SheTuanBean> getSheTuan();

}
