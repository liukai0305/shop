package com.example.lianxi.http;

public interface IHttpCallBack<T> {
    void onSuccess(T t);
    void onFile(String str);
}
