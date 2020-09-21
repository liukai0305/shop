package com.qutu.talk.activity.dashen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.adapter.MiLiIncomAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.PullRefreshBean;
import com.qutu.talk.bean.dashen.MiLiIncomeBean;
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

/**
 * 金币收入页面
 * 老王
 */
public class MiLiIncomeActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.today_income_text)
    TextView todayIncomeText;
    @BindView(R.id.mili_sr_num)
    TextView miliSrNum;
    @BindView(R.id.today_income_lin)
    LinearLayout todayIncomeLin;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.room_reward_recycler)
    RecyclerView roomRewardRecycler;
    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.room_reward_smart)
    SmartRefreshLayout roomRewardSmart;

    private ArrayList<MiLiIncomeBean.DataBean.HistoryBean> mDataList = new ArrayList<>();
    private MiLiIncomAdapter mAdapter;
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
        return R.layout.activity_mi_li_income;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle("钻石收入");

        mAdapter = new MiLiIncomAdapter(R.layout.income_sum_item, mDataList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        roomRewardRecycler.setLayoutManager(llm);
        roomRewardRecycler.setAdapter(mAdapter);

        loadData();

        View viewHead = ArmsUtils.inflate(this, R.layout.item_type_layout);
        mAdapter.addHeaderView(viewHead);

        roomRewardSmart.setOnRefreshListener(refreshLayout -> {
            mPullRefreshBean.setRefresh(mPullRefreshBean, roomRewardSmart);
            loadData();
        });
        roomRewardSmart.setOnLoadMoreListener(refreshLayout -> {
            mPullRefreshBean.setLoardMore(mPullRefreshBean, roomRewardSmart);
            loadData();
        });
    }

    //加载数据
    private void loadData() {
        RxUtils.loading(commonModel.GmOrderIncomeCount(), this)
                .subscribe(new ErrorHandleSubscriber<MiLiIncomeBean>(mErrorHandler) {
                    @Override
                    public void onNext(MiLiIncomeBean miLiIncomeBean) {
                        miliSrNum.setText(String.valueOf(miLiIncomeBean.getData().getTotalPrice()));
                        List<MiLiIncomeBean.DataBean.HistoryBean> history = miLiIncomeBean.getData().getHistory();
                        new DealRefreshHelper<MiLiIncomeBean.DataBean.HistoryBean>().dealDataToUI(roomRewardSmart, mAdapter, null, history, mDataList, mPullRefreshBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<MiLiIncomeBean.DataBean.HistoryBean>().dealDataToUI(roomRewardSmart, mAdapter, null, new ArrayList<>(), mDataList, mPullRefreshBean);
                    }
                });
    }
}
