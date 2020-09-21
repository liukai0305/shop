package com.qutu.talk.adapter.dashen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qutu.talk.R;
import com.qutu.talk.bean.dashen.ScreenPriceBean;

import java.util.ArrayList;
import java.util.List;

public class SreenPriceAdapter extends BaseQuickAdapter<ScreenPriceBean.DataBean, BaseViewHolder> {
    private Context mContext;
    public boolean isAdapter = true;

    public SreenPriceAdapter(Context context) {
        super(R.layout.screen_adapter_item, new ArrayList<>());
        this.mContext = context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ScreenPriceBean.DataBean item) {
        helper.addOnClickListener(R.id.screen_sex_all);
        helper.getView(R.id.screen_sex_all).setSelected(isAdapter == true ? item.isSelector : item.isClick);
        if (helper.getPosition() == 0) {
            helper.setText(R.id.screen_sex_all, item.getPrice());
        } else {
            helper.setText(R.id.screen_sex_all, item.getPrice() + "金币");
        }
    }
}
