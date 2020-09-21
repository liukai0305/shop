package com.qutu.talk.fragment;


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
import com.qutu.talk.adapter.IncomeFragmentAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmFragment;
import com.qutu.talk.bean.IncomeFragmentBean;
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
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * A simple {@link Fragment} subclass.
 */
public class IncomeFragment extends MyBaseArmFragment {
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

    private int mId;
    private IncomeFragmentAdapter mAdapter;
    private List<IncomeFragmentBean.DataBean> mDataList = new ArrayList<>();
    private PullRefreshBean mPullRefreshBean = new PullRefreshBean();

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
        mId = getArguments().getInt("id");

        mAdapter = new IncomeFragmentAdapter(R.layout.item_cash_his, mDataList, mId);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(llm);
        recyclerview.setAdapter(mAdapter);

        if (mId == 0) {
            loadUserGiftList();
        } else if (mId == 1) {
            loadUserSendGiftList();
        }

        smart.setOnRefreshListener(refreshLayout -> {
            if (mId == 0) {
                mPullRefreshBean.setRefresh(mPullRefreshBean, smart);//下拉刷新时 的处理
                loadUserGiftList();
            } else if (mId == 1) {
                mPullRefreshBean.setRefresh(mPullRefreshBean, smart);//下拉刷新时 的处理
                loadUserSendGiftList();
            }
        });

        smart.setOnLoadMoreListener(refreshLayout -> {
            if (mId == 0) {
                mPullRefreshBean.setLoardMore(mPullRefreshBean, smart);//上拉加载时 的处理
                loadUserGiftList();
            } else if (mId == 1) {
                mPullRefreshBean.setLoardMore(mPullRefreshBean, smart);//上拉加载时 的处理
                loadUserSendGiftList();
            }
        });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    //获取礼物收入的数据
    private void loadUserGiftList() {
        RxUtils.loading(commonModel.getUserGiftList(mPullRefreshBean.pageIndex), this)
                .subscribe(new ErrorHandleSubscriber<IncomeFragmentBean>(mErrorHandler) {
                    @Override
                    public void onNext(IncomeFragmentBean incomeFragmentBean) {
                        List<IncomeFragmentBean.DataBean> data = incomeFragmentBean.getData();
                        new DealRefreshHelper<IncomeFragmentBean.DataBean>().dealDataToUI(smart, mAdapter, noData, data, mDataList, mPullRefreshBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<IncomeFragmentBean.DataBean>().dealDataToUI(smart, mAdapter, noData, new ArrayList<>(), mDataList, mPullRefreshBean);
                    }
                });
    }

    private void loadUserSendGiftList() {
        RxUtils.loading(commonModel.getUserSendGiftList(mPullRefreshBean.pageIndex), this)
                .subscribe(new ErrorHandleSubscriber<IncomeFragmentBean>(mErrorHandler) {
                    @Override
                    public void onNext(IncomeFragmentBean incomeFragmentBean) {
                        List<IncomeFragmentBean.DataBean> data = incomeFragmentBean.getData();
                        new DealRefreshHelper<IncomeFragmentBean.DataBean>().dealDataToUI(smart, mAdapter, noData, data, mDataList, mPullRefreshBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<IncomeFragmentBean.DataBean>().dealDataToUI(smart, mAdapter, noData, new ArrayList<>(), mDataList, mPullRefreshBean);
                    }
                });
    }

    public static IncomeFragment getInstance(int tag) {
        IncomeFragment fragment = new IncomeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", tag);
        fragment.setArguments(bundle);
        return fragment;
    }
}
