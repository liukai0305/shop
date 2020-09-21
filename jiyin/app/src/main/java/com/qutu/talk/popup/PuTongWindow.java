package com.qutu.talk.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qutu.talk.R;
import com.qutu.talk.base.MyBaseArmActivity;

public class PuTongWindow extends PopupWindow {
    private View mMenuView;
    private Context context;
    private TextView cancel, sure, titText;

    public TextView getCancel() {
        return cancel;
    }

    public TextView getSure() {
        return sure;
    }

    public TextView getTitText() {
        return titText;
    }

    public PuTongWindow(Activity context) {
        super(context);
        this.context = context;
        mMenuView = LayoutInflater.from(context).inflate(R.layout.putong_popu, null);
        cancel = mMenuView.findViewById(R.id.cancel);
        sure = mMenuView.findViewById(R.id.sure);
        titText = mMenuView.findViewById(R.id.tit_popu);

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
        WindowManager.LayoutParams params = context.getWindow().getAttributes();
        params.alpha = 0.7f;
        context.getWindow().setAttributes(params);
    }

    public void dismiss() {
        super.dismiss();
        MyBaseArmActivity myBaseArmActivity = (MyBaseArmActivity) context;
        WindowManager.LayoutParams params = myBaseArmActivity.getWindow().getAttributes();
        params.alpha = 1f;
        myBaseArmActivity.getWindow().setAttributes(params);
    }
}
