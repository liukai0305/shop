package com.qutu.talk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.activity.my.MyPersonalCenterActivity;
import com.qutu.talk.activity.my.MyPersonalCenterTwoActivity;
import com.qutu.talk.adapter.MyConcernAdapter;
import com.qutu.talk.adapter.SearchUserAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.BaseBean;
import com.qutu.talk.bean.PullRefreshBean;
import com.qutu.talk.bean.Search;
import com.qutu.talk.bean.UserFriend;
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
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 搜索用户
 */
public class SearchUserActivity extends MyBaseArmActivity {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.smart)
    SmartRefreshLayout refreshLayout;

    @Inject
    CommonModel commonModel;
    private String name;

    private MyConcernAdapter friendAdapter;
    private List<UserFriend.DataBean> mDataList = new ArrayList<>();
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
        return R.layout.activity_search_user;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        name = getIntent().getStringExtra("name");
        friendAdapter = new MyConcernAdapter(R.layout.my_concern_item,mDataList);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(friendAdapter);

        loadData();

        refreshLayout.setOnRefreshListener(refreshlayout -> {
//            page = 1;
//            loadData();
            mPullRefreshBean.setRefresh(mPullRefreshBean, refreshLayout);//下拉刷新时 的处理
            loadData();
        });
        refreshLayout.setOnLoadMoreListener(refreshlayout -> {
//            page++;
//            loadData();
            mPullRefreshBean.setLoardMore(mPullRefreshBean,refreshLayout);
            loadData();
        });

        friendAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.btn_ok:
                    fllow(String.valueOf(friendAdapter.getData().get(position).getId()), position);
                    break;
                case R.id.btn_no_ok:
                    cancelFllow(String.valueOf(friendAdapter.getData().get(position).getId()), position);
                    break;
                case R.id.ci_head:
                    Intent intent = new Intent(SearchUserActivity.this, MyPersonalCenterTwoActivity.class);
                    if (friendAdapter.getData().get(position).getId() == UserManager.getUser().getUserId()) {
                        intent.putExtra("sign", 0);
                        intent.putExtra("id", "");
                    } else {
                        intent.putExtra("sign", 1);
                        intent.putExtra("id", friendAdapter.getData().get(position).getId() + "");
                    }
                    ArmsUtils.startActivity(intent);
                    break;
            }
        });

    }

    private void loadData() {
        RxUtils.loading(commonModel.search_all(UserManager.getUser() + "", name, "user", mPullRefreshBean.pageIndex + ""), this)
                .subscribe(new ErrorHandleSubscriber<UserFriend>(mErrorHandler) {
                    @Override
                    public void onNext(UserFriend search) {
                        List<UserFriend.DataBean> list = search.getData();
                        new DealRefreshHelper<UserFriend.DataBean>().dealDataToUI(refreshLayout, friendAdapter, null, list, mDataList, mPullRefreshBean);
//                        if (page == 1) {
//                            friendAdapter.setNewData(search.getData());
//                            refreshLayout.finishRefresh();
//                        } else {
//                            friendAdapter.addData(search.getData());
//                            refreshLayout.finishLoadMore();
//                        }

                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<UserFriend.DataBean>().dealDataToUI(refreshLayout, friendAdapter, null, new ArrayList<>(), mDataList, mPullRefreshBean);
                    }
                });
    }

    /**
     * 关注
     */
    private void fllow(String id, int pos) {
        RxUtils.loading(commonModel.follow(String.valueOf(UserManager.getUser().getUserId()), id), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean microphone) {
                        showToast("关注成功");
                        friendAdapter.notifyItemChanged(pos, "follow");
                    }
                });
    }


    /**
     * 取消关注
     */
    private void cancelFllow(String id, int pos) {
        RxUtils.loading(commonModel.cancel_follow(String.valueOf(UserManager.getUser().getUserId()), id), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean microphone) {
                        showToast("取消成功");
                        friendAdapter.notifyItemChanged(pos, "cancelFollow");
                    }
                });
    }

}
