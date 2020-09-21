package com.example.fenghaogoxiangmu.modle;

import android.util.Log;


import com.example.fenghaogoxiangmu.common.Constants;
import com.example.fenghaogoxiangmu.modle.api.AuthApi;
import com.example.fenghaogoxiangmu.modle.api.FkApi;
import com.example.fenghaogoxiangmu.utils.SystemUtils;

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

/**
 * http协议
 * 负责网络请求
 */
public class HttpManager {

    private static HttpManager instance;

    public static HttpManager getInstance(){
        if(instance == null){
            synchronized (HttpManager.class){
                if(instance == null){
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    /**
     * 网络请求接口
     */
    FkApi fkApi;
    AuthApi authApi;

    private static Retrofit getRetrofit(String url){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    private static OkHttpClient getOkHttpClient(){
        //定义本地缓存文件
        File file = new File(Constants.PATH_CACHE);
        //cache缓存文件的操作
        Cache cache = new Cache(file,1024*1204*100);
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(new LoggingInterceptor())
                .addNetworkInterceptor(new NetInterceptor())
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        return client;
    }

    /**
     * 日志的拦截器打印报文信息
     */
    static class LoggingInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            Log.i("interceptor",String.format("Sending request %s on %s%n%s",request.url(),chain.connection(),request.headers()));

            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            Log.i("Received:",String.format("Received response for %s in %.1fms%n%s",response.request().url(),(t2-t1)/1e6d,response.headers()));
            if(response.header("session_id") != null){
                //Constant.session_id = response.header("session_id");
            }
            return response;
        }
    }

    static class NetInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if(!SystemUtils.checkNetWork()){
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            //通过判断网络连接是否存在获取本地或者服务器的数据
            if(!SystemUtils.checkNetWork()){
                int maxAge = 0;
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control","public ,max-age="+maxAge).build();
            }else{
                int maxStale = 60*60*24*28; //设置缓存数据的保存时间
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control","public, onlyif-cached, max-stale="+maxStale).build();
            }
        }
    }


    /**
     * 获取网络接口请求的接口类
     * @return
     */
    public FkApi getFkApi(){
        if(fkApi == null){
            fkApi = getRetrofit(Constants.Base_FengKuangGo).create(FkApi.class);
        }
        return fkApi;
    }
    public AuthApi getAuthApi(){
        if(authApi == null){
            authApi = getRetrofit(Constants.Base_FengKuangGo).create(AuthApi.class);
        }
        return authApi;
    }



}
