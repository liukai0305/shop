package com.qutu.talk.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.qutu.talk.R;
import com.qutu.talk.base.BaseDelegateAdapter;
import com.qutu.talk.base.BaseDelegateViewHolder;

import java.util.List;

public class MainHomeTitleNewPeopleAdapter extends BaseDelegateAdapter<String, BaseDelegateViewHolder> {


    View.OnClickListener mOnClickListener;

    public MainHomeTitleNewPeopleAdapter(int layoutResId, @Nullable List<String> data, LayoutHelper layoutHelper) {
        super(layoutResId, data, layoutHelper);
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.mOnClickListener = onClickListener;
    }

    @Override
    public void convert(BaseDelegateViewHolder helper, String item) {

        TextView tvGirl = helper.getView(R.id.textGirl);

        TextView tvBoy = helper.getView(R.id.textBoy);
        ImageView imgRefresh = helper.getView(R.id.imgFresh);

        if(item.equals("1")){

            tvBoy.setSelected(true);

            tvGirl.setSelected(false);

        } else {
            tvBoy.setSelected(false);

            tvGirl.setSelected(true);
        }

        tvGirl.setOnClickListener(mOnClickListener);

        tvBoy.setOnClickListener(mOnClickListener);

        imgRefresh.setOnClickListener(mOnClickListener);

    }
}
