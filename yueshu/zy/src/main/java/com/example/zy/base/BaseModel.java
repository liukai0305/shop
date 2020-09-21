package com.example.zy.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseModel {
    private CompositeDisposable compositeDisposable=null;
    public void addDisposable(Disposable disposable){
        if (compositeDisposable==null){
            synchronized (this){
                if (compositeDisposable==null){
                    compositeDisposable=new CompositeDisposable();
                }
            }
        }
        compositeDisposable.add(disposable);
    }
    public void Dostey(){
        compositeDisposable.dispose();
    }
    public void removeDisposable(Disposable disposable){
        compositeDisposable.remove(disposable);

    }
}
