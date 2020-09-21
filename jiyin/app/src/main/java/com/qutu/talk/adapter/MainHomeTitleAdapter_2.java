package com.qutu.talk.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.qutu.talk.R;
import com.qutu.talk.activity.MoreRoomListActivity;
import com.qutu.talk.base.BaseDelegateAdapter;
import com.qutu.talk.base.BaseDelegateViewHolder;
import com.qutu.talk.bean.RoomTypeResult;
import com.qutu.talk.utils.ActivityUtils;

import java.util.List;

public class MainHomeTitleAdapter_2 extends BaseDelegateAdapter<RoomTypeResult.DataBean, BaseDelegateViewHolder> {

    public MainHomeTitleAdapter_2(int layoutResId, @Nullable List<RoomTypeResult.DataBean> data, LayoutHelper layoutHelper) {
        super(layoutResId, data, layoutHelper);
    }

    @Override
    public void convert(BaseDelegateViewHolder helper, RoomTypeResult.DataBean item) {

        helper.setText(R.id.tv_left_name, item.getName());

        helper.setGone(R.id.tv_right_more, true);

        if(item.getId().equals("1")){//娱乐
            helper.setImageResource(R.id.img_left, R.mipmap.icon_title_yule);
        } else if(item.getId().equals("2")){//语圈
            helper.setImageResource(R.id.img_left, R.mipmap.icon_title_yuquan);
        } else if(item.getId().equals("3")){//文坛
            helper.setImageResource(R.id.img_left, R.mipmap.icon_title_wentan);
        } else if(item.getId().equals("-9999")){//房间推荐
            helper.setImageResource(R.id.img_left, R.mipmap.icon_title_room_recommend);
        } else if(item.getId().equals("-8888")){//最佳公会
            helper.setImageResource(R.id.img_left, R.mipmap.icon_title_union);
            helper.setGone(R.id.tv_right_more, false);
        }

        TextView tvMore = helper.getView(R.id.tv_right_more);

        tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("title", item.getName());
                bundle.putString("parend_id", item.getId());
                Intent intent = new Intent(tvMore.getContext(), MoreRoomListActivity.class);
                intent.putExtras(bundle);
                tvMore.getContext().startActivity(intent);
            }
        });




    }
}
