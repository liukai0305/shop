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
import com.qutu.talk.bean.FamilyRank;
import com.qutu.talk.bean.Rank;

import java.util.ArrayList;

/**
 * 排行榜
 */
@ActivityScope
public class FamilyRankAdapter extends BaseQuickAdapter<FamilyRank.DataBean.Item, BaseViewHolder> {
    boolean mIsFirstTransion = true;
    BaseViewHolder mHelper;

    public FamilyRankAdapter() {
        super(R.layout.item_rank, new ArrayList<>());
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, FamilyRank.DataBean.Item item) {
        mHelper = helper;
        helper.addOnClickListener(R.id.ci_head);
        helper.setTextColor(R.id.textNum, mContext.getResources().getColor(R.color.color_FFBA1C));
        helper.setText(R.id.tit, "贡献值");
        helper.setText(R.id.text1, String.valueOf(helper.getPosition() + 3))
                .setText(R.id.tv_title, item.getJzname())
                .setText(R.id.textNum, item.getExp()+"");
        helper.setText(R.id.tv_id,"ID:"+item.getJzid());
        if (!TextUtils.isEmpty(item.getImg())) {
            ArmsUtils.obtainAppComponentFromContext(mContext)
                    .imageLoader()
                    .loadImage(mContext, ImageConfigImpl
                            .builder()
                            .url(item.getImg())
                            .placeholder(R.mipmap.no_tou)
                            .imageView(helper.getView(R.id.ci_head))
                            .errorPic(R.mipmap.no_tou)
                            .build());
        }
        if (helper.getPosition() == 1) {
            if (!item.isBg()){
                helper.setBackgroundRes(R.id.item_back,R.drawable.transion_corner_shape);
            }else {
//                helper.setBackgroundRes(R.id.item_back, R.drawable.white_corner_shape);
                helper.setBackgroundRes(R.id.item_back,R.drawable.transion_corner_shape);
            }
        } else {
            helper.setBackgroundRes(R.id.item_back, R.drawable.white_corner_shape_two);
        }
    }


}