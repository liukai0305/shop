package com.example.zy.model;

import com.example.zy.base.BaseModel;
import com.example.zy.http.RetrofitUtils;
import com.example.zy.net.IHttpCallBack;
import com.example.zy.net.IMainContract;

public class HomeModelImpl extends BaseModel implements IMainContract.IFragmentModel {
    @Override
    public <T> void getData(String url, IHttpCallBack<T> iHttpCallBack) {
        RetrofitUtils.getRetrofitUtils().get(url,iHttpCallBack);
    }
}
