package com.qutu.talk.adapter;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.qutu.talk.R;
import com.qutu.talk.bean.Rank;

import java.util.ArrayList;

/**
 * 接单排行榜
 */
@ActivityScope
public class RankJiedanAdapter extends BaseQuickAdapter<Rank.DataBean.OtherBean, BaseViewHolder> {
    private int type;
    boolean mIsFirstTransion = true;
    BaseViewHolder mHelper;

    public RankJiedanAdapter(int type) {
        super(R.layout.item_rank, new ArrayList<>());
        this.type = type;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Rank.DataBean.OtherBean item) {
        mHelper = helper;
        helper.addOnClickListener(R.id.ci_head);
        helper.setTextColor(R.id.textNum, mContext.getResources().getColor(R.color.main_color));
        helper.setText(R.id.tit, "邀约榜");

        helper.setText(R.id.text1, String.valueOf(helper.getPosition() + 3))
                .setText(R.id.tv_title, item.getNickname())
                .setText(R.id.textNum, item.getExp());
        helper.setText(R.id.tv_family,"所在家族:"+item.getJzname());
        helper.setText(R.id.tv_id,"ID:"+item.getUser_id());
        if (!TextUtils.isEmpty(item.getHeadimgurl())) {
            ArmsUtils.obtainAppComponentFromContext(mContext)
                    .imageLoader()
                    .loadImage(mContext, ImageConfigImpl
                            .builder()
                            .url(item.getHeadimgurl())
                            .placeholder(R.mipmap.no_tou)
                            .imageView(helper.getView(R.id.ci_head))
                            .errorPic(R.mipmap.no_tou)
                            .build());
        }
        if (helper.getPosition() == 1) {
            if (!item.isBg()){
                helper.setBackgroundRes(R.id.item_back,R.drawable.transion_corner_shape);
            }else {
//                helper.setBackgroundRes(R.id.item_back, R.drawable.white_corner_shape);
                helper.setBackgroundRes(R.id.item_back,R.drawable.transion_corner_shape);
            }
        } else {
            helper.setBackgroundRes(R.id.item_back, R.drawable.white_corner_shape_two);
        }
    }

    public void setFirstItemBg(boolean isTransion) {
        LogUtils.debugInfo("方法进来了==3==");
        if (mHelper != null) {
            if (mHelper.getPosition() == 1) {
                if (isTransion) {
                    if (mIsFirstTransion) {
                        mIsFirstTransion = false;
                        mHelper.setBackgroundRes(R.id.item_back, R.drawable.transion_corner_shape);
                        LogUtils.debugInfo("变成透明了");
                        notifyItemChanged(1);
                    }
                } else {
                    if (!mIsFirstTransion) {
                        mIsFirstTransion = true;
//                        mHelper.setBackgroundRes(R.id.item_back, R.drawable.white_corner_shape);
                        mHelper.setBackgroundRes(R.id.item_back,R.drawable.transion_corner_shape);
                        LogUtils.debugInfo("变成白色了");
                        notifyItemChanged(1);
                    }
                }


            } else {
                mHelper.setBackgroundRes(R.id.item_back, R.drawable.white_corner_shape_two);
            }
        }
    }


//    public static class ViewHolder {
//        TextView tv_title, textNum, text1;
//        CircularImage ci_head;
//
//        public ViewHolder(View convertView) {
//
//            tv_title = (TextView) convertView.findViewById(R.id.tv_title);
//            textNum = (TextView) convertView.findViewById(R.id.textNum);
//            ci_head = convertView.findViewById(R.id.ci_head);
//        }
//    }

}