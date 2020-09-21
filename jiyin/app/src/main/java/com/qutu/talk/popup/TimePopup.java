package com.qutu.talk.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.qutu.talk.R;
import com.qutu.talk.adapter.TimePopupAdapter;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.DialogBean;

import java.util.ArrayList;
import java.util.List;

public class TimePopup extends PopupWindow {
    private ListView mListView;
    private View mMenuView;
    private Context context;

    public ListView getMyListView() {
        return mListView;
    }

    public TimePopup(Activity context) {
        super(context);
        this.context = context;
        mMenuView = LayoutInflater.from(context).inflate(R.layout.time_popup_layout, null);
        mListView = mMenuView.findViewById(R.id.listview_time);

        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
//        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        //设置透明
        WindowManager.LayoutParams params = context.getWindow().getAttributes();
        params.alpha = 0.7f;
        context.getWindow().setAttributes(params);

        TimePopupAdapter adapter = new TimePopupAdapter(context);
        mListView.setAdapter(adapter);
        List<DialogBean> list = new ArrayList<>();
        list.add(new DialogBean("1分钟", 1));
        list.add(new DialogBean("2分钟", 2));
        list.add(new DialogBean("3分钟", 3));
        list.add(new DialogBean("4分钟", 4));
        list.add(new DialogBean("5分钟", 5));
        list.add(new DialogBean("6分钟", 6));
        list.add(new DialogBean("7分钟", 7));
        list.add(new DialogBean("8分钟", 8));
        list.add(new DialogBean("9分钟", 9));
        list.add(new DialogBean("10分钟", 10));
        adapter.getList_adapter().addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        MyBaseArmActivity myBaseArmActivity = (MyBaseArmActivity) context;
        WindowManager.LayoutParams params = myBaseArmActivity.getWindow().getAttributes();
        params.alpha = 1f;
        myBaseArmActivity.getWindow().setAttributes(params);
    }
}
