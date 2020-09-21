package com.qutu.talk.adapter;

import android.support.annotation.Nullable;

import com.alibaba.android.vlayout.LayoutHelper;
import com.qutu.talk.R;
import com.qutu.talk.base.BaseDelegateAdapter;
import com.qutu.talk.base.BaseDelegateViewHolder;

import java.util.List;

public class MainHomeTitleAdapter_1 extends BaseDelegateAdapter<String, BaseDelegateViewHolder> {

    public MainHomeTitleAdapter_1(int layoutResId, @Nullable List<String> data, LayoutHelper layoutHelper) {
        super(layoutResId, data, layoutHelper);
    }

    @Override
    public void convert(BaseDelegateViewHolder helper, String item) {

        helper.setText(R.id.tv_left_name, item);

        helper.setImageResource(R.id.img_left, R.mipmap.icon_title_recommend);

        helper.setGone(R.id.tv_right_more, false);

    }
}
