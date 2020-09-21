package com.qutu.talk.bean.task;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.chad.library.adapter.base.entity.SectionMultiEntity;
import com.qutu.talk.bean.task.ExchangeListBean;
import com.qutu.talk.bean.task.JBExchangeBean;

public class MySection extends SectionEntity<JBExchangeBean.DataBeanX.DataBean> {
//    private String name;

    public MySection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MySection(JBExchangeBean.DataBeanX.DataBean s) {
        super(s);
    }

//    public String getName() {
//        return name;
//    }

//    public void setName(String name) {
//        this.name = name;
//    }
}
