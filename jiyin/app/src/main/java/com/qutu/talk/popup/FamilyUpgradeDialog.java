package com.qutu.talk.popup;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qutu.talk.R;
import com.qutu.talk.activity.ChargeActivity;
import com.qutu.talk.activity.GxRecordActivity;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.BaoXiangBean;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.NldBean;
import com.qutu.talk.bean.OpenBoxBean;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.Arith;
import com.qutu.talk.utils.BToast;

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


public class FamilyUpgradeDialog extends Dialog {

    @BindView(R.id.tv_history)
    TextView tvHistory;
    @BindView(R.id.tv_key_count)
    TextView tvKeyCount;
    @BindView(R.id.layout_key_count)
    RelativeLayout layoutKeyCount;
    @BindView(R.id.ll_tab)
    LinearLayout llTab;
    @BindView(R.id.cv_open_hundred)
    CardView cvOpenHundred;
    @BindView(R.id.tv_cut_down_time)
    TextView tvCutDownTime;
    @BindView(R.id.img_add_key)
    ImageView imgAddKey;
    @BindView(R.id.cv_open_ten)
    CardView cvOpenTen;
    @BindView(R.id.cv_open_one)
    CardView cvOpenOne;
    @BindView(R.id.view_empty)
    View viewEmpty;
    @BindView(R.id.layout_baoxiao)
    LinearLayout layoutBaoxiao;
    @BindView(R.id.tv_gold_indicator)
    TextView tvGoldIndicator;
    @BindView(R.id.tv_color_indicator)
    TextView tvColorIndicator;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    @BindView(R.id.tv_one_price)
    TextView tvOnePrice;
    @BindView(R.id.tv_ten_price)
    TextView tvTenPrice;
    @BindView(R.id.tv_hundred_price)
    TextView tvHundredPrice;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.iv_egg)
    LottieAnimationView ivEgg;
    @BindView(R.id.tv_tab_gold)
    TextView tvTabGold;
    @BindView(R.id.tv_tab_color)
    TextView tvTabColor;

    private MyBaseArmActivity mContext;
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

    private int familyId=0;

    public FamilyUpgradeDialog(@NonNull MyBaseArmActivity context, CommonModel commonModel, RxErrorHandler errorHandler, int eggType,int familyId) {
        super(context, R.style.myChooseDialog);
        mContext = context;
        this.commonModel = commonModel;
        this.mErrorHandler = errorHandler;
        this.currentEggType=eggType;
        this.familyId=familyId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_family_upgrade);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        ivEgg.setImageAssetsFolder("star/");
        ivEgg.setAnimation("star.json");
        rlRoot.setOnClickListener(v -> {

        });
        if (currentEggType == 0) {
            initTab();
            tvTabGold.setTextColor(0xffee0092);
            tvGoldIndicator.setVisibility(View.VISIBLE);
            tvOnePrice.setText("1个能量豆");
            tvTenPrice.setText("10个能量豆");
            tvHundredPrice.setText("100个能量豆");
        } else {
            initTab();
            tvTabColor.setTextColor(0xffee0092);
            tvColorIndicator.setVisibility(View.VISIBLE);
            tvOnePrice.setText("10个能量豆");
            tvTenPrice.setText("100个能量豆");
            tvHundredPrice.setText("1000个能量豆");
        }
        setWidows();

//        initTime();

        getBaoXiang();
        getYuE();

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

    @OnClick({
            R.id.img_add_key,
            R.id.cv_open_ten,
            R.id.cv_open_one,
            R.id.cv_open_hundred,
            R.id.ll_guize,
            R.id.tv_history,
            R.id.ll_tab_gold,
            R.id.ll_tab_color,
            R.id.layout_baoxiao,
            R.id.ll_jiangchi,
            R.id.cv_lucky
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_tab_gold:
                initTab();
                tvTabGold.setTextColor(0xffee0092);
                tvGoldIndicator.setVisibility(View.VISIBLE);
                currentEggType = 0;
                tvOnePrice.setText("1个能量豆");
                tvTenPrice.setText("10个能量豆");
                tvHundredPrice.setText("100个能量豆");
                break;
            case R.id.ll_tab_color:
                initTab();
                tvTabColor.setTextColor(0xffee0092);
                tvColorIndicator.setVisibility(View.VISIBLE);
                currentEggType = 1;
                tvOnePrice.setText("10个能量豆");
                tvTenPrice.setText("100个能量豆");
                tvHundredPrice.setText("1000个能量豆");
                break;
            case R.id.ll_guize:
                FamilyUpgradeRuleWindow familyUpgradeRuleWindow = new FamilyUpgradeRuleWindow(mContext, (ViewGroup) getWindow().getDecorView(), commonModel, mErrorHandler);
                familyUpgradeRuleWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                break;
            case R.id.tv_history:
                Intent intent_record=new Intent(mContext, GxRecordActivity.class);
                intent_record.putExtra("familyId",familyId+"");
                intent_record.putExtra("type",(currentEggType+1)+"");
                intent_record.putExtra("lucky_type",0);
                ArmsUtils.startActivity(intent_record);
                break;
            case R.id.cv_lucky:
                Intent intent_lucky=new Intent(mContext, GxRecordActivity.class);
                intent_lucky.putExtra("familyId",familyId+"");
                intent_lucky.putExtra("type",(currentEggType+1)+"");
                intent_lucky.putExtra("lucky_type",1);
                ArmsUtils.startActivity(intent_lucky);
                break;
            case R.id.ll_jiangchi:
                dismiss();
                FamilyJackpotWindow familyJackpotWindow = new FamilyJackpotWindow(mContext, (ViewGroup) getWindow().getDecorView(), commonModel, mErrorHandler, currentEggType,rlRoot.getMeasuredHeight(),familyId);
                familyJackpotWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
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
            case R.id.cv_open_ten:
                if (!isMultiClick) {
                    ivEgg.playAnimation();
                    totalClickCount = 10;
                } else {
                    totalClickCount = totalClickCount + 10;
                }
                System.out.println("----------" + totalClickCount);
                isMultiClick = true;
                handler.removeMessages(WHAT_CLICK);
                handler.sendEmptyMessageDelayed(WHAT_CLICK, 1000);

                break;
            case R.id.cv_open_one:
                if (!isMultiClick) {
                    ivEgg.playAnimation();
                    totalClickCount = 1;
                } else {
                    totalClickCount = totalClickCount + 1;
                }
                System.out.println("----------" + totalClickCount);
                isMultiClick = true;
                handler.removeMessages(WHAT_CLICK);
                handler.sendEmptyMessageDelayed(WHAT_CLICK, 1000);
                break;
            case R.id.cv_open_hundred:
                if (!isMultiClick) {
                    ivEgg.playAnimation();
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
                    cvOpenOne.setClickable(false);
                    cvOpenTen.setClickable(false);
                    cvOpenHundred.setClickable(false);
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

    private void initTab() {
        tvGoldIndicator.setVisibility(View.INVISIBLE);
        tvColorIndicator.setVisibility(View.INVISIBLE);
        tvTabColor.setTextColor(0xffffffff);
        tvTabGold.setTextColor(0xffffffff);
    }

    /**
     * 宝箱动画完毕之后
     */
    private void setSvgImgClickble( OpenBoxBean openBoxBean) {
        LogUtils.debugInfo("====打不出来哇", "哦哦哦哦哦哦哦");
        if(openBoxBean==null){
            return;
        }
//        if (touMingDialog != null) {
//            touMingDialog.dismiss();
//        }
        GxResultActivity gxResultActivity =
                new GxResultActivity(mContext, openBoxBean,commonModel,mErrorHandler,currentEggType,familyId+"");
        gxResultActivity.show();
        dismiss();
//        touMingDialog = new TouMingDialog(mContext);
//        touMingDialog.show();
    }

    private void getYuE(){
        RxUtils.loading(commonModel.getNld(UserManager.getUser().getUserId()+""),mContext)
                .subscribe(new ErrorHandleSubscriber<NldBean>(mErrorHandler) {
                    @Override
                    public void onNext(NldBean nldBean) {
                        if(nldBean.getCode()==1){
                            tvYue.setText("剩余能量豆:"+nldBean.getData());
                        }
                    }
                });

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

    //普通贡献
    private void getAwardList(int keyNum) {
        setCanceledOnTouchOutside(false);
        RxUtils.loading(commonModel.getCommonGx(keyNum,familyId+""), mContext)
                .subscribe(new ErrorHandleSubscriber<OpenBoxBean>(mErrorHandler) {
                    @Override
                    public void onNext(OpenBoxBean openBoxBean) {
                        ivEgg.pauseAnimation();
                        ivEgg.setFrame(0);
                        if (openBoxBean.getCode() == 1) {
                            getYuE();
                            mOpenBoxBean = openBoxBean;
                            setSvgImgClickble(mOpenBoxBean);
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

                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        ivEgg.pauseAnimation();
                        ivEgg.setFrame(0);
                        cvOpenOne.setClickable(true);
                        cvOpenTen.setClickable(true);
                        cvOpenHundred.setClickable(true);
                        setSvgImgClickble(null);
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
        RxUtils.loading(commonModel.getQuickGx(keyNum,familyId+""), mContext)
                .subscribe(new ErrorHandleSubscriber<OpenBoxBean>(mErrorHandler) {
                    @Override
                    public void onNext(OpenBoxBean openBoxBean) {
                        ivEgg.pauseAnimation();
                        ivEgg.setFrame(0);
                        if (openBoxBean.getCode() == 1) {
                            getYuE();
                            mOpenBoxBean = openBoxBean;
                            setSvgImgClickble(mOpenBoxBean);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        ivEgg.pauseAnimation();
                        ivEgg.setFrame(0);
                        cvOpenOne.setClickable(true);
                        cvOpenTen.setClickable(true);
                        cvOpenHundred.setClickable(true);
                        setSvgImgClickble(null);
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
        } else if ("donghuawancheng".equals(tag)) {
//            if (touMingDialog != null) {
//                touMingDialog.dismiss();
//            }
            this.setCanceledOnTouchOutside(true);
            boo(true);
        }
    }

    private void boo(boolean booo) {
        cvOpenTen.setEnabled(booo);
        cvOpenOne.setEnabled(booo);
        cvOpenHundred.setEnabled(booo);
    }
}
