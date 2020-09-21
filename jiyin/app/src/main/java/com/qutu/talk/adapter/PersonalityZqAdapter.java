package com.qutu.talk.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.qutu.talk.R;
import com.qutu.talk.bean.PersonalityBean;

import java.util.ArrayList;

public class PersonalityZqAdapter extends BaseQuickAdapter<PersonalityBean.DataBean.ZqBean, BaseViewHolder> {

    public PersonalityZqAdapter() {
        super(R.layout.item_personality_zq, new ArrayList<>());
    }

    private int selectPosition=-1;

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
        notifyDataSetChanged();
    }

    public int getSelectPosition() {
        return selectPosition;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, PersonalityBean.DataBean.ZqBean item) {
        helper.setText(R.id.tv_price,item.getPrice());
        GlideArms.with(mContext)
                .load(item.getImage())
                .placeholder(R.mipmap.no_tou)
                .error(R.mipmap.no_tou)
                //.circleCrop()
                .into((ImageView) helper.getView(R.id.iv_image));
        helper.addOnClickListener(R.id.tv_test);
        helper.setText(R.id.tv_name,item.getName());
        if(helper.getLayoutPosition()==selectPosition){
            helper.getView(R.id.ll_parent).setBackgroundResource(R.drawable.personality_border_sel);
        }else{
            helper.getView(R.id.ll_parent).setBackgroundResource(R.drawable.personality_border);
        }
        helper.addOnClickListener(R.id.ll_parent);

    }
}
