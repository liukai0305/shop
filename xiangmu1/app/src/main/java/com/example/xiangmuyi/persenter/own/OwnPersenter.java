package com.example.xiangmuyi.persenter.own;


import com.example.xiangmuyi.base.BasePersenter;
import com.example.xiangmuyi.bean.OwnBean.RegisterBean;
import com.example.xiangmuyi.bean.homebean.HomeArticleBean;
import com.example.xiangmuyi.common.CommonSubscriber;
import com.example.xiangmuyi.interfaces.own.IOwn;
import com.example.xiangmuyi.model.HttpManager;
import com.example.xiangmuyi.utils.RxUtils;

public class OwnPersenter extends BasePersenter<IOwn.RegisterView> implements IOwn.RegisterPersenter {


    @Override
    public void getRegisterlogin(String username, String passwrd) {
        addSubscribe(HttpManager.getInstance().getUserApi().getRegisterData(username,passwrd)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<RegisterBean>(mView) {
                    @Override
                    public void onNext(RegisterBean registerBean) {
                        mView.getRegisterlogin(registerBean);
                    }
                })
        );
    }
}
