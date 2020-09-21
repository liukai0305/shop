package com.qutu.talk.adapter.dashen;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.activity.my.MyPersonalCenterTwoActivity;
import com.qutu.talk.activity.room.AdminHomeActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.Shengyou;
import com.qutu.talk.bean.dashen.MainHomePageSkillBean;

import java.util.ArrayList;

public class Shengyou1Adapter extends BaseQuickAdapter<Shengyou.DataBean, BaseViewHolder> {
    public Shengyou1Adapter() {
        super(R.layout.item_shengyou_1, new ArrayList<>());
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Shengyou.DataBean item) {

        ArmsUtils.obtainAppComponentFromContext(mContext)
                .imageLoader()
                .loadImage(mContext, ImageConfigImpl
                        .builder()
                        .url(item.headimgurl)
                        .placeholder(R.mipmap.no_tou)
                        .imageView(helper.getView(R.id.main_homepage_skill_img))
                        .errorPic(R.mipmap.no_tou)
                        .build());
        helper.itemView.setOnClickListener(v -> {

            Intent intent1 = new Intent(mContext, MyPersonalCenterTwoActivity.class);
            if (item.id == UserManager.getUser().getUserId()) {
                intent1.putExtra("sign", 0);
                intent1.putExtra("id", "");
                intent1.putExtra("isRoom", false);
            } else {
                intent1.putExtra("sign", 1);
                intent1.putExtra("id", item.id + "");
                intent1.putExtra("isRoom", false);
            }
            ArmsUtils.startActivity(intent1);
        });
    }
}
