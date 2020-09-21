package com.qutu.talk.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.activity.dashen.DaShenListActivity;
import com.qutu.talk.activity.dashen.GodPerCenterActivity;
import com.qutu.talk.activity.order.ConfirmOrderActivity;
import com.qutu.talk.adapter.SkillFragmentAdapter;
import com.qutu.talk.base.MyBaseArmFragment;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.MyPersonalCebterTwoBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.ItemDecorationUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 个人中心页面中的技能页面
 * 老王
 */
public class SkillFragment extends MyBaseArmFragment {
    @Inject
    CommonModel commonModel;

    @BindView(R.id.personal_slil_recyclerview)
    RecyclerView personalSlilRecyclerview;

    private SkillFragmentAdapter mAdapter;
    private Intent mIntent;

    @Override
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_skill);
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
        Bundle arguments = getArguments();

        int userId = UserManager.getUser().getUserId();

        mAdapter = new SkillFragmentAdapter(userId);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        ItemDecorationUtil did = new ItemDecorationUtil(mContext, ItemDecorationUtil.VERTICAL);
//        did.setDrawable(getResources().getDrawable(R.drawable.did_gray_dp8));
//        personalSlilRecyclerview.addItemDecoration(did);
        personalSlilRecyclerview.setLayoutManager(llm);
        personalSlilRecyclerview.setAdapter(mAdapter);

        if (arguments != null) {
            if (arguments.getParcelableArrayList("skillList") != null && arguments.getParcelableArrayList("skillList").size() != 0) {
                ArrayList<MyPersonalCebterTwoBean.DataBean.SkilllistBean> skillList = arguments.getParcelableArrayList("skillList");
                mAdapter.setNewData(skillList);
                mAdapter.setList(skillList);
            }
        }

        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            List<MyPersonalCebterTwoBean.DataBean.SkilllistBean> data = mAdapter.getData();
            MyPersonalCebterTwoBean.DataBean.SkilllistBean skilllistBean = data.get(position);
            if (mIntent==null){
                mIntent = new Intent(getActivity(), ConfirmOrderActivity.class);
            }
            mIntent.putExtra("skillId", String.valueOf(skilllistBean.getSkill_id()));
            mIntent.putExtra("id", skilllistBean.getId());
            mIntent.putExtra("userId",String.valueOf(skilllistBean.getUser_id()));
            mIntent.putExtra("rank1_head1", skilllistBean.getImg_1());
            mIntent.putExtra("naicName", skilllistBean.getNickname());
            mIntent.putExtra("price", String.valueOf(skilllistBean.getPrice()));
            mIntent.putExtra("unit", skilllistBean.getUnit());
            mIntent.putExtra("skillName", skilllistBean.getSkill_name());
            startActivity(mIntent);
        });

        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            MyPersonalCebterTwoBean.DataBean.SkilllistBean skilllistBean = mAdapter.getData().get(position);
            mIntent = new Intent(getActivity(), GodPerCenterActivity.class);
            mIntent.putExtra("id", skilllistBean.getId());
            mIntent.putExtra("skillName",skilllistBean.getSkill_name());
            mIntent.putExtra("skillId", String.valueOf(skilllistBean.getSkill_id()));
            mIntent.putExtra("price", String.valueOf(skilllistBean.getPrice()));
            mIntent.putExtra("unit", skilllistBean.getUnit());
            startActivity(mIntent);
        });
    }

    @Override
    public void setData(@Nullable Object data) {

    }
}
