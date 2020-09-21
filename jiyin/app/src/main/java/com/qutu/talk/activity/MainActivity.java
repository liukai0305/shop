package com.qutu.talk.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DeviceUtils;
import com.jess.arms.utils.LogUtils;
import com.orient.tea.barragephoto.adapter.AdapterListener;
import com.orient.tea.barragephoto.adapter.BarrageAdapter;
import com.orient.tea.barragephoto.ui.BarrageView;
import com.qutu.talk.R;
import com.qutu.talk.activity.dynamic.SocialReleaseActivity;
import com.qutu.talk.activity.login.LoginActivity;
import com.qutu.talk.activity.message.LiaoBaExtensionModule;
import com.qutu.talk.activity.message.MessageFragmentActivity;
import com.qutu.talk.activity.room.AdminHomeActivity;
import com.qutu.talk.adapter.DanMuViewHolder;
import com.qutu.talk.app.Api;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.app.view.CircularImage;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.BaseBean;
import com.qutu.talk.bean.EnterGroupMessageItemProvider;
import com.qutu.talk.bean.EnterRoom;
import com.qutu.talk.bean.FamilyDetail;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.GetFamilyDetailResult;
import com.qutu.talk.bean.LoginData;
import com.qutu.talk.bean.PushBean;
import com.qutu.talk.bean.RequestEnterGroupMessage;
import com.qutu.talk.bean.UpdateVersion;
import com.qutu.talk.bean.UserBean;
import com.qutu.talk.bean.task.SignInBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.floatingview.EnFloatingView;
import com.qutu.talk.floatingview.FloatingMagnetView;
import com.qutu.talk.floatingview.FloatingView;
import com.qutu.talk.floatingview.MagnetViewListener;
import com.qutu.talk.fragment.MainCenterFragment;
import com.qutu.talk.fragment.MainCommunityFragment;
import com.qutu.talk.fragment.MainHomeFragment;
import com.qutu.talk.fragment.MainMessageFragment;
import com.qutu.talk.fragment.MainPeiwanPageFragment;
import com.qutu.talk.fragment.MainYulePageFragment;
import com.qutu.talk.popup.SignInWindow;
import com.qutu.talk.popup.TodaySignWindow;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.ActivityUtils;
import com.qutu.talk.utils.Arith;
import com.qutu.talk.utils.EncodeUtils;
import com.qutu.talk.utils.FastJsonUtils;
import com.qutu.talk.utils.MyUtils;
import com.qutu.talk.utils.OkGoUpdateHttpUtil;
import com.qutu.talk.utils.SharedPreferencesUtils;
import com.qutu.talk.view.MiniBarrageViewLayout;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.TextMessageItemProvider;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import io.rong.message.TextMessage;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.qutu.talk.utils.Constant.FANHUIZHUYE;
import static com.qutu.talk.utils.Constant.KBXTUISONG;
import static com.qutu.talk.utils.Constant.LOGOUT;
import static com.qutu.talk.utils.Constant.TUISONG;
import static com.qutu.talk.utils.Constant.XUANFUYINCANG;

/**
 * implements RadioGroup.OnCheckedChangeListener
 */

public class MainActivity extends MyBaseArmActivity implements IUnReadMessageObserver {


    @BindView(R.id.frameLayout_main)
    FrameLayout frameLayoutMain;

    @BindView(R.id.barrage)
    BarrageView barrageView;
    @BindView(R.id.mini_bv_layout)
    MiniBarrageViewLayout mMiniBarrageViewLayout;
    @BindView(R.id.radio_yule)
    RadioButton radioYule;
    @BindView(R.id.radio_peiwan)
    RadioButton radioPeiwan;
    @BindView(R.id.radio_dongtai)
    RadioButton radioDongtai;
    @BindView(R.id.radio_center)
    RadioButton radioCenter;
    @BindView(R.id.radio_home_page)
    RadioButton radioHomePage;
    @BindView(R.id.float_button)
    View float_button;


    private CircularImage imgHeader;
    private ImageView img1, img2;
    private TodaySignWindow todaySignWindow;
    @Inject
    CommonModel commonModel;
    MainYulePageFragment mainYuleFragment = new MainYulePageFragment();
    MainPeiwanPageFragment mainPeiwanFragment = new MainPeiwanPageFragment();
    MainHomeFragment mainHomeFragment = new MainHomeFragment();
    //    MainFindFragment mainFindFragment = new MainFindFragment();
    MainCommunityFragment mainCommunityFragment = new MainCommunityFragment();
    //        MainMessageFragment mainMessageFragment = new MainMessageFragment();
    MainCenterFragment mainCenterFragment = new MainCenterFragment();

    private BarrageAdapter<PushBean> mAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @SuppressLint("CheckResult")
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
//        mRadioGroup.setOnCheckedChangeListener(this);
//        mRadioGroup.check(R.id.radio_home);
        RxUtils.loading(commonModel.getVersion(), this)
                .subscribe(new ErrorHandleSubscriber<UpdateVersion>(mErrorHandler) {
                    @Override
                    public void onNext(UpdateVersion updateVersion) {
                        if (!updateVersion.getData().getAndroidbanben().equals(MyUtils.INSTANCE.packageName(MainActivity.this))) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("提示");
                            builder.setMessage("发现新版本，是否更新");
                            builder.setPositiveButton("立即更新", (dialog, which) -> {
                                Intent intent = new Intent();
                                intent.setAction("android.intent.action.VIEW");
                                Uri contentUrl = Uri.parse(updateVersion.getData().getAndroidhef());
                                intent.setData(contentUrl);
                                startActivity(intent);
                                finish();
                            });
                            builder.setCancelable(false);
                            builder.show();
                        }
                    }
                });
        radioHomePage.setChecked(true);
        ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(),
                mainHomeFragment, R.id.frameLayout_main);
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        initUpdate();
                    } else {
                        showToast("请打开存储权限！");
                    }
                });

        //初始化弹幕
        initDanmu();

        LoginData loginData = UserManager.getUser();

        if (!TextUtils.isEmpty(loginData.getRy_token())) {

            RongIM.connect(loginData.getRy_token(), new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {
                    LogUtils.debugInfo("TAG", "0");
                }

                @Override
                public void onSuccess(String userid) {
                    LogUtils.debugInfo("TAG_RONGIM_CONNECT", "--onSuccess" + userid);

                    registerExtensionPlugin();

                    RongIM.registerMessageTemplate(new MyTextMessageItemProvider());

                    //设置自定义消息
                    RongIM.registerMessageType(RequestEnterGroupMessage.class);
                    //设置自定义消息view
                    RongIM.getInstance().registerMessageTemplate(new EnterGroupMessageItemProvider());

//                    RongIM.setOnReceiveMessageListener(new RongIMClient.OnReceiveMessageListener() {
//                        @Override
//                        public boolean onReceived(Message message, int i) {
//                            MessageContent content =  message.getContent();
//                            return false;
//                        }
//                    });

                    //        //连接成功以后设置用户消息
                    RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
                        @Override
                        public UserInfo getUserInfo(String s) {
                            //            LogUtils.debugInfo("sgm","====走到设置头像" + userId);
                            LogUtils.debugInfo("====走到设置头像", s);
                            return findUserId(s);//根据 userId 去你的用户系统里查询对应的用户信息返回给融云 SDK。
                        }
                    }, true);

                    //群聊信息
                    RongIM.setGroupInfoProvider(new RongIM.GroupInfoProvider() {
                        @Override
                        public Group getGroupInfo(String s) {
                            LogUtils.debugInfo("====群聊-走到设置头像", s);
//                            return new Group(s,"群聊",Uri.parse("http://rongcloud-web.qiniudn.com/docs_demo_rongcloud_logo.png"));
                            return getGroupInfoDetail(s);
                        }
                    }, true);
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    LogUtils.debugInfo("TAG", "--onError" + errorCode);
                }
            });
        }


//        //设置聊天用户信息

        if (UserManager.IS_LOGIN && TextUtils.isEmpty(UserManager.getUser().getToken())) {//登录状态，token为空，重新登录
            UserManager.layout();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        joinPublicGroup();

        //跳转消息页面
//        radioMessage.setOnClickListener(v -> {
//            LogUtils.debugInfo("sgm", "====点击消息");
////            radioMessage.setChecked(true);
//            radioMessageOne.setFocusableInTouchMode(false);
//            radioHome.setChecked(false);
//            radioShequ.setChecked(false);
//            radioFinder.setChecked(false);
//            radioCenter.setChecked(false);
//            EventBus.getDefault().post(new FirstEvent("指定发送", Constant.SHUAXINPENGYOU));
//            ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(),
//                    mainMessageFragment, R.id.frameLayout_main);
//        });
        getUnreadCount();
//        getGuanFang();
//        getOtherUserTwo();
//        mCountTimeUtils.start();
//        if (!TextUtils.isEmpty(loginData.getToken())){
//            RxUtils.loading(commonModel.go_binding_device(MyUtil.getAndroidID(MainActivity.this),loginData.getToken()),this)
//                    .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
//                        @Override
//                        public void onNext(BaseBean baseBean) {
////                            toast(baseBean.getMessage());
//                            LogUtils.debugInfo("====Android唯一ID2",MyUtil.getAndroidID(MainActivity.this));
//                        }
//                    });
//        }
        String deviceToken = (String) SharedPreferencesUtils.getParam(this, "deviceToken", "");

        if (!TextUtils.isEmpty(deviceToken) && UserManager.IS_LOGIN) {
            LogUtils.debugInfo("====Android唯一ID2", deviceToken);
            RxUtils.loading(commonModel.go_binding_device(deviceToken, UserManager.getUser().getToken()), this)
                    .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                        @Override
                        public void onNext(BaseBean baseBean) {
                            LogUtils.debugInfo("====Android唯一ID1", deviceToken);
                        }

                        @Override
                        public void onError(Throwable t) {
                            super.onError(t);
                            LogUtils.debugInfo("====Android唯一ID3", t.getMessage());
                        }
                    });
        }


        openApp();
    }

    private UserManager userManager;

//    List<PushBean> mPushBeanList = new Vector<>();
//    private UserBean mUserBean;
//
//    /**
//     * 用户信息
//     */
//    private void loadUserData() {
//        RxUtils.loading(commonModel.get_user_info(String.valueOf(UserManager.getUser().getUserId())), this)
//                .subscribe(new ErrorHandleSubscriber<UserBean>(mErrorHandler) {
//                    @Override
//                    public void onNext(UserBean userBean) {
//                        mUserBean = userBean;
////                        getMyFamily();
//                    }
//                });
//    }
//    private void getMyFamily() {
//        RxUtils.loading(commonModel.getMyFamily(String.valueOf(UserManager.getUser().getUserId())), this)
//                .subscribe(new ErrorHandleSubscriber<MyFamily>(mErrorHandler) {
//                    @Override
//                    public void onNext(MyFamily myFamily) {
//                        if (TextUtils.isEmpty(myFamily.getData().getJzid())) {
//                            FamilyListFragment familyListFragment = new FamilyListFragment();
//                            Bundle bundle = new Bundle();
//                            bundle.putInt("is_family_show", mUserBean.getData().getIs_family_show());
//                            bundle.putString("is_god", mUserBean.getData().getIs_god());
//                            bundle.putBoolean("show_back", false);
//                            familyListFragment.setArguments(bundle);
//                            familyFragment = familyListFragment;
//
//                        } else {
//                            FamilyDetailsFragment familyDetailsFragment = new FamilyDetailsFragment();
//                            Bundle bundle = new Bundle();
//                            bundle.putString("family_id", myFamily.getData().getJzid());
//                            bundle.putBoolean("show_back", false);
//                            familyDetailsFragment.setArguments(bundle);
//                            familyFragment = familyDetailsFragment;
//                        }
//                        if (radioFamily.isChecked()) {
//                            ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(),
//                                    familyFragment, R.id.frameLayout_main);
//                        }
//                    }
//                });
//    }

    /**
     * 用户信息
     */
    private UserInfo findUserId(String userId) {
        LogUtils.debugInfo("====进不进得来");

        UserInfo userInfo;

        String userListStr = (String) SharedPreferencesUtils.getParam(MainActivity.this, "rim_info", "");

        Map<String, Object> userMap = new HashMap<>();

        if (!TextUtils.isEmpty(userListStr)) {//本地有用户数据

            userListStr = new String(EncodeUtils.base64Decode(userListStr));//先解码

            LogUtils.debugInfo("====查询到所有聊天用户信息" + userListStr);

            userMap = FastJsonUtils.json2Map(userListStr);

            int m = 0;

            for (String key : userMap.keySet()) {

                if (TextUtils.equals(userId, key)) {

                    String userJson = (String) userMap.get(key);

                    if (!TextUtils.isEmpty(userJson)) {
                        m = 2;

                        UserBean userBean = JSON.parseObject(userJson, UserBean.class);

                        userInfo = new UserInfo(userBean.getData().getId() + "",
                                userBean.getData().getNickname(),
                                Uri.parse(userBean.getData().getHeadimgurl()));

                        LogUtils.debugInfo("====查询到当前聊天用户信息" + userJson);

                        return userInfo;

                    }
                    break;
                }
            }

            if (m == 0) {//没有查询到
                getOtherUser(userId, userMap);
            }

        } else {
            getOtherUser(userId, userMap);
        }


//        RongIM.getInstance().refreshUserInfoCache(new UserInfo(userId.getUserId() + "",
//                userId.getNickname(),
//                Uri.parse(userId.getHeadimgurl())));
        return null;
    }

    /**
     * 加入公聊
     */
    private void joinPublicGroup() {
        RxUtils.loading(commonModel.joinPublicGroup(UserManager.getUser().getUserId() + ""), this)
                .subscribe(new ErrorHandleSubscriber<String>(mErrorHandler) {
                    @Override
                    public void onNext(String userBean) {

                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);

                    }
                });
    }

    private Group getGroupInfoDetail(String familyId) {
        RxUtils.loading(commonModel.getFamilyDetail(familyId), this)
                .subscribe(new ErrorHandleSubscriber<GetFamilyDetailResult>(mErrorHandler) {
                    @Override
                    public void onNext(GetFamilyDetailResult userBean) {


                        FamilyDetail familyDetail = userBean.getData();
                        if (familyDetail != null) {
                            Log.e("获取群组信息了======", userBean.getData().getName());

                            RongIM.getInstance().refreshGroupInfoCache(new Group(familyDetail.getFamily_id(), familyDetail.getName(), Uri.parse(familyDetail.getImage())));
                        }

                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Log.e("获取群组信息出错了======", "0000");
                    }
                });
        return null;
    }

    private void getOtherUser(String userId, Map<String, Object> userMap) {

        RxUtils.loading(commonModel.get_user_info(userId), this)
                .subscribe(new ErrorHandleSubscriber<UserBean>(mErrorHandler) {
                    @Override
                    public void onNext(UserBean userBean) {

                        Log.e("获取用户信息了======", userBean.getData().getNickname());
                        UserInfo userInfo = new UserInfo(userBean.getData().getId() + "",
                                userBean.getData().getNickname(),
                                Uri.parse(userBean.getData().getHeadimgurl()));

                        String userStrs = JSON.toJSONString(userBean);//用户信息

                        userMap.put(userId, userStrs);

                        String mapsJsonStr = FastJsonUtils.map2Json(userMap);

                        String baseStr = EncodeUtils.base64Encode2String(mapsJsonStr.getBytes());//转码

                        LogUtils.debugInfo("聊天用户信息" + mapsJsonStr);

                        SharedPreferencesUtils.setParam(MainActivity.this, "rim_info", baseStr);

                        RongIM.getInstance().refreshUserInfoCache(userInfo);

                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Log.e("获取用户信息出错了======", "0000");
                    }
                });
    }

    /**
     * 每日第一次打开应用
     */
    private void openApp() {
//        RxUtils.loading(commonModel.is_open_today(), this)
//                .subscribe(new ErrorHandleSubscriber<SignInDisplayBean>(mErrorHandler) {
//                    @Override
//                    public void onNext(SignInDisplayBean signInDisplayBean) {
//                        todaySignWindow = new TodaySignWindow(MainActivity.this, commonModel, mErrorHandler, signInDisplayBean);
//                        todaySignWindow.showAtLocation(frameLayoutMain, Gravity.CENTER, 0, 0);
//                        todaySignWindow.getSure().setOnClickListener(v -> {
//                            if(signInDisplayBean.getData().getIs_sign() ==0){
//                                setSign();
//                            }
//                        });
//                        todaySignWindow.getColseBtn().setOnClickListener(v -> {
//                            todaySignWindow.dismiss();
//                        });
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                    }
//                });
    }

    //签到
    private void setSign() {
        RxUtils.loading(commonModel.sign(), this)
                .subscribe(new ErrorHandleSubscriber<SignInBean>(mErrorHandler) {
                    @Override
                    public void onNext(SignInBean signInBean) {
                        todaySignWindow.dismiss();
                        SignInWindow signInWindow = new SignInWindow(MainActivity.this, signInBean);
                        signInWindow.showAtLocation(frameLayoutMain, Gravity.CENTER, 0, 0);
                    }
                });
    }

//    //单一个嘿嘿嘿
//    private void getOtherUserTwo() {
//
//        RxUtils.loading(commonModel.get_user_info(String.valueOf(UserManager.getUser().getUserId())), this)
//                .subscribe(new ErrorHandleSubscriber<UserBean>(mErrorHandler) {
//                    @Override
//                    public void onNext(UserBean userBean) {
//
//                        if (userBean.getData().getIs_newpd() == 1 || userBean.getData().getIs_neworder() == 1 || userBean.getData().getIs_newpack() == 1) {
//                            unreadMyTips.setVisibility(View.VISIBLE);
//                        } else {
//                            unreadMyTips.setVisibility(View.GONE);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        super.onError(t);
//                        Log.e("获取用户信息出错了======", "0000");
//                    }
//                });
//    }

    /**
     * 弹幕
     */
    private void initDanmu() {
        BarrageView.Options options = new BarrageView.Options()
                .setGravity(BarrageView.GRAVITY_TOP) // 设置弹幕的位置
                .setInterval(600)  // 设置弹幕的发送间隔
                .setSpeed(200, 29) // 设置速度和波动值
                .setModel(BarrageView.MODEL_COLLISION_DETECTION)     // 设置弹幕生成模式
                .setRepeat(1)// 循环播放 默认为1次 -1 为无限循环
                .setClick(true);// 设置弹幕是否可以点击
        barrageView.setOptions(options);

        barrageView.setAdapter(mAdapter = new BarrageAdapter<PushBean>(null, this) {
            @Override
            public BarrageViewHolder<PushBean> onCreateViewHolder(View root, int type) {
                return new DanMuViewHolder(root, MainActivity.this);// 返回自己创建的ViewHolder
            }

            @Override
            public int getItemLayout(PushBean barrageData) {
                return R.layout.danmu;// 返回自己设置的布局文件
            }
        });

        // 设置监听器
        mAdapter.setAdapterListener(new AdapterListener<PushBean>() {
            @Override
            public void onItemClick(BarrageAdapter.BarrageViewHolder<PushBean> holder, PushBean item) {
                if (item != null) {
                    if ("gift".equals(item.type)) {
                        enterData(item.getData().getUid() + "", "", commonModel, 1, "0");
                    }
                }
            }
        });

    }

    private void sendMsg(String test, long delay) {

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject userJson = JSONObject.parseObject(test);
                            PushBean pushBean = JSON.toJavaObject(userJson, PushBean.class);
                            if (TextUtils.equals(pushBean.type, "gift")) {
                                LogUtils.debugInfo("自己发送消息====" + pushBean.toString());
                                EventBus.getDefault().post(new FirstEvent(pushBean, TUISONG));
                            } else if (TextUtils.equals(pushBean.type, "award")) {
                                LogUtils.debugInfo("自己发送消息====" + pushBean.toString());
                                EventBus.getDefault().post(new FirstEvent(pushBean, KBXTUISONG));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }.start();

    }

//    @Override
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        switch (checkedId) {
////            case R.id.radio_home:
////                radioMessage.setChecked(false);
//////                radioMessageOne.setFocusableInTouchMode(false);
////                ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(),
////                        mainHomeFragment, R.id.frameLayout_main);
////                break;
////            case R.id.radio_finder:
////                radioMessage.setChecked(false);
//////                radioMessageOne.setFocusableInTouchMode(false);
////                ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(),
////                        mainFindFragment, R.id.frameLayout_main);
////                break;
////            case R.id.radio_shequ:
////                radioMessage.setChecked(false);
//////                radioMessageOne.setFocusableInTouchMode(false);
////                ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(),
////                        mainCommunityFragment, R.id.frameLayout_main);
////                break;
////            case R.id.radio_message:
////                LogUtils.debugInfo("sgm", "====点击消息");
////                EventBus.getDefault().post(new FirstEvent("指定发送", Constant.SHUAXINPENGYOU));
////                ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(),
////                        mainMessageFragment, R.id.frameLayout_main);
////                break;
//            case R.id.radio_center:
//                radioMessage.setChecked(false);
////                radioMessageOne.setFocusableInTouchMode(false);
//                ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(),
//                        mainCenterFragment, R.id.frameLayout_main);
//                break;
//            default:
//        }
//    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //重写返回键
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (barrageView != null) {
            barrageView.destroy();
        }
//        if (mCountTimeUtils != null) {
//            mCountTimeUtils.cancel();
//        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        LogUtils.debugInfo("====onRestart");
        if (AdminHomeActivity.isStart && AdminHomeActivity.isTop) {
            startActivity(new Intent(this, AdminHomeActivity.class));
        }
    }

//    boolean mIsPushRuning = true;

//    CountTimeUtils mCountTimeUtils = new CountTimeUtils(10 * 6 * 10) {
//        @Override
//        public void onTick(long millisUntilFinished) {
//
//        }
//
//        @Override
//        public void onFinish() {
//            LogUtils.debugInfo("倒计时10完了===");
//            mIsPushRuning = false;
//        }
//    };


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {

        synchronized (this) {
            String tag = event.getTag();
            if (LOGOUT.equals(tag)) {
                finish();
            } else if (FANHUIZHUYE.equals(tag)) {//显示悬浮窗
                EnterRoom enterRoom = event.getEnterRoom();
                showFlow(enterRoom.getRoom_info().get(0).getRoom_cover());
            } else if (XUANFUYINCANG.equals(tag)) {//显示悬浮窗
                FloatingView.get().remove();
            } else if (TUISONG.equals(tag)) {//推送消息，显示布局
////                LogUtils.debugInfo("自己接受消息====");
////                PushBean pushBean = event.getPushBean();
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////                        if (!mIsPushRuning) {
////                            mMiniBarrageViewLayout.setData(pushBean);
////                            LogUtils.debugInfo("送礼物消息", "===" + JSON.toJSONString(pushBean));
////                        }
////                    }
////                });
//
//
////            barrageView.postDelayed(() -> mAdapter.add(pushBean), 1000);
//            } else if (KBXTUISONG.equals(tag)) { //开宝箱推送消息，显示布局
////                LogUtils.debugInfo("自己接受消息====");
////                PushBean pushBean = event.getPushBean();
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////                        if (!mIsPushRuning) {
////                            mMiniBarrageViewLayout.setData(pushBean);
////                            LogUtils.debugInfo("开宝箱消息", "===" + JSON.toJSONString(pushBean));
////                        }
////                    }
////                });
//
//            } else if ("yuedu".equals(tag)) {
////                getGuanFang();
//            } else if (QUANBUYINXIAN.equals(tag)) {
////                getOtherUserTwo();
//            } else if (TIAOSHEQU.equals(tag)) {
//                if (familyFragment == null) {
//                    return;
//                }
//                onClick(radioDongtai);
//
//            } else if (TIAORELIAO.equals(tag)) {
//                onClick(radioHomePage);
//            } else if (Constant.TUICHUFAMILY.equals(tag)) {
////                FamilyListFragment familyListFragment = new FamilyListFragment();
////                Bundle bundle = new Bundle();
////                bundle.putInt("is_family_show", mUserBean.getData().getIs_family_show());
////                bundle.putString("is_god", mUserBean.getData().getIs_god());
////                bundle.putBoolean("show_back", false);
////                familyListFragment.setArguments(bundle);
////                familyFragment = familyListFragment;
////                if (radioFamily.isChecked()) {
////                    ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(),
////                            familyFragment, R.id.frameLayout_main);
////                }
//            }
////            else if (TIAOXIADAN.equals(tag)) {
////                radioHomePage.setChecked(true);
////                radioHome.setChecked(true);
////                radioShequ.setChecked(false);
////                radioFinder.setChecked(false);
////                radioCenter.setChecked(false);
////                radioMessage.setChecked(false);
////                radioMessageOne.setFocusable(false);
////                radioMessageTwo.setFocusable(false);
////                ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(),
////                        mainHomePageFragment, R.id.frameLayout_main);
            }
        }


    }

    public void showFlow(String msg) {
        FloatingView.get().add();
        EnFloatingView view = FloatingView.get().getView();
        imgHeader = view.findViewById(R.id.imgHeader);
        img1 = view.findViewById(R.id.img1);
        img2 = view.findViewById(R.id.img2);
        img1.setSelected(true);
        loadImage(imgHeader, msg, R.mipmap.gender_zhuce_boy);
        img1.setOnClickListener(v -> {
            if (AdminHomeActivity.isStart) {
                if (img1.isSelected()) {
                    img1.setSelected(false);
                    AdminHomeActivity.mContext.stopTing(false);
                } else {
                    img1.setSelected(true);
                    AdminHomeActivity.mContext.stopTing(true);
                }
            }
        });
        img2.setOnClickListener(v -> {
            if (AdminHomeActivity.isStart) {
                AdminHomeActivity.isStart = false;
                AdminHomeActivity.mContext.finish();
            }
        });
        FloatingView.get().listener(new MagnetViewListener() {
            @Override
            public void onRemove(FloatingMagnetView magnetView) {
                Toast.makeText(MainActivity.this, "我没了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClick(FloatingMagnetView magnetView) {
                startActivity(new Intent(MainActivity.this, AdminHomeActivity.class));
            }
        });

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(3000);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setRepeatMode(Animation.RESTART);
        //让旋转动画一直转，不停顿的重点
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(-1);
        imgHeader.startAnimation(rotateAnimation);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        loadUserData();
        FloatingView.get().attach(this);
        if (AdminHomeActivity.mContext != null && AdminHomeActivity.mCanReture == true) {
            AdminHomeActivity.mCanReture = false;
            startActivity(new Intent(this, AdminHomeActivity.class));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
//        FloatingView.get().detach(this);
    }

    @Override
    public void onResume() {
        super.onResume();
//        LogUtils.debugInfo("====onResume");
        if (AdminHomeActivity.isStart && AdminHomeActivity.isTop) {
            AdminHomeActivity.mCanReture = false;
            startActivity(new Intent(this, AdminHomeActivity.class));
        }
    }

    private MaterialDialog progress;

    private void initUpdate() {

        String path = Environment.getExternalStorageDirectory().getAbsolutePath();

        Map<String, String> params = new HashMap<String, String>();

        params.put("appKey", "ab55ce55Ac4bcP408cPb8c1Aaeac179c5f6f");
        params.put("appVersion", DeviceUtils.getVersionName(this));
        params.put("versionCode", DeviceUtils.getVersionCode(this) + "");
        params.put("channel", Api.CHANNEL);

        new UpdateAppManager
                .Builder()
                //必须设置，当前Activity
                .setActivity(this)
                //必须设置，实现httpManager接口的对象
                .setHttpManager(new OkGoUpdateHttpUtil())
                //必须设置，更新地址
                .setUpdateUrl(Api.APP_DOMAIN + "android_version_check")

                //以下设置，都是可选
                //设置请求方式，默认get
                .setPost(false)
                //添加自定义参数，默认version=1.0.0（app的versionName）；apkKey=唯一表示（在AndroidManifest.xml配置）
                .setParams(params)
                //设置点击升级后，消失对话框，默认点击升级后，对话框显示下载进度
//                .hideDialogOnDownloading(false)
                //设置头部，不设置显示默认的图片，设置图片后自动识别主色调，然后为按钮，进度条设置颜色
//                .setTopPic(R.mipmap.top_8)
                //为按钮，进度条设置颜色，默认从顶部图片自动识别。
                //.setThemeColor(ColorUtil.getRandomColor())
                //设置apk下砸路径，默认是在下载到sd卡下/Download/1.0.0/test.apk
                .setTargetPath(path)
                //设置appKey，默认从AndroidManifest.xml获取，如果，使用自定义参数，则此项无效
                //.setAppKey("ab55ce55Ac4bcP408cPb8c1Aaeac179c5f6f")
                //不显示通知栏进度条
                .dismissNotificationProgress()
                //是否忽略版本
                //.showIgnoreVersion()

                .build()
                //检测是否有新版本
                .checkNewApp(new UpdateCallback() {
                    /**
                     * 解析json,自定义协议
                     *
                     * @param json 服务器返回的json
                     * @return UpdateAppBean
                     */
                    @Override
                    protected UpdateAppBean parseJson(String json) {
                        UpdateAppBean updateAppBean = new UpdateAppBean();
                        try {
                            org.json.JSONObject jsonObject = new org.json.JSONObject(json);
                            org.json.JSONObject dataObj = jsonObject.getJSONObject("data");
                            boolean isForceUpdate = dataObj.optBoolean("isForceUpdate");
                            String versionCode = dataObj.optString("newVersionCode");
                            String versionName = dataObj.optString("newVersionName");
                            String downLoadUrl = dataObj.optString("downLoadUrl");
                            String versionDetails = dataObj.optString("VersionDetails");

                            int localVersionCode = DeviceUtils.getVersionCode(MainActivity.this);

                            boolean canUpdate = false;
                            if (localVersionCode < Arith.strToInt(versionCode)) {//本地小于服务器版本
                                canUpdate = true;
                            }

                            updateAppBean
                                    //（必须）是否更新Yes,No
                                    .setUpdate(canUpdate ? "Yes" : "No")
                                    //（必须）新版本号，
                                    .setNewVersion(versionName)
                                    //（必须）下载地址
                                    .setApkFileUrl(downLoadUrl)
                                    //（必须）更新内容
                                    .setUpdateLog(versionDetails)
                                    //大小，不设置不显示大小，可以不设置
//                                    .setTargetSize(jsonObject.optString("target_size"))
                                    //是否强制更新，可以不设置
                                    .setConstraint(isForceUpdate);
                            //设置md5，可以不设置
//                                    .setNewMd5(jsonObject.optString("new_md51"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return updateAppBean;
                    }

                    /**
                     * 网络请求之前
                     */
                    @Override
                    public void onBefore() {
//                        CProgressDialogUtils.showProgressDialog(JavaActivity.this);
                    }

                    /**
                     * 网路请求之后
                     */
                    @Override
                    public void onAfter() {
//                        CProgressDialogUtils.cancelProgressDialog(JavaActivity.this);
                    }

                    /**
                     * 没有新版本
                     */
                    @Override
                    public void noNewApp(String error) {
//                        toast("没有新版本");
//                        Toast.makeText(JavaActivity.this, "没有新版本", Toast.LENGTH_SHORT).show();
                    }
                });

//        new PgyUpdateManager.Builder()
//                .register();
//        /** 新版本 **/
//        new PgyUpdateManager.Builder()
//                .setForced(true)                //设置是否强制提示更新,非自定义回调更新接口此方法有用
//                .setUserCanRetry(false)         //失败后是否提示重新下载，非自定义下载 apk 回调此方法有用
//                .setDeleteHistroyApk(false)     // 检查更新前是否删除本地历史 Apk， 默认为true
//                .setUpdateManagerListener(new UpdateManagerListener() {
//                    @Override
//                    public void onNoUpdateAvailable() {
//                        //没有更新是回调此方法
//                        Log.d("pgyer", "there is no new version");
//                    }
//
//                    @Override
//                    public void onUpdateAvailable(AppBean appBean) {
//                        //有更新回调此方法
//                        Log.d("pgyer", "there is new version can update"
//                                + "new versionCode is " + appBean.getVersionCode());
//                        //调用以下方法，DownloadFileListener 才有效；
//                        //如果完全使用自己的下载方法，不需要设置DownloadFileListener
//                        new MaterialDialog.Builder(MainActivity.this)
//                                .title("发现最新版本哦~")
//                                .content("请尽快升级到最新版本，以免影响您的正常使用")
//                                .positiveText("确认更新")
//                                .negativeText("取消")
//                                .onPositive((dialog, which) -> {
//                                    progress = new MaterialDialog.Builder(MainActivity.this)
//                                            .title("正在更新")
//                                            .content("请耐心等待")
//                                            .canceledOnTouchOutside(false)
//                                            .progress(true, 0)
//                                            .show();
//                                    PgyUpdateManager.downLoadApk(appBean.getDownloadURL());
//                                })
//                                .show();
//                    }
//
//                    @Override
//                    public void checkUpdateFailed(Exception e) {
//                        //更新检测失败回调
//                        //更新拒绝（应用被下架，过期，不在安装有效期，下载次数用尽）以及无网络情况会调用此接口
//                        Log.e("pgyer", "check update failed ", e);
//                    }
//                })
//                //注意 ：
//                //下载方法调用 PgyUpdateManager.downLoadApk(appBean.getDownloadURL()); 此回调才有效
//                //此方法是方便用户自己实现下载进度和状态的 UI 提供的回调
//                //想要使用蒲公英的默认下载进度的UI则不设置此方法
//                .setDownloadFileListener(new DownloadFileListener() {
//                    @Override
//                    public void downloadFailed() {
//                        //下载失败
//                        progress.dismiss();
//                    }
//
//                    @Override
//                    public void downloadSuccessful(Uri uri) {
//                        progress.dismiss();
//                        // 使用蒲公英提供的安装方法提示用户 安装apk
//                        PgyUpdateManager.installApk(uri);
//                    }
//
//                    @Override
//                    public void onProgressUpdate(Integer... integers) {
//                        Log.e("pgyer", "update download apk progress" + integers);
//                    }
//                })
//                .register();
    }


//    // 在多视图弹幕中自己需要构建多个类型ViewHolder
////    class ViewHolder extends BarrageAdapter.BarrageViewHolder<PushBean> {
////
//////        private ImageView imgGift;
//////        private TextView textUSer1, textUSer2, textNumber, textGiftName, box, OkBtn, wuYongOne;
////
////        public ViewHolder(View itemView) {
////            super(itemView);
////            imgGift = itemView.findViewById(R.id.imgGift);
////            textUSer1 = itemView.findViewById(R.id.textUSer1);
////            textUSer2 = itemView.findViewById(R.id.textUSer2);
////            textNumber = itemView.findViewById(R.id.textNumber);
////            textGiftName = itemView.findViewById(R.id.textGiftName);
////            box = itemView.findViewById(R.id.box);
////            OkBtn = itemView.findViewById(R.id.ok_btn);
////            wuYongOne = itemView.findViewById(R.id.wuyong_one);
////        }
////
////        @Override
////        protected void onBind(PushBean pushBean) {
////            if (pushBean != null) {
////                if ("gift".equals(pushBean.type)) {
////                    wuYongOne.setText("惊现土豪~");
////                    textUSer1.setText(pushBean.getData().getFrom_name());
////                    textUSer2.setText(pushBean.getData().getUser_name());
//////                    textNumber.setText(pushBean.getData().getNum() + "个");
////                    box.setText("赠送给");
////                    textGiftName.setText(pushBean.getData().getGift_name()+"x"+pushBean.getData().getNum());
////                    GlideArms
////                            .with(MainActivity.this)
////                            .load(pushBean.getData().getImg())
////                            .into(imgGift);
////                    OkBtn.setVisibility(View.VISIBLE);
////                } else if ("award".equals(pushBean.type)) {
//////                    String text = "哇哦，"+pushBean.getData().getUser_name()+"在"+pushBean.getData().getBoxclass()+"开出了"+pushBean.getData().getGift_name()+"，真是太优秀了！";
////                    wuYongOne.setText("哇哦~");
////                    textUSer1.setText(pushBean.getData().getUser_name());
////                    box.setText("在" + pushBean.getData().getBoxclass() + "开出了");
////                    textUSer2.setText(pushBean.getData().getGift_name());
////                    textNumber.setText("");
//////                    textNumber.setText("，真是太优秀了！");
////                    GlideArms
////                            .with(MainActivity.this)
////                            .load(pushBean.getData().getImg())
////                            .into(imgGift);
////                    OkBtn.setVisibility(View.GONE);
////
////                    //所有房间公屏提示
//////                    MessageEvent messageEvent = new MessageEvent();
//////                    messageEvent.setStateMessage(StateMessage.PEOPLE_OPEN_GEMSTONE);
//////                    messageEvent.setObject(text);
//////                    EventBus.getDefault().post(messageEvent);
////
////                }
////            }
////        }
////    }

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

    @OnClick({R.id.radio_yule, R.id.radio_peiwan, R.id.radio_home_page, R.id.radio_dongtai, R.id.radio_center, R.id.float_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.radio_yule:
                radioYule.setChecked(true);
                radioPeiwan.setChecked(false);
                radioHomePage.setChecked(false);
                radioDongtai.setChecked(false);
                radioCenter.setChecked(false);
                ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(),
                        mainYuleFragment, R.id.frameLayout_main);
                float_button.setVisibility(View.VISIBLE);
                break;
            case R.id.radio_peiwan:
                radioYule.setChecked(false);
                radioPeiwan.setChecked(true);
                radioHomePage.setChecked(false);
                radioDongtai.setChecked(false);
                radioCenter.setChecked(false);
                ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(),
                        mainPeiwanFragment, R.id.frameLayout_main);
                float_button.setVisibility(View.VISIBLE);
                break;
            case R.id.radio_home_page:

                radioYule.setChecked(false);
                radioPeiwan.setChecked(false);
                radioHomePage.setChecked(true);
                radioDongtai.setChecked(false);
                radioCenter.setChecked(false);
                ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(),
                        mainHomeFragment, R.id.frameLayout_main);
                float_button.setVisibility(View.VISIBLE);
                break;
            case R.id.radio_dongtai:

                radioYule.setChecked(false);
                radioPeiwan.setChecked(false);
                radioHomePage.setChecked(false);
                radioDongtai.setChecked(true);
                radioCenter.setChecked(false);
                ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(),
                        mainCommunityFragment, R.id.frameLayout_main);
                float_button.setVisibility(View.VISIBLE);
                break;
            case R.id.radio_center:
                radioYule.setChecked(false);
                radioPeiwan.setChecked(false);
                radioHomePage.setChecked(false);
                radioDongtai.setChecked(false);
                radioCenter.setChecked(true);
                ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(),
                        mainCenterFragment, R.id.frameLayout_main);
                float_button.setVisibility(View.GONE);
                break;

            case R.id.float_button:
                ArmsUtils.startActivity(MessageFragmentActivity.class);
                break;

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

                data.getMessage().getContent().getUserInfo();
//                LogUtils.debugInfo("用户名称："+userName);
            }
        }

    }

    //获取融云未读消息
    private void getUnreadCount() {
        Conversation.ConversationType[] conversationTypes = {
                Conversation.ConversationType.PRIVATE,
                Conversation.ConversationType.GROUP,
                Conversation.ConversationType.SYSTEM,
                Conversation.ConversationType.PUBLIC_SERVICE,
                Conversation.ConversationType.APP_PUBLIC_SERVICE};
        RongIM.getInstance().addUnReadMessageCountChangedObserver(this, conversationTypes);
    }

    @Override
    public void onCountChanged(int i) {
        LogUtils.debugInfo("====我也不知道是啥", i + "");
//        if (unreadHomeTips == null) {
//            return;
//        }
//        if (i == 0) {
//            unreadHomeTips.setVisibility(View.GONE);
//        } else {
//            unreadHomeTips.setVisibility(View.VISIBLE);
//        }
    }


//    //获取官方消息
//    private void getGuanFang() {
//        RxUtils.loading(commonModel.mini_official(String.valueOf(UserManager.getUser().getUserId())), this)
//                .subscribe(new ErrorHandleSubscriber<MiniOfficBean>(mErrorHandler) {
//                    @Override
//                    public void onNext(MiniOfficBean miniOfficBean) {
//                        if (unreadHomeTips == null) {
//                            return;
//                        }
//                        if (miniOfficBean.getData().getUnread() == 0) {
//                            unreadHomeTips.setVisibility(View.GONE);
//                        } else {
//                            unreadHomeTips.setVisibility(View.VISIBLE);
//                        }
//                    }
//                });
//    }

    //设置点击todaySignWindow外部点击事件不会传递到Activity
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (todaySignWindow != null && todaySignWindow.isShowing()) {
            return false;
        }
        return super.dispatchTouchEvent(event);
    }

}
