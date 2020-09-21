package com.example.xiangmuyi.adapter.homeadapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.bean.homebean.PersonalActivityBean;
import com.example.xiangmuyi.utils.DateUtils;

import java.util.List;

public class DongTaiAdapter extends BaseAdapter {
    private ImageView mRecommedIvHeader;
    private TextView mRecommedTvName;
    private TextView mRecommedTvTime;
    private ImageView mRecommedIvLevel;
    private TextView mRecommedTvAttention;
    private TextView mRecommedTvText;
    private TextView mTvExpand;
    private TextView mTvPutaway;
    private RecyclerView mRecommendImgRv;
    private TextView mRecommendTvPraise;
    private TextView mRecommendTvComment;
    Boolean flag = true;

    public DongTaiAdapter(List data, Context context) {
        super(data, context);

    }

    @Override
    protected int getLayout() {
        return R.layout.item_dongtai;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        mRecommedIvHeader = (ImageView) vh.getViewById(R.id.recommed_iv_header);
        mRecommedTvName = (TextView) vh.getViewById(R.id.recommed_tv_name);
        mRecommedTvTime = (TextView) vh.getViewById(R.id.recommed_tv_time);
        mRecommedIvLevel = (ImageView) vh.getViewById(R.id.recommed_iv_level);
        mRecommedTvAttention = (TextView) vh.getViewById(R.id.recommed_tv_attention);
        mRecommedTvText = (TextView) vh.getViewById(R.id.recommed_tv_text);
        mTvExpand = (TextView) vh.getViewById(R.id.tv_expand);
        mTvPutaway = (TextView) vh.getViewById(R.id.tv_putaway);
        mRecommendImgRv = (RecyclerView) vh.getViewById(R.id.recommend_img_rv);
        mRecommendTvPraise = (TextView) vh.getViewById(R.id.recommend_tv_praise);
        mRecommendTvComment = (TextView) vh.getViewById(R.id.recommend_tv_comment);
        PersonalActivityBean.DataBean.DynamicsBean dynamicsBean = (PersonalActivityBean.DataBean.DynamicsBean) data;
        //头像圆型
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(context).load(dynamicsBean.getHeadUrl()).apply(requestOptions).into(mRecommedIvHeader);
        //设置名字
        mRecommedTvName.setText(dynamicsBean.getNickName());
        //设置时间
        Long dateToTime = DateUtils.getDateToTime(((PersonalActivityBean.DataBean.DynamicsBean) data).getCreateTime(), null);
        String standardDate = DateUtils.getStandardDate(dateToTime);
        mRecommedTvTime.setText(standardDate);
        //用户等级
        if (dynamicsBean.getLevel()==1){
            mRecommedIvLevel.setImageResource(R.mipmap.lv1);
        }else if (dynamicsBean.getLevel()==2){
            mRecommedIvLevel.setImageResource(R.mipmap.lv2);
        }else  if (dynamicsBean.getLevel()==3){
            mRecommedIvLevel.setImageResource(R.mipmap.lv3);
        }else if (dynamicsBean.getLevel()==4){
            mRecommedIvLevel.setImageResource(R.mipmap.lv4);
        }else if (dynamicsBean.getLevel()==5){
            mRecommedIvLevel.setImageResource(R.mipmap.lv5);
        }else  if (dynamicsBean.getLevel()==6){
            mRecommedIvLevel.setImageResource(R.mipmap.lv6);
        }else if (dynamicsBean.getLevel()==7){
            mRecommedIvLevel.setImageResource(R.mipmap.lv7);
        }else if (dynamicsBean.getLevel()==8){
            mRecommedIvLevel.setImageResource(R.mipmap.lv8);
        }
        //文本内容
        mRecommedTvText.setText(dynamicsBean.getContent());
//        //图片

        List<PersonalActivityBean.DataBean.DynamicsBean.ImagesBean> filePath = dynamicsBean.getImages();
        mRecommendImgRv.setLayoutManager(new GridLayoutManager(context,3));
        PersonalImgAdapter imgAdapter = new PersonalImgAdapter(filePath,context);
        mRecommendImgRv.setAdapter(imgAdapter);

        //全文的点击事件
        mTvExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=false;
                mRecommedTvText.setEllipsize(null);
                mRecommedTvText.setSingleLine(flag);
                mTvExpand.setVisibility(View.GONE);
                mTvPutaway.setVisibility(View.VISIBLE);
            }
        });
        //收起的点击事件
        mTvPutaway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=true;
                mRecommedTvText.setLines(3);
                mTvPutaway.setVisibility(View.GONE);
                mTvExpand.setVisibility(View.VISIBLE);
            }
        });
    }
    }

