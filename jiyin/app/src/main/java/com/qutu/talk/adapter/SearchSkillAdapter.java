package com.qutu.talk.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.qutu.talk.R;
import com.qutu.talk.app.view.CircularImage;
import com.qutu.talk.bean.Search;

import java.util.ArrayList;
import java.util.List;

public class SearchSkillAdapter extends BaseQuickAdapter<Search.DataBean.GmskillBean, BaseViewHolder> {
    public SearchSkillAdapter() {
        super(R.layout.search_skill_item, new ArrayList<>());
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Search.DataBean.GmskillBean item) {
        CircularImage headImage = helper.getView(R.id.search_skill_head);
        GlideArms.with(mContext)
                .load(item.getImage())
                .placeholder(R.mipmap.no_tou)
                .error(R.mipmap.no_tou)
                .circleCrop()
                .into(headImage);

        helper.setText(R.id.search_skill_name, item.getName());
    }
}
