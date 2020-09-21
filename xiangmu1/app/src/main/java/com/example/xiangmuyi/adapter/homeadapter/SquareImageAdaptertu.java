package com.example.xiangmuyi.adapter.homeadapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.xiangmuyi.bean.homebean.HomeSquareBean;

import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class SquareImageAdaptertu extends PagerAdapter {
    public static final String TAG = SquareImageAdaptertu.class.getSimpleName();
    private List<HomeSquareBean.DataBean.DynamicsBean.ImagesBean> imageUrls;
    private AppCompatActivity activity;

    public SquareImageAdaptertu(List<HomeSquareBean.DataBean.DynamicsBean.ImagesBean> imageUrls, AppCompatActivity activity) {
        this.imageUrls = imageUrls;
        this.activity = activity;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        HomeSquareBean.DataBean.DynamicsBean.ImagesBean imagesBean = imageUrls.get(position);
        PhotoView photoView = new PhotoView(activity);
        Glide.with(activity)
                .load(imagesBean.getFilePath())
                .into(photoView);
        container.addView(photoView);
        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                activity.finish();
            }

            @Override
            public void onOutsidePhotoTap() {

            }
        });
        return photoView;
    }

    @Override
    public int getCount() {
        return imageUrls != null ? imageUrls.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
