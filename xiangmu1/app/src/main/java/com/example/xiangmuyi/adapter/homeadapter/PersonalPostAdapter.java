package com.example.xiangmuyi.adapter.homeadapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.bean.homebean.PersonalPostBean;

import java.util.List;

public class PersonalPostAdapter extends BaseAdapter {
    private ImageView mDynamicIvHeader;
    private TextView mDynamicFinsh;
    private TextView mDynamicTitle;
    private TextView mDynamicTags;
    private ImageView mDynamicIvTime;
    private TextView mDynamicTvTime;
    private TextView mDynamicApply;
    private ImageView mDynamicIvPlace;
    private TextView mDynamicTvPlace;

    public PersonalPostAdapter(Context context, List data) {
        super(data, context);

    }

    @Override
    protected int getLayout() {
        return R.layout.dynamic_item;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        mDynamicIvHeader = (ImageView) vh.getViewById (R.id.dynamic_iv_header);
        mDynamicFinsh = (TextView) vh.getViewById (R.id.dynamic_finsh);
        mDynamicTitle = (TextView) vh.getViewById (R.id.dynamic_title);
        mDynamicTags = (TextView) vh.getViewById (R.id.dynamic_tags);
        mDynamicIvTime = (ImageView) vh.getViewById (R.id.dynamic_iv_time);
        mDynamicTvTime = (TextView) vh.getViewById (R.id.dynamic_tv_time);
        mDynamicApply = (TextView) vh.getViewById (R.id.dynamic_apply);
        mDynamicIvPlace = (ImageView) vh.getViewById (R.id.dynamic_iv_place);
        mDynamicTvPlace = (TextView) vh.getViewById(R.id.dynamic_tv_place);

        PersonalPostBean.DataBean.ActivitysBean bean = (PersonalPostBean.DataBean.ActivitysBean) data;
        mDynamicTitle.setText(bean.getTitle());
        mDynamicTags.setText(bean.getTags());
        mDynamicTvTime.setText(bean.getStartTime());
        mDynamicApply.setText("已报名"+bean.getApplyUserCount()+"人");
        mDynamicTvPlace.setText(bean.getLocation());
        if (!TextUtils.isEmpty(bean.getCover())) {
            Glide.with(context).load(bean.getCover()).into(mDynamicIvHeader);
        }

        //清理布局中的所有子元素
//        layoutTags.removeAllViews();

        //动态添加
        for (PersonalPostBean.DataBean.ActivitysBean.ColorTagsBean item : bean.getColorTags()) {

            TextView txt = (TextView) LayoutInflater.from(context).inflate(R.layout.layout_tag_status, null);
            txt.setText(item.getTagName());
            txt.setTextColor(Color.parseColor(item.getTagColor()));
//            layoutTags.addView(txt);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMarginEnd(10);
            txt.setLayoutParams(params);
            //动态修改边框的颜色
            GradientDrawable gradientDrawable = (GradientDrawable) txt.getBackground();
            gradientDrawable.setStroke(2, Color.parseColor(item.getTagColor()));
        }


    }
}

