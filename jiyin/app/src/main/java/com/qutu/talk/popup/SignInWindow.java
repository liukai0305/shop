package com.qutu.talk.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.task.SignInBean;
import com.qutu.talk.utils.MyUtil;

public class SignInWindow extends PopupWindow {
    private Context mContext;
    private SignInBean mSignInBean;
    private View mMenuView;
    private TextView signInConText;
    private RelativeLayout bjOne, bjTwo;
    private ImageView show_img_one, show_img_two;
    private TextView show_text_one, show_text_two;

    public SignInWindow(Activity context, SignInBean signInBean) {
        super(context);
        this.mContext = context;
        this.mSignInBean = signInBean;
        mMenuView = LayoutInflater.from(context).inflate(R.layout.sign_in_window, null);
        signInConText = mMenuView.findViewById(R.id.sign_in_con_text);
        bjOne = mMenuView.findViewById(R.id.bj_one);
        bjTwo = mMenuView.findViewById(R.id.bj_two);
        show_img_one = mMenuView.findViewById(R.id.show_img_one);
        show_img_two = mMenuView.findViewById(R.id.show_img_two);
        show_text_one = mMenuView.findViewById(R.id.show_text_one);
        show_text_two = mMenuView.findViewById(R.id.show_text_two);
        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
//        this.setWidth(RelativeLayout.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体的高
//        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
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
        Display d = context.getWindowManager().getDefaultDisplay();
        params.alpha = 0.7f;
        this.setWidth(d.getWidth() / 3 * 2);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        context.getWindow().setAttributes(params);

        if (mSignInBean != null) {
            signInConText.setText(mSignInBean.getData().getTitle());

            ArmsUtils.obtainAppComponentFromContext(mContext)
                    .imageLoader()
                    .loadImage(mContext, ImageConfigImpl
                            .builder()
                            .url(mSignInBean.getData().getData().get(0).getImg())
                            .placeholder(R.mipmap.no_tu)
                            .imageView(show_img_one)
                            .errorPic(R.mipmap.no_tu)
                            .build());

            show_text_one.setText(mSignInBean.getData().getData().get(0).getTitle());
            if (mSignInBean.getData().getData().size() == 1) {
                bjTwo.setVisibility(View.GONE);
            } else {
                bjTwo.setVisibility(View.VISIBLE);
                ArmsUtils.obtainAppComponentFromContext(mContext)
                        .imageLoader()
                        .loadImage(mContext, ImageConfigImpl
                                .builder()
                                .url(mSignInBean.getData().getData().get(1).getImg())
                                .placeholder(R.mipmap.no_tu)
                                .imageView(show_img_two)
                                .errorPic(R.mipmap.no_tu)
                                .build());
                show_text_two.setText(mSignInBean.getData().getData().get(1).getTitle());
            }
        }
    }

    public void dismiss() {
        super.dismiss();
        MyBaseArmActivity myBaseArmActivity = (MyBaseArmActivity) mContext;
        WindowManager.LayoutParams params = myBaseArmActivity.getWindow().getAttributes();
        params.alpha = 1f;
        myBaseArmActivity.getWindow().setAttributes(params);
    }
}
