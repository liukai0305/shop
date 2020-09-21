package com.qutu.talk.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.adapter.MyCouponAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmFragment;
import com.qutu.talk.bean.MyCouponsBean;
import com.qutu.talk.bean.PullRefreshBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.DealRefreshHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 我的优惠券
 * 老王
 */
public class MyCouponFragment extends MyBaseArmFragment {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.my_coupon_recyc)
    RecyclerView myCouponRecyc;
    @BindView(R.id.my_coupon_smart)
    SmartRefreshLayout myCouponSmart;
    @BindView(R.id.no_data)
    LinearLayout noData;

    private String mType;
    private PullRefreshBean mPullRefreshBean = new PullRefreshBean();
    private ArrayList<MyCouponsBean.DataBean> mDataList = new ArrayList<>();
    private MyCouponAdapter mAdapter;

    @Override
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_my_coupon);
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
        mType = getArguments().getString("type");

        mAdapter = new MyCouponAdapter(R.layout.my_coupon_item, mDataList, getActivity(), mType);
        myCouponRecyc.setLayoutManager(new LinearLayoutManager(getActivity()));
        myCouponRecyc.setAdapter(mAdapter);

        getData();

        myCouponSmart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPullRefreshBean.setLoardMore(mPullRefreshBean, myCouponSmart);//上拉加载时 的处理
                getData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPullRefreshBean.setRefresh(mPullRefreshBean, myCouponSmart);
                getData();
            }
        });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    //获取数据
    private void getData() {
        RxUtils.loading(commonModel.my_coupon(mType, mPullRefreshBean.pageIndex), this)
                .subscribe(new ErrorHandleSubscriber<MyCouponsBean>(mErrorHandler) {
                    @Override
                    public void onNext(MyCouponsBean myCouponsBean) {
                        if (mPullRefreshBean.pageIndex == 1) {
                            mDataList.clear();
                        }
                        List<MyCouponsBean.DataBean> data = myCouponsBean.getData();
                        new DealRefreshHelper<MyCouponsBean.DataBean>().dealDataToUI(myCouponSmart, mAdapter, noData, data, mDataList, mPullRefreshBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<MyCouponsBean.DataBean>().dealDataToUI(myCouponSmart, mAdapter, noData, new ArrayList<>(), mDataList, mPullRefreshBean);
                    }
                });
    }

    public static MyCouponFragment getInstance(String type) {
        MyCouponFragment fragment = new MyCouponFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }
}
