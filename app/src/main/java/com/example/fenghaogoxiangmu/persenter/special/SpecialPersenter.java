package com.example.fenghaogoxiangmu.persenter.special;

import com.example.fenghaogoxiangmu.base.BasePersenter;
import com.example.fenghaogoxiangmu.bean.special.SpecialBean;
import com.example.fenghaogoxiangmu.common.CommonSubscriber;
import com.example.fenghaogoxiangmu.interfaces.special.ISpecial;
import com.example.fenghaogoxiangmu.modle.HttpManager;
import com.example.fenghaogoxiangmu.utils.RxUtils;

public class SpecialPersenter extends BasePersenter<ISpecial.SpecialView> implements ISpecial.Persenter {
    @Override
    public void getSpecia() {
        addSubscribe(HttpManager.getInstance().getFkApi().getSpecial()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<SpecialBean>(mView) {
                    @Override
                    public void onNext(SpecialBean specialBean) {
                        mView.getSpecialReturn(specialBean);
                    }
                })
        );
    }
}
