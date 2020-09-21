package com.example.xiangmuyi.adapter.homeadapter.zhishi;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.zhishibean.HomeTraDitionalBean;

import java.util.List;

public class ChuanTongAdapter extends BaseAdapter {
    private ImageView mIv;
    private TextView mTv;
    private TextView mTv2;

    public ChuanTongAdapter(List data, Context context) {
        super(data, context);

    }

    @Override
    protected int getLayout() {
        return R.layout.item_zhishi_know;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        mIv = (ImageView) vh.getViewById(R.id.iv);
        mTv = (TextView) vh.getViewById(R.id.tv);
        mTv2 = (TextView) vh.getViewById(R.id.tv_2);
        HomeTraDitionalBean.DataBean dataBean= (HomeTraDitionalBean.DataBean) data;
        Glide.with(context).load(dataBean.getCover()).into(mIv);
        mTv.setText(dataBean.getTitle());
        mTv2.setText(dataBean.getContentDescribe());
    }
}
