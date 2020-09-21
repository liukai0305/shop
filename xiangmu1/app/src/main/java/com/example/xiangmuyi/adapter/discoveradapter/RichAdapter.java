package com.example.xiangmuyi.adapter.discoveradapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.bean.discoverbean.PaiHangBang.ListBean;

import java.util.List;


public class RichAdapter extends BaseAdapter<ListBean> {

    private int type;

    public RichAdapter(List<ListBean> data, Context context) {
        super(data, context);
    }


    public void setType(int type) {
        this.type = type;
    }

    @Override
    protected int getLayout() {
        return R.layout.adapter_rich_sign_level;
    }

    @Override
    protected void bindData(BaseViewHolder baseViewHolder, ListBean listBean, int position) {
        //第一二三名
        ImageView imgLv = (ImageView) baseViewHolder.getViewById(R.id.img_lv);

        //三...
        TextView tvLv = (TextView) baseViewHolder.getViewById(R.id.tv_lv);
        if (position >= 3) {
            tvLv.setText((position + 1) + "");
        }else{
            tvLv.setText("");
        }
        //头像
        ImageView imgRichHead = (ImageView) baseViewHolder.getViewById(R.id.img_rich_head);
        Glide.with(context).load(listBean.getHeadUrl()).apply(RequestOptions.circleCropTransform()).into(imgRichHead);
        //昵称
        TextView tvRichNickName = (TextView) baseViewHolder.getViewById(R.id.tv_rich_nickName);
        tvRichNickName.setText(listBean.getNickName());
        //性别
        TextView tvRichSex = (TextView) baseViewHolder.getViewById(R.id.tv_rich_sex);
        tvRichSex.setText(listBean.getAge() + "");
        //地点
        TextView tvRichLocation = (TextView) baseViewHolder.getViewById(R.id.tv_rich_location);
        tvRichLocation.setText(listBean.getCity());
        //等级
        ImageView imgRichLevel = (ImageView) baseViewHolder.getViewById(R.id.img_rich_level);
       // LevelUtil.setLevelImage(listBean.getLevel(), imgRichLevel);
        //铜钱 ， 经验 ，天数
        TextView tvRichMoney = (TextView) baseViewHolder.getViewById(R.id.tv_rich_money);

        switch (type) {
            case 0:
                tvRichMoney.setText(listBean.getTongQian()+"铜钱");
                break;
            case 1:
                tvRichMoney.setText(listBean.getExpScore()+"经验");
                break;
            case 2:
                if (!TextUtils.isEmpty(listBean.getDays())) {
                    tvRichMoney.setText(listBean.getDays()+"天");
                }
                break;
        }

        if(position==0){
            imgLv.setVisibility(View.VISIBLE);
            imgLv.setImageResource(R.mipmap.ic_back);
            tvRichMoney.setTextColor(Color.BLUE);
        }else if(position==1){
            imgLv.setVisibility(View.VISIBLE);
            imgLv.setImageResource(R.mipmap.ic_back_dark);
            tvRichMoney.setTextColor(Color.BLUE);
        }else if(position==2){
            imgLv.setVisibility(View.VISIBLE);
            imgLv.setImageResource(R.mipmap.ic_commit);
            tvRichMoney.setTextColor(Color.BLUE);
        }else{
            imgLv.setVisibility(View.GONE);
            tvRichMoney.setTextColor(Color.BLACK);
        }


    }
}
