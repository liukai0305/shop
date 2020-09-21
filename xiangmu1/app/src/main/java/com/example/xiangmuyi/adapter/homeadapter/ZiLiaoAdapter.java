package com.example.xiangmuyi.adapter.homeadapter;

import android.content.Context;
import android.widget.TextView;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.bean.homebean.UserInfoBean;

import java.util.List;

public class ZiLiaoAdapter extends BaseAdapter {


    private TextView mTvSex;
    private TextView mTvBirth;
    private TextView mTvHoroscope;
    private TextView mTvCity;

    public ZiLiaoAdapter(List data, Context context) {
        super(data, context);

    }

    @Override
    protected int getLayout() {
        return R.layout.item_ziliao;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        mTvSex = (TextView) vh.getViewById(R.id.tv_sex);
        mTvBirth = (TextView) vh.getViewById(R.id.tv_birth);
        mTvHoroscope = (TextView) vh. getViewById(R.id.tv_Horoscope);
        mTvCity = (TextView) vh.getViewById(R.id.tv_city);
        UserInfoBean.DataBean dataBean = (UserInfoBean.DataBean) data;
        mTvSex.setText("性别" + dataBean.getSex());
        mTvBirth.setText("年龄" + dataBean.getBirthday());
        mTvCity.setText("地区" + dataBean.getCity());

    }
}
