package com.example.xiangmuyi.adapter.discoveradapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.bean.discoverbean.paozibean.PaoNearbyBean;
import com.example.xiangmuyi.utils.GildeUtils;

import java.util.List;

public class PaoNearbyAdapter extends BaseAdapter {
    private ImageView mPaoNearbyIv;
    private TextView mPaoNearbyName;
    private ImageView mPaoNearbyIvji;
    private ImageView mIv;
    private TextView mPaoNearbyWen;

    public PaoNearbyAdapter(List data, Context context) {
        super(data, context);

    }

    @Override
    protected int getLayout() {
        return R.layout.item_pap_nearby;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        PaoNearbyBean.DataBean.ListBean bean= (PaoNearbyBean.DataBean.ListBean) data;
        mPaoNearbyIv = (ImageView) vh.getViewById(R.id.pao_nearby_iv);
        mPaoNearbyName = (TextView) vh.getViewById(R.id.pao_nearby_name);
        mPaoNearbyIvji = (ImageView) vh.getViewById(R.id.pao_nearby_ivji);
        mIv = (ImageView) vh.getViewById(R.id.iv);
        mPaoNearbyWen = (TextView) vh.getViewById(R.id.pao_nearby_wen);
        GildeUtils.loadRoundImg(context,bean.getHeadUrl(),mPaoNearbyIv);
        mPaoNearbyName.setText(bean.getNickName());
        mPaoNearbyWen.setText(bean.getSignature());
    }
}
