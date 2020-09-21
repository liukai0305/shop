package com.qutu.talk.utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.jess.arms.utils.LogUtils;
import com.qutu.talk.base.UserManager;
import com.vector.update_app.HttpManager;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkGoUpdateHttpUtil implements HttpManager {

    /**
     * 异步get
     *
     * @param url      get请求地址
     * @param params   get参数
     * @param callBack 回调
     */
    @Override
    public void asyncGet(@NonNull String url, @NonNull Map<String, String> params, @NonNull final Callback callBack) {


        OkHttpUtils.get()
                .url(url)
                .params(params)
                .build()
                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Response response, Exception e, int id) {
//                        callBack.onError(validateError(e, response));
//                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onError("异常");
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.debugInfo("版本升级==",response.toString());
                        callBack.onResponse(response);
                    }
                });

//        StringBuilder builder = new StringBuilder();
//
//        OkHttpClient client=new OkHttpClient();
//
//        if(params.size()>0){
//            for(String key:params.keySet()){
//                builder.append(key);
//                builder.append("=");
//                builder.append(params.get(key));
//                builder.append("&&");
//            }
//            builder = builder.deleteCharAt(builder.length() - 1);
//        }
//
//        String totalUrl = url;
//        if(!TextUtils.isEmpty(builder.toString())){
//            totalUrl= totalUrl+"?"+builder.toString();
//        }
//
//        Request request=new Request
//                .Builder()
//                .url(totalUrl)
//                .build();
//
//        Call call=client.newCall(request);
//
//        call.enqueue(new okhttp3.Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                callBack.onError("异常");
//            }
//
//            @Override
//            public void onResponse(Call call, okhttp3.Response response) throws IOException {
//                String res=response.body().string();
//                LogUtils.e("response===========>",res);
//                callBack.onResponse(res);
//            }
//        });

    }

    /**
     * 异步post
     *
     * @param url      post请求地址
     * @param params   post请求参数
     * @param callBack 回调
     */
    @Override
    public void asyncPost(@NonNull String url, @NonNull Map<String, String> params, @NonNull final Callback callBack) {


        OkHttpUtils.post()
                .url(url)
                .params(params)
                .addHeader("token", UserManager.getToken())
                .build()
                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Response response, Exception e, int id) {
//                        callBack.onError(validateError(e, response));
//                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onError("异常");
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        callBack.onResponse(response);
                    }
                });

//        FormBody.Builder builder=new FormBody.Builder();
//
//        if(params.size()>0){
//            for(String key:params.keySet()){
//                builder.add(key, params.get(key));
//            }
//        }
//
//        OkHttpClient client=new OkHttpClient();
//
//        Request request=new Request
//                .Builder()
//                .url(url)
//                .post(builder.build())
//                .build();
//        Call call=client.newCall(request);
//
//        call.enqueue(new okhttp3.Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                callBack.onError("异常");
//            }
//
//            @Override
//            public void onResponse(Call call, okhttp3.Response response) throws IOException {
//                String res=response.body().string();
//                LogUtils.e("response===========>",res);
//                callBack.onResponse(res);
//            }
//        });

    }

    /**
     * 下载
     *
     * @param url      下载地址
     * @param path     文件保存路径
     * @param fileName 文件名称
     * @param callback 回调
     */
    @Override
    public void download(@NonNull String url, @NonNull String path, @NonNull String fileName, @NonNull final FileCallback callback) {
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new FileCallBack(path, fileName) {
                    @Override
                    public void inProgress(float progress, long total, int id) {
                        callback.onProgress(progress, total);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.onError("请求失败");
                        e.printStackTrace();
                    }


                    @Override
                    public void onResponse(File response, int id) {
                        callback.onResponse(response);

                    }

                    @Override
                    public void onBefore(Request request, int id) {
                        super.onBefore(request, id);
                        callback.onBefore();
                    }
                });
    }


}
