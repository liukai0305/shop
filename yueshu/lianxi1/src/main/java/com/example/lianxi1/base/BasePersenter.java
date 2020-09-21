package com.example.lianxi1.base;

import com.example.lianxi1.interfaces.IBasePersenter;
import com.example.lianxi1.interfaces.IBaseView;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePersenter<V extends IBaseView> implements IBasePersenter<V> {

    protected V mView;
    WeakReference<V> weakReference;
    CompositeDisposable compositeDisposable;



    @Override
    public void attchView(V view) {
        weakReference=new WeakReference<>(view);
        mView=weakReference.get();
    }

    public void addSubscribe(Disposable disposable){
        if (compositeDisposable==null) compositeDisposable=new CompositeDisposable();
        compositeDisposable.add(disposable);
    }

    public void clearSubscribe(){
        if (compositeDisposable!=null){
            compositeDisposable.dispose();
        }
    }

    @Override
    public void detachView() {
        mView=null;
        clearSubscribe();
    }
}
