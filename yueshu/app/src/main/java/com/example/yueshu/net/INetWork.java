package com.example.yueshu.net;

import java.util.HashMap;

public interface INetWork {
    public <T> void get(String url, ICollBack<T> iCollBack);

    public <T> void get(String url, HashMap<String, String> hashMap, ICollBack<T> iCollBack);

    public <T> void post(String url, ICollBack<T> iCollBack);

    public <T> void post(String url, HashMap<String, String> hashMap, ICollBack<T> iCollBack);

}
