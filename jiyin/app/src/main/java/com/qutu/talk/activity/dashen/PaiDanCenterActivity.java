package com.qutu.talk.activity.dashen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jess.arms.di.component.AppComponent;
import com.qutu.talk.R;
import com.qutu.talk.adapter.dashen.PaiDanCenterAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.PullRefreshBean;
import com.qutu.talk.bean.dashen.PaiDanCenterBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.DealRefreshHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.qutu.talk.utils.Constant.KBXTUISONG;
import static com.qutu.talk.utils.Constant.PAIDANZHONGXIN;

/**
 * 派单中心
 * 老王
 * pai_dan_center_item   RV的item布局文件
 */

public class PaiDanCenterActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.paidan_center_recyc)
    RecyclerView paidanCenterRecyc;
    @BindView(R.id.paidan_center_smart)
    SmartRefreshLayout paidanCenterSmart;

    private PullRefreshBean mPullRefreshBean = new PullRefreshBean();
    private ArrayList<PaiDanCenterBean.DataBean> mDataList = new ArrayList<>();
    private PaiDanCenterAdapter mAdapter;

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
        return R.layout.activity_pai_dan_center;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle("派单中心");

        mAdapter = new PaiDanCenterAdapter(R.layout.pai_dan_center_item, mDataList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        paidanCenterRecyc.setLayoutManager(llm);
        paidanCenterRecyc.setAdapter(mAdapter);

        getData();

        paidanCenterSmart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPullRefreshBean.setRefresh(mPullRefreshBean, paidanCenterSmart);
                getData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPullRefreshBean.setRefresh(mPullRefreshBean, paidanCenterSmart);
                getData();
            }
        });

        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            List<PaiDanCenterBean.DataBean> data = mAdapter.getData();
            PaiDanCenterBean.DataBean dataBean = data.get(position);
            if (dataBean.getStatus() == 0) {
                enterData(String.valueOf(dataBean.getUid()), "", commonModel, 1, dataBean.getRoom_cover());
            }
        });
    }

    //获取数据
    private void getData() {
        RxUtils.loading(commonModel.getReceiptCenterList(mPullRefreshBean.pageIndex), this)
                .subscribe(new ErrorHandleSubscriber<PaiDanCenterBean>(mErrorHandler) {
                    @Override
                    public void onNext(PaiDanCenterBean paiDanCenterBean) {
                        List<PaiDanCenterBean.DataBean> data = paiDanCenterBean.getData();
                        new DealRefreshHelper<PaiDanCenterBean.DataBean>().dealDataToUI(paidanCenterSmart, mAdapter, null, data, mDataList, mPullRefreshBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<PaiDanCenterBean.DataBean>().dealDataToUI(paidanCenterSmart, mAdapter, null, new ArrayList<>(), mDataList, mPullRefreshBean);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().post(new FirstEvent("派单中心", PAIDANZHONGXIN));
        super.onDestroy();
    }
}
