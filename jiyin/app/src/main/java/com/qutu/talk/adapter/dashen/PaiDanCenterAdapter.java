package com.qutu.talk.adapter.dashen;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qutu.talk.R;
import com.qutu.talk.bean.dashen.PaiDanCenterBean;

import java.util.List;

public class PaiDanCenterAdapter extends BaseQuickAdapter<PaiDanCenterBean.DataBean, BaseViewHolder> {
    public PaiDanCenterAdapter(int layoutResId, @Nullable List<PaiDanCenterBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, PaiDanCenterBean.DataBean item) {
        helper.addOnClickListener(R.id.paidan_ok_btn);

        helper.setText(R.id.paidan_now_time, item.getAddtime())
                .setText(R.id.paidan_game_name, item.getSkill_name())
                .setText(R.id.paidan_roomandid, "房间：" + item.getRoom_name() + "  ID：" + item.getUid())
                .setText(R.id.paidan_price, "备注：" + item.getRemark())
                .setText(R.id.paidan_time, "日期：" + item.getTime());

        if (item.getStatus() == 0) {
            helper.setText(R.id.paidan_ok_btn, "去接单");
            helper.getView(R.id.paidan_ok_btn).setSelected(true);
        } else {
            helper.setText(R.id.paidan_ok_btn, "已结束");
            helper.getView(R.id.paidan_ok_btn).setSelected(false);
        }


    }
}
