package com.example.xiangmuyi.adapter.discoveradapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.bean.discoverbean.shetuan.SheTuanBean;
import com.example.xiangmuyi.utils.GildeUtils;

import java.util.List;

public class SheTuanAdapter extends BaseAdapter {
    private ImageView mSheIv;
    private TextView mSheName;
    private TextView mSheCheng;
    private TextView mSheHuo;
    private TextView mSheJian;

    public SheTuanAdapter(List data, Context context) {
        super(data, context);

    }

    @Override
    protected int getLayout() {
        return R.layout.item_shetuan;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        mSheIv = (ImageView) vh.getViewById(R.id.she_iv);
        mSheName = (TextView) vh.getViewById(R.id.she_name);
        mSheCheng = (TextView) vh.getViewById(R.id.she_cheng);
        mSheHuo = (TextView) vh.getViewById(R.id.she_huo);
        mSheJian = (TextView) vh.getViewById(R.id.she_jian);
        SheTuanBean.DataBean.ListBean bean= (SheTuanBean.DataBean.ListBean) data;
        GildeUtils.loadRoundImg(context,bean.getLogo(),mSheIv);
        mSheName.setText(bean.getFullName());
        mSheCheng.setText("社团"+bean.getCountUser());
        mSheHuo.setText("活动"+bean.getCountActivity());
        mSheJian.setText(bean.getNote());

    }
}
