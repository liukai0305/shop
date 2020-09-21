package com.qutu.talk.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.activity.my.MyPersonalCenterTwoActivity;
import com.qutu.talk.adapter.dashen.PeiwanHoriAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.HeaderViewPagerFragment;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.PullRefreshBean;
import com.qutu.talk.bean.dashen.MainHomePageBean;
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
public class RecomHomePageFragment extends HeaderViewPagerFragment {

    @Inject
    CommonModel commonModel;
    @BindView(R.id.no_data)
    LinearLayout noData;
    @BindView(R.id.myList1)
    RecyclerView myList1;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;

    Unbinder unbinder;

    SmartRefreshLayout mSmartRefreshLayout;
    List<MainHomePageBean.DataBean> mDataList = new ArrayList<>();
    PullRefreshBean mPullRefreshBean = new PullRefreshBean();//下拉刷新帮助类
    private Intent intent1;


    private int id;
    private String skill_id;
    private PeiwanHoriAdapter mAdapter;

    public void setDisableLoadMore(boolean disableLoadMore) {
        mPullRefreshBean.setDisableLoadMore(disableLoadMore);
    }

    /**
     * 上拉加载
     *
     * @param refreshLayout
     */
    public void onLoadMore(final SmartRefreshLayout refreshLayout) {
        mPullRefreshBean.setLoardMore(mPullRefreshBean, refreshLayout);//加载更多时的处理
        if (id == 0) {
            tuijianData(refreshLayout);
        } else {
            feiTuiData(refreshLayout);
        }
    }

    /**
     * 下拉刷新
     *
     * @param refreshLayout
     */
    public void onRefresh(final SmartRefreshLayout refreshLayout) {
        mPullRefreshBean.setRefresh(mPullRefreshBean, refreshLayout);//下拉刷新时的处理
        if (id == 0) {
            tuijianData(refreshLayout);
        } else {
            feiTuiData(refreshLayout);
        }
    }

    public void setRefersh(SmartRefreshLayout refershLayout) {
        this.mSmartRefreshLayout = refershLayout;
    }


    @Override
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = null;
        if (mRootView == null) {
            mRootView = ArmsUtils.inflate(getActivity(), R.layout.recom_home_page_fragment);
            unbinder = ButterKnife.bind(this, mRootView);
        }
        return mRootView;
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
        id = getArguments().getInt("id");
        skill_id = getArguments().getString("skill_id");

        mAdapter = new PeiwanHoriAdapter(  mDataList, getActivity());
//        GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
        LinearLayoutManager glm = new LinearLayoutManager(getActivity() );
        myList1.setLayoutManager(glm);
        myList1.setAdapter(mAdapter);

        if (id == 0) {
            tuijianData(mSmartRefreshLayout);
        } else {
            feiTuiData(mSmartRefreshLayout);
        }

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (intent1==null){
                    intent1 = new Intent(getContext(), MyPersonalCenterTwoActivity.class);
                }
                if (mAdapter.getData().get(position).getUser_id() == UserManager.getUser().getUserId()) {
                    intent1.putExtra("sign", 0);
                    intent1.putExtra("id", "");
                } else {
                    intent1.putExtra("sign", 1);
                    intent1.putExtra("id", mAdapter.getData().get(position).getUser_id() + "");
                }
                ArmsUtils.startActivity(intent1);
            }
        });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    public static RecomHomePageFragment getInstance(int tag, String skill_id) {
        RecomHomePageFragment fragment = new RecomHomePageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", tag);
        bundle.putString("skill_id", skill_id);
        fragment.setArguments(bundle);
        return fragment;
    }

    //获取首页推荐的数据
    private void tuijianData(SmartRefreshLayout smartRefreshLayout) {
        RxUtils.loading(commonModel.getSkillGodList("1", "", "", "", skill_id, mPullRefreshBean.pageIndex), this)
                .subscribe(new ErrorHandleSubscriber<MainHomePageBean>(mErrorHandler) {
                    @Override
                    public void onNext(MainHomePageBean mainHomePageBean) {
                        List<MainHomePageBean.DataBean> data = mainHomePageBean.getData();
                        //设置数据，包括加载更多，下拉刷新，无数据的情况都处理过了
                        new DealRefreshHelper<MainHomePageBean.DataBean>().dealDataToUI(smartRefreshLayout, mAdapter, noData, data, mDataList, mPullRefreshBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<MainHomePageBean.DataBean>().dealDataToUI(smartRefreshLayout, mAdapter, noData, new ArrayList<>(), mDataList, mPullRefreshBean);
                    }
                });
    }

    //获取非推荐的数据
    private void feiTuiData(SmartRefreshLayout smartRefreshLayout) {
//        RxUtils.loading(commonModel.getSkillGodList("1", "", "", "", skill_id, mPullRefreshBean.pageIndex), this)
        RxUtils.loading(commonModel.getSkillGodList("", "", "", "", skill_id, mPullRefreshBean.pageIndex), this)
                .subscribe(new ErrorHandleSubscriber<MainHomePageBean>(mErrorHandler) {
                    @Override
                    public void onNext(MainHomePageBean mainHomePageBean) {
                        List<MainHomePageBean.DataBean> data = mainHomePageBean.getData();
                        //设置数据，包括加载更多，下拉刷新，无数据的情况都处理过了
                        new DealRefreshHelper<MainHomePageBean.DataBean>().dealDataToUI(smartRefreshLayout, mAdapter, noData, data, mDataList, mPullRefreshBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<MainHomePageBean.DataBean>().dealDataToUI(smartRefreshLayout, mAdapter, noData, new ArrayList<>(), mDataList, mPullRefreshBean);
                    }
                });
    }

    @Override
    public View getScrollableView() {
        return scrollView;
    }

}
