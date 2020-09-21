package com.example.xiangmuyi.adapter.homeadapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.bean.homebean.HomeRecommendBean;
import com.example.xiangmuyi.common.Constants;
import com.example.xiangmuyi.ui.Users.Activitys.PerInfoActivity;
import com.example.xiangmuyi.ui.home.activity.PhotoViewActivity;
import com.example.xiangmuyi.utils.DateUtils;
import com.example.xiangmuyi.utils.SystemUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecommendAdapter extends BaseAdapter {
    private ImageView mRecommedIvHeader;
    private TextView mRecommedTvName;
    private TextView mRecommedTvTime;
    private ImageView mRecommedIvLevel;
    private TextView mRecommedTvAttention;
    private TextView mRecommedTvText;
    private RecyclerView mRecommendImgRv;
    private ImageView mRecommendIvPraise;
    private ImageView mRecommendIvComment;
    private TextView mRecommendTvPraise;
    private TextView mRecommendTvComment;
    private TextView tvexpand;
    private TextView putaway;
    Boolean flag = true;

    public RecommendAdapter(List data, Context context) {
        super(data, context);

    }

    @Override
    protected int getLayout() {
        return R.layout.item_recommend;
    }


    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        mRecommedIvHeader = (ImageView) vh.getViewById(R.id.recommed_iv_header);
        mRecommedTvName = (TextView) vh.getViewById(R.id.recommed_tv_name);
        mRecommedTvTime = (TextView) vh.getViewById(R.id.recommed_tv_time);
        mRecommedIvLevel = (ImageView) vh.getViewById(R.id.recommed_iv_level);
        mRecommedTvAttention = (TextView) vh.getViewById(R.id.recommed_tv_attention);
        mRecommedTvText = (TextView) vh.getViewById(R.id.recommed_tv_text);
        mRecommendImgRv = (RecyclerView) vh.getViewById(R.id.recommend_img_rv);
        mRecommendTvPraise = (TextView) vh.getViewById(R.id.recommend_tv_praise);
        mRecommendTvComment = (TextView) vh.getViewById(R.id.recommend_tv_comment);
        tvexpand = (TextView) vh.getViewById(R.id.tv_expand);
        putaway = (TextView) vh.getViewById(R.id.tv_putaway);
        HomeRecommendBean.DataBean.PostDetailBean detailBean = (HomeRecommendBean.DataBean.PostDetailBean) data;
        int round = SystemUtils.px2Dp(context, 50);
        RoundedCorners roundedCorners = new RoundedCorners(round);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
        Glide.with(context).load(detailBean.getHeadUrl()).apply(options).into(mRecommedIvHeader);
        mRecommedIvHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PerInfoActivity.class);
                context.startActivity(intent);
            }
        });
        if (!TextUtils.isEmpty(detailBean.getNickName())) {
            mRecommedTvName.setText(detailBean.getNickName());
        }

        //显示时间
        if (!TextUtils.isEmpty(detailBean.getCreateTime())) {
            Long time = DateUtils.getDateToTime(detailBean.getCreateTime(), null);
            String dateStr = DateUtils.getStandardDate(time);
            mRecommedTvTime.setText(dateStr);

        }
        //内容的显示 ##包裹起来数据  @符号后面的数据
        String content = detailBean.getContent();
        SpannableString mSpannableString = new SpannableString(content);
        //第一层切割
        String exp_1 = "#[^#]*#";
        Pattern pattern = Pattern.compile(exp_1);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            //使用富文本修改 内容中的文字颜色
            mSpannableString.setSpan(new ForegroundColorSpan(Color.BLUE), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        }
        String exp_2 = "@.+?\\s";
        Pattern pattern2 = Pattern.compile(exp_2);
        Matcher matcher2 = pattern2.matcher(content);
        while (matcher2.find()) {
            int start1 = matcher2.start();
            int end1 = matcher2.end();
            //使用富文本修改 内容中的文字颜色
            mSpannableString.setSpan(new ForegroundColorSpan(Color.BLUE), start1, end1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        }
        mRecommedTvText.setText(mSpannableString);
        tvexpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = false;
                mRecommedTvText.setEllipsize(null);
                mRecommedTvText.setSingleLine(flag);
                tvexpand.setVisibility(View.GONE);
                putaway.setVisibility(View.VISIBLE);
            }
        });
        putaway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = true;
                mRecommedTvText.setLines(3);
                putaway.setVisibility(View.GONE);
                tvexpand.setVisibility(View.VISIBLE);
            }
        });

        List<HomeRecommendBean.DataBean.PostDetailBean.ImagesBean> imgsList = detailBean.getImages();
        ImageAdapter imageAdapter = new ImageAdapter(imgsList, context);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        mRecommendImgRv.setLayoutManager(gridLayoutManager);
        mRecommendImgRv.setAdapter(imageAdapter);
        mRecommendTvPraise.setText(detailBean.getLikeNumber() + "");
        mRecommendTvComment.setText(detailBean.getCommentNumber() + "");
        imageAdapter.setOnClickItem(new ImageAdapter.OnClickItem() {
            @Override
            public void onClick(int pos, List<HomeRecommendBean.DataBean.PostDetailBean.ImagesBean> bean) {
                if( Constants.IMG_DATA!=null&&Constants.IMG_DATA.size()>0){

                    Constants.IMG_DATA.clear();
                }
                Constants.IMG_DATA.addAll(bean);
                Constants.INDEX = pos;

                Intent intent = new Intent(context, PhotoViewActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
