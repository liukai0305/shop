package com.example.tongpao.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tongpao.R;
import com.example.tongpao.base.BaseAdapter;
import com.example.tongpao.bean.HomeRecommendBean;
import com.example.tongpao.ui.view.SustomAvaterView;
import com.example.tongpao.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends BaseAdapter {
    private static final int MAX_HEADER_NUMBER = 9;

    public CommentAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.comment_viewite;
    }

    @Override
    protected void bindData(BaseVirwHolder baseVirwHolder, Object t_data, int position) {
        ImageView commed_iv_header = (ImageView) baseVirwHolder.getViewById(R.id.commed_iv_header);
        TextView commed_tv_name = (TextView) baseVirwHolder.getViewById(R.id.commed_tv_name);
        TextView commed_tv_time = (TextView) baseVirwHolder.getViewById(R.id.commed_tv_time);
        ImageView commed_iv_level = (ImageView) baseVirwHolder.getViewById(R.id.commed_iv_level);
        TextView commed_tv_attention = (TextView) baseVirwHolder.getViewById(R.id.commed_tv_attention);
        final TextView commed_tv_text = (TextView) baseVirwHolder.getViewById(R.id.commed_tv_text);
        final TextView comment_tv_like = (TextView) baseVirwHolder.getViewById(R.id.comment_tv_like);
        SustomAvaterView sustomAvaterView = (SustomAvaterView) baseVirwHolder.getViewById(R.id.customvideo);
        final HomeRecommendBean.DataBean.PostDetailBean postDetailBean = (HomeRecommendBean.DataBean.PostDetailBean) t_data;
//        sustomAvaterView.setMaxCount(7);
//        Log.i("bean",postDetailBean.getContent());
//        if(postDetailBean != null && postDetailBean.getLikeDetails().size() > 0){
//            List<String> headers = new ArrayList<>();
//            for(int i=0; i<postDetailBean.getImages().size(); i++){
//                if(i >= MAX_HEADER_NUMBER) break;
//                headers.add(postDetailBean.getLikeDetails().get(i).getHeadUrl());
//            }
//            sustomAvaterView.initDatas(headers);
//        }



        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(context).load(postDetailBean.getHeadUrl()).apply(requestOptions).into(commed_iv_header);
        commed_tv_name.setText(postDetailBean.getNickName());

        Long dateToTime = DateUtils.getDateToTime(((HomeRecommendBean.DataBean.PostDetailBean) t_data).getCreateTime(), null);
        String standardDate = DateUtils.getStandardDate(dateToTime);
        commed_tv_time.setText(standardDate);

        commed_tv_text.setText(postDetailBean.getContent());
        comment_tv_like.setText(postDetailBean.getLikeNumber()+"人点赞 >");
        //九宫格图片
        List<HomeRecommendBean.DataBean.PostDetailBean.ImagesBean> images = postDetailBean.getImages();
        RecyclerView recommend_img_rv = (RecyclerView) baseVirwHolder.getViewById(R.id.recommend_img_rv);
        recommend_img_rv.setLayoutManager(new GridLayoutManager(context,3));
        ImgAdapter imgAdapter = new ImgAdapter(context, images);
        recommend_img_rv.setAdapter(imgAdapter);

        //用户等级
        if (postDetailBean.getLevel()==1){
            commed_iv_level.setImageResource(R.mipmap.lv1);
        }else if (postDetailBean.getLevel()==2){
            commed_iv_level.setImageResource(R.mipmap.lv2);
        }else  if (postDetailBean.getLevel()==3){
            commed_iv_level.setImageResource(R.mipmap.lv3);
        }else if (postDetailBean.getLevel()==4){
            commed_iv_level.setImageResource(R.mipmap.lv4);
        }else if (postDetailBean.getLevel()==5){
            commed_iv_level.setImageResource(R.mipmap.lv5);
        }else  if (postDetailBean.getLevel()==6){
            commed_iv_level.setImageResource(R.mipmap.lv6);
        }else if (postDetailBean.getLevel()==7){
            commed_iv_level.setImageResource(R.mipmap.lv7);
        }else if (postDetailBean.getLevel()==8){
            commed_iv_level.setImageResource(R.mipmap.lv8);
        }
    }
}
