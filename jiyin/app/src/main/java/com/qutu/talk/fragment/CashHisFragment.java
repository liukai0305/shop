package com.qutu.talk.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.qutu.talk.adapter.CashHisAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmFragment;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.CashHis;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 作者:sgm
 * 描述:粉丝，关注
 */
public class CashHisFragment extends MyBaseArmFragment {


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
    SmartRefreshLayout refreshLayout;
    private int page = 1;

    private CashHisAdapter friendAdapter;

    private int id;
    private int mType;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    @Override
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_item_fans);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        id = getArguments().getInt("id");
        mType = getArguments().getInt("type");
        friendAdapter = new CashHisAdapter(id);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setAdapter(friendAdapter);

        if (id == 0) {
            loadDuihuan();
        } else {
            loadCash(page);
        }

        refreshLayout.setOnRefreshListener(refreshlayout -> {
            page = 1;
            if (id == 0) {
                loadDuihuan();
            } else {
                loadCash(page);
            }
        });
        refreshLayout.setOnLoadMoreListener(refreshlayout -> {

            page++;
            if (id == 0) {
                loadDuihuan();
            } else {
                loadCash(page);
            }
        });

    }

    /**
     * 兑换记录
     */
    private void loadDuihuan() {
        RxUtils.loading(commonModel.exchange_log(String.valueOf(UserManager.getUser().getUserId()),
                page + ""), this)
                .subscribe(new ErrorHandleSubscriber<CashHis>(mErrorHandler) {
                    @Override
                    public void onNext(CashHis userFriend) {
                        if (userFriend.getData().size() != 0) {
                            noData.setVisibility(View.GONE);
                        } else {
                            noData.setVisibility(View.VISIBLE);
                        }
                        if (page == 1) {
                            friendAdapter.setNewData(userFriend.getData());
                            refreshLayout.finishRefresh();
                        } else {
                            friendAdapter.addData(userFriend.getData());
                            refreshLayout.finishLoadMore();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
                    }
                });
    }

    /**
     * 提现记录
     */
    private void loadCash(int page) {
        RxUtils.loading(commonModel.tixian_log(String.valueOf(UserManager.getUser().getUserId()), page, mType), this)
                .subscribe(new ErrorHandleSubscriber<CashHis>(mErrorHandler) {
                    @Override
                    public void onNext(CashHis userFriend) {
                        if (userFriend.getData().size() != 0) {
                            noData.setVisibility(View.GONE);
                        } else {
                            noData.setVisibility(View.VISIBLE);
                        }
                        if (page == 1) {
                            friendAdapter.setNewData(userFriend.getData());
                        } else {
                            friendAdapter.addData(userFriend.getData());
                        }

                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
                    }
                });
    }


    @Override
    public void setData(@Nullable Object data) {

    }

    public static CashHisFragment getInstance(int tag, int type) {
        CashHisFragment fragment = new CashHisFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", tag);
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }
}
