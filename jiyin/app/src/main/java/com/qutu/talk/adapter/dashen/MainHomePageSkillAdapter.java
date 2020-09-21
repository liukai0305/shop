package com.qutu.talk.adapter.dashen;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.bean.dashen.MainHomePageSkillBean;

import java.util.ArrayList;
import java.util.List;

public class MainHomePageSkillAdapter extends BaseQuickAdapter<MainHomePageSkillBean.DataBean, BaseViewHolder> {
    public MainHomePageSkillAdapter() {
        super(R.layout.main_hone_page_skill_item, new ArrayList<>());
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MainHomePageSkillBean.DataBean item) {

        ArmsUtils.obtainAppComponentFromContext(mContext)
                .imageLoader()
                .loadImage(mContext, ImageConfigImpl
                        .builder()
                        .url(item.getImage())
                        .placeholder(R.mipmap.no_tou)
                        .imageView(helper.getView(R.id.main_homepage_skill_img))
                        .errorPic(R.mipmap.no_tou)
                        .build());
        helper.setText(R.id.main_homepage_skill_name, item.getName());
    }
}
