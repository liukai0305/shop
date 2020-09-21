package com.example.xiangmuyi.adapter.homeadapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.bean.homebean.HomeSquareBean;

import java.util.List;

public class SquareImageAdapter extends BaseAdapter {
    private ImageView mSquareIvImage;

    public SquareImageAdapter(List data, Context context) {
        super(data, context);

    }

    @Override
    protected int getLayout() {
        return R.layout.item_squareimage;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        mSquareIvImage = (ImageView) vh.getViewById(R.id.square_iv_image);
        HomeSquareBean.DataBean.DynamicsBean.ImagesBean imagesBean = (HomeSquareBean.DataBean.DynamicsBean.ImagesBean) data;
        Glide.with(context).load(imagesBean.getFilePath()).into(mSquareIvImage);
        mSquareIvImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClickItem!=null){
                    onClickItem.onClick(position,mData);
                }
            }
        });
    }



   private OnClickItem onClickItem;

    public void setOnClickItem1(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public interface OnClickItem{
        void onClick(int pos,List<HomeSquareBean.DataBean.DynamicsBean.ImagesBean> bean);
    }

}
