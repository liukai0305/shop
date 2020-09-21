package com.example.xiangmuyi.interfaces.home;


import com.example.xiangmuyi.bean.homebean.HomeArticleBean;
import com.example.xiangmuyi.bean.homebean.HomeBannderBean;
import com.example.xiangmuyi.bean.homebean.HomeHotUserBean;
import com.example.xiangmuyi.bean.homebean.HomePhtotBean;
import com.example.xiangmuyi.bean.homebean.HomeRecommendBean;
import com.example.xiangmuyi.bean.homebean.HomeRewardBean;
import com.example.xiangmuyi.bean.homebean.HomeSquareBean;
import com.example.xiangmuyi.bean.homebean.HomeTopicDiscussedBean;
import com.example.xiangmuyi.bean.homebean.HomeVideoBean;
import com.example.xiangmuyi.bean.homebean.WanZhangEventsBean;
import com.example.xiangmuyi.interfaces.IBasePersenter;
import com.example.xiangmuyi.interfaces.IBaseView;
import com.example.xiangmuyi.zhishibean.HomeEventshanfuBean;
import com.example.xiangmuyi.zhishibean.HomeEvolutionBean;
import com.example.xiangmuyi.zhishibean.HomeKnowHanfuBean;
import com.example.xiangmuyi.zhishibean.HomeTraDitionalBean;

public interface IHome {

    interface View extends IBaseView {

    }

    interface Persenter extends IBasePersenter<View> {
    }


    /**
     * 推荐
     */
    interface RecommendView extends IBaseView {
        void getBannerReturn(HomeBannderBean result);

        //热门话题请求返回
        void getTopicDiscussedReturn(HomeTopicDiscussedBean result);

        //获取推荐的默认列表数据
        void getRecommendReturn(HomeRecommendBean result);

        //获取热门用户数据
        void getHotUserReturn(HomeHotUserBean result);

        //获取热门广场数据
        void getSquareReturn(HomeSquareBean result);

        //文章

        void getRewardReturn(HomeRewardBean result);

    }

    interface RecommendPersenter extends IBasePersenter<RecommendView> {
        void getBanner();

        //热门话题
        void getTopicDiscussed();

        //获取推荐数据
        void getRecommend();

        //热门用户
        void getHotUser();

        //热门广场
        void getSquare();

        void getReward();
    }
    //视频
    interface VideoView extends IBaseView {

        void getVideo(HomeVideoBean result);
    }
    //视频
    interface VideoPersenter extends IBasePersenter<VideoView> {

        void getVideo();

    }
    //视频
    interface PhtotView extends IBaseView {

        void getPhtot(HomePhtotBean result);
    }
    //视频
    interface PhtotPersenter extends IBasePersenter<PhtotView> {

        void getPhtot();
    }
    //视频
    interface ArticleView extends IBaseView {

        void getArticle(HomeArticleBean result);
    }
    //视频
    interface ArticlePersenter extends IBasePersenter<ArticleView> {

        void getArticle();
    }

//认识汉服
    interface KnowHanFuView extends IBaseView {

        void getKnow(HomeKnowHanfuBean result);
    }

    interface KnowHanFuPersenter extends IBasePersenter<KnowHanFuView> {

        void getKnow();
    }
//传统礼仪：
    interface TraDitionalView extends IBaseView {

        void getTraDitional(HomeTraDitionalBean result);
    }

    interface TraDitionalPersenter extends IBasePersenter<TraDitionalView> {

        void getTraDitional();
    }

//汉服演进史：
    interface EvolutionView extends IBaseView {

        void getEvolution(HomeEvolutionBean result);
    }

    interface EvolutionPersenter extends IBasePersenter<EvolutionView> {

        void getEvolution();
    }
//汉服大事件
    interface EventshanfuView extends IBaseView {

        void getEventshanfu(HomeEventshanfuBean result);
    }

    interface EventshanfuPersenter extends IBasePersenter<EventshanfuView> {

        void getEventshanfu();
    }

    interface EventView extends IBaseView {

        void getEvent(WanZhangEventsBean result);
    }

    interface EventPersenter extends IBasePersenter<EventView> {

        void getEvent(int id);
    }


}
