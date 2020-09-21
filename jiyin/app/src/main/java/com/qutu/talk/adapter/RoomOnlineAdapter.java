package com.qutu.talk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.bean.GetOnlineUserResult;
import com.qutu.talk.bean.RecommendUser;
import com.qutu.talk.bean.RoomRankBean;

import java.util.ArrayList;
import java.util.List;

public class RoomOnlineAdapter extends BaseQuickAdapter<GetOnlineUserResult.DataBean.UsersBean, BaseViewHolder> {

    Context mContext;
    public RoomOnlineAdapter(@Nullable List<GetOnlineUserResult.DataBean.UsersBean> data,Context context) {
        super(R.layout.item_room_user_online, data);
        this.mContext = context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GetOnlineUserResult.DataBean.UsersBean item) {

        ArmsUtils.obtainAppComponentFromContext(mContext)
                .imageLoader()
                .loadImage(mContext, ImageConfigImpl
                        .builder()
                        .url(item.getHeadimgurl())
                        .placeholder(R.mipmap.no_tou)
                        .imageView(helper.getView(R.id.img_head))
                        .errorPic(R.mipmap.no_tou)
                        .build());

        ArmsUtils.obtainAppComponentFromContext(mContext)
                .imageLoader()
                .loadImage(mContext, ImageConfigImpl
                        .builder()
                        .url(item.getVip_img())
                        .imageView(helper.getView(R.id.img_vip_level))
                        .build());

        helper.setText(R.id.tv_user_name, item.getNickname());

        helper.setText(R.id.tv_user_id, "ID:  "+item.getId());

        helper.setGone(R.id.img_status, true);

        int sort = item.getSort();

        if(sort ==0){
            helper.setImageResource(R.id.img_status, R.mipmap.room_fz);
        } else if(sort==1){
            helper.setImageResource(R.id.img_status, R.mipmap.room_gly);
        } else {
            if(item.getIs_mic().equals("1")){
                helper.setImageResource(R.id.img_status, R.mipmap.room_smz);
            } else {
                helper.setGone(R.id.img_status, false);
            }
        }

        helper.addOnClickListener(R.id.item_layout_user);
    }
}
