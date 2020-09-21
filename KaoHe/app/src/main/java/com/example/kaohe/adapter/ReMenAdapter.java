package com.example.kaohe.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.kaohe.R;
import com.example.kaohe.base.BaseAdapter;
import com.example.kaohe.bean.LieBiaoBean;
import com.example.kaohe.bean.ReMenBean;
import com.example.kaohe.utils.GildeUtils;

import java.util.List;

public class ReMenAdapter extends BaseAdapter {
    private ImageView mMImg;
    private TextView mMTitle;
    private TextView mNumber;
    private CardView mMCard;

    public ReMenAdapter(Context context, List data) {
        super(context, data);

    }

    @Override
    protected int getLayout() {
        return R.layout.item_remen;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        mMImg = (ImageView) vh.getViewById(R.id.mImg);
        mMTitle = (TextView) vh.getViewById(R.id.mTitle);
        mNumber = (TextView) vh.getViewById(R.id.number);
        mMCard = (CardView) vh.getViewById(R.id.mCard);
        ReMenBean.DataBean bean= (ReMenBean.DataBean) data;
        RoundedCorners roundedCorners = new RoundedCorners(10);
        RequestOptions override = RequestOptions.bitmapTransform(roundedCorners).override(100, 100);
        Glide.with(context).load(bean.getImageUrl()).apply(override).into(mMImg);
        mMTitle.setText("#"+bean.getName()+"#");
        mNumber.setText(bean.getUseTime()+"参与");
        if(position>=0||position<=2){
            mMImg.setVisibility(View.VISIBLE);
        }
        if (position % 3 == 0) {
           mMCard.setCardBackgroundColor(Color.argb(1f, 255, 197, 197));
        } else if (position % 3 == 1) {
            mMCard.setCardBackgroundColor(Color.argb(1f, 188, 255, 155));
        } else {
            mMCard.setCardBackgroundColor(Color.argb(1f, 122, 120, 255));
        }
    }
}
