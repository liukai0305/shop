package com.qutu.talk.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qutu.talk.R;
import com.qutu.talk.activity.MainActivity;
import com.qutu.talk.adapter.taskcenter.TodaySignAdapter;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.task.SignInBean;
import com.qutu.talk.bean.task.SignInDisplayBean;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.view.ShapeTextView;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

public class TodaySignWindow extends PopupWindow {
    private MainActivity mContext;
    private View mMenuView;
    private CommonModel mCommonModel;
    private RxErrorHandler mRxErrorHandler;
    private TodaySignAdapter mAdapter;
    private TextView timeText;
    private RecyclerView todayRecyc;
    private ShapeTextView btn;
    private ImageView colseBtn;
    private SignInDisplayBean mBean;

    public ShapeTextView getSure() {
        return btn;
    }
    public ImageView getColseBtn(){
        return colseBtn;
    }

    public TodaySignWindow(MainActivity context, CommonModel commonModel, RxErrorHandler rxErrorHandler, SignInDisplayBean signInDisplayBean) {
        super(context);
        this.mContext = context;
        this.mCommonModel = commonModel;
        this.mRxErrorHandler = rxErrorHandler;
        this.mBean = signInDisplayBean;
        mMenuView = LayoutInflater.from(context).inflate(R.layout.today_sign_window, null);
        timeText = mMenuView.findViewById(R.id.today_sign_text);
        todayRecyc = mMenuView.findViewById(R.id.today_sign_recyc);
        btn = mMenuView.findViewById(R.id.today_sign_btn);
        colseBtn=mMenuView.findViewById(R.id.colse);
        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(RelativeLayout.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(false);
        this.setOutsideTouchable(false);

        timeText.setText(mBean.getData().getTitle());
        if(mBean.getData().getIs_sign() == 1){
            btn.setText("已签到");
        }
        // 刷新状态
//        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        //设置透明
        WindowManager.LayoutParams params = context.getWindow().getAttributes();
        params.alpha = 0.7f;
//        this.setWidth(d.getWidth() / 3 * 2);
//        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        context.getWindow().setAttributes(params);

        mAdapter = new TodaySignAdapter(mContext);
        todayRecyc.setLayoutManager(new GridLayoutManager(mContext, 4));
        todayRecyc.setAdapter(mAdapter);

        mAdapter.setNewData(mBean.getData().getData());
    }

    public void dismiss() {
        super.dismiss();
        MyBaseArmActivity myBaseArmActivity = (MyBaseArmActivity) mContext;
        WindowManager.LayoutParams params = myBaseArmActivity.getWindow().getAttributes();
        params.alpha = 1f;
        myBaseArmActivity.getWindow().setAttributes(params);
    }

}
