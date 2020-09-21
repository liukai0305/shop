package com.qutu.talk.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qutu.talk.R;
import com.qutu.talk.bean.dashen.MiLiIncomeBean;

import java.util.List;

public class MiLiIncomAdapter extends BaseQuickAdapter<MiLiIncomeBean.DataBean.HistoryBean, BaseViewHolder> {
    public MiLiIncomAdapter(int layoutResId, @Nullable List<MiLiIncomeBean.DataBean.HistoryBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MiLiIncomeBean.DataBean.HistoryBean item) {
        helper.setText(R.id.name, item.getCount_date())
                .setText(R.id.price, item.getTotal_price() + "钻石");

        if (helper.getPosition() == getData().size()) {
            helper.getView(R.id.line).setVisibility(View.GONE);
        }
    }
}
