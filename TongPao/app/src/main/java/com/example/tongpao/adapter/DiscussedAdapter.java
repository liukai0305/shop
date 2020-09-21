package com.example.tongpao.adapter;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.tongpao.R;
import com.example.tongpao.base.BaseAdapter;
import com.example.tongpao.bean.HomeTopicDiscussedBean;

import java.util.List;

public class DiscussedAdapter extends BaseAdapter {
    public DiscussedAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.discussed_item;
    }

    @Override
    protected void bindData(BaseVirwHolder baseVirwHolder, Object t_data, int position) {
        ImageView discussed_iv_img = (ImageView) baseVirwHolder.getViewById(R.id.discussed_iv_img);
        TextView discussed_tv_text = (TextView) baseVirwHolder.getViewById(R.id.discussed_tv_text);
        TextView num_tv_text = (TextView) baseVirwHolder.getViewById(R.id.num_tv_text);
        ConstraintLayout constra = (ConstraintLayout) baseVirwHolder.getViewById(R.id.constra);
        TextView discussec_activity = (TextView) baseVirwHolder.getViewById(R.id.discussec_activity);
        HomeTopicDiscussedBean.DataBean dataBean = (HomeTopicDiscussedBean.DataBean) t_data;

        Glide.with(context).load(dataBean.getImageUrl()).into(discussed_iv_img);
        discussed_tv_text.setText("#"+dataBean.getName()+"#");
        num_tv_text.setText(dataBean.getAttentionNum()+"人参与");
        if (dataBean.getType()==1){
            discussec_activity.setVisibility(View.VISIBLE);
        }
        if (position%3==0){
            constra.setBackgroundResource(R.color.colorwhite);
        }else if (position%3==1){
            constra.setBackgroundResource(R.color.colortwo);
        }else {
            constra.setBackgroundResource(R.color.colorthree);
        }
    }


}
