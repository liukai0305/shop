package com.qutu.talk.adapter;

import android.support.annotation.Nullable;

import com.alibaba.android.vlayout.LayoutHelper;
import com.qutu.talk.R;
import com.qutu.talk.base.BaseDelegateAdapter;
import com.qutu.talk.base.BaseDelegateViewHolder;

import java.util.List;

public class MainHomeBottomLineAdapter extends BaseDelegateAdapter<String, BaseDelegateViewHolder> {

    public MainHomeBottomLineAdapter(int layoutResId, @Nullable List<String> data, LayoutHelper layoutHelper) {
        super(layoutResId, data, layoutHelper);
    }

    @Override
    public void convert(BaseDelegateViewHolder helper, String item) {


    }
}
