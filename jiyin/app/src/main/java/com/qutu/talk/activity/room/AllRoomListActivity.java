package com.qutu.talk.activity.room;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.LogUtils;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qutu.talk.R;
import com.qutu.talk.adapter.CollectionRoomListOffAdapter;
import com.qutu.talk.adapter.CollectionRoomListOnAdapter;
import com.qutu.talk.adapter.MyPagerAdapter;
import com.qutu.talk.adapter.dashen.MainHomePageSkillAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.BaseBean;
import com.qutu.talk.bean.CollectionRoomListBean;
import com.qutu.talk.bean.dashen.MainHomePageSkillBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.fragment.RecomHomePageFragment;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.view.MyListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class AllRoomListActivity extends MyBaseArmActivity {

    @Inject
    CommonModel commonModel;
    @BindView(R.id.homepage_game_recyc)
    RecyclerView homepage_game_recyc;


    private MainHomePageSkillAdapter mSkillAdapter;

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
        return R.layout.activity_all_room_list;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        homepage_game_recyc.setLayoutManager(new GridLayoutManager(this,5));
        mSkillAdapter = new MainHomePageSkillAdapter();
        homepage_game_recyc.setAdapter(mSkillAdapter);
        loadSkillList();
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarTitle("", true);
    }

    /**
     * 获取技能列表数据
     */
    private void loadSkillList() {
        RxUtils.loading(commonModel.getAllSkillsLists(), this)
                .subscribe(new ErrorHandleSubscriber<MainHomePageSkillBean>(mErrorHandler) {
                    @Override
                    public void onNext(MainHomePageSkillBean mainHomePageSkillBean) {
                        mSkillAdapter.setNewData(mainHomePageSkillBean.getData());
                    }
                });
    }

}
