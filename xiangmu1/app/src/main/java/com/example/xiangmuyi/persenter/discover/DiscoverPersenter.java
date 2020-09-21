package com.example.xiangmuyi.persenter.discover;


import com.example.xiangmuyi.base.BasePersenter;
import com.example.xiangmuyi.bean.discoverbean.DiscoverHotBean;
import com.example.xiangmuyi.bean.discoverbean.DiscoverHotspotBean;
import com.example.xiangmuyi.bean.discoverbean.DiscoverNavigationBean;
import com.example.xiangmuyi.bean.homebean.HomeArticleBean;
import com.example.xiangmuyi.common.CommonSubscriber;
import com.example.xiangmuyi.interfaces.discover.IDiscover;
import com.example.xiangmuyi.model.HttpManager;
import com.example.xiangmuyi.utils.RxUtils;

public class DiscoverPersenter extends BasePersenter<IDiscover.HotView> implements IDiscover.HotPersenter {
    @Override
    public void getHot() {
        addSubscribe(HttpManager.getInstance().getTpApi().getHot()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<DiscoverHotBean>(mView) {
                    @Override
                    public void onNext(DiscoverHotBean discoverHotBean) {
                        mView.getHot(discoverHotBean);
                    }
                })
        );
    }

    @Override
    public void getNavigation() {
        addSubscribe(HttpManager.getInstance().getTpApi().getNavigation()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<DiscoverNavigationBean>(mView) {
                    @Override
                    public void onNext(DiscoverNavigationBean discoverNavigationBean) {
                        mView.getNavigation(discoverNavigationBean);
                    }
                })
        );
    }

    @Override
    public void getHospot(int cid) {
        addSubscribe(HttpManager.getInstance().getTpApi().getHotspot(cid)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<DiscoverHotspotBean>(mView) {
                    @Override
                    public void onNext(DiscoverHotspotBean discoverHotspotBean) {
                        mView.getHospot(discoverHotspotBean);
                    }
                })
        );
    }
}
