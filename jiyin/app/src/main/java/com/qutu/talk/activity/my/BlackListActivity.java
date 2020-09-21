package com.qutu.talk.activity.my;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jess.arms.di.component.AppComponent;
import com.qutu.talk.R;
import com.qutu.talk.adapter.BlackListAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.BaseBean;
import com.qutu.talk.bean.UserFriend;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.ItemDecorationUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 黑名单列表页面
 * 老王
 */
public class BlackListActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.heat_topic_recyc)
    RecyclerView heatTopicRecyc;
    @BindView(R.id.heat_topic_smat)
    SmartRefreshLayout heatTopicSmat;

    private BlackListAdapter blackListAdapter;
    private int pos;
    private int page = 1;

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
        return R.layout.activity_heat_topic;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        blackListAdapter = new BlackListAdapter();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        ItemDecorationUtil did = new ItemDecorationUtil(this, ItemDecorationUtil.VERTICAL);
        heatTopicRecyc.addItemDecoration(did);
        heatTopicRecyc.setLayoutManager(llm);
        heatTopicRecyc.setAdapter(blackListAdapter);

        getBlackList(page);

        blackListAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            setRemoveBlackList(String.valueOf(UserManager.getUser().getUserId()), String.valueOf(blackListAdapter.getData().get(position).getId()));
            pos = position;
        });
        heatTopicSmat.setOnRefreshListener(refreshlayout -> {
            page = 1;
            getBlackList(page);
        });
        heatTopicSmat.setOnLoadMoreListener(refreshlayout -> {
            page++;
            getBlackList(page);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarTitle("黑名单", true);
    }

    //获取黑名单列表
    private void getBlackList(int pagee) {
        RxUtils.loading(commonModel.blackList(String.valueOf(UserManager.getUser().getUserId()), String.valueOf(pagee)), this)
                .subscribe(new ErrorHandleSubscriber<UserFriend>(mErrorHandler) {
                    @Override
                    public void onNext(UserFriend userFriend) {
                        if (pagee == 1) {
                            heatTopicSmat.finishRefresh();
                            blackListAdapter.setNewData(userFriend.getData());
                        } else {
                            heatTopicSmat.finishLoadMore();
                            blackListAdapter.addData(userFriend.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        heatTopicSmat.finishRefresh();
                        heatTopicSmat.finishLoadMore();
                    }
                });
    }

    private void setRemoveBlackList(String userId, String fromId) {
        RxUtils.loading(commonModel.cancel_black(userId, fromId), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        toast(baseBean.getMessage());
                        blackListAdapter.remove(pos);
                        blackListAdapter.notifyDataSetChanged();
                    }
                });
    }
}
