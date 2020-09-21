package com.qutu.talk.popup;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.utils.ArmsUtils;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qutu.talk.R;
import com.qutu.talk.activity.FamilyMeltingRankActivity;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.service.CommonModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;


public class FamilyMeltingDialog extends Dialog {

    @BindView(R.id.ll_root)
    LinearLayout llRoot;
    @BindView(R.id.cv_guize)
    CardView cvGuize;
    @BindView(R.id.tv_rank)
    TextView tvRank;
    @BindView(R.id.cv_melting)
    CardView cvMelting;
    @BindView(R.id.layout_baoxiao)
    LinearLayout layoutBaoxiao;

    private MyBaseArmActivity mContext;
    private CommonModel commonModel;
    private RxErrorHandler mErrorHandler;

    private int familyId=0;

    public FamilyMeltingDialog(@NonNull Activity context, CommonModel commonModel, RxErrorHandler errorHandler,int familyId) {
        super(context, R.style.myChooseDialog);
        mContext = (MyBaseArmActivity) context;
        this.commonModel = commonModel;
        this.mErrorHandler = errorHandler;
        this.familyId=familyId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_family_melting);
        ButterKnife.bind(this);
        llRoot.setOnClickListener(v -> {

        });
        layoutBaoxiao.setOnClickListener(v -> {
            dismiss();
        });
        setWidows();

    }

    private void setWidows() {

        Display display = getWindow().getWindowManager().getDefaultDisplay();

        WindowManager.LayoutParams lp = getWindow().getAttributes();

        lp.width = (int) (display.getWidth() - QMUIDisplayHelper.dp2px(mContext, 72));

        lp.alpha = 1.0f;

        lp.gravity = Gravity.BOTTOM;

//        getWindow().setAttributes(lp);

        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        this.setCanceledOnTouchOutside(true);

    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @OnClick({
        R.id.cv_guize,
        R.id.cv_melting,
            R.id.tv_rank
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cv_guize:
                FamilyMeltingRuleWindow familyMeltingRuleWindow = new FamilyMeltingRuleWindow(mContext, (ViewGroup) getWindow().getDecorView(), commonModel, mErrorHandler);
                familyMeltingRuleWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                break;
            case R.id.cv_melting:
                MeltingPackDialog meltingPackDialog = new MeltingPackDialog(mContext, commonModel, mErrorHandler, familyId);
                meltingPackDialog.show();
                break;
            case R.id.tv_rank:
                Intent intent=new Intent(mContext, FamilyMeltingRankActivity.class);
                intent.putExtra("familyId",familyId);
                ArmsUtils.startActivity(intent);
                break;
        }
    }

}
