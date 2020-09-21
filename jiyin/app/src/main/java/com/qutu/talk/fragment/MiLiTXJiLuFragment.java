package com.qutu.talk.fragment;

/**
 * 我的金币体现记录页面
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
import com.qutu.talk.adapter.MiLiTXJiLuAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmFragment;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.CashHis;
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
import butterknife.Unbinder;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * A simple {@link Fragment} subclass.
 */
public class MiLiTXJiLuFragment extends MyBaseArmFragment {
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

    private MiLiTXJiLuAdapter mAdapter;
    private int mType;
    private List<CashHis.DataBean> mDataList = new ArrayList<>();
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
        mType = getArguments().getInt("type");

        mAdapter = new MiLiTXJiLuAdapter(R.layout.item_cash_his, mDataList);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(llm);
        recyclerview.setAdapter(mAdapter);

        loadCash();

        smart.setOnRefreshListener(refreshlayout -> {
            mPullRefreshBean.setRefresh(mPullRefreshBean, smart);//下拉刷新时 的处理
            loadCash();
        });
        smart.setOnLoadMoreListener(refreshlayout -> {
            mPullRefreshBean.setLoardMore(mPullRefreshBean, smart);
            loadCash();
        });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    //金币体现记录
    private void loadCash() {
        RxUtils.loading(commonModel.tixian_log(String.valueOf(UserManager.getUser().getUserId()), mPullRefreshBean.pageIndex, mType), this)
                .subscribe(new ErrorHandleSubscriber<CashHis>(mErrorHandler) {
                    @Override
                    public void onNext(CashHis userFriend) {
                        List<CashHis.DataBean> data = userFriend.getData();
                        new DealRefreshHelper<CashHis.DataBean>().dealDataToUI(smart, mAdapter, noData, data, mDataList, mPullRefreshBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<CashHis.DataBean>().dealDataToUI(smart, mAdapter, noData, new ArrayList<>(), mDataList, mPullRefreshBean);
                    }
                });
    }

    public static MiLiTXJiLuFragment getInstance(int type) {
        MiLiTXJiLuFragment fragment = new MiLiTXJiLuFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

}
