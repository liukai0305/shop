package com.example.xiangmuyi.adapter.homeadapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.bean.homebean.PersonalActivityBean;

import java.util.List;

public class PersonalImgAdapter extends BaseAdapter {

    public PersonalImgAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_rcy_zi;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        ImageView iv = (ImageView) vh.getViewById(R.id.iv);
        PersonalActivityBean.DataBean.DynamicsBean.ImagesBean imagesBean = (PersonalActivityBean.DataBean.DynamicsBean.ImagesBean) data;
        Glide.with(context).load(imagesBean.getFilePath()).into(iv);

        }
    }

