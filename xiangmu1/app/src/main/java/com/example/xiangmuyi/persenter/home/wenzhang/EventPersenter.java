package com.example.xiangmuyi.persenter.home.wenzhang;

import com.example.xiangmuyi.base.BasePersenter;
import com.example.xiangmuyi.bean.homebean.WanZhangEventsBean;
import com.example.xiangmuyi.common.CommonSubscriber;
import com.example.xiangmuyi.interfaces.home.IHome;
import com.example.xiangmuyi.model.HttpManager;
import com.example.xiangmuyi.utils.RxUtils;

public class EventPersenter extends BasePersenter<IHome.EventView> implements IHome.EventPersenter {


    @Override
    public void getEvent(int id) {
        addSubscribe(HttpManager.getInstance().getTpApi().getEvents(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<WanZhangEventsBean>(mView) {
                    @Override
                    public void onNext(WanZhangEventsBean wanZhangEventsBean) {
                        mView.getEvent(wanZhangEventsBean);
                    }
                })
        );
    }
}
