package com.example.zy.net;

public interface INetWork {
   public  <T> void get(String Url, IHttpCallBack<T> iHttpCallBack);
}
