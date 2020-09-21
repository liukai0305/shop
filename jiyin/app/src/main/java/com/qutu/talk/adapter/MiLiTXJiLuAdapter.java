package com.qutu.talk.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qutu.talk.R;
import com.qutu.talk.bean.CashHis;

import java.util.List;

public class MiLiTXJiLuAdapter extends BaseQuickAdapter<CashHis.DataBean, BaseViewHolder> {
    public MiLiTXJiLuAdapter(int layoutResId, @Nullable List<CashHis.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CashHis.DataBean item) {
        helper.getView(R.id.state).setVisibility(View.VISIBLE);
        helper.setText(R.id.tv_title, "提现金币")
                .setText(R.id.tv_userid, item.getAddtime())
                .setText(R.id.btn_ok, "-" + item.getMoney() + "金币")
                .setText(R.id.state, item.getTitle());
    }
}
