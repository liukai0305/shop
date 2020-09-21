package com.qutu.talk.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.ImmersionOwner;
import com.gyf.immersionbar.components.ImmersionProxy;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.activity.MyFriendActivity;
import com.qutu.talk.activity.dynamic.DynamicNewsActivity;
import com.qutu.talk.activity.family.FamilyApplyActivity;
import com.qutu.talk.activity.family.FamilyDetailsActivity;
import com.qutu.talk.activity.family.FamilyListActivity;
import com.qutu.talk.activity.game.OrderCenterActivity;
import com.qutu.talk.adapter.RankPagerAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.app.view.CircularImage;
import com.qutu.talk.base.MyBaseArmFragment;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.MyFamily;
import com.qutu.talk.bean.UserBean;
import com.qutu.talk.bean.UserTypeBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.qutu.talk.utils.Constant.FAMILYSHENQING;
import static com.qutu.talk.utils.Constant.TUICHUFAMILY;

/**
 * 作者:sgm
 * 描述:消息
 */
public class MainMessageFragment extends MyBaseArmFragment implements ImmersionOwner {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.view_main_bar)
    public View viewMainBar;
    @BindView(R.id.message_news)
    ImageView messageNews;
    @BindView(R.id.tishi)
    CircularImage tishi;
    @BindView(R.id.tishi_rela)
    RelativeLayout tishiRela;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private List<String> titleList = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    private RankPagerAdapter mAdapter;
    private int tag = 0;

    @OnClick({R.id.ll_my_friend, R.id.ll_public_chat, R.id.ll_my_focus, R.id.ll_my_fans})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_my_friend:
                ArmsUtils.startActivity(MyFriendActivity.class);
                break;
            case R.id.ll_public_chat:
//                /**
//                 * 启动会话界面。
//                 */
//                RongIM.getInstance().startConversation(mContext, Conversation.ConversationType.GROUP , "500", "公聊大厅");
//                ArmsUtils.startActivity(SocialReleaseActivity.class);
                ArmsUtils.startActivity(DynamicNewsActivity.class);
                break;
            case R.id.ll_my_focus:
//                Intent intent=new Intent(getActivity(),MyFollowActivity.class);
//                intent.putExtra("position",0);
//                ArmsUtils.startActivity(intent);
                ArmsUtils.startActivity(OrderCenterActivity.class);


                break;
            case R.id.ll_my_fans:
//                Intent intent1=new Intent(getActivity(),MyFollowActivity.class);
//                intent1.putExtra("position",1);
//                ArmsUtils.startActivity(intent1);

                getMyFamily();
                break;
        }
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

    private void getMyFamily(){
        RxUtils.loading(commonModel.getMyFamily(String.valueOf(UserManager.getUser().getUserId())),this)
                .subscribe(new ErrorHandleSubscriber<MyFamily>(mErrorHandler) {
                    @Override
                    public void onNext(MyFamily myFamily) {
                        if(TextUtils.isEmpty(myFamily.getData().getJzid())){
                            if (mUserBean == null) {
                                return;
                            }
                            Intent intent = new Intent(mContext, FamilyListActivity.class);
                            intent.putExtra("is_family_show",mUserBean.getData().getIs_family_show());
                            intent.putExtra("is_god",mUserBean.getData().getIs_god());
                            startActivity(intent);
                        }else{
                            if (mUserBean == null) {
                                return;
                            }
                            Intent intent = new Intent(mContext, FamilyDetailsActivity.class);
                            intent.putExtra("family_id", myFamily.getData().getJzid());
                            startActivity(intent);
                        }
                    }
                });
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
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        titleList.add("消息");
//        titleList.add("关注");
//        titleList.add("粉丝");
        MessageFragment messageFragment = new MessageFragment();
//        MessageFansFragment messageFansFragment1 = MessageFansFragment.getInstance(0);关注
//        MessageFansFragment messageFansFragment2 =MessageFansFragment.getInstance(1);粉丝
//        mFragments.add(messageFragment);
//        mFragments.add(messageFriendFragment);
        mFragments.add(messageFragment);
//        mFragments.add(messageFansFragment1);
//        mFragments.add(messageFansFragment2);

        mAdapter = new RankPagerAdapter(getChildFragmentManager(), mFragments, titleList);
        viewPager.setAdapter(mAdapter);
        getUserType();

        tishiRela.setOnClickListener(v -> {
            ArmsUtils.startActivity(FamilyApplyActivity.class);
        });
        loadUserData();
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    /**
     * 获取是否族长与管理员
     */
    private void getUserType() {
        RxUtils.loading(commonModel.getUserType(), this)
                .subscribe(new ErrorHandleSubscriber<UserTypeBean>(mErrorHandler) {
                    @Override
                    public void onNext(UserTypeBean userTypeBean) {
                        if ("0".equals(userTypeBean.getData().getUser_type())) {
                            tishiRela.setVisibility(View.GONE);
                        } else {
                            tishiRela.setVisibility(View.VISIBLE);
                            if (userTypeBean.getData().getNum() == 0) {
                                tishi.setVisibility(View.GONE);
                            } else {
                                tishi.setVisibility(View.VISIBLE);
                            }
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
        if (hidden) {
            getUserType();
        }
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
        if (FAMILYSHENQING.equals(tag)) {
            getUserType();
        } else if (TUICHUFAMILY.equals(tag)) {
            getUserType();
        }
    }
}
