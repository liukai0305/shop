package com.example.tongpao.presenters.personal;

import android.util.Log;

import com.example.tongpao.base.BasePersenter;
import com.example.tongpao.bean.PersonalMsgBean;
import com.example.tongpao.common.CommonSubscriber;
import com.example.tongpao.interfaces.home.IHomePersonal;
import com.example.tongpao.model.HttpManager;
import com.example.tongpao.utils.RxUtils;

public class PersonalPresenter extends BasePersenter<IHomePersonal.PersonalMsgView> implements IHomePersonal.PersonalMsgPresenter{
    private static final String TAG = "PersonalPresenter";
    @Override
    public void getPersonalMsg() {
        addSubscribe(HttpManager.getInstance().getTpApi().getPersonalMsg()
                .compose(RxUtils.<PersonalMsgBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<PersonalMsgBean>(mView) {
                    @Override
                    public void onNext(PersonalMsgBean personalMsgBean) {
                        mView.getPersonalMsgReturn(personalMsgBean);
                        Log.d(TAG, "onNext: "+personalMsgBean.toString());

                    }
                }));
    }
}
