package com.qutu.talk.adapter;

import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.bean.FamilyPdinfo;

public class FamilyPartyAdapter extends BaseQuickAdapter<FamilyPdinfo, BaseViewHolder> {

    public FamilyPartyAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, FamilyPdinfo item) {
        ArmsUtils.obtainAppComponentFromContext(helper.itemView.getContext())
                .imageLoader()
                .loadImage(helper.itemView.getContext(), ImageConfigImpl
                        .builder()
                        .url(item.getRoom_cover())
                        .placeholder(R.mipmap.no_tou)
                        .imageView(helper.getView(R.id.civ_avatar))
                        .errorPic(R.mipmap.no_tou)
                        .build());
        helper.setText(R.id.tv_title,item.getRoom_name());
        helper.setText(R.id.tv_intro,item.getRoom_intro());
    }
}
