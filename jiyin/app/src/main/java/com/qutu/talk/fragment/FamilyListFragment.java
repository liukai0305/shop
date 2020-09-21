package com.qutu.talk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.activity.family.CreateFamilyFirstActivity;
import com.qutu.talk.activity.family.FamilyDetailsActivity;
import com.qutu.talk.adapter.FamilyListAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.BaseWebActivity;
import com.qutu.talk.base.MyBaseArmFragment;
import com.qutu.talk.bean.FamilyItem;
import com.qutu.talk.bean.FamilyListResult;
import com.qutu.talk.bean.IntroResult;
import com.qutu.talk.bean.PullRefreshBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.DealRefreshHelper;
import com.qutu.talk.utils.ToastUtil;
import com.qutu.talk.view.ShapeTextView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class FamilyListFragment extends MyBaseArmFragment implements FamilyListAdapter.OnButtonClickListener {

    @Inject
    CommonModel commonModel;
    Unbinder unbinder;

    PullRefreshBean mPullRefreshBean = new PullRefreshBean();

    List<FamilyItem> mDataList = new ArrayList<>();
    FamilyListAdapter mAdapter = null;

    String mIntroUrl = "";
    @BindView(R.id.toolbar_back)
    RelativeLayout toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.rightTitle)
    TextView rightTitle;
    @BindView(R.id.tv_help_history)
    TextView tvHelpHistory;
    @BindView(R.id.rightConfirm)
    ShapeTextView rightConfirm;
    @BindView(R.id.tv_bar_right)
    TextView tvBarRight;
    @BindView(R.id.img_bar_right)
    ImageView imgBarRight;
    @BindView(R.id.toolbar_right)
    RelativeLayout toolbarRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ly_view_no_data)
    LinearLayout lyViewNoData;
    @BindView(R.id.rlv_family)
    RecyclerView rlvFamily;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_create)
    TextView tvCreate;

    @Override
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = null;
        if (mRootView == null) {
            mRootView = ArmsUtils.inflate(getActivity(), R.layout.fragment_family_list);
            unbinder = ButterKnife.bind(this, mRootView);
        }
        return mRootView;
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
        toolbarTitle.setText("家族列表");
        toolbarTitle.setVisibility(View.VISIBLE);
        if(getArguments().getBoolean("show_back",false)){
            toolbarBack.setVisibility(View.VISIBLE);
        }else{
            toolbarBack.setVisibility(View.GONE);
        }
        imgBarRight.setImageResource(R.mipmap.cp_help);

        imgBarRight.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(mIntroUrl)) {
                Intent intent = new Intent(getActivity(), BaseWebActivity.class);
                intent.putExtra("url", mIntroUrl);
                intent.putExtra("title", "家族介绍");
                startActivity(intent);
            }
        });

        int is_family_show = getArguments().getInt("is_family_show", 0);

        //String isGod = getArguments().getString("is_god");

        if (is_family_show == 1) {
            tvCreate.setVisibility(View.VISIBLE);
        } else {
            tvCreate.setVisibility(View.GONE);
        }

        tvCreate.setOnClickListener(v -> {
            ArmsUtils.startActivity(CreateFamilyFirstActivity.class);  //创建家族
        });
        initRecyclerView();

        initRefreshLayout();

        getDataFromServer();

        getIntro();
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    private void initRefreshLayout() {

        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout1) {
                mPullRefreshBean.setLoardMore(mPullRefreshBean, refreshLayout);

                getDataFromServer();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout1) {
                mPullRefreshBean.setRefresh(mPullRefreshBean, refreshLayout);

                getDataFromServer();
            }
        });

    }

    private void initRecyclerView() {

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());

        manager.setOrientation(RecyclerView.VERTICAL);

        rlvFamily.setHasFixedSize(true);

        rlvFamily.setLayoutManager(manager);

        mAdapter = new FamilyListAdapter(getActivity(), R.layout.item_family, mDataList);

        mAdapter.setOnClickListener(this);

        rlvFamily.setHasFixedSize(true);

        rlvFamily.setLayoutManager(manager);

//        mAdapter!!.setHasStableIds(true)//防止数据错乱

        rlvFamily.setAdapter(mAdapter);

    }

    private void getIntro(){
        RxUtils.loading(commonModel.family_introduce())
                .subscribe(new ErrorHandleSubscriber<IntroResult>(mErrorHandler) {
                    @Override
                    public void onNext(IntroResult introResult) {
                        if(introResult != null && introResult.getData() != null){
                            mIntroUrl = introResult.getData().getUrl();
                        }
                    }
                });
    }

    //获取数据
    private void getDataFromServer() {

        RxUtils.loading(commonModel.getFamilyList(mPullRefreshBean.pageIndex+""))
                .subscribe(new ErrorHandleSubscriber<FamilyListResult>(mErrorHandler) {
                    @Override
                    public void onNext(FamilyListResult familyListResult) {
                        if (familyListResult != null && familyListResult.getData() != null) {

                            List<FamilyItem> list= familyListResult.getData();

                            new DealRefreshHelper<FamilyItem> ().dealDataToUI(refreshLayout, mAdapter, lyViewNoData, list, mDataList, mPullRefreshBean);

                        } else {
                            new DealRefreshHelper<FamilyItem>().dealDataToUI(refreshLayout, mAdapter, lyViewNoData, new ArrayList<>(), mDataList, mPullRefreshBean);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<FamilyItem>().dealDataToUI(refreshLayout, mAdapter, lyViewNoData, new ArrayList<>(), mDataList, mPullRefreshBean);
                    }
                });

    }

    @Override
    public void itemClick(@NotNull FamilyItem item) {
        Intent intent = new Intent(getActivity(), FamilyDetailsActivity.class);
        intent.putExtra("family_id", item.getFamily_id());
        startActivity(intent);
    }
}
