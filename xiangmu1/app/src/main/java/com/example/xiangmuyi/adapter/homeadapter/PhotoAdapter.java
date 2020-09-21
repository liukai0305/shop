package com.example.xiangmuyi.adapter.homeadapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.bean.homebean.HomePhtotBean;
import com.example.xiangmuyi.utils.GildeUtils;

import java.util.List;

public class PhotoAdapter extends BaseAdapter {
    private ImageView mPhotoIvImg;
    private TextView mPhotoTvContent;
    private ImageView mPhotoIvHeader;
    private TextView mPhotoIvName;
    private TextView mPhotoTvLike;

    public PhotoAdapter(List data, Context context) {
        super(data, context);

    }

    @Override
    protected int getLayout() {
        return R.layout.item_photo;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        HomePhtotBean.DataBean.DynamicsBean bean= (HomePhtotBean.DataBean.DynamicsBean) data;
        mPhotoIvImg = (ImageView) vh.getViewById(R.id.photo_iv_img);
        mPhotoTvContent = (TextView) vh.getViewById(R.id.photo_tv_content);
        mPhotoIvHeader = (ImageView) vh.getViewById(R.id.photo_iv_header);
        mPhotoIvName = (TextView) vh.getViewById(R.id.photo_iv_name);
        mPhotoTvLike = (TextView) vh.getViewById(R.id.photo_tv_like);
        Glide.with(context).load(bean.getImages().get(0).getFilePath()).into(mPhotoIvImg);
        mPhotoTvContent.setText(bean.getContent());
        GildeUtils.loadRoundImg(context,bean.getHeadUrl(),mPhotoIvHeader);
        mPhotoIvName.setText(bean.getNickName());
        mPhotoTvLike.setText(bean.getLikeNumber()+"");


    }
}
