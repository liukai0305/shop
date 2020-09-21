package com.example.lianxi.http;

import java.util.HashMap;

public class HttpUrlConnetUtils implements INetWork {
    private static volatile HttpUrlConnetUtils httpUrlConnetUtils;

    public static HttpUrlConnetUtils getHttpUrlConnetUtils() {
        if (httpUrlConnetUtils==null){
            synchronized (RetrofitUtils.class){
                if (httpUrlConnetUtils==null){
                    httpUrlConnetUtils=new HttpUrlConnetUtils();
                }
            }
        }
        return httpUrlConnetUtils;
    }

    @Override
    public <T> void get(String url, IHttpCallBack<T> iHttpCallBack) {

    }

    @Override
    public <T> void get(String url, HashMap<String, String> hashMap, IHttpCallBack<T> iHttpCallBack) {

    }

    @Override
    public <T> void post(String url, IHttpCallBack<T> iHttpCallBack) {

    }

    @Override
    public <T> void post(String url, HashMap<String, String> hashMap, IHttpCallBack<T> iHttpCallBack) {

    }
}
