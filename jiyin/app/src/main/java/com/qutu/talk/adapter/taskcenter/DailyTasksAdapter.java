package com.qutu.talk.adapter.taskcenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.bean.task.NewbieTaskBean;
import com.qutu.talk.view.ShapeTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 日常任务适配器
 */
public class DailyTasksAdapter extends BaseQuickAdapter<NewbieTaskBean.DataBean, BaseViewHolder> {
    private Context mContext;

    public DailyTasksAdapter(Context context) {
        super(R.layout.newbie_task_item, new ArrayList<>());
        this.mContext = context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NewbieTaskBean.DataBean item) {
        helper.addOnClickListener(R.id.task_item_btn);
        //图片
        ArmsUtils.obtainAppComponentFromContext(mContext)
                .imageLoader()
                .loadImage(mContext, ImageConfigImpl
                        .builder()
                        .url(item.getImg())
                        .placeholder(R.mipmap.no_tu)
                        .imageView(helper.getView(R.id.task_item_headimg))
                        .errorPic(R.mipmap.no_tu)
                        .build());

        helper.setText(R.id.task_item_name, item.getTitle())
                .setText(R.id.task_item_piace, "+" + item.getJinbi() + "钻石奖励");

        ShapeTextView shapeTextView = helper.getView(R.id.task_item_btn);
        if (item.getStatus() == 1) {
//                helper.setText(R.id.task_item_btn,"去完成");
            shapeTextView.setText("去完成");
            shapeTextView.setClickable(true);
        } else if (item.getStatus() == 2) {
//                helper.setText(R.id.task_item_btn,"领取");
            shapeTextView.setText("领取");
            shapeTextView.setClickable(true);
        } else {
//                helper.setText(R.id.task_item_btn,"已领取");
            shapeTextView.setText("已领取");
            shapeTextView.setClickable(false);
            shapeTextView.setEnabled(false);
        }
    }
}
