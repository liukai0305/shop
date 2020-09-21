package com.qutu.talk.activity.room;

/**
 * 房间内打赏记录
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.qutu.talk.R;
import com.qutu.talk.adapter.RoomRewardOneAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.PullRefreshBean;
import com.qutu.talk.bean.RoomRewardOneBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.DealRefreshHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class RoomRewardActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.toolbar_back)
    RelativeLayout toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.room_reward_recycler)
    RecyclerView roomRewardRecycler;
    @BindView(R.id.room_reward_smart)
    SmartRefreshLayout smartRefreshLayout;

    private String mUid;
    private RoomRewardOneAdapter mAdapter;
    private List<RoomRewardOneBean.DataBean> mDataList = new ArrayList<>();
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
        return R.layout.activity_room_reward;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mUid = getIntent().getStringExtra("uid");

        setTitle("打赏记录");

        mAdapter = new RoomRewardOneAdapter(R.layout.room_reward_recycler_item, mDataList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        roomRewardRecycler.setLayoutManager(llm);
        roomRewardRecycler.setAdapter(mAdapter);

        loadData();

        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPullRefreshBean.setRefresh(mPullRefreshBean, smartRefreshLayout);//下拉刷新时 的处理
            loadData();
        });
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPullRefreshBean.setLoardMore(mPullRefreshBean, smartRefreshLayout);//上拉加载时 的处理
            loadData();
        });

        findViewById(R.id.toolbar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RoomRewardActivity.this, AdminHomeActivity.class));
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

    //请求数据
    private void loadData() {
        RxUtils.loading(commonModel.room_gifts_log(mUid, mPullRefreshBean.pageIndex), this)
                .subscribe(new ErrorHandleSubscriber<RoomRewardOneBean>(mErrorHandler) {
                    @Override
                    public void onNext(RoomRewardOneBean roomRewardOneBean) {
                        List<RoomRewardOneBean.DataBean> data = roomRewardOneBean.getData();
                        new DealRefreshHelper<RoomRewardOneBean.DataBean>().dealDataToUI(smartRefreshLayout, mAdapter, null, data, mDataList, mPullRefreshBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<RoomRewardOneBean.DataBean>().dealDataToUI(smartRefreshLayout, mAdapter, null, new ArrayList<>(), mDataList, mPullRefreshBean);
                    }
                });
    }
}
