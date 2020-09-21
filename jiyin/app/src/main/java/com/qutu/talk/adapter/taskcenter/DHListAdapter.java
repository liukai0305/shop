package com.qutu.talk.adapter.taskcenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.bean.task.ExchangeListBean;

import java.util.ArrayList;
import java.util.List;

public class DHListAdapter extends BaseQuickAdapter<ExchangeListBean.DataBeanX.DataBean, BaseViewHolder> {
    private Context mContext;

    public DHListAdapter(Context context) {
        super(R.layout.dh_list_item, new ArrayList<>());
        this.mContext = context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ExchangeListBean.DataBeanX.DataBean item) {

        helper.setText(R.id.show_text, item.getName())
                .setText(R.id.dh_price, item.getJinbi() + "钻石")
                .addOnClickListener(R.id.dh_btn);

        ArmsUtils.obtainAppComponentFromContext(mContext)
                .imageLoader()
                .loadImage(mContext, ImageConfigImpl
                        .builder()
                        .url(item.getShow_img())
                        .placeholder(R.mipmap.no_tu)
                        .imageView(helper.getView(R.id.show_img))
                        .errorPic(R.mipmap.no_tu)
                        .build());

    }
}
