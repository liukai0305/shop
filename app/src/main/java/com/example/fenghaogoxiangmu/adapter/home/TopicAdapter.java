package com.example.fenghaogoxiangmu.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fenghaogoxiangmu.R;
import com.example.fenghaogoxiangmu.base.BaseAdapter;
import com.example.fenghaogoxiangmu.bean.home.HomeBean;
import com.example.fenghaogoxiangmu.ui.home.activitys.HomeBannerActivity;

import java.util.List;

public class TopicAdapter extends BaseAdapter {
    public TopicAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_home_topic;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        HomeBean.DataBean.TopicListBean topicListBean= (HomeBean.DataBean.TopicListBean) data;
        TextView name = (TextView) vh.getViewById(R.id.txt_topic_name);
        if(!TextUtils.isEmpty(topicListBean.getItem_pic_url())){
            Glide.with(context).load(topicListBean.getItem_pic_url()).into((ImageView) vh.getViewById(R.id.img_topic));
        }
        name.setText(topicListBean.getTitle());
    }
}
