package com.qutu.talk.activity.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.adapter.FriendAdapter;
import com.qutu.talk.adapter.OfficeListAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.BaseWebActivity;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.BaseBean;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.OffiMessageBean;
import com.qutu.talk.bean.UserFriend;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.Constant;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class MessageOfficeActivity extends MyBaseArmActivity {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.smart)
    SmartRefreshLayout refreshLayout;

    @Inject
    CommonModel commonModel;
    private int page = 1;

    private OfficeListAdapter officeListAdapter;

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
        return R.layout.activity_message_office;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        officeListAdapter = new OfficeListAdapter();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(officeListAdapter);

        loadData();

        refreshLayout.setOnRefreshListener(refreshlayout -> {
            page = 1;
            loadData();
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                loadData();
            }
        });

//        setToolbarRightText("清空消息", v -> {
//            if(officeListAdapter.getData() == null || officeListAdapter.getData().size()==0){
//                showToast("暂无消息");
//                return;
//            }
//            RxUtils.loading(commonModel.clear_message(String.valueOf(UserManager.getUser().getUserId())), this)
//                    .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
//                        @Override
//                        public void onNext(BaseBean userFriend) {
//                            loadData();
//                        }
//                    });
//        },R.color.textcolor);

        officeListAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (!TextUtils.isEmpty(officeListAdapter.getData().get(position).getUrl())) {
                Intent intent = new Intent(this, BaseWebActivity.class);
                intent.putExtra("url", officeListAdapter.getData().get(position).getUrl());
                intent.putExtra("name", "");
                ArmsUtils.startActivity(intent);
            }
        });
    }

    private void loadData() {
        RxUtils.loading(commonModel.official_message(String.valueOf(UserManager.getUser().getUserId()), page), this)
                .subscribe(new ErrorHandleSubscriber<OffiMessageBean>(mErrorHandler) {
                    @Override
                    public void onNext(OffiMessageBean userFriend) {
                        if (page == 1) {

                            officeListAdapter.setNewData(userFriend.getData());
                        } else {
                            officeListAdapter.addData(userFriend.getData());
                        }
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
                        if (userFriend.getData() == null || userFriend.getData().isEmpty()) {
                            refreshLayout.finishLoadMoreWithNoMoreData();
                        }


                        Intent intent = new Intent();
                        intent.setAction("has_read_office");
                        sendBroadcast(intent);
//                        EventBus.getDefault().post(new FirstEvent("指定发送", Constant.SHUAXINGUANFANGXIAOXI));
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
                    }
                });
    }

}
