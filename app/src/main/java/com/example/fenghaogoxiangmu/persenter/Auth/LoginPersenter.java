package com.example.fenghaogoxiangmu.persenter.Auth;

import com.example.fenghaogoxiangmu.base.BasePersenter;
import com.example.fenghaogoxiangmu.bean.user.LoginBean;
import com.example.fenghaogoxiangmu.common.CommonSubscriber;
import com.example.fenghaogoxiangmu.interfaces.auth.IAuth;
import com.example.fenghaogoxiangmu.modle.HttpManager;
import com.example.fenghaogoxiangmu.utils.RxUtils;

public class LoginPersenter extends BasePersenter<IAuth.LoginView> implements IAuth.LoginPresenter {
    @Override
    public void getLogin(String username, String passwrd) {
        addSubscribe(HttpManager.getInstance().getAuthApi().getLoginData(username,passwrd)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<LoginBean>(mView) {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        mView.getLogin(loginBean);
                    }
                })
        );
    }
}
