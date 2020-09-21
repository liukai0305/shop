package com.example.tongpao.common;



import com.example.tongpao.app.MyApp;
import com.example.tongpao.bean.HomeRecommendBean;

import java.io.File;
import java.util.List;

public class Constants {
    public static final String Base_YunUrl = "http://cdwan.cn:7000/tongpao/";
    //网络缓存的地址
    public static final String PATH_DATA = MyApp.app.getCacheDir().getAbsolutePath()+ File.separator+"data";

    public static final String PATH_CACHE = PATH_DATA +"/project";

    public static HomeRecommendBean.DataBean.PostDetailBean curClickPost;

    public static HomeRecommendBean.DataBean.CommentsBean commentsBean;

    public static List<HomeRecommendBean.DataBean.PostDetailBean.ImagesBean> imagesBean;
}
