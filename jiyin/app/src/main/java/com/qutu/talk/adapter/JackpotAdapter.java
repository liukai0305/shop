package com.qutu.talk.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.qutu.talk.R;
import com.qutu.talk.bean.BoxOpenRecordBean;
import com.qutu.talk.bean.Jackpot;

import java.util.List;

public class JackpotAdapter extends BaseQuickAdapter<Jackpot.DataBean.Info, BaseViewHolder> {


    public JackpotAdapter(int layoutResId, @Nullable List<Jackpot.DataBean.Info> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Jackpot.DataBean.Info item) {
//        helper.setText(R.id.record, item.getAddtime() + "开出" + item.getName());
//        helper.setText(R.id.num, "x" + item.getNum());
        helper.setText(R.id.tv_name,item.getGift_name());
        helper.setText(R.id.tv_num,item.getPrice()+"金币");
        GlideArms.with(helper.itemView.getContext())
                .load(item.getShow_img())
                .placeholder(R.mipmap.no_tu)
                .error(R.mipmap.no_tu)
                .into((ImageView) helper.getView(R.id.iv));
    }
}
