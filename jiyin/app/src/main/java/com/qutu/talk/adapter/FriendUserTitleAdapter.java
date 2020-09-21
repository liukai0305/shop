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

import java.util.List;

public class FriendUserTitleAdapter extends BaseDelegateAdapter<String, BaseDelegateViewHolder> {

    public FriendUserTitleAdapter(int layoutResId, @Nullable List<String> data, LayoutHelper layoutHelper) {
        super(layoutResId, data, layoutHelper);
//        super(R.layout.item_friend_user_title, data, layoutHelper);
    }

    @Override
    public void convert(BaseDelegateViewHolder helper, String item) {

        helper.setText(R.id.tv_title_friend_type, item);

    }
}
