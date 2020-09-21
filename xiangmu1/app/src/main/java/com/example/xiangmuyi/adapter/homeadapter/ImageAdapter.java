package com.example.xiangmuyi.adapter.homeadapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.bean.homebean.HomeRecommendBean;

import java.util.List;

public class ImageAdapter extends BaseAdapter {

    public ImageAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_rcy_zi;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        ImageView iv = (ImageView) vh.getViewById(R.id.iv);
        HomeRecommendBean.DataBean.PostDetailBean.ImagesBean imagesBean = (HomeRecommendBean.DataBean.PostDetailBean.ImagesBean) data;
        Glide.with(context).load(imagesBean.getFilePath()).into(iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClickItem!=null){
                    onClickItem.onClick(position,mData);
                }
            }
        });
    }



    OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public interface OnClickItem{
        void onClick(int pos,List<HomeRecommendBean.DataBean.PostDetailBean.ImagesBean> bean);
    }

}
