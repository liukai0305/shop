package com.example.tongpao.presenters.personal;

import android.util.Log;

import com.example.tongpao.base.BasePersenter;
import com.example.tongpao.bean.PersonalActivityBean;
import com.example.tongpao.bean.PersonalMsgBean;
import com.example.tongpao.common.CommonSubscriber;
import com.example.tongpao.interfaces.home.IHomePersonal;
import com.example.tongpao.model.HttpManager;
import com.example.tongpao.utils.RxUtils;

public class PersonalActivityPresenter extends BasePersenter<IHomePersonal.PersonalActivityView> implements IHomePersonal.PersonalActivityPresenter {
    @Override
    public void getPersonalActivity() {
        addSubscribe(HttpManager.getInstance().getTpApi().getPersonalActivity()
                .compose(RxUtils.<PersonalActivityBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<PersonalActivityBean>(mView) {
                    @Override
                    public void onNext(PersonalActivityBean personalActivityBean) {
                        mView.getPersonalActivityReturn(personalActivityBean);
                    }
                }));
    }
}
