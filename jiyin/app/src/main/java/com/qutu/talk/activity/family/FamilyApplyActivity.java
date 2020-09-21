package com.qutu.talk.activity.family;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jess.arms.di.component.AppComponent;
import com.qutu.talk.R;
import com.qutu.talk.adapter.family.FamilyApplyAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.CommentBean;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.PullRefreshBean;
import com.qutu.talk.bean.family.FamilyApplyBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.DealRefreshHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.qutu.talk.utils.Constant.FAMILYSHENQING;

/**
 * 家族申请
 * 老王
 */

public class FamilyApplyActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.famliy_apply_recyc)
    RecyclerView famliyApplyRecyc;
    @BindView(R.id.family_apply_smart)
    SmartRefreshLayout familyApplySmart;

    private PullRefreshBean mPullRefreshBean = new PullRefreshBean();
    private ArrayList<FamilyApplyBean.DataBean> mDataList = new ArrayList<>();
    private FamilyApplyAdapter mAdapter;

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
        return R.layout.activity_family_apply;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle("家族申请");

        mAdapter = new FamilyApplyAdapter(R.layout.family_apply_item, mDataList, this);
        famliyApplyRecyc.setLayoutManager(new LinearLayoutManager(this));
        famliyApplyRecyc.setAdapter(mAdapter);

        getData();

        familyApplySmart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPullRefreshBean.setLoardMore(mPullRefreshBean, familyApplySmart);
                getData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPullRefreshBean.setRefresh(mPullRefreshBean, familyApplySmart);
                getData();
            }
        });

        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.jujue:
                    actionRefuseFamily(mAdapter.getData().get(position).getFamily_user_id());
                    break;
                case R.id.tongyi:
                    actionAgreeFamily(mAdapter.getData().get(position).getFamily_user_id());
                    break;
            }
        });
    }

    //获取家族申请列表
    private void getData() {
        RxUtils.loading(commonModel.getFamilyApllyList(mPullRefreshBean.pageIndex), this)
                .subscribe(new ErrorHandleSubscriber<FamilyApplyBean>(mErrorHandler) {
                    @Override
                    public void onNext(FamilyApplyBean familyApplyBean) {
                        List<FamilyApplyBean.DataBean> data = familyApplyBean.getData();
                        new DealRefreshHelper<FamilyApplyBean.DataBean>().dealDataToUI(familyApplySmart, mAdapter, null, data, mDataList, mPullRefreshBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<FamilyApplyBean.DataBean>().dealDataToUI(familyApplySmart, mAdapter, null, new ArrayList<>(), mDataList, mPullRefreshBean);
                    }
                });
    }

    //同意加入家族
    private void actionAgreeFamily(String family_id) {
        RxUtils.loading(commonModel.actionAgreeFamily(family_id), this)
                .subscribe(new ErrorHandleSubscriber<CommentBean>(mErrorHandler) {
                    @Override
                    public void onNext(CommentBean commentBean) {
                        mPullRefreshBean.setRefresh(mPullRefreshBean, familyApplySmart);
                        getData();
                    }
                });
    }

    //拒绝加入家族
    private void actionRefuseFamily(String family_id) {
        RxUtils.loading(commonModel.actionRefuseFamily(family_id), this)
                .subscribe(new ErrorHandleSubscriber<CommentBean>(mErrorHandler) {
                    @Override
                    public void onNext(CommentBean commentBean) {
                        mPullRefreshBean.setRefresh(mPullRefreshBean, familyApplySmart);
                        getData();
                    }
                });
    }

    @Override
    public void finish() {
        EventBus.getDefault().post(new FirstEvent("fanhui",FAMILYSHENQING));
        super.finish();
    }
}
