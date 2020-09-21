package com.qutu.talk.fragment;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.ImmersionOwner;
import com.gyf.immersionbar.components.ImmersionProxy;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.qutu.talk.R;
import com.qutu.talk.activity.SearchHisActivity;
import com.qutu.talk.activity.dashen.DaShenList2Activity;
import com.qutu.talk.activity.mine.RealNameActivity;
import com.qutu.talk.activity.room.AllRoomListActivity;
import com.qutu.talk.activity.room.CollectionRoomListActivity;
import com.qutu.talk.adapter.MyPagerAdapter;
import com.qutu.talk.adapter.dashen.MainHomePageSkillAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.BaseWebActivity;
import com.qutu.talk.base.HeaderViewPagerFragment;
import com.qutu.talk.base.MyBaseArmFragment;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.BannerBean;
import com.qutu.talk.bean.UserBean;
import com.qutu.talk.bean.dashen.MainHomePageSkillBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.MediaManager;
import com.qutu.talk.utils.mytablayout.TabLayout;
import com.qutu.talk.view.GlideImageLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 首页
 * 陪玩
 */
public class MainPeiwanPageFragment extends MyBaseArmFragment implements ImmersionOwner {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.sousuo)
    LinearLayout sousuo;
    @BindView(R.id.shoucang_room)
    ImageView shoucangRoom;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.card_banner)
    CardView cardBanner;
    @BindView(R.id.homepage_game_recyc)
    RecyclerView homepageGameRecyc;
    @BindView(R.id.top_o)
    LinearLayout topO;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.top)
    RelativeLayout top;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.view_main_bar)
    View viewMainBar;
    @BindView(R.id.ll_all)
    View ll_all;
    @BindView(R.id.imgSearch)
    ImageView imgSearch;
    @BindView(R.id.iv_search)
    ImageView iv_search;
    @BindView(R.id.iv_room)
    View iv_room;
    private MainHomePageSkillAdapter mSkillAdapter;
    private MyPagerAdapter mPagerAdapter;

    private List<String> tabList;
    private List<HeaderViewPagerFragment> mFragments;
    private List<MainHomePageSkillBean.DataBean> skillBeanData;


    @Override
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_peiwan);
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initTabLayout();
        loadUserData();
        iv_room.setOnClickListener(v->{
            if (mUserBean == null) {
                return;
            }
            if (mUserBean.getData().getIs_idcard() == 0) {
                ArmsUtils.startActivity(RealNameActivity.class);
            } else {
                enterData(String.valueOf(UserManager.getUser().getUserId()), "", commonModel, 1, mUserBean.getData().getHeadimgurl());
            }
        });

        mSkillAdapter = new MainHomePageSkillAdapter();
        homepageGameRecyc.setLayoutManager(new GridLayoutManager(mContext,5));
        homepageGameRecyc.setAdapter(mSkillAdapter);

        loadBanner();
        loadSkillList();


        //设置viewpager的页面刷新
        refreshLayout.setOnRefreshListener(refreshlayout -> {
            if (viewPager != null && mFragments != null && mFragments.size() > 0) {
//                if (viewPager.getCurrentItem() == 0) {
                ((RecomHomePageFragment) mFragments.get(viewPager.getCurrentItem())).onRefresh(refreshLayout);
//                } else {
//                    ((RecomHomePageFragment) mFragments.get(viewPager.getCurrentItem())).onRefresh(refreshLayout);
//                }
            }
        });

        mSkillAdapter.setOnItemClickListener((adapter, view, position) -> {
            MainHomePageSkillBean.DataBean dataBean = mSkillAdapter.getData().get(position);
            Intent intent = new Intent(getActivity(), DaShenList2Activity.class);
            intent.putExtra("id", String.valueOf(dataBean.getId()));
            intent.putExtra("name", dataBean.getName());
            intent.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) skillBeanData);
            ArmsUtils.startActivity(intent);
        });

        sousuo.setOnClickListener(v -> {
            ArmsUtils.startActivity(SearchHisActivity.class);
        });
        iv_search.setOnClickListener(v -> {
            ArmsUtils.startActivity(SearchHisActivity.class);
        });

        ll_all.setOnClickListener(v -> {
            ArmsUtils.startActivity(AllRoomListActivity.class);
        });
        shoucangRoom.setOnClickListener(v -> {
            ArmsUtils.startActivity(CollectionRoomListActivity.class);
        });
    }
    private UserBean mUserBean;

    /**
     * 用户信息
     */
    private void loadUserData() {
        RxUtils.loading(commonModel.get_user_info(String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<UserBean>(mErrorHandler) {


                    @Override
                    public void onNext(UserBean userBean) {
                        mUserBean = userBean;
                    }
                });
    }
    @Override
    public void setData(@Nullable Object data) {

    }

    /**
     * 初始化顶部Banner图
     */
    public void loadBanner() {
        RxUtils.loading(commonModel.carousel("2"), this)
                .subscribe(new ErrorHandleSubscriber<BannerBean>(mErrorHandler) {
                    @Override
                    public void onNext(BannerBean bannerBean) {
                        List<String> imgurls = new ArrayList<>();
                        List<BannerBean.DataBean> data = bannerBean.getData();
                        for (BannerBean.DataBean list : data) {
                            imgurls.add(list.getImg());
                        }
                        //设置图片加载器
                        banner.setImageLoader(new GlideImageLoader());
                        //设置图片集合
                        banner.setImages(imgurls);
                        //设置指示器位置（当banner模式中有指示器时）
                        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                        banner.setIndicatorGravity(BannerConfig.CENTER);
                        //设置自动轮播，默认为true
                        banner.isAutoPlay(true);
                        banner.setOnBannerListener(position -> {
                            Intent intent = new Intent(getActivity(), BaseWebActivity.class);
                            intent.putExtra("url", bannerBean.getData().get(position).url);
                            intent.putExtra("name", "");
                            ArmsUtils.startActivity(intent);
                        });
                        //banner设置方法全部调用完毕时最后调用
                        banner.start();
                    }
                });
    }

    /**
     * 获取技能列表数据
     */
    private void loadSkillList() {
        RxUtils.loading(commonModel.getAllSkillsLists(), this)
                .subscribe(new ErrorHandleSubscriber<MainHomePageSkillBean>(mErrorHandler) {
                    @Override
                    public void onNext(MainHomePageSkillBean mainHomePageSkillBean) {
                        skillBeanData = mainHomePageSkillBean.getData();

                        List<MainHomePageSkillBean.DataBean> temp4 = new ArrayList<>();
                        for (int i = 0; i < skillBeanData.size(); i++) {
                            if (i < 4) {
                                temp4.add(skillBeanData.get(i));
                            }
                        }
                        mSkillAdapter.setNewData(temp4);

                        mFragments = new ArrayList<>();
                        tabList = new ArrayList<>();
                        List<MainHomePageSkillBean.DataBean> data = skillBeanData;
                        tabList.add("推荐");
                    //                        for (MainHomePageSkillBean.DataBean list : data) {
                    //                            tabList.add(list.getName());
                    //                        }
                        //推荐的
                        mFragments.add(RecomHomePageFragment.getInstance(1, "0"));
//                        for (int i = 0; i < data.size(); i++) {
//                            mFragments.add(RecomHomePageFragment.getInstance(1, String.valueOf(data.get(i).getId())));
//                        }
                        for (int i = 0; i < mFragments.size(); i++) {
                            if (i == 0) {
                                RecomHomePageFragment recomHomeFragment = (RecomHomePageFragment) mFragments.get(i);
                                recomHomeFragment.setRefersh(refreshLayout);
                            } else {
                                RecomHomePageFragment recomHomeFragment = (RecomHomePageFragment) mFragments.get(i);
                                recomHomeFragment.setRefersh(refreshLayout);
                            }

                        }
                        mPagerAdapter = new MyPagerAdapter(getChildFragmentManager(), mFragments, tabList);
                        LogUtils.debugInfo("====数据1", mFragments.size() + "");
                        LogUtils.debugInfo("====数据2", tabList.size() + "");
                        viewPager.setAdapter(mPagerAdapter);
                        tabLayout.setupWithViewPager(viewPager);
//                        tab_layout.setViewPager(view_pager);
//                        tab_layout.setTextBold(tag);
//                        tab_layout.setCurrentTab(tag);
//                        tab_layout.setSnapOnTabClick(true);
                        viewPager.setCurrentItem(0);
                        tabLayout.getTabAt(0).select();
                        viewPager.setOffscreenPageLimit(mFragments.size());
//                        //滑动监听  切换数据
//                        scrollableLayout.setCurrentScrollableContainer(mFragments.get(0));
//                        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//                            @Override
//                            public void onPageSelected(int position) {
//                                if (position == 0) {
//                                    refreshLayout.setEnableLoadMore(false);
//                                    ((RecomHomePageFragment) mFragments.get(position)).setDisableLoadMore(true);
//                                } else {
//                                    refreshLayout.setEnableLoadMore(true);
//                                    ((RecomHomePageFragment) mFragments.get(position)).setDisableLoadMore(false);
//                                }
//                                scrollableLayout.setCurrentScrollableContainer(mFragments.get(position));
//                            }
//                        });
                    }
                });
    }


    /**
     * ImmersionBar代理类
     */
    private ImmersionProxy mImmersionProxy = new ImmersionProxy(this);

    @Override
    public void onLazyBeforeView() {

    }

    @Override
    public void onLazyAfterView() {

    }

    @Override
    public void onVisible() {

    }

    @Override
    public void onInvisible() {

    }

    @Override
    public void initImmersionBar() {

        ImmersionBar.with(this)
                .statusBarColor(R.color.white)
                .statusBarView(viewMainBar)
                .autoStatusBarDarkModeEnable(true, 0.2f) //自动状态栏字体变色，必须指定状态栏颜色才可以自动变色哦
                .init();//设置状态栏白色

    }

    @Override
    public void onResume() {
        super.onResume();
        mImmersionProxy.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //取消在播放的语音
        MediaManager.pause();
        mImmersionProxy.onPause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mImmersionProxy.onDestroy();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mImmersionProxy.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImmersionProxy.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mImmersionProxy.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mImmersionProxy.onHiddenChanged(hidden);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mImmersionProxy.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean immersionBarEnabled() {//是否用沉浸式
        return true;
    }

    private void initTabLayout() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.hei_title_text_layout, null);
        if (boo) {
            textView.setSelected(true);
            textView.setText(tab.getText());
            tab.setCustomView(textView);
        } else {
            tab.setCustomView(null);
        }
    }

}
