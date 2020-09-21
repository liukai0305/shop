package com.example.tongpao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.tongpao.bean.HomeRecommendBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import uk.co.senab.photoview.PhotoView;

public class VpAdapter extends PagerAdapter {
    List<HomeRecommendBean.DataBean.PostDetailBean.ImagesBean> imagesBeans;
    AppCompatActivity context;
    public VpAdapter(AppCompatActivity context,List<HomeRecommendBean.DataBean.PostDetailBean.ImagesBean> imagesBeans ) {
        this.context = context;
        this.imagesBeans = imagesBeans;
    }

    @Override
    public int getCount() {
        return imagesBeans!=null?imagesBeans.size():0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (View) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        HomeRecommendBean.DataBean.PostDetailBean.ImagesBean imagesBean = imagesBeans.get(position);
        PhotoView photoView = new PhotoView(context);
        Glide.with(context).load(imagesBean.getFilePath()).into(photoView);
        container.addView(photoView);
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.finish();
            }
        });
        return photoView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
