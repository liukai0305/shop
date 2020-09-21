package com.example.tongpao.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.tongpao.bean.PersonalActivityBean;
import com.example.tongpao.ui.activity.LoginActivity;
import com.example.tongpao.utils.DateUtils;

import java.util.List;

public class PersonalActivityAdapter extends BaseAdapter {
    Boolean flag = true;
    public PersonalActivityAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.personalactivity_item;
    }

    @Override
    protected void bindData(BaseVirwHolder baseVirwHolder, Object t_data, int position) {
        ImageView personal_activity_header = (ImageView) baseVirwHolder.getViewById(R.id.personal_activity_header);
        TextView personal_activity_name = (TextView) baseVirwHolder.getViewById(R.id.personal_activity_name);
        TextView personal_activity_time = (TextView) baseVirwHolder.getViewById(R.id.personal_activity_time);
        ImageView personal_activity_level = (ImageView) baseVirwHolder.getViewById(R.id.personal_activity_level);
        final TextView personal_activity_text = (TextView) baseVirwHolder.getViewById(R.id.personal_activity_text);
        RecyclerView personal_rv = (RecyclerView) baseVirwHolder.getViewById(R.id.personal_rv);
        final TextView personal_expand = (TextView) baseVirwHolder.getViewById(R.id.personal_expand);
        final TextView personal_putaway = (TextView) baseVirwHolder.getViewById(R.id.personal_putaway);

        PersonalActivityBean.DataBean.DynamicsBean dynamicsBean = (PersonalActivityBean.DataBean.DynamicsBean) t_data;
        //头像圆型
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(context).load(dynamicsBean.getHeadUrl()).apply(requestOptions).into(personal_activity_header);
        //设置名字
        personal_activity_name.setText(dynamicsBean.getNickName());
        //设置时间
        Long dateToTime = DateUtils.getDateToTime(((PersonalActivityBean.DataBean.DynamicsBean) t_data).getCreateTime(), null);
        String standardDate = DateUtils.getStandardDate(dateToTime);
        personal_activity_time.setText(standardDate);
        //用户等级
        if (dynamicsBean.getLevel()==1){
            personal_activity_level.setImageResource(R.mipmap.lv1);
        }else if (dynamicsBean.getLevel()==2){
            personal_activity_level.setImageResource(R.mipmap.lv2);
        }else  if (dynamicsBean.getLevel()==3){
            personal_activity_level.setImageResource(R.mipmap.lv3);
        }else if (dynamicsBean.getLevel()==4){
            personal_activity_level.setImageResource(R.mipmap.lv4);
        }else if (dynamicsBean.getLevel()==5){
            personal_activity_level.setImageResource(R.mipmap.lv5);
        }else  if (dynamicsBean.getLevel()==6){
            personal_activity_level.setImageResource(R.mipmap.lv6);
        }else if (dynamicsBean.getLevel()==7){
            personal_activity_level.setImageResource(R.mipmap.lv7);
        }else if (dynamicsBean.getLevel()==8){
            personal_activity_level.setImageResource(R.mipmap.lv8);
        }
        //文本内容
        personal_activity_text.setText(dynamicsBean.getContent());
//        //图片

        List<PersonalActivityBean.DataBean.DynamicsBean.ImagesBean> filePath = dynamicsBean.getImages();
        personal_rv.setLayoutManager(new GridLayoutManager(context,3));
        PersonalImgAdapter imgAdapter = new PersonalImgAdapter(context, filePath);
        personal_rv.setAdapter(imgAdapter);

        //全文的点击事件
        personal_expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=false;
                personal_activity_text.setEllipsize(null);
                personal_activity_text.setSingleLine(flag);
                personal_expand.setVisibility(View.GONE);
                personal_putaway.setVisibility(View.VISIBLE);
            }
        });
        //收起的点击事件
        personal_putaway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=true;
                personal_activity_text.setLines(3);
                personal_putaway.setVisibility(View.GONE);
                personal_expand.setVisibility(View.VISIBLE);
            }
        });
    }
}
