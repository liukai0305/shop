package com.qutu.talk.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.jess.arms.di.component.AppComponent;
import com.qutu.talk.R;
import com.qutu.talk.adapter.ProblemHelpAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.BaseWebActivity;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.AgreementBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class ProblemHelpActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.problem_help_recyc)
    RecyclerView problemHelpRecyc;

    private ProblemHelpAdapter mAdapter;

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
        return R.layout.activity_problem_help;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle("问题帮助");

        mAdapter = new ProblemHelpAdapter(this);
        problemHelpRecyc.setLayoutManager(new LinearLayoutManager(this));
        problemHelpRecyc.setAdapter(mAdapter);

        getData();

        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            AgreementBean.DataBean dataBean = mAdapter.getData().get(position);
            Intent intent = new Intent(this, BaseWebActivity.class);
            intent.putExtra("url", dataBean.getUrl());
            intent.putExtra("title", dataBean.getName());
            startActivity(intent);
        });
    }

    //获取数据
    private void getData() {
        RxUtils.loading(commonModel.getAgreement("4"), this)
                .subscribe(new ErrorHandleSubscriber<AgreementBean>(mErrorHandler) {
                    @Override
                    public void onNext(AgreementBean agreementBean) {
                        mAdapter.setNewData(agreementBean.getData());
                    }
                });
    }
}
