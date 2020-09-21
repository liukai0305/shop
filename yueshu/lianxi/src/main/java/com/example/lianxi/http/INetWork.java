package com.example.lianxi.http;

import java.util.HashMap;

public interface INetWork  {
    public <T> void get(String url,IHttpCallBack<T> iHttpCallBack);
    public <T> void get(String url, HashMap<String,String> hashMap, IHttpCallBack<T> iHttpCallBack);
    public <T> void post(String url,IHttpCallBack<T> iHttpCallBack);
    public <T> void post(String url,HashMap<String,String> hashMap,IHttpCallBack<T> iHttpCallBack);
}
