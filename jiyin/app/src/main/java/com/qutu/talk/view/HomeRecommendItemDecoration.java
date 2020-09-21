package com.qutu.talk.view;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jess.arms.utils.LogUtils;
import com.qutu.talk.bean.HomeMultipleItem;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import java.util.ArrayList;
import java.util.List;

public class HomeRecommendItemDecoration extends RecyclerView.ItemDecoration {


    List<HomeMultipleItem> mDataList = new ArrayList<>();

    int mStartPositionRecommend;//个人推荐的数据在list中的起始位置

    public HomeRecommendItemDecoration(List<HomeMultipleItem> list) {
        this.mDataList = list;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

//        GridLayoutManager gridLayoutManager = (GridLayoutManager) parent.getLayoutManager();
//        int spanCount = gridLayoutManager.getSpanCount();
//        LogUtils.debugInfo("一行几个====="+spanCount+"数据长度===="+mDataList.size());
        int position = parent.getChildLayoutPosition(view);

        int type = mDataList.get(position).getItemType();

        int preItemPosition = position-1;

        switch (type){
            case HomeMultipleItem.title_today_recommend://个人推荐title
                break;
            case HomeMultipleItem.list_today_recommend://个人推荐数据
                int totalW = QMUIDisplayHelper.dpToPx(16);//缝隙总宽度
                if(preItemPosition>0){
                    HomeMultipleItem item = mDataList.get(preItemPosition);
                    if(item.getItemType() != HomeMultipleItem.list_today_recommend){//判断当前数据是不是这个类型中的第一个，是第一个
                        outRect.left = 0;
                        mStartPositionRecommend = position;
                    }
                    else {//不是第一个，边距设置为8
                        outRect.left = (position-mStartPositionRecommend)*totalW/6;
                    }
                }
                break;
            case HomeMultipleItem.title_hot_recommend://热门推荐、娱乐、语圈、文坛title
                break;
            case HomeMultipleItem.list_hot_recommend://热门推荐、娱乐、语圈、文坛数据
                break;
            case HomeMultipleItem.title_union_recommend://最佳工会title
                break;
            case HomeMultipleItem.list_union_recommend://最佳工会数据
                break;
            case HomeMultipleItem.center_banner://center_banner
                break;
            case HomeMultipleItem.title_new_people://新人推荐title
                break;
            case HomeMultipleItem.text_last_string://我也是有底线的
                break;
        }

    }
}
