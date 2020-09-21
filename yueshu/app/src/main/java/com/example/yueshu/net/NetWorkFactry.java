package com.example.yueshu.net;

public class NetWorkFactry {
    public static volatile NetWorkFactry netWorkFactry;

    public static NetWorkFactry getNetWorkFactry() {
        if (netWorkFactry==null){
            synchronized (RetrofitUtils.class){
                if (netWorkFactry==null){
                    netWorkFactry=new NetWorkFactry();
                }
            }
        }
        return netWorkFactry;
    }
    private int NET_TYPE=0;
    private INetWork iNetWork;

    public INetWork getiNetWork() {
        if (NET_TYPE==0){
            iNetWork=RetrofitUtils.getInstance();
        }else {
            iNetWork=HttpUrlConnetUtils.getUrlConnetUtils();
        }
        return iNetWork;
    }
}
