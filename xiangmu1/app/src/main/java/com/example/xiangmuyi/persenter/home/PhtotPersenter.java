package com.example.xiangmuyi.persenter.home;

import com.example.xiangmuyi.base.BasePersenter;
import com.example.xiangmuyi.bean.homebean.HomePhtotBean;
import com.example.xiangmuyi.common.CommonSubscriber;
import com.example.xiangmuyi.interfaces.home.IHome;
import com.example.xiangmuyi.model.HttpManager;
import com.example.xiangmuyi.utils.RxUtils;

public class PhtotPersenter extends BasePersenter<IHome.PhtotView> implements IHome.PhtotPersenter {

    @Override
    public void getPhtot() {
        addSubscribe(HttpManager.getInstance().getTpApi().getPhtot()
        .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HomePhtotBean>(mView) {
                    @Override
                    public void onNext(HomePhtotBean homePhtotBean) {
                        mView.getPhtot(homePhtotBean);
                    }
                })
        );
    }
}
