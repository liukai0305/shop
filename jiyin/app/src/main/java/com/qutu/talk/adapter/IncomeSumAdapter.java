package com.qutu.talk.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qutu.talk.R;
import com.qutu.talk.bean.IncomeSumBean;

import java.util.List;

public class IncomeSumAdapter extends BaseQuickAdapter<IncomeSumBean.DataBean.HistoryIncomeListBean, BaseViewHolder> {
    public IncomeSumAdapter(int layoutResId, @Nullable List<IncomeSumBean.DataBean.HistoryIncomeListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, IncomeSumBean.DataBean.HistoryIncomeListBean item) {
        helper.setText(R.id.name, item.getCount_date());
        helper.setText(R.id.price, item.getTotal_price() + "金币");
    }
}
