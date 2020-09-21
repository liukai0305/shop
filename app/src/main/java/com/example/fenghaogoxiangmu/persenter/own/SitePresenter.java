package com.example.fenghaogoxiangmu.persenter.own;

import com.example.fenghaogoxiangmu.base.BasePersenter;
import com.example.fenghaogoxiangmu.bean.own.SiteBean;
import com.example.fenghaogoxiangmu.common.CommonSubscriber;
import com.example.fenghaogoxiangmu.interfaces.own.IOwn;
import com.example.fenghaogoxiangmu.modle.HttpManager;
import com.example.fenghaogoxiangmu.utils.RxUtils;

public class SitePresenter extends BasePersenter<IOwn.ISiteView>implements IOwn.ISitePresenter {
    @Override
    public void getSite(int id) {
        addSubscribe(HttpManager.getInstance().getFkApi().getsite(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<SiteBean>(mView) {
                    @Override
                    public void onNext(SiteBean siteBean) {
                        mView.getSiteresult(siteBean);
                    }
                })
        );
    }
}
