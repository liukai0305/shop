package com.example.xiangmuyi.adapter.homeadapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.bean.homebean.HomeRecommendBean;
import com.example.xiangmuyi.utils.DateUtils;
import com.example.xiangmuyi.utils.SystemUtils;

import java.util.List;

public class PingLunAdapter extends BaseAdapter {
    private ImageView mRecommedIvHeader;
    private TextView mRecommedTvName;
    private TextView mRecommedTvTime;
    private ImageView mRecommedIvLevel;
    private TextView mRecommedTvAttention;
    private TextView mRecommedTvText;

    public PingLunAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_pinglun;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        mRecommedIvHeader = (ImageView) vh.getViewById(R.id.recommed_iv_header);
        mRecommedTvName = (TextView) vh.getViewById(R.id.recommed_tv_name);
        mRecommedTvTime = (TextView) vh.getViewById(R.id.recommed_tv_time);
        mRecommedIvLevel = (ImageView) vh.getViewById(R.id.recommed_iv_level);
        mRecommedTvAttention = (TextView) vh.getViewById(R.id.recommed_tv_attention);
        mRecommedTvText = (TextView) vh.getViewById(R.id.recommed_tv_text);
        HomeRecommendBean.DataBean.CommentsBean.AllCommentsBean detailBean = (HomeRecommendBean.DataBean.CommentsBean.AllCommentsBean) data;
        int round = SystemUtils.px2Dp(context, 50);
        RoundedCorners roundedCorners = new RoundedCorners(round);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
        Glide.with(context).load(detailBean.getHeadUrl()).apply(options).into(mRecommedIvHeader);
        if (!TextUtils.isEmpty(detailBean.getNickName())) {
            mRecommedTvName.setText(detailBean.getNickName());
        }
        //显示时间
        if (!TextUtils.isEmpty(detailBean.getCreateTime())) {
            Long time = DateUtils.getDateToTime(detailBean.getCreateTime(), null);
            String dateStr = DateUtils.getStandardDate(time);
            mRecommedTvTime.setText(dateStr);
        }
        mRecommedTvText.setText(detailBean.getComment());
        mRecommedTvAttention.setText(detailBean.getLevel()+"");
    }
}
