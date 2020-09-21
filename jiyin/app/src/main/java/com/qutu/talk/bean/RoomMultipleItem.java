package com.qutu.talk.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class RoomMultipleItem implements MultiItemEntity {

    public static final int TITLE_MIC_UP = 1;
    public static final int MIC_UP = 2;
    public static final int TITLE_MIC_DOWN = 3;
    public static final int MIC_DOWN = 4;
    public static final int TITLE_MIC_PAI = 5;
    public static final int MIC_PAI = 6;
    public static final int TITLE_MIC_SHIYIN = 7;
    public static final int MIC_SHIYIN = 8;

    private int itemType;

    private MicUserBean micUserBean;

    public RoomMultipleItem(int itemType, MicUserBean data) {
        this.itemType = itemType;
        this.micUserBean = data;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public MicUserBean getData(){
        return micUserBean;
    }
}
