package com.qutu.talk.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alipay.sdk.app.AuthTask;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.qutu.talk.R;
import com.qutu.talk.activity.ChargeActivity;
import com.qutu.talk.activity.ChargeMiLiActivity;
import com.qutu.talk.activity.mine.BindSuccessActivity;
import com.qutu.talk.activity.mine.CashHisActivity;
import com.qutu.talk.activity.mine.CashMoneyActivity;
import com.qutu.talk.activity.mine.CouponsActivity;
import com.qutu.talk.activity.mine.DiamondHelpRechargeActivity;
import com.qutu.talk.activity.mine.MiLiRecordActivity;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmFragment;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.AliInfor;
import com.qutu.talk.bean.BaseBean;
import com.qutu.talk.bean.BindBean;
import com.qutu.talk.bean.ChaMoneyBean;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.IsOpenDc;
import com.qutu.talk.bean.MoneyBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.AuthResult;
import com.qutu.talk.utils.BaseUtils;
import com.qutu.talk.utils.Constant;
import com.qutu.talk.view.ShapeTextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.qutu.talk.utils.Constant.JIEBANGALI;

/**
 * 我的金币/我的金币页面
 * mType = 1    我的金币
 * mType = 2    我的金币
 */
public class MiBiAndMiLiFragment extends MyBaseArmFragment {

    @Inject
    CommonModel commonModel;
    @BindView(R.id.mi_and_bi_tips_zuan)
    TextView miAndBiTipsZuan;
    @BindView(R.id.mi_and_li_name_zuan)
    TextView miAndLiNameZuan;
    @BindView(R.id.textZuanNum)
    TextView textZuanNum;
    @BindView(R.id.imgCHongzhi_li)
    ImageView imgCHongzhiLi;
    @BindView(R.id.imgDaiChong_li)
    ImageView imgDaiChongLi;
    @BindView(R.id.imgDuihuan_li)
    ImageView imgDuihuanLi;
    @BindView(R.id.imgJilu_zuan)
    ImageView imgJiluZuan;
    @BindView(R.id.bi_and_li_top)
    RelativeLayout biAndLiTop;
    @BindView(R.id.textBiNum)
    TextView textBiNum;
    @BindView(R.id.imgTixian)
    ImageView imgTixian;
    @BindView(R.id.imgDuihuan)
    ImageView imgDuihuan;
    @BindView(R.id.mi_and_bi_tips_bi)
    TextView miAndBiTipsBi;
    @BindView(R.id.imgJilu)
    ImageView imgJilu;
    @BindView(R.id.mi_and_li_zhong)
    RelativeLayout miAndLiZhong;
    @BindView(R.id.textAliName)
    TextView textAliName;
    @BindView(R.id.llBang)
    LinearLayout llBang;
    Unbinder unbinder;
    @BindView(R.id.mili_zhi)
    TextView miliZhi;
    @BindView(R.id.textDiscountName)
    TextView textDiscountName;
    @BindView(R.id.llDiscount)
    LinearLayout llDiscount;
    @BindView(R.id.textNldNum)
    TextView textNldNum;
    @BindView(R.id.rl_nld)
    RelativeLayout rlNld;

    private int mType;
    private MoneyBean mMoneyBean;

    //兑换所需的
    private int allSign = 0;
    private String moneyStr;
    private TextView uselessText;
    private TextView zengText;
    private TextView zuanNum;

    private Intent mIntent;

    @Override
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_mi_bi_and_mi_li);
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
    public void initData(@Nullable Bundle savedInstanceState) {
        mType = getArguments().getInt("type");
        isOpenDc();
        if (mType == 1) {
//            miAndLiZhong.setVisibility(View.VISIBLE);
//            miAndBiTipsZuan.setText("用于房间打赏");
//            imgDuihuanLi.setVisibility(View.GONE);
//            miAndLiNameZuan.setText("我的金币");

        } else {
            miAndBiTipsZuan.setText("用于订单服务");
            miAndBiTipsBi.setText("订单收入");
            imgDuihuanLi.setVisibility(View.VISIBLE);
            imgTixian.setVisibility(View.GONE);
            imgDuihuan.setVisibility(View.GONE);
            imgDaiChongLi.setVisibility(View.GONE);
            miAndLiNameZuan.setText("我的金币");
            miliZhi.setText("钻石");
        }

        loadData(mType);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    public static MiBiAndMiLiFragment getInstance(int type) {
        MiBiAndMiLiFragment fragment = new MiBiAndMiLiFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    //获取数据
    private void loadData(int typea) {
        RxUtils.loading(commonModel.my_store(String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<MoneyBean>(mErrorHandler) {
                    @Override
                    public void onNext(MoneyBean moneyBean) {
                        mMoneyBean = moneyBean;
                        if (typea == 1) {
                            textBiNum.setText(moneyBean.getData().get(0).getMibi());
                            textZuanNum.setText(moneyBean.getData().get(0).getMizuan());
                            textNldNum.setText(moneyBean.getData().get(0).getNld());
                        } else {
                            textBiNum.setText(moneyBean.getData().get(0).getMlz());
                            textZuanNum.setText(moneyBean.getData().get(0).getMili());
                            textNldNum.setText(moneyBean.getData().get(0).getNld());
                        }
                        if (!TextUtils.isEmpty(moneyBean.getData().get(0).getAli_nick_name())) {
                            textAliName.setText(moneyBean.getData().get(0).getAli_nick_name());
                        } else {
                            textAliName.setText("");
                        }
                        if (!TextUtils.isEmpty(moneyBean.getData().get(0).getCoupons())) {
                            textDiscountName.setText(moneyBean.getData().get(0).getCoupons() + "张");
                        } else {
                            textDiscountName.setText("");
                        }
                    }
                });
    }

    //是否开启代充
    private void isOpenDc() {
        RxUtils.loading(commonModel.isOpenDc(String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<IsOpenDc>(mErrorHandler) {
                    @Override
                    public void onNext(IsOpenDc isOpenDc) {
                        if (isOpenDc.getData().getIskq() == 1) {
                            imgDaiChongLi.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    @OnClick({R.id.imgCHongzhi_li, R.id.imgDuihuan_li, R.id.imgTixian, R.id.imgJilu, R.id.imgJilu_zuan, R.id.llBang, R.id.imgDuihuan, R.id.llDiscount, R.id.imgDaiChong_li})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgCHongzhi_li:
                if (mType == 1) {
                    ArmsUtils.startActivity(ChargeActivity.class);
                } else {
                    ArmsUtils.startActivity(ChargeMiLiActivity.class);
                }
                break;
            case R.id.imgDaiChong_li:
                ArmsUtils.startActivity(DiamondHelpRechargeActivity.class);
                break;
            case R.id.imgDuihuan_li:
                if (mMoneyBean.getData().get(0).getIs_ali() == 1) {
                    tiXian();
                } else {
                    showToast("请先绑定支付宝！");
                }
                break;
            case R.id.imgTixian:
                if (mMoneyBean.getData().get(0).getIs_ali() == 1) {
                    tiXian();
                } else {
                    showToast("请先绑定支付宝！");
                }
                break;
            case R.id.imgJilu:
                if (mType == 1) {
                    mIntent = new Intent(getActivity(), CashHisActivity.class);
                    mIntent.putExtra("type", mType);
                    ArmsUtils.startActivity(mIntent);
                } else {
                    mIntent = new Intent(getActivity(), MiLiRecordActivity.class);
                    mIntent.putExtra("type", mType);
                    ArmsUtils.startActivity(mIntent);
                }
                break;
            case R.id.imgJilu_zuan:
                if (mType == 1) {
                    mIntent = new Intent(getActivity(), CashHisActivity.class);
                    mIntent.putExtra("type", mType);
                    ArmsUtils.startActivity(mIntent);
                } else {
                    mIntent = new Intent(getActivity(), MiLiRecordActivity.class);
                    mIntent.putExtra("type", mType);
                    ArmsUtils.startActivity(mIntent);
                }
                break;
            case R.id.llBang:
                if (mMoneyBean.getData().get(0).getIs_ali() == 1) {
                    mIntent = new Intent(getActivity(), BindSuccessActivity.class);
                    mIntent.putExtra("head", mMoneyBean.getData().get(0).getAli_avatar());
                    mIntent.putExtra("name", mMoneyBean.getData().get(0).getAli_nick_name());
//                    intent.putExtra("phone",mMoneyBean.getData().get(0).get)
                    ArmsUtils.startActivity(mIntent);
                } else {
                    RxUtils.loading(commonModel.ali_oauth_code(null), this)
                            .subscribe(new ErrorHandleSubscriber<BindBean>(mErrorHandler) {
                                @Override
                                public void onNext(BindBean moneyBean) {
                                    Runnable payRunnable = new Runnable() {
                                        @Override
                                        public void run() {
                                            AuthTask alipay = new AuthTask(getActivity());
                                            Map<String, String> result = alipay.authV2(moneyBean.getData().getSign(), true);
                                            Message msg = new Message();
                                            msg.what = SDK_PAY_FLAG;
                                            msg.obj = result;
                                            mHandler.sendMessage(msg);
                                        }
                                    };
                                    // 必须异步调用
                                    Thread payThread = new Thread(payRunnable);
                                    payThread.start();
                                }
                            });
                }
                break;
            case R.id.imgDuihuan:
                MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                        .customView(R.layout.dialog_duhuan, true)
                        .show();
                TextView textId = (TextView) dialog.findViewById(R.id.textId);
                EditText textBiNum = (EditText) dialog.findViewById(R.id.textBiNum);
                zuanNum = (TextView) dialog.findViewById(R.id.textZuanNum);
                ShapeTextView btn_ok = (ShapeTextView) dialog.findViewById(R.id.btn_ok);
                TextView textAll = (TextView) dialog.findViewById(R.id.textAll);
                uselessText = (TextView) dialog.findViewById(R.id.one);
                zengText = (TextView) dialog.findViewById(R.id.zeng_money);
                if (mMoneyBean != null) {
                    textId.setText("账户可兑换上限：" + mMoneyBean.getData().get(0).getMibi());
                    zuanNum.setText(0 + "");
                }
                textBiNum.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        moneyStr = s.toString();
                        //改变完，计算
                        try {
                            if (!TextUtils.isEmpty(BaseUtils.getNumber(s.toString()))) {
                                moneyHandler.removeCallbacks(runnable);
                                moneyHandler.postDelayed(runnable, 300);
                                if (s.toString().equals(mMoneyBean.getData().get(0).getMibi())) {
                                    allSign = 1;
                                } else {
                                    allSign = 0;
                                }
                            } else {
                                zuanNum.setText("0");
                                uselessText.setVisibility(View.GONE);
                                zengText.setVisibility(View.GONE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                btn_ok.setOnClickListener(v -> {
                    if (allSign == 0) {
                        RxUtils.loading(commonModel.exchange(String.valueOf(UserManager.getUser().getUserId()),
                                textBiNum.getText().toString()), this)
                                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                                    @Override
                                    public void onNext(BaseBean moneyBean) {
                                        dialog.dismiss();
                                        showToast(moneyBean.getMessage());
                                        EventBus.getDefault().post(new FirstEvent("指定发送", Constant.CHONGZHISHUAXIN));
                                    }
                                });
                    } else {
                        RxUtils.loading(commonModel.exchange(String.valueOf(UserManager.getUser().getUserId()),
                                mMoneyBean.getData().get(0).getMibi() + ""), this)
                                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                                    @Override
                                    public void onNext(BaseBean moneyBean) {
                                        dialog.dismiss();
                                        showToast(moneyBean.getMessage());
                                        EventBus.getDefault().post(new FirstEvent("指定发送", Constant.CHONGZHISHUAXIN));
                                    }
                                });
                    }
                });
                textAll.setOnClickListener(v -> {
                    textBiNum.setText(String.valueOf(mMoneyBean.getData().get(0).getMibi()));
                });

                break;
            case R.id.llDiscount: //优惠券
                ArmsUtils.startActivity(CouponsActivity.class);
                break;
        }
    }

    private void tiXian() {
        Intent mIntent = new Intent(getActivity(), CashMoneyActivity.class);
        mIntent.putExtra("type", mType);
        startActivity(mIntent);
    }

    private static final int SDK_PAY_FLAG = 1;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult payResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    LogUtils.debugInfo("sgm", "====result" + resultInfo);
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")
                            && TextUtils.equals(payResult.getResultCode(), "200")) {
                        loadInfor(payResult.getAuthCode());
                    } else {
                        // 失败。
                        showToast("授权失败");
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    private void loadInfor(String code) {
        RxUtils.loading(commonModel.ali_oauth_token(code, UserManager.getUser().getUserId() + ""), this)
                .subscribe(new ErrorHandleSubscriber<AliInfor>(mErrorHandler) {
                    @Override
                    public void onNext(AliInfor aliInfor) {
                        loadData(mType);
//                        aliInfor.getData().getAvatar();
                    }
                });
    }

    Handler moneyHandler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            getMoney(moneyStr);
        }
    };

    private void getMoney(String money) {
        RxUtils.loading(commonModel.getMoney(money), this)
                .subscribe(new ErrorHandleSubscriber<ChaMoneyBean>(mErrorHandler) {
                    @Override
                    public void onNext(ChaMoneyBean chaMoneyBean) {
                        if (chaMoneyBean.getCode() == 1) {
                            if (chaMoneyBean.getData().getGive() == 0) {
                                uselessText.setVisibility(View.GONE);
                                zengText.setVisibility(View.GONE);
                            } else {
                                uselessText.setVisibility(View.VISIBLE);
                                zengText.setVisibility(View.VISIBLE);
                            }
                            zuanNum.setText(String.valueOf(chaMoneyBean.getData().getMizuan()));
                            zengText.setText(String.valueOf(chaMoneyBean.getData().getGive()));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        uselessText.setVisibility(View.GONE);
                        zengText.setVisibility(View.GONE);
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
        if (Constant.CHONGZHISHUAXIN.equals(tag)) {
            loadData(mType);
        } else if (JIEBANGALI.equals(tag)) {
            loadData(mType);
        }
    }
}
