package com.example.lianxi.interfaces;

public interface IBasePersenter<T extends IBaseView> {
    //V层接口的关联
    void attachView(T view);
    //V层取消接口的关联
    void detachView();
}
