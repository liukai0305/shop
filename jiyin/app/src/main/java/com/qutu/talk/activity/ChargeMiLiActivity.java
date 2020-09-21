package com.qutu.talk.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alipay.sdk.app.PayTask;
import com.jess.arms.di.component.AppComponent;
import com.qutu.talk.R;
import com.qutu.talk.adapter.dashen.ChargeMiLiAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.BaseWebActivity;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.GoodsList;
import com.qutu.talk.bean.PayBean;
import com.qutu.talk.bean.WxEndBean;
import com.qutu.talk.bean.Wxmodel;
import com.qutu.talk.bean.dashen.GoodsMiLiListBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.Constant;
import com.qutu.talk.utils.PayResult;
import com.qutu.talk.view.MyGridView;
import com.qutu.talk.view.ShapeTextView;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class ChargeMiLiActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.textNum)
    TextView textNum;
    @BindView(R.id.myGrid)
    MyGridView myGrid;
    @BindView(R.id.textZfb)
    TextView textZfb;
    @BindView(R.id.textVx)
    TextView textVx;
    @BindView(R.id.btn_chongzhi)
    ShapeTextView btnChongzhi;
    @BindView(R.id.yonghu)
    TextView yonghu;

    private ChargeMiLiAdapter chargeAdapter;
    private String mUrl;

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
        return R.layout.activity_charge_mi_li;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle("我的账户");

        chargeAdapter = new ChargeMiLiAdapter(this);
        myGrid.setAdapter(chargeAdapter);

        RxUtils.loading(commonModel.androidMiliList(), this)
                .subscribe(new ErrorHandleSubscriber<GoodsMiLiListBean>(mErrorHandler) {
                    @Override
                    public void onNext(GoodsMiLiListBean goodsMiLiListBean) {
                        mUrl = goodsMiLiListBean.getData().getTreaty();
                        textNum.setText(goodsMiLiListBean.getData().getMili());
                        chargeAdapter.getList_adapter().clear();
                        chargeAdapter.getList_adapter().addAll(goodsMiLiListBean.getData().getGoods());
                        chargeAdapter.notifyDataSetChanged();
                    }
                });

        myGrid.setOnItemClickListener((parent, view, position, id) -> {
            List<GoodsMiLiListBean.DataBean.GoodsBean> list_adapter = chargeAdapter.getList_adapter();
            if (list_adapter.get(position).isSelect) {

            } else {
                for (GoodsMiLiListBean.DataBean.GoodsBean list : list_adapter) {
                    list.isSelect = false;
                }
                list_adapter.get(position).isSelect = true;
                chargeAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick({R.id.textZfb, R.id.textVx, R.id.btn_chongzhi, R.id.yonghu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textZfb:
                textVx.setSelected(false);
                textZfb.setSelected(true);
                break;
            case R.id.textVx:
                textVx.setSelected(true);
                textZfb.setSelected(false);
                break;
            case R.id.btn_chongzhi:
                List<GoodsMiLiListBean.DataBean.GoodsBean> list_adapter = chargeAdapter.getList_adapter();
                List<GoodsMiLiListBean.DataBean.GoodsBean> listNew = new ArrayList<>();
                for (GoodsMiLiListBean.DataBean.GoodsBean list : list_adapter) {
                    if (list.isSelect) {
                        listNew.add(list);
                    }
                }
                if (listNew.size() > 0) {
                    if (textVx.isSelected()) {
                        loadWxData(String.valueOf(listNew.get(0).getId()));
                    } else if (textZfb.isSelected()) {
                        loadZfbData(String.valueOf(listNew.get(0).getId()));
                    } else if (!textVx.isSelected() && !textZfb.isSelected()) {
                        showToast("请选择充值方式");
                    }
                } else {
                    showToast("请选择充值金额");
                }
                break;
            case R.id.yonghu:
                Intent intent = new Intent(ChargeMiLiActivity.this, BaseWebActivity.class);
                intent.putExtra("url", mUrl);
                intent.putExtra("title", "充值协议");
                startActivity(intent);
                break;
        }
    }

    //后台支付宝订单
    private void loadZfbData(String goods_id) {
        RxUtils.loading(commonModel.miliRecharge(goods_id, "1"), this)
                .subscribe(new ErrorHandleSubscriber<PayBean>(mErrorHandler) {
                    @Override
                    public void onNext(PayBean payBean) {
                        Runnable payRunnable = new Runnable() {
                            @Override
                            public void run() {
                                PayTask alipay = new PayTask(ChargeMiLiActivity.this);
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

    //后台微信订单
    private void loadWxData(String goods_id) {
        RxUtils.loading(commonModel.miliWXRecharge(goods_id, "2"), this)
                .subscribe(new ErrorHandleSubscriber<Wxmodel>(mErrorHandler) {
                    @Override
                    public void onNext(Wxmodel wxmodel) {
                        final IWXAPI mWxApi = WXAPIFactory.createWXAPI(ChargeMiLiActivity.this, "wxd8441197c38a74f9", true);
                        // 将该app注册到微信
                        mWxApi.registerApp("wxd8441197c38a74f9");
                        // 判断是否安装客户端
                        if (!mWxApi.isWXAppInstalled()) {
                            toast("请您先安装微信客户端！");
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
                        showToast("支付成功");
                        EventBus.getDefault().post(new FirstEvent("指定发送", Constant.CHONGZHISHUAXIN));
                        loadYue();
                    } else {
                        // 失败。
                        showToast("支付失败,请重试");
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    /**
     * 请求余额
     */
    private void loadYue() {
        RxUtils.loading(commonModel.androidMiliList(), this)
                .subscribe(new ErrorHandleSubscriber<GoodsMiLiListBean>(mErrorHandler) {
                    @Override
                    public void onNext(GoodsMiLiListBean goodsMiLiListBean) {
                        textNum.setText(goodsMiLiListBean.getData().getMili());
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
        String msg = event.getMsg();
        if (Constant.WEIXINZHIFU.equals(tag)) {
            WxEndBean baseResp = JSON.parseObject(msg, WxEndBean.class);
            if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
                if (TextUtils.equals(baseResp.getErrCode(), "0")) {
                    toast("支付成功");
                    loadYue();
                    EventBus.getDefault().post(new FirstEvent("指定发送", Constant.CHONGZHISHUAXIN));
                } else if (TextUtils.equals(baseResp.getErrCode(), "-2")) {
                    toast("取消了！");
                } else {
                    // 失败。
                    showToast("支付失败,请重试");
                }
            } else {
                // 失败。
                showToast("支付失败,请重试");
            }
        }
    }
}
