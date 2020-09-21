package com.example.xiangmuyi.guide;

import android.view.View;

import java.util.ArrayList;


import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

class ViewPager_item extends PagerAdapter {
    private ArrayList<View> list;

    public ViewPager_item(ArrayList<View> list) {
        this.list = list;
    }

    @Override
    //返回集合中页面个数
    public int getCount() {
        return list.size();
    }

    @Override
    //判断并返回是否与集合中的页面相同
    public boolean isViewFromObject(View view, Object o) {
        return view==o;
    }

    @Override
    //销毁翻过去的布局
    public void destroyItem(ViewGroup container, int position, Object object) {
        //通过ViewGroup父布局，来进行销毁
        View view = list.get(position);

        container.removeView(view);//通过集合和position获取布局，调用removeView方法进行销毁

    }

    @Override
    //添加要翻到的布局  返回一个布局
    public Object instantiateItem(ViewGroup container, int position) {
        //通过ViewGroup父布局进行添加布局

        container.addView(list.get(position));
        return list.get(position);//返回添加过的布局(list.get(position))
    }
}