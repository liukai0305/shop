package com.qutu.talk.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.lzy.widget.CircleImageView;
import com.qutu.talk.R;
import com.qutu.talk.bean.ClearMlHistory;
import com.qutu.talk.bean.Microphone;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ClearMlHistoryAdapter extends BaseQuickAdapter<ClearMlHistory.DataBean, BaseViewHolder> {

    public ClearMlHistoryAdapter(int layoutResId, @Nullable List<ClearMlHistory.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ClearMlHistory.DataBean item) {
        helper.setText(R.id.tv_time,"您于"+new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA).format(new Date(Long.parseLong(item.getAddtime())*1000)) +"清除");
        helper.setText(R.id.tv_nickname,item.getNickname());
        helper.setText(R.id.tv_ml,item.getNum());
//        GlideArms.with(helper.itemView.getContext())
//                .load(item.getHeadimgurl())
//                .placeholder(R.mipmap.no_tu)
//                .error(R.mipmap.no_tu)
//                .into((CircleImageView) helper.getView(R.id.civ_avatar));
    }
}
