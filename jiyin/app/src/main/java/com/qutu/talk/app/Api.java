/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qutu.talk.app;

import com.qutu.talk.BuildConfig;

/**
 * ================================================
 * 存放一些与 API 有关的东西,如请求地址,请求码等
 * <p>
 * Created by JessYan on 08/05/2016 11:25
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public interface Api {

    public static boolean IS_DEBUG = BuildConfig.DEBUG;
//
//    public static String APP_DOMAIN = "http://peiwan.zzmzrj.com/api/";
//    public static String APP_DOMAIN = "http://app.26kl.com/api/";
    public static String APP_DOMAIN = "http://jiyinapp.cn/api/";
    public static String RONG_YUN_KEY =  "3argexb63sybe"  ;//融云key


    // FIXME: 2020/7/1
//    public static String AGORA_KEY =  "f30932e392b5488db11bcfb90d1942e6" ;//声网key
    public static String AGORA_KEY =  "357f59db740141568476034ddaf94bed" ;//声网key
    public static String YOUMENG_KEY =  "5e15961fcb23d242ec0000ce" ; //友盟key
    public static String YOUMENG_SECRECT ="c2nbxta8lt4rdcngkhtm0dubj2mzyafc" ; //友盟Secrect

//    public static String APP_DOMAIN = "http://tp5_erqi.miniyuyin.cn/api/";//正式
//
//    public static String RONG_YUN_KEY = "z3v5yqkbzi650";//融云key
//    public static String AGORA_KEY = "0b48c2c2ee5c4fd9910234af6c78fd9c";//声网key


//    static {
//        switch (API_MODE) {
//            case 0://生产环境
//                IS_DEBUG = false;
//                APP_DOMAIN="http://tp5_erqi.miniyuyin.cn/api/";//正式
//                RONG_YUN_KEY = "z3v5yqkbzi650";
//                AGORA_KEY = "0b48c2c2ee5c4fd9910234af6c78fd9c";//声网key
//                break;
//            case 1://测试环境
//                IS_DEBUG = true;
//                APP_DOMAIN="http://tp5_test.miniyuyin.cn/api/";//测试
//                RONG_YUN_KEY = "z3v5yqkbzi650";
//                AGORA_KEY = "b3bbbd8c41ad4a6bb9db0313ee2c5334";//声网key
//                break;
//        }
//    }


//        public String CHANNEL = "guanfang";
//    String CHANNEL = "lianxiang";//联想
//    String CHANNEL = "meizu";//魅族
//    String CHANNEL = "sanxing";//三星
//    String CHANNEL = "360";//360
//    String CHANNEL = "huawei";//华为应用商店
//    String CHANNEL = "xiaomi";//小米应用市场
//    String CHANNEL = "oppo";//oppo
//    String CHANNEL = "vivo";//vivo
//    String CHANNEL = "ali";//阿里
//    String CHANNEL = "baidu";//百度手机助手
//    String CHANNEL = "yingyongbao";//应用宝
//    String CHANNEL = "sougou";//搜狗手机助手
//    String CHANNEL = "qita";//其他
//    String CHANNEL = "landie01";//蓝蝶
//    String CHANNEL = "landie02";//蓝蝶
//    String CHANNEL = "landie03";//蓝蝶
//    String CHANNEL = "landie04";//蓝蝶
//    String CHANNEL = "landie05";//蓝蝶
    String CHANNEL = "reyun";//热云


    String RequestSuccess = "0";
}
