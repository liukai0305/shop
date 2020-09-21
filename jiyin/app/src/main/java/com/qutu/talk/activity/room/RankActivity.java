package com.qutu.talk.activity.room;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.LogUtils;
import com.lzy.widget.HeaderViewPager;
import com.qutu.talk.Interface.MyPackBaoShiInter;
import com.qutu.talk.R;
import com.qutu.talk.adapter.RankPagerAdapter;
import com.qutu.talk.app.view.CircularImage;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.Rank;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.fragment.CardFragment;
import com.qutu.talk.fragment.GemFragment;
import com.qutu.talk.fragment.GemstoneFragment;
import com.qutu.talk.fragment.PresentFragment;
import com.qutu.talk.fragment.RankFragment;
import com.qutu.talk.fragment.RankJiedanFragment;
import com.qutu.talk.utils.MyUtil;
import com.qutu.talk.utils.StatusBarUtils;
import com.qutu.talk.view.HXLinePagerIndicator;
import com.qutu.talk.view.HXLinePagerIndicatorTwo;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.scwang.smartrefresh.layout.util.SmartUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.qutu.talk.utils.Constant.QIEHUAN;
import static com.umeng.socialize.utils.ContextUtil.getContext;

/**
 * 排行榜
 * implements MyPackBaoShiInter.onRankInter
 */
public class RankActivity extends MyBaseArmActivity implements MyPackBaoShiInter.onRankInter {

    //    @BindView(R.id.tab_layout)
//    MagicIndicator tabLayout;
    //    @BindView(R.id.view_pager)
//    ViewPager viewPager;
//    @BindView(R.id.imgBack)
//    ImageView imgBack;
    //    @BindView(R.id.lin)
//    LinearLayout lin;
//    @BindView(R.id.scrollableLayout)
//    HeaderViewPager scrollableLayout;
//    @BindView(R.id.shuo_ming)
//    ImageView shuoMing;

    private LinearLayout lin;
    private ViewPager viewPager;
    private MagicIndicator tabLayout;
    private ImageView imgBack, shuoMing;
    public LinearLayout viewTouMing;
    private static String[] AAA = new String[]{"豪气榜", "魅力榜", "接单榜"};
    private ExamplePagerAdapter mExamplePagerAdapter;
    private int mScrollY = 0;
    private int mOffset = 0;
    private int mImgScrollY = 0;
    private int statusBarHeight;
    private int lastScrollY = 0;
    private int lastImgScrollY = 0;
    private int h = SmartUtil.dp2px(48);
    private int imgHeight = 0;//滑动时图片的最大滑动距离
    private int color = 0;
    private int mType;
    private String title;

    RankFragment mRankFragment1, mRankFragment2;
    RankJiedanFragment mRankFragment3;


    int mTitleHeight = 0;

    int mCurrentPage = 0;

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
        return R.layout.activity_rank;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mType = getIntent().getIntExtra("type", 1);
        mCurrentPage = mType;
        title = AAA[mCurrentPage-1];

        lin = findViewById(R.id.lin);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tablayout);
        imgBack = findViewById(R.id.imgBack);
        viewTouMing = findViewById(R.id.view_corner_touming);
        shuoMing = findViewById(R.id.shuo_ming);

        viewTouMing.setFocusableInTouchMode(false);
        statusBarHeight = StatusBarUtils.getStatusBarHeight(this);
        MyUtil.setMargins(lin, 0, statusBarHeight, 0, 0);
        QMUIStatusBarHelper.translucent(this);
        color = ContextCompat.getColor(getApplicationContext(), R.color.color_daa1e1) & 0x00ffffff;

        initFragment();

        mExamplePagerAdapter = new ExamplePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mExamplePagerAdapter);

        tabLayout.setBackgroundColor(Color.TRANSPARENT);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setRightPadding(50);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
//                return AAA == null ? 0 : AAA.length;
                return 1;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);
                View customLayout = LayoutInflater.from(context).inflate(R.layout.title_text_big_layout, null);
                final TextView titleText = customLayout.findViewById(R.id.textView_tit);
                titleText.setText(AAA[mCurrentPage-1]);
                commonPagerTitleView.setContentView(customLayout);
                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {
                    @Override
                    public void onSelected(int index, int totalCount) {
                        mCurrentPage = index;

//                        titleText.setText(AAA[mCurrentPage]);
                        titleText.setTextSize(22);
                        mTitleHeight = mRankFragment1.mTitleLayoutHeight;
//                        switch (index) {
//                            case 0:
//                                break;
//                            case 1:
//
//                                break;
//                        }
                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {
//                        titleText.setText(AAA[mCurrentPage]);
                        titleText.setTextSize(15);
                    }

                    @Override
                    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {

                    }

                    @Override
                    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {

                    }
                });

                commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });

                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                HXLinePagerIndicatorTwo indicator = new HXLinePagerIndicatorTwo(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
//                indicator.setLineHeight(UIUtil.dip2px(context, 3));
                indicator.setLineHeight(UIUtil.dip2px(context, 0));
                indicator.setLineWidth(UIUtil.dip2px(context, 35));
                indicator.setRoundRadius(UIUtil.dip2px(context, 2));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));

                return indicator;
            }
        });
        tabLayout.setNavigator(commonNavigator);

        ViewPagerHelper.bind(tabLayout, viewPager);
        viewPager.setOffscreenPageLimit(2);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                viewTouMing.setVisibility(View.GONE);
                if (i == 0) {
                    mRankFragment1.myList.smoothScrollToPosition(0);
                    lin.setBackgroundColor((0) | color);
                } else if (i == 1) {
                    mRankFragment2.myList.smoothScrollToPosition(0);
                    lin.setBackgroundColor((0) | color);
                } else if (i == 2) {
                    mRankFragment3.myList.smoothScrollToPosition(0);
                    lin.setBackgroundColor((0) | color);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        imgBack.setOnClickListener(v -> {
//            startActivity(new Intent(this, AdminHomeActivity.class));
            overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
            finish();
        });
        shuoMing.setOnClickListener(v -> {
            Intent intent = new Intent(RankActivity.this, RankExplainActivity.class);
            intent.putExtra("type", "1");
            startActivity(intent);
        });

//        int w = View.MeasureSpec.makeMeasureSpec(0,
//                View.MeasureSpec.UNSPECIFIED);
//        int h = View.MeasureSpec.makeMeasureSpec(0,
//                View.MeasureSpec.UNSPECIFIED);
//        if (mType == 1) {
//            viewPager.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    viewPager.setCurrentItem(mType);
//                }
//            }, 1500);
//        }

    }

    private void initFragment() {


        mRankFragment1 = RankFragment.getInstance(2, 1, statusBarHeight);

        mRankFragment1.setmOnPageChangeLister(RankActivity.this);

        mRankFragment2 = RankFragment.getInstance(1, 2, statusBarHeight);

        mRankFragment2.setmOnPageChangeLister(RankActivity.this);

        mRankFragment3 = RankJiedanFragment.getInstance(1, 2, statusBarHeight);

        mRankFragment3.setmOnPageChangeLister(RankActivity.this);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
//            startActivity(new Intent(this, AdminHomeActivity.class));
            overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void OnRankInter(int distance) {
//        LogUtils.debugInfo("distance======"+distance);
        int titleY = distance;
        int imgY = distance;
        if (lastScrollY < h) {
            titleY = Math.min(h, titleY);
            mScrollY = titleY > h ? h : titleY;
            lin.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
            setThemeColor(((255 * mScrollY / h) << 24) | color);
        }
        lastScrollY = titleY;
        if (lastImgScrollY < imgHeight) {
            imgY = Math.min(imgHeight, imgY);
            mImgScrollY = imgY > imgHeight ? imgHeight : imgY;
        }
        lastImgScrollY = imgY;
    }

    public class ExamplePagerAdapter extends FragmentPagerAdapter {

        public ExamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public int getCount() {
//            return AAA == null ? 0 : AAA.length;
            return 1;
        }

        @Override
        public Fragment getItem(int i) {
            if (mCurrentPage == 1) {
                return mRankFragment1;
            }else if (mCurrentPage==2){
                return mRankFragment2;

            }else if (mCurrentPage==3){
                return mRankFragment3;

            }
            return mRankFragment3;
//            Fragment fragment = null;
//            switch (i) {
//                case 0:
//                    fragment = mRankFragment1;
//                    break;
//                case 1:
//                    fragment = mRankFragment2;
//                    break;
//                case 2:
//                    fragment = mRankFragment3;
//                    break;
//            }
//
//            return fragment;
        }


        @Override
        public CharSequence getPageTitle(int position) {
//            return AAA[position];
            return title;
        }
    }
}
