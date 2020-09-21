package com.qutu.talk.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.qutu.talk.R;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.fragment.FamilyMeltingRankFragment;
import com.qutu.talk.fragment.RankFragment;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.StatusBarUtils;
import com.qutu.talk.view.ShapeTextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FamilyMeltingRankActivity extends MyBaseArmActivity {
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
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;
    private FamilyMeltingRankFragment familyMeltingRankFragment;

    private int statusBarHeight;

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
        return R.layout.activity_family_melting_rank;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle("熔炼榜单");
        statusBarHeight = StatusBarUtils.getStatusBarHeight(this);
        familyMeltingRankFragment = FamilyMeltingRankFragment.getInstance(getIntent().getIntExtra("familyId",0),2, statusBarHeight);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.rl_content, familyMeltingRankFragment, "a");
        fragmentTransaction.commit();
    }

}
