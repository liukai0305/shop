package com.example.xiangmuyi.persenter.discover.she;

import com.example.xiangmuyi.base.BasePersenter;
import com.example.xiangmuyi.bean.discoverbean.paozibean.PaoRecommendBean;
import com.example.xiangmuyi.bean.discoverbean.shetuan.SheTuanBean;
import com.example.xiangmuyi.common.CommonSubscriber;
import com.example.xiangmuyi.interfaces.discover.IDiscover;
import com.example.xiangmuyi.model.HttpManager;
import com.example.xiangmuyi.utils.RxUtils;

public class SheTuanPresenter extends BasePersenter<IDiscover.SheTuanView> implements IDiscover.SheTuanPersenter {
    @Override
    public void getShetuan() {
        addSubscribe(HttpManager.getInstance().getTpApi().getSheTuan()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<SheTuanBean>(mView) {
                    @Override
                    public void onNext(SheTuanBean sheTuanBean) {
                        mView.getSheTuan(sheTuanBean);
                    }
                })
        );
    }
}