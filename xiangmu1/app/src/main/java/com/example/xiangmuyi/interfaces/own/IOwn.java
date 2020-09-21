package com.example.xiangmuyi.interfaces.own;


import com.example.xiangmuyi.bean.OwnBean.LoginBean;
import com.example.xiangmuyi.bean.OwnBean.RegisterBean;
import com.example.xiangmuyi.bean.OwnBean.UserUpdateBean;
import com.example.xiangmuyi.interfaces.IBasePersenter;
import com.example.xiangmuyi.interfaces.IBaseView;
import com.example.xiangmuyi.interfaces.home.IHome;

import java.util.Map;

public interface IOwn {
    interface View extends IBaseView {

    }

    interface Persenter extends IBasePersenter<IHome.View> {

    }
    interface RegisterView extends IBaseView{
        void getRegisterlogin(RegisterBean login);
    }
    interface RegisterPersenter extends IBasePersenter<RegisterView>{
        void getRegisterlogin(String username,String passwrd);
    }
    //登入
    interface LoginView extends IBaseView{
        void getlogin(LoginBean login);
    }
    interface LoginPersenter extends IBasePersenter<LoginView>{
        void getlogin(String username,String passwrd);
    }
    //更新信息
    interface UpDataView extends IBaseView {
        void updateUserInfoReturn(UserUpdateBean result);
    }

    interface UpDataPersenter extends IBasePersenter<UpDataView> {

        void updateUserInfo(Map<String,String> map);

    }
}
