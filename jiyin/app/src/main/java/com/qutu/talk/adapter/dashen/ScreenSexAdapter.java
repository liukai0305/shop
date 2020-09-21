package com.qutu.talk.adapter.dashen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qutu.talk.R;
import com.qutu.talk.bean.dashen.ScreenSexBean;

import java.util.ArrayList;
import java.util.List;

public class ScreenSexAdapter extends BaseQuickAdapter<ScreenSexBean.DataBean, BaseViewHolder> {
    private Context mContext;
    public boolean isAdapter = true;
    public ScreenSexAdapter(Context context) {
        super(R.layout.screen_adapter_item, new ArrayList<>());
        this.mContext=context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ScreenSexBean.DataBean item) {
        helper.addOnClickListener(R.id.screen_sex_all);
        helper.getView(R.id.screen_sex_all).setSelected(isAdapter == true ? item.isSelector : item.isClick);
        helper.setText(R.id.screen_sex_all, item.getSex());
    }
}
