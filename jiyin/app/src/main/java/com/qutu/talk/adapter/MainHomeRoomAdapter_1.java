package com.qutu.talk.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.qutu.talk.R;
import com.qutu.talk.base.BaseDelegateAdapter;
import com.qutu.talk.base.BaseDelegateViewHolder;
import com.qutu.talk.bean.HomeMultipleItem;
import com.qutu.talk.bean.RecommendUser;
import com.qutu.talk.bean.RoomSimpleIntro;
import com.qutu.talk.bean.RoomTypeResult;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import java.util.List;

public class MainHomeRoomAdapter_1 extends BaseDelegateAdapter<RoomSimpleIntro, BaseDelegateViewHolder> {

    int mThreeChildWidth;

    int mSrceenWidth;

    int mTwoChildWidth;

    Context mContext;

    int mStartPosition = 0;

    RoomTypeResult.DataBean mParentBean;

    OnChildViewClickListener mOnClickListener;

    public void setOnClickListener(OnChildViewClickListener onClickListener){
        this.mOnClickListener = onClickListener;
    }


    public MainHomeRoomAdapter_1(Context context,int layoutResId, @Nullable List<RoomSimpleIntro> data, LayoutHelper layoutHelper) {
        super(layoutResId, data, layoutHelper);
        this.mContext = context;
        mSrceenWidth = QMUIDisplayHelper.getScreenWidth(mContext);
        mThreeChildWidth = (mSrceenWidth - QMUIDisplayHelper.dpToPx(40)) / 3;
        mTwoChildWidth = (mSrceenWidth - QMUIDisplayHelper.dpToPx(35)) / 2;
    }

    public void setParentType(RoomTypeResult.DataBean dataBean){
        mParentBean = dataBean;
    }

    public void setStartPosition(int startPosition){
        this.mStartPosition = startPosition;
    }

    @Override
    public void convert(BaseDelegateViewHolder helper, RoomSimpleIntro item) {

        ConstraintLayout rootLayout1 = helper.getView(R.id.csl_item_root);

        ViewGroup.LayoutParams rootParams1 = rootLayout1.getLayoutParams();
//
//        rootParams1.width = mTwoChildWidth;

        ImageView imageView1 = helper.getView(R.id.img_people_head);

        ViewGroup.LayoutParams params1 = imageView1.getLayoutParams();

//        params1.width = mTwoChildWidth;

        int position = helper.getAdapterPosition();

        position = position-mStartPosition;

//        LogUtils.debugInfo("位置+position:"+position,"===="+(position+3)%4);
//            if((position)%4 == 0||(position)%4 == 3){
            if((position+3)%4 == 0 || (position+3)%4 == 3){
//                LogUtils.debugInfo("长度","短的====");
                rootParams1.height = mTwoChildWidth;
                params1.height = mTwoChildWidth;
            } else {
//                LogUtils.debugInfo("长度","长的====");
                rootParams1.height = mTwoChildWidth+QMUIDisplayHelper.dpToPx(30);
                params1.height = mTwoChildWidth+QMUIDisplayHelper.dpToPx(30);
            }

        rootLayout1.setLayoutParams(rootParams1);

        imageView1.setLayoutParams(params1);

        ArmsUtils.obtainAppComponentFromContext(mContext)
                .imageLoader()
                .loadImage(mContext, ImageConfigImpl
                        .builder()
                        .url(item.getRoom_cover())
                        .placeholder(R.mipmap.no_tou)
                        .imageView(helper.getView(R.id.img_people_head))
                        .errorPic(R.mipmap.no_tou)
                        .build());

        helper.setText(R.id.tv_home_room_name, item.getRoom_name());

        if(TextUtils.equals(item.getSex(),"1")){
            helper.setBackgroundRes(R.id.tv_home_room_name, R.drawable.shape_home_room_man_bg);
        } else {
            helper.setBackgroundRes(R.id.tv_home_room_name, R.drawable.shape_home_room_women_bg);
        }

        helper.setText(R.id.tv_hot_count, item.getHot());
        helper.setText(R.id.tv_label, item.getName());

        if(mParentBean != null){
            if(mParentBean.getId().equals("1")){//娱乐
                helper.setBackgroundRes(R.id.tv_label,R.mipmap.icon_label_yule);
            } else if(mParentBean.getId().equals("2")){//语圈
                helper.setBackgroundRes(R.id.tv_label,R.mipmap.icon_lable_yuquan);
            } else if(mParentBean.getId().equals("3")){//文坛
                helper.setBackgroundRes(R.id.tv_label,R.mipmap.icon_label_wentan);
            } else if(mParentBean.getId().equals("-9999")){//房间推荐

                String pid = item.getPid();

                if(pid.equals("1")){//娱乐
                    helper.setBackgroundRes(R.id.tv_label,R.mipmap.icon_label_yule);
                } else if(pid.equals("2")){//语圈
                    helper.setBackgroundRes(R.id.tv_label,R.mipmap.icon_lable_yuquan);
                } else if(pid.equals("3")){//文坛
                    helper.setBackgroundRes(R.id.tv_label,R.mipmap.icon_label_wentan);
                }

            }
        }

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnClickListener != null){
                    mOnClickListener.OnChildClick(view,item);
                }
            }
        });


    }

    public interface OnChildViewClickListener{

        void OnChildClick(View view, RoomSimpleIntro roomSimpleIntro);

    }
}
