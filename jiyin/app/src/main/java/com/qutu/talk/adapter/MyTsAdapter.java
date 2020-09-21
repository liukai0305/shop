package com.qutu.talk.adapter;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.qutu.talk.R;
import com.qutu.talk.bean.MyDress;
import com.qutu.talk.bean.PersonalityBean;

import java.util.ArrayList;

public class MyTsAdapter extends BaseQuickAdapter<MyDress.DataBean.TsinfoBean, BaseViewHolder> {

    public MyTsAdapter() {
        super(R.layout.item_my_ts, new ArrayList<>());
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MyDress.DataBean.TsinfoBean item) {
        GlideArms.with(mContext)
                .load(item.getImage())
                .placeholder(R.mipmap.no_tou)
                .error(R.mipmap.no_tou)
                .circleCrop()
                .into((ImageView) helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_name,item.getTsname());
        if(item.getStatus()==1){
            helper.getView(R.id.ll_parent).setBackgroundResource(R.drawable.personality_border_sel);
            helper.setText(R.id.tv_status,"正在使用");
            helper.setTextColor(R.id.tv_status,0xffee0092);
        }else{
            helper.getView(R.id.ll_parent).setBackgroundResource(R.drawable.personality_border);
            helper.setText(R.id.tv_status,"未使用");
            helper.setTextColor(R.id.tv_status,0xffBAB9B9);
        }
        helper.addOnClickListener(R.id.ll_parent);
    }
}
