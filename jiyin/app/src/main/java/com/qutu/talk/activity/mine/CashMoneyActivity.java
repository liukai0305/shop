package com.qutu.talk.activity.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.qutu.talk.R;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.BaseBean;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.MoneyBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.Constant;
import com.qutu.talk.view.ShapeTextView;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 提现
 */
public class CashMoneyActivity extends MyBaseArmActivity {

    @BindView(R.id.textName)
    TextView textName;
    @BindView(R.id.imgUser)
    ImageView imgUser;
    @BindView(R.id.textAll)
    TextView textAll;
    @BindView(R.id.editMoney)
    EditText editMoney;
    @BindView(R.id.btn_ok)
    ShapeTextView btnOk;
    @BindView(R.id.tishi_mibi)
    TextView tishiMibi;
    @BindView(R.id.tishi_mibli)
    TextView tishiMibli;
    @Inject
    CommonModel commonModel;


    private int mType; //mType=1 钻石体现   mType=2 金币体现

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
        return R.layout.activity_cash_money;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mType = getIntent().getIntExtra("type", 0);
        loadData();
        if (mType == 2) {
            tishiMibi.setVisibility(View.GONE);
            tishiMibli.setVisibility(View.VISIBLE);
        }
    }

    private void loadData() {
        RxUtils.loading(commonModel.my_store(String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<MoneyBean>(mErrorHandler) {
                    @Override
                    public void onNext(MoneyBean moneyBean) {
                        if (!TextUtils.isEmpty(moneyBean.getData().get(0).getAli_nick_name())) {
                            textName.setText(moneyBean.getData().get(0).getAli_nick_name() + "");
                        } else {
                            textName.setText("");
                        }
                        loadImage(imgUser, moneyBean.getData().get(0).getAli_avatar(), R.mipmap.gender_zhuce_boy);
                        if (mType == 1) {
                            textAll.setText(moneyBean.getData().get(0).getMibi() + "元");
                        } else {
                            textAll.setText(moneyBean.getData().get(0).getMlz() + "元");
                        }

                    }
                });
    }

    @OnClick({R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                String str = editMoney.getText().toString();
                if (TextUtils.isEmpty(str)) {
                    toast("请输入提现金额");
                } else {
                    RxUtils.loading(commonModel.tixian(String.valueOf(UserManager.getUser().getUserId()), str, String.valueOf(mType)), this)
                            .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                                @Override
                                public void onNext(BaseBean moneyBean) {
                                    toast(moneyBean.getMessage());
                                    EventBus.getDefault().post(new FirstEvent("指定发送", Constant.CHONGZHISHUAXIN));
                                    loadData();
                                }
                            });
                }
                break;
        }
    }
}
