package com.example.yueshu.net;



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
    Observable<ResponseBody> getHttp(@Url String str);

    @POST
    @FormUrlEncoded
    Observable<ResponseBody>PostHttp(@Url String str);

    @POST
    @FormUrlEncoded
    Observable<ResponseBody> postHttp(@Url String url, @FieldMap HashMap<String,String>hashMap);
}
