package com.example.xiangmuyi.persenter.home.wenzhang;

import com.example.xiangmuyi.base.BasePersenter;
import com.example.xiangmuyi.common.CommonSubscriber;
import com.example.xiangmuyi.interfaces.home.IHome;
import com.example.xiangmuyi.model.HttpManager;
import com.example.xiangmuyi.utils.RxUtils;
import com.example.xiangmuyi.zhishibean.HomeEventshanfuBean;

public class EventshanfuPersenter extends BasePersenter<IHome.EventshanfuView> implements IHome.EventshanfuPersenter {

    @Override
    public void getEventshanfu() {
        addSubscribe(HttpManager.getInstance().getTpApi().getEventshanfu()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HomeEventshanfuBean>(mView) {
                    @Override
                    public void onNext(HomeEventshanfuBean homeEventshanfuBean) {
                        mView.getEventshanfu(homeEventshanfuBean);
                    }
                })
        );
    }
}
