package com.example.tongpao.adapter;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tongpao.R;
import com.example.tongpao.base.BaseAdapter;
import com.example.tongpao.bean.HomeRecommendBean;
import com.example.tongpao.common.Constants;
import com.example.tongpao.ui.activity.CommentActivity;
import com.example.tongpao.ui.activity.LoginActivity;
import com.example.tongpao.ui.home.personal.PersonalActivity;
import com.example.tongpao.ui.activity.VpActivity;
import com.example.tongpao.utils.DateUtils;

import java.util.List;

public class RecommendAdapter extends BaseAdapter {
    private static final String TAG = "RecommendAdapter";
    public RecommendAdapter(Context context, List data) {
        super(context, data);
    }
    Boolean flag = true;
    @Override
    protected int getLayout() {
        return R.layout.recomomend_item;
    }

    @Override
    protected void bindData(BaseVirwHolder baseVirwHolder, Object t_data, final int position) {
        ImageView recommed_iv_header = (ImageView) baseVirwHolder.getViewById(R.id.recommed_iv_header);
        TextView recommed_tv_name = (TextView) baseVirwHolder.getViewById(R.id.recommed_tv_name);
        TextView recommed_tv_time = (TextView) baseVirwHolder.getViewById(R.id.recommed_tv_time);
        ImageView recommed_iv_level = (ImageView) baseVirwHolder.getViewById(R.id.recommed_iv_level);
        TextView recommed_tv_attention = (TextView) baseVirwHolder.getViewById(R.id.recommed_tv_attention);
        final TextView recommed_tv_text = (TextView) baseVirwHolder.getViewById(R.id.recommed_tv_text);
        final TextView tv_expand = (TextView) baseVirwHolder.getViewById(R.id.tv_expand);
        final TextView tv_putaway = (TextView) baseVirwHolder.getViewById(R.id.tv_putaway);
        TextView recommend_tv_like = (TextView) baseVirwHolder.getViewById(R.id.recommend_tv_like);
        TextView recommend_iv_comment = (TextView) baseVirwHolder.getViewById(R.id.recommend_tv_comment);
        //关注的点击事件
        recommed_tv_attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            }
        });
        //点赞的点击事件
        recommend_tv_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            }
        });
        //全文的点击事件
        tv_expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=false;
                recommed_tv_text.setEllipsize(null);
                recommed_tv_text.setSingleLine(flag);
                tv_expand.setVisibility(View.GONE);
                tv_putaway.setVisibility(View.VISIBLE);
            }
        });
        //收起的点击事件
        tv_putaway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=true;
                recommed_tv_text.setLines(3);
                tv_putaway.setVisibility(View.GONE);
                tv_expand.setVisibility(View.VISIBLE);
            }
        });

          final HomeRecommendBean.DataBean.PostDetailBean postDetailBean = (HomeRecommendBean.DataBean.PostDetailBean) t_data;

        //头像圆形
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(context).load(postDetailBean.getHeadUrl()).apply(requestOptions).into(recommed_iv_header);
        //设置时间
        Long dateToTime = DateUtils.getDateToTime(((HomeRecommendBean.DataBean.PostDetailBean) t_data).getCreateTime(), null);
        String standardDate = DateUtils.getStandardDate(dateToTime);
        recommed_tv_time.setText(standardDate);
        //设置点赞人数和评论人数
        recommend_tv_like.setText(postDetailBean.getLikeNumber()+"");
        recommend_iv_comment.setText(postDetailBean.getCommentNumber()+"");
        //设置名字和文本内容
        recommed_tv_name.setText(postDetailBean.getNickName());
        recommed_tv_text.setText(postDetailBean.getContent());
        //评论的点击事件
        Log.d(TAG, "bindData: "+postDetailBean);
        recommend_iv_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.curClickPost = postDetailBean;
                Intent intent = new Intent(context, CommentActivity.class);
                context.startActivity(intent);
            }
        });
        recommed_iv_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PersonalActivity.class);
                context.startActivity(intent);
            }
        });

        //九宫格图片
        final List<HomeRecommendBean.DataBean.PostDetailBean.ImagesBean> images = postDetailBean.getImages();
        RecyclerView recommend_img_rv = (RecyclerView) baseVirwHolder.getViewById(R.id.recommend_img_rv);
        recommend_img_rv.setLayoutManager(new GridLayoutManager(context,3));
        ImgAdapter imgAdapter = new ImgAdapter(context, images);
        recommend_img_rv.setAdapter(imgAdapter);

        imgAdapter.setClick(new IClick() {
            @Override
            public void click(int pos) {
                Constants.imagesBean = images;
                Intent intent = new Intent(context, VpActivity.class);
                intent.putExtra("pos",pos);
                context.startActivity(intent);
            }
        });


        //用户等级
        if (postDetailBean.getLevel()==1){
            recommed_iv_level.setImageResource(R.mipmap.lv1);
        }else if (postDetailBean.getLevel()==2){
            recommed_iv_level.setImageResource(R.mipmap.lv2);
        }else  if (postDetailBean.getLevel()==3){
            recommed_iv_level.setImageResource(R.mipmap.lv3);
        }else if (postDetailBean.getLevel()==4){
            recommed_iv_level.setImageResource(R.mipmap.lv4);
        }else if (postDetailBean.getLevel()==5){
            recommed_iv_level.setImageResource(R.mipmap.lv5);
        }else  if (postDetailBean.getLevel()==6){
            recommed_iv_level.setImageResource(R.mipmap.lv6);
        }else if (postDetailBean.getLevel()==7){
            recommed_iv_level.setImageResource(R.mipmap.lv7);
        }else if (postDetailBean.getLevel()==8){
            recommed_iv_level.setImageResource(R.mipmap.lv8);
        }
    }

}
