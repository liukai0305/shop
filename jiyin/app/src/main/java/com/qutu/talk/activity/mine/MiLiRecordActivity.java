package com.qutu.talk.activity.mine;

/**
 * 金币记录页面
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.qutu.talk.R;
import com.qutu.talk.adapter.MyPagerAdapterTwo;
import com.qutu.talk.adapter.RankPagerAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.UserTypeBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.fragment.MiLiSZJiLuFragment;
import com.qutu.talk.fragment.MiLiTXJiLuFragment;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.mytablayout.TabLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class MiLiRecordActivity extends MyBaseArmActivity {

    @Inject
    CommonModel commonModel;

    @BindView(R.id.mili_tablayout)
    TabLayout miliTablayout;
    @BindView(R.id.mili_viewpager)
    ViewPager miliViewpager;

    private List<String> titleList = new ArrayList<>(); //tabLayout的数据源
    private List<Fragment> mFragments = new ArrayList<>(); //fragment的数据源
    private MyPagerAdapterTwo mAdapter;

    private int mType;


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
        return R.layout.activity_mi_li_record;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle("记录");

        mType = getIntent().getIntExtra("type", 0);
        initTabLayout();

        getUserType();

//        //添加TabLayout的数据
//        titleList.add("订单收入");
//        titleList.add("订单支出");
//        titleList.add("钻石提现");
//
//        //添加Fragment数据
//        mFragments.add(MiLiSZJiLuFragment.getInstance(2));
//        mFragments.add(MiLiSZJiLuFragment.getInstance(1));
//        mFragments.add(MiLiTXJiLuFragment.getInstance(mType));
//
//        mAdapter = new MyPagerAdapterTwo(getSupportFragmentManager(), mFragments, titleList);
//        miliViewpager.setAdapter(mAdapter);
//        miliTablayout.setupWithViewPager(miliViewpager);
//
//        miliViewpager.setCurrentItem(0);
//        miliViewpager.setOffscreenPageLimit(3);
//        miliTablayout.getTabAt(0).select();
    }

    //改变tabLayout选中时Tab的文字状态
    private void initTabLayout() {
        miliTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

    /**
     * 获取是否族长与管理员
     */
    private void getUserType() {
        showDialogLoding();
        RxUtils.loading(commonModel.getUserType(), this)
                .subscribe(new ErrorHandleSubscriber<UserTypeBean>(mErrorHandler) {
                    @Override
                    public void onNext(UserTypeBean userTypeBean) {
                        disDialogLoding();
                        if ("2".equals(userTypeBean.getData().getUser_type())) {//族长
                            //添加TabLayout的数据
                            titleList.add("订单收入");
                            titleList.add("订单支出");
                            titleList.add("钻石提现");
                            titleList.add("家族收入");

                            //添加Fragment数据
                            mFragments.add(MiLiSZJiLuFragment.getInstance(2));
                            mFragments.add(MiLiSZJiLuFragment.getInstance(1));
                            mFragments.add(MiLiTXJiLuFragment.getInstance(mType));
                            mFragments.add(MiLiSZJiLuFragment.getInstance(3));
                        } else {
                            //添加TabLayout的数据
                            titleList.add("订单收入");
                            titleList.add("订单支出");
                            titleList.add("钻石提现");

                            //添加Fragment数据
                            mFragments.add(MiLiSZJiLuFragment.getInstance(2));
                            mFragments.add(MiLiSZJiLuFragment.getInstance(1));
                            mFragments.add(MiLiTXJiLuFragment.getInstance(mType));
                        }

                        mAdapter = new MyPagerAdapterTwo(getSupportFragmentManager(), mFragments, titleList);
                        miliViewpager.setAdapter(mAdapter);
                        miliTablayout.setupWithViewPager(miliViewpager);

                        miliViewpager.setCurrentItem(0);
                        miliViewpager.setOffscreenPageLimit(3);
                        miliTablayout.getTabAt(0).select();
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        disDialogLoding();
                    }
                });
    }

    private void initUpData(TabLayout.Tab tab, boolean boo) {
        TextView textView = (TextView) LayoutInflater.from(MiLiRecordActivity.this).inflate(R.layout.hei_title_text_layout, null);
        if (boo) {
//            textView.setSelected(true);
            textView.setText(tab.getText());
//            textView.setTextSize(14);
            textView.setTextColor(getResources().getColor(R.color.font_ff3e6d));
            tab.setCustomView(textView);
        } else {
            tab.setCustomView(null);
        }
    }
}
