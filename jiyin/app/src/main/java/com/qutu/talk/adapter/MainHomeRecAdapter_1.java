package com.qutu.talk.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.qutu.talk.R;
import com.qutu.talk.base.BaseDelegateAdapter;
import com.qutu.talk.base.BaseDelegateViewHolder;
import com.qutu.talk.bean.RecommendUser;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import java.util.List;

public class MainHomeRecAdapter_1 extends BaseDelegateAdapter<RecommendUser, BaseDelegateViewHolder> {

    Context mContext;
    int mThreeChildWidth;
    int mSrceenWidth;

    OnChildViewClickListener mOnClickListener;

    public MainHomeRecAdapter_1(Context context,int layoutResId, @Nullable List<RecommendUser> data, LayoutHelper layoutHelper) {
        super(layoutResId, data, layoutHelper);
        this.mContext = context;
        mSrceenWidth = QMUIDisplayHelper.getScreenWidth(mContext);
        mThreeChildWidth = (mSrceenWidth - QMUIDisplayHelper.dpToPx(40)) / 3;
    }

    public void setOnClickListener(OnChildViewClickListener onClickListener){
        this.mOnClickListener = onClickListener;
    }

    @Override
    public void convert(BaseDelegateViewHolder helper, RecommendUser item) {

        ImageView imageView = helper.getView(R.id.img_people_head);

//        int size = getItemCount();

//        if(size<3){

            ViewGroup.LayoutParams params1 = imageView.getLayoutParams();

            params1.height = mThreeChildWidth;
            params1.width = mThreeChildWidth;

            imageView.setLayoutParams(params1);
//        }

        ArmsUtils.obtainAppComponentFromContext(mContext)
                .imageLoader()
                .loadImage(mContext, ImageConfigImpl
                        .builder()
                        .url(item.getHeadimgurl())
                        .placeholder(R.mipmap.no_tou)
                        .imageView(helper.getView(R.id.img_people_head))
                        .errorPic(R.mipmap.no_tou)
                        .build());

        TextView tvAttention = helper.getView(R.id.tv_to_attention);

        if (item.getIs_follow() == 0) {
            helper.setText(R.id.tv_to_attention, "关注");
            helper.setVisible(R.id.tv_to_attention, true);
        } else {
            helper.setText(R.id.tv_to_attention, "已关注");
            helper.setVisible(R.id.tv_to_attention, false);
        }

        helper.setText(R.id.tv_people_name, item.getNickname());

        tvAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnClickListener != null){
                    int layoutPosition = helper.getLayoutPosition();
                    int adapterPosition = helper.getAdapterPosition();
                    LogUtils.debugInfo("layoutPosition==="+layoutPosition);
                    LogUtils.debugInfo("adapterPosition==="+adapterPosition);
                    mOnClickListener.OnChildClick(view,item);
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnClickListener != null){
                    mOnClickListener.OnChildClick(view,item);
                }
            }
        });




    }

    public interface OnChildViewClickListener{

        void OnChildClick(View view,RecommendUser recommendUser);

    }
}
