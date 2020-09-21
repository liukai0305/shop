package com.qutu.talk.activity.task;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.qutu.talk.R;
import com.qutu.talk.adapter.taskcenter.DHJiLuAdapter;
import com.qutu.talk.adapter.taskcenter.DHListAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.CommentBean;
import com.qutu.talk.bean.PullRefreshBean;
import com.qutu.talk.bean.task.ExchangeListBean;
import com.qutu.talk.bean.task.JBExchangeBean;
import com.qutu.talk.bean.task.MySection;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.popup.PuTongWindow;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.DealRefreshHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class GoldExchangeActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.exchange_gold_tit)
    TextView exchangeGoldTit;
    @BindView(R.id.my_gold)
    TextView myGold;
    @BindView(R.id.tit_one)
    TextView titOne;
    @BindView(R.id.dh_recycler)
    RecyclerView dhRecycler;
    @BindView(R.id.tit_two)
    TextView titTwo;
    @BindView(R.id.dh_record_recycler)
    RecyclerView dhRecordRecycler;
    @BindView(R.id.smart_dh)
    SmartRefreshLayout smartDh;
    @BindView(R.id.quanbu)
    LinearLayout quanbu;

    private DHListAdapter mDhListAdapter; //兑换列表适配器
    private DHJiLuAdapter mAdapter; // 兑换记录适配器

    private PullRefreshBean mPullRefreshBean = new PullRefreshBean();
    private ArrayList<MySection> mDataList = new ArrayList<>();
    private PuTongWindow puTongWindow;

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
        return R.layout.activity_gold_exchange;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle("兑换");

        mDhListAdapter = new DHListAdapter(this);
        dhRecycler.setLayoutManager(new GridLayoutManager(GoldExchangeActivity.this, 3));
        dhRecycler.setAdapter(mDhListAdapter);

        mAdapter = new DHJiLuAdapter(R.layout.dh_jilu_item_two, R.layout.dh_jilu_item_one, mDataList);
        dhRecordRecycler.setLayoutManager(new LinearLayoutManager(this));
        dhRecordRecycler.setAdapter(mAdapter);
        getDHList();
        getDHLog();

        smartDh.setOnRefreshListener(refreshLayout -> {
            mPullRefreshBean.setRefresh(mPullRefreshBean, smartDh);
            getDHLog();
        });
        smartDh.setOnLoadMoreListener(refreshLayout -> {
            mPullRefreshBean.setLoardMore(mPullRefreshBean, smartDh);
            getDHLog();
        });

        mDhListAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (puTongWindow==null){
                puTongWindow = new PuTongWindow(this);
            }
            puTongWindow.showAtLocation(quanbu, Gravity.CENTER, 0, 0);
            puTongWindow.getTitText().setText("确定要兑换" + mDhListAdapter.getData().get(position).getName() + "吗？");
            puTongWindow.getCancel().setText("取消");
            puTongWindow.getSure().setText("确认");
            puTongWindow.getCancel().setOnClickListener(v -> {
                puTongWindow.dismiss();
            });
            puTongWindow.getSure().setOnClickListener(v -> {
                setDH(String.valueOf(mDhListAdapter.getData().get(position).getId()));
            });
        });
    }

    //获取兑换列表
    private void getDHList() {
        RxUtils.loading(commonModel.jb_exchange_list(), this)
                .subscribe(new ErrorHandleSubscriber<ExchangeListBean>(mErrorHandler) {
                    @Override
                    public void onNext(ExchangeListBean exchangeListBean) {
                        myGold.setText(String.valueOf(exchangeListBean.getData().getJinbi()));
                        mDhListAdapter.setNewData(exchangeListBean.getData().getData());
                    }
                });
    }

    //获取兑换记录
    private void getDHLog() {
        RxUtils.loading(commonModel.jb_exchange_log(mPullRefreshBean.pageIndex), this)
                .subscribe(new ErrorHandleSubscriber<JBExchangeBean>(mErrorHandler) {
                    @Override
                    public void onNext(JBExchangeBean jbExchangeBean) {
                        List<MySection> data = new ArrayList<>();
                        for (int a = 0; a < jbExchangeBean.getData().size(); a++) {
                            data.add(new MySection(true, jbExchangeBean.getData().get(a).getTime()));
                            for (int b = 0; b < jbExchangeBean.getData().get(a).getData().size(); b++) {
                                data.add(new MySection(jbExchangeBean.getData().get(a).getData().get(b)));
                            }
                        }
                        new DealRefreshHelper<MySection>().dealDataToUI(smartDh, mAdapter, null, data, mDataList, mPullRefreshBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<MySection>().dealDataToUI(smartDh, mAdapter, null, new ArrayList<>(), mDataList, mPullRefreshBean);
                    }
                });
    }

    //兑换
    private void setDH(String id) {
        RxUtils.loading(commonModel.jb_exchange(id), this)
                .subscribe(new ErrorHandleSubscriber<CommentBean>(mErrorHandler) {
                    @Override
                    public void onNext(CommentBean commentBean) {
                        toast(commentBean.getMessage());
                        puTongWindow.dismiss();
                    }
                });
    }
}
