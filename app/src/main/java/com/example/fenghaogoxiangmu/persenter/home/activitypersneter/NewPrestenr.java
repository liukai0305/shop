package com.example.fenghaogoxiangmu.persenter.home.activitypersneter;

import com.example.fenghaogoxiangmu.base.BasePersenter;

import com.example.fenghaogoxiangmu.bean.home.activitybean.BrandRBean;
import com.example.fenghaogoxiangmu.common.CommonSubscriber;
import com.example.fenghaogoxiangmu.interfaces.home.activity.INew;
import com.example.fenghaogoxiangmu.modle.HttpManager;
import com.example.fenghaogoxiangmu.utils.RxUtils;

import java.util.HashMap;

public class NewPrestenr extends BasePersenter<INew.View> implements INew.Persenter {
    @Override
    public void getBrand(HashMap<String, Integer> map) {
        addSubscribe(HttpManager.getInstance().getFkApi().getBrand(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<BrandRBean>(mView) {
                    @Override
                    public void onNext(BrandRBean bean) {
                        mView.getBrandRBean(bean);
                    }
                }));
    }

    @Override
    public void getNew(int id) {
        addSubscribe(
                HttpManager.getInstance().getFkApi().getNewBean(id)
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<BrandRBean>(mView) {
                            @Override
                            public void onNext(BrandRBean brandRBean) {
                                mView.getNewReturn(brandRBean);
                            }
                        })
        );
    }
}
