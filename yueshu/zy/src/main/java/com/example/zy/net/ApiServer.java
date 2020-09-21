package com.example.zy.net;

import io.reactivex.Observable;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiServer {
    @GET
    Observable<ResponseBody> get(@Url String url );
}
