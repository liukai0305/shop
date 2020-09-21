package com.qutu.talk.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qutu.talk.R;
import com.qutu.talk.bean.RoomInfoBean;

import java.util.ArrayList;
import java.util.List;

public class RoomCategoryAdapterOne extends BaseQuickAdapter<RoomInfoBean.DataBean.RoomsCateBean, BaseViewHolder> {
    public RoomCategoryAdapterOne() {
        super(R.layout.room_category_item_one, new ArrayList<>());
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RoomInfoBean.DataBean.RoomsCateBean item) {
        helper.setText(R.id.room_category_text_one, item.getName());

        if (item.getIs_check() == 1) {
            helper.getView(R.id.room_category_text_one).setSelected(true);
        } else {
            helper.getView(R.id.room_category_text_one).setSelected(false);
        }
    }
}
