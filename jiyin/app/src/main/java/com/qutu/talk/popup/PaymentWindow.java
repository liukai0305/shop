package com.qutu.talk.popup;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.qutu.talk.R;
import com.qutu.talk.activity.order.ConfirmOrderActivity;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.CommentBean;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.MoneyBean;
import com.qutu.talk.bean.PayBean;
import com.qutu.talk.bean.Wxmodel;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.Constant;
import com.qutu.talk.utils.PayResult;
import com.qutu.talk.utils.ToastUtil;
import com.qutu.talk.view.ShapeTextView;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class PaymentWindow extends Dialog {
    @BindView(R.id.tit)
    TextView tit;
    @BindView(R.id.pay_ok_btn)
    TextView payOkBtn;
    @BindView(R.id.touxiang_yue)
    ImageView touxiangYue;
    @BindView(R.id.tit_yue)
    TextView titYue;
    @BindView(R.id.tips_yue)
    TextView tipsYue;
    @BindView(R.id.yue_ok_btn)
    ImageView yueOkBtn;
    @BindView(R.id.yu_e)
    RelativeLayout yuE;
    @BindView(R.id.zfb_ok_btn)
    ImageView zfbOkBtn;
    @BindView(R.id.zfb)
    LinearLayout zfb;
    @BindView(R.id.wx_ok_btn)
    ImageView wxOkBtn;
    @BindView(R.id.wx)
    LinearLayout wx;
    @BindView(R.id.zhi_ok_btn)
    ShapeTextView zhiOkBtn;
    @BindView(R.id.no_tips)
    TextView noTips;
    private MyBaseArmActivity mContext;
    private CommonModel mCommonModel;
    private RxErrorHandler mRxErrorHandler;
    private String myUserId;
    private int price;

    private String mType;
    private String mOrder;
    private boolean isChlick;

    public PaymentWindow(@NonNull MyBaseArmActivity context, CommonModel commonModel, RxErrorHandler rxErrorHandler, String UserId, String pricae, String order) {
        super(context, R.style.myChooseDialog);
        mContext = context;
        this.mCommonModel = commonModel;
        this.mRxErrorHandler = rxErrorHandler;
        this.myUserId = UserId;
        this.price = Integer.parseInt(pricae);
        this.mOrder = order;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.payment_layout);

        ButterKnife.bind(this);

        setWidows();


    }

    private void setWidows() {

        Display display = getWindow().getWindowManager().getDefaultDisplay();

        WindowManager.LayoutParams lp = getWindow().getAttributes();

        lp.width = (int) (display.getWidth());

        lp.alpha = 1.0f;

        lp.gravity = Gravity.BOTTOM;

        getWindow().setAttributes(lp);

        this.setCanceledOnTouchOutside(false);

        setCancelable(false);
        zhiOkBtn.setText("支付" + price + "元");

        getData();

    }

    //获取数据
    private void getData() {
        RxUtils.loading(mCommonModel.my_store(myUserId), mContext)
                .subscribe(new ErrorHandleSubscriber<MoneyBean>(mRxErrorHandler) {
                    @Override
                    public void onNext(MoneyBean moneyBean) {
                        double a = Double.parseDouble(moneyBean.getData().get(0).getMizuan());
                        int miZuan = (int) a;
//                        int mili = Integer.parseInt(moneyBean.getData().get(0).getMili());
//                        String mili = moneyBean.getData().get(0).getMili();
                        tipsYue.setText(moneyBean.getData().get(0).getMizuan());
                        if (miZuan < price) {
                            noTips.setVisibility(View.VISIBLE);
                            isChlick = false;
                        } else {
                            noTips.setVisibility(View.GONE);
                            isChlick = true;
                        }
                    }
                });
    }

    //支付

    @OnClick({R.id.pay_ok_btn, R.id.yu_e, R.id.zfb, R.id.wx, R.id.zhi_ok_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pay_ok_btn:
                dismiss();
                break;
            case R.id.yu_e:
                if (isChlick) {
                    yueOkBtn.setSelected(true);
                    zfbOkBtn.setSelected(false);
                    wxOkBtn.setSelected(false);
                    mType = "3";
                }
                break;
            case R.id.zfb:
                zfbOkBtn.setSelected(true);
                yueOkBtn.setSelected(false);
                wxOkBtn.setSelected(false);
                mType = "1";
                break;
            case R.id.wx:
                wxOkBtn.setSelected(true);
                zfbOkBtn.setSelected(false);
                yueOkBtn.setSelected(false);
                mType = "2";
                break;
            case R.id.zhi_ok_btn:
                if (yueOkBtn.isSelected()) {
                    YuE();
                } else if (zfbOkBtn.isSelected()) {
                    ALi();
                } else if (wxOkBtn.isSelected()) {
                    WX();
                } else {
                    ToastUtil.showToast(mContext, "请选择充值方式");
                }
                break;
        }
    }

    private void YuE() {
        RxUtils.loading(mCommonModel.go_pay_game_yu_e(mOrder, "3"), mContext)
                .subscribe(new ErrorHandleSubscriber<CommentBean>(mRxErrorHandler) {
                    @Override
                    public void onNext(CommentBean commentBean) {
                        ToastUtil.showToast(mContext, commentBean.getMessage());
                        EventBus.getDefault().post(new FirstEvent("指定发送", Constant.MILICHONGZHI));
                        dismiss();
                    }
                });
    }

    private void ALi() {
        RxUtils.loading(mCommonModel.go_pay_game_zfb(mOrder, "1"), mContext)
                .subscribe(new ErrorHandleSubscriber<PayBean>(mRxErrorHandler) {
                    @Override
                    public void onNext(PayBean payBean) {
                        Runnable payRunnable = new Runnable() {
                            @Override
                            public void run() {
                                PayTask alipay = new PayTask(mContext);
                                Map<String, String> result = alipay.payV2(payBean.getData(),
                                        true);
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

    private void WX() {
        RxUtils.loading(mCommonModel.go_pay_game_wx(mOrder, "2"), mContext)
                .subscribe(new ErrorHandleSubscriber<Wxmodel>(mRxErrorHandler) {
                    @Override
                    public void onNext(Wxmodel wxmodel) {
                        final IWXAPI mWxApi = WXAPIFactory.createWXAPI(mContext, "wxd8441197c38a74f9", true);
                        mWxApi.registerApp("wxd8441197c38a74f9");
                        // 判断是否安装客户端
                        if (!mWxApi.isWXAppInstalled()) {
                            ToastUtil.showToast(mContext, "请您先安装微信客户端！");
                            return;
                        }
                        PayReq req = new PayReq();
                        req.appId = "wxd8441197c38a74f9";// 微信开放平台审核通过的应用APPID
                        req.partnerId = wxmodel.getData().getPartnerid();// 微信支付分配的商户号
                        req.prepayId = wxmodel.getData().getPrepayid();// 预支付订单号，app服务器调用“统一下单”接口获取
                        req.nonceStr = wxmodel.getData().getNoncestr();// 随机字符串，不长于32位
                        req.timeStamp = String.valueOf(wxmodel.getData().getTimestamp());// 时间戳
                        req.packageValue = wxmodel.getData().getPackageX();// 固定值Sign=WXPay，可以直接写死，服务器返回的也是这个固定值
                        req.sign = wxmodel.getData().getSign();// 签名，
                        // 调用微信SDK，发起支付，回调WxPayEntryActivity
                        mWxApi.sendReq(req);
                    }
                });
    }

    private static final int SDK_PAY_FLAG = 1;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            PayResult payResult2 = new PayResult((Map<String, String>) msg.obj);
//            LogUtils.e("sgm","====支付结果" + payResult2.getResult());
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtil.showToast(mContext, "支付成功");
                        EventBus.getDefault().post(new FirstEvent("指定发送", Constant.MILICHONGZHI));
                    } else {
                        // 失败。
                        ToastUtil.showToast(mContext, "支付失败,请重试");
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

}
