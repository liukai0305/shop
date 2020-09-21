package com.example.zy2.model;

import com.example.zy2.bean.CollBean;
import com.example.zy2.bean.HomeBean;
import com.example.zy2.net.ApiServer;
import com.example.zy2.net.ICollBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentsModelImp implements IModel {

    @Override
    public void setHomeData(ICollBack iCollBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServer.WANAND)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        Observable<HomeBean> home = apiServer.getHome();
        home.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                            iCollBack.onSuccessHome(homeBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCollBack.onFile(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    public void setCollData(ICollBack iCollBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServer.WANAND)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        Observable<CollBean> coll = apiServer.getColl();
        coll.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CollBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CollBean collBean) {
                    iCollBack.onSuccessColl(collBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                    iCollBack.onFile(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
