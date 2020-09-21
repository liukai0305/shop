package com.qutu.talk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.scwang.smartrefresh.layout.util.SmartUtil;
import com.qutu.talk.Interface.MyPackBaoShiInter;
import com.qutu.talk.R;
import com.qutu.talk.activity.family.FamilyDetailsActivity;
import com.qutu.talk.activity.room.GxRankActivity;
import com.qutu.talk.adapter.FamilyRankAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.app.view.CircularImage;
import com.qutu.talk.base.HeaderViewPagerFragment;
import com.qutu.talk.bean.FamilyRank;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.MyUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 作者:sgm
 * 描述:排行榜
 */
public class FamilyRankFragment extends HeaderViewPagerFragment {

    @BindView(R.id.textRi)
    TextView textRi;
    @BindView(R.id.textZhou)
    TextView textZhou;
//    @BindView(R.id.textYue)
//    TextView textYue;
    @BindView(R.id.myList)
    public RecyclerView myList;
    @BindView(R.id.ohuo)
    LinearLayout ohuo;
    @BindView(R.id.num)
    TextView num;
    @BindView(R.id.ci_head)
    CircularImage ciHead;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.textNum)
    TextView textNum;
    @BindView(R.id.bottom)
    LinearLayout bottom;
    @BindView(R.id.my_one)
    ImageView myOne;
    @BindView(R.id.my_two)
    ImageView myTwo;
    @BindView(R.id.me_cf_tit)
    TextView meCfTit;
    @BindView(R.id.tv_indicator_0)
    TextView tvIndicator0;
    @BindView(R.id.tv_indicator_1)
    TextView tvIndicator1;

    private int  statusBarHeight;
    public int shengHeight;
    private String date = "1";
    @Inject
    CommonModel commonModel;

    private CircularImage img1, img2, img3;
    private TextView textName1, textName2, textName3;
    private TextView tvFamily1, tvFamily2, tvFamily3;
    private TextView textDec1, textDec2, textDec3;
    private TextView tvId1, tvId2, tvId3;
    private TextView ti1, ti2, ti3;
//    private View TMView;

    private int color = 0;
    private int h = SmartUtil.dp2px(48);
    private int lastScrollY = 0;
    private int aa = 0;

    public FamilyRankAdapter rankAdapter;
    private View headView;
    private MyPackBaoShiInter.onRankInter mRankInter;
    private List<FamilyRank.DataBean.Item> mList;

    public int mTitleLayoutHeight = 0;

    public void setmOnPageChangeLister(MyPackBaoShiInter.onRankInter lister) {
        this.mRankInter = lister;

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
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_rank);
    }


    @Override
    public void setData(@Nullable Object data) {

    }

    public void setItemBg(boolean isTrans) {
        LogUtils.debugInfo("方法进来了====");
        if (rankAdapter != null && rankAdapter.getData().size() > 0) {
            rankAdapter.getData().get(0).setBg(isTrans);
            rankAdapter.notifyDataSetChanged();
        }
    }

    public static FamilyRankFragment getInstance( int stateBarHeight) {
        FamilyRankFragment fragment = new FamilyRankFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("statusBarHeight", stateBarHeight);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        statusBarHeight = getArguments().getInt("statusBarHeight");
//        TMView = getActivity().findViewById(R.id.view_touming);

        color = ContextCompat.getColor(getActivity(), R.color.color_daa1e1) & 0x00ffffff;

//        textYue.setSelected(false);

        rankAdapter = new FamilyRankAdapter();
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myList.setLayoutManager(llm);
        myList.setAdapter(rankAdapter);

        loadData(date);
        headView = ArmsUtils.inflate(mContext, R.layout.rank_head);


        img1 = headView.findViewById(R.id.img1);
        img2 = headView.findViewById(R.id.img2);
        img3 = headView.findViewById(R.id.img3);
        ti1 = headView.findViewById(R.id.room_rank_zuan_tit1);
        ti2 = headView.findViewById(R.id.room_rank_zuan_tit2);
        ti3 = headView.findViewById(R.id.room_rank_zuan_tit3);
        textName1 = headView.findViewById(R.id.textName1);
        textName2 = headView.findViewById(R.id.textName2);
        textName3 = headView.findViewById(R.id.textName3);
        textDec1 = headView.findViewById(R.id.textDec1);
        textDec2 = headView.findViewById(R.id.textDec2);
        textDec3 = headView.findViewById(R.id.textDec3);
        tvFamily1 = headView.findViewById(R.id.tv_family_1);
        tvFamily2 = headView.findViewById(R.id.tv_family_2);
        tvFamily3 = headView.findViewById(R.id.tv_family_3);
        tvId1 = headView.findViewById(R.id.tv_id1);
        tvId2 = headView.findViewById(R.id.tv_id2);
        tvId3 = headView.findViewById(R.id.tv_id3);
        View rcvHeadView = headView.findViewById(R.id.rcv_rank_head);
        //点击第一的头像跳转个人主页
        img1.setOnClickListener(v -> {
            if (!mList.get(0).getJzname().equals("")) {
                Intent intent = new Intent(getContext(), FamilyDetailsActivity.class);
                intent.putExtra("family_id", mList.get(0).getJzid());
                ArmsUtils.startActivity(intent);
            }
        });
        //点击第二的头像跳转个人主页
        img2.setOnClickListener(v -> {
            if (!mList.get(1).getJzname().equals("")) {
                Intent intent = new Intent(getContext(), FamilyDetailsActivity.class);
                intent.putExtra("family_id", mList.get(1).getJzid());
                ArmsUtils.startActivity(intent);
            }
        });
        //点击第三的头像跳转个人主页
        img3.setOnClickListener(v -> {
            if (!mList.get(2).getJzname().equals("")) {
                Intent intent = new Intent(getContext(), FamilyDetailsActivity.class);
                intent.putExtra("family_id", mList.get(2).getJzid());
                ArmsUtils.startActivity(intent);
            }
        });

        //点击除了前三的头像跳转个人主页
        rankAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Intent intent = new Intent(getContext(), FamilyDetailsActivity.class);
            intent.putExtra("family_id", rankAdapter.getData().get(position).getJzid());
            ArmsUtils.startActivity(intent);
        });

        myList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int mScrollY = 0;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mScrollY += dy;

                if (mRankInter != null) {
//                    LogUtils.debugInfo("scrollY===" + mScrollY);
                    mRankInter.OnRankInter(mScrollY);
                    int titleY = mScrollY;
                    if (lastScrollY < h) {
                        titleY = Math.min(h, titleY);
                        aa = titleY > h ? h : titleY;
                        ohuo.setBackgroundColor(((255 * aa / h) << 24) | color);
                    }
                    aa = titleY;
                }
            }
        });

        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        ohuo.measure(w, h);
        mTitleLayoutHeight = ohuo.getMeasuredHeight();
        LogUtils.debugInfo("====周月榜高度" + mTitleLayoutHeight);
        GxRankActivity gxRankActivity = (GxRankActivity) getActivity();
        View view = gxRankActivity.viewTouMing;


        MyUtil.setMargins(view, 0, mTitleLayoutHeight, 0, 0);

        shengHeight = MyUtil.dip2px(getActivity(), 418) - statusBarHeight - MyUtil.dip2px(getActivity(), 46) - MyUtil.dip2px(getActivity(), 40) - MyUtil.dip2px(getActivity(), 50);
        LogUtils.debugInfo("====head高" + statusBarHeight);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, shengHeight);
        rcvHeadView.setLayoutParams(params);
    }

    private void loadData(String date) {
        showDialogLoding();
        RxUtils.loading(commonModel.getFamilyRank(date), this)
                .subscribe(new ErrorHandleSubscriber<FamilyRank>(mErrorHandler) {
                    @Override
                    public void onNext(FamilyRank familyRank) {
                        disDialogLoding();
                        rankAdapter.setNewData(familyRank.getData().getOther());
                        mList = familyRank.getData().getTop();
                        setTop(familyRank.getData().getTop());

                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        disDialogLoding();
                    }
                });

    }

    private void setTop(List<FamilyRank.DataBean.Item> list) {
        if (list.size() != 0) {
            int headerCount = rankAdapter.getHeaderLayoutCount();
            if (headerCount > 0) {
                rankAdapter.removeAllHeaderView();
            }
            rankAdapter.addHeaderView(headView);

            if (list.get(0).getJzname().equals("")) {
                textName1.setText("虚位以待");
                textDec1.setText("");
                loadImage(img1, list.get(0).getImg(), R.mipmap.no_tou);
            } else {
                textName1.setText(list.get(0).getJzname());
                loadImage(img1, list.get(0).getImg(), R.mipmap.no_tou);
                textDec1.setText(list.get(0).getExp());
                ti1.setText("贡献值:");
                textDec1.setTextColor(getActivity().getResources().getColor(R.color.color_FFBA1C));
                tvFamily1.setVisibility(View.GONE);
                tvId1.setText("ID:"+list.get(0).getJzid());
            }

            if (list.get(1).getJzname().equals("")) {
                textName2.setText("虚位以待");
                textDec2.setText("");
                loadImage(img2, list.get(1).getImg(), R.mipmap.no_tou);
            } else {
                textName2.setText(list.get(1).getJzname());
                loadImage(img2, list.get(1).getImg(), R.mipmap.no_tou);

                textDec2.setText(list.get(1).getExp());
                ti2.setText("贡献值:");
                textDec2.setTextColor(getActivity().getResources().getColor(R.color.color_FFBA1C));
                tvFamily2.setVisibility(View.GONE);
                tvId2.setText("ID:"+list.get(1).getJzid());
            }
            if (list.get(2).getJzname().equals("")) {
                textName3.setText("虚位以待");
                textDec3.setText("");
                loadImage(img3, list.get(2).getImg(), R.mipmap.no_tou);
            } else {
                textName3.setText(list.get(2).getJzname());
                loadImage(img3, list.get(2).getImg(), R.mipmap.no_tou);
                textDec3.setText(list.get(2).getExp());
                ti3.setText("贡献值:");
                textDec3.setTextColor(getActivity().getResources().getColor(R.color.color_FFBA1C));
                tvFamily3.setVisibility(View.GONE);
                tvId3.setText("ID:"+list.get(2).getJzid());
            }
        } else {
            rankAdapter.removeAllHeaderView();
        }

    }


    @OnClick({R.id.textRi, R.id.textZhou})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textRi:
                tvIndicator0.setVisibility(View.VISIBLE);
                tvIndicator1.setVisibility(View.INVISIBLE);
//                textYue.setSelected(false);
                date = "1";
                loadData(date);
                break;
            case R.id.textZhou:
                tvIndicator0.setVisibility(View.INVISIBLE);
                tvIndicator1.setVisibility(View.VISIBLE);
//                textYue.setSelected(false);
                date = "2";
                loadData(date);
                break;
        }
    }

    @Override
    public View getScrollableView() {
        return myList;
    }
}
