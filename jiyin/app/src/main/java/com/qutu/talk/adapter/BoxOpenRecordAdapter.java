package com.qutu.talk.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.qutu.talk.R;
import com.qutu.talk.bean.BoxOpenRecordBean;

import java.util.ArrayList;
import java.util.List;

public class BoxOpenRecordAdapter extends BaseQuickAdapter<BoxOpenRecordBean.DataBean, BaseViewHolder> {


    public BoxOpenRecordAdapter(int layoutResId, @Nullable List<BoxOpenRecordBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, BoxOpenRecordBean.DataBean item) {
        helper.setText(R.id.tv_id,item.getId()+"、");
        helper.setText(R.id.tv_gift_name,item.getName());
        helper.setText(R.id.tv_num,"X"+item.getNum());
        helper.setText(R.id.tv_price,item.getPrice()+"金币");
        GlideArms.with(helper.itemView.getContext())
                .load(item.getShow_img())
                .placeholder(R.mipmap.no_tu)
                .error(R.mipmap.no_tu)
                .into((ImageView) helper.getView(R.id.iv_gift));
        helper.setText(R.id.tv_time,item.getAddtime());
    }
}
