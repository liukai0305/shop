package com.example.tongpao.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tongpao.R;
import com.example.tongpao.base.BaseAdapter;
import com.example.tongpao.bean.HomeRecommendBean;
import com.example.tongpao.bean.PersonalActivityBean;

import java.util.List;

public class PersonalImgAdapter extends BaseAdapter {
    public PersonalImgAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.personal_iv_img;
    }

    @Override
    protected void bindData(BaseVirwHolder baseVirwHolder, Object t_data, final int position) {
        ImageView grid_iv_img = (ImageView) baseVirwHolder.getViewById(R.id.personal_iv_imgs);
        final PersonalActivityBean.DataBean.DynamicsBean.ImagesBean imagesBean = (PersonalActivityBean.DataBean.DynamicsBean.ImagesBean) t_data;
        Glide.with(context).load(imagesBean.getFilePath()).into(grid_iv_img);

    }
}
