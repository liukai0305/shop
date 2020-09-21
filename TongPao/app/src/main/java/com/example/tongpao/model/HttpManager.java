package com.example.tongpao.model;

import android.util.Log;


import com.example.tongpao.common.Constants;
import com.example.tongpao.model.api.TpApi;
import com.example.tongpao.utils.SystemUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    private static HttpManager httpManager;

    public static HttpManager getInstance(){
        if (httpManager==null){
            synchronized (HttpManager.class){
                if (httpManager==null){
                    httpManager = new HttpManager();
                }
            }
        }
        return httpManager;
    }
    TpApi tpApi;

    private static Retrofit getRetrofit(String url){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    private static OkHttpClient getOkHttpClient() {
        File file = new File(Constants.PATH_CACHE);
        Cache cache = new Cache(file, 1024 * 1024 *100);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(new LoggingINterceptor())
                .addNetworkInterceptor(new NetInterceptor())
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        return okHttpClient;
    }

    private static class LoggingINterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long l = System.nanoTime();
            Log.i("interceptor",String.format("Sending request %s on %s%n%s",request.url(),chain.connection(),request.headers()));
            Response response = chain.proceed(request);
            if (response.header("session_id")!=null){

            }
            return response;
        }
    }

    private static class NetInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!SystemUtils.checkNetWork()){
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            if (!SystemUtils.checkNetWork()){
                int maxAge = 0;
                return response.newBuilder()
                        .removeHeader("Pragme")
                        .header("Cache-Control","public,max-age="+maxAge).build();
            }else {
                int maxStale = 60*60*24*28;
                return response.newBuilder()
                        .removeHeader("Pragme")
                        .header("Cache-Control","public,onlyif=cached,max-stale="+maxStale).build();
            }
        }
    }
    public TpApi getTpApi(){
        if (tpApi==null){
            tpApi = getRetrofit(Constants.Base_YunUrl).create(TpApi.class);
        }
        return tpApi;
    }
}
