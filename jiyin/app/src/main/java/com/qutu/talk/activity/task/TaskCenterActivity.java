package com.qutu.talk.activity.task;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.activity.MainActivity;
import com.qutu.talk.activity.mine.MoneyActivity;
import com.qutu.talk.activity.my.ModifyDataActivity;
import com.qutu.talk.adapter.taskcenter.DailyTasksAdapter;
import com.qutu.talk.adapter.taskcenter.NewbieTaskAdapter;
import com.qutu.talk.adapter.taskcenter.SignInAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.CommentBean;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.task.NewbieTaskBean;
import com.qutu.talk.bean.task.SignInBean;
import com.qutu.talk.bean.task.SignInDisplayBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.popup.SignInWindow;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.view.ShapeTextView;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.qutu.talk.utils.Constant.TIAORELIAO;
import static com.qutu.talk.utils.Constant.TIAOSHEQU;
import static com.qutu.talk.utils.Constant.TIAOXIADAN;

/**
 * 任务中心
 * 老王
 */

public class TaskCenterActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.sign_in_recyc)
    RecyclerView signInRecyc;
    @BindView(R.id.signin_btn)
    ShapeTextView signinBtn;
    @BindView(R.id.new_recyc)
    RecyclerView newRecyc;
    @BindView(R.id.day_recyc)
    RecyclerView dayRecyc;
    @BindView(R.id.new_card)
    CardView newCard;

    private SignInAdapter mSignAdapter; //每日签到
    private NewbieTaskAdapter mNewAdapter; //新手任务
    private DailyTasksAdapter mDailyAdapter; //日常任务

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
        return R.layout.activity_task_center;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle("任务中心");

        mSignAdapter = new SignInAdapter(this);
        signInRecyc.setLayoutManager(new GridLayoutManager(this, 7));
        signInRecyc.setAdapter(mSignAdapter);

        mNewAdapter = new NewbieTaskAdapter(this);
        newRecyc.setLayoutManager(new LinearLayoutManager(this));
        newRecyc.setAdapter(mNewAdapter);

        mDailyAdapter = new DailyTasksAdapter(this);
        dayRecyc.setLayoutManager(new LinearLayoutManager(this));
        dayRecyc.setAdapter(mDailyAdapter);

        getSignDis();
        getNewData();
        getDailyData();
        setAdapterClick();
    }

    //适配器点击事件以及按钮的点击事件
    private void setAdapterClick() {
        signinBtn.setOnClickListener(v -> {
            setSign();
        });

        mNewAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            NewbieTaskBean.DataBean dataBean = mNewAdapter.getData().get(position);
            if (dataBean.getStatus() == 1) {
                if ("1".equals(dataBean.getId()) || "2".equals(dataBean.getId())) {
                    ArmsUtils.startActivity(ModifyDataActivity.class);
                } else if ("3".equals(dataBean.getId())) {
                    EventBus.getDefault().post(new FirstEvent("跳热聊", TIAORELIAO));
                    ArmsUtils.startActivity(MainActivity.class);
                } else if ("4".equals(dataBean.getId())) {
                    EventBus.getDefault().post(new FirstEvent("跳社区", TIAOSHEQU));
                    ArmsUtils.startActivity(MainActivity.class);
                } else {
                    EventBus.getDefault().post(new FirstEvent("跳下单", TIAOXIADAN));
                    ArmsUtils.startActivity(MainActivity.class);
                }
                finish();
            } else if (dataBean.getStatus() == 2) {
                getReceviceTask(dataBean.getId(), 1, position);
            }
        });
        mDailyAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            NewbieTaskBean.DataBean dataBean = mDailyAdapter.getData().get(position);
            if (dataBean.getStatus() == 1) {
                if (position == 5) {
                    ArmsUtils.startActivity(MoneyActivity.class);
                } else if (position == 4 || position == 6) {
                    EventBus.getDefault().post(new FirstEvent("跳社区", TIAOSHEQU));
                    ArmsUtils.startActivity(MainActivity.class);
                } else {
                    EventBus.getDefault().post(new FirstEvent("跳热聊", TIAORELIAO));
                    ArmsUtils.startActivity(MainActivity.class);
                }
            } else if (dataBean.getStatus() == 2) {
                getReceviceTask(dataBean.getId(), 2, position);
            }
        });
    }

    //获取签到的展示
    private void getSignDis() {
        RxUtils.loading(commonModel.show_sign(), this)
                .subscribe(new ErrorHandleSubscriber<SignInDisplayBean>(mErrorHandler) {
                    @Override
                    public void onNext(SignInDisplayBean signInDisplayBean) {
                        mSignAdapter.setNewData(signInDisplayBean.getData().getData());

                        if (signInDisplayBean.getData().getIs_sign() == 1) {
                            signinBtn.setClickable(false);
                            signinBtn.setEnabled(false);
                            signinBtn.setText("已签到");
                        } else {
                            signinBtn.setClickable(true);
                            signinBtn.setEnabled(true);
                            signinBtn.setText("立即签到");
                        }
                    }
                });
    }

    //签到
    private void setSign() {
        RxUtils.loading(commonModel.sign(), this)
                .subscribe(new ErrorHandleSubscriber<SignInBean>(mErrorHandler) {
                    @Override
                    public void onNext(SignInBean signInBean) {
                        getSignDis();
                        SignInWindow signInWindow = new SignInWindow(TaskCenterActivity.this, signInBean);
                        signInWindow.showAtLocation(dayRecyc, Gravity.CENTER, 0, 0);
                    }
                });
    }

    //获取新手任务数据
    private void getNewData() {
        RxUtils.loading(commonModel.new_task(), this)
                .subscribe(new ErrorHandleSubscriber<NewbieTaskBean>(mErrorHandler) {
                    @Override
                    public void onNext(NewbieTaskBean newbieTaskBean) {
                        if (newbieTaskBean.getData().size() != 0) {
                            mNewAdapter.setNewData(newbieTaskBean.getData());
                        } else {
                            newCard.setVisibility(View.GONE);
                        }

                    }
                });
    }

    //获取日常任务数据
    private void getDailyData() {
        RxUtils.loading(commonModel.daily_task(), this)
                .subscribe(new ErrorHandleSubscriber<NewbieTaskBean>(mErrorHandler) {
                    @Override
                    public void onNext(NewbieTaskBean newbieTaskBean) {
                        mDailyAdapter.setNewData(newbieTaskBean.getData());
                    }
                });
    }

    //领取任务奖励
    private void getReceviceTask(String task_id, int type, int pos) {
        RxUtils.loading(commonModel.recevice_task(task_id), this)
                .subscribe(new ErrorHandleSubscriber<CommentBean>(mErrorHandler) {
                    @Override
                    public void onNext(CommentBean commentBean) {
                        if (type == 1) {
                            mNewAdapter.getData().remove(pos);
                            mNewAdapter.notifyDataSetChanged();
                        } else if (type == 2) {
                            mDailyAdapter.getData().get(pos).setStatus(3);
                            mDailyAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setToolbarRightText("兑换", v -> {
            ArmsUtils.startActivity(GoldExchangeActivity.class);
        }, R.color.font_333333);
    }
}
