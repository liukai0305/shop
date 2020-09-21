package com.qutu.talk.activity.order;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.qutu.talk.R;
import com.qutu.talk.adapter.ChoiceCouponAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.ChoiceCouponBean;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.MyCouponsBean;
import com.qutu.talk.bean.PullRefreshBean;
import com.qutu.talk.bean.ZhuanYongBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.DealRefreshHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.qutu.talk.utils.Constant.XUANCOUPON;

/**
 * 选择优惠券
 * 老王
 */
public class ChoiceCouponActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.choice_coupon_recyc)
    RecyclerView choiceCouponRecyc;
    @BindView(R.id.choice_coupon_btn)
    TextView choiceCouponBtn;

    private ChoiceCouponAdapter mAdapter;
    private boolean check;
    private String pos;
    private ZhuanYongBean zhuanYongBean;

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
        return R.layout.activity_choice_coupon;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle("优惠券");

        ArrayList<ChoiceCouponBean.DataBean> coupon = getIntent().getParcelableArrayListExtra("coupon");
        pos = getIntent().getStringExtra("pos");
        if (!TextUtils.isEmpty(pos)) {
            coupon.get(Integer.parseInt(pos)).setCheck(true);
        }
        mAdapter = new ChoiceCouponAdapter();
        choiceCouponRecyc.setLayoutManager(new LinearLayoutManager(this));
        choiceCouponRecyc.setAdapter(mAdapter);

        mAdapter.setNewData(coupon);

        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            List<ChoiceCouponBean.DataBean> data = mAdapter.getData();
            check = data.get(position).isCheck();
            for (ChoiceCouponBean.DataBean dataBean : data) {
                dataBean.setCheck(false);
            }
            data.get(position).setCheck(!check);
            pos = String.valueOf(position);
            mAdapter.notifyDataSetChanged();
        });
    }

    @OnClick(R.id.choice_coupon_btn)
    public void onClick() {
        for (int a = 0; a < mAdapter.getData().size(); a++) {
            if (mAdapter.getData().get(a).isCheck()) {
                zhuanYongBean = new ZhuanYongBean();
                zhuanYongBean.setName(mAdapter.getData().get(a).getName());
                zhuanYongBean.setId(mAdapter.getData().get(a).getId());
                zhuanYongBean.setPrice(mAdapter.getData().get(a).getPrice());
                zhuanYongBean.setPosition(pos);
            }
            finish();
            EventBus.getDefault().post(new FirstEvent(zhuanYongBean, XUANCOUPON));
        }
    }
}
