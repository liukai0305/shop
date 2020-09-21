package com.example.fenghaogoxiangmu.adapter.classify;

import android.content.Context;
import android.widget.TextView;

import com.example.fenghaogoxiangmu.R;
import com.example.fenghaogoxiangmu.base.BaseAdapter;
import com.example.fenghaogoxiangmu.bean.classify.ClassifyBean;

import java.util.List;

public class ClassifyAdapter extends BaseAdapter {
    private TextView mTvShijain;


    public ClassifyAdapter(List data, Context context) {
        super(data, context);

    }

    @Override
    protected int getLayout() {
        return R.layout.adapter_classify_shuxiang;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {

        ClassifyBean.DataBean.CategoryListBean bean = (ClassifyBean.DataBean.CategoryListBean) data;
        mTvShijain = (TextView) vh.getViewById(R.id.tv_shijain);
        mTvShijain.setText(bean.getName());

    }
}
