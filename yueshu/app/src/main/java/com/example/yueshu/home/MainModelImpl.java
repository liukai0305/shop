package com.example.yueshu.home;

import com.example.yueshu.base.BaseMedol;
import com.example.yueshu.home.contract.IMainContract;
import com.example.yueshu.net.HttpUrlConnetUtils;
import com.example.yueshu.net.ICollBack;
import com.example.yueshu.net.NetWorkFactry;
import com.example.yueshu.net.RetrofitUtils;

public class MainModelImpl extends BaseMedol implements IMainContract.IMainModel {
    @Override
    public <T> void getData(String url, ICollBack<T> collBack) {
       RetrofitUtils.getInstance().get(url,collBack);
//       HttpUrlConnetUtils.getUrlConnetUtils().get(url,collBack);
//        NetWorkFactry.getNetWorkFactry().getiNetWork().get("",collBack);
    }
}
