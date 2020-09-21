package com.example.tongpao.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tongpao.R;
import com.example.tongpao.base.BaseAdapter;
import com.example.tongpao.bean.HomeRecommendBean;
import com.example.tongpao.common.Constants;

import java.util.List;

public class AllCommentAdapter extends BaseAdapter {
    public AllCommentAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.view_custom_layout;
    }

    @Override
    protected void bindData(BaseVirwHolder baseVirwHolder, Object t_data, int position) {

        ImageView img_icon = (ImageView) baseVirwHolder.getViewById(R.id.img_icon);
        TextView txt_tile = (TextView) baseVirwHolder.getViewById(R.id.txt_tile);
        ImageView iv_level = (ImageView) baseVirwHolder.getViewById(R.id.iv_level);
        TextView txt_time = (TextView) baseVirwHolder.getViewById(R.id.txt_time);
        HomeRecommendBean.DataBean.CommentsBean.AllCommentsBean bean = (HomeRecommendBean.DataBean.CommentsBean.AllCommentsBean) t_data;
        Glide.with(context).load(bean.getHeadUrl()).into(img_icon);
        txt_tile.setText(bean.getNickName());
        txt_time.setText(bean.getCreateTime());
        if (bean.getLevel()==1){
            iv_level.setImageResource(R.mipmap.lv1);
        }else if (bean.getLevel()==2){
            iv_level.setImageResource(R.mipmap.lv2);
        }else  if (bean.getLevel()==3){
            iv_level.setImageResource(R.mipmap.lv3);
        }else if (bean.getLevel()==4){
            iv_level.setImageResource(R.mipmap.lv4);
        }else if (bean.getLevel()==5){
            iv_level.setImageResource(R.mipmap.lv5);
        }else  if (bean.getLevel()==6){
            iv_level.setImageResource(R.mipmap.lv6);
        }else if (bean.getLevel()==7){
            iv_level.setImageResource(R.mipmap.lv7);
        }else if (bean.getLevel()==8){
            iv_level.setImageResource(R.mipmap.lv8);
        }
    }
}
