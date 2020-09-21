package com.example.yueshu.net;

public interface ICollBack<T> {
    void onSuccess(T t);
    void onFile(String str);
}
