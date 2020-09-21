package com.qutu.talk.activity.room;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.adapter.IncomeSumAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.IncomeSumBean;
import com.qutu.talk.bean.PullRefreshBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.DealRefreshHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

//收入统计

public class IncomeSumActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.toolbar_back)
    RelativeLayout toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.today_income_text)
    TextView todayIncomeText;
    @BindView(R.id.today_income_lin)
    LinearLayout todayIncomeLin;
    //    @BindView(R.id.one)
//    TextView one;
//    @BindView(R.id.textone)
//    TextView textone;
    @BindView(R.id.room_reward_recycler)
    RecyclerView roomRewardRecycler;
    @BindView(R.id.room_reward_smart)
    SmartRefreshLayout roomRewardSmart;
    @BindView(R.id.one_wu)
    TextView oneWu;
    @BindView(R.id.sum_price_text)
    TextView sumPriceText;
    @BindView(R.id.sum_prive_relative)
    RelativeLayout sumPriveRelative;

    private String mUid;

    private IncomeSumAdapter mAdapter;
    private List<IncomeSumBean.DataBean.HistoryIncomeListBean> mDataList = new ArrayList<>();
    private PullRefreshBean mPullRefreshBean = new PullRefreshBean();

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
        return R.layout.activity_income_sum;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mUid = getIntent().getStringExtra("uid");

        setTitle("收入统计");

        mAdapter = new IncomeSumAdapter(R.layout.income_sum_item, mDataList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        roomRewardRecycler.setLayoutManager(llm);
        roomRewardRecycler.setAdapter(mAdapter);

        View viewHead = ArmsUtils.inflate(this, R.layout.item_type_layout);
        mAdapter.addHeaderView(viewHead);

        loadData();

        roomRewardSmart.setOnRefreshListener(refreshLayout -> {
            mPullRefreshBean.setRefresh(mPullRefreshBean, roomRewardSmart);
            loadData();
        });
        roomRewardSmart.setOnLoadMoreListener(refreshLayout -> {
            mPullRefreshBean.setLoardMore(mPullRefreshBean, roomRewardSmart);
            loadData();
        });

        findViewById(R.id.toolbar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IncomeSumActivity.this, AdminHomeActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbarBack.setOnClickListener(v -> {
            startActivity(new Intent(this, AdminHomeActivity.class));
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, AdminHomeActivity.class));
        finish();
    }

    //获取数据
    private void loadData() {
        RxUtils.loading(commonModel.roomIncomeCount(mPullRefreshBean.pageIndex, mUid), this)
                .subscribe(new ErrorHandleSubscriber<IncomeSumBean>(mErrorHandler) {
                    @Override
                    public void onNext(IncomeSumBean incomeSumBean) {
                        todayIncomeText.setText(incomeSumBean.getData().getTodayTotalPrice());  //今日收入
                        sumPriceText.setText(incomeSumBean.getData().getTotalPrice() + "金币");  //总收入

                        List<IncomeSumBean.DataBean.HistoryIncomeListBean> historyIncomeList = incomeSumBean.getData().getHistoryIncomeList();
                        new DealRefreshHelper<IncomeSumBean.DataBean.HistoryIncomeListBean>().dealDataToUI(roomRewardSmart, mAdapter, null, historyIncomeList, mDataList, mPullRefreshBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<IncomeSumBean.DataBean.HistoryIncomeListBean>().dealDataToUI(roomRewardSmart, mAdapter, null, new ArrayList<>(), mDataList, mPullRefreshBean);
                    }
                });
    }
}
