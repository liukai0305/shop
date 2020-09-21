package com.qutu.talk.adapter.dashen;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.bean.dashen.GodCenterBean;
import com.qutu.talk.view.StarBar;

import java.util.List;

public class GodCenterAdapter extends BaseQuickAdapter<GodCenterBean.DataBean.CommentsBean, BaseViewHolder> {
    public GodCenterAdapter(int layoutResId, @Nullable List<GodCenterBean.DataBean.CommentsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GodCenterBean.DataBean.CommentsBean item) {

        if (helper.getPosition() == (getData().size())) {
            helper.getView(R.id.god_center_line).setVisibility(View.GONE);
        }

        ArmsUtils.obtainAppComponentFromContext(mContext)
                .imageLoader()
                .loadImage(mContext, ImageConfigImpl
                        .builder()
                        .url(item.getHeadimgurl())
                        .placeholder(R.mipmap.default_home)
                        .imageView(helper.getView(R.id.god_center_item_headimg))
                        .errorPic(R.mipmap.default_home)
                        .build());

        helper.setText(R.id.god_center_item_name, item.getNickname())
                .setText(R.id.god_center_item_num, item.getStar())
                .setText(R.id.god_center_item_time, item.getAddtime())
                .setText(R.id.god_center_content, item.getContent());

        StarBar starBar = helper.getView(R.id.starBar);
        starBar.setStarMark(Float.parseFloat(item.getStar()));
        starBar.setEnabled(false);
    }
}
