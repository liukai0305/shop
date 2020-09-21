package com.qutu.talk.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class HomeMultipleItem<T> implements MultiItemEntity {

    public static final int title_today_recommend = 1;//个人推荐title

    public static final int list_today_recommend = 2;//个人推荐数据

    public static final int title_hot_recommend = 3;//热门推荐、娱乐、语圈、文坛title

    public static final int list_hot_recommend = 4;//热门推荐、娱乐、语圈、文坛数据

    public static final int title_union_recommend = 5;//最佳工会title

    public static final int list_union_recommend = 6;//最佳工会数据

    public static final int center_banner= 7;//中央banner

    public static final int title_new_people= 8;//新人推荐title

    public static final int list_new_people= 9;//新人推荐数据

    public static final int text_last_string= 10;//最后的底线

    private int itemType;

    private T mEntity;//具体数据

    public T getEntity() {
        return mEntity;
    }

    public void setEntity(T entity) {
        mEntity = entity;
    }

    public HomeMultipleItem(int itemType) {
        this.itemType = itemType;
    }
    @Override
    public int getItemType() {
        return itemType;
    }
}
