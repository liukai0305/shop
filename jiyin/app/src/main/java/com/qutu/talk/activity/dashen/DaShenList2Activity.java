package com.qutu.talk.activity.dashen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.LogUtils;
import com.qutu.talk.R;
import com.qutu.talk.adapter.MyPagerAdapter;
import com.qutu.talk.adapter.dashen.MainHomePageSkillAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.HeaderViewPagerFragment;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.dashen.MainHomePageBean;
import com.qutu.talk.bean.dashen.MainHomePageSkillBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.fragment.RecomHomePageFragment;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.DealRefreshHelper;
import com.qutu.talk.utils.mytablayout.TabLayout;
import com.qutu.talk.view.ShapeTextView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 大神列表页面
 * da_shen_list_item  adapter的布局
 */

public class DaShenList2Activity extends BaseActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.toolbar_back)
    RelativeLayout toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.rightTitle)
    TextView rightTitle;
    @BindView(R.id.tv_help_history)
    TextView tvHelpHistory;
    @BindView(R.id.rightConfirm)
    ShapeTextView rightConfirm;
    @BindView(R.id.tv_bar_right)
    TextView tvBarRight;
    @BindView(R.id.img_bar_right)
    ImageView imgBarRight;
    @BindView(R.id.toolbar_right)
    RelativeLayout toolbarRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.coordinator_layout)
    LinearLayout coordinatorLayout;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    private List<MainHomePageSkillBean.DataBean> skillBeanData;
    private String mId;
    private String mUserId;

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
        return R.layout.activity_da_shen_list2;
    }

    private MainHomePageSkillAdapter mSkillAdapter;
    private MyPagerAdapter mPagerAdapter;

    private List<String> tabList;
    private List<HeaderViewPagerFragment> mFragments;
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mId = getIntent().getStringExtra("id");
        skillBeanData = getIntent().getParcelableArrayListExtra("data");
        mUserId = String.valueOf(UserManager.getUser().getUserId());
        setTitle("分类");

        initTabLayout();

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

        mFragments = new ArrayList<>();
        tabList = new ArrayList<>();
        List<MainHomePageSkillBean.DataBean> data = skillBeanData;
        tabList.add("全部");
        for (MainHomePageSkillBean.DataBean list : data) {
            tabList.add(list.getName());
        }
        //全部
        mFragments.add(RecomHomePageFragment.getInstance(1, "0"));
        for (int i = 0; i < data.size(); i++) {
            mFragments.add(RecomHomePageFragment.getInstance(1, String.valueOf(data.get(i).getId())));
        }
        for (int i = 0; i < mFragments.size(); i++) {
            if (i == 0) {
                RecomHomePageFragment recomHomeFragment = (RecomHomePageFragment) mFragments.get(i);
                recomHomeFragment.setRefersh(refreshLayout);
            } else {
                RecomHomePageFragment recomHomeFragment = (RecomHomePageFragment) mFragments.get(i);
                recomHomeFragment.setRefersh(refreshLayout);
            }

        }
        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), mFragments, tabList);
        viewPager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        int index = 0;
        for (MainHomePageSkillBean.DataBean skillBeanDatum : skillBeanData) {
            if (mId.equals(String.valueOf(skillBeanDatum.getId()))) {
                index = skillBeanData.indexOf(skillBeanDatum)+1;
                break;
            }
        }

        viewPager.setCurrentItem(index);
        tabLayout.getTabAt(index).select();
        viewPager.setOffscreenPageLimit(mFragments.size());
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
        TextView textView = (TextView) LayoutInflater.from(getBaseContext()).inflate(R.layout.hei_title_text_layout, null);
        if (boo) {
            textView.setSelected(true);
            textView.setText(tab.getText());
            tab.setCustomView(textView);
        } else {
            tab.setCustomView(null);
        }
    }
    private void getData() {

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

}
