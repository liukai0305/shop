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

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.HttpResponseCache;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.mobstat.StatService;
import com.blankj.utilcode.util.Utils;
import com.jess.arms.base.BaseApplication;
import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.integration.cache.IntelligentCache;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.lzx.starrysky.manager.MusicManager;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.qutu.talk.BuildConfig;
import com.qutu.talk.activity.MainActivity;
import com.qutu.talk.activity.message.LiaoBaExtensionModule;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.BaseBean;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.LoginData;
import com.qutu.talk.bean.PushBean;
import com.qutu.talk.utils.FileHelpers;
import com.qutu.talk.utils.GlideImageLoader;
import com.qutu.talk.utils.MyUtil;
import com.qutu.talk.utils.SharedPreferencesUtils;
import com.pgyersdk.crash.PgyCrashManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.PlatformConfig;

import org.greenrobot.eventbus.EventBus;
import org.litepal.LitePal;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.TextMessageItemProvider;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import io.rong.message.TextMessage;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import timber.log.Timber;

import static com.qutu.talk.app.Api.YOUMENG_KEY;
import static com.qutu.talk.app.Api.YOUMENG_SECRECT;
import static com.qutu.talk.utils.Constant.KBXTUISONG;
import static com.qutu.talk.utils.Constant.TUISONG;

/**
 * ================================================
 * 展示 {@link AppLifecycles} 的用法
 * <p>
 * Created by JessYan on 04/09/2017 17:12
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class AppLifecyclesImpl implements AppLifecycles {

    @Override
    public void attachBaseContext(@NonNull Context base) {
//          MultiDex.install(base);  //这里比 onCreate 先执行,常用于 MultiDex 初始化,插件化框架的初始化
    }

    @Override
    public void onCreate(@NonNull Application application) {
        if (LeakCanary.isInAnalyzerProcess(application)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        if (Api.IS_DEBUG) {//Timber初始化
            //Timber 是一个日志框架容器,外部使用统一的Api,内部可以动态的切换成任何日志框架(打印策略)进行日志打印
            //并且支持添加多个日志框架(打印策略),做到外部调用一次 Api,内部却可以做到同时使用多个策略
            //比如添加三个策略,一个打印日志,一个将日志保存本地,一个将日志上传服务器
            Timber.plant(new Timber.DebugTree());
            // 如果你想将框架切换为 Logger 来打印日志,请使用下面的代码,如想切换为其他日志框架请根据下面的方式扩展
//                    Logger.addLogAdapter(new AndroidLogAdapter());
//                    Timber.plant(new Timber.DebugTree() {
//                        @Override
//                        protected void log(int priority, String tag, String message, Throwable t) {
//                            Logger.log(priority, tag, message, t);
//                        }
//                    });
            ButterKnife.setDebug(true);
        }
        Timber.d("AppLifecyclesImpl init ");
        //LeakCanary 内存泄露检查
        //使用 IntelligentCache.KEY_KEEP 作为 key 的前缀, 可以使储存的数据永久存储在内存中
        //否则存储在 LRU 算法的存储空间中, 前提是 extras 使用的是 IntelligentCache (框架默认使用)
        ArmsUtils.obtainAppComponentFromContext(application).extras()
                .put(IntelligentCache.getKeyOfKeep(RefWatcher.class.getName())
                        , BuildConfig.USE_CANARY ? LeakCanary.install(application) : RefWatcher.DISABLED);
        //设置全局的下拉刷新
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> new ClassicsHeader(context));
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> new ClassicsFooter(context));
        //图片选择
        initImagePicker();
        //数据库初始化
        LitePal.initialize(application);
//        if (!Api.IS_DEBUG) {
        CrashReport.initCrashReport(application, "029cd8f5db", Api.IS_DEBUG);
        try {
            CrashReport.setUserId(UserManager.getUser().getPhone());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        }
        //音乐库初始化
        MusicManager.initMusicManager(application);
        //读取本地用户信息
        UserManager.initData();
        //融云
        RongIM.init(application, Api.RONG_YUN_KEY);//正式环境的key
//        RongIM.init(application, "z3v5yqkbzi650");//测式环境的key
//        RongIM.init(application, "z3v5yqkbzi650");//测试环境的key


//        LoginData loginData = UserManager.getUser();
//
//        if(!TextUtils.isEmpty(loginData.getRy_token())){
//
//            RongIM.connect(loginData.getRy_token(), new RongIMClient.ConnectCallback() {
//                @Override
//                public void onTokenIncorrect() {
//                    LogUtils.debugInfo("TAG", "0");
//                }
//
//                @Override
//                public void onSuccess(String userid) {
//                    LogUtils.debugInfo("TAG", "--onSuccess" + userid);
//                    //设置用户消息
//                    UserInfo user = new UserInfo(userid,
//                            loginData.getNickname(), Uri.parse(loginData.getHeadimgurl()));
//                    RongIM.getInstance().setCurrentUserInfo(user);
//                    RongIM.getInstance().setMessageAttachedUserInfo(true);
//
//                    registerExtensionPlugin();
//
//                    RongIM.registerMessageTemplate(new MyTextMessageItemProvider());
//                }
//
//                @Override
//                public void onError(RongIMClient.ErrorCode errorCode) {
//                    LogUtils.debugInfo("TAG", "--onError" + errorCode);
//                }
//            });
//        }


        //分享
        initShare(application);

        //蒲公英
//        PgyCrashManager.register(application);  // 弃用
        PgyCrashManager.register(); //推荐使用,蒲公英
        //上报异常
        try {
            // code
        } catch (Exception e) {
            /** 新版本 **/
            PgyCrashManager.reportCaughtException(e);
        }
        //百度统计
//        StatService.autoTrace(application);
        if (!Api.IS_DEBUG) {
            StatService.start(application);
            StatService.setDebugOn(Api.IS_DEBUG);
        }

        //svga缓存
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        File cacheDir = FileHelpers.getFileByPath(path + "/svga_file");
        try {
            HttpResponseCache.install(cacheDir, 1024 * 1024 * 128);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Utils.init(application);
        // 获取测试设备ID
//        String testDeviceId = StatService.getTestDeviceId(application);
//// 日志输出
//        android.util.Log.d("BaiduMobStat", "Test DeviceId : " + testDeviceId);
    }

    private void initShare(Application application) {
        UMConfigure.init(application, Api.YOUMENG_KEY
                , "", UMConfigure.DEVICE_TYPE_PHONE, YOUMENG_SECRECT);
        //获取消息推送代理示例
        PushAgent mPushAgent = PushAgent.getInstance(application);

        mPushAgent.setNotificaitonOnForeground(true);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                LogUtils.debugInfo("sgm", "====注册成功：deviceToken：-------->  " + deviceToken);
                SharedPreferencesUtils.setParam(BaseApplication.mApplication, "deviceToken", deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                LogUtils.debugInfo("sgm", "====注册失败：-------->  " + "s:" + s + ",s1:" + s1);
            }
        });

//        AppID: 分享：wx59b8d8736a11236f 支付：wxd8441197c38a74f9
        PlatformConfig.setWeixin("wx59b8d8736a11236f", "748723241c46893bd7b9d470ee48433b");
        PlatformConfig.setQQZone("101844589", "eb92f27b0fb7c317961a6c6e5442abe6");
        PlatformConfig.setSinaWeibo("550865829", "70e69881edd8a44cc99f7e83b34de1b6", "http://qutu.zzmzrj.com");


        // TODO: 2020/7/9 待调整 xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        /**
         * 应用下载地址：http://fir.zzmzrj.com/tvc2
         *
         * 积音微信移动应用
         * AppID: wx59b8d8736a11236f
         * AppSecret: f68f96559aca5a85dd7d7d34f3ccfa9f --748723241c46893bd7b9d470ee48433b
         * 包名：com.quanjing.jiyin
         * 签名：CD8CB12272345F153CC90BC26CE25D19
         *
         * 微信支付参数：
         * 商户ID：1495754782
         * 支付密钥：3A8BAD98301176A942FDA56345B5203C
         * AppID：wxd8441197c38a74f9
         * AppSecret：37903b9dd9ce524a51011996607c3bdb
         */


//        mPushAgent.setMessageHandler(messageHandler);
    }

//    UmengMessageHandler messageHandler = new UmengMessageHandler() {
//
//        @Override
//        public Notification getNotification(Context context, UMessage msg) {
//            Map<String, String> extra = msg.extra;
//            String test = extra.get("data");
//            LogUtils.debugInfo("====消息：" + extra.toString());
//            if (!TextUtils.isEmpty(test)) {
//
//                try {
//                    JSONObject userJson = JSONObject.parseObject(test);
//                    PushBean pushBean = JSON.toJavaObject(userJson, PushBean.class);
//                    if (TextUtils.equals(pushBean.type, "gift")) {
//                        EventBus.getDefault().post(new FirstEvent(pushBean, TUISONG));
//                    } else if (TextUtils.equals(pushBean.type, "award")) {
//                        EventBus.getDefault().post(new FirstEvent(pushBean, KBXTUISONG));
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            return super.getNotification(context, msg);
//        }
//    };

    @Override
    public void onTerminate(@NonNull Application application) {

    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    /**
     * 删除扩展区域
     */
    private void registerExtensionPlugin() {
        List<IExtensionModule> moduleList = RongExtensionManager.getInstance().getExtensionModules();
        IExtensionModule defaultModule = null;
        if (moduleList != null) {
            for (IExtensionModule module : moduleList) {
                if (module instanceof DefaultExtensionModule) {
                    defaultModule = module;
                    break;
                }
            }
            if (defaultModule != null) {
                RongExtensionManager.getInstance().unregisterExtensionModule(defaultModule);
                RongExtensionManager.getInstance().registerExtensionModule(new LiaoBaExtensionModule());
            }
        }
    }

    /**
     * 聊天字体颜色
     */
    @ProviderTag(messageContent = TextMessage.class, showReadState = true)
    public class MyTextMessageItemProvider extends TextMessageItemProvider {

        @Override
        public void bindView(View v, int position, TextMessage content, UIMessage data) {
            super.bindView(v, position, content, data);
            TextView textView = (TextView) v;
            if (data.getMessageDirection() == Message.MessageDirection.SEND) {
                textView.setTextColor(Color.WHITE);
            } else {
                textView.setTextColor(Color.BLACK);
                UIMessage userName = data;
//                LogUtils.debugInfo("用户名称："+userName);
            }
        }
    }
}
