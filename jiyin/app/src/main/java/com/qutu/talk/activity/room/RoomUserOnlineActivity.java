package com.qutu.talk.activity.room;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.qutu.talk.R;
import com.qutu.talk.activity.my.MyPersonalCenterActivity;
import com.qutu.talk.activity.my.MyPersonalCenterTwoActivity;
import com.qutu.talk.adapter.RoomOnlineAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.GetGapResult;
import com.qutu.talk.bean.GetOnlineUserResult;
import com.qutu.talk.bean.MessageBean;
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
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class RoomUserOnlineActivity extends MyBaseArmActivity {


    @Inject
    CommonModel commonModel;

    @BindView(R.id.img_close_pages)
    ImageView mImgClose;
    @BindView(R.id.tv_user_count)
    TextView mTvUserCount;
    @BindView(R.id.rcv_list_user)
    RecyclerView mRcvListUser;
    @BindView(R.id.smart_layout)
    SmartRefreshLayout mSmartLayout;
    @BindView(R.id.root_layout)
    ConstraintLayout mRootLayout;
    @BindView(R.id.img_bg)
    ImageView mImageView;

    List<GetOnlineUserResult.DataBean.UsersBean> mDataList = new ArrayList<>();

    RoomOnlineAdapter mAdapter;

    PullRefreshBean mPullRefreshBean = new PullRefreshBean();

    String mRoomId = "", mRoomBg = "";

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
        return R.layout.activity_room_user_online;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

//        ImmersionBar.with(this).transparentBar().titleBar(mRootLayout).init();
        ImmersionBar.with(this)
                .reset()
                .titleBar(mRootLayout)
                .keyboardEnable(true)
                .init();

        mSmartLayout.setEnableLoadMore(false);

        mPullRefreshBean.setDisableLoadMore(true);

        mGestureDetector = new GestureDetector(this, new MyGestureDector());

        mSmartLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                mPullRefreshBean.setRefresh(mPullRefreshBean,mSmartLayout);

                loadData();

            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(this);

        manager.setOrientation(LinearLayoutManager.VERTICAL);

        mRcvListUser.setLayoutManager(manager);

        mAdapter = new RoomOnlineAdapter(mDataList, this);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(mDataList.size() == 0 || mDataList.get(position) == null || position>=mDataList.size()){
                    return;
                }
                Intent intent1 = new Intent(RoomUserOnlineActivity.this, MyPersonalCenterTwoActivity.class);

                if (mDataList.get(position).getId().equals(String.valueOf(UserManager.getUser().getUserId()))) {
                    intent1.putExtra("sign", 0);
                    intent1.putExtra("id", "");
                } else {
                    intent1.putExtra("sign", 1);
                    intent1.putExtra("id", mDataList.get(position).getId() + "");
                }
                intent1.putExtra("isRoom", false);
                ArmsUtils.startActivity(intent1);
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
            }
        });

        mRcvListUser.setAdapter(mAdapter);


        mRoomId = getIntent().getStringExtra("room_id");

        mRoomBg = getIntent().getStringExtra("room_bg");

        if(!TextUtils.isEmpty(mRoomBg)){
            ArmsUtils.obtainAppComponentFromContext(this)
                    .imageLoader()
                    .loadImage(this, ImageConfigImpl
                            .builder()
                            .url(mRoomBg)
                            .imageView(mImageView)
                            .build());

        }

        loadData();

    }

    private void loadData() {

        RxUtils.loading(commonModel.room_online_users(mRoomId), this)
                .subscribe(new ErrorHandleSubscriber<GetOnlineUserResult>(mErrorHandler) {
                    @Override
                    public void onNext(GetOnlineUserResult gapResult) {
                        if (gapResult != null && gapResult.getData() != null && gapResult.getData().getUsers() != null) {

                            List<GetOnlineUserResult.DataBean.UsersBean> usersBeans = gapResult.getData().getUsers();

                            mTvUserCount.setText("房间在线用户（"+usersBeans.size()+"）");
                            new DealRefreshHelper<GetOnlineUserResult.DataBean.UsersBean>().dealDataToUI(mSmartLayout,mAdapter,null,usersBeans,mDataList,mPullRefreshBean);

                        } else {
                            new DealRefreshHelper<GetOnlineUserResult.DataBean.UsersBean>().dealDataToUI(mSmartLayout,mAdapter,null,new ArrayList<>(),mDataList,mPullRefreshBean);
                        }
                    }
                });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, AdminHomeActivity.class));
        finish();
    }

    @OnClick({R.id.img_close_pages})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_close_pages:
                startActivity(new Intent(RoomUserOnlineActivity.this, AdminHomeActivity.class));
                finish();
                break;
            case R.id.tv_user_count:
                break;
        }
    }
    private GestureDetector mGestureDetector;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mGestureDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    class MyGestureDector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //e1:手指按下的移动事件.
            float e1X = e1.getRawX();
            float e1Y = e1.getRawY();
            // e2 : 手指移动的动作事件.
            float e2X = e2.getRawX();
            float e2Y = e2.getRawY();
            if (Math.abs(e2X - e1X) < 50) {
                LogUtils.debugInfo("左右滑动小于50px");
                return super.onFling(e1, e2, velocityX, velocityY);
            } else if (Math.abs(e2Y - e1Y) > 200) {
                LogUtils.debugInfo("手势上下滑动");
                return super.onFling(e1, e2, velocityX, velocityY);
            } else if (Math.abs(e2X - e1X) > 50) {
                if ((e2X - e1X) > 0) {
                    LogUtils.debugInfo("右滑");
                    startActivity(new Intent(RoomUserOnlineActivity.this, AdminHomeActivity.class));
                    finish();
                    return super.onFling(e1, e2, velocityX, velocityY);
                } else {
                    LogUtils.debugInfo("左滑");
                }
                return true;
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
}
