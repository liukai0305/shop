package com.qutu.talk.adapter;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.qutu.talk.R;
import com.qutu.talk.bean.Rank;

import java.util.ArrayList;

/**
 * 排行榜
 */
@ActivityScope
public class FamilyMeltingRankAdapter extends BaseQuickAdapter<Rank.DataBean.OtherBean, BaseViewHolder> {


    public FamilyMeltingRankAdapter() {
        super(R.layout.item_family_melting_rank, new ArrayList<>());
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Rank.DataBean.OtherBean item) {
        helper.setText(R.id.tv_rank,helper.getLayoutPosition()+3+"");
        if (!TextUtils.isEmpty(item.getHeadimgurl())) {
            ArmsUtils.obtainAppComponentFromContext(mContext)
                    .imageLoader()
                    .loadImage(mContext, ImageConfigImpl
                            .builder()
                            .url(item.getHeadimgurl())
                            .placeholder(R.mipmap.no_tou)
                            .imageView(helper.getView(R.id.civ_avatar))
                            .errorPic(R.mipmap.no_tou)
                            .build());
        }
        helper.setText(R.id.tv_nickname,item.getNickname());
        helper.setText(R.id.tv_id,"ID："+item.getUser_id());
        helper.setText(R.id.tv_num,item.getExp());
    }


}