package com.qutu.talk.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qutu.talk.R;
import com.qutu.talk.bean.MiLiSZJiLuBean;
import com.qutu.talk.utils.TimeUtil;

import java.util.List;

public class MiLiSZAdapter extends BaseQuickAdapter<MiLiSZJiLuBean.DataBean, BaseViewHolder> {
    private int type;

    public MiLiSZAdapter(int layoutResId, @Nullable List<MiLiSZJiLuBean.DataBean> data, int type) {
        super(layoutResId, data);
        this.type = type;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MiLiSZJiLuBean.DataBean item) {
        helper.setText(R.id.mili_peo_name, item.getUser_name())
                .setText(R.id.mili_game_name, item.getSkill_name())
                .setText(R.id.mili_game_num, "x " + item.getNum());

        if (type == 2 || type ==3) {
            helper.setText(R.id.mili_time, item.getFinishtime());
            helper.setText(R.id.mili_num, "+ " + item.getReal_price() + "钻石");
        } else {
            helper.setText(R.id.mili_time, item.getAddtime());
            helper.setText(R.id.mili_num, "- " + item.getTotal_price() + "钻石");
        }
        RoundedImageView headImage = helper.getView(R.id.mili_headimg);
        GlideArms.with(mContext)
                .load(item.getHeadimgurl())
                .placeholder(R.mipmap.no_tou)
                .error(R.mipmap.no_tou)
                .circleCrop()
                .into(headImage);
    }
}
