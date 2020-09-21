package com.example.xiangmuyi.adapter.homeadapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.bean.homebean.HomeRewardBean;

import java.util.List;

public class RewardAdapter extends BaseAdapter {
    private ImageView mOfferBun;
    private TextView mOfferMoney;
    private View mOfferView;
    private ImageView mOfferImg;
    private TextView mOfferName;
    private TextView mOfferContent;
    private TextView mOfferTime;
    private TextView mOfferOver;
    private TextView mOfferOfferPart;

    public RewardAdapter(List data, Context context) {
        super(data, context);

    }

    @Override
    protected int getLayout() {
        return R.layout.item_home_xuanshang;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        HomeRewardBean.DataBean.ListBean bean= (HomeRewardBean.DataBean.ListBean) data;
        mOfferBun = (ImageView) vh.getViewById(R.id.offer_bun);
        mOfferMoney = (TextView) vh.getViewById(R.id.offer_money);
        mOfferView = vh.getViewById(R.id.offer_view);
        mOfferImg = (ImageView) vh.getViewById(R.id.offer_img);
        mOfferName = (TextView) vh.getViewById(R.id.offer_name);
        mOfferContent = (TextView) vh.getViewById(R.id.offer_content);
        mOfferTime = (TextView) vh.getViewById(R.id.offer_time);
        mOfferOver = (TextView) vh.getViewById(R.id.offer_over);
        mOfferOfferPart = (TextView) vh.getViewById(R.id.offer_offer_part);
        Glide.with(context).load(bean.getHeadUrl()).into(mOfferImg);
        mOfferName.setText(bean.getNickName());
        mOfferContent.setText(bean.getContent());
        mOfferTime.setText(bean.getCreatetime());
        mOfferOfferPart.setText(bean.getRewardmoney()+"人参加");
        mOfferOver.setText(bean.getSurplusDate());
        mOfferMoney.setText(bean.getRewardmoney()+"铜钱");
    }
}
