package com.qutu.talk.adapter.dashen;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qutu.talk.R;
import com.qutu.talk.bean.HotBean;
import com.qutu.talk.bean.dashen.MainHomePageSkillBean;

import java.util.ArrayList;
import java.util.List;

public class Shengyou2Adapter extends BaseQuickAdapter<HotBean.DataBean, BaseViewHolder> {
    public Shengyou2Adapter(List<HotBean.DataBean> mDataList1) {
        super(R.layout.item_shengyou, mDataList1);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HotBean.DataBean item) {
        helper.setText(R.id.biaoqian, item.getName())
                .setText(R.id.room_biaoqian, item.getRoom_name())
                .setText(R.id.id, item.getHot())
                .setText(R.id.hot, item.getHot());

        if (TextUtils.isEmpty(item.getHost())) {
            helper.setText(R.id.name, "主持：暂无主持");
        } else {
            helper.setText(R.id.name, "主持：" + item.getHost());
        }
        helper.setText(R.id.tv_id, "ID：" + item.getNumid());

        RoundedImageView headImg = helper.getView(R.id.head_img);
        if (!TextUtils.isEmpty(item.getRoom_cover())) {
            ArmsUtils.obtainAppComponentFromContext(mContext)
                    .imageLoader()
                    .loadImage(mContext, ImageConfigImpl
                            .builder()
                            .url(item.getRoom_cover())
                            .placeholder(R.mipmap.no_tu)
                            .imageView(headImg)
                            .errorPic(R.mipmap.no_tu)
                            .build());
        }

        ImageView labelImg = helper.getView(R.id.biaoqian_img);
        if (!TextUtils.isEmpty(item.getCate_img())) {
            ArmsUtils.obtainAppComponentFromContext(mContext)
                    .imageLoader()
                    .loadImage(mContext, ImageConfigImpl
                            .builder()
                            .url(item.getCate_img())
                            .placeholder(R.mipmap.no_tu)
                            .imageView(labelImg)
                            .errorPic(R.mipmap.no_tu)
                            .build());
        }
    }
}
