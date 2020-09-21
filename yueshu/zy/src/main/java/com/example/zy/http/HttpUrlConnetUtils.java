package com.example.zy.http;

import com.example.zy.net.IHttpCallBack;
import com.example.zy.net.INetWork;

public class HttpUrlConnetUtils implements INetWork {
    private static  volatile HttpUrlConnetUtils retrofitUtils;
    public static HttpUrlConnetUtils getRetrofitUtils() {
        if (retrofitUtils == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofitUtils == null) {
                    retrofitUtils = new HttpUrlConnetUtils();
                }
            }
        }
        return retrofitUtils;
    }
    @Override
    public <T> void get(String Url, IHttpCallBack<T> iHttpCallBack) {

    }
}
