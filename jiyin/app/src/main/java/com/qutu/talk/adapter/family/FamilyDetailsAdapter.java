package com.qutu.talk.adapter.family;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.bean.FamilyUser;
import com.qutu.talk.bean.GetFamilyDetailResult;

import java.util.ArrayList;
import java.util.List;

public class FamilyDetailsAdapter extends BaseQuickAdapter<FamilyUser, BaseViewHolder> {
    private Context mContext;

    public FamilyDetailsAdapter(Context context) {
        super(R.layout.family_user_item, new ArrayList<>());
        this.mContext = context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, FamilyUser item) {
        ArmsUtils.obtainAppComponentFromContext(mContext)
                .imageLoader()
                .loadImage(mContext, ImageConfigImpl
                        .builder()
                        .url(item.getHeadimgurl())
                        .placeholder(R.mipmap.no_tou)
                        .imageView(helper.getView(R.id.family_user_head))
                        .errorPic(R.mipmap.no_tou)
                        .build());

        helper.getView(R.id.family_user_title).setVisibility(View.VISIBLE);
        if (item.getUser_type() == 2) {
            helper.setBackgroundRes(R.id.family_user_title, R.drawable.lan_family_user_shape);
            helper.setText(R.id.family_user_title, "族长");
        } else if (item.getUser_type() == 1) {
            helper.setBackgroundRes(R.id.family_user_title, R.drawable.juhuang_family_user_shape);
            helper.setText(R.id.family_user_title, "管理");
        } else {
            helper.getView(R.id.family_user_title).setVisibility(View.GONE);
        }
    }
}
