package com.example.fenghaogoxiangmu.adapter.classify.fragment;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fenghaogoxiangmu.R;
import com.example.fenghaogoxiangmu.base.BaseAdapter;
import com.example.fenghaogoxiangmu.bean.classify.ClassifyBean;

import java.util.List;

public class VoerAdapter extends BaseAdapter {


    private ImageView mImg;
    private TextView mText;

    public VoerAdapter(List data, Context context) {
        super(data, context);

    }

    @Override
    protected int getLayout() {
        return R.layout.vore_adapter;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        ClassifyBean.DataBean.CurrentCategoryBean.SubCategoryListBean bean= (ClassifyBean.DataBean.CurrentCategoryBean.SubCategoryListBean) data;
        mImg = (ImageView) vh.getViewById(R.id.img);
        mText = (TextView) vh.getViewById(R.id.text);
        mText.setText(bean.getName());
        Glide.with(context).load(bean.getBanner_url()).into(mImg);
    }
}
