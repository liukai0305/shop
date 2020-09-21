package com.example.kaohe.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.kaohe.R;
import com.example.kaohe.base.BaseAdapter;
import com.example.kaohe.bean.LieBiaoBean;

import java.util.List;

public class ImageAdapter extends BaseAdapter {

    public ImageAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_iv;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        ImageView iv = (ImageView) vh.getViewById(R.id.iv);
        LieBiaoBean.DataBean.DynamicsBean.ImagesBean imagesBean = (LieBiaoBean.DataBean.DynamicsBean.ImagesBean) data;
        Glide.with(context).load(imagesBean.getFilePath()).into(iv);

    }
}
