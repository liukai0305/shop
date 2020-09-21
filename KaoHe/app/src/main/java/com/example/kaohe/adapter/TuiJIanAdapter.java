package com.example.kaohe.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kaohe.R;
import com.example.kaohe.base.BaseAdapter;
import com.example.kaohe.bean.TuiJianBean;
import com.example.kaohe.utils.GildeUtils;

import java.util.List;

public class TuiJIanAdapter extends BaseAdapter {

    private ImageView mImageViewHeadUrl;
    private TextView mTextViewNickName;
    private TextView mTextViewCity;
    private ImageView mImageViewFilePathI;
    private ImageView mImageViewFilePathIi;
    private LinearLayout mLinearLayout;

    public TuiJIanAdapter(Context context, List data) {
        super(context, data);

    }

    @Override
    protected int getLayout() {
        return R.layout.item_tuijian;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        mImageViewHeadUrl = (ImageView) vh.getViewById(R.id.ImageView_headUrl);
        mTextViewNickName = (TextView) vh.getViewById(R.id.TextView_nickName);
        mTextViewCity = (TextView) vh.getViewById(R.id.TextView_city);
        mImageViewFilePathI = (ImageView) vh.getViewById(R.id.ImageView_filePath_i);
        mImageViewFilePathIi = (ImageView) vh.getViewById(R.id.ImageView_filePath_ii);
        mLinearLayout = (LinearLayout) vh.getViewById(R.id.LinearLayout);
        TuiJianBean.DataBean bean = (TuiJianBean.DataBean) data;
        GildeUtils.loadRoundImg(context, bean.getHeadUrl(), mImageViewHeadUrl);
        mTextViewNickName.setText(bean.getNickName());
        mTextViewCity.setText(bean.getCity());
        GildeUtils.loadImg(context,bean.getFileBeanList().get(0).getFilePath(),mImageViewFilePathI);
        GildeUtils.loadImg(context,bean.getFileBeanList().get(1).getFilePath(),mImageViewFilePathIi);

    }
}
