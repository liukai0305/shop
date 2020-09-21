package com.qutu.talk.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.ImmersionOwner;
import com.gyf.immersionbar.components.ImmersionProxy;
import com.jaeger.library.StatusBarUtil;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.qutu.talk.R;
import com.qutu.talk.activity.MainActivity;
import com.qutu.talk.activity.SearchDynamicActivity;
import com.qutu.talk.activity.SearchHisActivity;
import com.qutu.talk.activity.dynamic.DynamicNewsActivity;
import com.qutu.talk.activity.dynamic.SocialReleaseActivity;
import com.qutu.talk.activity.message.MessageFragmentActivity;
import com.qutu.talk.adapter.RankPagerAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.app.view.CircularImage;
import com.qutu.talk.base.MyBaseArmFragment;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.LoginData;
import com.qutu.talk.bean.UnreadBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.qutu.talk.utils.Constant.FABUCHENGGONG;
import static com.qutu.talk.utils.Constant.XIUGAIGERENZILIAOCHENGGONG;

/**
 * 作者:sgm
 * 描述:动态首页
 */
public class MainCommunityFragment extends MyBaseArmFragment  implements ImmersionOwner {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.community_search)
    ImageView communitySearch;
    @BindView(R.id.community_tab_layout)
    TabLayout communityTabLayout;
    @BindView(R.id.community_news)
    ImageView communityNews;
    @BindView(R.id.community_iew_pager)
    ViewPager communityIewPager;
    @BindView(R.id.tishi)
    CircularImage tishi;
    @BindView(R.id.view_main_bar)
    public View viewMainBar;

    private List<String> titleList = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    private RankPagerAdapter mAdapter;
    private int tag = 0;
    private LoginData user;

    MainActivity mMainActivity;
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

    @Override
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_community);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

//        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.white), 0);

        user = UserManager.getUser();

        initTabLayout();

//        titleList.add("最新");
        titleList.add("推荐");
        titleList.add("关注");

//        NewestDynamicFragment commFragment1 = NewestDynamicFragment.getInstance();
        CommFragment commFragment2 = CommFragment.getInstance();
        FollowDynamicFragment commFragment3 = FollowDynamicFragment.getInstance();
//        mFragments.add(commFragment1);
        mFragments.add(commFragment2);
        mFragments.add(commFragment3);
        mAdapter = new RankPagerAdapter(getChildFragmentManager(), mFragments, titleList);
        communityIewPager.setAdapter(mAdapter);
        communityTabLayout.setupWithViewPager(communityIewPager);
//        communityIewPager.setOffscreenPageLimit(mFragments.size());
        communityIewPager.setCurrentItem(0);
        communityTabLayout.getTabAt(0).select();
        communityIewPager.setOffscreenPageLimit(mFragments.size());


    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (!hidden && getActivity() != null) {
//            StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.white), 0);
//        }
//    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @OnClick({R.id.community_search, R.id.community_news, R.id.float_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.community_search:
                ArmsUtils.startActivity(SearchHisActivity.class);
                break;
            case R.id.community_news:
//                ArmsUtils.startActivity(DynamicNewsActivity.class);
                ArmsUtils.startActivity(SocialReleaseActivity.class);
                break;
            case R.id.float_button:
                ArmsUtils.startActivity(MessageFragmentActivity.class);
                break;
        }
    }

    private void initTabLayout() {
        communityTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.title_text_layout, null);
        if (boo) {
            textView.setSelected(true);
            textView.setText(tab.getText());
            tab.setCustomView(textView);
        } else {
            tab.setCustomView(null);
        }
    }

    //获取未读消息 total 0 没有未读 其他有未读，并是未读的信息条数
    private void getUnreadMessage() {
        RxUtils.loading(commonModel.getUnreadMessage(String.valueOf(user.getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<UnreadBean>(mErrorHandler) {
                    @Override
                    public void onNext(UnreadBean unreadBean) {
                        UnreadBean.DataBean data = unreadBean.getData();
                        if (data.getTotal() == 0) {
                            tishi.setVisibility(View.GONE);
                        } else {
                            tishi.setVisibility(View.VISIBLE);
                        }
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
                .autoStatusBarDarkModeEnable(true,0.2f) //自动状态栏字体变色，必须指定状态栏颜色才可以自动变色哦
                .init();//设置状态栏白色

    }

    @Override
    public void onResume() {
        super.onResume();
        mImmersionProxy.onResume();
//        getUnreadMessage();
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
        if (FABUCHENGGONG.equals(tag)) {
            communityTabLayout.getTabAt(0).select();
            communityIewPager.setCurrentItem(0);
        }
    }
}
