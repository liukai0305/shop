package com.qutu.talk.activity.dashen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.qutu.talk.R;
import com.qutu.talk.activity.order.ConfirmOrderActivity;
import com.qutu.talk.adapter.dashen.DaShenListAdapter;
import com.qutu.talk.adapter.dashen.ScreenDuanWeiAdapter;
import com.qutu.talk.adapter.dashen.ScreenSexAdapter;
import com.qutu.talk.adapter.dashen.SreenPriceAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.PullRefreshBean;
import com.qutu.talk.bean.dashen.DuanWeiBean;
import com.qutu.talk.bean.dashen.MainHomePageBean;
import com.qutu.talk.bean.dashen.ScreenPriceBean;
import com.qutu.talk.bean.dashen.ScreenSexBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.DealRefreshHelper;
import com.qutu.talk.view.ShapeTextView;
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

/**
 * 大神列表页面
 * da_shen_list_item  adapter的布局
 */

public class DaShenListActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.dashen_list_recycler)
    RecyclerView dashenListRecycler;
    @BindView(R.id.dashen_list_smart)
    SmartRefreshLayout dashenListSmart;
    @BindView(R.id.screen_duanwei_recyc)
    RecyclerView screenDuanweiRecyc;
    @BindView(R.id.screen_price_recyc)
    RecyclerView screenPriceRecyc;
    @BindView(R.id.screen_ok_btn)
    ShapeTextView screenOkBtn;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.screen_sex_recyc)
    RecyclerView screenSexRecyc;
    @BindView(R.id.no_data)
    LinearLayout noData;
    @BindView(R.id.screen_price_text)
    TextView screenPriceText;

    private DaShenListAdapter mAdapter;
    private PullRefreshBean mPullRefreshBean = new PullRefreshBean();
    private ArrayList<MainHomePageBean.DataBean> mDataList = new ArrayList<>();

    private ScreenSexAdapter mSexAdapter;
    private ScreenDuanWeiAdapter mDuanWeiAdapter;
    private SreenPriceAdapter mPriceAdapter;

    private ArrayList<ScreenSexBean.DataBean> mSexList;
    private ArrayList<DuanWeiBean.DataBean> mDuanWeiDataList;
    private ArrayList<ScreenPriceBean.DataBean> mPriceList;

    private String mId; // 技能ID
    private String mSex, mDuanWei, mPrice; // 性别，段位，价格
    private String mUserId;

    private int tag = 0;
    private boolean isBtnOk = false;
    private Intent mIntent;

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
        return R.layout.activity_da_shen_list;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mId = getIntent().getStringExtra("id");
        mUserId = String.valueOf(UserManager.getUser().getUserId());
        setTitle(getIntent().getStringExtra("name"));

        mSexList = new ArrayList<>();
        mDuanWeiDataList = new ArrayList<>();
        mPriceList = new ArrayList<>();

        mAdapter = new DaShenListAdapter(R.layout.da_shen_list_item, mDataList, this, mUserId);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        dashenListRecycler.setLayoutManager(llm);
        dashenListRecycler.setAdapter(mAdapter);

        mSexAdapter = new ScreenSexAdapter(this);
        GridLayoutManager sexglm = new GridLayoutManager(this, 3);
        screenSexRecyc.setLayoutManager(sexglm);
        screenSexRecyc.setAdapter(mSexAdapter);

        mDuanWeiAdapter = new ScreenDuanWeiAdapter(this);
        GridLayoutManager glm = new GridLayoutManager(this, 3);
        screenDuanweiRecyc.setLayoutManager(glm);
        screenDuanweiRecyc.setAdapter(mDuanWeiAdapter);

        mPriceAdapter = new SreenPriceAdapter(this);
        GridLayoutManager glm2 = new GridLayoutManager(this, 3);
        screenPriceRecyc.setLayoutManager(glm2);
        screenPriceRecyc.setAdapter(mPriceAdapter);

        getData();
        getSexData();
        getDuanWeiData();
        getPriceData();
        getAdapterOnClick();
        drawerLayout.addDrawerListener(drawerListener);

    }

    //获取数据
    private void getData() {
        RxUtils.loading(commonModel.getSkillGodList("", "", "", "", mId, mPullRefreshBean.pageIndex), this)
                .subscribe(new ErrorHandleSubscriber<MainHomePageBean>(mErrorHandler) {
                    @Override
                    public void onNext(MainHomePageBean mainHomePageBean) {
                        List<MainHomePageBean.DataBean> data = mainHomePageBean.getData();
                        new DealRefreshHelper<MainHomePageBean.DataBean>().dealDataToUI(dashenListSmart, mAdapter, noData, data, mDataList, mPullRefreshBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<MainHomePageBean.DataBean>().dealDataToUI(dashenListSmart, mAdapter, noData, new ArrayList<>(), mDataList, mPullRefreshBean);
                    }
                });
    }

    //筛选后获取数据
    private void getShaiData(String sex, String levelId, String priceId) {
        showDialogLoding();
        RxUtils.loading(commonModel.getSkillGodList("", sex, levelId, priceId, mId, mPullRefreshBean.pageIndex), this)
                .subscribe(new ErrorHandleSubscriber<MainHomePageBean>(mErrorHandler) {
                    @Override
                    public void onNext(MainHomePageBean mainHomePageBean) {
                        if (mPullRefreshBean.pageIndex == 1) {
                            mDataList.clear();
                        }
                        List<MainHomePageBean.DataBean> data = mainHomePageBean.getData();
                        new DealRefreshHelper<MainHomePageBean.DataBean>().dealDataToUI(dashenListSmart, mAdapter, noData, data, mDataList, mPullRefreshBean);
                        tag = 1;
                        disDialogLoding();
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<MainHomePageBean.DataBean>().dealDataToUI(dashenListSmart, mAdapter, noData, new ArrayList<>(), mDataList, mPullRefreshBean);
                        disDialogLoding();
                    }
                });
    }

    //添加性别数据
    private void getSexData() {
        ScreenSexBean.DataBean sexBean1 = new ScreenSexBean.DataBean("", "全部", true, true);
        mSexList.add(sexBean1);
        ScreenSexBean.DataBean sexBean2 = new ScreenSexBean.DataBean("1", "男生", false, false);
        mSexList.add(sexBean2);
        ScreenSexBean.DataBean sexBean3 = new ScreenSexBean.DataBean("2", "女生", false, false);
        mSexList.add(sexBean3);

        mSexAdapter.setNewData(mSexList);
    }

    //获取段位数据
    private void getDuanWeiData() {
        RxUtils.loading(commonModel.getSkillLevelListJava(mId), this)
                .subscribe(new ErrorHandleSubscriber<DuanWeiBean>(mErrorHandler) {
                    @Override
                    public void onNext(DuanWeiBean duanWeiBean) {
                        if (duanWeiBean.getData().size() == 0) {
                            screenPriceText.setVisibility(View.GONE);
                            screenDuanweiRecyc.setVisibility(View.GONE);
                        } else {
                            DuanWeiBean.DataBean dataBean = new DuanWeiBean.DataBean();
                            dataBean.isSelector = true;
                            dataBean.isClick = true;
                            dataBean.setName("全部");
                            dataBean.setId("");
                            mDuanWeiDataList.add(dataBean);
                            mDuanWeiDataList.addAll(duanWeiBean.getData());
                            mDuanWeiAdapter.setNewData(mDuanWeiDataList);
                        }

                    }
                });
    }

    //获取价格列表
    private void getPriceData() {
        RxUtils.loading(commonModel.getSkillPriceListJava(mId), this)
                .subscribe(new ErrorHandleSubscriber<ScreenPriceBean>(mErrorHandler) {
                    @Override
                    public void onNext(ScreenPriceBean screenPriceBean) {
                        ScreenPriceBean.DataBean dataBean = new ScreenPriceBean.DataBean();
                        dataBean.setChecked(1);
                        dataBean.isSelector = true;
                        dataBean.isClick = true;
                        dataBean.setPrice("全部");
                        dataBean.setId("");
                        mPriceList.add(dataBean);
                        List<ScreenPriceBean.DataBean> data = screenPriceBean.getData();
                        for (int a = 0; a < data.size(); a++) {
                            ScreenPriceBean.DataBean dataBean1 = data.get(a);
                            dataBean1.setChecked(0);
                            mPriceList.add(dataBean1);
                        }
                        mPriceAdapter.setNewData(mPriceList);
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setToolbarRightText("筛选", v -> {
            drawerLayout.openDrawer(Gravity.RIGHT);
        }, R.color.textcentercolor);
    }


    @OnClick({R.id.screen_ok_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.screen_ok_btn:
                isBtnOk = true;
                drawerLayout.closeDrawer(Gravity.RIGHT);
                break;
        }
    }

    //上拉刷新下拉加载以及适配器的点击事件
    private void getAdapterOnClick() {
        dashenListSmart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPullRefreshBean.setLoardMore(mPullRefreshBean, dashenListSmart);
                if (tag == 0) {
                    getData();
                } else {
                    getShaiData(mSex, mDuanWei, mPrice);
                }

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPullRefreshBean.setRefresh(mPullRefreshBean, dashenListSmart);
                if (tag == 0) {
                    getData();
                } else {
                    getShaiData(mSex, mDuanWei, mPrice);
                }
            }
        });

        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            MainHomePageBean.DataBean dataBean = mAdapter.getData().get(position);
            switch (view.getId()) {
                case R.id.skill_list_sure: //确认下单
                    mIntent = new Intent(DaShenListActivity.this, ConfirmOrderActivity.class);
                    mIntent.putExtra("skillId", String.valueOf(dataBean.getSkill_id()));
                    mIntent.putExtra("id", dataBean.getId());
                    mIntent.putExtra("userId", String.valueOf(dataBean.getUser_id()));
                    mIntent.putExtra("img_1", dataBean.getImg_1());
                    mIntent.putExtra("naicName", dataBean.getNickname());
                    mIntent.putExtra("price", String.valueOf(dataBean.getPrice()));
                    mIntent.putExtra("unit", dataBean.getUnit());
                    mIntent.putExtra("skillName", dataBean.getSkill_name());
                    startActivity(mIntent);
                    break;
                case R.id.da_shen_item: //大神技能详细信息
                    mIntent = new Intent(DaShenListActivity.this, GodPerCenterActivity.class);
                    mIntent.putExtra("id", dataBean.getId());
                    mIntent.putExtra("skillName", dataBean.getSkill_name());
                    mIntent.putExtra("skillId", String.valueOf(dataBean.getSkill_id()));
                    mIntent.putExtra("price", String.valueOf(dataBean.getPrice()));
                    mIntent.putExtra("unit", dataBean.getUnit());
                    startActivity(mIntent);
                    break;
            }
        });

        mSexAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            List<ScreenSexBean.DataBean> data = mSexAdapter.getData();
            for (ScreenSexBean.DataBean dataList : data) {
                dataList.isClick = false;
            }
            data.get(position).isClick = true;
            mSexAdapter.isAdapter = false;
            mSexAdapter.notifyDataSetChanged();
        });

        mDuanWeiAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            List<DuanWeiBean.DataBean> data = mDuanWeiAdapter.getData();
            for (DuanWeiBean.DataBean dataList : data) {
                dataList.isClick = false;
            }
            data.get(position).isClick = true;
            mDuanWeiAdapter.isAdapter = false;
            mDuanWeiAdapter.notifyDataSetChanged();
        });

        mPriceAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            List<ScreenPriceBean.DataBean> data1 = mPriceAdapter.getData();
            for (ScreenPriceBean.DataBean dataList : data1) {
                dataList.isClick = false;
            }
            data1.get(position).isClick = true;
            mPriceAdapter.isAdapter = false;
            mPriceAdapter.notifyDataSetChanged();
        });

    }

    //侧滑监听
    DrawerLayout.DrawerListener drawerListener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            //打开侧滑界面触发
        }

        @Override
        public void onDrawerClosed(View drawerView) {
//关闭侧滑界面触发
            List<ScreenSexBean.DataBean> sexData = mSexAdapter.getData();
            if (!mSexAdapter.isAdapter) {
                for (int a = 0; a < sexData.size(); a++) {
                    if (isBtnOk) {
                        sexData.get(a).isSelector = sexData.get(a).isClick;
                        sexData.get(a).isClick = true;
                    } else {
                        sexData.get(a).isClick = false;
                    }
//                    LogUtils.debugInfo("====走" + "性别isSelector" + a + sexData.get(a).isSelector);
//                    LogUtils.debugInfo("====走" + "性别isClick" + a + sexData.get(a).isSelector);
                }
                mSexAdapter.isAdapter = true;
            }
            mSexAdapter.notifyDataSetChanged();

            List<DuanWeiBean.DataBean> duanweiData = mDuanWeiAdapter.getData();
            if (!mDuanWeiAdapter.isAdapter) {
                for (int a = 0; a < duanweiData.size(); a++) {
                    if (isBtnOk) {
                        duanweiData.get(a).isSelector = duanweiData.get(a).isClick;
                        duanweiData.get(a).isClick = true;
                    } else {
                        duanweiData.get(a).isClick = false;
                    }
//                    LogUtils.debugInfo("====走" + "段位isSelector" + a + duanweiData.get(a).isSelector);
//                    LogUtils.debugInfo("====走" + "段位isClick" + a + duanweiData.get(a).isSelector);
                }
                mDuanWeiAdapter.isAdapter = true;
            }
            mDuanWeiAdapter.notifyDataSetChanged();

            List<ScreenPriceBean.DataBean> priceData = mPriceAdapter.getData();
            if (!mPriceAdapter.isAdapter) {
                for (int a = 0; a < priceData.size(); a++) {
                    if (isBtnOk) {
                        priceData.get(a).isSelector = priceData.get(a).isClick;
                        priceData.get(a).isClick = true;
                    } else {
                        priceData.get(a).isClick = false;
                    }
//                    LogUtils.debugInfo("====走" + "价格isSelector" + a + priceData.get(a).isSelector);
//                    LogUtils.debugInfo("====走" + "价格isClick" + a + priceData.get(a).isSelector);
                }
                mPriceAdapter.isAdapter = true;
            }
            mPriceAdapter.notifyDataSetChanged();

            List<ScreenSexBean.DataBean> data2 = mSexAdapter.getData();
            for (ScreenSexBean.DataBean dataBean : data2) {
                if (dataBean.isSelector) {
                    mSex = dataBean.getId();
                }
            }

            List<DuanWeiBean.DataBean> data1 = mDuanWeiAdapter.getData();
            for (DuanWeiBean.DataBean dataBean : data1) {
                if (dataBean.isSelector) {
                    mDuanWei = dataBean.getId();
                }
            }

            List<ScreenPriceBean.DataBean> data = mPriceAdapter.getData();
            for (ScreenPriceBean.DataBean dataBean : data) {
                if (dataBean.isSelector) {
                    mPrice = dataBean.getId();
                }
            }

            if (isBtnOk) {
                getShaiData(mSex, mDuanWei, mPrice);
            }

            isBtnOk = false;
        }

        @Override
        public void onDrawerStateChanged(int newState) {
            //状态改变时触发
        }
    };
}
