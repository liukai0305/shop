package com.example.fenghaogoxiangmu.adapter.home.activity;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fenghaogoxiangmu.R;
import com.example.fenghaogoxiangmu.base.BaseAdapter;
import com.example.fenghaogoxiangmu.bean.home.activitybean.GoodDetailBean;
import com.example.fenghaogoxiangmu.bean.home.activitybean.TopicPingLBean;

import java.util.List;

public class TopicPingLunAdapter extends BaseAdapter {
    private ImageView mImgIvAdapter;
    private TextView mTvShijainAdapter;
    private TextView mTvNameAdapter;

    public TopicPingLunAdapter(List data, Context context) {
        super(data, context);

    }

    @Override
    protected int getLayout() {
        return R.layout.item_tiopic_pinglun;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
       TopicPingLBean.DataBeanX.DataBean bean= (TopicPingLBean.DataBeanX.DataBean) data;
        mImgIvAdapter = (ImageView) vh.getViewById(R.id.img_iv_adapter);
        mTvShijainAdapter = (TextView) vh.getViewById(R.id.tv_shijain_adapter);
        mTvNameAdapter = (TextView) vh.getViewById(R.id.tv_name_adapter);
        mTvShijainAdapter.setText(bean.getAdd_time());
        mTvNameAdapter.setText(bean.getContent());

    }

    @Override
    public int getItemCount() {
        if (mData.size()>=5){
            return 5;
        }else {
            return mData.size();
        }
    }
}
