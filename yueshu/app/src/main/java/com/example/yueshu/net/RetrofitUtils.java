package com.example.yueshu.net;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrofitUtils implements INetWork {

    private static volatile RetrofitUtils retrofitUtils;
    private final ApiServer apiServer;

    public static RetrofitUtils getInstance() {
        if (retrofitUtils == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofitUtils == null) {
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }


    private RetrofitUtils() {
        OkHttpClient build = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gank.io/api/data/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiServer = retrofit.create(ApiServer.class);
    }

    @Override
    public <T> void get(String url, final ICollBack<T> iCollBack) {
        apiServer.getHttp(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            Gson gson = new Gson();
                            Type[] genericInterfaces = iCollBack.getClass().getGenericInterfaces();
                            Type[] actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();
                            Type type = actualTypeArguments[0];
                            T t = gson.fromJson(responseBody.string(), type);
                            iCollBack.onSuccess(t);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public <T> void get(String url, HashMap<String, String> hashMap, ICollBack<T> iCollBack) {

    }

    @Override
    public <T> void post(String url, ICollBack<T> iCollBack) {

    }

    @Override
    public <T> void post(String url, HashMap<String, String> hashMap, ICollBack<T> iCollBack) {

    }
}
