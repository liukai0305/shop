package com.example.xiangmuyi.persenter.home.wenzhang;

import com.example.xiangmuyi.base.BasePersenter;
import com.example.xiangmuyi.common.CommonSubscriber;
import com.example.xiangmuyi.interfaces.home.IHome;
import com.example.xiangmuyi.model.HttpManager;
import com.example.xiangmuyi.utils.RxUtils;
import com.example.xiangmuyi.zhishibean.HomeKnowHanfuBean;

public class KnowHanfuPersenter extends BasePersenter<IHome.KnowHanFuView> implements IHome.KnowHanFuPersenter{

    @Override
    public void getKnow() {
        addSubscribe(HttpManager.getInstance().getTpApi().getKnow()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HomeKnowHanfuBean>(mView) {
                    @Override
                    public void onNext(HomeKnowHanfuBean homeKnowHanfuBean) {
                        mView.getKnow(homeKnowHanfuBean);
                    }
                })
        );
    }
}
