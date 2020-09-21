package com.qutu.talk.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.ImmersionOwner;
import com.gyf.immersionbar.components.ImmersionProxy;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.activity.MainActivity;
import com.qutu.talk.activity.SearchHisActivity;
import com.qutu.talk.activity.mine.RealNameActivity;
import com.qutu.talk.activity.room.GxRankActivity;
import com.qutu.talk.activity.room.RankActivity;
import com.qutu.talk.adapter.MyPagerAdapter;
import com.qutu.talk.adapter.dashen.MainHomePageSkillAdapter;
import com.qutu.talk.adapter.dashen.Shengyou1Adapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.app.view.CircularImage;
import com.qutu.talk.base.BaseWebActivity;
import com.qutu.talk.base.HeaderViewPagerFragment;
import com.qutu.talk.base.MyBaseArmFragment;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.BannerBean;
import com.qutu.talk.bean.Rank;
import com.qutu.talk.bean.RoomTypeResult;
import com.qutu.talk.bean.Shengyou;
import com.qutu.talk.bean.UserBean;
import com.qutu.talk.bean.dashen.MainHomePageSkillBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.mytablayout.TabLayout;
import com.qutu.talk.view.GlideImageLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 作者:sgm
 * 描述:首页
 */
public class MainYulePageFragment extends MyBaseArmFragment implements ImmersionOwner {

    @Inject
    CommonModel commonModel;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    @BindView(R.id.view_pager)
    ViewPager view_pager;
    //    @BindView(R.id.scrollableLayout)
//    HeaderViewPager scrollableLayout;
    @BindView(R.id.imgSearch)
    ImageView imgSearch;
//    @BindView(R.id.shoucang_room)
//    ImageView shoucangRoom;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.sousuo)
    LinearLayout sousuo;
    @BindView(R.id.view_main_bar)
    public View viewMainBar;
    @BindView(R.id.tou_3)
    CircularImage tou_3;
    @BindView(R.id.tou_2)
    CircularImage tou_2;
    @BindView(R.id.img1)
    CircularImage img_1;
    @BindView(R.id.head_image_kuang_1)
    ImageView headImageKuang_1;
    @BindView(R.id.tou1)
    ConstraintLayout tou1;
    @BindView(R.id.tou_4)
    CircularImage tou_4;
    @BindView(R.id.tou_5)
    CircularImage tou_5;
    @BindView(R.id.img2)
    CircularImage img2;
    @BindView(R.id.head_image_kuang_2)
    ImageView headImageKuang2;
    @BindView(R.id.tou2)
    ConstraintLayout tou2;
    @BindView(R.id.top_o)
    LinearLayout topO;
    @BindView(R.id.one_rank)
    LinearLayout oneRank;
    @BindView(R.id.two_rank)
    LinearLayout twoRank;
    Unbinder unbinder;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.homepage_game_recyc)
    RecyclerView homepageGameRecyc;
    private List<String> titleList;
    private List<HeaderViewPagerFragment> mFragments;
    private MyPagerAdapter mAdapter;
    private int tag = 0;
    @BindView(R.id.iv_room)
    View iv_room;
    MainActivity mMainActivity;
    private Shengyou1Adapter mSkillAdapter;

//    private static List<String> AAA = new ArrayList<>(); //tab数据源
//    private ExamplePagerAdapter mExamplePagerAdapter; //适配器

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mMainActivity = (MainActivity) context;
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
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_yule);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
//        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.white), 0);
        mSkillAdapter = new Shengyou1Adapter();
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
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        homepageGameRecyc.setLayoutManager(llm);
        homepageGameRecyc.setAdapter(mSkillAdapter);


        //设置viewpager的页面刷新
        refreshLayout.setOnLoadMoreListener(refreshlayout -> {
//            oneRank();
//            twoRank();
            if (view_pager != null && mFragments != null && mFragments.size() > 0) {
//                if (view_pager.getCurrentItem() == 0) {
//                    ((CollectionHomeFragment) mFragments.get(view_pager.getCurrentItem())).onLoadMore(refreshLayout);
//                }else
                    if (view_pager.getCurrentItem() == 0) {
                    ((YuleFragment) mFragments.get(view_pager.getCurrentItem())).onLoadMore(refreshLayout);
                } else {

                    ((YuleFragment) mFragments.get(view_pager.getCurrentItem())).onLoadMore(refreshLayout);
                }
            }

        });
        refreshLayout.setOnRefreshListener(refreshlayout -> {
            if (view_pager != null && mFragments != null && mFragments.size() > 0) {
                ((YuleFragment) mFragments.get(view_pager.getCurrentItem())).onRefresh(refreshLayout);
//                if (view_pager.getCurrentItem() == 0) {
//                    ((YuleFragment) mFragments.get(view_pager.getCurrentItem())).onRefresh(refreshLayout);
//                }else if (view_pager.getCurrentItem() == 1) {
//                    ((YuleFragment) mFragments.get(view_pager.getCurrentItem())).onRefresh(refreshLayout);
//                } else {
//                    ((YuleFragment) mFragments.get(view_pager.getCurrentItem())).onRefresh(refreshLayout);
//                }
            }
        });
        refreshLayout.setEnableLoadMore(false);
        loadBanner();
        loadRoomType();
//        loadCategory();
        imgSearch.setOnClickListener(v -> {
            ArmsUtils.startActivity(RankActivity.class);
//            getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        });

        ivSearch.setOnClickListener(v -> {
            ArmsUtils.startActivity(SearchHisActivity.class);
        });

        loadSkillList();
        initTabLayout();
    }

    /**
     * 获取技能列表数据
     */
    private void loadSkillList() {
        RxUtils.loading(commonModel.shengyou(), this)
                .subscribe(new ErrorHandleSubscriber<Shengyou>(mErrorHandler) {
                    @Override
                    public void onNext(Shengyou mainHomePageSkillBean) {
                        mSkillAdapter.setNewData(mainHomePageSkillBean.getData());
                    }
                });
    }
    @Override
    public void setData(@Nullable Object data) {

    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if(!hidden){
//            StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.white), 0);
//        }
//    }

    /**
     * 初始化顶部Banner图
     */
    public void loadBanner() {
        RxUtils.loading(commonModel.carousel("1"), this)
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
     * 房间类型
     */
    private void loadRoomType() {
        RxUtils.loading(commonModel.room_type_three(), this)
                .subscribe(new ErrorHandleSubscriber<RoomTypeResult>(mErrorHandler) {
                    @Override
                    public void onNext(RoomTypeResult todayRecommBean) {


                        mFragments = new ArrayList<>();
                        titleList = new ArrayList<>();
                        List<RoomTypeResult.DataBean> data = todayRecommBean.getData();
//                        titleList.add("收藏");
                        titleList.add("热门");
                        for (RoomTypeResult.DataBean list : data) {
                            titleList.add(list.getName());
                        }
//                        //收藏的
//                        mFragments.add(CollectionHomeFragment.getInstance());
                        //推荐的
                        mFragments.add(YuleFragment.getInstance(0, todayRecommBean));
                        for (int i = 0; i < data.size(); i++) {
                            mFragments.add(YuleFragment.getInstance(Integer.parseInt(data.get(i).getId()), todayRecommBean));
                        }
                        for (int i = 0; i < mFragments.size(); i++) {
//                            if (i == 0) {
//                                CollectionHomeFragment collectionHomeFragment = (CollectionHomeFragment) mFragments.get(i);
//                                collectionHomeFragment.setRefersh(refreshLayout);
//                            }else
//                                if(i==0){
//                                RecomHomeFragment recomHomeFragment = (RecomHomeFragment) mFragments.get(i);
//                                recomHomeFragment.setRefersh(refreshLayout);
//                            } else {
//                                RecomFragment recomHomeFragment = (RecomFragment) mFragments.get(i);
//                                recomHomeFragment.setRefersh(refreshLayout);
//                            }
                            YuleFragment recomHomeFragment = (YuleFragment) mFragments.get(i);
                            recomHomeFragment.setRefersh(refreshLayout);
                        }
                        mAdapter = new MyPagerAdapter(getChildFragmentManager(), mFragments, titleList);
//                        LogUtils.debugInfo("====数据1", mFragments.size() + "");
//                        LogUtils.debugInfo("====数据2", titleList.size() + "");
                        view_pager.setAdapter(mAdapter);
                        tab_layout.setupWithViewPager(view_pager);
//                        tab_layout.setViewPager(view_pager);
//                        tab_layout.setTextBold(tag);
//                        tab_layout.setCurrentTab(tag);
//                        tab_layout.setSnapOnTabClick(true);
                        view_pager.setCurrentItem(0);
                        tab_layout.getTabAt(0).select();
                        view_pager.setOffscreenPageLimit(mFragments.size());
//                        //滑动监听  切换数据
//                        scrollableLayout.setCurrentScrollableContainer(mFragments.get(0));
                        view_pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                            @Override
                            public void onPageSelected(int position) {
//                                if (position == 0) {
//                                    refreshLayout.setEnableLoadMore(false);
//                                    ((CollectionHomeFragment) mFragments.get(position)).setDisableLoadMore(true);
//                                }else
                                    if (position == 0) {
                                    refreshLayout.setEnableLoadMore(false);
                                    ((YuleFragment) mFragments.get(position)).setDisableLoadMore(true);
                                } else {
                                    refreshLayout.setEnableLoadMore(true);
                                    ((YuleFragment) mFragments.get(position)).setDisableLoadMore(false);
                                }
//                                scrollableLayout.setCurrentScrollableContainer(mFragments.get(position));
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                    }
                });
    }


    /**
     * 类别
     */
//    private void loadCategory() {
//        RxUtils.loading(commonModel.room_categories(), this)
//                .subscribe(new ErrorHandleSubscriber<CategorRoomBean>(mErrorHandler) {
//                    @Override
//                    public void onNext(CategorRoomBean categorRoomBean) {
//                        mFragments = new ArrayList<>();
//                        titleList = new ArrayList<>();
//                        List<CategorRoomBean.DataBean> data = categorRoomBean.getData();
//                        titleList.add("热门");
//                        for (CategorRoomBean.DataBean list : data) {
//                            titleList.add(list.getName());
//                        }
//                        //推荐的
//                        mFragments.add(RecomHomeFragment.getInstance(0, categorRoomBean));
//                        for (int i = 0; i < data.size(); i++) {
//                            mFragments.add(RecomFragment.getInstance(data.get(i).getId(), categorRoomBean));
//                        }
//                        mAdapter = new MyPagerAdapter(getChildFragmentManager(), mFragments, titleList);
//
//                        view_pager.setAdapter(mAdapter);
//                        tab_layout.setViewPager(view_pager);
//                        tab_layout.setTextBold(tag);
//                        tab_layout.setCurrentTab(tag);
//                        tab_layout.setSnapOnTabClick(true);
//                        view_pager.setOffscreenPageLimit(mFragments.size());
////                        //滑动监听  切换数据
////                        scrollableLayout.setCurrentScrollableContainer(mFragments.get(0));
//                        view_pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//                            @Override
//                            public void onPageSelected(int position) {
//                                if (position == 0) {
//                                    refreshLayout.setEnableLoadMore(false);
//                                    ((RecomHomeFragment) mFragments.get(position)).setDisableLoadMore(true);
//                                } else {
//                                    refreshLayout.setEnableLoadMore(true);
//                                    ((RecomFragment) mFragments.get(position)).setDisableLoadMore(false);
//                                }
////                                scrollableLayout.setCurrentScrollableContainer(mFragments.get(position));
//                            }
//                        });
////                        loadRecommentData();
//                    }
//                });
//    }


    /**
     * 热门，房间
     */
//    private void loadRecommentData() {
    //热门推荐
//        RxUtils.loading(commonModel.is_popular(), this)
//                .subscribe(new ErrorHandleSubscriber<RecommenRoomBean>(mErrorHandler) {
//                    @Override
//                    public void onNext(RecommenRoomBean categorRoomBean) {
//                        RecomFragment recomFragment = (RecomFragment) mFragments.get(0);
//                        recomFragment.setPopularData(categorRoomBean);
//                    }
//                });
//        //密聊推荐
//        RxUtils.loading(commonModel.secret_chat(), this)
//                .subscribe(new ErrorHandleSubscriber<RecommenRoomBean>(mErrorHandler) {
//                    @Override
//                    public void onNext(RecommenRoomBean categorRoomBean) {
//                        RecomFragment recomFragment = (RecomFragment) mFragments.get(0);
//                        recomFragment.setSecretData(categorRoomBean);
//                    }
//                });
//    }
//    @OnClick({R.id.one_rank, R.id.two_rank})
//    public void onClick(View view) {
//        switch (view.getId()) {
////            case R.id.llGift:
////                if (mPushBean != null) {
////                    enterData(mPushBean.getUid() + "", "", commonModel, 1);
////                    llGift.setVisibility(View.GONE);
////                }
////                break;
//            case R.id.one_rank:
//                Intent intent = new Intent(getActivity(), RankActivity.class);
//                intent.putExtra("type", 1);
//                startActivity(intent);
//                break;
//            case R.id.two_rank:
//                Intent intent1 = new Intent(getActivity(), GxRankActivity.class);
//                startActivity(intent1);
//                break;
//        }
//
//    }


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
        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
//        TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.hei_title_text_layout, null);
//        if (boo) {
//            textView.setSelected(true);
//            textView.setText(tab.getText());
//            tab.setCustomView(textView);
//        } else {
//            tab.setCustomView(null);
//        }
    }

//    /**
//     * 两个排行榜
//     */
//    private void oneRank() {
//        RxUtils.loading(commonModel.leaderboard("1", "1", "1"), this)
//                .subscribe(new ErrorHandleSubscriber<Rank>(mErrorHandler) {
//                    @Override
//                    public void onNext(Rank rank) {
//                        List<Rank.DataBean.TopBean> topList = rank.getData().getTop();
//
//                        if (TextUtils.isEmpty(topList.get(0).getNickname())) {
//                            rank1_head1.setImageResource(R.mipmap.no_tou);
//                        } else {
//                            loadImage(rank1_head1, topList.get(0).getHeadimgurl(), R.mipmap.no_tou);
//                        }
//
//                        if (TextUtils.isEmpty(topList.get(1).getNickname())) {
//                            rank1_head2.setImageResource(R.mipmap.no_tou);
//                        } else {
//                            loadImage(rank1_head2, topList.get(1).getHeadimgurl(), R.mipmap.no_tou);
//                        }
//
//                        if (TextUtils.isEmpty(topList.get(2).getNickname())) {
//                            tou_3.setImageResource(R.mipmap.no_tou);
//                        } else {
//                            loadImage(tou_3, topList.get(2).getHeadimgurl(), R.mipmap.no_tou);
//                        }
//                    }
//                });
//    }
//
//    private void twoRank() {
//        RxUtils.loading(commonModel.getAllFamilyGxRank("1"), this)
//                .subscribe(new ErrorHandleSubscriber<Rank>(mErrorHandler) {
//                    @Override
//                    public void onNext(Rank rank) {
//                        List<Rank.DataBean.TopBean> topList = rank.getData().getTop();
//
//                        if (TextUtils.isEmpty(topList.get(0).getNickname())) {
//                            img2.setImageResource(R.mipmap.no_tou);
//                        } else {
//                            loadImage(img2, topList.get(0).getHeadimgurl(), R.mipmap.no_tou);
//                        }
//
//                        if (TextUtils.isEmpty(topList.get(1).getNickname())) {
//                            tou_4.setImageResource(R.mipmap.no_tou);
//                        } else {
//                            loadImage(tou_4, topList.get(1).getHeadimgurl(), R.mipmap.no_tou);
//                        }
//
//                        if (TextUtils.isEmpty(topList.get(2).getNickname())) {
//                            tou_5.setImageResource(R.mipmap.no_tou);
//                        } else {
//                            loadImage(tou_5, topList.get(2).getHeadimgurl(), R.mipmap.no_tou);
//                        }
//
//                    }
//                });
//    }
}
