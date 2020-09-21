package com.example.fenghaogoxiangmu.interfaces.auth;

import com.example.fenghaogoxiangmu.bean.user.LoginBean;
import com.example.fenghaogoxiangmu.interfaces.IBasePersenter;
import com.example.fenghaogoxiangmu.interfaces.IBaseView;

public interface IAuth {
    interface LoginView extends IBaseView{
        void getLogin(LoginBean bean);
    }
    interface LoginPresenter extends IBasePersenter<LoginView>{
        void getLogin(String username,String passwrd);
    }
}
