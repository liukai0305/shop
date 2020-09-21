package com.example.fenghaogoxiangmu.modle.api;

import com.example.fenghaogoxiangmu.bean.user.LoginBean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthApi {
    //登入
    @POST("auth/login")
    @FormUrlEncoded
    Flowable<LoginBean> getLoginData(@Field("username") String username, @Field("password") String password);

}
