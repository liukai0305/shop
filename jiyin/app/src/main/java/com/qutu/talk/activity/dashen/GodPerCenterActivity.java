package com.qutu.talk.activity.dashen;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qutu.talk.R;
import com.qutu.talk.activity.dynamic.CommentDetailsActivity;
import com.qutu.talk.activity.message.ReportActivity;
import com.qutu.talk.activity.my.MyPersonalCenterTwoActivity;
import com.qutu.talk.activity.order.ConfirmOrderActivity;
import com.qutu.talk.adapter.dashen.GodCenterAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.BaseBean;
import com.qutu.talk.bean.PullRefreshBean;
import com.qutu.talk.bean.dashen.GodCenterBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.popup.PersonalTwoPopul;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.AppBarStateChangeListener;
import com.qutu.talk.utils.DealRefreshHelper;
import com.qutu.talk.utils.MediaManager;
import com.qutu.talk.utils.RoomHelper;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class GodPerCenterActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.top_img)
    ImageView topImg;
    @BindView(R.id.toolbar_back)
    RelativeLayout toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_right)
    RelativeLayout toolbarRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.AppBarLayout)
    android.support.design.widget.AppBarLayout AppBarLayout;
    //    @BindView(R.id.god_center_head_img)
//    RoundedImageView godCenterHeadImg;
//    @BindView(R.id.god_center_name)
//    TextView godCenterName;
//    @BindView(R.id.god_center_online)
//    TextView godCenterOnline;
//    @BindView(R.id.god_center_right)
//    LinearLayout godCenterRight;
//    @BindView(R.id.god_center_evaluate)
//    TextView godCenterEvaluate;
//    @BindView(R.id.god_center_Receipt)
//    TextView godCenterReceipt;
//    @BindView(R.id.god_center_receipt_time)
//    TextView godCenterReceiptTime;
//    @BindView(R.id.god_center_receipt_local)
//    TextView godCenterReceiptLocal;
//    @BindView(R.id.god_center_receipt_role)
//    TextView godCenterReceiptRole;
//    @BindView(R.id.bofang)
//    ImageView bofang;
//    @BindView(R.id.gif_jing)
//    ImageView gifJing;
//    @BindView(R.id.gif_jing_gif)
//    GifImageView gifJingGif;
//    @BindView(R.id.time_recom_home_page)
//    TextView timeRecomHomePage;
//    @BindView(R.id.bofang_and_zanting)
//    RelativeLayout bofangAndZanting;
//    @BindView(R.id.god_center_remarks)
//    TextView godCenterRemarks;
//    @BindView(R.id.evaluate_num)
//    TextView evaluateNum;
//    @BindView(R.id.evaluate_tit)
//    LinearLayout evaluateTit;
    @BindView(R.id.god_center_recyc)
    RecyclerView godCenterRecyc;
    //    @BindView(R.id.coordinatorLayout)
//    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.ok_btn)
    ImageView okBtn;
    @BindView(R.id.ok_btn_lin)
    LinearLayout okBtnLin;
    @BindView(R.id.god_center_smart)
    SmartRefreshLayout godCenterSmart;
    @BindView(R.id.toolbar_back_img)
    ImageView toolbarBackImg;
    @BindView(R.id.toolbar_right_img)
    ImageView toolbarRightImg;
//    @BindView(R.id.liaotian)
//    ImageView liaotian;
//    @BindView(R.id.guanzhu)
//    ImageView guanzhu;

    private GodCenterBean.DataBean data;

    private String skillId, skillName, skillPrice, skillUnit;
    private String mUserId, hisId;
    private int follow;

    private View headView;
    private RoundedImageView godCenterHeadImg;
    private TextView godCenterName, godCenterOnline, godCenterEvaluate, godCenterReceipt, godCenterReceiptTime, godCenterReceiptLocal, godCenterReceiptRole, godCenterRemarks, evaluateNum, timeRecomHomePage;
    private LinearLayout godCenterRight, evaluateTit;
    private RelativeLayout bofangAndZanting;
    private ImageView liaotian, guanzhu, bofang, gifJing, gifJingGif;

    private ArrayList<GodCenterBean.DataBean.CommentsBean> mDataList = new ArrayList<>();
    private GodCenterAdapter mAdapter;
    private PullRefreshBean mPullRefreshBean = new PullRefreshBean();

    private boolean play = true;//判断播放还是暂停
    private CountDownTimer timer; // 倒计时
//    private int color = ContextCompat.getColor(getApplicationContext(), R.color.white) & 0x00ffffff;

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
        return R.layout.activity_god_per_center;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        //沉浸式状态栏
        ImmersionBar.with(this)
                .reset()
                .titleBar(toolbar)
                .init();

        skillId = getIntent().getStringExtra("id");
        skillName = getIntent().getStringExtra("skillName");
        skillPrice = getIntent().getStringExtra("price");
        skillUnit = getIntent().getStringExtra("unit");
        mUserId = String.valueOf(UserManager.getUser().getUserId());

        setTitle(skillName);

        mAdapter = new GodCenterAdapter(R.layout.god_center_item, mDataList);
        godCenterRecyc.setLayoutManager(new LinearLayoutManager(this));
        godCenterRecyc.setAdapter(mAdapter);

        getFind();

        getData();
        mAdapter.addHeaderView(headView);

        initonClick();
    }

    private void getFind() {
        headView = ArmsUtils.inflate(this, R.layout.god_per_center_head);
        godCenterHeadImg = headView.findViewById(R.id.god_center_head_img);
        godCenterName = headView.findViewById(R.id.god_center_name);
        godCenterOnline = headView.findViewById(R.id.god_center_online);
        godCenterEvaluate = headView.findViewById(R.id.god_center_evaluate);
        godCenterReceipt = headView.findViewById(R.id.god_center_Receipt);
        godCenterReceiptTime = headView.findViewById(R.id.god_center_receipt_time);
        godCenterReceiptLocal = headView.findViewById(R.id.god_center_receipt_local);
        godCenterReceiptRole = headView.findViewById(R.id.god_center_receipt_role);
        godCenterRemarks = headView.findViewById(R.id.god_center_remarks);
        evaluateNum = headView.findViewById(R.id.evaluate_num);
        godCenterRight = headView.findViewById(R.id.god_center_right);
        evaluateTit = headView.findViewById(R.id.evaluate_tit);
        bofangAndZanting = headView.findViewById(R.id.bofang_and_zanting);
        liaotian = headView.findViewById(R.id.liaotian);
        guanzhu = headView.findViewById(R.id.guanzhu);
        bofang = headView.findViewById(R.id.bofang);
        gifJing = headView.findViewById(R.id.gif_jing);
        gifJingGif = headView.findViewById(R.id.gif_jing_gif);
        timeRecomHomePage = headView.findViewById(R.id.time_recom_home_page);
    }

    private void initonClick() {
        godCenterSmart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPullRefreshBean.setLoardMore(mPullRefreshBean, godCenterSmart);
                getData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPullRefreshBean.setRefresh(mPullRefreshBean, godCenterSmart);
                getData();
            }
        });

        liaotian.setOnClickListener(view -> {
            RongIM.getInstance().startConversation(this, Conversation.ConversationType.PRIVATE,
                    data.getGod_id(),
                    data.getNickname());
        });

        guanzhu.setOnClickListener(view -> {
            if (follow == 1) {
                setTakeOff();
            } else {
                setFollow();
            }
        });

        okBtn.setOnClickListener(view -> {
            Intent intent = new Intent(GodPerCenterActivity.this, ConfirmOrderActivity.class);
            intent.putExtra("skillId", String.valueOf(skillId));
            intent.putExtra("id", data.getId());
            intent.putExtra("userId", String.valueOf(data.getGod_id()));
            intent.putExtra("img_1", data.getImage());
            intent.putExtra("naicName", data.getNickname());
            intent.putExtra("price", skillPrice);
            intent.putExtra("unit", skillUnit);
            intent.putExtra("skillName", skillName);
            startActivity(intent);
        });

        toolbarRight.setOnClickListener(view -> {
            PersonalTwoPopul roomTopWindow = new PersonalTwoPopul(this);
            roomTopWindow.showAtLocation(godCenterRecyc, Gravity.BOTTOM, 0, 0);
            roomTopWindow.getLlClose().setOnClickListener(v -> {
                roomTopWindow.dismiss();
            });
            roomTopWindow.getLlJubao().setOnClickListener(v -> {//举报
                roomTopWindow.dismiss();
                Intent intent = new Intent(GodPerCenterActivity.this, ReportActivity.class);
                intent.putExtra("type", "1");
                intent.putExtra("targetId", String.valueOf(data.getGod_id()));
                ArmsUtils.startActivity(intent);
            });
            roomTopWindow.getLlShare().setOnClickListener(v -> {
                roomTopWindow.dismiss();
                fenxiang();
            });
        });

        //音频播放
        bofangAndZanting.setOnClickListener(view -> {
            if (play) {
                play = false;
                bofang.setImageResource(R.mipmap.yy_zt);
                gifJingGif.setVisibility(View.VISIBLE);
                gifJing.setVisibility(View.GONE);
                MediaManager.playSound(data.getAudio(), new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        bofang.setImageResource(R.mipmap.yy_bf);
                        gifJingGif.setVisibility(View.GONE);
                        gifJing.setVisibility(View.VISIBLE);
                    }
                });
                int t = Integer.parseInt(data.getAudio_time().replace("s", "").trim());
                long time = (long) t * 1000;
                timer = new CountDownTimer(time, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int i = (int) (millisUntilFinished / 1000);
                        timeRecomHomePage.setText(i + "''");
                    }

                    @Override
                    public void onFinish() {
                        timeRecomHomePage.setText(data.getAudio_time() + "''");
                        bofang.setImageResource(R.mipmap.yy_bf);
                        gifJingGif.setVisibility(View.GONE);
                        gifJing.setVisibility(View.VISIBLE);
                        MediaManager.pause();
                        MediaManager.release();
                        play = false;
                    }
                }.start();
            } else {
                play = true;
                bofang.setImageResource(R.mipmap.yy_bf);
                timer.cancel();
                timeRecomHomePage.setText(data.getAudio_time() + "''");
                gifJingGif.setVisibility(View.GONE);
                gifJing.setVisibility(View.VISIBLE);
                MediaManager.pause();
                MediaManager.release();
            }
        });

        AppBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(android.support.design.widget.AppBarLayout appBarLayout, State state, int verticalOffset) {
                if (state == State.EXPANDED) {
                    //展开状态
                    toolbarTitle.setTextColor(getResources().getColor(R.color.white));
                    toolbarBackImg.setImageResource(R.mipmap.my_back);
                    toolbarRightImg.setImageResource(R.mipmap.my_more);

                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    toolbarTitle.setTextColor(getResources().getColor(R.color.black));
                    toolbarBackImg.setImageResource(R.mipmap.fh);
                    toolbarRightImg.setImageResource(R.mipmap.gd);
//                    toolbar.setBackgroundColor(changeAlpha(getResources().getColor(R.color.white), Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange()));
                } else {
                    //中间状态
                    if (Math.abs(verticalOffset) >= (appBarLayout.getTotalScrollRange() / 2)) {
                        toolbarTitle.setTextColor(getResources().getColor(R.color.black));
                        toolbarBackImg.setImageResource(R.mipmap.fh);
                        toolbarRightImg.setImageResource(R.mipmap.gd);
                    } else {
                        toolbarTitle.setTextColor(getResources().getColor(R.color.white));
                        toolbarBackImg.setImageResource(R.mipmap.my_back);
                        toolbarRightImg.setImageResource(R.mipmap.my_more);
                    }

                }
            }
        });

        //点击头像跳转个人主页
        godCenterHeadImg.setOnClickListener(view -> {
            Intent intent1 = new Intent(GodPerCenterActivity.this, MyPersonalCenterTwoActivity.class);
            if (data.getGod_id().equals(String.valueOf(UserManager.getUser().getUserId()))) {
                intent1.putExtra("sign", 0);
                intent1.putExtra("id", "");
            } else {
                intent1.putExtra("sign", 1);
                intent1.putExtra("id", data.getGod_id());
            }
            ArmsUtils.startActivity(intent1);
        });

    }

    //获取数据
    private void getData() {
        RxUtils.loading(commonModel.getGodSkillInfo(skillId, mPullRefreshBean.pageIndex), this)
                .subscribe(new ErrorHandleSubscriber<GodCenterBean>(mErrorHandler) {
                    @Override
                    public void onNext(GodCenterBean godCenterBean) {
                        data = godCenterBean.getData();
                        hisId = godCenterBean.getData().getGod_id();
                        if (mUserId.equals(godCenterBean.getData().getGod_id())) {
                            godCenterRight.setVisibility(View.GONE);
                            okBtnLin.setVisibility(View.GONE);
                        } else {
                            godCenterRight.setVisibility(View.VISIBLE);
                            okBtnLin.setVisibility(View.VISIBLE);
                        }
                        //头部大图
                        loadImage(topImg, godCenterBean.getData().getImage(), R.mipmap.no_tu);
                        //头像
                        ArmsUtils.obtainAppComponentFromContext(GodPerCenterActivity.this)
                                .imageLoader()
                                .loadImage(GodPerCenterActivity.this, ImageConfigImpl
                                        .builder()
                                        .url(godCenterBean.getData().getHeadimgurl())
                                        .placeholder(R.mipmap.no_tou)
                                        .imageView(godCenterHeadImg)
                                        .errorPic(R.mipmap.no_tou)
                                        .build());
                        //名字
                        godCenterName.setText(godCenterBean.getData().getNickname());
                        //在线表示显示与隐藏
                        if (godCenterBean.getData().getIsOnline() == 0) {
                            godCenterOnline.setVisibility(View.GONE);
                        } else {
                            godCenterOnline.setVisibility(View.VISIBLE);
                        }
                        //评分
                        float score = Float.parseFloat(godCenterBean.getData().getScore());
                        godCenterEvaluate.setText("评分：" + Float.toString(score));
                        //接单量
                        godCenterReceipt.setText("接单量：" + godCenterBean.getData().getNum());
                        if (TextUtils.isEmpty(godCenterBean.getData().getJd_date())) {
                            godCenterReceiptTime.setVisibility(View.GONE);
                        } else {
                            godCenterReceiptTime.setText(godCenterBean.getData().getJd_date());
                        }

                        if (TextUtils.isEmpty(godCenterBean.getData().getAreas())) {
                            godCenterReceiptLocal.setVisibility(View.GONE);
                        } else {
                            godCenterReceiptLocal.setText("可接单大区：" + godCenterBean.getData().getAreas());
                        }

                        if (TextUtils.isEmpty(godCenterBean.getData().getPositions())) {
                            godCenterReceiptRole.setVisibility(View.GONE);
                        } else {
                            godCenterReceiptRole.setText("擅长位置：" + godCenterBean.getData().getPositions());
                        }

                        //音频时长
                        timeRecomHomePage.setText(godCenterBean.getData().getAudio_time() + "''");
                        //内容
                        godCenterRemarks.setText(godCenterBean.getData().getIntroduce());
                        //评论条数
                        evaluateNum.setText(godCenterBean.getData().getTotal());
                        //关注与否
                        follow = godCenterBean.getData().getIs_follow();
                        if (godCenterBean.getData().getIs_follow() == 1) {
                            guanzhu.setSelected(true);
                        } else {
                            guanzhu.setSelected(false);
                        }

                        List<GodCenterBean.DataBean.CommentsBean> commentsList = godCenterBean.getData().getComments();
                        new DealRefreshHelper<GodCenterBean.DataBean.CommentsBean>().dealDataToUI(godCenterSmart, mAdapter, null, commentsList, mDataList, mPullRefreshBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<GodCenterBean.DataBean.CommentsBean>().dealDataToUI(godCenterSmart, mAdapter, null, null, mDataList, mPullRefreshBean);
                    }
                });
    }

    //关注
    private void setFollow() {
        RxUtils.loading(commonModel.follow(String.valueOf(UserManager.getUser().getUserId()), hisId), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        follow = 1;
                        guanzhu.setSelected(true);
                    }
                });
    }

    //取消关注
    private void setTakeOff() {
        RxUtils.loading(commonModel.cancel_follow(String.valueOf(UserManager.getUser().getUserId()), hisId), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        follow = 0;
                        guanzhu.setSelected(false);
                    }
                });
    }

    //分享
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

        new ShareAction(GodPerCenterActivity.this)
                .withMedia(web)
                .setDisplayList(SHARE_MEDIA.WEIXIN,
                        SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
                .setCallback(RoomHelper.getUMShareListener(this,commonModel, GodPerCenterActivity.this,mErrorHandler))
                .open(config);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
        MediaManager.pause();
        MediaManager.release();
    }
}
