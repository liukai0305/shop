package com.example.lianxi.base;

import com.example.lianxi.interfaces.IBasePersenter;
import com.example.lianxi.interfaces.IBaseView;

import java.lang.ref.WeakReference;

public abstract class BasePersenter<V extends IBaseView> implements IBasePersenter<V> {
    protected V mView;
    WeakReference<V> weakReference;

    @Override
    public void attachView(V view) {
        weakReference=new WeakReference<>(view);
        mView=weakReference.get();

    }

    @Override
    public void detachView() {
            mView=null;
    }
}
