package com.qutu.talk.activity.my;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.activity.HelpActivity;
import com.qutu.talk.base.MyBaseArmActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HelpAndFanKuiActivity extends MyBaseArmActivity {

    @BindView(R.id.xxgf)
    RelativeLayout xxgf;
    @BindView(R.id.dsjs)
    RelativeLayout dsjs;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_help_and_fan_kui;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle("帮助");
    }

    @OnClick({R.id.xxgf, R.id.dsjs})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.xxgf:
                ArmsUtils.startActivity(ProblemHelpActivity.class);  //问题帮助
                break;
            case R.id.dsjs:
                ArmsUtils.startActivity(HelpActivity.class);  //意见反馈
                break;
        }
    }
}
