package com.qutu.talk.fragment;

/**
 * 我的金币记录——订单收入与支出页面
 * 老王
 * mType = 1; 订单收入记录页面
 * mType = 2；订单支出记录页面
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.adapter.MiLiSZAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmFragment;
import com.qutu.talk.bean.IncomeFragmentBean;
import com.qutu.talk.bean.MiLiSZJiLuBean;
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
 * A simple {@link Fragment} subclass.
 */
public class MiLiSZJiLuFragment extends MyBaseArmFragment {

    @Inject
    CommonModel commonModel;

    @BindView(R.id.no_data_image)
    ImageView noDataImage;
    @BindView(R.id.no_data_text)
    TextView noDataText;
    @BindView(R.id.no_data)
    LinearLayout noData;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;

    private int mType;

    private List<MiLiSZJiLuBean.DataBean> mDataList = new ArrayList<>();
    private PullRefreshBean mPullRefreshBean = new PullRefreshBean();

    private MiLiSZAdapter mAdapter;

    @Override
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_item_fans);
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
        mType = getArguments().getInt("type");
        mAdapter = new MiLiSZAdapter(R.layout.mi_li_item, mDataList, mType);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(llm);
        recyclerview.setAdapter(mAdapter);

        getLoad();

        smart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPullRefreshBean.setLoardMore(mPullRefreshBean, smart);//上拉加载时 的处理
                getLoad();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPullRefreshBean.setLoardMore(mPullRefreshBean, smart);//上拉加载时 的处理
                getLoad();
            }
        });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    //获取数据
    private void getLoad() {
        RxUtils.loading(commonModel.go_order_log(mPullRefreshBean.pageIndex, mType), this)
                .subscribe(new ErrorHandleSubscriber<MiLiSZJiLuBean>(mErrorHandler) {
                    @Override
                    public void onNext(MiLiSZJiLuBean miLiSZJiLuBean) {
                        List<MiLiSZJiLuBean.DataBean> data = miLiSZJiLuBean.getData();
                        new DealRefreshHelper<MiLiSZJiLuBean.DataBean>().dealDataToUI(smart, mAdapter, noData, data, mDataList, mPullRefreshBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<MiLiSZJiLuBean.DataBean>().dealDataToUI(smart, mAdapter, noData, new ArrayList<>(), mDataList, mPullRefreshBean);
                    }
                });
    }

    public static MiLiSZJiLuFragment getInstance(int type) {
        MiLiSZJiLuFragment fragment = new MiLiSZJiLuFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }
}
