package com.qutu.talk.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qutu.talk.R;
import com.qutu.talk.bean.IncomeFragmentBean;

import java.util.List;

public class IncomeFragmentAdapter extends BaseQuickAdapter<IncomeFragmentBean.DataBean, BaseViewHolder> {
    private int tag;

    public IncomeFragmentAdapter(int layoutResId, @Nullable List<IncomeFragmentBean.DataBean> data, int tag) {
        super(layoutResId, data);
        this.tag = tag;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, IncomeFragmentBean.DataBean item) {
        if (tag == 0) {
            helper.setText(R.id.tv_title, item.getNickname() + "赠送 " + item.getGiftName() + "x" + item.getGiftNum())
                    .setText(R.id.tv_userid, item.getCreated_at())
                    .setText(R.id.btn_ok, "+" + item.getGiftPrice() + "钻石");
        } else {
            helper.setText(R.id.tv_title,  "赠送"+item.getNickname()+" "+ item.getGiftName() + "x" + item.getGiftNum())
                    .setText(R.id.tv_userid, item.getCreated_at())
                    .setText(R.id.btn_ok, "-" + item.getGiftPrice() + "金币");
        }
    }
}
