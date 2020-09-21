package com.qutu.talk.activity.room;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.qutu.talk.R;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.RankExplainBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.MyUtil;
import com.qutu.talk.utils.StatusBarUtils;
import com.qutu.talk.view.ShapeTextView;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class RankExplainActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.rank_explain_text)
    TextView rankExplainText;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private String type;
    private int statusBarHeight; //导航栏的高度

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
        return R.layout.activity_rank_explain;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        statusBarHeight = StatusBarUtils.getStatusBarHeight(this);
        MyUtil.setMargins(toolbar, 0, statusBarHeight, 0, 0);
        QMUIStatusBarHelper.translucent(this);

        type = getIntent().getStringExtra("type");
        setTitle("榜单说明");
        if ("1".equals(type)) {
            loadData("room_ranking_details");
        }
    }

    private void loadData(String name) {
        RxUtils.loading(commonModel.get_shuoming(name), this)
                .subscribe(new ErrorHandleSubscriber<RankExplainBean>(mErrorHandler) {
                    @Override
                    public void onNext(RankExplainBean rankExplainBean) {

                        rankExplainText.setText(Html.fromHtml(rankExplainBean.getData().getValue()));
                    }
                });
    }
}
