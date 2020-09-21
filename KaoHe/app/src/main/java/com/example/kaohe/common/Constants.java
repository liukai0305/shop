package com.example.kaohe.common;



import com.example.kaohe.app.MyApp;

import java.io.File;

public class Constants {

    public static final String Base_TPUrl = "http://cdwan.cn:7000/exam/";


    //网络缓存的地址
    public static final String PATH_DATA = MyApp.app.getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/exam";

    public static int Window_W;


    /**
     * 当前点击的推荐数据
     */

}