package com.qutu.talk.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.qutu.talk.R;
import com.qutu.talk.bean.WaitList;

import java.util.ArrayList;

/**
 * 举报
 */
@ActivityScope
public class ReportAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public ReportAdapter() {
        super(R.layout.item_report, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        helper.setText(R.id.textName, item);
    }
}
