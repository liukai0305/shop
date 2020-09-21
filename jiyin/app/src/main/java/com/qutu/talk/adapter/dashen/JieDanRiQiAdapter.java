package com.qutu.talk.adapter.dashen;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qutu.talk.R;
import com.qutu.talk.bean.dashen.JieDanRiQiBean;

import java.util.ArrayList;
import java.util.List;

public class JieDanRiQiAdapter extends BaseQuickAdapter<JieDanRiQiBean.DataBean, BaseViewHolder> {
    public JieDanRiQiAdapter() {
        super(R.layout.jiedan_data_item, new ArrayList<>());
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, JieDanRiQiBean.DataBean item) {
        helper.addOnClickListener(R.id.jiedan_data_item);

        helper.setText(R.id.jiedan_data_item, item.getWeek());
        if (item.getStatus() == 0) {
            helper.getView(R.id.jiedan_data_item).setSelected(false);
        } else {
            helper.getView(R.id.jiedan_data_item).setSelected(true);
        }
    }
}
