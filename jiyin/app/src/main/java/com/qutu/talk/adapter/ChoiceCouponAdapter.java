package com.qutu.talk.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qutu.talk.R;
import com.qutu.talk.bean.ChoiceCouponBean;

import java.util.ArrayList;
import java.util.List;

public class ChoiceCouponAdapter extends BaseQuickAdapter<ChoiceCouponBean.DataBean, BaseViewHolder> {
    public ChoiceCouponAdapter() {
        super(R.layout.choice_coupon_item, new ArrayList<>());
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ChoiceCouponBean.DataBean item) {
        helper.setText(R.id.my_coupon_pirce, String.valueOf(item.getPrice()))
                .setText(R.id.my_coupon_source, item.getGet_type())
                .setText(R.id.my_coupon_time, item.getAddtime() + "~" + item.getExpire());

        TextView source = helper.getView(R.id.my_coupon_source);
        TextView purpose = helper.getView(R.id.my_coupon_purpose);
        TextView time = helper.getView(R.id.my_coupon_time);
        source.setTextColor(mContext.getResources().getColor(R.color.font_333333));
        purpose.setTextColor(mContext.getResources().getColor(R.color.coloe_666666));
        time.setTextColor(mContext.getResources().getColor(R.color.font_999999));

        if (item.isCheck()) {
            helper.getView(R.id.xuanze).setSelected(true);
        } else {
            helper.getView(R.id.xuanze).setSelected(false);
        }
    }
}
