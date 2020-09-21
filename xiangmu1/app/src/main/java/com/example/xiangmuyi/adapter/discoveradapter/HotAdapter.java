package com.example.xiangmuyi.adapter.discoveradapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.bean.discoverbean.DiscoverHotBean;

import java.util.List;

public class HotAdapter extends BaseAdapter {
    private ImageView mIvRcyHot;
    private TextView mTvTitleHot;
    private ImageView mIvGpsHot;
    private TextView mTvLocationHot;
    private TextView mTvTimeHot;

    public HotAdapter(List data, Context context) {
        super(data, context);

    }

    @Override
    protected int getLayout() {
        return R.layout.item_hot;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        DiscoverHotBean.DataBean dataBean= (DiscoverHotBean.DataBean) data;
        mIvRcyHot = (ImageView) vh.getViewById(R.id.iv_rcy_hot);
        mTvTitleHot = (TextView) vh.getViewById(R.id.tv_title_hot);
        mIvGpsHot = (ImageView) vh.getViewById(R.id.iv_gps_hot);
        mTvLocationHot = (TextView) vh.getViewById(R.id.tv_location_hot);
        mTvTimeHot = (TextView) vh.getViewById(R.id.tv_time_hot);
        Glide.with(context).load(dataBean.getCover()).diskCacheStrategy(DiskCacheStrategy.ALL).into(mIvRcyHot);
        mTvTimeHot.setText(dataBean.getStartTime());
        mTvTitleHot.setText(dataBean.getTitle());
        mTvLocationHot.setText(dataBean.getLocation());
    }
}
