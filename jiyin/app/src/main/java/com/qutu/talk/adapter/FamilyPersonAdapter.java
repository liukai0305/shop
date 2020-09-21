package com.qutu.talk.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.bean.FamilyUser;

import java.util.List;

public class FamilyPersonAdapter extends BaseQuickAdapter<FamilyUser, BaseViewHolder> {


    public FamilyPersonAdapter(int layoutResId, @Nullable List<FamilyUser> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, FamilyUser item) {
        ArmsUtils.obtainAppComponentFromContext(helper.itemView.getContext())
                .imageLoader()
                .loadImage(helper.itemView.getContext(), ImageConfigImpl
                        .builder()
                        .url(item.getHeadimgurl())
                        .placeholder(R.mipmap.no_tou)
                        .imageView(helper.getView(R.id.civ_avatar))
                        .errorPic(R.mipmap.no_tou)
                        .build());
        helper.setText(R.id.tv_name,item.getNickname());
        switch (item.getUser_type()){
            case 0:
                helper.setText(R.id.tv_level,"成员");
                break;
            case 1:
                helper.setText(R.id.tv_level,"副会长");
                break;
            case 2:
                helper.setText(R.id.tv_level,"会长");
                break;
        }
    }
}
