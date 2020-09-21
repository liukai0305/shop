package com.qutu.talk.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.qutu.talk.R;
import com.qutu.talk.base.MyBaseArmActivity;


public class FamilyTopWindow extends PopupWindow {


    private View mMenuView;
    private Context context;

    private LinearLayout llIntroduce,llExit;

    public LinearLayout getLlIntroduce() {
        return llIntroduce;
    }

    public LinearLayout getLlExit() {
        return llExit;
    }

    public FamilyTopWindow(Activity context, boolean isFamilyUser) {
        super(context);
        this.context = context;
        mMenuView = LayoutInflater.from(context).inflate(R.layout.dialog_family_top, null);
        llIntroduce = (LinearLayout) mMenuView.findViewById(R.id.ll_introduce);
        llExit = (LinearLayout) mMenuView.findViewById(R.id.llExit);
        if(isFamilyUser){
            llExit.setVisibility(View.VISIBLE);
        }else{
            llExit.setVisibility(View.GONE);
        }
        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(RelativeLayout.LayoutParams.WRAP_CONTENT);
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
        WindowManager.LayoutParams params= context.getWindow().getAttributes();
        params.alpha=0.7f;
        context.getWindow().setAttributes(params);
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
