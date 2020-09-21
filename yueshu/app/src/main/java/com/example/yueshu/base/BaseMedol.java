package com.example.yueshu.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.completable.CompletableDisposeOn;

public class BaseMedol {
    private CompositeDisposable compositeDisposable=null;

    public void addDisposable(Disposable disposable) {
        if (compositeDisposable==null){
            synchronized (this){
                if (compositeDisposable==null){
                    compositeDisposable=new CompositeDisposable();
                }
            }
            compositeDisposable.add(disposable);
        }
    }
    public void Destroy(){
        compositeDisposable.dispose();
    }
    public void removeDispose(Disposable disposable){
        compositeDisposable.remove(disposable);

    }
}
