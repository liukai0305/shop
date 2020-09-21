package com.qutu.talk.adapter.dashen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.bean.dashen.MainHomePageBean;

import java.util.List;

public class DaShenListAdapter extends BaseQuickAdapter<MainHomePageBean.DataBean, BaseViewHolder> {
    private Context mContext;
    private String mUserId;

    public DaShenListAdapter(int layoutResId, @Nullable List<MainHomePageBean.DataBean> data, Context context, String id) {
        super(layoutResId, data);
        this.mContext = context;
        this.mUserId = id;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MainHomePageBean.DataBean item) {
        helper.addOnClickListener(R.id.skill_list_sure).addOnClickListener(R.id.da_shen_item);

        if (mUserId.equals(String.valueOf(item.getUser_id()))) {
            helper.getView(R.id.skill_list_sure).setVisibility(View.INVISIBLE);
        } else {
            helper.getView(R.id.skill_list_sure).setVisibility(View.VISIBLE);
        }

        if (item.getIsOnline() == 0) {
            helper.getView(R.id.zaixian).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.zaixian).setVisibility(View.VISIBLE);
        }

        helper.setText(R.id.skill_list_name, item.getNickname())
                .setText(R.id.skill_list_level, item.getLevel_name())
                .setText(R.id.skill_list_score, "评分：" + item.getScore() + "     " + "接单量：" + item.getNum())
                .setText(R.id.skill_list_price, item.getPrice() + "金币/" + item.getUnit());

        ArmsUtils.obtainAppComponentFromContext(mContext)
                .imageLoader()
                .loadImage(mContext, ImageConfigImpl
                        .builder()
                        .url(item.getHeadimgurl())
                        .placeholder(R.mipmap.no_tu)
                        .imageView(helper.getView(R.id.skill_list_img))
                        .errorPic(R.mipmap.no_tu)
                        .build());

        if (helper.getPosition() == (getData().size() - 1)) {
            helper.getView(R.id.skill_list_line).setVisibility(View.GONE);
        }
    }
}
