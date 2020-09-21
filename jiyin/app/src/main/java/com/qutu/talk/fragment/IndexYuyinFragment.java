package com.qutu.talk.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.adapter.HomeTopAdapter;
import com.qutu.talk.adapter.dashen.Shengyou2Adapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.HeaderViewPagerFragment;
import com.qutu.talk.bean.EnterRoom;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.HotBean;
import com.qutu.talk.bean.PullRefreshBean;
import com.qutu.talk.bean.RoomTypeResult;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.DealRefreshHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.qutu.talk.utils.Constant.FANHUIZHUYE;
import static com.qutu.talk.utils.Constant.XUANFUYINCANG;

/**
 * 作者:sgm
 * 描述:首页语音娱乐
 */
public class IndexYuyinFragment extends HeaderViewPagerFragment {



    //    @BindView(R.id.recyclerView)
//    public XRecyclerView recyclerView;
//    private RecomAdapter adapter;
    @Inject
    CommonModel commonModel;
    @BindView(R.id.myList1)
    RecyclerView myList1;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.no_data)
    LinearLayout noData;
    Unbinder unbinder;

    private int id;
    private HomeTopAdapter topAdapter;
    private Shengyou2Adapter horizontalAdapter;
    //    private HomeRecommendAdapter homeRecommendAdapter;
//    private HomeRecommendAdapter homeRecommendAdapter3;
//    private HomeBoyAdapter homeBoyAdapter;
//
//    private StartLoftBean girlData;
//    private StartLoftBean boyData;
    private RoomTypeResult roomTypeResult;
    private String old_id = "";

//    /**
//     * 是否为可见状态
//     */
//    protected boolean isVisible;
//    /**
//     * 是否初始视图完成
//     */
//    private boolean isPrepared;

    List<HotBean.DataBean> mDataList = new ArrayList<>();
    List<HotBean.DataBean> mDataList1 = new ArrayList<>();

    PullRefreshBean mPullRefreshBean = new PullRefreshBean();//下拉刷新帮助类

    SmartRefreshLayout mSmartRefreshLayout;

    //    View mRootView;
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    public void setRefersh(SmartRefreshLayout refershLayout) {
        this.mSmartRefreshLayout = refershLayout;
    }
    @Override
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = null;
        if (mRootView == null) {
            mRootView = ArmsUtils.inflate(getActivity(), R.layout.fragment_recom);
            unbinder = ButterKnife.bind(this, mRootView);
        }
        return mRootView;
    }

    @Override
    protected void visibleToLoadData() {
        super.visibleToLoadData();
        visibleToloadData();
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
//        id = getArguments().getInt("id");
//        roomTypeResult = (RoomTypeResult) getArguments().getSerializable("categorRoomBean");

    }

    protected void visibleToloadData() {
        topAdapter = new HomeTopAdapter(R.layout.item_recom_two, mDataList);
        horizontalAdapter = new Shengyou2Adapter( mDataList1);
        myList1.setLayoutManager(new GridLayoutManager(getActivity(),2));
        myList1.setAdapter(topAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recycler.setAdapter(horizontalAdapter);

        hotDataNew(mSmartRefreshLayout);
        topAdapter.setOnItemClickListener((adapter, view, position) -> {
            enterData(String.valueOf(topAdapter.getData().get(position).getUid()), "", commonModel, 1, topAdapter.getData().get(position).getRoom_cover());
        });
        horizontalAdapter.setOnItemClickListener((adapter, view, position) -> {
            enterData(String.valueOf(horizontalAdapter.getData().get(position).getUid()), "", commonModel, 1, horizontalAdapter.getData().get(position).getRoom_cover());
        });

    }


    /**
     * 下拉刷新
     *
     * @param refreshLayout
     */
    public void onRefresh(final SmartRefreshLayout refreshLayout) {

        mPullRefreshBean.setRefresh(mPullRefreshBean, refreshLayout);//下拉刷新时的处理
        hotDataNew(refreshLayout);

    }

    /**
     * 上拉加载
     *
     * @param refreshLayout
     */
    public void onLoadMore(final SmartRefreshLayout refreshLayout) {

        mPullRefreshBean.setLoardMore(mPullRefreshBean, refreshLayout);//加载更多时的处理

//        loadData(refreshLayout);
        hotDataNew(refreshLayout);
    }

    public void setDisableLoadMore(boolean disableLoadMore) {
        mPullRefreshBean.setDisableLoadMore(disableLoadMore);
    }

    public void setDisableRefresh(boolean disableRefresh) {
        mPullRefreshBean.setDisableRefresh(disableRefresh);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    public static IndexYuyinFragment getInstance() {
//    public static RecomFragment getInstance(int tag, RoomTypeResult roomTypeResult) {
        IndexYuyinFragment fragment = new IndexYuyinFragment();
        Bundle bundle = new Bundle();
//        bundle.putInt("id", tag);
//        bundle.putSerializable("categorRoomBean", roomTypeResult);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View getScrollableView() {
        return scrollView;
    }

    private void hotDataNew(SmartRefreshLayout refreshLayout) {
        RxUtils.loading(commonModel.hot_room_three(mPullRefreshBean.pageIndex), this)
                .subscribe(new ErrorHandleSubscriber<HotBean>(mErrorHandler) {
                    @Override
                    public void onNext(HotBean hotBean) {
                        List<HotBean.DataBean> data = hotBean.getData();
                        List<HotBean.DataBean> topData=new ArrayList<>();
                        for (int i = 0; i < (data.size()>=4?4:data.size()); i++) {
                            topData.add(data.get(i));
                        }
                        //设置数据，包括加载更多，下拉刷新，无数据的情况都处理过了
                        new DealRefreshHelper<HotBean.DataBean>().dealDataToUI(refreshLayout, topAdapter, noData, topData, mDataList, mPullRefreshBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<HotBean.DataBean>().dealDataToUI(refreshLayout, topAdapter, noData, new ArrayList<>(), mDataList, mPullRefreshBean);
                    }
                });

        hotDataNew2(refreshLayout);
    }

    private void hotDataNew2(SmartRefreshLayout refreshLayout) {
        RxUtils.loading(commonModel.quanbutop(), this)
                .subscribe(new ErrorHandleSubscriber<HotBean>(mErrorHandler) {
                    @Override
                    public void onNext(HotBean hotBean) {
                        List<HotBean.DataBean> data = hotBean.getData();
                        horizontalAdapter.setNewData(data);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        horizontalAdapter.setNewData(new ArrayList<>());
                    }
                });
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
        if (FANHUIZHUYE.equals(tag)) {//显示悬浮窗
            EnterRoom enterRoom = event.getEnterRoom();
            old_id = String.valueOf(enterRoom.getRoom_info().get(0).getUid());
        } else if (XUANFUYINCANG.equals(tag)) {//显示悬浮窗
            old_id = "";
        }
    }
}
