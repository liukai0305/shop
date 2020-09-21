package com.qutu.talk.activity.dashen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.activity.game.applyskill.MyGameSkillActivity;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.app.view.CircularImage;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.dashen.DaShenZhuanShuBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.qutu.talk.utils.Constant.DASHENZHUANSHU;
import static com.qutu.talk.utils.Constant.PAIDANZHONGXIN;

public class DaShenExclusiveActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.dszs_tit_num)
    TextView dszsTitNum;
    @BindView(R.id.pdzx_yuan)
    CircularImage pdzxYuan;
    @BindView(R.id.dszs_pdzx)
    LinearLayout dszsPdzx;
    @BindView(R.id.dszs_myjn)
    LinearLayout dszsMyjn;
    @BindView(R.id.dszs_jdset)
    LinearLayout dszsJdset;
    @BindView(R.id.dszs_myjn_num)
    TextView dszsMyjnNum;
    @BindView(R.id.dashen_top)
    RelativeLayout dashenTop;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_da_shen_exclusive;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle("大神专属");

        getData();
    }

    @OnClick({R.id.dszs_pdzx, R.id.dszs_myjn, R.id.dszs_jdset, R.id.dashen_top})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dszs_pdzx: //派单中心
                ArmsUtils.startActivity(PaiDanCenterActivity.class);
                break;
            case R.id.dszs_myjn: //我的技能
                ArmsUtils.startActivity(MyGameSkillActivity.class);
                break;
            case R.id.dszs_jdset: //接单设置
                ArmsUtils.startActivity(JieDanSetActivity.class);
                break;
            case R.id.dashen_top:
                ArmsUtils.startActivity(MiLiIncomeActivity.class);
                break;
        }
    }

    //获取数据
    private void getData() {
        RxUtils.loading(commonModel.getGodInfo(), this)
                .subscribe(new ErrorHandleSubscriber<DaShenZhuanShuBean>(mErrorHandler) {
                    @Override
                    public void onNext(DaShenZhuanShuBean daShenZhuanShuBean) {
                        if (daShenZhuanShuBean.getData() != null) {
                            dszsTitNum.setText(daShenZhuanShuBean.getData().getMili());
                            if (daShenZhuanShuBean.getData().getIs_newpd() == 0) {
                                pdzxYuan.setVisibility(View.GONE);
                            } else {
                                pdzxYuan.setVisibility(View.VISIBLE);
                            }
                            dszsMyjnNum.setText(daShenZhuanShuBean.getData().getNum() + "个技能接单中");
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().post(new FirstEvent("大神专属",DASHENZHUANSHU));
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
        if (PAIDANZHONGXIN.equals(tag)) {
            getData();
        }
    }
}
