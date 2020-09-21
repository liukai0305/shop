package com.example.xiangmuyi.adapter.homeadapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.bean.homebean.HomeArticleBean;

import java.util.List;

public class ArticleAdapter extends BaseAdapter {
    private ImageView mXuanIv;
    private TextView mXuanSaishi1;
    private TextView mXuanSaishi2;
    private TextView mXuanName;
    private TextView mXuanShijian;

    public ArticleAdapter(List data, Context context) {
        super(data, context);

    }

    @Override
    protected int getLayout() {
        return R.layout.item_artoicle;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        mXuanIv = (ImageView) vh.getViewById(R.id.xuan_iv);
        mXuanSaishi1 = (TextView) vh.getViewById(R.id.xuan_saishi1);
        mXuanSaishi2 = (TextView) vh.getViewById(R.id.xuan_saishi2);
        mXuanName = (TextView) vh.getViewById(R.id.xuan_name);
        mXuanShijian = (TextView) vh.getViewById(R.id.xuan_shijian);
        HomeArticleBean.DataBean.ArticlesBean bean= (HomeArticleBean.DataBean.ArticlesBean) data;
        Glide.with(context).load(bean.getCover()).into(mXuanIv);
        mXuanSaishi1.setText(bean.getTitle());
        mXuanSaishi2.setText(bean.getContentDescribe());
        mXuanName.setText(bean.getNickName());
        mXuanShijian.setText(bean.getCreateTime());
    }
}
