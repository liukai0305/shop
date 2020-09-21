package com.example.xiangmuyi.common;


import com.example.xiangmuyi.app.MyApp;
import com.example.xiangmuyi.bean.discoverbean.DiscoverNavigationBean;
import com.example.xiangmuyi.bean.homebean.HomeRecommendBean;
import com.example.xiangmuyi.bean.homebean.HomeSquareBean;
import com.example.xiangmuyi.bean.homebean.PersonalPostBean;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Constants {

    public static final String Base_TPUrl = "http://cdwan.cn:7000/tongpao/";
    public static final String Base_UserUrl = "http://cdwan.cn:9001/";

    public static final String Base_UploadUrl = "http://yun918.cn/study/public/";  //资源上传的基础地址


    //网络缓存的地址
    public static final String PATH_DATA = MyApp.app.getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/tongpao";

    public static HomeRecommendBean.DataBean.PostDetailBean curClickPost;

    public static List<HomeRecommendBean.DataBean.PostDetailBean.ImagesBean> IMG_DATA = new ArrayList<>();
    public static int INDEX=-1;

    public static List<PersonalPostBean.DataBean.ActivitysBean> activitysBean;

    public static List<HomeSquareBean.DataBean.DynamicsBean.ImagesBean> Square = new ArrayList<>();
    public static int square=-1;
    public static DiscoverNavigationBean.DataBean DATA_SUCCESS;

}