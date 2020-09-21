package com.example.xiangmuyi.persenter.home.wenzhang;

import com.example.xiangmuyi.base.BasePersenter;
import com.example.xiangmuyi.common.CommonSubscriber;
import com.example.xiangmuyi.interfaces.home.IHome;
import com.example.xiangmuyi.model.HttpManager;
import com.example.xiangmuyi.utils.RxUtils;
import com.example.xiangmuyi.zhishibean.HomeKnowHanfuBean;
import com.example.xiangmuyi.zhishibean.HomeTraDitionalBean;

public class TraDitionalPersenter extends BasePersenter<IHome.TraDitionalView> implements IHome.TraDitionalPersenter{



    @Override
    public void getTraDitional() {
        addSubscribe(HttpManager.getInstance().getTpApi().getTraDitional()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HomeTraDitionalBean>(mView) {
                    @Override
                    public void onNext(HomeTraDitionalBean homeKnowHanfuBean) {
                        mView.getTraDitional(homeKnowHanfuBean);
                    }
                })
        );
    }
}
