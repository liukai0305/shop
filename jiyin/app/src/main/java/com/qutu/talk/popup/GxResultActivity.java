package com.qutu.talk.popup;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.utils.ArmsUtils;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.qutu.talk.R;
import com.qutu.talk.activity.ChargeActivity;
import com.qutu.talk.adapter.BXAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
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

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.qutu.talk.utils.Constant.XIANSHIWANBI;

//宝箱开后显示的那个页面
public class GxResultActivity extends Dialog {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.bj)
    RelativeLayout bj;
    @BindView(R.id.svga)
    SVGAImageView svgaImageView;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_recharge)
    TextView tvRecharge;
    @BindView(R.id.img_ten_open)
    ImageView imgTenOpen;
    @BindView(R.id.img_open_one)
    ImageView imgOpenOne;
    @BindView(R.id.img_open_hundred)
    ImageView imgOpenHundred;
    private MyBaseArmActivity mContext;
    private OpenBoxBean mOpenBoxBean;
    private BXAdapter mAdapter;
    private CommonModel mCommonModel;
    private RxErrorHandler rxErrorHandler;
    private int currentTypeEgg=0;
    private boolean isReShow=false;
    private String familyId="";

    public GxResultActivity(
            @NonNull MyBaseArmActivity context,
            OpenBoxBean openBoxBean,
            CommonModel commonModel,
            RxErrorHandler rxErrorHandler,
            int eggType,
            String familyId
    ) {
        super(context, R.style.myChooseDialog);
        mContext = context;
        this.mOpenBoxBean = openBoxBean;
        this.mCommonModel = commonModel;
        this.rxErrorHandler = rxErrorHandler;
        this.currentTypeEgg=eggType;
        this.familyId=familyId;
    }

    @OnClick({R.id.tv_recharge, R.id.img_ten_open, R.id.img_open_one, R.id.img_open_hundred,R.id.iv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_recharge:
                Intent intent = new Intent(mContext, ChargeActivity.class);
                intent.putExtra("type", 0);
                ArmsUtils.startActivity(intent);
                break;
            case R.id.img_open_one:
                imgOpenOne.setClickable(false);
                if (currentTypeEgg == 0) {
                    getAwardList(1);
                } else {
                    getAwardListColor(1);
                }
                break;
            case R.id.img_ten_open:
                imgTenOpen.setClickable(false);
                if (currentTypeEgg == 0) {
                    getAwardList(10);
                } else {
                    getAwardListColor(10);
                }
                break;
            case R.id.img_open_hundred:
                imgOpenHundred.setClickable(false);
                if (currentTypeEgg == 0) {
                    getAwardList(100);
                } else {
                    getAwardListColor(100);
                }
                break;
        }
    }

    //开启金蛋
    private void getAwardList(int keyNum) {
        setCanceledOnTouchOutside(false);
        RxUtils.loading(mCommonModel.getAwardList(keyNum), mContext)
                .subscribe(new ErrorHandleSubscriber<OpenBoxBean>(rxErrorHandler) {
                    @Override
                    public void onNext(OpenBoxBean openBoxBean) {
                        loadYue();
                        if (openBoxBean.getCode() == 1) {
                            mOpenBoxBean = openBoxBean;
                            isReShow=true;
                            GxResultActivity boxGiftActivity = new GxResultActivity(mContext, openBoxBean,mCommonModel,rxErrorHandler,currentTypeEgg,familyId);
                            boxGiftActivity.show();
                            dismiss();
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
                        BToast.showText(mContext, t.getMessage());
//                        touMingDialog.dismiss();
                        setCanceledOnTouchOutside(false);
                        boo(true);
                    }
                });
    }

    private void boo(boolean booo) {
        imgTenOpen.setEnabled(booo);
        imgOpenOne.setEnabled(booo);
        imgOpenHundred.setEnabled(booo);
    }


    //开启彩蛋
    private void getAwardListColor(int keyNum) {
        setCanceledOnTouchOutside(false);
        RxUtils.loading(mCommonModel.getAwardListColor(keyNum), mContext)
                .subscribe(new ErrorHandleSubscriber<OpenBoxBean>(rxErrorHandler) {
                    @Override
                    public void onNext(OpenBoxBean openBoxBean) {
                        loadYue();
                        if (openBoxBean.getCode() == 1) {
                            mOpenBoxBean = openBoxBean;
                            isReShow=true;
                            GxResultActivity boxGiftActivity = new GxResultActivity(mContext, openBoxBean,mCommonModel,rxErrorHandler,currentTypeEgg,familyId);
                            boxGiftActivity.show();
                            dismiss();
                            //更新房间公屏消息
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        BToast.showText(mContext, t.getMessage());
//                        touMingDialog.dismiss();
                        setCanceledOnTouchOutside(false);
                        boo(true);
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_gift);
        ButterKnife.bind(this);
        setWidows();
        setData();
        addLayoutAnimation(recyclerview);
    }

    /**
     * 请求余额
     */
    private void loadYue() {
        RxUtils.loading(mCommonModel.goodsList(String.valueOf(UserManager.getUser().getUserId())), mContext)
                .subscribe(new ErrorHandleSubscriber<GoodsList>(rxErrorHandler) {
                    @Override
                    public void onNext(GoodsList goodsList) {
                        tvMoney.setText("余额  " + goodsList.getData().getMizuan());
                    }
                });
    }

    //设置数据
    private void setData() {
        loadYue();
        SVGAParser parser = new SVGAParser(mContext);
        parser.decodeFromAssets("light.svga", new SVGAParser.ParseCompletion() {
            @Override
            public void onComplete(@NotNull SVGAVideoEntity svgaVideoEntity) {
                if (svgaImageView != null){
                    svgaImageView.setVideoItem(svgaVideoEntity);
                    svgaImageView.setLoops(0);
                    svgaImageView.stepToFrame(1, true);
                }
            }

            @Override
            public void onError() {

            }
        });

        if (0 < mOpenBoxBean.getData().getAwardList().size() && 5 > mOpenBoxBean.getData().getAwardList().size()) {
            bj.setBackgroundResource(R.mipmap.bx_yhtc);
        } else if (4 < mOpenBoxBean.getData().getAwardList().size() && 9 > mOpenBoxBean.getData().getAwardList().size()) {
            bj.setBackgroundResource(R.mipmap.bx_ehtc);
        } else {
            bj.setBackgroundResource(R.mipmap.bx_shtc);
        }

        mAdapter = new BXAdapter();
        GridLayoutManager GM = new GridLayoutManager(mContext, 4);
        recyclerview.setLayoutManager(GM);
        recyclerview.setAdapter(mAdapter);
        mAdapter.setNewData(mOpenBoxBean.getData().getAwardList());


    }

    private void setWidows() {

        Display display = getWindow().getWindowManager().getDefaultDisplay();

        WindowManager.LayoutParams lp = getWindow().getAttributes();

        lp.width = (int) (display.getWidth());

        lp.alpha = 1.0f;

        lp.gravity = Gravity.CENTER;

        getWindow().setAttributes(lp);

        this.setCanceledOnTouchOutside(false);

    }

    private void addLayoutAnimation(ViewGroup view) {

        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.item_animation_from_right);
        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(animation);
        layoutAnimationController.setDelay(1f);
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
        view.setLayoutAnimation(layoutAnimationController);
        view.setLayoutAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (mOpenBoxBean.getData().getIspointsfirst() == 1) {
                    BoxFirstOpenWindow bow = new BoxFirstOpenWindow(mContext);
                    bow.showAtLocation(recyclerview, Gravity.CENTER, 0, 0);
                }
                EventBus.getDefault().post(new FirstEvent("动画完成", "donghuawancheng"));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


    @Override
    public void dismiss() {
        super.dismiss();
        EventBus.getDefault().post(new FirstEvent("显示完毕", XIANSHIWANBI));
        if(!isReShow){
            FamilyUpgradeDialog familyUpgradeDialog = new FamilyUpgradeDialog(mContext, mCommonModel, rxErrorHandler,currentTypeEgg,Integer.parseInt(familyId));
            familyUpgradeDialog.show();
        }
    }
}
