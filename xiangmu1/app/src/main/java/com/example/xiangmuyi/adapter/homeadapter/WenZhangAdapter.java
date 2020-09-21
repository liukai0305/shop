package com.example.xiangmuyi.adapter.homeadapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.bean.homebean.PersonalArticleBean;

import java.util.List;

public class WenZhangAdapter extends BaseAdapter {
    private ImageView mWenzhangIv;
    private TextView mWenzhangSaishi1;
    private TextView mWenzhangSaishi2;
    private TextView mWanzhangName;
   private TextView mWanzhangShijian;

    public WenZhangAdapter(List data, Context context) {
        super(data, context);



    }

    @Override
    protected int getLayout() {
        return R.layout.item_wenzhang;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        mWenzhangIv = (ImageView) vh.getViewById(R.id.wenzhang_iv);
        mWenzhangSaishi1 = (TextView) vh.getViewById(R.id.wenzhang_saishi1);
        mWenzhangSaishi2 = (TextView) vh.getViewById(R.id.wenzhang_saishi2);
        mWanzhangName = (TextView) vh.getViewById(R.id.wanzhang_name);
        mWanzhangShijian = (TextView) vh.getViewById(R.id.wanzhang_shijian);
        PersonalArticleBean.DataBean.ArticlesBean articlesBean = (PersonalArticleBean.DataBean.ArticlesBean) data;
        Glide.with(context).load(articlesBean.getCover()).into(mWenzhangIv);
        mWenzhangSaishi1.setText(articlesBean.getTitle());
        mWenzhangSaishi2.setText(articlesBean.getContentDescribe());
        mWanzhangName.setText(articlesBean.getNickName());
        mWanzhangShijian.setText(articlesBean.getCreateTime());
    }
}
