package com.qutu.talk.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
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
import com.jess.arms.utils.LogUtils;
import com.qutu.talk.R;
import com.qutu.talk.adapter.HomeBoyAdapter;
import com.qutu.talk.adapter.HomeRecommendAdapter;
import com.qutu.talk.adapter.HomeTopAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.HeaderViewPagerFragment;
import com.qutu.talk.bean.CategorRoomBean;
import com.qutu.talk.bean.EnterRoom;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.HotBean;
import com.qutu.talk.bean.PullRefreshBean;
import com.qutu.talk.bean.RecommenRoomBean;
import com.qutu.talk.bean.RoomTypeResult;
import com.qutu.talk.bean.StartLoftBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.DealRefreshHelper;
import com.qutu.talk.view.MyGridView;
import com.qutu.talk.view.MyListView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.qutu.talk.utils.Constant.FANHUIZHUYE;
import static com.qutu.talk.utils.Constant.XUANFUYINCANG;

/**
 * 作者:sgm
 * 描述:首页分类
 */
public class RecomHomeFragment extends HeaderViewPagerFragment {


    //    @BindView(R.id.recyclerView)
//    public XRecyclerView recyclerView;
//    private RecomAdapter adapter;
    @Inject
    CommonModel commonModel;
    @BindView(R.id.myList1)
    RecyclerView myList1;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.no_data)
    LinearLayout noData;
    //    @BindView(R.id.myList2)
//    MyListView myList2;
//    @BindView(R.id.myList3)
//    MyListView myList3;
    //    @BindView(R.id.refreshLayout)
//    SmartRefreshLayout refreshLayout;
//    @BindView(R.id.myGrid)
//    MyGridView myGrid;
    Unbinder unbinder;
//    @BindView(R.id.textGirl)
//    TextView textGirl;
//    @BindView(R.id.textBoy)
//    TextView textBoy;
//    @BindView(R.id.ll1)
//    LinearLayout ll1;
//    @BindView(R.id.ll2)
//    LinearLayout ll2;
//    @BindView(R.id.ll3)
//    LinearLayout ll3;
//    @BindView(R.id.imgFresh)
//    ImageView imgFresh;

    private int id;
    private HomeTopAdapter homeTopAdapter;
//    private HomeRecommendAdapter homeRecommendAdapter;
//    private HomeRecommendAdapter homeRecommendAdapter3;
//    private HomeBoyAdapter homeBoyAdapter;

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

    //    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        if(mRootView == null){
//            mRootView = view;
//        }
//        super.onViewCreated(view, savedInstanceState);
//        isPrepared = true;
//        onVisible();
//    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
//        textGirl.setSelected(true);
        id = getArguments().getInt("id");
        roomTypeResult = (RoomTypeResult) getArguments().getSerializable("categorRoomBean");

        mPullRefreshBean.setDisableLoadMore(true);

//        if (id == 0) {
//            refreshLayout.setEnableLoadmore(false);
//        } else {
//            refreshLayout.setEnableLoadmore(true);
//            refreshLayout.setOnLoadmoreListener(refreshlayout -> {
//                page++;
//                loadData();
//            });
//        }
//        refreshLayout.setOnRefreshListener(refreshlayout -> {
//            page = 1;
//            if (id == 0) {
//                loadRecommendData();
//            } else {
//                loadData();
//            }
//        });

    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//            isVisible = true;
//            onVisible();
//        } else {
//            isVisible = false;
////            onInvisible();
//        }
//    }

    /**
     * 可见做懒加载
     */
//    private void onVisible() {
//        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
//        if (isVisible && isPrepared) {
//            visibleToloadData();
//            //数据加载完毕,恢复标记,防止重复加载
//            isVisible = false;
//            isPrepared = false;
//        }
//    }
    protected void visibleToloadData() {
        homeTopAdapter = new HomeTopAdapter(R.layout.item_recom_two, mDataList);
        myList1.setLayoutManager(new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false));
        myList1.setAdapter(homeTopAdapter);
//        homeRecommendAdapter = new HomeRecommendAdapter(getActivity());
//        mDataList = homeRecommendAdapter.getList_adapter();
//        myList2.setAdapter(homeRecommendAdapter);
//        homeRecommendAdapter3 = new HomeRecommendAdapter(getActivity());
//        myList3.setAdapter(homeRecommendAdapter3);
//        homeBoyAdapter = new HomeBoyAdapter(getActivity(), roomTypeResult);
//        myGrid.setAdapter(homeBoyAdapter);
//        if (id == 0) {
//            ll1.setVisibility(View.VISIBLE);
//            ll2.setVisibility(View.VISIBLE);
//            ll3.setVisibility(View.VISIBLE);
//            loadRecommendData(null);
//            loadGirlData();
//        } else {
//            ll1.setVisibility(View.GONE);
//            ll2.setVisibility(View.GONE);
//            ll3.setVisibility(View.GONE);
        hotData(mSmartRefreshLayout);
//        }
        homeTopAdapter.setOnItemClickListener((adapter, view, position) -> {
            enterData(String.valueOf(homeTopAdapter.getData().get(position).getUid()), "", commonModel, 1, homeTopAdapter.getData().get(position).getRoom_cover());
        });
//        myList1.setOnItemClickListener((parent, view, position, id) -> {
//            enterData(String.valueOf(homeTopAdapter.getList_adapter().get(position).getUid()), "", commonModel, 1, homeTopAdapter.getList_adapter().get(position).getRoom_cover());
//        });
//        myList2.setOnItemClickListener((parent, view, position, id) -> {
//            enterData(homeRecommendAdapter.getList_adapter().get(position).getUid(), "", commonModel, 1, homeRecommendAdapter.getList_adapter().get(position).getRoom_cover());
//        });
//        myList3.setOnItemClickListener((parent, view, position, id) -> {
//            enterData(homeRecommendAdapter3.getList_adapter().get(position).getUid(), "", commonModel, 1, homeRecommendAdapter3.getList_adapter().get(position).getRoom_cover());
//        });
//        myGrid.setOnItemClickListener((parent, view, position, id) -> {
//            enterData(String.valueOf(homeBoyAdapter.getList_adapter().get(position).getUid()), "", commonModel, 1, homeBoyAdapter.getList_adapter().get(position).getRoom_cover());
//        });

//        loadRecommentData();
    }

    /**
     * 热门数据
     */
    private void hotData(SmartRefreshLayout refreshLayout) {
        RxUtils.loading(commonModel.hot_room_three(mPullRefreshBean.pageIndex), this)
                .subscribe(new ErrorHandleSubscriber<HotBean>(mErrorHandler) {
                    @Override
                    public void onNext(HotBean hotBean) {
                        LogUtils.debugInfo("====进来了", hotBean.getData().size() + "");
                        List<HotBean.DataBean> data = hotBean.getData();
                        //设置数据，包括加载更多，下拉刷新，无数据的情况都处理过了
                        new DealRefreshHelper<HotBean.DataBean>().dealDataToUI(refreshLayout, homeTopAdapter, noData, data, mDataList, mPullRefreshBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<HotBean.DataBean>().dealDataToUI(refreshLayout, homeTopAdapter, noData, new ArrayList<>(), mDataList, mPullRefreshBean);
                    }
                });
    }

    /**
     * 头部数据
     */
//    private void loadData(SmartRefreshLayout refreshLayout) {
//        RxUtils.loading(commonModel.getrecommendroom(id, mPullRefreshBean.pageIndex), this)
//                .subscribe(new ErrorHandleSubscriber<RecommenRoomBean>(mErrorHandler) {
//                    @Override
//                    public void onNext(RecommenRoomBean categorRoomBean) {
//
//                        List<RecommenRoomBean.DataBean> list = categorRoomBean.getData();
//
//                        //设置数据，包括加载更多，下拉刷新，无数据的情况都处理过了
//                        new DealRefreshHelper<RecommenRoomBean.DataBean>().dealDataToUI(refreshLayout, homeRecommendAdapter, null, list, mDataList, mPullRefreshBean);
//
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        super.onError(t);
//                        //设置数据，包括加载更多，下拉刷新，无数据的情况都处理过了
//                        new DealRefreshHelper<RecommenRoomBean.DataBean>().dealDataToUI(refreshLayout, homeRecommendAdapter, null, new ArrayList<>(), mDataList, mPullRefreshBean);
//                    }
//                });
//    }

    /**
     * 推荐的数据单独走接口
     */
//    private void loadRecommendData(SmartRefreshLayout refreshLayout) {
//        RxUtils.loading(commonModel.is_top(""), this)
//                .subscribe(new ErrorHandleSubscriber<RecommenRoomBean>(mErrorHandler) {
//                    @Override
//                    public void onNext(RecommenRoomBean categorRoomBean) {
//                        homeTopAdapter.getList_adapter().clear();
//                        homeTopAdapter.getList_adapter().addAll(categorRoomBean.getData());
//                        homeTopAdapter.notifyDataSetChanged();
//                        if (refreshLayout != null) {
//                            refreshLayout.finishRefresh();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        super.onError(t);
//                        if (refreshLayout != null) {
//                            refreshLayout.finishRefresh();
//                        }
//                    }
//                });
//
//        RxUtils.loading(commonModel.hot_room_three(mPullRefreshBean.pageIndex),this)
//                .subscribe(new ErrorHandleSubscriber<HotBean>(mErrorHandler) {
//                    @Override
//                    public void onNext(HotBean hotBean) {

//                    }
//                });
//    }

    /**
     * 热门，房间
     */
//    private void loadRecommentData() {
//        //热门推荐
//        RxUtils.loading(commonModel.is_popular(), this)
//                .subscribe(new ErrorHandleSubscriber<RecommenRoomBean>(mErrorHandler) {
//                    @Override
//                    public void onNext(RecommenRoomBean categorRoomBean) {
//                        setPopularData(categorRoomBean);
//                    }
//                });
//        //密聊推荐
//        RxUtils.loading(commonModel.secret_chat(), this)
//                .subscribe(new ErrorHandleSubscriber<RecommenRoomBean>(mErrorHandler) {
//                    @Override
//                    public void onNext(RecommenRoomBean categorRoomBean) {
//                        setSecretData(categorRoomBean);
//                    }
//                });
//    }

    /**
     * 下拉刷新
     *
     * @param refreshLayout
     */
    public void onRefresh(final SmartRefreshLayout refreshLayout) {

        mPullRefreshBean.setRefresh(mPullRefreshBean, refreshLayout);//下拉刷新时的处理
        hotData(refreshLayout);
        if (id == 0) {
//            loadRecommendData(refreshLayout);
//            loadRecommentData();
        } else {
//            loadData(refreshLayout);
        }

    }

    /**
     * 上拉加载
     *
     * @param refreshLayout
     */
    public void onLoadMore(final SmartRefreshLayout refreshLayout) {

        mPullRefreshBean.setLoardMore(mPullRefreshBean, refreshLayout);//加载更多时的处理

//        loadData(refreshLayout);
        hotData(refreshLayout);
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

    public static RecomHomeFragment getInstance(int tag, RoomTypeResult roomTypeResult) {
        RecomHomeFragment fragment = new RecomHomeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", tag);
        bundle.putSerializable("categorRoomBean", roomTypeResult);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setRefersh(SmartRefreshLayout refershLayout) {
        this.mSmartRefreshLayout = refershLayout;
    }

    @Override
    public View getScrollableView() {
        return scrollView;
    }

    //推荐
//    public void setPopularData(RecommenRoomBean categorRoomBean) {
//        if (homeRecommendAdapter != null) {
//            homeRecommendAdapter.getList_adapter().clear();
//            homeRecommendAdapter.getList_adapter().addAll(categorRoomBean.getData());
//            homeRecommendAdapter.notifyDataSetChanged();
//        } else {
//            Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    if (homeRecommendAdapter != null) {
//                        homeRecommendAdapter.getList_adapter().clear();
//                        homeRecommendAdapter.getList_adapter().addAll(categorRoomBean.getData());
//                        homeRecommendAdapter.notifyDataSetChanged();
//                    }
//                }
//            }, 1000);
//        }
//    }

    //密聊
    /**
     public void setSecretData(RecommenRoomBean categorRoomBean) {
     if (homeRecommendAdapter3 != null) {
     homeRecommendAdapter3.getList_adapter().clear();
     homeRecommendAdapter3.getList_adapter().addAll(categorRoomBean.getData());
     homeRecommendAdapter3.notifyDataSetChanged();
     } else {
     Handler handler = new Handler();
     handler.postDelayed(new Runnable() {
    @Override public void run() {
    if (homeRecommendAdapter3 != null) {
    homeRecommendAdapter3.getList_adapter().clear();
    homeRecommendAdapter3.getList_adapter().addAll(categorRoomBean.getData());
    homeRecommendAdapter3.notifyDataSetChanged();
    }
    }
    }, 1000);
     }
     }
     **/

    /**
     * public void loadBoyData() {
     * //男生数据
     * RxUtils.loading(commonModel.star_loft(1), this)
     * .subscribe(new ErrorHandleSubscriber<StartLoftBean>(mErrorHandler) {
     *
     * @Override public void onNext(StartLoftBean categorRoomBean) {
     * homeBoyAdapter.getList_adapter().clear();
     * homeBoyAdapter.getList_adapter().addAll(categorRoomBean.getData());
     * homeBoyAdapter.notifyDataSetChanged();
     * }
     * });
     * }
     * <p>
     * public void loadGirlData() {
     * //女生数据
     * RxUtils.loading(commonModel.star_loft(2), this)
     * .subscribe(new ErrorHandleSubscriber<StartLoftBean>(mErrorHandler) {
     * @Override public void onNext(StartLoftBean categorRoomBean) {
     * homeBoyAdapter.getList_adapter().clear();
     * homeBoyAdapter.getList_adapter().addAll(categorRoomBean.getData());
     * homeBoyAdapter.notifyDataSetChanged();
     * }
     * });
     * }
     * @OnClick({R.id.textGirl, R.id.textBoy, R.id.imgFresh})
     * public void onViewClicked(View view) {
     * switch (view.getId()) {
     * case R.id.textGirl:
     * textBoy.setSelected(false);
     * textGirl.setSelected(true);
     * loadGirlData();
     * break;
     * case R.id.textBoy:
     * textBoy.setSelected(true);
     * textGirl.setSelected(false);
     * loadBoyData();
     * break;
     * case R.id.imgFresh:
     * if (textBoy.isSelected()) {
     * loadBoyData();
     * } else {
     * loadGirlData();
     * }
     * break;
     * default:
     * }
     * }
     **/
    public void setCategory(CategorRoomBean categorRoomBean) {

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
