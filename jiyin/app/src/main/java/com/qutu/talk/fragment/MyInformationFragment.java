package com.qutu.talk.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
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
import com.qutu.talk.R;
import com.qutu.talk.activity.my.DengJiShuoMingActivity;
import com.qutu.talk.activity.my.MyPersonalCenterActivity;
import com.qutu.talk.activity.my.MyPersonalCenterTwoActivity;
import com.qutu.talk.adapter.CPAdapter;
import com.qutu.talk.adapter.RongYuAdapter;
import com.qutu.talk.base.MyBaseArmFragment;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.MyPersonalCebterBean;
import com.qutu.talk.bean.MyPersonalCebterTwoBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.relex.circleindicator.CircleIndicator;

/**
 * 个人中心中的个人资料页面
 * 老王
 */
public class MyInformationFragment extends MyBaseArmFragment {

    @Inject
    CommonModel commonModel;
    @BindView(R.id.ge_ren_zi_liao)
    LinearLayout geRenZiLiao;
    @BindView(R.id.personal_age)
    TextView personalAge;
    @BindView(R.id.personal_cta)
    TextView personalCta;
    @BindView(R.id.personal_address)
    TextView personalAddress;
    @BindView(R.id.personal_id)
    TextView personalId;
    @BindView(R.id.rongyu)
    LinearLayout rongyu;
    @BindView(R.id.no_rongyu)
    LinearLayout noRongyu;
    @BindView(R.id.rongyu_recyc)
    RecyclerView rongyuRecyc;
    @BindView(R.id.juti_rongyu)
    CardView jutiRongyu;
    @BindView(R.id.cp_help)
    ImageView cpHelp;
    @BindView(R.id.cp)
    LinearLayout cp;
    @BindView(R.id.cp_viewpager)
    ViewPager cpViewpager;
    @BindView(R.id.vp_magicindicator)
    CircleIndicator vpMagicindicator;

    private RongYuAdapter rongYuAdapter;

    private List<Fragment> viewpagerFragment;
    private CPAdapter mPagerAdapter;
    private CPAFragment cpaFragment;
    private CPBFragment cpbFragment;
    private CPCFragment cpcFragment;

    private String fromId;

    @Override
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_my_information);
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
    public void initData(@Nullable Bundle savedInstanceState) {

        viewpagerFragment = new ArrayList<>();
        cpaFragment = new CPAFragment();
        cpbFragment = new CPBFragment();
        cpcFragment = new CPCFragment();
        viewpagerFragment.add(cpaFragment);
        viewpagerFragment.add(cpbFragment);
        viewpagerFragment.add(cpcFragment);
        mPagerAdapter = new CPAdapter(getChildFragmentManager(), viewpagerFragment);
        cpViewpager.setAdapter(mPagerAdapter);
        vpMagicindicator.setViewPager(cpViewpager);
        cpViewpager.setOffscreenPageLimit(3);

        rongYuAdapter = new RongYuAdapter();
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        SpaceItemDecoration sid = new SpaceItemDecoration(20, 0);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        rongyuRecyc.addItemDecoration(sid);
        rongyuRecyc.setLayoutManager(llm);
        rongyuRecyc.setAdapter(rongYuAdapter);

        Bundle arguments = getArguments();
        fromId = arguments.getString("fromId");
        if (arguments != null) {
            if (arguments.getParcelable("userinfo") != null) {
                MyPersonalCebterTwoBean.DataBean.UserInfoBean userinfo = arguments.getParcelable("userinfo");
                //年龄
                personalAge.setText(userinfo.getAge() + "岁");
                //星座
                personalCta.setText(userinfo.getConstellation());
                //地址
                personalAddress.setText(userinfo.getCity());
                //ID
                personalId.setText("ID：" + String.valueOf(userinfo.getId()));
            }

            //荣誉
            if (arguments.getParcelableArrayList("rongyu") != null && arguments.getParcelableArrayList("rongyu").size() != 0) {
                ArrayList<MyPersonalCebterTwoBean.DataBean.GloryBean> rongyu = arguments.getParcelableArrayList("rongyu");
                noRongyu.setVisibility(View.GONE);
                rongyuRecyc.setVisibility(View.VISIBLE);
                rongYuAdapter.setNewData(rongyu);
            } else {
                noRongyu.setVisibility(View.VISIBLE);
                rongyuRecyc.setVisibility(View.GONE);
            }

            if (arguments.getParcelableArrayList("cp") != null && arguments.getParcelableArrayList("cp").size() != 0) {
                ArrayList<MyPersonalCebterTwoBean.DataBean.CplistBean> cp = arguments.getParcelableArrayList("cp");
                setCP(cp);
            }
        }

        cpHelp.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DengJiShuoMingActivity.class);
            intent.putExtra("tag", "2");
            ArmsUtils.startActivity(intent);
        });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    //设置CP
    private void setCP(List<MyPersonalCebterTwoBean.DataBean.CplistBean> cplistBeanList) {

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("cpbeanone", (ArrayList<? extends Parcelable>) cplistBeanList);
        if ("".equals(fromId)) {
            bundle.putString("idd", UserManager.getUser().getUserId() + "");
        } else {
            bundle.putString("idd", fromId + "");
        }
        cpaFragment.setArguments(bundle);

        Bundle bundle1 = new Bundle();
        bundle1.putParcelableArrayList("cpbeantwo", (ArrayList<? extends Parcelable>) cplistBeanList);
        if ("".equals(fromId)) {
            bundle1.putString("idd", UserManager.getUser().getUserId() + "");
        } else {
            bundle1.putString("idd", fromId + "");
        }
        cpbFragment.setArguments(bundle1);


        Bundle bundle2 = new Bundle();
        bundle2.putParcelableArrayList("cpbeanthree", (ArrayList<? extends Parcelable>) cplistBeanList);
        if ("".equals(fromId)) {
            bundle2.putString("idd", UserManager.getUser().getUserId() + "");
        } else {
            bundle2.putString("idd", fromId + "");
        }
        cpcFragment.setArguments(bundle2);

        mPagerAdapter.notifyDataSetChanged();
    }
}
