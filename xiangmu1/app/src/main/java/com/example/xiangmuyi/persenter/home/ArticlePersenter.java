package com.example.xiangmuyi.persenter.home;

import com.example.xiangmuyi.base.BasePersenter;
import com.example.xiangmuyi.bean.homebean.HomeArticleBean;
import com.example.xiangmuyi.common.CommonSubscriber;
import com.example.xiangmuyi.interfaces.home.IHome;
import com.example.xiangmuyi.model.HttpManager;
import com.example.xiangmuyi.utils.RxUtils;

public class ArticlePersenter extends BasePersenter<IHome.ArticleView> implements IHome.ArticlePersenter {

    @Override
    public void getArticle() {
        addSubscribe(HttpManager.getInstance().getTpApi().getArticle()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HomeArticleBean>(mView) {
                    @Override
                    public void onNext(HomeArticleBean homeArticleBean) {
                        mView.getArticle(homeArticleBean);
                    }
                })
        );
    }
}
