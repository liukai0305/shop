package com.example.kaohe.modle.api;


import com.example.kaohe.bean.LieBiaoBean;
import com.example.kaohe.bean.ReMenBean;
import com.example.kaohe.bean.TuiJianBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface TpApi {
    @GET("hotpost.json")
    Flowable<LieBiaoBean> getLieBiao();

    @GET("hottopic.json")
    Flowable<ReMenBean> getReMen();

    @GET("hotuser.json ")
    Flowable<TuiJianBean> getTuijian();

}
