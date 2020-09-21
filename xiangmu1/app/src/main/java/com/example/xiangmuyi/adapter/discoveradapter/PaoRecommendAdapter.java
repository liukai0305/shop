package com.example.xiangmuyi.adapter.discoveradapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.bean.discoverbean.paozibean.PaoRecommendBean;
import com.example.xiangmuyi.utils.GildeUtils;

import java.util.List;

public class PaoRecommendAdapter extends BaseAdapter {
    private ImageView mRobeHead;
    private TextView mRobeSocialTitle;
    private TextView mRobeName;
    private TextView mRobeSex;
    private TextView mRobePlace;

    public PaoRecommendAdapter(List data, Context context) {
        super(data, context);

    }

    @Override
    protected int getLayout() {
        return R.layout.item_pao_recommend;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        mRobeHead = (ImageView) vh.getViewById(R.id.robe_head);
        mRobeSocialTitle = (TextView) vh.getViewById(R.id.robe_socialTitle);
        mRobeName = (TextView) vh.getViewById(R.id.robe_name);
        mRobeSex = (TextView) vh.getViewById(R.id.robe_sex);
        mRobePlace = (TextView) vh.getViewById(R.id.robe_place);
        PaoRecommendBean.DataBean bean= (PaoRecommendBean.DataBean) data;
        GildeUtils.loadRoundImg(context,bean.getHeadUrl(),mRobeHead);
        mRobeName.setText(bean.getNickName());
        mRobeSex.setText(bean.getSex());
        mRobeSocialTitle.setText(bean.getSocialTitle());
        mRobePlace.setText(bean.getProvince());
    }
}
