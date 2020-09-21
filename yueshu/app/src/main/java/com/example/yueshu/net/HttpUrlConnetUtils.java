package com.example.yueshu.net;

import java.util.HashMap;

public class HttpUrlConnetUtils implements INetWork {
    private static volatile HttpUrlConnetUtils retrofitUtils;

    public static HttpUrlConnetUtils getUrlConnetUtils() {
        if (retrofitUtils==null){
            synchronized (RetrofitUtils.class){
                if (retrofitUtils==null){
                    retrofitUtils=new HttpUrlConnetUtils();
                }
            }
        }
        return retrofitUtils;
    }

    @Override
    public <T> void get(String url, ICollBack<T> iCollBack) {

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
