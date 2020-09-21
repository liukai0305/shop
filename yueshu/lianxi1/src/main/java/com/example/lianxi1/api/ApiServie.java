package com.example.lianxi1.api;

import com.example.lianxi1.bean.ChapterBean;
import com.example.lianxi1.bean.ResultBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ApiServie {

    @GET("index")
    Flowable<ResultBean> getIndex();

    @GET("wxarticle/chapters/json")
    Flowable<ChapterBean> getChapters();
}
