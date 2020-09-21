package com.qutu.talk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.gyf.immersionbar.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.qutu.talk.R;
import com.qutu.talk.adapter.MainHomeRoomAdapter_1;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.RoomListResult;
import com.qutu.talk.bean.RoomSimpleIntro;
import com.qutu.talk.bean.RoomTypeResult;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class MoreRoomListActivity extends MyBaseArmActivity {


    @Inject
    CommonModel commonModel;

    @BindView(R.id.view_main_bar)
    View mStatuesView;
    @BindView(R.id.toolbar_back)
    RelativeLayout mToolbarBack;
    @BindView(R.id.rcv_more_room)
    RecyclerView mRecyclerView;

    DelegateAdapter mDelegateAdapter;

    MainHomeRoomAdapter_1 mMainHomeRoomAdapter_1;

    List<RoomSimpleIntro> mRoomSimpleIntroList = new ArrayList<>();

    String mParentId = "";

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
        return R.layout.activity_more_room_list;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        ImmersionBar.with(this)
                .reset()
                .statusBarView(mStatuesView)
                .statusBarColor(R.color.white)
                .autoDarkModeEnable(true)
                .init();

        Intent intent = getIntent();

        if(intent != null && intent.getExtras() != null){

            String title = intent.getStringExtra("title");

            mParentId = intent.getStringExtra("parend_id");

            setTitle(title);

            initRecyclerView();

            if(mParentId.equals("-9999")){//房间推荐
                loadRoomList();
            } else {
                loadRoomList(mParentId);
            }


        } else {
            finish();
        }

    }

    private void initRecyclerView() {

        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);

        mRecyclerView.setLayoutManager(layoutManager);

        //设置服用池设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）：
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

        viewPool.setMaxRecycledViews(0, 10);

        mRecyclerView.setRecycledViewPool(viewPool);

        //创建delegateadapter
        mDelegateAdapter = new DelegateAdapter(layoutManager, false);

        mRecyclerView.setAdapter(mDelegateAdapter);

        initAdapter();

    }

    private void initAdapter() {

        StaggeredGridLayoutHelper staggeredGridLayoutHelper = new StaggeredGridLayoutHelper(2);
//                                staggeredGridLayoutHelper.setHGap(QMUIDisplayHelper.dpToPx(8));
        staggeredGridLayoutHelper.setGap(QMUIDisplayHelper.dpToPx(10));

        mMainHomeRoomAdapter_1 = new MainHomeRoomAdapter_1(MoreRoomListActivity.this, R.layout.item_home_room, mRoomSimpleIntroList, staggeredGridLayoutHelper);

        mMainHomeRoomAdapter_1.setOnClickListener(new MainHomeRoomAdapter_1.OnChildViewClickListener() {
            @Override
            public void OnChildClick(View view, RoomSimpleIntro roomSimpleIntro) {
                switch (view.getId()){
                    case R.id.img_people_head:
                        enterData(roomSimpleIntro.getUid(), "", commonModel, 1, roomSimpleIntro.getRoom_cover());
                        break;

                }
            }
        });

        mMainHomeRoomAdapter_1.setStartPosition(-1);

        RoomTypeResult.DataBean dataBean = new RoomTypeResult.DataBean();
        dataBean.setId(mParentId);

        mMainHomeRoomAdapter_1.setParentType(dataBean);

        mDelegateAdapter.addAdapter(mMainHomeRoomAdapter_1);
    }

    /**
     * 获取房间列表
     *
     */
    private void loadRoomList(String id) {

        RxUtils.loading(commonModel.room_list_three(id, "16"), this)
                .subscribe(new ErrorHandleSubscriber<RoomListResult>(mErrorHandler) {
                    @Override
                    public void onNext(RoomListResult todayRecommBean) {

                        if (todayRecommBean != null && todayRecommBean.getData() != null) {

                            List<RoomSimpleIntro> roomList = todayRecommBean.getData();

                            mRoomSimpleIntroList.clear();

                            if (roomList.size() > 0) {

                                mRoomSimpleIntroList.addAll(roomList);

                            }

                            mMainHomeRoomAdapter_1.notifyDataSetChanged();

                        }

                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);

                    }
                });

    }

    /**
     * 获取房间列表
     *
     */
    private void loadRoomList() {

        RxUtils.loading(commonModel.tuijian_room_three("16"), this)
                .subscribe(new ErrorHandleSubscriber<RoomListResult>(mErrorHandler) {
                    @Override
                    public void onNext(RoomListResult todayRecommBean) {

                        if (todayRecommBean != null && todayRecommBean.getData() != null) {

                            List<RoomSimpleIntro> roomList = todayRecommBean.getData();

                            mRoomSimpleIntroList.clear();

                            if (roomList.size() > 0) {

                                mRoomSimpleIntroList.addAll(roomList);

                            }

                            mMainHomeRoomAdapter_1.notifyDataSetChanged();

                        }

                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);

                    }
                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.toolbar_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
        }
    }
}
