package com.example.zy.net;

public interface IHttpCallBack<T> {
    void onSuccess(T t);
    void onFile(String str);
}
