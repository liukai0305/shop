package com.example.xiangmuyi.persenter.home.wenzhang;

import com.example.xiangmuyi.base.BasePersenter;
import com.example.xiangmuyi.common.CommonSubscriber;
import com.example.xiangmuyi.interfaces.home.IHome;
import com.example.xiangmuyi.model.HttpManager;
import com.example.xiangmuyi.utils.RxUtils;
import com.example.xiangmuyi.zhishibean.HomeEvolutionBean;
import com.example.xiangmuyi.zhishibean.HomeKnowHanfuBean;

public class EvolutionPersenter extends BasePersenter<IHome.EvolutionView> implements IHome.EvolutionPersenter{

    @Override
    public void getEvolution() {
        addSubscribe(HttpManager.getInstance().getTpApi().getEvolution()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HomeEvolutionBean>(mView) {
                    @Override
                    public void onNext(HomeEvolutionBean homeEvolutionBean) {
                        mView.getEvolution(homeEvolutionBean);
                    }
                })
        );
    }
}
