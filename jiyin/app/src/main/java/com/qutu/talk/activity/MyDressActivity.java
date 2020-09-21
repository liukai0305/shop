package com.qutu.talk.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.qutu.talk.R;
import com.qutu.talk.adapter.MyTsAdapter;
import com.qutu.talk.adapter.MyZqAdapter;
import com.qutu.talk.adapter.PersonalityTsAdapter;
import com.qutu.talk.adapter.PersonalityZqAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.BaseBean;
import com.qutu.talk.bean.MyDress;
import com.qutu.talk.bean.PersonalityBean;
import com.qutu.talk.bean.UserBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.ToastUtil;
import com.qutu.talk.view.ShapeTextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class MyDressActivity extends MyBaseArmActivity {

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
    @BindView(R.id.tv_tab_0)
    TextView tvTab0;
    @BindView(R.id.tv_indicator_0)
    TextView tvIndicator0;
    @BindView(R.id.ll_tab_0)
    LinearLayout llTab0;
    @BindView(R.id.tv_tab_1)
    TextView tvTab1;
    @BindView(R.id.tv_indicator_1)
    TextView tvIndicator1;
    @BindView(R.id.ll_tab_1)
    LinearLayout llTab1;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private int selectTab = 0;// 0.头饰 1.坐骑

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
        return R.layout.activity_my_dress;
    }

    private MyTsAdapter myTsAdapter = new MyTsAdapter();
    private MyZqAdapter myZqAdapter = new MyZqAdapter();

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        myTsAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            use(1,myTsAdapter.getData().get(position).getTsid());
        });
        myZqAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            use(2,myZqAdapter.getData().get(position).getZqid());
        });
        recyclerView.setLayoutManager(
                new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(myTsAdapter);
        loadList();
    }

    private void initTab() {
        tvTab0.setTextColor(0xff000000);
        tvTab1.setTextColor(0xff000000);
        tvIndicator0.setVisibility(View.INVISIBLE);
        tvIndicator1.setVisibility(View.INVISIBLE);
    }

    @OnClick({R.id.ll_tab_0, R.id.ll_tab_1, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_tab_0:
                initTab();
                tvTab0.setTextColor(0xffee0092);
                tvIndicator0.setVisibility(View.VISIBLE);
                recyclerView.setAdapter(myTsAdapter);
                selectTab = 0;
                break;
            case R.id.ll_tab_1:
                initTab();
                tvTab1.setTextColor(0xffee0092);
                tvIndicator1.setVisibility(View.VISIBLE);
                recyclerView.setAdapter(myZqAdapter);
                selectTab = 1;
                break;
            case R.id.cv_buy:
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    /**
     * 商品列表
     */
    private void loadList() {
        RxUtils.loading(commonModel.getMyDress(String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<MyDress>(mErrorHandler) {
                    @Override
                    public void onNext(MyDress myDress) {
                        myTsAdapter.setNewData(myDress.getData().getTsinfo());
                        myZqAdapter.setNewData(myDress.getData().getZqinfo());
                    }
                });
    }

    /**
     * 使用
     * type 1 头饰 2坐骑
     */
    private void use(int type, int id) {
        RxUtils.loading(commonModel.useDress(
                        String.valueOf(type),
                        String.valueOf(UserManager.getUser().getUserId()),
                        String.valueOf(id)
                ), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        loadList();
                    }
                });
    }

}
