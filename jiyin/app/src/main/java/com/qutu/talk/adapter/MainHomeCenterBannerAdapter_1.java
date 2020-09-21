package com.qutu.talk.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.base.BaseDelegateAdapter;
import com.qutu.talk.base.BaseDelegateViewHolder;
import com.qutu.talk.base.BaseWebActivity;
import com.qutu.talk.bean.BannerBean;
import com.qutu.talk.bean.RoomSimpleIntro;
import com.qutu.talk.bean.RoomTypeResult;
import com.qutu.talk.view.GlideImageLoader;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class MainHomeCenterBannerAdapter_1 extends BaseDelegateAdapter<BannerBean, BaseDelegateViewHolder> {

    int mThreeChildWidth;

    int mSrceenWidth;

    int mTwoChildWidth;

    Context mContext;


    public MainHomeCenterBannerAdapter_1(Context context, int layoutResId, @Nullable List<BannerBean> data, LayoutHelper layoutHelper) {
        super(layoutResId, data, layoutHelper);
        this.mContext = context;
        mSrceenWidth = QMUIDisplayHelper.getScreenWidth(mContext);
        mThreeChildWidth = (mSrceenWidth - QMUIDisplayHelper.dpToPx(40)) / 3;
        mTwoChildWidth = (mSrceenWidth - QMUIDisplayHelper.dpToPx(35)) / 2;
    }


    @Override
    public void convert(BaseDelegateViewHolder helper, BannerBean item) {

//        ConstraintLayout rootLayout1 = helper.getView(R.id.csl_item_root);
//
//        ViewGroup.LayoutParams rootParams1 = rootLayout1.getLayoutParams();
//
//        ImageView imageView1 = helper.getView(R.id.img_people_head);
//
//        ViewGroup.LayoutParams params1 = imageView1.getLayoutParams();
//
//
//        int position = helper.getAdapterPosition();
//
//        position = position-mStartPosition;
//
//
//        rootLayout1.setLayoutParams(rootParams1);
//
//        imageView1.setLayoutParams(params1);
//
//        ArmsUtils.obtainAppComponentFromContext(mContext)
//                .imageLoader()
//                .loadImage(mContext, ImageConfigImpl
//                        .builder()
//                        .url(item.getData().get(0).getImg())
//                        .placeholder(R.mipmap.no_tou)
//                        .imageView(helper.getView(R.id.img_people_head))
//                        .errorPic(R.mipmap.no_tou)
//                        .build());
//
//        helper.setText(R.id.tv_home_room_name, item.getRoom_name());

        List<String> imgurls = new ArrayList<>();
        List<BannerBean.DataBean> data = item.getData();
        for (BannerBean.DataBean list : data) {
            imgurls.add(list.getImg());
        }

        Banner banner = helper.getView(R.id.banner_center);

        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(imgurls);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置自动轮播，默认为true
        banner.isAutoPlay(false);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if(item.getData() == null || item.getData().size()==0 || item.getData().get(position) == null || TextUtils.isEmpty(item.getData().get(position).url)){
                    return;
                }
                Intent intent = new Intent(mContext, BaseWebActivity.class);
                intent.putExtra("url", item.getData().get(position).url);
                intent.putExtra("name", "");
                ArmsUtils.startActivity(intent);
            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }
}
