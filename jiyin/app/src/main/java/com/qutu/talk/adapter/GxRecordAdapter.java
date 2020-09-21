package com.qutu.talk.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.bean.BxRecord;

import java.util.List;

public class GxRecordAdapter extends BaseQuickAdapter<BxRecord.DataBean, BaseViewHolder> {

    public GxRecordAdapter(int layoutResId, @Nullable List<BxRecord.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, BxRecord.DataBean item) {
        ArmsUtils.obtainAppComponentFromContext(helper.itemView.getContext())
                .imageLoader()
                .loadImage(helper.itemView.getContext(), ImageConfigImpl
                        .builder()
                        .url(item.getHeadimgurl())
                        .placeholder(R.mipmap.no_tou)
                        .imageView(helper.getView(R.id.civ_avatar))
                        .errorPic(R.mipmap.no_tou)
                        .build());
        helper.setText(R.id.tv_username,item.getNickname());
        helper.setText(R.id.tv_gift_name,item.getName());
        helper.setText(R.id.tv_type,item.getBox_type()==1?"在普通贡献中得到":"在快速贡献中得到");
        helper.setText(R.id.tv_num,"X"+item.getNum());
        ArmsUtils.obtainAppComponentFromContext(helper.itemView.getContext())
                .imageLoader()
                .loadImage(helper.itemView.getContext(), ImageConfigImpl
                        .builder()
                        .url(item.getShow_img())
                        .placeholder(R.mipmap.no_tou)
                        .imageView(helper.getView(R.id.iv_image))
                        .errorPic(R.mipmap.no_tou)
                        .build());
    }
}
