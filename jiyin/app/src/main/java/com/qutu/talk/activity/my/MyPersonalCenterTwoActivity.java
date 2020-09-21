package com.qutu.talk.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.activity.message.ReportActivity;
import com.qutu.talk.activity.room.AdminHomeActivity;
import com.qutu.talk.adapter.MyPagerAdapterTwo;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.BaseBean;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.MyPersonalCebterTwoBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.fragment.MyDongTaiFragment;
import com.qutu.talk.fragment.MyGiftFragment;
import com.qutu.talk.fragment.MyInformationFragment;
import com.qutu.talk.fragment.SkillFragment;
import com.qutu.talk.popup.PersonalTwoPopul;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.AppBarStateChangeListener;
import com.qutu.talk.utils.FullScreenUtil;
import com.qutu.talk.utils.RoomHelper;
import com.qutu.talk.utils.mytablayout.TabLayout;
import com.qutu.talk.view.GlideImageLoader;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.qutu.talk.utils.Constant.JIECHUCP;
import static com.qutu.talk.utils.Constant.KAIQICPWEI;
import static com.qutu.talk.utils.Constant.XIUGAIGERENZILIAOCHENGGONG;

public class MyPersonalCenterTwoActivity extends MyBaseArmActivity {

    @Inject
    CommonModel commonModel;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.fensi_text)
    TextView fensiText;
    @BindView(R.id.banner_rela)
    RelativeLayout bannerRela;
    @BindView(R.id.personal_name)
    TextView personalName;
    @BindView(R.id.personal_sex)
    ImageView personalSex;
    @BindView(R.id.personal_vip_one)
    ImageView personalVipOne;
    @BindView(R.id.personal_vip_two)
    ImageView personalVipTwo;
    @BindView(R.id.suozai)
    ImageView suozai;
    @BindView(R.id.top)
    RelativeLayout top;
    @BindView(R.id.toolbar_back)
    RelativeLayout toolbarBack;
    @BindView(R.id.xiugai)
    ImageView xiugai;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.fenxiang)
    TextView fenxiang;
    @BindView(R.id.toolbar_right)
    RelativeLayout toolbarRight;
    @BindView(R.id.top_toolbar)
    Toolbar topToolbar;
    @BindView(R.id.AppBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.tabs_promotlist)
    TabLayout tabsPromotlist;
    @BindView(R.id.viewpager_personal)
    ViewPager viewpagerPersonal;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.gz_text)
    TextView gzText;
    @BindView(R.id.guanzhu)
    LinearLayout guanzhu;
    @BindView(R.id.liaotian)
    LinearLayout liaotian;
    @BindView(R.id.buttom_btn)
    LinearLayout buttomBtn;
    @BindView(R.id.aaaaaa)
    CardView aaaaaa;
    @BindView(R.id.toolbar_back_img)
    ImageView toolbarBackImg;
    @BindView(R.id.personal_vip_three)
    ImageView personalVipThree;


//    private ViewPager viewpagerPromotlist;
//    private TabLayout tabsPromotlist;
//    private ImageView xiugai;
//    private CardView aaaaa;

    private List<String> tabList; //tabLayout的数据源
    private List<Fragment> fragmentList; //fragment的数据源
    private MyPagerAdapterTwo myPagerAdapter;
    private int sign, follow, user_id;
    private String fromId;
    private MyPersonalCebterTwoBean.DataBean data;
    private SkillFragment sf;

    private boolean isRoom;//是否房间进入

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
        return R.layout.activity_my_personal_center_two;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        //沉浸式状态栏
        ImmersionBar.with(this)
                .reset()
                .titleBar(topToolbar)
                .init();

        sign = getIntent().getIntExtra("sign", 0);
        fromId = getIntent().getStringExtra("id");
        isRoom = getIntent().getBooleanExtra("isRoom", false);

        //sign=0 是从自己的个人主页  否则是别人的个人主页
        if (sign == 0) {
            xiugai.setVisibility(View.VISIBLE);
            aaaaaa.setVisibility(View.GONE);
            fenxiang.setVisibility(View.VISIBLE);
            fenxiang.setText("分享");
            more.setVisibility(View.GONE);
        } else {
            xiugai.setVisibility(View.INVISIBLE);
            aaaaaa.setVisibility(View.VISIBLE);
            fenxiang.setVisibility(View.GONE);
            more.setVisibility(View.VISIBLE);
        }

        setData();

        initTabLayout();

        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state, int verticalOffset) {
                if (state == State.EXPANDED) {
                    //展开状态
                    fenxiang.setTextColor(getResources().getColor(R.color.white));
                    toolbarBackImg.setImageResource(R.mipmap.my_back);
                    xiugai.setImageResource(R.mipmap.my_bj);
                    more.setImageResource(R.mipmap.my_more);

                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    fenxiang.setTextColor(getResources().getColor(R.color.black));
                    toolbarBackImg.setImageResource(R.mipmap.fh);
                    xiugai.setImageResource(R.mipmap.bj);
                    more.setImageResource(R.mipmap.gd);

                } else {
                    //中间状态

                    if (Math.abs(verticalOffset) >= (appBarLayout.getTotalScrollRange() / 3 * 2)) {
                        fenxiang.setTextColor(getResources().getColor(R.color.black));
                        toolbarBackImg.setImageResource(R.mipmap.fh);
                        xiugai.setImageResource(R.mipmap.bj);
                        more.setImageResource(R.mipmap.gd);
                    } else {
                        fenxiang.setTextColor(getResources().getColor(R.color.white));
                        toolbarBackImg.setImageResource(R.mipmap.my_back);
                        xiugai.setImageResource(R.mipmap.my_bj);
                        more.setImageResource(R.mipmap.my_more);
                    }
                }
            }
        });
    }

    //获取数据
    private void setData() {
        RxUtils.loading(commonModel.getPersonalCabter(UserManager.getUser().getUserId() + "", fromId), this)
                .subscribe(new ErrorHandleSubscriber<MyPersonalCebterTwoBean>(mErrorHandler) {
                    @Override
                    public void onNext(MyPersonalCebterTwoBean myPersonalCebterBean) {
                        data = myPersonalCebterBean.getData();
                        if (TextUtils.isEmpty(data.getRoomInfo().getRoom_name())) {
                            suozai.setVisibility(View.INVISIBLE);
                        } else {
                            suozai.setVisibility(View.VISIBLE);
                        }

                        //为轮播图加载数据
                        loadBanner(myPersonalCebterBean);

                        MyPersonalCebterTwoBean.DataBean.UserInfoBean userInfo = data.getUserInfo();
                        follow = userInfo.getIs_follow();
                        user_id = userInfo.getId();

                        //昵称
                        personalName.setText(userInfo.getNickname());

                        //性别
                        if (userInfo.getSex() == 1) {
                            personalSex.setImageResource(R.mipmap.gender_boy);
                        } else {
                            personalSex.setImageResource(R.mipmap.gender_girl);
                        }

                        //第一个等级图标
                        if (!TextUtils.isEmpty(userInfo.getGold_img())) {
                            loadImage(personalVipOne, userInfo.getGold_img(), R.mipmap.cf_00);
                        } else {
                            personalVipOne.setVisibility(View.GONE);
                        }

                        //第二个等级图标
                        if (!TextUtils.isEmpty(userInfo.getStar_img())) {
                            loadImage(personalVipTwo, userInfo.getStar_img(), R.mipmap.ml_00);
                        } else {
                            personalVipTwo.setVisibility(View.GONE);
                        }
                        //第三个等级图标
                        if (!TextUtils.isEmpty(userInfo.getVip_img())) {
                            loadImage(personalVipThree, userInfo.getVip_img(), R.drawable.vip_0);
                        } else {
                            personalVipThree.setVisibility(View.GONE);
                        }

                        //粉丝数
                        fensiText.setText("粉丝：" + userInfo.getFans_num() + "");
                        //关注
                        if (userInfo.getIs_follow() == 1) {
                            gzText.setText("已关注");
                        } else {
                            gzText.setText("关注");
                        }

                        tabList = new ArrayList<>();
                        fragmentList = new ArrayList<>();

                        tabList.add("资料");
                        tabList.add("礼物");
                        tabList.add("动态");
                        if (myPersonalCebterBean.getData().getSkilllist().size() != 0) {
                            tabList.add("技能");
                        }

                        MyInformationFragment mif = new MyInformationFragment();
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("fromId", fromId);
                        bundle1.putParcelable("userinfo", userInfo);
                        bundle1.putParcelableArrayList("rongyu", (ArrayList<? extends Parcelable>) myPersonalCebterBean.getData().getGlory());
                        bundle1.putParcelableArrayList("cp", (ArrayList<? extends Parcelable>) myPersonalCebterBean.getData().getCplist());
                        mif.setArguments(bundle1);

                        MyGiftFragment myGiftFragment = new MyGiftFragment();
                        List<MyPersonalCebterTwoBean.DataBean.GiftsBean> gifts = data.getGifts();
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("gifts", (ArrayList<? extends Parcelable>) gifts);
                        myGiftFragment.setArguments(bundle);

                        MyDongTaiFragment myDongTaiFragment = new MyDongTaiFragment();
                        Bundle bundlee = new Bundle();
                        if ("".equals(fromId)) {
                            bundlee.putString("idd", UserManager.getUser().getUserId() + "");
                            bundlee.putString("tag", "0");
                        } else {
                            bundlee.putString("idd", fromId + "");
                            bundlee.putString("tag", "1");
                        }
                        myDongTaiFragment.setArguments(bundlee);

                        if (myPersonalCebterBean.getData().getSkilllist().size() != 0) {
                            sf = new SkillFragment();
                            List<MyPersonalCebterTwoBean.DataBean.SkilllistBean> skillList = myPersonalCebterBean.getData().getSkilllist();
                            Bundle sfBundle = new Bundle();
                            sfBundle.putParcelableArrayList("skillList", (ArrayList<? extends Parcelable>) skillList);
                            sf.setArguments(sfBundle);
                        }


                        fragmentList.add(mif);
                        fragmentList.add(myGiftFragment);
                        fragmentList.add(myDongTaiFragment);

                        if (myPersonalCebterBean.getData().getSkilllist().size() != 0) {
                            fragmentList.add(sf);
                        }

                        myPagerAdapter = new MyPagerAdapterTwo(getSupportFragmentManager(), fragmentList, tabList);
                        viewpagerPersonal.setAdapter(myPagerAdapter);
                        tabsPromotlist.setupWithViewPager(viewpagerPersonal);

                        viewpagerPersonal.setCurrentItem(0);
                        tabsPromotlist.getTabAt(0).select();

                    }
                });
    }

    private void initTabLayout() {
        tabsPromotlist.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                initUpData(tab, true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                initUpData(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initUpData(TabLayout.Tab tab, boolean boo) {
        TextView textView = (TextView) LayoutInflater.from(MyPersonalCenterTwoActivity.this).inflate(R.layout.hei_title_text_layout, null);
        if (boo) {
            textView.setSelected(true);
            textView.setText(tab.getText());
            tab.setCustomView(textView);
        } else {
            tab.setCustomView(null);
        }
    }

    //关注
    private void setFollow() {
        RxUtils.loading(commonModel.follow(String.valueOf(UserManager.getUser().getUserId()), user_id + ""), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        follow = 1;
                        gzText.setText("已关注");
                    }
                });
    }

    //取消关注
    private void setTakeOff() {
        RxUtils.loading(commonModel.cancel_follow(String.valueOf(UserManager.getUser().getUserId()), user_id + ""), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        follow = 0;
                        gzText.setText("关注");
                    }
                });
    }

    /**
     * 初始化顶部Banner图
     */
    public void loadBanner(MyPersonalCebterTwoBean myPersonalCebterTwoBean) {
        List<String> mBanner = new ArrayList<>();
        List<MyPersonalCebterTwoBean.DataBean.ImgList> imglist = myPersonalCebterTwoBean.getData().getImglist();
        for (MyPersonalCebterTwoBean.DataBean.ImgList bannerImg : imglist) {
            mBanner.add(bannerImg.getImg());
        }
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(mBanner);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);

        banner.setOnBannerListener(position -> {
            FullScreenUtil.showFullScreenDialog(this, position, mBanner);
        });

        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        banner.start();
    }

    @OnClick({R.id.suozai, R.id.xiugai, R.id.more, R.id.fenxiang, R.id.guanzhu, R.id.liaotian})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.suozai:
                enterData(String.valueOf(data.getRoomInfo().getUid()), "", commonModel, 1, data.getRoomInfo().getRoom_cover());
                break;
            case R.id.xiugai:
                ArmsUtils.startActivity(ModifyDataActivity.class);
                break;
            case R.id.more:
                PersonalTwoPopul roomTopWindow = new PersonalTwoPopul(this);
                roomTopWindow.showAtLocation(aaaaaa, Gravity.BOTTOM, 0, 0);
                roomTopWindow.getLlClose().setOnClickListener(v -> {
                    roomTopWindow.dismiss();
                });
                roomTopWindow.getLlJubao().setOnClickListener(v -> {//举报
                    roomTopWindow.dismiss();
                    Intent intent = new Intent(MyPersonalCenterTwoActivity.this, ReportActivity.class);
                    intent.putExtra("type", "1");
                    intent.putExtra("targetId", String.valueOf(data.getUserInfo().getId()));
                    ArmsUtils.startActivity(intent);
                });
                roomTopWindow.getLlShare().setOnClickListener(v -> {
                    roomTopWindow.dismiss();
                    fenxiang();
                });
                break;
            case R.id.fenxiang:
                fenxiang();
                break;
            case R.id.guanzhu:
                if (follow == 1) {
                    setTakeOff();
                } else {
                    setFollow();
                }
                break;

            case R.id.liaotian:
                RongIM.getInstance().startConversation(this, Conversation.ConversationType.PRIVATE,
                        data.getUserInfo().getRy_uid(),
                        data.getUserInfo().getNickname());
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (AdminHomeActivity.isStart && AdminHomeActivity.isTop) {
            startActivity(new Intent(this, AdminHomeActivity.class));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
        if (XIUGAIGERENZILIAOCHENGGONG.equals(tag)) {
            setData();
        } else if (JIECHUCP.equals(tag)) {
            setData();
        } else if (KAIQICPWEI.equals(tag)) {
            setData();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (isRoom) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                startActivity(new Intent(this, AdminHomeActivity.class));
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void fenxiang() {
        UMWeb web = new UMWeb("https://www.jiyinapp.cn");
        web.setTitle("积音语音");//标题
        web.setDescription("快来加入积音语音直播啦！");//描述

        ShareBoardConfig config = new ShareBoardConfig();//新建ShareBoardConfig
        config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_BOTTOM);//设置位置
//                    config.setMenuItemBackgroundShape(getResources().getColor(R.drawable.shape_home_round));
        config.setCancelButtonVisibility(true);
        config.setTitleText("分享至");
        config.setTitleTextColor(getResources().getColor(R.color.font_333333));
        config.setMenuItemTextColor(getResources().getColor(R.color.font_333333));
        config.setIndicatorVisibility(false);
        config.setCancelButtonVisibility(false);
        config.setShareboardBackgroundColor(getResources().getColor(R.color.white));

        new ShareAction(MyPersonalCenterTwoActivity.this)
                .withMedia(web)
                .setDisplayList(SHARE_MEDIA.WEIXIN,
                        SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
                .setCallback(RoomHelper.getUMShareListener(this,commonModel, MyPersonalCenterTwoActivity.this,mErrorHandler))
                .open(config);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
