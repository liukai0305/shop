package com.example.xiangmuyi.adapter.homeadapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.bean.homebean.HomePhtotBean;

import java.util.List;

public class PhotoImageAdapter extends BaseAdapter {
    private ImageView mIv;

    public PhotoImageAdapter(List data, Context context) {
        super(data, context);

    }

    @Override
    protected int getLayout() {
        return R.layout.item_photoimage;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        HomePhtotBean.DataBean.DynamicsBean.ImagesBean imagesBean = (HomePhtotBean.DataBean.DynamicsBean.ImagesBean) data;
        mIv = (ImageView) vh.getViewById(R.id.iv);
        Glide.with(context).load(imagesBean.getFilePath()).into(mIv);
    }
}
