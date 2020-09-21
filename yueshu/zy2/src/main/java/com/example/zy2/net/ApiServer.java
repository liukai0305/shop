package com.example.zy2.net;

import com.example.zy2.bean.CollBean;
import com.example.zy2.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiServer {
    String  WANAND="https://wanandroid.com/";

    @GET("wxarticle/chapters/json")
    Observable<HomeBean> getHome();

    @GET("wxarticle/list/408/1/json")
    Observable<CollBean> getColl();
}
