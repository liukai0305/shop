package com.qutu.talk.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qutu.talk.R;
import com.qutu.talk.bean.RoomRewardOneBean;

import java.util.List;

public class RoomRewardOneAdapter extends BaseQuickAdapter<RoomRewardOneBean.DataBean, BaseViewHolder> {
    public RoomRewardOneAdapter(int layoutResId, @Nullable List<RoomRewardOneBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RoomRewardOneBean.DataBean item) {
        helper.setText(R.id.name,item.getUser_name()+"送给"+item.getFrom_name());
        helper.setText(R.id.num,item.getGiftName()+" x"+item.getGiftNum());
        helper.setText(R.id.price,item.getGiftPrice()+"金币");
        helper.setText(R.id.time,item.getCreated_at());
    }
}
