package com.example.fenghaogoxiangmu.adapter.special;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fenghaogoxiangmu.R;
import com.example.fenghaogoxiangmu.base.BaseAdapter;
import com.example.fenghaogoxiangmu.bean.special.SpecialBean;

import java.util.List;

public class SpecialAdapter extends BaseAdapter {

    private ImageView mImgTopic;
    private TextView mTxtTopicName;
    private TextView mTxtTopicPrice;

    public SpecialAdapter(List data, Context context) {
        super(data, context);

    }

    @Override
    protected int getLayout() {
        return R.layout.adapter_special_fragment;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        SpecialBean.DataBeanX.DataBean bean = (SpecialBean.DataBeanX.DataBean) data;
        mImgTopic = (ImageView) vh.getViewById(R.id.img_topic);
        mTxtTopicName = (TextView) vh.getViewById(R.id.txt_topic_name);
        mTxtTopicPrice = (TextView) vh.getViewById(R.id.txt_topic_price);
        if(!TextUtils.isEmpty(bean.getScene_pic_url())){
            Glide.with(context).load(bean.getScene_pic_url()).into(mImgTopic);
        }
        mTxtTopicName.setText(bean.getTitle());

    }
}
