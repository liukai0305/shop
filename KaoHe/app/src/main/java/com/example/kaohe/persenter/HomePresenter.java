package com.example.kaohe.persenter;

import android.util.Log;

import com.example.kaohe.base.BasePersenter;
import com.example.kaohe.bean.LieBiaoBean;
import com.example.kaohe.bean.ReMenBean;
import com.example.kaohe.bean.TuiJianBean;
import com.example.kaohe.common.CommonSubscriber;
import com.example.kaohe.interfaces.IHome;
import com.example.kaohe.modle.HttpManager;
import com.example.kaohe.utils.RxUtils;

public class HomePresenter extends BasePersenter<IHome.IHomeView> implements IHome.IHomePersenter {
    @Override
    public void getLieBiao() {
        addSubscribe(HttpManager.getInstance().getTpApi().getLieBiao()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<LieBiaoBean>(mView) {
                    @Override
                    public void onNext(LieBiaoBean lieBiaoBean) {
                        mView.getLieBiao(lieBiaoBean);

                    }
                }));
    }

    @Override
    public void getReMen() {
        addSubscribe(HttpManager.getInstance().getTpApi().getReMen()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<ReMenBean>(mView) {
                    @Override
                    public void onNext(ReMenBean reMenBean) {
                        mView.getReMen(reMenBean);

                    }
                }));
    }

    @Override
    public void getTuiJian() {
        addSubscribe(HttpManager.getInstance().getTpApi().getTuijian()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TuiJianBean>(mView) {
                    @Override
                    public void onNext(TuiJianBean tuiJianBean) {
                        mView.getTuiJian(tuiJianBean);

                    }
                }));
    }
}
