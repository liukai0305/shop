package com.example.fenghaogoxiangmu.persenter.home;


import com.example.fenghaogoxiangmu.base.BasePersenter;
import com.example.fenghaogoxiangmu.bean.home.HomeBean;
import com.example.fenghaogoxiangmu.common.CommonSubscriber;
import com.example.fenghaogoxiangmu.interfaces.home.IHome;
import com.example.fenghaogoxiangmu.modle.HttpManager;
import com.example.fenghaogoxiangmu.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;

public class HomePersenter extends BasePersenter<IHome.View> implements IHome.Persenter {

    @Override
    public void getHome() {
        addSubscribe(HttpManager.getInstance().getFkApi().getHomeContent()
                .compose(RxUtils.rxScheduler())
                .map(new Function<HomeBean, List<HomeBean.HomeListBean>>() {
                    @Override
                    public List<HomeBean.HomeListBean> apply(HomeBean homebean) throws Exception {
                        List<HomeBean.HomeListBean> list=new ArrayList<>();
                        //第一个对象的封装 Banner
                        HomeBean.HomeListBean banner=new HomeBean.HomeListBean();
                        banner.currentType=HomeBean.ITEM_TYPE_BANNER;
                        banner.data=homebean.getData().getBanner();
                        list.add(banner);
                        //导航的封装
                        HomeBean.HomeListBean tab=new HomeBean.HomeListBean();
                        tab.currentType=HomeBean.ITEM_TYPE_TAB;
                        tab.data=homebean.getData().getChannel();
                        list.add(tab);
                        //封装带top边距的标题
                        HomeBean.HomeListBean title1=new HomeBean.HomeListBean();
                        title1.currentType=HomeBean.ITEM_TYPE_TITLETOP;
                        title1.data="品牌制造商直供";
                        list.add(title1);
                        //封装品牌制造商直供的列表数据
                        for (int i = 0; i < homebean.getData().getBrandList().size(); i++) {
                            HomeBean.HomeListBean brand=new HomeBean.HomeListBean();
                            brand.currentType=HomeBean.ITEM_TYPE_BRAND;
                            brand.data=homebean.getData().getBrandList().get(i);
                            list.add(brand);
                        }
                        //新品首发标题
                        HomeBean.HomeListBean title2=new HomeBean.HomeListBean();
                        title2.currentType=HomeBean.ITEM_TYPE_TITLE;
                        title2.data="周一周四·新品首发";
                        list.add(title2);
                        //新品首发数据封装
                        for (int i = 0; i < homebean.getData().getNewGoodsList().size(); i++) {
                            HomeBean.HomeListBean brand=new HomeBean.HomeListBean();
                            brand.currentType=HomeBean.ITEM_TYPE_NEW;
                            brand.data=homebean.getData().getNewGoodsList().get(i);
                            list.add(brand);
                        }
                        //人气推荐
                        HomeBean.HomeListBean title3=new HomeBean.HomeListBean();
                        title3.currentType=HomeBean.ITEM_TYPE_TITLETOP;
                        title3.data="人气推荐";
                        list.add(title3);
                        //人气推荐数据
                        for (int i = 0; i < homebean.getData().getHotGoodsList().size(); i++) {
                            HomeBean.HomeListBean brand=new HomeBean.HomeListBean();
                            brand.currentType=HomeBean.ITEM_TYPE_HOT;
                            brand.data=homebean.getData().getHotGoodsList().get(i);
                            list.add(brand);
                        }
                        //专题精选
                        HomeBean.HomeListBean title4=new HomeBean.HomeListBean();
                        title4.currentType=HomeBean.ITEM_TYPE_TITLETOP;
                        title4.data="专题精选";
                        list.add(title4);
                        //专题推荐数据
                        HomeBean.HomeListBean brand = new HomeBean.HomeListBean();
                        brand.currentType = HomeBean.ITEM_TYPE_TOPIC;
                        brand.data = homebean.getData().getTopicList();
                        list.add(brand);

                        //居家推荐
                        for (HomeBean.DataBean.CategoryListBean item:homebean.getData().getCategoryList()) {
                            HomeBean.HomeListBean title5 = new HomeBean.HomeListBean();
                            title5.currentType = HomeBean.ITEM_TYPE_TITLETOP;
                            title5.data = item.getName();
                            list.add(title5);
                            //居家推荐数据
                            for (HomeBean.DataBean.CategoryListBean.GoodsListBean good:item.getGoodsList()) {
                                HomeBean.HomeListBean bean = new HomeBean.HomeListBean();
                                bean.currentType = HomeBean.ITEM_TYPE_LIVING;
                                bean.data = good;
                                list.add(bean);
                            }
                        }
                        //餐厨推荐
                        HomeBean.HomeListBean title6=new HomeBean.HomeListBean();
                        title6.currentType=HomeBean.ITEM_TYPE_TITLETOP;
                        title6.data="餐厨";
                        list.add(title6);
                        //餐厨推荐数据
                        for (int i = 0; i < homebean.getData().getNewGoodsList().size(); i++) {
                            HomeBean.HomeListBean bean = new HomeBean.HomeListBean();
                            bean.currentType=HomeBean.ITEM_TYPE_KITCHEN;
                            bean.data=homebean.getData().getNewGoodsList().get(i);
                            list.add(bean);
                        }



                        return list;
                    }
                })
                .subscribeWith(new CommonSubscriber<List<HomeBean.HomeListBean>>(mView) {
                    @Override
                    public void onNext(List<HomeBean.HomeListBean> list) {
                        mView.getHomeReturn(list);
                    }
                })
        );
    }
}
