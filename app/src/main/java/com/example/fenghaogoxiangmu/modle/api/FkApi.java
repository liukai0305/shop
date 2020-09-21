package com.example.fenghaogoxiangmu.modle.api;



import com.example.fenghaogoxiangmu.adapter.home.activity.HomeTopicBean;
import com.example.fenghaogoxiangmu.bean.classify.ClassifyBean;
import com.example.fenghaogoxiangmu.bean.home.HomeBean;
import com.example.fenghaogoxiangmu.bean.home.activitybean.AddCartInfoBean;
import com.example.fenghaogoxiangmu.bean.home.activitybean.BrandRBean;
import com.example.fenghaogoxiangmu.bean.own.SiteBean;
import com.example.fenghaogoxiangmu.bean.shopping.CartBean;
import com.example.fenghaogoxiangmu.bean.home.activitybean.DeleteCartBean;
import com.example.fenghaogoxiangmu.bean.home.activitybean.GoodDetailBean;
import com.example.fenghaogoxiangmu.bean.home.activitybean.TopicPingLBean;
import com.example.fenghaogoxiangmu.bean.home.activitybean.TopicTuiJBean;
import com.example.fenghaogoxiangmu.bean.special.SpecialBean;

import java.util.HashMap;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface FkApi {

    @GET("index")
    Flowable<HomeBean> getHomeContent();
    //分类
    @GET("catalog/index")
    Flowable<ClassifyBean> getClassifyContent();

    //专题
    @GET("topic/list")
    Flowable<SpecialBean> getSpecial();
    //商品购买页详情
    @GET("goods/detail")
    Flowable<GoodDetailBean> getGoodDetail(@Query("id") int id);

    //添加到购物车
    @POST("cart/add")
    @FormUrlEncoded
    Flowable<AddCartInfoBean> addCart(@Field("goodsId") int goodsId, @Field("number") int number, @Field("productId") int productId);

    @GET("cart/index")
    Flowable<CartBean> getCartList();

    //删除购物车
    @POST("cart/delete")
    Flowable<DeleteCartBean> cartDelete(@Field("productIds") String productIds);

    //首页品牌制造商直供
    @GET("brand/detail")
    Flowable<BrandRBean> getNewBean(@Query("id") int id);

    @GET("goods/list")
    Flowable<BrandRBean> getBrand(@QueryMap() HashMap<String,Integer> map);
    //专题详情
    @GET("topic/detail")
    Flowable<HomeTopicBean> getTopic(@Query("id") int id);
    //评论数据
    @GET("comment/list")
    Flowable<TopicPingLBean> getTopicPingBean(@QueryMap() HashMap<String,Integer> map);

    //下部推荐数据
    @GET("topic/related")
    Flowable<TopicTuiJBean> getTopicTuiJBean(@Query("id") int id);

    @GET("region/list")
    Flowable<SiteBean> getsite(@Query("id") int id);
}
