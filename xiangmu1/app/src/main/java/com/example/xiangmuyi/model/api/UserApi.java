package com.example.xiangmuyi.model.api;

import com.example.xiangmuyi.bean.OwnBean.LoginBean;
import com.example.xiangmuyi.bean.OwnBean.RegisterBean;
import com.example.xiangmuyi.bean.OwnBean.UserUpdateBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserApi {
    //注册
    @POST("user/register")
    @FormUrlEncoded
    Flowable<RegisterBean> getRegisterData(@Field("username") String username, @Field("password") String password);

    //登录
    @POST("user/login")
    @FormUrlEncoded
    Flowable<LoginBean> getLoginData(@Field("username") String username, @Field("password") String password);


    //更新用户信息
    @POST("user/updateinfo")
    @FormUrlEncoded
    Flowable<UserUpdateBean> updateUserInfo(@FieldMap Map<String,String> map);
}
