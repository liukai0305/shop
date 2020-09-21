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
import com.qutu.talk.bean.BestRoomResult;
import com.qutu.talk.bean.RecommendUser;
import com.qutu.talk.bean.RoomSimpleIntro;
import com.qutu.talk.bean.RoomTypeResult;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import java.util.List;

public class MainHomeBestRoomAdapter extends BaseDelegateAdapter<BestRoomResult.DataBean, BaseDelegateViewHolder> {

    int mThreeChildWidth;

    int mSrceenWidth;

//    int mTwoChildWidth;

    Context mContext;

    OnChildViewClickListener mOnClickListener;

    public void setOnClickListener(OnChildViewClickListener onClickListener){
        this.mOnClickListener = onClickListener;
    }

    public MainHomeBestRoomAdapter(Context context, int layoutResId, @Nullable List<BestRoomResult.DataBean> data, LayoutHelper layoutHelper) {
        super(layoutResId, data, layoutHelper);
        this.mContext = context;
        mSrceenWidth = QMUIDisplayHelper.getScreenWidth(mContext);
        mThreeChildWidth = (mSrceenWidth - QMUIDisplayHelper.dpToPx(40)) / 3;
//        mTwoChildWidth = (mSrceenWidth - QMUIDisplayHelper.dpToPx(35)) / 2;
    }

    @Override
    public void convert(BaseDelegateViewHolder helper, BestRoomResult.DataBean item) {

//        ConstraintLayout rootLayout1 = helper.getView(R.id.csl_item_root);
//
//        ViewGroup.LayoutParams rootParams1 = rootLayout1.getLayoutParams();
//

        helper.setText(R.id.tv_hot_count_union, item.getHot());

        ImageView imageView1 = helper.getView(R.id.img_people_head);

//        int size = getItemCount();

//        if(size<3){

            ViewGroup.LayoutParams params1 = imageView1.getLayoutParams();

            params1.height = mThreeChildWidth;
            params1.width = mThreeChildWidth;

            imageView1.setLayoutParams(params1);
//        }

        ArmsUtils.obtainAppComponentFromContext(mContext)
                .imageLoader()
                .loadImage(mContext, ImageConfigImpl
                        .builder()
                        .url(item.getRoom_cover())
                        .placeholder(R.mipmap.no_tou)
                        .imageView(imageView1)
                        .errorPic(R.mipmap.no_tou)
                        .build());

        helper.setText(R.id.tv_union_name, item.getRoom_name());

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

        void OnChildClick(View view, BestRoomResult.DataBean recommendUser);

    }
}
