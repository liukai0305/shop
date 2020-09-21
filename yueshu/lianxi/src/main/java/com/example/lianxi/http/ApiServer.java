package com.example.lianxi.http;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiServer {
    @GET
    Observable<ResponseBody> getHttp(@Url String url);

    @POST
    @FormUrlEncoded
    Observable<ResponseBody> postHttp(@Url String Url);

    @POST
    @FormUrlEncoded
    Observable<ResponseBody> postHttp(@Url String Url, @FieldMap HashMap<String ,String>hashMap);
}
