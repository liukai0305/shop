package com.qutu.talk.popup;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.http.imageloader.glide.GlideArms;
import com.qutu.talk.R;
import com.qutu.talk.app.view.CircularImage;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.PwdEditText;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PwdWindow extends Dialog {
    @BindView(R.id.guan_btn)
    ImageView guanBtn;
    @BindView(R.id.head_image)
    CircularImage headImage;
    @BindView(R.id.pwd_text)
    PwdEditText pwdText;
    @BindView(R.id.error_tit)
    TextView errorTit;
    private Activity mContext;

    public CircularImage getHeadImage() {
        return headImage;
    }

    public PwdEditText getPwdText() {
        return pwdText;
    }

    public TextView getErrorTit() {
        return errorTit;
    }

    public PwdWindow(Activity context) {
        super(context, R.style.myChooseDialog);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pwd_window);
        ButterKnife.bind(this);
        setWidows();
    }

    private void setWidows() {
        Display display = getWindow().getWindowManager().getDefaultDisplay();

        WindowManager.LayoutParams lp = getWindow().getAttributes();

        lp.width = (int) (display.getWidth() - QMUIDisplayHelper.dp2px(mContext, 38));

        lp.alpha = 1.0f;

        lp.gravity = Gravity.CENTER;

        getWindow().setAttributes(lp);

        this.setCanceledOnTouchOutside(true);

        guanBtn.setOnClickListener(v -> {
            hideKeyboard(pwdText);
            dismiss();
        });
    }
    public static void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        Window window = this.getWindow();
////Window window = getDialog().getWindow();如果是在activity中则用这段代码
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
////window.requestWindowFeature(Window.FEATURE_NO_TITLE); //用在activity中，去标题
//        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                |View.SYSTEM_UI_FLAG_IMMERSIVE
//                |View.SYSTEM_UI_FLAG_FULLSCREEN;
//        window.getDecorView().setSystemUiVisibility(uiOptions);
//
//    }
}
