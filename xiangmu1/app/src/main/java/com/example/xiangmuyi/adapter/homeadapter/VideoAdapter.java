package com.example.xiangmuyi.adapter.homeadapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.bean.homebean.HomeVideoBean;
import com.example.xiangmuyi.utils.GildeUtils;
import com.example.xiangmuyi.utils.TVUtils;

import java.util.List;

public class VideoAdapter extends BaseAdapter {
    private ImageView mImgCover;
    private TextView mTxtTitle;
    private ImageView mImgHead;
    private TextView mTxtUsername;
    private TextView mTxtLive;

    public VideoAdapter(List data, Context context) {
        super(data, context);

    }

    @Override
    protected int getLayout() {
        return R.layout.item_video;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        HomeVideoBean.DataBean.ListBean bean= (HomeVideoBean.DataBean.ListBean) data;
        mImgCover = (ImageView) vh.getViewById(R.id.img_cover);
        mTxtTitle = (TextView) vh.getViewById(R.id.txt_title);
        mImgHead = (ImageView) vh.getViewById(R.id.img_head);
        mTxtUsername = (TextView) vh.getViewById(R.id.txt_username);
        mTxtLive = (TextView) vh.getViewById(R.id.txt_live);
        GildeUtils.loadImg(context,bean.getCover(),mImgCover);
        TVUtils.setText(mTxtTitle,bean.getContent());
        GildeUtils.loadRoundImg(context,bean.getHeadUrl(),mImgHead);
        TVUtils.setText(mTxtUsername,bean.getNickName() );
        TVUtils.setText(mTxtLive,bean.getLevel());
    }
}
