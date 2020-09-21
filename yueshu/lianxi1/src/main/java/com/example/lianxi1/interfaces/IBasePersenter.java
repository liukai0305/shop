package com.example.lianxi1.interfaces;

public interface IBasePersenter <T extends IBaseView> {
    void attchView(T view);
    void detachView();
}
