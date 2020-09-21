package com.qutu.talk.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.qutu.talk.R;
import com.qutu.talk.bean.MyPersonalCebterTwoBean;

import java.util.ArrayList;
import java.util.List;

public class SkillFragmentAdapter extends BaseQuickAdapter<MyPersonalCebterTwoBean.DataBean.SkilllistBean, BaseViewHolder> {
    private List<MyPersonalCebterTwoBean.DataBean.SkilllistBean> list;
    private int mUserId;

    public SkillFragmentAdapter(int userId) {
        super(R.layout.skill_list_item, new ArrayList<>());
        this.mUserId = userId;
    }

    public void setList(List<MyPersonalCebterTwoBean.DataBean.SkilllistBean> list) {
        this.list = list;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MyPersonalCebterTwoBean.DataBean.SkilllistBean item) {
        helper.addOnClickListener(R.id.skill_list_sure);

        if (mUserId == item.getUser_id()) {
            helper.getView(R.id.skill_list_sure).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.skill_list_sure).setVisibility(View.VISIBLE);
        }
        LogUtils.debugInfo("====传过来的ID", mUserId + "");
        LogUtils.debugInfo("====本身的ID", item.getUser_id() + "");

//        helper.getView(R.id.zaixian).setVisibility(View.GONE);

        helper.setText(R.id.skill_list_name, item.getSkill_name())
                .setText(R.id.skill_list_level, item.getLevel_name())
                .setText(R.id.skill_list_score, "评分：" + item.getScore() + "    " + "接单量：" + item.getNum())
                .setText(R.id.skill_list_price, item.getPrice() + "金币/" + item.getUnit());

        if (!TextUtils.isEmpty(item.getImg_1())) {
            ArmsUtils.obtainAppComponentFromContext(mContext)
                    .imageLoader()
                    .loadImage(mContext, ImageConfigImpl
                            .builder()
                            .url(item.getImg_2())
                            .placeholder(R.mipmap.no_tu)
                            .imageView(helper.getView(R.id.skill_list_img))
                            .errorPic(R.mipmap.no_tu)
                            .build());
        }

        if (helper.getPosition() == (list.size() - 1)) {
            helper.getView(R.id.skill_list_line).setVisibility(View.GONE);
        }
    }
}
