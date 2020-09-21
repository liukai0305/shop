package com.example.xiangmuyi.persenter.own;


import com.example.xiangmuyi.base.BasePersenter;
import com.example.xiangmuyi.bean.OwnBean.LoginBean;
import com.example.xiangmuyi.bean.OwnBean.RegisterBean;
import com.example.xiangmuyi.common.CommonSubscriber;
import com.example.xiangmuyi.interfaces.own.IOwn;
import com.example.xiangmuyi.model.HttpManager;
import com.example.xiangmuyi.utils.RxUtils;

public class LoginPersenter extends BasePersenter<IOwn.LoginView> implements IOwn.LoginPersenter {



    @Override
    public void getlogin(String username, String passwrd) {
        addSubscribe(HttpManager.getInstance().getUserApi().getLoginData(username,passwrd)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<LoginBean>(mView) {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        mView.getlogin(loginBean);
                    }
                })
        );
    }
}
