package com.example.xiangmuyi.adapter.homeadapter.zhishi;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.zhishibean.HomeKnowHanfuBean;

import java.util.List;

public class KnowAdapter extends BaseAdapter {
    private ImageView mIv;
    private TextView mTv;
    private TextView mTv2;

    public KnowAdapter(List data, Context context) {
        super(data, context);

    }

    @Override
    protected int getLayout() {
        return R.layout.item_zhishi_know;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        HomeKnowHanfuBean.DataBean bean = (HomeKnowHanfuBean.DataBean) data;
        mIv = (ImageView) vh.getViewById(R.id.iv);
        mTv = (TextView) vh.getViewById(R.id.tv);
        mTv2 = (TextView) vh.getViewById(R.id.tv_2);
        Glide.with(context).load(bean.getCover()).into(mIv);
        mTv.setText(bean.getTitle());
        mTv2.setText(bean.getContentDescribe());
    }
}
