package com.qutu.talk.adapter.dashen;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qutu.talk.R;
import com.qutu.talk.bean.ShengyouQuanbutop;

import java.util.List;

public class Shengyou2_Adapter extends BaseQuickAdapter<ShengyouQuanbutop.DataBean, BaseViewHolder> {
    public Shengyou2_Adapter(List<ShengyouQuanbutop.DataBean> mDataList1) {
        super(R.layout.item_shengyou, mDataList1);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ShengyouQuanbutop.DataBean item) {
        helper.setText(R.id.biaoqian, item.getName())
                .setText(R.id.room_biaoqian, item.getRoom_name())
//                .setText(R.id.id, item.getHot())
//                .setText(R.id.hot, item.getHot())
        ;

//        if (TextUtils.isEmpty(item.getHost())) {
//            helper.setText(R.id.name, "主持：暂无主持");
//        } else {
//            helper.setText(R.id.name, "主持：" + item.getHost());
//        }

        if (1==item.getIs_afk()) {
            helper.setText(R.id.tv_status, "在线");
        } else {
            helper.setText(R.id.tv_status, "离线");
        }


        RoundedImageView headImg = helper.getView(R.id.head_img);
        if (!TextUtils.isEmpty(item.getRoom_cover())) {
            ArmsUtils.obtainAppComponentFromContext(mContext)
                    .imageLoader()
                    .loadImage(mContext, ImageConfigImpl
                            .builder()
                            .url(item.getRoom_cover())
                            .placeholder(R.mipmap.no_tu)
                            .imageView(headImg)
                            .errorPic(R.mipmap.no_tu)
                            .build());
        }

        ImageView labelImg = helper.getView(R.id.biaoqian_img);
//        if (!TextUtils.isEmpty(item.getCate_img())) {
//            ArmsUtils.obtainAppComponentFromContext(mContext)
//                    .imageLoader()
//                    .loadImage(mContext, ImageConfigImpl
//                            .builder()
//                            .url(item.getCate_img())
//                            .placeholder(R.mipmap.no_tu)
//                            .imageView(labelImg)
//                            .errorPic(R.mipmap.no_tu)
//                            .build());
//        }
    }
}
