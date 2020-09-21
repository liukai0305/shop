package com.qutu.talk.activity.room;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.LogUtils;
import com.qutu.talk.R;
import com.qutu.talk.adapter.RoomRankAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.app.view.CircularImage;
import com.qutu.talk.base.BaseWebActivity;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.RoomRankBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.MyUtil;
import com.qutu.talk.utils.StatusBarUtils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class RoomRank2Activity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;

    @BindView(R.id.room_rank_bj_two)
    ImageView roomRankBjTwo;
    @BindView(R.id.imgBack_two)
    ImageView imgBackTwo;
    @BindView(R.id.tab_layout_two)
    TextView tabLayoutTwo;
    @BindView(R.id.shuo_ming_two)
    ImageView shuoMingTwo;
    @BindView(R.id.room_rank_tit_two)
    LinearLayout roomRankTitTwo;
    @BindView(R.id.room_rank_ri_two)
    TextView roomRankRiTwo;
    @BindView(R.id.room_rank_zhou_two)
    TextView roomRankZhouTwo;
    @BindView(R.id.er_biao)
    LinearLayout erBiao;
    @BindView(R.id.img2)
    CircularImage img2;
    @BindView(R.id.head_image_kuang2)
    ImageView headImageKuang2;
    @BindView(R.id.tou2)
    ConstraintLayout tou2;
    @BindView(R.id.room_rank_name2)
    TextView roomRankName2;
    @BindView(R.id.room_rank_id2)
    TextView roomRankId2;
    @BindView(R.id.room_rank_zuan_tit2)
    TextView roomRankZuanTit2;
    @BindView(R.id.room_rank_zuan2)
    TextView roomRankZuan2;
    @BindView(R.id.two)
    RelativeLayout two;
    @BindView(R.id.img1)
    CircularImage img1;
    @BindView(R.id.head_image_kuang)
    ImageView headImageKuang;
    @BindView(R.id.tou1)
    ConstraintLayout tou1;
    @BindView(R.id.room_rank_name1)
    TextView roomRankName1;
    @BindView(R.id.room_rank_id1)
    TextView roomRankId1;
    @BindView(R.id.room_rank_zuan_tit1)
    TextView roomRankZuanTit1;
    @BindView(R.id.room_rank_zuan1)
    TextView roomRankZuan1;
    @BindView(R.id.one)
    RelativeLayout one;
    @BindView(R.id.img3)
    CircularImage img3;
    @BindView(R.id.head_image_kuang3)
    ImageView headImageKuang3;
    @BindView(R.id.tou3)
    ConstraintLayout tou3;
    @BindView(R.id.room_rank_name3)
    TextView roomRankName3;
    @BindView(R.id.room_rank_id3)
    TextView roomRankId3;
    @BindView(R.id.room_rank_zuan_tit3)
    TextView roomRankZuanTit3;
    @BindView(R.id.room_rank_zuan3)
    TextView roomRankZuan3;
    @BindView(R.id.three)
    RelativeLayout three;
    @BindView(R.id.top)
    LinearLayout top;
    @BindView(R.id.no_data)
    LinearLayout noData;
    @BindView(R.id.room_rank_rv)
    RecyclerView roomRankRv;

    private String mUid; //房间ID
    private String mType = "1"; //1日榜2周榜
    private int type;
    private RoomRankAdapter mAdapter;
    private int statusBarHeight; //导航栏的高度
    private String mUrl; //房间背景

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
        return R.layout.activity_room_rank2;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        statusBarHeight = StatusBarUtils.getStatusBarHeight(this);
        MyUtil.setMargins(roomRankTitTwo, 0, statusBarHeight, 0, 0);
        QMUIStatusBarHelper.translucent(this);

        mUid = getIntent().getStringExtra("uid");
        LogUtils.debugInfo("====房间ID", mUid + "有木有");
        type = getIntent().getIntExtra("type", 1);
        mUrl = getIntent().getStringExtra("url");

        //设置日周月榜单的默认
        roomRankRiTwo.setSelected(true);
        roomRankZhouTwo.setSelected(false);

        loadImage(roomRankBjTwo, mUrl, 0);

        //设置适配器
        mAdapter = new RoomRankAdapter(type);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        roomRankRv.setLayoutManager(llm);
        roomRankRv.setAdapter(mAdapter);

        loadData(mType);
    }

    @Override
    public void onResume() {
        super.onResume();
        findViewById(R.id.imgBack_two).setOnClickListener(v -> {
            startActivity(new Intent(this, AdminHomeActivity.class));
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, AdminHomeActivity.class));
        finish();
    }

    //获取数据
    private void loadData(String type) {
        showDialogLoding();
        RxUtils.loading(commonModel.room_ranking(mUid, "2", type), this)
                .subscribe(new ErrorHandleSubscriber<RoomRankBean>(mErrorHandler) {
                    @Override
                    public void onNext(RoomRankBean roomRankBean) {
                        disDialogLoding();
                        if (roomRankBean.getData().getOther().size() != 0) {
                            roomRankRv.setVisibility(View.VISIBLE);
                            noData.setVisibility(View.GONE);
                            mAdapter.setNewData(roomRankBean.getData().getOther());
                        } else {
                            roomRankRv.setVisibility(View.GONE);
                            noData.setVisibility(View.VISIBLE);
                        }

                        setTop(roomRankBean.getData().getTop());
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        disDialogLoding();
                    }
                });
    }

    //设置前三的数据
    private void setTop(List<RoomRankBean.DataBean.TopBean> topBeanList) {

        if ("".equals(topBeanList.get(0).getName())) {
            roomRankName1.setText("虚位以待");
            roomRankZuanTit1.setText("");
            loadImage(img1, topBeanList.get(0).getImg(), R.mipmap.no_tou);
            roomRankId1.setText("");
            roomRankZuan1.setText("");
        } else {
            roomRankName1.setText(topBeanList.get(0).getName());
            roomRankZuanTit1.setText("金币:");
            loadImage(img1, topBeanList.get(0).getImg(), 0);
            roomRankId1.setText("ID:" + topBeanList.get(0).getId());
            roomRankZuan1.setText(String.valueOf(topBeanList.get(0).getMizuan()));

            roomRankZuan1.setTextColor(getResources().getColor(R.color.font_ff3e6d));

        }

        if ("".equals(topBeanList.get(1).getName())) {
            roomRankName2.setText("虚位以待");
            roomRankZuanTit2.setText("");
            loadImage(img2, topBeanList.get(1).getImg(), R.mipmap.no_tou);
            roomRankId2.setText("");
            roomRankZuan2.setText("");
        } else {
            roomRankName2.setText(topBeanList.get(1).getName());
            roomRankZuanTit2.setText("金币:");
            loadImage(img2, topBeanList.get(1).getImg(), 0);
            roomRankId2.setText("ID:" + topBeanList.get(1).getId());
            roomRankZuan2.setText(String.valueOf(topBeanList.get(1).getMizuan()));

            roomRankZuan2.setTextColor(getResources().getColor(R.color.font_ff3e6d));
        }

        if ("".equals(topBeanList.get(2).getName())) {
            roomRankName3.setText("虚位以待");
            roomRankZuanTit3.setText("");
            loadImage(img3, topBeanList.get(2).getImg(), R.mipmap.no_tou);
            roomRankId3.setText("");
            roomRankZuan3.setText("");
        } else {
            roomRankName3.setText(topBeanList.get(2).getName());
            roomRankZuanTit3.setText("金币:");
            loadImage(img3, topBeanList.get(2).getImg(), 0);
            roomRankId3.setText("ID:" + topBeanList.get(2).getId());
            roomRankZuan3.setText(String.valueOf(topBeanList.get(2).getMizuan()));
            roomRankZuan3.setTextColor(getResources().getColor(R.color.font_ff3e6d));

        }
    }

    @OnClick({R.id.imgBack_two, R.id.shuo_ming_two, R.id.room_rank_ri_two, R.id.room_rank_zhou_two})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack_two:
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
                finish();
                break;
            case R.id.shuo_ming_two:
                Intent intent = new Intent(RoomRank2Activity.this, RankExplainActivity.class);
                intent.putExtra("type","1");
                startActivity(intent);
                break;
            case R.id.room_rank_ri_two:
                roomRankRiTwo.setSelected(true);
                roomRankZhouTwo.setSelected(false);
                mType = "1";
                loadData(mType);
                break;
            case R.id.room_rank_zhou_two:
                roomRankZhouTwo.setSelected(true);
                roomRankRiTwo.setSelected(false);
                mType = "2";
                loadData(mType);
                break;
        }
    }
}
