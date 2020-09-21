package com.qutu.talk.popup;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.qutu.talk.R;
import com.qutu.talk.activity.ChargeActivity;
import com.qutu.talk.activity.room.AdminHomeActivity;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.BaoXiangBean;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.GoodsList;
import com.qutu.talk.bean.LoginData;
import com.qutu.talk.bean.MessageBean;
import com.qutu.talk.bean.MessageEvent;
import com.qutu.talk.bean.OpenBoxBean;
import com.qutu.talk.bean.StateMessage;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.Arith;
import com.qutu.talk.utils.BToast;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.qutu.talk.utils.Constant.DUIHUAN;
import static com.qutu.talk.utils.Constant.GOUMAIYAOSHI;
import static com.qutu.talk.utils.Constant.XIANSHIWANBI;

/**
 * 砸金蛋窗口
 */
public class GemStoneDialog extends Dialog {

    //    @BindView(R.id.tv_gemstone_integral)
//    TextView mTvGemstoneIntegral;
//    @BindView(R.id.img_winning_record)
//    ImageView mImgWinningRecord;
//    @BindView(R.id.img_gemstone_intro)
//    ImageView mImgGemstoneIntro;
//    @BindView(R.id.img_gemstone_exchange)
//    ImageView mImgGemstoneExchange;;
    //    @BindView(R.id.img_gemstone_logo)
//    ImageView mImgGemstoneLogo;
    @BindView(R.id.tv_history)
    TextView tvHistory;
    @BindView(R.id.tv_key_count)
    TextView tvKeyCount;
    @BindView(R.id.layout_key_count)
    RelativeLayout layoutKeyCount;
    @BindView(R.id.ll_tab)
    LinearLayout llTab;
    @BindView(R.id.iv_egg)
    ImageView ivEgg;
    @BindView(R.id.ll_money)
    LinearLayout llMoney;
    @BindView(R.id.img_open_hundred)
    ImageView imgOpenHundred;
    @BindView(R.id.tv_cut_down_time)
    TextView tvCutDownTime;
    @BindView(R.id.img_add_key)
    ImageView imgAddKey;
    @BindView(R.id.img_ten_open)
    ImageView imgTenOpen;
    @BindView(R.id.img_open_one)
    ImageView imgOpenOne;
    @BindView(R.id.view_empty)
    View viewEmpty;
    @BindView(R.id.layout_baoxiao)
    LinearLayout layoutBaoxiao;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_gold_indicator)
    TextView tvGoldIndicator;
    @BindView(R.id.tv_color_indicator)
    TextView tvColorIndicator;
    @BindView(R.id.tv_recharge)
    TextView tvRecharge;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;

    private AdminHomeActivity mContext;
    private CommonModel commonModel;
    private RxErrorHandler mErrorHandler;
    private BaoXiangBean.DataBean mDataBean;
//    private TouMingDialog touMingDialog;
//    private OpenBoxBean mOpenBoxBean;
//    private BoxGiftActivity boxGiftActivity;
//    private int anInt = 0;

    boolean mHasThreeMinate = true;//距离守护宝箱开启还有3分钟倒计时

    boolean mIsGuardOpen = true;//距离守护宝箱关闭还有多少时间的倒计时

    long mHasGoneThreeMinateTime = 0;//3分钟倒计时已经过去了多少时间

    long mHasGoneGuardOpenTime = 0;//30分钟倒计时已经过去了多少时间


    long mLimitGuardTime = 0;//进入时距离活动结束还有多少时间

    long mLimitThreeMinateTime = 0;//进入时距离3分钟结束还有多少时间

    Handler mHandlerThreeMinate = new Handler();

    Handler mHandlerGuardOpen = new Handler();


    Runnable mThreeMinateRunnable = new Runnable() {//距离守护宝箱开启还有3分钟倒计时
        @Override
        public void run() {

//            long limite = 3 * 60 * 1000;//总共毫秒

            mHasGoneThreeMinateTime += 1000;

            if (mHasGoneThreeMinateTime >= mLimitThreeMinateTime) {
                mHasGoneThreeMinateTime = mLimitThreeMinateTime;
            }

            long second = (mLimitThreeMinateTime - mHasGoneThreeMinateTime) / 1000;//还有多少秒

            String timeStr = "";

            if (second == 0) {

                timeStr = "0:00";

            } else {

                if (second >= 60) {//剩余时间大于60秒

                    int minate = (int) (second / 60);

                    timeStr = "" + minate;

                    int sec = (int) (second % 60);

                    if (sec >= 10) {
                        timeStr = timeStr + ":" + sec;
                    } else {
                        timeStr = timeStr + ":0" + sec;
                    }

                } else {//剩余时间小于60秒

                    if (second >= 10) {
                        timeStr = "0:" + second;
                    } else {
                        timeStr = "0:0" + second;
                    }

                }

            }
            tvCutDownTime.setText("守护宝箱开启还有" + timeStr + "分钟");

            if (timeStr.equals("0:00")) {
                getBaoXiang();
                return;
            } else {
                LogUtils.debugInfo("时间啦啦啦啦====" + timeStr);
                mHandlerThreeMinate.postDelayed(this, 1000);
            }

        }
    };

    Runnable mGuardCloseRunnable = new Runnable() {//距离守护宝箱关闭还有多少分钟倒计时
        @Override
        public void run() {

//            long limite = 30 * 60 * 1000;//总共毫秒

            mHasGoneGuardOpenTime += 1000;

            if (mHasGoneGuardOpenTime >= mLimitGuardTime) {
                mHasGoneGuardOpenTime = mLimitGuardTime;
            }

            long second = (mLimitGuardTime - mHasGoneGuardOpenTime) / 1000;//还有多少秒

            String timeStr = "";

            if (second == 0) {

                timeStr = "0:00";

            } else {

                if (second >= 60) {//剩余时间大于60秒

                    int minate = (int) (second / 60);

                    if (minate >= 10) {
                        timeStr = "" + minate;
                    } else {

                        timeStr = "0" + minate;
                    }


                    int sec = (int) (second % 60);

                    if (sec >= 10) {
                        timeStr = timeStr + ":" + sec;
                    } else {
                        timeStr = timeStr + ":0" + sec;
                    }

                } else {//剩余时间小于60秒

                    if (second >= 10) {
                        timeStr = "0:" + second;
                    } else {
                        timeStr = "0:0" + second;
                    }

                }

            }
            tvCutDownTime.setText("守护宝箱关闭还有" + timeStr + "分钟");

            if (timeStr.equals("0:00")) {
                getBaoXiang();
                return;
            } else {
                mHandlerGuardOpen.postDelayed(this, 1000);
            }

        }
    };

    public GemStoneDialog(@NonNull Activity context, CommonModel commonModel, RxErrorHandler errorHandler,int eggType) {
        super(context, R.style.myChooseDialog);
        mContext = (AdminHomeActivity) context;
        this.commonModel = commonModel;
        this.mErrorHandler = errorHandler;
        this.currentEggType=eggType;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_open_gemstone);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        rlRoot.setOnClickListener(v -> {

        });
        if (currentEggType == 0) {
            initTab();
            tvGoldIndicator.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(R.mipmap.gold_egg).into(ivEgg);
            imgOpenOne.setImageResource(R.mipmap.icon_open_one);
            imgTenOpen.setImageResource(R.mipmap.icon_open_ten);
            imgOpenHundred.setImageResource(R.mipmap.icon_open_hundred);
        } else {
            initTab();
            tvColorIndicator.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(R.mipmap.color_egg).into(ivEgg);
            imgOpenOne.setImageResource(R.mipmap.icon_open_one_color);
            imgTenOpen.setImageResource(R.mipmap.icon_open_ten_color);
            imgOpenHundred.setImageResource(R.mipmap.icon_open_hundred_color);
        }
        setWidows();

//        initTime();

        getBaoXiang();
        loadYue();

    }

    /**
     * 请求余额
     */
    private void loadYue() {
        RxUtils.loading(commonModel.goodsList(String.valueOf(UserManager.getUser().getUserId())), mContext)
                .subscribe(new ErrorHandleSubscriber<GoodsList>(mErrorHandler) {
                    @Override
                    public void onNext(GoodsList goodsList) {
                        tvMoney.setText("余额  " + goodsList.getData().getMizuan());
                    }
                });
    }

    private void initTime() {
        mHandlerThreeMinate.removeCallbacks(mThreeMinateRunnable);
        mHandlerGuardOpen.removeCallbacks(mGuardCloseRunnable);
        LogUtils.debugInfo("当前时间：" + System.currentTimeMillis());

        long currentTime = System.currentTimeMillis() / 1000;

        if (mDataBean != null && !TextUtils.isEmpty(mDataBean.getBoxstartdate()) && !TextUtils.isEmpty(mDataBean.getBoxenddate())) {

            long startTime = Arith.strToLong(mDataBean.getBoxstartdate());

            long endTime = Arith.strToLong(mDataBean.getBoxenddate());

//            startTime=currentTime+2*60;
//            endTime+=currentTime+32*60;

            LogUtils.debugInfo("开启时间：" + startTime);
            LogUtils.debugInfo("关闭时间：" + endTime);

            if (currentTime >= startTime && currentTime < endTime) {//当前时间大于活动开始时间，小于结束时间

                LogUtils.debugInfo("当前时间大于活动开始时间，小于结束时间");

                mLimitGuardTime = endTime - currentTime;//还剩多少时间

                mLimitGuardTime = mLimitGuardTime * 1000;//转成毫秒

                tvCutDownTime.setText("");

                tvCutDownTime.setVisibility(View.VISIBLE);

                mHasGoneGuardOpenTime = 0;

                mHandlerGuardOpen.postDelayed(mGuardCloseRunnable, 1000);

                updateViewHeight();

            } else if (currentTime > endTime) {//已经结束

                LogUtils.debugInfo("已经结束");

                tvCutDownTime.setVisibility(View.GONE);

                updateViewHeight();

            } else if (currentTime < startTime && startTime - currentTime > 3 * 60) {//开启时间大于3分钟

                LogUtils.debugInfo("开启时间大于3分钟");

                tvCutDownTime.setVisibility(View.GONE);

                updateViewHeight();

            } else if (currentTime < startTime && startTime - currentTime <= 3 * 60) {//守护宝箱开启前3分钟倒计时

                LogUtils.debugInfo("守护宝箱开启前3分钟倒计时");

                mLimitThreeMinateTime = startTime - currentTime;//还剩多少时间

                mLimitThreeMinateTime = mLimitThreeMinateTime * 1000;//转成毫秒

                tvCutDownTime.setText("");

                tvCutDownTime.setVisibility(View.VISIBLE);

                mHasGoneThreeMinateTime = 0;

                mHandlerThreeMinate.postDelayed(mThreeMinateRunnable, 1000);

            }


        }

//        if(mHasThreeMinate){//还有3分钟开启守护宝箱的倒计时
//
//            mTvCutDownTime.setVisibility(View.VISIBLE);
//
//            mHandlerThreeMinate.postDelayed(mThreeMinateRunnable, 1000);
//        }
//        if (mIsGuardOpen) {
//
//            mTvCutDownTime.setText("守护宝箱关闭还有30:00分钟");
//
//            mTvCutDownTime.setVisibility(View.VISIBLE);
//
//            mHandlerGuardOpen.postDelayed(mGuardCloseRunnable, 1000);
//
//            updateViewHeight();
//        }

    }

    /**
     * 设置dialog底部空白高度
     */
    private void updateViewHeight() {

        if (tvCutDownTime.getVisibility() == View.GONE) {
            ViewGroup.LayoutParams params = viewEmpty.getLayoutParams();
            params.height = QMUIDisplayHelper.dpToPx(30);
            viewEmpty.setLayoutParams(params);
        } else {
            ViewGroup.LayoutParams params = viewEmpty.getLayoutParams();
            params.height = QMUIDisplayHelper.dpToPx(10);
            viewEmpty.setLayoutParams(params);
        }
    }

    private void setWidows() {

        Display display = getWindow().getWindowManager().getDefaultDisplay();

        WindowManager.LayoutParams lp = getWindow().getAttributes();

        lp.width = (int) (display.getWidth() - QMUIDisplayHelper.dp2px(mContext, 72));

        lp.alpha = 1.0f;

        lp.gravity = Gravity.BOTTOM;

//        getWindow().setAttributes(lp);

        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        this.setCanceledOnTouchOutside(true);

    }

    @Override
    public void dismiss() {
        super.dismiss();
        mHandlerThreeMinate.removeCallbacks(mThreeMinateRunnable);
        mHandlerGuardOpen.removeCallbacks(mGuardCloseRunnable);
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.img_add_key, R.id.img_ten_open, R.id.img_open_one, R.id.img_open_hundred, R.id.ll_guize, R.id.tv_history, R.id.tv_recharge, R.id.ll_tab_gold, R.id.ll_tab_color, R.id.layout_baoxiao, R.id.ll_jiangchi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_tab_gold:
                initTab();
                tvGoldIndicator.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(R.mipmap.gold_egg).into(ivEgg);
                imgOpenOne.setImageResource(R.mipmap.icon_open_one);
                imgTenOpen.setImageResource(R.mipmap.icon_open_ten);
                imgOpenHundred.setImageResource(R.mipmap.icon_open_hundred);
                currentEggType = 0;
                break;
            case R.id.ll_tab_color:
                initTab();
                tvColorIndicator.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(R.mipmap.color_egg).into(ivEgg);
                imgOpenOne.setImageResource(R.mipmap.icon_open_one_color);
                imgTenOpen.setImageResource(R.mipmap.icon_open_ten_color);
                imgOpenHundred.setImageResource(R.mipmap.icon_open_hundred_color);
                currentEggType = 1;
                break;
            case R.id.ll_guize:
                BoxTitleWindow boxTitleWindow = new BoxTitleWindow(mContext, (ViewGroup) getWindow().getDecorView(), commonModel, mErrorHandler);
                boxTitleWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                break;
            case R.id.tv_history:
                dismiss();
                BoxOpenRecordWindow boxOpenRecordWindow = new BoxOpenRecordWindow(mContext, (ViewGroup) getWindow().getDecorView(), commonModel, mErrorHandler,rlRoot.getMeasuredHeight());
                boxOpenRecordWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
                break;
            case R.id.ll_jiangchi:
                dismiss();
                JackpotWindow jackpotWindow = new JackpotWindow(mContext, (ViewGroup) getWindow().getDecorView(), commonModel, mErrorHandler, currentEggType,rlRoot.getMeasuredHeight());
                jackpotWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
                break;
            case R.id.tv_recharge:
                Intent intent = new Intent(mContext, ChargeActivity.class);
                intent.putExtra("type", 0);
                ArmsUtils.startActivity(intent);
                break;
            case R.id.layout_baoxiao:
                dismiss();
                break;
//            case R.id.img_winning_record:
//                BoxOpenRecordWindow boxOpenRecordWindow = new BoxOpenRecordWindow(mContext, mConstraintLayoutBaoxiao, commonModel, mErrorHandler);
//                boxOpenRecordWindow.showAtLocation(mTvGemstoneIntegral, Gravity.CENTER, 0, 0);
//                break;
//            case R.id.img_gemstone_intro:
//                BoxTitleWindow boxTitleWindow = new BoxTitleWindow(mContext, mConstraintLayoutBaoxiao, commonModel, mErrorHandler);
//                boxTitleWindow.showAtLocation(mTvGemstoneIntegral, Gravity.CENTER, 0, 0);
//                break;
//            case R.id.img_gemstone_exchange:
//                BoxExchangeWindow boxExchangeWindow = new BoxExchangeWindow(mContext, commonModel, mErrorHandler, mDataBean.getPoints());
//                boxExchangeWindow.showAtLocation(mTvGemstoneIntegral, Gravity.CENTER, 0, 0);
//                break;
            case R.id.img_add_key://钥匙添加
                BuyKeyDialog buyKeyDialog = new BuyKeyDialog(mContext, commonModel, mErrorHandler);
                buyKeyDialog.show();
                break;
            case R.id.img_ten_open:
                totalClickCount = 10;
                if (!isMultiClick) {
                    showServerSVG(ivEgg);
                    totalClickCount = 10;
                } else {
                    totalClickCount = totalClickCount + 10;
                }
                System.out.println("----------" + totalClickCount);
                isMultiClick = true;
                handler.removeMessages(WHAT_CLICK);
                handler.sendEmptyMessageDelayed(WHAT_CLICK, 1000);

                break;
            case R.id.img_open_one:
                totalClickCount = 1;
                if (!isMultiClick) {
                    showServerSVG(ivEgg);
                    totalClickCount = 1;
                } else {
                    totalClickCount = totalClickCount + 1;
                }
                System.out.println("----------" + totalClickCount);
                isMultiClick = true;
                handler.removeMessages(WHAT_CLICK);
                handler.sendEmptyMessageDelayed(WHAT_CLICK, 1000);
                break;
            case R.id.img_open_hundred:
                totalClickCount = 100;
                if (!isMultiClick) {
                    showServerSVG(ivEgg);
                    totalClickCount = 100;
                } else {
                    totalClickCount = totalClickCount + 100;
                }
                System.out.println("----------" + totalClickCount);
                isMultiClick = true;
                handler.removeMessages(WHAT_CLICK);
                handler.sendEmptyMessageDelayed(WHAT_CLICK, 1000);
                break;
        }
    }

    //未开宝箱
    public void showWeiSVG(SVGAParser parser, String giftUrl, SVGAImageView svgaImageView) {
        try {
            parser.decodeFromAssets(giftUrl, new SVGAParser.ParseCompletion() {
                @Override
                public void onComplete(@NotNull SVGAVideoEntity svgaVideoEntity) {
                    svgaImageView.setVideoItem(svgaVideoEntity);
                    svgaImageView.setLoops(0);
                    svgaImageView.stepToFrame(1, true);
                }

                @Override
                public void onError() {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.debugInfo("====SVAG错误", e.getMessage());
        }
    }

    private static final int WHAT_CLICK = 1;
    private boolean isMultiClick = false;
    private int totalClickCount = 0;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WHAT_CLICK:
                    isMultiClick = false;
                    int count = totalClickCount;
//                    touMingDialog = new TouMingDialog(mContext);
                    setCanceledOnTouchOutside(false);
                    imgOpenOne.setClickable(false);
                    imgTenOpen.setClickable(false);
                    imgOpenHundred.setClickable(false);
//                    touMingDialog.show();
                    if (currentEggType == 0) {
                        getAwardList(count);
                    } else {
                        getAwardListColor(count);
                    }
                    break;
            }
        }
    };

    private OpenBoxBean mOpenBoxBean;
    private int currentEggType = 0;//0金蛋，1彩蛋

    //开过宝箱
    public void showServerSVG(ImageView imageView) {
        if (currentEggType == 0) {
            Glide.with(mContext).load(R.drawable.gold_egg_ani).into(imageView);
        } else {
            Glide.with(mContext).load(R.drawable.color_egg_ani).into(imageView);
        }
    }

    private void initTab() {
        tvGoldIndicator.setVisibility(View.GONE);
        tvColorIndicator.setVisibility(View.GONE);
    }

    /**
     * 宝箱动画完毕之后
     */
    private void setSvgImgClickble(ImageView imageView, OpenBoxBean openBoxBean) {
        LogUtils.debugInfo("====打不出来哇", "哦哦哦哦哦哦哦");
        if (currentEggType == 0) {
            Glide.with(mContext).load(R.mipmap.gold_egg).into(imageView);
        } else {
            Glide.with(mContext).load(R.mipmap.color_egg).into(imageView);
        }
        if(openBoxBean==null){
            return;
        }
//        if (touMingDialog != null) {
//            touMingDialog.dismiss();
//        }
        BoxGiftActivity boxGiftActivity = new BoxGiftActivity(mContext, openBoxBean,commonModel,mErrorHandler,currentEggType);
        boxGiftActivity.show();
        dismiss();
//        touMingDialog = new TouMingDialog(mContext);
//        touMingDialog.show();
    }

    //获取宝箱信息
    private void getBaoXiang() {
        RxUtils.loading(commonModel.getBoxInfo("xx"), mContext)
                .subscribe(new ErrorHandleSubscriber<BaoXiangBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaoXiangBean baoXiangBean) {
                        if (baoXiangBean.getCode() == 1) {
                            mDataBean = baoXiangBean.getData();
//                            mTvGemstoneIntegral.setText("宝箱积分： " + baoXiangBean.getData().getPoints());
                            tvKeyCount.setText(String.valueOf(baoXiangBean.getData().getKeys_num()));
                            initTime();
                            if (baoXiangBean.getData().getBoxclass() == 1) {
                                SVGAParser parser = new SVGAParser(mContext);
//                                showWeiSVG(parser, "putong_weikai.svga", mSVGAImageView);
                            } else if (baoXiangBean.getData().getBoxclass() == 2) {
                                SVGAParser parser = new SVGAParser(mContext);
//                                showWeiSVG(parser, "shouhu_weikai.svga", mSVGAImageView);
                            }
//                           int points= Integer.parseInt(baoXiangBean.getData().getPoints());

                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        LogUtils.debugInfo("====数据请求错误", t.getMessage());
                    }
                });
    }

    //开启金蛋
    private void getAwardList(int keyNum) {
        setCanceledOnTouchOutside(false);
        RxUtils.loading(commonModel.getAwardList(keyNum), mContext)
                .subscribe(new ErrorHandleSubscriber<OpenBoxBean>(mErrorHandler) {
                    @Override
                    public void onNext(OpenBoxBean openBoxBean) {
                        if (openBoxBean.getCode() == 1) {
                            mOpenBoxBean = openBoxBean;
                            setSvgImgClickble(ivEgg, mOpenBoxBean);
                            //更新房间公屏消息
                            List<OpenBoxBean.DataBean.AwardListBean> awardList = openBoxBean.getData().getAwardList();
                            if (awardList != null && awardList.size() > 0) {
                                List<OpenBoxBean.DataBean.AwardListBean> newAwardList = new ArrayList<>();
                                //单个礼物的价值大于20金币，才发频道消息
                                for (OpenBoxBean.DataBean.AwardListBean itemBean : awardList) {

                                    double price = Arith.strToDouble(itemBean.getPrice());

                                    if (price >= 20) {
                                        newAwardList.add(itemBean);
                                    }

                                }

//                                StringBuilder builder = new StringBuilder();
//                                builder.append("哇哦，");
//                                builder.append(UserManager.getUser().getNickname());
//                                builder.append("在");
//                                if (openBoxBean.getData().getBox_class() == 1) {
//                                    builder.append("普通宝箱");
//                                } else {
//                                    builder.append("守护宝箱");
//                                }
//                                builder.append("开出了");
//                                for (OpenBoxBean.DataBean.AwardListBean awardListBean : awardList) {
//                                    String number = "x" + awardListBean.getNum();
//                                    String name = awardListBean.getName();
//                                    builder.append(name);
//                                    builder.append(number);
//                                    builder.append(",");
//                                }
//                                builder.append("真是太优秀了！");

                                //有礼物的价值大于20金币
                                if (newAwardList.size() > 0 || !TextUtils.isEmpty(openBoxBean.getData().getAward_tips())) {

                                    LoginData loginData = UserManager.getUser();
                                    MessageBean messageBean = new MessageBean();
                                    messageBean.setNickName(loginData.getNickname());
                                    messageBean.setUser_id(loginData.getUserId() + "");
                                    messageBean.box_class = String.valueOf(1);
                                    messageBean.awardList = newAwardList;
                                    messageBean.setMessage(openBoxBean.getData().getAward_tips());
//                                    messageBean.award_tips = openBoxBean.getData().getAward_tips();
                                    messageBean.setMessageType("13");
                                    MessageBean.PushAwards pushAwards = new MessageBean.PushAwards();
                                    pushAwards.user_name = openBoxBean.getData().getPush_awards().user_name;
                                    pushAwards.gift_name = openBoxBean.getData().getPush_awards().gift_name;
                                    messageBean.push_awards = pushAwards;

                                    MessageEvent messageEvent = new MessageEvent();
                                    messageEvent.setStateMessage(StateMessage.PEOPLE_OPEN_GEMSTONE);
                                    messageEvent.setObject(messageBean);
                                    EventBus.getDefault().post(messageEvent);

                                }

                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        imgOpenOne.setClickable(true);
                        imgTenOpen.setClickable(true);
                        imgOpenHundred.setClickable(true);
                        setSvgImgClickble(ivEgg, null);
                        BToast.showText(mContext, t.getMessage());
//                        touMingDialog.dismiss();
                        setCanceledOnTouchOutside(true);
                        boo(true);
                    }
                });
    }

    //开启彩蛋
    private void getAwardListColor(int keyNum) {
        setCanceledOnTouchOutside(false);
        RxUtils.loading(commonModel.getAwardListColor(keyNum), mContext)
                .subscribe(new ErrorHandleSubscriber<OpenBoxBean>(mErrorHandler) {
                    @Override
                    public void onNext(OpenBoxBean openBoxBean) {
                        if (openBoxBean.getCode() == 1) {
                            mOpenBoxBean = openBoxBean;
                            setSvgImgClickble(ivEgg, mOpenBoxBean);
                            //更新房间公屏消息
                            List<OpenBoxBean.DataBean.AwardListBean> awardList = openBoxBean.getData().getAwardList();
                            if (awardList != null && awardList.size() > 0) {

                                List<OpenBoxBean.DataBean.AwardListBean> newAwardList = new ArrayList<>();
                                //单个礼物的价值大于20金币，才发频道消息
                                for (OpenBoxBean.DataBean.AwardListBean itemBean : awardList) {

                                    double price = Arith.strToDouble(itemBean.getPrice());

                                    if (price >= 20) {
                                        newAwardList.add(itemBean);
                                    }

                                }

//                                StringBuilder builder = new StringBuilder();
//                                builder.append("哇哦，");
//                                builder.append(UserManager.getUser().getNickname());
//                                builder.append("在");
//                                if (openBoxBean.getData().getBox_class() == 1) {
//                                    builder.append("普通宝箱");
//                                } else {
//                                    builder.append("守护宝箱");
//                                }
//                                builder.append("开出了");
//                                for (OpenBoxBean.DataBean.AwardListBean awardListBean : awardList) {
//                                    String number = "x" + awardListBean.getNum();
//                                    String name = awardListBean.getName();
//                                    builder.append(name);
//                                    builder.append(number);
//                                    builder.append(",");
//                                }
//                                builder.append("真是太优秀了！");

                                //有礼物的价值大于20金币
                                if (newAwardList.size() > 0 || !TextUtils.isEmpty(openBoxBean.getData().getAward_tips())) {

                                    LoginData loginData = UserManager.getUser();
                                    MessageBean messageBean = new MessageBean();
                                    messageBean.setNickName(loginData.getNickname());
                                    messageBean.setUser_id(loginData.getUserId() + "");
                                    messageBean.box_class = String.valueOf(2);
                                    messageBean.awardList = newAwardList;
                                    messageBean.setMessage(openBoxBean.getData().getAward_tips());
//                                    messageBean.award_tips = openBoxBean.getData().getAward_tips();
                                    messageBean.setMessageType("13");
                                    MessageBean.PushAwards pushAwards = new MessageBean.PushAwards();
                                    pushAwards.user_name = openBoxBean.getData().getPush_awards().user_name;
                                    pushAwards.gift_name = openBoxBean.getData().getPush_awards().gift_name;
                                    messageBean.push_awards = pushAwards;

                                    MessageEvent messageEvent = new MessageEvent();
                                    messageEvent.setStateMessage(StateMessage.PEOPLE_OPEN_GEMSTONE);
                                    messageEvent.setObject(messageBean);
                                    EventBus.getDefault().post(messageEvent);

                                }

                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        imgOpenOne.setClickable(true);
                        imgTenOpen.setClickable(true);
                        imgOpenHundred.setClickable(true);
                        setSvgImgClickble(ivEgg, null);
                        BToast.showText(mContext, t.getMessage());
//                        touMingDialog.dismiss();
                        setCanceledOnTouchOutside(true);
                        boo(true);
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
        if (DUIHUAN.equals(tag)) {
            getBaoXiang();
        } else if (GOUMAIYAOSHI.equals(tag)) {
            getBaoXiang();
        } else if (XIANSHIWANBI.equals(tag)) {
//            getBaoXiang();
            loadYue();
        } else if ("donghuawancheng".equals(tag)) {
//            if (touMingDialog != null) {
//                touMingDialog.dismiss();
//            }
            this.setCanceledOnTouchOutside(true);
            boo(true);
        }
    }

    private void boo(boolean booo) {
        imgTenOpen.setEnabled(booo);
        imgOpenOne.setEnabled(booo);
        imgOpenHundred.setEnabled(booo);
    }
}
