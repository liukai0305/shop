package com.example.xiangmuyi.persenter.discover.pao;

import com.example.xiangmuyi.base.BasePersenter;
import com.example.xiangmuyi.bean.discoverbean.DiscoverHotBean;
import com.example.xiangmuyi.bean.discoverbean.paozibean.PaoNearbyBean;
import com.example.xiangmuyi.bean.discoverbean.paozibean.PaoRecommendBean;
import com.example.xiangmuyi.common.CommonSubscriber;
import com.example.xiangmuyi.interfaces.discover.IDiscover;
import com.example.xiangmuyi.model.HttpManager;
import com.example.xiangmuyi.utils.RxUtils;

public class PaoPersenter extends BasePersenter<IDiscover.PaoView> implements IDiscover.PaoPersenter {
    @Override
    public void getPaoRecommend() {
        addSubscribe(HttpManager.getInstance().getTpApi().getPaoRecommend()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<PaoRecommendBean>(mView) {
                    @Override
                    public void onNext(PaoRecommendBean paoRecommendBean) {
                        mView.getPaoRecommend(paoRecommendBean);
                    }
                })
        );
    }

    @Override
    public void getPaoNearby() {
        addSubscribe(HttpManager.getInstance().getTpApi().getPaoNearby()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<PaoNearbyBean>(mView) {
                    @Override
                    public void onNext(PaoNearbyBean paoNearbyBean) {
                        mView.getPaoNearby(paoNearbyBean);
                    }
                })
        );
    }
}
