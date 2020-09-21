package com.example.xiangmuyi.adapter.homeadapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.bean.homebean.PersonalMsgBean;

import java.util.List;

public class SheiTuanAdapter extends BaseAdapter {
    private ImageView mImageAssociation;
    private TextView mTvTitleAssociation;
    private TextView mTvCountUserAssociation;
    private TextView mTvCountActivityAssociation;
    private TextView mTvContentAssociation;

    public SheiTuanAdapter(List data, Context context) {
        super(data, context);

    }

    @Override
    protected int getLayout() {
        return R.layout.item_sheituan;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        mImageAssociation = (ImageView) vh.getViewById(R.id.image_association);
        mTvTitleAssociation = (TextView) vh.getViewById(R.id.tv_title_association);
        mTvCountUserAssociation = (TextView) vh.getViewById(R.id.tv_countUser_association);
        mTvCountActivityAssociation = (TextView) vh.getViewById(R.id.tv_countActivity_association);
        mTvContentAssociation = (TextView) vh.getViewById(R.id.tv_content_association);

        PersonalMsgBean.DataBean dataBean= (PersonalMsgBean.DataBean) data;
        Glide.with(context).load(dataBean.getLogo()).into(mImageAssociation);
        mTvTitleAssociation.setText(dataBean.getFullName());
        mTvCountUserAssociation.setText("成员"+dataBean.getCountUser());
        mTvCountActivityAssociation.setText("活动"+dataBean.getCountActivity());
        mTvContentAssociation.setText(dataBean.getNote());

    }
}
