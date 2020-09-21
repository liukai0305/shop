package com.example.lianxi.interfaces.login;

import com.example.lianxi.interfaces.IBasePersenter;
import com.example.lianxi.interfaces.IBaseView;

public interface IRegister {


    interface View extends IBaseView {
        void registerReturn();

    }
    interface Presenter extends IBasePersenter<View> {
        void register(String name,String pw);

    }
}
