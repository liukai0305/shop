package com.example.fenghaogoxiangmu.persenter.classify;

import android.util.Log;

import com.example.fenghaogoxiangmu.base.BasePersenter;
import com.example.fenghaogoxiangmu.bean.classify.ClassifyBean;
import com.example.fenghaogoxiangmu.bean.home.activitybean.GoodDetailBean;
import com.example.fenghaogoxiangmu.common.CommonSubscriber;
import com.example.fenghaogoxiangmu.interfaces.classify.IClassify;
import com.example.fenghaogoxiangmu.interfaces.home.activity.ICart;
import com.example.fenghaogoxiangmu.modle.HttpManager;
import com.example.fenghaogoxiangmu.utils.RxUtils;

public class ClassifyPersenter  extends BasePersenter<IClassify.View> implements IClassify.Persenter {


    @Override
    public void getClassify() {
        addSubscribe(HttpManager.getInstance().getFkApi().getClassifyContent()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<ClassifyBean>(mView) {
                    @Override
                    public void onNext(ClassifyBean classifyBean) {

                        mView.getClassifyReturn(classifyBean);
                    }
                })
        );
    }
}
