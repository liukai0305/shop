package com.qutu.talk.adapter.dashen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.utils.LogUtils;
import com.qutu.talk.R;
import com.qutu.talk.bean.dashen.DuanWeiBean;

import java.util.ArrayList;
import java.util.List;

public class ScreenDuanWeiAdapter extends BaseQuickAdapter<DuanWeiBean.DataBean, BaseViewHolder> {
    private Context context;
    public boolean isAdapter = true;

    public ScreenDuanWeiAdapter(Context context) {
        super(R.layout.screen_adapter_item, new ArrayList<>());
        this.context = context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DuanWeiBean.DataBean item) {
        helper.addOnClickListener(R.id.screen_sex_all);
//        if (isAdapter == true ? item.isSelector == true ? true : false : item.isClick == true ? true : false) {
//            if (item.isSelector) {
        helper.getView(R.id.screen_sex_all).setSelected(isAdapter == true ? item.isSelector : item.isClick);
//            } else {
//                helper.getView(R.id.screen_sex_all).setSelected(false);
//            }
//        } else {
//            helper.getView(R.id.screen_sex_all).setSelected(false);
//            isAdapter = false;
//        }


        helper.setText(R.id.screen_sex_all, item.getName());
    }
}
