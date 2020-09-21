package com.example.xiangmuyi.persenter.home;

import com.example.xiangmuyi.base.BasePersenter;
import com.example.xiangmuyi.bean.homebean.HomeVideoBean;
import com.example.xiangmuyi.common.CommonSubscriber;
import com.example.xiangmuyi.interfaces.home.IHome;
import com.example.xiangmuyi.model.HttpManager;
import com.example.xiangmuyi.utils.RxUtils;

public class VideoPersenter extends BasePersenter<IHome.VideoView> implements IHome.VideoPersenter {
    @Override
    public void getVideo() {
        addSubscribe(HttpManager.getInstance().getTpApi().getVideo()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HomeVideoBean>(mView) {
                    @Override
                    public void onNext(HomeVideoBean homeVideoBean) {
                        mView.getVideo(homeVideoBean);
                    }
                })
        );
    }
}
