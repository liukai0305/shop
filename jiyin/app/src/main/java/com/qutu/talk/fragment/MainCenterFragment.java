package com.qutu.talk.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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
import com.qutu.talk.R;
import com.qutu.talk.activity.MainActivity;
import com.qutu.talk.activity.PersonalityShopActivity;
import com.qutu.talk.activity.SetActivity;
import com.qutu.talk.activity.dashen.DaShenExclusiveActivity;
import com.qutu.talk.activity.family.FamilyDetailsActivity;
import com.qutu.talk.activity.family.FamilyListActivity;
import com.qutu.talk.activity.game.OrderCenterActivity;
import com.qutu.talk.activity.game.applyskill.ApplyGameSkillActivity;
import com.qutu.talk.activity.mine.MoneyActivity;
import com.qutu.talk.activity.mine.MyProfitActivity;
import com.qutu.talk.activity.mine.RealNameActivity;
import com.qutu.talk.activity.my.GradeCenterActivity;
import com.qutu.talk.activity.my.HelpAndFanKuiActivity;
import com.qutu.talk.activity.my.MemberCoreActivity;
import com.qutu.talk.activity.my.MyFollowActivity;
import com.qutu.talk.activity.my.MyPackageActivity;
import com.qutu.talk.activity.my.MyPersonalCenterTwoActivity;
import com.qutu.talk.activity.room.CollectionRoomListActivity;
import com.qutu.talk.activity.task.TaskCenterActivity;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.app.view.CircularImage;
import com.qutu.talk.base.MyBaseArmFragment;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.EnterRoom;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.MyFamily;
import com.qutu.talk.bean.UserBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.Constant;
import com.qutu.talk.view.CustomDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.qutu.talk.utils.Constant.CHONGZHISHUAXIN;
import static com.qutu.talk.utils.Constant.CREAT_FAMILY;
import static com.qutu.talk.utils.Constant.DASHENZHUANSHU;
import static com.qutu.talk.utils.Constant.FANHUIZHUYE;
import static com.qutu.talk.utils.Constant.KAIQICPWEI;
import static com.qutu.talk.utils.Constant.LOGIN;
import static com.qutu.talk.utils.Constant.PACKFANHUI;
import static com.qutu.talk.utils.Constant.PAIDANZHONGXIN;
import static com.qutu.talk.utils.Constant.QUANBUYINXIAN;
import static com.qutu.talk.utils.Constant.XIUGAIGERENZILIAOCHENGGONG;
import static com.qutu.talk.utils.Constant.XUANFUYINCANG;

/**
 * 作者:sgm
 * 描述: 我的
 */
public class MainCenterFragment extends MyBaseArmFragment implements ImmersionOwner {
    @BindView(R.id.iv_head)
    CircularImage ivHead;
    @BindView(R.id.iv_username)
    TextView ivUsername;
    //    @BindView(R.id.ll1)
//    LinearLayout ll1;
    @BindView(R.id.im_myhome)
    ImageView imMyhome;
    @BindView(R.id.myhome)
    TextView myhome;
    //    @BindView(R.id.im_mywallet)
//    ImageView imMywallet;
//    @BindView(R.id.mywallet)
//    TextView mywallet;
//    @BindView(R.id.im_myshouyi)
//    ImageView imMyshouyi;
//    @BindView(R.id.myshouyi)
//    TextView myshouyi;
    @BindView(R.id.im_mydengji)
    ImageView imMydengji;
    @BindView(R.id.mydengji)
    TextView mydengji;
    @BindView(R.id.im_myhelp)
    ImageView imMyhelp;
    @BindView(R.id.myhelp)
    TextView myhelp;
    @BindView(R.id.im_myset)
    ImageView imMyset;
    @BindView(R.id.myset)
    TextView myset;
    Unbinder unbinder;
    @BindView(R.id.text_vip)
    TextView textVip;
    @BindView(R.id.rlSet)
    RelativeLayout rlSet;
    CustomDialog mDialog;
    //    @BindView(R.id.iv_modifyusermsg)
//    ImageView ivModifyusermsg;
    @BindView(R.id.rl_myhome)
    RelativeLayout rlMyhome;
    @BindView(R.id.rl_help)
    RelativeLayout rlHelp;

    @BindView(R.id.textCollection)
    TextView textCollection;
    @BindView(R.id.textFans)
    TextView textFans;
    @BindView(R.id.rlMoney)
    RelativeLayout rlMoney;
//    @BindView(R.id.my_dd)
//    RelativeLayout myDd;
    @BindView(R.id.rlShouyi)
    RelativeLayout rlShouyi;
    @BindView(R.id.dengji)
    RelativeLayout dengji;
    @BindView(R.id.huiyuan)
    RelativeLayout huiyuan;
    @BindView(R.id.my_lv)
    TextView myLv;
    @BindView(R.id.top_image_bj)
    ImageView topImageBj;
    @BindView(R.id.dakuai)
    LinearLayout dakuai;
    @BindView(R.id.dashen)
    TextView dashen;
//    @BindView(R.id.wddd_yuan)
//    TextView wdddYuan;
    @BindView(R.id.ds_yuan)
    CircularImage dsYuan;
    @BindView(R.id.bb_yuan)
    CircularImage bbYuan;
    @BindView(R.id.rl_task)
    RelativeLayout rlTask;
    @BindView(R.id.rl_family)
    RelativeLayout rlFamily;

//    @BindView(R.id.mizuan)
//    TextView mizuanNum;
//    @BindView(R.id.layout_head_title)
//    RelativeLayout layoutHeadTitle;

    private String old_id = "";

    @Inject
    CommonModel commonModel;

    private UserBean mUserBean;

    private MainActivity mActivity;

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

//        ArmsUtils.inflate(getActivity(), R.layout.fragment_center)
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_center_two);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

//        StatusBarUtil.setTranslucentForImageViewInFragment(getActivity(), 0, null);

//        ll1.setVisibility(View.VISIBLE);
        textVip.setVisibility(View.VISIBLE);
        ivUsername.setText(UserManager.getUser().getNickname());

        loadUserData();
    }


    /**
     * 用户信息
     */
    private void loadUserData() {
        RxUtils.loading(commonModel.get_user_info(String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<UserBean>(mErrorHandler) {
                    @Override
                    public void onNext(UserBean userBean) {
                        mUserBean = userBean;
                        loadImage(ivHead, userBean.getData().getHeadimgurl(),
                                R.mipmap.no_tou);
                        loadImage(topImageBj, userBean.getData().getHeadimgurl(),
                                R.mipmap.no_tu);
                        ivUsername.setText(userBean.getData().getNickname());
                        textVip.setText("ID:" + userBean.getData().getId());
                        textCollection.setText("关注：" + userBean.getData().getFollows_num() + "");
                        textFans.setText("粉丝：" + userBean.getData().getFans_num() + "");
                        myLv.setText("Lv. " + userBean.getData().getVip_level());
                        String mizuan = userBean.getData().getMizuan();
                        if (!TextUtils.isEmpty(mizuan)) {
                            if (mizuan.contains(".")) {
                                int index = mizuan.indexOf(".");
                                mizuan = mizuan.substring(0, index);
                            }
                        }
//                        mizuanNum.setText(mizuan);

                        if (userBean.getData().getIs_god().equals("0")) {
                            dashen.setText("申请大神");
                        } else {
                            dashen.setText("大神专属");
                        }

                        if (userBean.getData().getIs_newpd() == 1) {
                            dsYuan.setVisibility(View.VISIBLE);
                        } else {
                            dsYuan.setVisibility(View.GONE);
                        }

//                        if (userBean.getData().getIs_neworder() == 0) {
//                            wdddYuan.setVisibility(View.GONE);
//                        } else {
//                            wdddYuan.setVisibility(View.VISIBLE);
//                            wdddYuan.setText(String.valueOf(userBean.getData().getIs_neworder()));
//                        }

                        if (userBean.getData().getIs_newpack() == 1) {
                            bbYuan.setVisibility(View.VISIBLE);
                        } else {
                            bbYuan.setVisibility(View.GONE);
                        }
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
                            //家族列表页面
                            Intent intent = new Intent(mActivity, FamilyListActivity.class);
                            intent.putExtra("is_family_show",mUserBean.getData().getIs_family_show());
                            //intent.putExtra("is_god",mUserBean.getData().getIs_god());
                            startActivity(intent);
                        }else{
                            if (mUserBean == null) {
                                return;
                            }
                            //我的家族详情页面
                            Intent intent = new Intent(mActivity, FamilyDetailsActivity.class);
                            intent.putExtra("family_id", myFamily.getData().getJzid());
                            startActivity(intent);
                        }
                    }
                });
    }

    /**
     * 用户信息
     * 用于显示小圆点
     */
    private void loadUserDataYuan() {
        RxUtils.loading(commonModel.get_user_info(String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<UserBean>(mErrorHandler) {
                    @Override
                    public void onNext(UserBean userBean) {
                        if (userBean.getData().getIs_newpd() == 1) {
                            dsYuan.setVisibility(View.VISIBLE);
                        } else {
                            dsYuan.setVisibility(View.GONE);
                        }
//                        if (userBean.getData().getIs_neworder() == 0) {
//                            wdddYuan.setVisibility(View.GONE);
//                        } else {
//                            wdddYuan.setVisibility(View.VISIBLE);
//                            wdddYuan.setText(String.valueOf(userBean.getData().getIs_neworder()));
//                        }
                        if (userBean.getData().getIs_newpack() == 1) {
                            bbYuan.setVisibility(View.VISIBLE);
                        } else {
                            bbYuan.setVisibility(View.GONE);
                        }

                        if (userBean.getData().getIs_newpd() == 0 && userBean.getData().getIs_neworder() == 0 && userBean.getData().getIs_newpack() == 0) {
                            EventBus.getDefault().post(new FirstEvent("全部隐现", QUANBUYINXIAN));
                        }
                    }
                });
    }

    @Override
    public void setData(@Nullable Object data) {

    }


    @OnClick({R.id.my_dd,R.id.iv_head, R.id.rlShouyi, R.id.rlMoney,R.id.store,
            R.id.rl_help, R.id.iv_username, R.id.rlSet, R.id.rl_myhome, R.id.dengji, R.id.huiyuan, R.id.shoucang,R.id.rl_my_package, R.id.rl_dashen, R.id.dakuai, R.id.rl_task,R.id.rl_family})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_dd:
                if (mUserBean == null) {
                    return;
                }
                ArmsUtils.startActivity(OrderCenterActivity.class);
                break;
            case R.id.rl_myhome:
                if (mUserBean == null) {
                    return;
                }
                if (mUserBean.getData().getIs_idcard() == 0) {
                    ArmsUtils.startActivity(RealNameActivity.class);
                } else {
                    enterData(String.valueOf(UserManager.getUser().getUserId()), "", commonModel, 1, mUserBean.getData().getHeadimgurl());
                }
                break;
//            case R.id.iv_modifyusermsg:
////                    ArmsUtils.startActivity(ChangeUserActivity.class);
////                    ArmsUtils.startActivity(UserMineStateActivity.class);
////                    ArmsUtils.startActivity(AdminHomeActivity.class);
//                mDialog = new CustomDialog(getActivity(), "您已被踢出房间", "60分钟后可重新进入该房间", "确定", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mDialog.dismiss();
//                        Toast.makeText(getActivity(), "退出了--伤心", Toast.LENGTH_LONG).show();
//                    }
//                });
//                mDialog.setCanotBackPress();
//                mDialog.setCanceledOnTouchOutside(true);
//                mDialog.show();
//                break;
            case R.id.rl_help:
                ArmsUtils.startActivity(HelpAndFanKuiActivity.class);//帮助和反馈
                break;
            case R.id.iv_head:
                tiaogezhu();
                break;
            case R.id.fanhui:
                tiaogezhu();
                break;
//            case R.id.iv_username:
//                ArmsUtils.startActivity(LoginActivity.class);
//                break;
            case R.id.rlSet: //设置
                ArmsUtils.startActivity(SetActivity.class);
                break;

            case R.id.rlShouyi:
                Intent intent2 = new Intent(getActivity(), MyProfitActivity.class);
                ArmsUtils.startActivity(intent2);
                break;
            case R.id.rlMoney:  //我的钱包
                ArmsUtils.startActivity(MoneyActivity.class);
                break;
//            case R.id.ll1:  //我的关注
//                ArmsUtils.startActivity(MyFollowActivity.class);
//                break;
            case R.id.dengji: //我的等级
                ArmsUtils.startActivity(GradeCenterActivity.class);
                break;
            case R.id.store:  //商城
                ArmsUtils.startActivity(PersonalityShopActivity.class);
                break;
            case R.id.huiyuan:  //会员等级
                ArmsUtils.startActivity(MemberCoreActivity.class);
                break;
            case R.id.shoucang:  //我的收藏
                ArmsUtils.startActivity(CollectionRoomListActivity.class);
                break;
            case R.id.rl_my_package:  //我的背包
                if (mUserBean == null) {
                    return;
                }
                Intent intent1 = new Intent(getActivity(), MyPackageActivity.class);
                intent1.putExtra("url", mUserBean.getData().getHeadimgurl());
                ArmsUtils.startActivity(intent1);
                break;
            case R.id.rl_dashen://大神
                if (mUserBean == null || mUserBean.getData() == null) {
                    return;
                }

                if (mUserBean.getData().getIs_idcard() == 0) {
                    ArmsUtils.startActivity(RealNameActivity.class);
                } else {
                    if (mUserBean.getData().getIs_god().equals("0")) {//不是大神
                        ArmsUtils.startActivity(ApplyGameSkillActivity.class);//选择技能
                    } else {
                        ArmsUtils.startActivity(DaShenExclusiveActivity.class);  //大神专属
                    }
                }

                break;
            case R.id.dakuai:
                ArmsUtils.startActivity(MyFollowActivity.class);
                break;
            case R.id.rl_task:  //我的任务
                ArmsUtils.startActivity(TaskCenterActivity.class);
                break;
            case R.id.rl_family:
                getMyFamily();
                break;
        }
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

        ImmersionBar.with(this).init();

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
        if (!hidden && getActivity() != null) {
            loadUserData();
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
        if (LOGIN.equals(tag)) {
//            ll1.setVisibility(View.VISIBLE);
            textVip.setVisibility(View.VISIBLE);
            //做请求个人信息的操作
        } else if (FANHUIZHUYE.equals(tag)) {//显示悬浮窗
            EnterRoom enterRoom = event.getEnterRoom();
            old_id = String.valueOf(enterRoom.getRoom_info().get(0).getUid());
        } else if (XUANFUYINCANG.equals(tag)) {//显示悬浮窗
            old_id = "";
        } else if (Constant.RENZHENGCHENGGONG.equals(tag)) {
            showToast("认证成功！");
            mUserBean.getData().setIs_idcard(1);
        } else if ("send_gift".equals(tag)) {
            loadUserData();
        } else if (XIUGAIGERENZILIAOCHENGGONG.equals(tag)) {
            loadUserData();
        } else if (CHONGZHISHUAXIN.equals(tag)) {
            loadUserData();
        } else if (PACKFANHUI.equals(tag)) {
            loadUserData();
        } else if (KAIQICPWEI.equals(tag)) {
            loadUserData();
        } else if (Constant.COMMIT_GAME_INFO == tag) {
            loadUserData();
        } else if (DASHENZHUANSHU.equals(tag)) {
            loadUserDataYuan();
        } else if (PAIDANZHONGXIN.equals(tag)) {
            loadUserDataYuan();
        } else if(CREAT_FAMILY.equals(tag)){
            loadUserData();
        }
    }

    private void tiaogezhu() {
        Intent intent = new Intent(getActivity(), MyPersonalCenterTwoActivity.class);
        intent.putExtra("sign", 0);
        intent.putExtra("id", "");
        ArmsUtils.startActivity(intent);
    }
}
