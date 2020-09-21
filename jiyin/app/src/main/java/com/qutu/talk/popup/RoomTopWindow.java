package com.qutu.talk.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.qutu.talk.R;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.view.ShapeTextView;


/**
 * 房间输入
 */
public class RoomTopWindow extends PopupWindow {


    private View mMenuView;
    private Context context;

    public LinearLayout getLlShare() {
        return llShare;
    }

    public LinearLayout getLlClose() {
        return llClose;
    }

    public LinearLayout getLlJubao() {
        return llJubao;
    }

    public LinearLayout getLlClear() {
        return llClear;
    }

    private LinearLayout llShare,llClose,llJubao,llClear;


    ////TODO 1.房主 2.管理员 3.禁言 4.评委 5.普通用户
    public RoomTopWindow(Activity context,int userType) {
        super(context);
        this.context = context;
        mMenuView = LayoutInflater.from(context).inflate(R.layout.dialog_room_top, null);
        llShare = (LinearLayout) mMenuView.findViewById(R.id.llShare);
        llClose = (LinearLayout) mMenuView.findViewById(R.id.llClose);
        llJubao = (LinearLayout) mMenuView.findViewById(R.id.llJubao);
        llClear = (LinearLayout) mMenuView.findViewById(R.id.ll_clear);
        if(userType==1||userType==2){
            llClear.setVisibility(View.VISIBLE);
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
