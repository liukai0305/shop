package com.example.fenghaogoxiangmu.adapter.home.activity;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fenghaogoxiangmu.R;
import com.example.fenghaogoxiangmu.base.BaseAdapter;
import com.example.fenghaogoxiangmu.bean.home.activitybean.TopicTuiJBean;

import java.util.List;

public class TopicLieBioaAdapter extends BaseAdapter {
    private TextView mTvAdapter;
    private ImageView mImgAdapter;
    private TextView mTvName;

    public TopicLieBioaAdapter(List data, Context context) {
        super(data, context);

    }

    @Override
    protected int getLayout() {
        return R.layout.item_adapter_liebiao;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        TopicTuiJBean.DataBean dataBean= (TopicTuiJBean.DataBean) data;
        mTvAdapter = (TextView) vh.getViewById(R.id.tv_adapter);
        mImgAdapter = (ImageView) vh.getViewById(R.id.img_adapter);
        mTvName = (TextView) vh.getViewById(R.id.tv_name);
        mTvName.setText(dataBean.getTitle());
        Glide.with(context).load(dataBean.getScene_pic_url()).into(mImgAdapter);
    }
}
