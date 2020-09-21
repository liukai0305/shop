package com.qutu.talk.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qutu.talk.R;
import com.qutu.talk.bean.MyCouponsBean;

import java.util.List;

public class MyCouponAdapter extends BaseQuickAdapter<MyCouponsBean.DataBean, BaseViewHolder> {
    private Context mContext;
    private String mType;

    public MyCouponAdapter(int layoutResId, @Nullable List<MyCouponsBean.DataBean> data, Context context, String type) {
        super(layoutResId, data);
        this.mContext = context;
        this.mType = type;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(@NonNull BaseViewHolder helper, MyCouponsBean.DataBean item) {
        helper.setText(R.id.my_coupon_pirce, String.valueOf(item.getPrice()))
                .setText(R.id.my_coupon_source, item.getGet_type())
                .setText(R.id.my_coupon_time, item.getAddtime() + "~" + item.getExpire());
        TextView source = helper.getView(R.id.my_coupon_source);
        TextView purpose = helper.getView(R.id.my_coupon_purpose);
        TextView time = helper.getView(R.id.my_coupon_time);
        if ("1".equals(mType)) {
            helper.getView(R.id.tishi).setVisibility(View.GONE);
            helper.getView(R.id.tishi_img).setVisibility(View.GONE);
            source.setTextColor(mContext.getResources().getColor(R.color.font_333333));
            purpose.setTextColor(mContext.getResources().getColor(R.color.coloe_666666));
            time.setTextColor(mContext.getResources().getColor(R.color.font_999999));
        } else if ("2".equals(mType)) {
            helper.getView(R.id.tishi).setVisibility(View.VISIBLE);
            helper.getView(R.id.tishi_img).setVisibility(View.VISIBLE);
            helper.setText(R.id.tishi, "已使用")
                    .setImageResource(R.id.tishi_img, R.mipmap.shiyong);

            source.setTextColor(mContext.getResources().getColor(R.color.font_999999));
            purpose.setTextColor(mContext.getResources().getColor(R.color.font_999999));
            time.setTextColor(mContext.getResources().getColor(R.color.font_999999));
        }else {
            helper.getView(R.id.tishi).setVisibility(View.VISIBLE);
            helper.getView(R.id.tishi_img).setVisibility(View.VISIBLE);
            helper.setText(R.id.tishi, "已过期")
                    .setImageResource(R.id.tishi_img, R.mipmap.guoqi);

            source.setTextColor(mContext.getResources().getColor(R.color.font_999999));
            purpose.setTextColor(mContext.getResources().getColor(R.color.font_999999));
            time.setTextColor(mContext.getResources().getColor(R.color.font_999999));

        }
    }
}
