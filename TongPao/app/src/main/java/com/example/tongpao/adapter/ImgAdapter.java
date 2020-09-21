package com.example.tongpao.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tongpao.R;
import com.example.tongpao.base.BaseAdapter;
import com.example.tongpao.bean.HomeRecommendBean;
import com.example.tongpao.common.Constants;
import com.example.tongpao.ui.activity.VpActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImgAdapter extends BaseAdapter {
    public ImgAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.recomomend_iv_img;
    }

    @Override
    protected void bindData(BaseVirwHolder baseVirwHolder, Object t_data, final int position) {
        ImageView grid_iv_img = (ImageView) baseVirwHolder.getViewById(R.id.grid_iv_img);
        final HomeRecommendBean.DataBean.PostDetailBean.ImagesBean imagesBean = (HomeRecommendBean.DataBean.PostDetailBean.ImagesBean) t_data;
        Glide.with(context).load(imagesBean.getFilePath()).into(grid_iv_img);

    }
}
