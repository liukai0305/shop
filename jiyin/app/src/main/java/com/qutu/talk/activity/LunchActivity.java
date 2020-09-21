package com.qutu.talk.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.activity.login.LoginActivity;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.BannerBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.SharedPreferencesUtils;

import java.util.List;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class LunchActivity extends MyBaseArmActivity {

    @Inject
    CommonModel commonModel;
    private ImageView imageView;
    Handler handler = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);//恢复原有的样式

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean isFirstIn = (boolean) SharedPreferencesUtils.getParam(mContext, "isFirstOpen", true);
                if (isFirstIn) {
                    SharedPreferencesUtils.setParam(mContext, "isFirstOpen", false);
                    ArmsUtils.startActivity(GuildActivity.class);
                    LunchActivity.this.finish();

                } else {
                    if (UserManager.IS_LOGIN) {
                        ArmsUtils.startActivity(MainActivity.class);
                        finish();
                    }else{
                        ArmsUtils.startActivity(LoginActivity.class);
                        LunchActivity.this.finish();
                    }
                }
            }
        }, 4000);
    }

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
        return R.layout.splash;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        imageView = findViewById(R.id.imageView);
        loadBanner();
    }

    public void loadBanner() {
        final AppComponent appComponent = ArmsUtils.obtainAppComponentFromContext(mContext);
        String isFirstOpenImg = (String) SharedPreferencesUtils.getParam(mContext, "isFirstOpenImg", "");
        if (!TextUtils.isEmpty(isFirstOpenImg)) {
            appComponent
                    .imageLoader()
                    .loadImage(mContext, ImageConfigImpl
                            .builder()
                            .url(isFirstOpenImg)
                            .cacheStrategy(0)
                            .imageView(imageView)
                            .build());
        }
        RxUtils.loading(commonModel.carousel("4"), this)
                .subscribe(new ErrorHandleSubscriber<BannerBean>(mErrorHandler) {
                    @Override
                    public void onNext(BannerBean bannerBean) {
                        List<BannerBean.DataBean> beanList = bannerBean.getData();
                        if (beanList != null && beanList.size() > 0) {
                            BannerBean.DataBean dataBean = beanList.get(0);
                            String img = dataBean.getImg();
                            if (!TextUtils.isEmpty(isFirstOpenImg)&&isFirstOpenImg.equals(img)) {
                                return;
                            }
                            SharedPreferencesUtils.setParam(mContext, "isFirstOpenImg", img);
                            appComponent
                                    .imageLoader()
                                    .loadImage(mContext, ImageConfigImpl
                                            .builder()
                                            .url(img)
                                            .cacheStrategy(0)
                                            .imageView(imageView)
                                            .build());
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacks(null);
        }
    }
}
