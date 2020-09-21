package com.example.lianxi1.common;

import com.example.lianxi1.app.MyApp;

import java.io.File;

public class Constants {
    public static  final String Base_YunUrl = "https://cdwan.cn/api/";

    public static final String Base_WanUrl = "https://www.wanandroid.com/";

    //网络缓存的地址
    public static final String PATH_DATA = MyApp.app.getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/client";
}
