package com.qutu.talk.activity.mine;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jess.arms.di.component.AppComponent;
import com.qutu.talk.R;
import com.qutu.talk.adapter.HelpRechargeHistoryAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.HelpRechargeHistory;
import com.qutu.talk.bean.UserInfo;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;

import javax.inject.Inject;

import butterknife.BindView;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class HelpRechargeHistoryActivity extends MyBaseArmActivity {

    @Inject
    CommonModel commonModel;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private HelpRechargeHistoryAdapter helpRechargeHistoryAdapter;

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
        return R.layout.activity_help_recharge_history;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(linearLayoutManager);
        helpRechargeHistoryAdapter=new HelpRechargeHistoryAdapter();
        recycler.setAdapter(helpRechargeHistoryAdapter);
        RxUtils.loading(commonModel.getHelpRechargeHistories(String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<HelpRechargeHistory>(mErrorHandler) {
                    @Override
                    public void onNext(HelpRechargeHistory helpRechargeHistory) {
                        helpRechargeHistoryAdapter.setNewData(helpRechargeHistory.getData());
                    }
                });
    }
}
