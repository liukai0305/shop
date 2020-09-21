package com.example.fenghaogoxiangmu.adapter.home.activity;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fenghaogoxiangmu.R;
import com.example.fenghaogoxiangmu.base.BaseAdapter;
import com.example.fenghaogoxiangmu.bean.home.activitybean.GoodDetailBean;

import java.util.List;

public class HomeHotAdapter extends BaseAdapter {
    private ImageView mImgHotAdapter;
    private TextView mTvNameAdapter;
    private TextView mTvMoneyAdapter;

    public HomeHotAdapter(List data, Context context) {
        super(data, context);

    }

    @Override
    protected int getLayout() {
        return R.layout.item_adapter_homehot;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        GoodDetailBean.DataBeanX.InfoBean bean= (GoodDetailBean.DataBeanX.InfoBean) data;
        mImgHotAdapter = (ImageView) vh.getViewById(R.id.img_hot_adapter);
        mTvNameAdapter = (TextView) vh.getViewById(R.id.tv_name_adapter);
        mTvMoneyAdapter = (TextView) vh.getViewById(R.id.tv_money_adapter);
        mTvNameAdapter.setText(bean.getName());
        mTvMoneyAdapter.setText("$"+bean.getRetail_price());
        Glide.with(context).load(bean.getPrimary_pic_url()).into(mImgHotAdapter);
    }
}
