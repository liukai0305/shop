package com.qutu.talk.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

public class TabBean<T> implements CustomTabEntity {

    private T tabKey;
    private String tabName;

    public TabBean(T tabKey, String tabName) {
        this.tabKey = tabKey;
        this.tabName = tabName;
    }

    public T getTabKey() {
        return tabKey;
    }

    @Override
    public String getTabTitle() {
        return tabName;
    }

    @Override
    public int getTabSelectedIcon() {
        return 0;
    }

    @Override
    public int getTabUnselectedIcon() {
        return 0;
    }


}
