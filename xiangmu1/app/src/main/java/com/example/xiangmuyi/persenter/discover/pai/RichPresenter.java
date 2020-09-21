package com.example.xiangmuyi.persenter.discover.pai;

import com.example.xiangmuyi.base.BasePersenter;
import com.example.xiangmuyi.bean.discoverbean.DiscoverHotBean;
import com.example.xiangmuyi.bean.discoverbean.PaiHangBang.EsperienBean;
import com.example.xiangmuyi.bean.discoverbean.PaiHangBang.LocalBean;
import com.example.xiangmuyi.bean.discoverbean.PaiHangBang.QianDaoBean;
import com.example.xiangmuyi.common.CommonSubscriber;
import com.example.xiangmuyi.interfaces.discover.IDiscover;
import com.example.xiangmuyi.model.HttpManager;
import com.example.xiangmuyi.utils.RxUtils;


public class RichPresenter extends BasePersenter<IDiscover.EsperienView> implements IDiscover.EsperienPersenter {


    @Override
    public void getEsperien() {
        addSubscribe(HttpManager.getInstance().getTpApi().getEsperien()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<EsperienBean>(mView) {
                    @Override
                    public void onNext(EsperienBean esperienBean) {
                        mView.getEsperien(esperienBean);
                    }
                })
        );
    }

    @Override
    public void getLocal() {
        addSubscribe(HttpManager.getInstance().getTpApi().getLocal()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<LocalBean>(mView) {
                    @Override
                    public void onNext(LocalBean localBean) {
                        mView.getLocal(localBean);
                    }
                })
        );
    }

    @Override
    public void getQian() {
        addSubscribe(HttpManager.getInstance().getTpApi().getQianDao()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<QianDaoBean>(mView) {
                    @Override
                    public void onNext(QianDaoBean qianDaoBean) {
                        mView.getQian(qianDaoBean);
                    }
                })
        );
    }
}
