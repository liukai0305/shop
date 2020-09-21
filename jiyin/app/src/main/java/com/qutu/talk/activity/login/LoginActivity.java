package com.qutu.talk.activity.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DeviceUtils;
import com.jess.arms.utils.LogUtils;
import com.kongzue.dialog.v3.MessageDialog;
import com.qutu.talk.R;
import com.qutu.talk.activity.MainActivity;
import com.qutu.talk.activity.message.LiaoBaExtensionModule;
import com.qutu.talk.app.Api;
import com.qutu.talk.app.converter.ApiIOException;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.BaseWebActivity;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.CodeBean;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.Login;
import com.qutu.talk.bean.LoginData;
import com.qutu.talk.bean.OtherBean;
import com.qutu.talk.bean.VerifyReturn;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.Arith;
import com.qutu.talk.utils.Constant;
import com.qutu.talk.utils.OkGoUpdateHttpUtil;
import com.qutu.talk.utils.SharedPreferencesUtils;
import com.qutu.talk.utils.ToastUtil;
import com.qutu.talk.utils.VerificationUtils;
import com.qutu.talk.view.ClearEditText;
import com.qutu.talk.view.ShapeTextView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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


public class LoginActivity extends MyBaseArmActivity {

    public static final String tag = "LoginActivity";
    @Inject
    CommonModel commonModel;
    @BindView(R.id.edt_login_name)
    ClearEditText edtLoginName;
    @BindView(R.id.edt_login_pass)
    ClearEditText edtLoginPass;
    @BindView(R.id.btn_ok)
    ShapeTextView btnOk;
    @BindView(R.id.textRegister)
    TextView textRegister;
    @BindView(R.id.img1)
    ImageView img1;
    @BindView(R.id.img2)
    ImageView img2;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.oneline)
    View oneline;
    @BindView(R.id.twoline)
    View twoline;
    @BindView(R.id.yonghu)
    TextView xieyiText;
    @BindView(R.id.toolbar_back)
    RelativeLayout toolbarBack;
    @BindView(R.id.layout_other_agreement)
    LinearLayout mLayoutOtherAgreement;
    @BindView(R.id.tv_yingyongbao)
    TextView mTvYingyongbao;
    @BindView(R.id.layout_yingyognbao_agreement)
    LinearLayout mLayoutYingyognbaoAgreement;
    @BindView(R.id.tv_change_login_type)
    TextView tvChangeLoginType;
    @BindView(R.id.et_code)
    ClearEditText etCode;
    @BindView(R.id.btn_get_code)
    Button btnGetCode;
    @BindView(R.id.ll_verify_code)
    LinearLayout llVerifyCode;
    private int loginType=0;//0.验证码登录 1.手机号密码登录
//    @BindView(R.id.img4)
//    ImageView img4;


    private String openid, nackName, iconurl, type;

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
        return R.layout.activity_login;
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        if (Api.CHANNEL.equals("yingyongbao")) {
            mLayoutOtherAgreement.setVisibility(View.GONE);
            mLayoutYingyognbaoAgreement.setVisibility(View.VISIBLE);
            String str1 = "已同意";
            String str2 = "《应用宝软件许可及服务协议》";
            String str3 = "及";
            String str4 = "《隐私政策》";

            mTvYingyongbao.append(new SpannableString(str1));

            SpannableString clickStringOne = new SpannableString(str2);

            clickStringOne.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {//点击事件
                    LogUtils.debugInfo("点击了=======");
                    Intent intent = new Intent(LoginActivity.this, BaseWebActivity.class);
//                    intent.putExtra("url", "http://qutu.zzmzrj.com/index/index/user_protocol");
                    intent.putExtra("url", "file:///android_asset/user.html");
                    intent.putExtra("title", "服务协议");
                    startActivity(intent);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setColor(Color.parseColor("#ff3e6d"));//设置颜色
                    ds.setUnderlineText(false);//去掉下划线
                }
            }, 0, clickStringOne.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            mTvYingyongbao.append(clickStringOne);

            mTvYingyongbao.append(new SpannableString(str3));

            clickStringOne = new SpannableString(str4);

            clickStringOne.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {//点击事件
                    Intent intent = new Intent(LoginActivity.this, BaseWebActivity.class);
//                    intent.putExtra("url", "http://qutu.zzmzrj.com/index/index/user_protocol");
                    intent.putExtra("url", "file:///android_asset/userPrivacy.html");
                    intent.putExtra("title", "隐私协议");
                    startActivity(intent);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setColor(Color.parseColor("#ff3e6d"));//设置颜色
                    ds.setUnderlineText(false);//去掉下划线
                }
            }, 0, clickStringOne.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            mTvYingyongbao.append(clickStringOne);

            mTvYingyongbao.setMovementMethod(LinkMovementMethod.getInstance());

            //设置超文本点击后的背景色为透明
            mTvYingyongbao.setHighlightColor(mTvYingyongbao.getResources().getColor(android.R.color.transparent));


        } else {
            mLayoutOtherAgreement.setVisibility(View.VISIBLE);
            mLayoutYingyognbaoAgreement.setVisibility(View.GONE);
        }

        if (getIntent().getIntExtra("sign", 0) == 1) {
            edtLoginName.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MessageDialog.show(LoginActivity.this, "下线通知", "您的账号在别的设备登录，您已被迫下线", "确定");
                }
            }, 2000);
        }
        SharedPreferencesUtils.setParam(this, "isFirstIn", false);
        //读取本地用户信息
        UserManager.initData();
        if (UserManager.IS_LOGIN) {
            ArmsUtils.startActivity(MainActivity.class);
            finish();
        }
        try {
            edtLoginName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (TextUtils.isEmpty(s.toString().replace(" ", ""))) {
                        oneline.setBackgroundColor(getResources().getColor(R.color.line));
                    } else {
                        oneline.setBackgroundColor(getResources().getColor(R.color.font_ff3e6d));
                    }
                }
            });
            edtLoginPass.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (TextUtils.isEmpty(s.toString().replace(" ", ""))) {
                        twoline.setBackgroundColor(getResources().getColor(R.color.line));
                    } else {
                        twoline.setBackgroundColor(getResources().getColor(R.color.font_ff3e6d));
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            twoline.setBackgroundColor(getResources().getColor(R.color.font_ff3e6d));
            oneline.setBackgroundColor(getResources().getColor(R.color.font_ff3e6d));
        }

        initUpdate();

    }

    private void initUpdate() {

        String path = Environment.getExternalStorageDirectory().getAbsolutePath();

        Map<String, String> params = new HashMap<String, String>();

        params.put("appKey", "ab55ce55Ac4bcP408cPb8c1Aaeac179c5f6f");
        params.put("appVersion", DeviceUtils.getVersionName(this));
        params.put("versionCode", DeviceUtils.getVersionCode(this) + "");

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
                            JSONObject jsonObject = new JSONObject(json);
                            JSONObject dataObj = jsonObject.getJSONObject("data");
                            boolean isForceUpdate = dataObj.optBoolean("isForceUpdate");
                            String versionCode = dataObj.optString("newVersionCode");
                            String versionName = dataObj.optString("newVersionName");
                            String downLoadUrl = dataObj.optString("downLoadUrl");
                            String versionDetails = dataObj.optString("VersionDetails");

                            int localVersionCode = DeviceUtils.getVersionCode(LoginActivity.this);

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

    }

    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case WHAT_GET_CODE:
                    int currentSecond=Integer.parseInt(btnGetCode.getText().toString().replace("s",""));
                    if(currentSecond>1){
                        btnGetCode.setText((currentSecond-1)+"s");
                        btnGetCode.setClickable(false);
                        sendEmptyMessageDelayed(msg.what,1000);
                    }else{
                        btnGetCode.setText("发送");
                        btnGetCode.setClickable(true);
                    }
                    break;
            }
        }
    };
    private static final int WHAT_GET_CODE=0;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(WHAT_GET_CODE);
    }

    @OnClick({R.id.btn_ok, R.id.tv_change_login_type, R.id.textRegister, R.id.img1, R.id.img2, R.id.yonghu,R.id.btn_get_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_code:
                String phone1 = edtLoginName.getText().toString().replace(" ", "");
                if(phone1.length()!=11){
                    showMessage("手机号格式不正确");
                    return;
                }
                RxUtils.loading(commonModel.getVerifyCode(phone1), this)
                        .subscribe(new ErrorHandleSubscriber<VerifyReturn>(mErrorHandler) {

                            @Override
                            public void onNext(VerifyReturn verifyReturn) {
                                handler.sendEmptyMessageDelayed(WHAT_GET_CODE,1000);
                                btnGetCode.setText("60s");
                            }

                            @Override
                            public void onError(Throwable t) {
                                super.onError(t);
                            }
                        });
                break;
            case R.id.btn_ok:
                String phone = edtLoginName.getText().toString().replace(" ", "");
                String password = edtLoginPass.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    showMessage("账号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(password)&&loginType==1) {
                    showMessage("密码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(etCode.getText().toString().trim())&&loginType==0) {
                    showMessage("验证码不能为空");
                    return;
                }
                if (!VerificationUtils.VildateMobile(phone)) {
                    showMessage("账号输入不合法");
                    return;
                }
                showDialogLoding();
                if(loginType==1){
                    RxUtils.loading(
                            commonModel.login(phone, password), this)
                            .subscribe(new ErrorHandleSubscriber<Login>(mErrorHandler) {
                                @Override
                                public void onNext(Login userInfo) {
                                    disDialogLoding();
                                    //设置融云用户信息
//                                    initRooIm(userInfo);

//                                    Uri mediaUriFromPath = BaseUtils.getMediaUriFromPath(LoginActivity.this,
//                                            userInfo.getData().getHeadimgurl());

//                                    Uri mediaUriFromPath = Uri.parse(userInfo.getData().getHeadimgurl());
//
//                                    UserInfo user = new UserInfo(String.valueOf(userInfo.getData().getId()),
//                                            userInfo.getData().getNickname(),mediaUriFromPath);
//                                    RongIM.getInstance().setCurrentUserInfo(user);


                                    showToast("登录成功");
                                    //用户信息存入数据库
                                    LoginData loginData = new LoginData();
                                    loginData.setBirthday(userInfo.getData().getBirthday());
                                    loginData.setCity(userInfo.getData().getCity());
                                    loginData.setHeadimgurl(userInfo.getData().getHeadimgurl());
                                    loginData.setIntroduction(userInfo.getData().getIntroduction());
                                    loginData.setIs_room(userInfo.getData().getIs_room());
                                    loginData.setLevel(userInfo.getData().getLevel());
                                    loginData.setMizuan(userInfo.getData().getMizuan());
                                    loginData.setNickname(userInfo.getData().getNickname());
                                    loginData.setOpenid(userInfo.getData().getOpenid());
                                    loginData.setUserId(userInfo.getData().getId());
                                    loginData.setPass(userInfo.getData().getPass());
                                    loginData.setPhone(userInfo.getData().getPhone());
                                    loginData.setSex(userInfo.getData().getSex());
                                    loginData.setRy_token(userInfo.getData().getRy_token());
                                    loginData.setToken(userInfo.getData().getToken());
//                                    if(TextUtils.isEmpty(userInfo.getData().getProvince())){
//                                        loginData.setPr(userInfo.getData().getProvince());
//                                    }
//                                    if(TextUtils.isEmpty(userInfo.getData().getCountry())){
//                                        loginData.set(userInfo.getData().getCountry());
//                                    }
                                    LitePal.deleteAll(LoginData.class);
                                    loginData.save();//litepal数据库，不能随便改LoginData数据
                                    UserManager.initData();//存储完，初始化
                                    EventBus.getDefault().post(new FirstEvent("指定发送", Constant.LOGIN));
                                    ArmsUtils.startActivity(MainActivity.class);
                                    finish();
                                }

                                @Override
                                public void onError(Throwable t) {
                                    super.onError(t);
                                    disDialogLoding();
                                }
                            });
                }else{
                    RxUtils.loading(
                            commonModel.loginByVerifyCode(phone, etCode.getText().toString().trim()), this)
                            .subscribe(new ErrorHandleSubscriber<Login>(mErrorHandler) {
                                @Override
                                public void onNext(Login userInfo) {
                                    disDialogLoding();
                                    //设置融云用户信息
//                                    initRooIm(userInfo);

//                                    Uri mediaUriFromPath = BaseUtils.getMediaUriFromPath(LoginActivity.this,
//                                            userInfo.getData().getHeadimgurl());

//                                    Uri mediaUriFromPath = Uri.parse(userInfo.getData().getHeadimgurl());
//
//                                    UserInfo user = new UserInfo(String.valueOf(userInfo.getData().getId()),
//                                            userInfo.getData().getNickname(),mediaUriFromPath);
//                                    RongIM.getInstance().setCurrentUserInfo(user);


                                    showToast("登录成功");
                                    //用户信息存入数据库
                                    LoginData loginData = new LoginData();
                                    loginData.setBirthday(userInfo.getData().getBirthday());
                                    loginData.setCity(userInfo.getData().getCity());
                                    loginData.setHeadimgurl(userInfo.getData().getHeadimgurl());
                                    loginData.setIntroduction(userInfo.getData().getIntroduction());
                                    loginData.setIs_room(userInfo.getData().getIs_room());
                                    loginData.setLevel(userInfo.getData().getLevel());
                                    loginData.setMizuan(userInfo.getData().getMizuan());
                                    loginData.setNickname(userInfo.getData().getNickname());
                                    loginData.setOpenid(userInfo.getData().getOpenid());
                                    loginData.setUserId(userInfo.getData().getId());
                                    loginData.setPass(userInfo.getData().getPass());
                                    loginData.setPhone(userInfo.getData().getPhone());
                                    loginData.setSex(userInfo.getData().getSex());
                                    loginData.setRy_token(userInfo.getData().getRy_token());
                                    loginData.setToken(userInfo.getData().getToken());
//                                    if(TextUtils.isEmpty(userInfo.getData().getProvince())){
//                                        loginData.setPr(userInfo.getData().getProvince());
//                                    }
//                                    if(TextUtils.isEmpty(userInfo.getData().getCountry())){
//                                        loginData.set(userInfo.getData().getCountry());
//                                    }
                                    LitePal.deleteAll(LoginData.class);
                                    loginData.save();//litepal数据库，不能随便改LoginData数据
                                    UserManager.initData();//存储完，初始化
                                    EventBus.getDefault().post(new FirstEvent("指定发送", Constant.LOGIN));
                                    ArmsUtils.startActivity(MainActivity.class);
                                    finish();
                                }

                                @Override
                                public void onError(Throwable t) {
                                    super.onError(t);
                                    disDialogLoding();
                                }
                            });
                }
                break;
            case R.id.tv_change_login_type:
                if(loginType==0){
                    loginType=1;
                    tvChangeLoginType.setText("验证码登录");
                    llVerifyCode.setVisibility(View.GONE);
                    edtLoginPass.setVisibility(View.VISIBLE);
                }else{
                    loginType=0;
                    tvChangeLoginType.setText("密码登录");
                    edtLoginPass.setVisibility(View.GONE);
                    llVerifyCode.setVisibility(View.VISIBLE);
                }
//                ArmsUtils.startActivity(ForgetPsActivity.class);
                break;
            case R.id.textRegister:
                ArmsUtils.startActivity(RegisterActivity.class);
                break;
            case R.id.img1:
                UMShareAPI.get(this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, authListener);
                break;
            case R.id.img2:
                UMShareAPI.get(this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.WEIXIN, authListener);
                break;
            case R.id.img3:
                UMShareAPI.get(this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.SINA, authListener);
                break;
            case R.id.yonghu:
                Intent intent = new Intent(LoginActivity.this, BaseWebActivity.class);
                intent.putExtra("url", "http://qutu.zzmzrj.com/index/index/user_protocol");
                intent.putExtra("title", "用户协议");
                startActivity(intent);
                break;

            default:
        }
    }


    /**
     * 初始化融云
     *
     * @param userInfo
     */
    private void initRooIm(Login userInfo) {
        RongIM.connect(userInfo.getData().getRy_token(), new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                LogUtils.debugInfo("TAG", "====token错误");
            }

            @Override
            public void onSuccess(String userid) {
                LogUtils.debugInfo("TAG", "--onSuccess" + userid);

                Uri mediaUriFromPath = Uri.parse(userInfo.getData().getHeadimgurl());

                UserInfo user = new UserInfo(String.valueOf(userInfo.getData().getId()),
                        userInfo.getData().getNickname(), mediaUriFromPath);

                RongIM.getInstance().setCurrentUserInfo(user);

//                UserInfo user = new UserInfo(userid,
//                        UserManager.getUser().getNickname(), Uri.parse(UserManager.getUser().getHeadimgurl()));
//                RongIM.getInstance().setCurrentUserInfo(user);
//                RongIM.getInstance().setMessageAttachedUserInfo(true);
                registerExtensionPlugin();
                RongIM.registerMessageTemplate(new MyTextMessageItemProvider());
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                LogUtils.debugInfo("TAG", "--Error" + errorCode);
            }
        });

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
            }
        }
    }

    private UMAuthListener authListener = new UMAuthListener() {

        @Override
        public void onStart(SHARE_MEDIA platform) {
            LogUtils.debugInfo("onStart" + platform);
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            int a = 0;

            if (data == null) {
                return;
            }
            if (SHARE_MEDIA.QQ == platform) {
                if (data.get("gender").equals("男")) {
                    logOther(data.get("uid"), "qq", data.get("name"), data.get("iconurl"), "1");
                } else {
                    logOther(data.get("uid"), "qq", data.get("name"), data.get("iconurl"), "2");
                }

            } else if (SHARE_MEDIA.WEIXIN == platform) {
                if (data.get("gender").equals("男")) {
                    logOther(data.get("uid"), "wx", data.get("name"), data.get("iconurl"), "1");
                } else {
                    logOther(data.get("uid"), "wx", data.get("name"), data.get("iconurl"), "2");
                }
            } else if (SHARE_MEDIA.SINA == platform) {
                if (data.get("gender").equals("男")) {
                    logOther(data.get("uid"), "wb", data.get("name"), data.get("iconurl"), "1");
                } else {
                    logOther(data.get("uid"), "wb", data.get("name"), data.get("iconurl"), "2");
                }

            } else {
                Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            LogUtils.debugInfo("onError" + t.getMessage());
            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            LogUtils.debugInfo("onCancel" + "取消了");
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private void logOther(String uid, String typa, String name, String iconurl, String gender) {
        RxUtils.loading(commonModel.logOther(uid, typa), this)
                .subscribe(new ErrorHandleSubscriber<OtherBean>(mErrorHandler) {
                    @Override
                    public void onNext(OtherBean login) {
                        LogUtils.debugInfo("====错了吗", login.getMessage());
                        if (login.getCode() == 1) {
                            LoginData loginData = new LoginData();
                            loginData.setHeadimgurl(login.getData().getHeadimgurl());
                            loginData.setNickname(login.getData().getNickname());
                            loginData.setUserId(login.getData().getId());
                            loginData.setPhone(login.getData().getPhone());
                            loginData.setRy_token(login.getData().getRy_token());
                            loginData.setToken(login.getData().getToken());
                            LitePal.deleteAll(LoginData.class);
                            loginData.save();//litepal数据库，不能随便改LoginData数据
                            UserManager.initData();//存储完，初始化
                            EventBus.getDefault().post(new FirstEvent("指定发送", Constant.LOGIN));
                            ArmsUtils.startActivity(MainActivity.class);
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Intent intent = new Intent(LoginActivity.this, BingPhoneActivity.class);
                        intent.putExtra("openid", uid);
                        intent.putExtra("type", typa);
                        intent.putExtra("gender", gender);
                        intent.putExtra("nackName", name);
                        intent.putExtra("iconurl", iconurl);
                        ArmsUtils.startActivity(intent);
                    }
                });
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void receiveMsg(FirstEvent event) {
//        String tag = event.getTag();
//        if (REGISTER.equals(tag)) {
//            finish();
//        }
//    }

    @Override
    public void onResume() {
        super.onResume();
        toolbarTitle.setVisibility(View.GONE);
        toolbarBack.setVisibility(View.GONE);
    }

}
