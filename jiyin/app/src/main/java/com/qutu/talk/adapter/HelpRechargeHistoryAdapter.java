package com.qutu.talk.adapter;

import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.bean.ActiveListBean;
import com.qutu.talk.bean.HelpRechargeHistory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HelpRechargeHistoryAdapter extends BaseQuickAdapter<HelpRechargeHistory.DataBean, BaseViewHolder> {
    public HelpRechargeHistoryAdapter() {
        super(R.layout.item_help_recharge_history, new ArrayList<>());
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HelpRechargeHistory.DataBean item) {
        helper.addOnClickListener(R.id.ll_root);
        ArmsUtils.obtainAppComponentFromContext(mContext)
                .imageLoader()
                .loadImage(mContext, ImageConfigImpl
                        .builder()
                        .url(item.getB_headimg())
                        .placeholder(R.mipmap.default_home)
                        .imageView(helper.getView(R.id.iv_icon))
                        .errorPic(R.mipmap.default_home)
                        .build());
        helper.setText(R.id.tv_user_id,item.getB_user_id());
        helper.setText(R.id.tv_time,new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(new Date(Long.parseLong(item.getAddtime())*1000)));
        helper.setText(R.id.tv_diamond,item.getNum()+"金币");
    }
}
