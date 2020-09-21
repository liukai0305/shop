package com.qutu.talk.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.VerticalRangeSeekBar;
import com.jaygoo.widget.VerticalSeekBar;
import com.jess.arms.utils.LogUtils;
import com.qutu.talk.R;
import com.qutu.talk.base.MyBaseArmActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 房间音量
 */
public class MusicVolumeWindow extends PopupWindow {


    @BindView(R.id.seek_bar)
    VerticalRangeSeekBar mSeekBar;

    private View mMenuView;

    private Context context;

    OnRangeChangedListener mOnRangeChangedListener;


    public View getMenuView(){
        return mMenuView;
    }

    public void setOnRangeChangedListener(OnRangeChangedListener onRangeChangedListener){
        this.mOnRangeChangedListener = onRangeChangedListener;
        if(mSeekBar != null){
            LogUtils.debugInfo("设置音量改变监听====");
            mSeekBar.setOnRangeChangedListener(mOnRangeChangedListener);
        }
    }

    public void setProgress(float progress){
        if(mSeekBar != null){
            mSeekBar.setProgress(progress);
        }
    }


    public MusicVolumeWindow(Activity context) {
        super(context);
        this.context = context;
        mMenuView = LayoutInflater.from(context).inflate(R.layout.dialog_music_volume, null);
//        mVerticalSeekBar = (VerticalSeekBar) mMenuView.findViewById(R.id.seek_bar);
        ButterKnife.bind(this, mMenuView);
        mSeekBar.setProgress(10);
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

    @Override
    public void dismiss() {
        super.dismiss();
        MyBaseArmActivity myBaseArmActivity = (MyBaseArmActivity) context;
        WindowManager.LayoutParams params = myBaseArmActivity.getWindow().getAttributes();
        params.alpha = 1f;
        myBaseArmActivity.getWindow().setAttributes(params);
    }
}
