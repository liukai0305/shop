package com.example.tongpao.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tongpao.R;
import com.example.tongpao.base.BaseAdapter;
import com.example.tongpao.bean.PersonalMsgBean;

import java.util.List;

public class PersonalMsgAdapter extends BaseAdapter {
    public PersonalMsgAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.personalmsg_item;
    }

    @Override
    protected void bindData(BaseVirwHolder baseVirwHolder, Object t_data, int position) {
        ImageView personal_iv_header = (ImageView) baseVirwHolder.getViewById(R.id.personal_iv_header);
        TextView personal_tv_name = (TextView) baseVirwHolder.getViewById(R.id.personal_tv_name);
        ImageView personal_iv_level = (ImageView) baseVirwHolder.getViewById(R.id.personal_iv_level);
        TextView personal_tv_attention = (TextView) baseVirwHolder.getViewById(R.id.personal_tv_attention);
        TextView personal_tv_hold = (TextView) baseVirwHolder.getViewById(R.id.personal_tv_hold);
        TextView personal_tv_group = (TextView) baseVirwHolder.getViewById(R.id.personal_tv_group);
        TextView personal_attention_number = (TextView) baseVirwHolder.getViewById(R.id.personal_attention_number);
        TextView personal_vermicell_number = (TextView) baseVirwHolder.getViewById(R.id.personal_vermicell_number);
        TextView personal_empirical_value = (TextView) baseVirwHolder.getViewById(R.id.personal_empirical_value);
        PersonalMsgBean.DataBean dataBean = (PersonalMsgBean.DataBean) t_data;
        // 头像
        Glide.with(context).load(dataBean.getHeadUrl()).into(personal_iv_header);
        personal_tv_name.setText(dataBean.getNickName());
        //用户等级
        if (dataBean.getLevel() == 1) {
            personal_iv_level.setImageResource(R.mipmap.lv1);
        } else if (dataBean.getLevel() == 2) {
            personal_iv_level.setImageResource(R.mipmap.lv2);
        } else if (dataBean.getLevel() == 3) {
            personal_iv_level.setImageResource(R.mipmap.lv3);
        } else if (dataBean.getLevel() == 4) {
            personal_iv_level.setImageResource(R.mipmap.lv4);
        } else if (dataBean.getLevel() == 5) {
            personal_iv_level.setImageResource(R.mipmap.lv5);
        } else if (dataBean.getLevel() == 6) {
            personal_iv_level.setImageResource(R.mipmap.lv6);
        } else if (dataBean.getLevel() == 7) {
            personal_iv_level.setImageResource(R.mipmap.lv7);
        } else if (dataBean.getLevel() == 8) {
            personal_iv_level.setImageResource(R.mipmap.lv8);
        }
        //qq群
        personal_tv_group.setText(dataBean.getSignature());
        personal_attention_number.setText("\t" + dataBean.getMyContact() + "\n" + "关注");
        personal_vermicell_number.setText(+dataBean.getContactMe() + "\n" + "粉丝");
        personal_empirical_value.setText("\t" + dataBean.getExpScore() + "\n" + "经验值");
    }
}
