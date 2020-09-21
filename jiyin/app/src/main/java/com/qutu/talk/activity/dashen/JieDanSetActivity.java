package com.qutu.talk.activity.dashen;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.LogUtils;
import com.qutu.talk.R;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.CardBean;
import com.qutu.talk.bean.CommentBean;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.dashen.JieDanSetBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.popup.JieDanRiQiPopup;
import com.qutu.talk.popup.PaiDanPopup;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.R;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


public class JieDanSetActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.jiedan_time_text)
    TextView jiedanTimeText;
    @BindView(R.id.jiedan_time)
    LinearLayout jiedanTime;
    @BindView(R.id.jiedan_date_text)
    TextView jiedanDateText;
    @BindView(R.id.jiedan_date)
    LinearLayout jiedanDate;
    @BindView(R.id.jiedan_set)
    LinearLayout jiedanSet;

    private OptionsPickerView pvCustomOptions; //接单时间选择器
    // 接单时间的数据
    private List<CardBean> cardItem = new ArrayList<>();
    private List<CardBean> cardItem2 = new ArrayList<>();

    private String txOne, txTwo;
    private int startTime, endTime, startTime2, endTime2;
    private String startStr, endStr;

    private List<JieDanSetBean.DataBean.PdReceiveBean> pd_receive;

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
        return R.layout.activity_jie_dan_set;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle("接单设置");

        getData();
        getCardData();
        jiedanTimeText.postDelayed(new Runnable() {
            @Override
            public void run() {
                initCustomOptionPicker();
            }
        }, 1000);
    }

    @OnClick({R.id.jiedan_time, R.id.jiedan_date, R.id.jiedan_set})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.jiedan_time:
                if (pvCustomOptions != null) {
                    pvCustomOptions.show();
                }
                break;
            case R.id.jiedan_date:
                JieDanRiQiPopup jieDanRiQiPopup = new JieDanRiQiPopup(this, commonModel, mErrorHandler);
                jieDanRiQiPopup.show();
                break;
            case R.id.jiedan_set:
                PaiDanPopup paiDanPopup = new PaiDanPopup(this, pd_receive, commonModel, mErrorHandler);
                paiDanPopup.show();
                break;
        }
    }

    //加载接单设置选择器的数据
    private void getCardData() {
        for (int i = 0; i < 24; i++) {
            cardItem.add(new CardBean(i, i + "时"));
        }

        for (int a = 0; a < 24; a++) {
            cardItem2.add(new CardBean(a + 1, (a + 1) + "时"));
        }
    }

    //获取数据
    private void getData() {
        RxUtils.loading(commonModel.getReceiptList(), this)
                .subscribe(new ErrorHandleSubscriber<JieDanSetBean>(mErrorHandler) {
                    @Override
                    public void onNext(JieDanSetBean jieDanSetBean) {
                        JieDanSetBean.DataBean data = jieDanSetBean.getData();
                        pd_receive = jieDanSetBean.getData().getPd_receive();
                        startTime2 = jieDanSetBean.getData().getStart();
                        endTime2 = jieDanSetBean.getData().getEnd();
                        if (startTime2 < 10) {
                            startStr = "0" + startTime2 + ":00";
                        } else {
                            startStr = startTime2 + ":00";
                        }
                        if (endTime2 < 10) {
                            endStr = "0" + endTime2 + ":00";
                        } else {
                            endStr = endTime2 + ":00";
                        }
                        jiedanTimeText.setText(startStr + "~" + endStr);
                        jiedanDateText.setText(data.getDate());
                    }
                });
    }

    private void initCustomOptionPicker() {
        //条件选择器初始化，自定义布局

        /**
         * @description
         *
         * 注意事项：
         * 自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针。
         * 具体可参考demo 里面的两个自定义layout布局。
         */
        pvCustomOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
//                String tx = cardItem.get(options1).getPickerViewText();
                if (cardItem.get(options1).getId() < 10) {
                    txOne = "0" + cardItem.get(options1).getId() + ":00";
                } else {
                    txOne = cardItem.get(options1).getId() + ":00";
                }

                if (cardItem2.get(option2).getId() < 10) {
                    txTwo = "0" + cardItem2.get(option2).getId() + ":00";
                } else {
                    txTwo = cardItem2.get(option2).getId() + ":00";
                }

                startTime = cardItem.get(options1).getId();
                endTime = cardItem2.get(option2).getId();

                jiedanTimeText.setText(txOne + "~" + txTwo);
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView ivCancel = v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.returnData();
                                setJieDanTime(startTime, endTime);
                            }
                        });

                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.dismiss();
                            }
                        });
                    }
                })
                .setSelectOptions(startTime2, (endTime2 - 1))
                .isDialog(true)
                .setOutSideCancelable(true)
                .setCyclic(true, true, false)
                .build();

        Dialog mDialog = pvCustomOptions.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvCustomOptions.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }

        pvCustomOptions.setNPicker(cardItem, cardItem2, new ArrayList());//添加数据
    }

    //设置接单时间
    private void setJieDanTime(int start, int end) {
        RxUtils.loading(commonModel.actionReceiptTime(start, end), this)
                .subscribe(new ErrorHandleSubscriber<CommentBean>(mErrorHandler) {
                    @Override
                    public void onNext(CommentBean commentBean) {
                        pvCustomOptions.dismiss();
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
        if ("jiedansetwancheng".equals(tag)) {
            getData();
        }
    }


}
