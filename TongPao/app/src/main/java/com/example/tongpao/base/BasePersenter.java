package com.example.tongpao.base;

import com.example.tongpao.interfaces.IBasePresenter;
import com.example.tongpao.interfaces.IBaseView;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePersenter<V extends IBaseView> implements IBasePresenter<V> {

    protected V mView;
    private WeakReference<V> vWeakReference;
    CompositeDisposable compositeDisposable;

    @Override
    public void attachView(V view) {
        vWeakReference = new WeakReference<>(view);
        mView = vWeakReference.get();
    }

    public void addSubscribe(Disposable disposable) {
        if (compositeDisposable == null)
            compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(disposable);
    }

    public void clearSubscribe(){
        if (compositeDisposable!=null){
            compositeDisposable.clear();
        }
    }

    @Override
    public void detachView() {
        mView = null;
        clearSubscribe();
    }
}
