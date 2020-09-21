package com.example.xiangmuyi.adapter.homeadapter.zhishi;

import android.content.Context;
import android.widget.TextView;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.zhishibean.HomeEventshanfuBean;

import java.util.List;

public class DaShiAdapter extends BaseAdapter {
    private TextView mTvName;
    private TextView mTvJieshao;

    public DaShiAdapter(List data, Context context) {
        super(data, context);

    }

    @Override
    protected int getLayout() {
        return R.layout.item_zhishi_yan;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        mTvName = (TextView) vh.getViewById(R.id.tv_name);
        mTvJieshao = (TextView) vh.getViewById(R.id.tv_jieshao);
        HomeEventshanfuBean.DataBean dataBean= (HomeEventshanfuBean.DataBean) data;
        mTvName.setText(dataBean.getTitle());
        mTvJieshao.setText(dataBean.getContentDescribe());
    }
}
