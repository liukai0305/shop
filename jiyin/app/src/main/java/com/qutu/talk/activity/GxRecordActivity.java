package com.qutu.talk.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.qutu.talk.R;
import com.qutu.talk.adapter.GxRecordAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.BxRecord;
import com.qutu.talk.bean.PullRefreshBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.DealRefreshHelper;
import com.qutu.talk.view.ShapeTextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class GxRecordActivity extends MyBaseArmActivity {

    @Inject
    CommonModel commonModel;
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
    View lyViewNoData;
    @BindView(R.id.rlv_family)
    RecyclerView rlvFamily;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    private String familyId = "";
    private String type = "1";//贡献类型 1普通 2快速
    private int lucky_type=0;//0 贡献记录 1:好运榜

    private PullRefreshBean pullRefreshBean = new PullRefreshBean();
    private List<BxRecord.DataBean> list = new ArrayList<>();
    private GxRecordAdapter gxRecordAdapter=new GxRecordAdapter(R.layout.item_gx_record,list);

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
        return R.layout.activity_gx_record;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        familyId = getIntent().getStringExtra("familyId");
        type = getIntent().getStringExtra("type");
        lucky_type = getIntent().getIntExtra("lucky_type",0);
        initRefreshLayout();
        initRecyclerView();
        if(lucky_type==0){
            setTitle("贡献记录");
            getRecordList();
        }else{
            setTitle("好运榜");
            getLuckyRecordList();
        }

    }

    private void initRefreshLayout() {
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pullRefreshBean.setLoardMore(pullRefreshBean, mRefreshLayout);
                if(lucky_type==0){
                    getRecordList();
                }else{
                    getLuckyRecordList();
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pullRefreshBean.setRefresh(pullRefreshBean, mRefreshLayout);
                if(lucky_type==0){
                    getRecordList();
                }else{
                    getLuckyRecordList();
                }
            }
        });

    }

    private void initRecyclerView() {

        LinearLayoutManager manager = new LinearLayoutManager(this);

        manager.setOrientation(RecyclerView.VERTICAL);

        rlvFamily.setHasFixedSize(true);

        rlvFamily.setLayoutManager(manager);

        rlvFamily.setHasFixedSize(true);

        rlvFamily.setLayoutManager(manager);

//        mAdapter!!.setHasStableIds(true)//防止数据错乱

        rlvFamily.setAdapter(gxRecordAdapter);

    }

    //获取数据
    private void getRecordList() {

        RxUtils.loading(commonModel.getBxRecord(type, pullRefreshBean.pageIndex + "", familyId))
                .subscribe(new ErrorHandleSubscriber<BxRecord>(mErrorHandler) {
                    @Override
                    public void onNext(BxRecord bxRecord) {
                        if (bxRecord != null && bxRecord.getData() != null) {
                            new DealRefreshHelper<BxRecord.DataBean>().dealDataToUI(mRefreshLayout,gxRecordAdapter,lyViewNoData,bxRecord.getData(),list,pullRefreshBean);
                        }else{
                            new DealRefreshHelper<BxRecord.DataBean>().dealDataToUI(mRefreshLayout,gxRecordAdapter,lyViewNoData,new ArrayList<>(),list,pullRefreshBean);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<BxRecord.DataBean>().dealDataToUI(mRefreshLayout,gxRecordAdapter,lyViewNoData,new ArrayList<>(),list,pullRefreshBean);
                    }
                });

    }

    //获取数据
    private void getLuckyRecordList() {

        RxUtils.loading(commonModel.getLuckyRecord(pullRefreshBean.pageIndex + "", familyId,type))
                .subscribe(new ErrorHandleSubscriber<BxRecord>(mErrorHandler) {
                    @Override
                    public void onNext(BxRecord bxRecord) {
                        if (bxRecord != null && bxRecord.getData() != null) {
                            new DealRefreshHelper<BxRecord.DataBean>().dealDataToUI(mRefreshLayout,gxRecordAdapter,lyViewNoData,bxRecord.getData(),list,pullRefreshBean);
                        }else{
                            new DealRefreshHelper<BxRecord.DataBean>().dealDataToUI(mRefreshLayout,gxRecordAdapter,lyViewNoData,new ArrayList<>(),list,pullRefreshBean);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<BxRecord.DataBean>().dealDataToUI(mRefreshLayout,gxRecordAdapter,lyViewNoData,new ArrayList<>(),list,pullRefreshBean);
                    }
                });

    }

}
