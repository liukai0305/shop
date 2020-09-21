package com.qutu.talk.activity.my;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.qutu.talk.R;
import com.qutu.talk.base.MyBaseArmActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DengJiShuoMingActivity extends MyBaseArmActivity {
    @BindView(R.id.image_one)
    ImageView imageOne;
    @BindView(R.id.image_two)
    ImageView imageTwo;
    @BindView(R.id.caiyumei)
    LinearLayout caiYuMei;
    @BindView(R.id.caifu)
    TextView caiFu;
    @BindView(R.id.meili)
    TextView meiLi;

    private String tag;
    private int mPage = 0;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_deng_ji_shuo_ming;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        tag = getIntent().getStringExtra("tag");
        if (tag.equals("0")) {
            imageTwo.setVisibility(View.GONE);
            setTitle("等级说明");
            caiYuMei.setVisibility(View.GONE);
            setDengjiOne();
        } else if (tag.equals("1")) {
            setTitle("等级说明");
            setImage(0);
        } else if (tag.equals("2")) {
            imageTwo.setVisibility(View.GONE);
            setTitle("守护CP规则");
            setCPGuiZe();
        }
    }

    private void setDengjiOne() {
        imageOne.setImageResource(R.mipmap.vip_djsm);
    }


    private void setCPGuiZe() {
        imageOne.setImageResource(R.mipmap.cp_guize);
    }


    @OnClick({R.id.caifu, R.id.meili})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.caifu:
                setImage(0);
                break;
            case R.id.meili:
                setImage(1);
                break;
        }
    }

    private void setImage(int page) {
        if (page == 0) {
            caiFu.setText("财富等级");
            caiFu.setTextColor(getResources().getColor(R.color.font_ff3e6d));
            caiFu.setTextSize(16);
            meiLi.setText("魅力等级");
            meiLi.setTextColor(getResources().getColor(R.color.font_999999));
            meiLi.setTextSize(14);
            imageOne.setImageResource(R.mipmap.cfdj);
        } else {
            meiLi.setText("魅力等级");
            meiLi.setTextColor(getResources().getColor(R.color.font_ff3e6d));
            meiLi.setTextSize(16);
            caiFu.setText("财富等级");
            caiFu.setTextColor(getResources().getColor(R.color.font_999999));
            caiFu.setTextSize(14);
            imageOne.setImageResource(R.mipmap.mldj);
        }
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        setToolbarTitle("等级说明", true);
//    }
}
