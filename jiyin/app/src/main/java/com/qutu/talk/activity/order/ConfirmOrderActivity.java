package com.qutu.talk.activity.order;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qutu.talk.R;
import com.qutu.talk.activity.room.RoomSettingActivity;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.ChoiceCouponBean;
import com.qutu.talk.bean.CommentBean;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.Microphone;
import com.qutu.talk.bean.PaiDanTimeBean;
import com.qutu.talk.bean.ZhuanYongBean;
import com.qutu.talk.bean.dashen.ConfirmOrderSkillBean;
import com.qutu.talk.bean.order.Orderbean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.popup.PaymentWindow;
import com.qutu.talk.popup.SelectGameTypeDialogTwo;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.DealRefreshHelper;
import com.qutu.talk.utils.TextNumUtil;
import com.qutu.talk.utils.TimeConstants;
import com.qutu.talk.utils.TimeSwitchUtil;
import com.qutu.talk.utils.ToastUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.qutu.talk.utils.Constant.MILICHONGZHI;
import static com.qutu.talk.utils.Constant.XUANCOUPON;

/**
 * 确定订单页面
 * 老王
 */

public class ConfirmOrderActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.order_headimg)
    RoundedImageView orderHeadimg;
    @BindView(R.id.order_name)
    TextView orderName;
    @BindView(R.id.order_price)
    TextView orderPrice;
    @BindView(R.id.order_unit)
    TextView orderUnit;
    @BindView(R.id.skill_choice_tit)
    TextView skillChoiceTit;
    @BindView(R.id.skill_choice_tu)
    ImageView skillChoiceTu;
    @BindView(R.id.skill_choice_content)
    TextView skillChoiceContent;
    @BindView(R.id.skill_choice)
    RelativeLayout skillChoice;
    @BindView(R.id.service_time_tit)
    TextView serviceTimeTit;
    @BindView(R.id.service_time_tu)
    ImageView serviceTimeTu;
    @BindView(R.id.service_time_content)
    TextView serviceTimeContent;
    @BindView(R.id.service_time)
    RelativeLayout serviceTime;
    @BindView(R.id.discount_tit)
    TextView discountTit;
    @BindView(R.id.discount_tu)
    ImageView discountTu;
    @BindView(R.id.discount_content)
    TextView discountContent;
    @BindView(R.id.discount)
    RelativeLayout discount;
    @BindView(R.id.number_tit)
    TextView numberTit;
    @BindView(R.id.number_jian)
    ImageView numberJian;
    @BindView(R.id.number_content)
    TextView numberContent;
    @BindView(R.id.number_jia)
    ImageView numberJia;
    @BindView(R.id.number)
    RelativeLayout number;
    @BindView(R.id.remarks_tit)
    TextView remarksTit;
    @BindView(R.id.remarks_edit)
    EditText remarksEdit;
    @BindView(R.id.remarks_num)
    TextView remarksNum;
    @BindView(R.id.remarks)
    RelativeLayout remarks;
    @BindView(R.id.total_num)
    TextView totalNum;
    @BindView(R.id.total_price)
    TextView totalPrice;
    @BindView(R.id.confirm_order_btn_ok)
    TextView confirmOrderBtnOk;

    private String mSkillId, mSkillName, mId, mUserId; // 技能ID,技能名字
    private String mSelectDay, mSelectHour, mSelectMinate;
    private String myId;
    private int mPrice;
    private PaymentWindow paymentWindow;

    //输入框初始值
    private int num = 0;
    //输入框最大值
    public int mMaxNum = 100;

    private String mSelectGameId;
    private String mPos;

    private List<ChoiceCouponBean.DataBean> mCouponData;
    private ZhuanYongBean zhuanYongBean;
    private String couponId;

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
        return R.layout.activity_confirm_order;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle("确认订单");
        mPrice = Integer.parseInt(getIntent().getStringExtra("price"));
        myId = String.valueOf(UserManager.getUser().getUserId());
        mSkillId = getIntent().getStringExtra("skillId");
        mSkillName = getIntent().getStringExtra("skillName");
        mId = getIntent().getStringExtra("id");
        mUserId = getIntent().getStringExtra("userId");
        if (!TextUtils.isEmpty(getIntent().getStringExtra("img_1"))) {
            loadImage(orderHeadimg, getIntent().getStringExtra("img_1"), R.mipmap.no_tou);
        }
        orderName.setText(getIntent().getStringExtra("naicName"));
        orderPrice.setText(getIntent().getStringExtra("price"));
        orderUnit.setText("/" + getIntent().getStringExtra("unit"));
        skillChoiceContent.setText(getIntent().getStringExtra("skillName"));
        totalPrice.setText(mPrice + "");
        getEditTextStr();

        getData();
    }


    @OnClick({R.id.skill_choice, R.id.service_time, R.id.discount, R.id.confirm_order_btn_ok, R.id.number_jian, R.id.number_jia})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.skill_choice:  //技能选择
                getGameType();
                break;
            case R.id.service_time: //服务时间
                showTimeDialog();
                break;
            case R.id.discount:
//                toast("暂无优惠");
                if (mCouponData.size() == 0) {
                    toast("暂无优惠券");
                } else {
                    Intent intent = new Intent(ConfirmOrderActivity.this, ChoiceCouponActivity.class);
                    intent.putParcelableArrayListExtra("coupon", (ArrayList<? extends Parcelable>) mCouponData);
                    intent.putExtra("pos", mPos);
                    startActivity(intent);
                }
                break;
            case R.id.confirm_order_btn_ok:
//                if ()
                if (TextUtils.isEmpty(mId)) {
                    ToastUtil.showToast(this, "请选择技能类型");
                    return;
                }
                if (TextUtils.isEmpty(mSelectDay)) {
                    ToastUtil.showToast(this, "请选择服务时间");
                    return;
                }
                if (TextUtils.isEmpty(numberContent.getText().toString())) {
                    ToastUtil.showToast(this, "请选择服务数量");
                    return;
                }
                goCommitInfo();
                break;
            case R.id.number_jian: //减
                doNumLogic(numberContent, false);
                break;
            case R.id.number_jia: //加
                doNumLogic(numberContent, true);
                break;

        }
    }

    //获取技能选择的数据
    private void getGameType() {
        RxUtils.loading(commonModel.orderUserSkillList(mUserId), this)
                .subscribe(new ErrorHandleSubscriber<ConfirmOrderSkillBean>(mErrorHandler) {
                    @Override
                    public void onNext(ConfirmOrderSkillBean confirmOrderSkillBean) {
                        SelectGameTypeDialogTwo selectGameTypeDialogTwo = new SelectGameTypeDialogTwo(ConfirmOrderActivity.this, (ArrayList<ConfirmOrderSkillBean.DataBean>) confirmOrderSkillBean.getData());
                        selectGameTypeDialogTwo.show();
                        selectGameTypeDialogTwo.setOnItemSelectListener(new SelectGameTypeDialogTwo.onItemClickListener() {
                            @Override
                            public void onSegmentItemClick(ConfirmOrderSkillBean.@NotNull DataBean price) {
                                if (!mSkillId.equals(price.getSkill_id())) {
                                    mSkillId = price.getSkill_id();
                                    mId = String.valueOf(price.getSkill_apply_id());
                                    skillChoiceContent.setText(price.getName());
                                    orderPrice.setText(String.valueOf(price.getPrice()));
                                    orderUnit.setText("/" + price.getUnit());
                                    if (zhuanYongBean != null) {
                                        totalPrice.setText(String.valueOf(price.getPrice() - zhuanYongBean.getPrice()));
                                    } else {
                                        totalPrice.setText(String.valueOf(price.getPrice()));
                                    }
//                                    totalPrice.setText(String.valueOf(price.getPrice()));
                                    mPrice = price.getPrice();
                                    totalNum.setText(1 + "");
                                    numberContent.setText("1");
                                    mSelectGameId = String.valueOf(price.getSkill_apply_id());
                                }
                            }
                        });
                    }
                });
    }

    //获取优惠券的数据
    private void getData() {
        RxUtils.loading(commonModel.my_coupontwo("1", 1), this)
                .subscribe(new ErrorHandleSubscriber<ChoiceCouponBean>(mErrorHandler) {
                    @Override
                    public void onNext(ChoiceCouponBean choiceCouponBean) {
                        mCouponData = choiceCouponBean.getData();
                        if (choiceCouponBean.getData().size() == 0) {
                            discountContent.setText("暂无优惠券");
                        } else {
                            discountContent.setText("有" + choiceCouponBean.getData().size() + "张优惠券");
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                    }
                });
    }

    private final void showTimeDialog() {
        ArrayList<PaiDanTimeBean> dayList = new ArrayList<>();
        ArrayList<PaiDanTimeBean> hourList = new ArrayList<>();
        ArrayList<PaiDanTimeBean> minateList = new ArrayList<>();
        PaiDanTimeBean paiDanTimeBean = new PaiDanTimeBean();
        paiDanTimeBean.setId("1");
        paiDanTimeBean.setName("今天");
        paiDanTimeBean.setUnit("");
        dayList.add(paiDanTimeBean);
        PaiDanTimeBean paiDanTimeBean1 = new PaiDanTimeBean();
        paiDanTimeBean1.setId("2");
        paiDanTimeBean1.setName("明天");
        paiDanTimeBean.setUnit("");
        dayList.add(paiDanTimeBean1);
        PaiDanTimeBean paiDanTimeBean2 = new PaiDanTimeBean();
        paiDanTimeBean2.setId("3");
        paiDanTimeBean2.setName("后天");
        paiDanTimeBean.setUnit("");
        dayList.add(paiDanTimeBean2);


        for (int i = 0; i < 24; ++i) {
            PaiDanTimeBean paiDanTimeBean3 = new PaiDanTimeBean();
            paiDanTimeBean3.setId(String.valueOf(i));
            if (i < 10) {
                paiDanTimeBean3.setName("0" + String.valueOf(i));
            } else {
                paiDanTimeBean3.setName(String.valueOf(i));
            }

            paiDanTimeBean3.setUnit("时");
            hourList.add(paiDanTimeBean3);
        }

        Set mite = SetsKt.setOf(new String[]{"00", "15", "30", "45"});
        Iterator var14 = mite.iterator();

        while (var14.hasNext()) {
            String i = (String) var14.next();
            PaiDanTimeBean paiDanTimeBean4 = new PaiDanTimeBean();
            paiDanTimeBean4.setId(i);
            paiDanTimeBean4.setName(i);
            paiDanTimeBean4.setUnit("分");
            minateList.add(paiDanTimeBean4);
        }

        int hourSize = hourList.size();
        List<PaiDanTimeBean> hourOldList1 = Arrays.asList(new PaiDanTimeBean[hourSize]);
        ArrayList<PaiDanTimeBean> hourOldList = new ArrayList<>(hourOldList1);
        Collections.copy(hourOldList, hourList);

        int minateSize = minateList.size();
        List<PaiDanTimeBean> minateOldList1 = Arrays.asList(new PaiDanTimeBean[minateSize]);
        ArrayList<PaiDanTimeBean> minateOldList = new ArrayList<>(minateOldList1);
        Collections.copy(minateOldList, minateList);

        getAfterNowTime(dayList, hourList, minateList);

        ArrayList<ArrayList<PaiDanTimeBean>> hourTwo = new ArrayList<ArrayList<PaiDanTimeBean>>();
        for (int item = 0; item < dayList.size(); item++) {
            if (item == 0) {
                hourTwo.add(hourList);
            } else {
                hourTwo.add((ArrayList<PaiDanTimeBean>) hourOldList);
            }
        }
        ArrayList<ArrayList<ArrayList<PaiDanTimeBean>>> lastDayList = new ArrayList<ArrayList<ArrayList<PaiDanTimeBean>>>();

        for (int item = 0; item < hourTwo.size(); item++) {
            ArrayList<ArrayList<PaiDanTimeBean>> lastHList = new ArrayList<ArrayList<PaiDanTimeBean>>();
            lastDayList.add(lastHList);
            ArrayList<PaiDanTimeBean> hList = hourTwo.get(item);
            if (item == 0) {
                for (int mm = 0; mm < hList.size(); mm++) {
                    if (mm == 0) {
                        lastHList.add(minateList);
                    } else {
                        lastHList.add((ArrayList<PaiDanTimeBean>) minateOldList);
                    }
                }
            } else {
                for (int mm = 0; mm < hList.size(); mm++) {
                    {
//                    var minateLast:java.util.ArrayList<java.util.ArrayList<PaiDanTimeBean>> = ArrayList<ArrayList<PaiDanTimeBean>>()
//                    minateLast.add(minateOldList as ArrayList<PaiDanTimeBean>)
//                        lastHList.add(minateOldList)
                        lastHList.add((ArrayList<PaiDanTimeBean>) minateOldList);
                    }
                }

            }
        }

        OptionsPickerView pvOptions = (new OptionsPickerBuilder(this, (OnOptionsSelectListener) (new OnOptionsSelectListener() {
            public void onOptionsSelect(int options1, int option2, int options3, @org.jetbrains.annotations.Nullable View v) {
                String txt = dayList.get(options1).getName() + hourList.get(option2).getName() + ":" + minateList.get(options3).getName();
                String selectDay = dayList.get(options1).getId();
//                String selectHour = hourList.get(option2).getName();
//                String selectMinate = minateList.get(options3).getName();
                String selectHour = hourTwo.get(options1).get(option2).getName();
                String selectMinate = lastDayList.get(options1).get(option2).get(options3).getName();

                if ("1".equals(selectDay)) {
                    boolean small = isSmallCurrentTime(selectDay, selectHour, selectMinate);
                    if (small) {
                        return;
                    }
                }

                mSelectDay = dayList.get(options1).getId();
                mSelectHour = hourList.get(option2).getName();
                mSelectMinate = minateList.get(options3).getName();
                serviceTimeContent.setText(txt);
            }
        }))).isDialog(true).build();
//        pvOptions.setNPicker(dayList, hourList, minateList);

        Dialog mDialog = pvOptions.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvOptions.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }

        pvOptions.setPicker(dayList, hourTwo, lastDayList);
        pvOptions.show();
    }

    //是否小于当前时间
    private boolean isSmallCurrentTime(String selectDay, String selectHour, String selectMinate) {
        String var10000 = TimeSwitchUtil.getNowString((DateFormat) (new SimpleDateFormat("yyyy-MM-dd")));
        Intrinsics.checkExpressionValueIsNotNull(var10000, "TimeSwitchUtil.getNowStr…DateFormat(\"yyyy-MM-dd\"))");
        String norDayString = var10000;
        norDayString = norDayString + " " + selectHour + ":" + selectMinate;
        LogUtils.debugInfo("norDayString==" + norDayString);
        long norDayTimeSpan = TimeSwitchUtil.string2Millis(norDayString, (DateFormat) (new SimpleDateFormat("yyyy-MM-dd HH:mm")));
        LogUtils.debugInfo("norDayString=时间戳=" + norDayTimeSpan);
        long nowTimeString = TimeSwitchUtil.getNowMills();
        LogUtils.debugInfo("当前时间戳=" + nowTimeString);
        if (nowTimeString - norDayTimeSpan >= 0L) {
            ToastUtil.showToast(this, "不能小于当前时间");
            return true;
        } else {
            return false;
        }
    }

    /**
     * 数量的加减
     *
     * @param isAdd
     */
    private void doNumLogic(TextView tvNum, boolean isAdd) {

        String numS = tvNum.getText().toString().trim();

        if (TextUtils.isEmpty(numS)) {
            tvNum.setText("1");
            return;
        }
        long num = Long.valueOf(numS);
        if (isAdd) {
            num += 1;
        } else {
            if (num <= 1) num = 1;
            else
                num -= 1;
        }
        tvNum.setText(num + "");
        totalNum.setText(num + "");
        if (zhuanYongBean != null) {
            totalPrice.setText(String.valueOf(mPrice * (num - 1) + (mPrice - zhuanYongBean.getPrice())));
        } else {
            totalPrice.setText(String.valueOf(mPrice * num));
        }
    }

    private void getEditTextStr() {
        TextNumUtil.initTextNum(ConfirmOrderActivity.this, remarksEdit, remarksNum);

        //实时记录公告字数
        remarksEdit.addTextChangedListener(new TextWatcher() {
            //记录输入的字数
            private CharSequence wordNum;
            private int selectionStart;
            private int selectionEnd;
            //记录空格跟换行的次数；
            private int kh = 0;
            String regex = "\\n";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//实时记录输入的字数
//                Pattern pattern = Pattern.compile(regex);
//                Matcher matcher = pattern.matcher(s);
//                if (s.equals("") || matcher.matches()) {
//                    kh++;
//                }
//                LogUtils.debugInfo("====数量数量",kh+"");
                wordNum = s;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                s.toString().trim().replace(" ","");
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(s.toString());
                String strAfter = m.replaceAll("");
//                s.toString().trim().replaceAll(" ","").length()
                int number = num + strAfter.length();
                //TextView显示剩余字数
                remarksNum.setText(number + "");
                selectionStart = remarksEdit.getSelectionStart();
                selectionEnd = remarksNum.getSelectionEnd();
                //判断大于最大值
//                int length = wordNum.length();
//                int i = length - kh;
                if (wordNum.length() > mMaxNum) {
                    //删除多余输入的字（不会显示出来）
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionEnd;
                    remarksEdit.setText(s);
                    remarksEdit.setSelection(tempSelection);//设置光标在最后
//                    roomSetGgNum.setText("300");
                    //吐司最多输入300字
                    toast("最多输入300字");
                }
            }
        });

//        //键盘弹起，布局上移
//        roomSetGgEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
////                roomSetPw.clearFocus();
////                roomSetName.clearFocus();
//                if (hasFocus) {
//                    mView.getViewTreeObserver().addOnGlobalLayoutListener(listener);
//                } else {
//                    mView.scrollTo(0, 0);
//                    mView.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
//                }
//            }
//        });
    }

    private void goCommitInfo() {

        long norDayTimeSpan = 0;

        String norDayString = TimeSwitchUtil.getNowString(new SimpleDateFormat("yyyy-MM-dd"));

        norDayString = norDayString + " " + mSelectHour + ":" + mSelectMinate;

        if (mSelectDay.equals("1")) {//今天

            LogUtils.debugInfo("norDayString==" + norDayString);

            norDayTimeSpan = TimeSwitchUtil.string2Millis(norDayString, new SimpleDateFormat("yyyy-MM-dd HH:mm")) / 1000;

            LogUtils.debugInfo("今天的=时间戳=" + norDayTimeSpan);

        } else if (mSelectDay.equals("2")) {//明天

            //第一步获取今天对应的时间

            //第二步，获取24小时对应的时间戳
            long twoTimeStr = 24 * 60 * 60 * 1000;

            Date date = TimeSwitchUtil.getDate(norDayString, new SimpleDateFormat("yyyy-MM-dd HH:mm"), twoTimeStr, TimeConstants.MSEC);

            String time = TimeSwitchUtil.date2String(date, new SimpleDateFormat("yyyy-MM-dd HH:mm"));

            LogUtils.debugInfo("明天的=时间=" + time);

            norDayTimeSpan = TimeSwitchUtil.date2Millis(date) / 1000;

            LogUtils.debugInfo("明天的=时间戳=" + norDayTimeSpan);

        } else if (mSelectDay.equals("3")) {//后天

            long twoTimeStr = 48 * 60 * 60 * 1000;

            Date date = TimeSwitchUtil.getDate(norDayString, new SimpleDateFormat("yyyy-MM-dd HH:mm"), twoTimeStr, TimeConstants.MSEC);

            String time = TimeSwitchUtil.date2String(date, new SimpleDateFormat("yyyy-MM-dd HH:mm"));

            LogUtils.debugInfo("后天的=时间=" + time);

            norDayTimeSpan = TimeSwitchUtil.date2Millis(date) / 1000;
            LogUtils.debugInfo("后天的=时间戳=" + norDayTimeSpan);

        }

        if (zhuanYongBean != null) {
            couponId = zhuanYongBean.getId();
        } else {
            couponId = "";
        }

        //开始派单
        RxUtils.loading(commonModel.go_add_order(mId, String.valueOf(norDayTimeSpan), numberContent.getText().toString(), remarksEdit.getText().toString(), couponId), this)
                .subscribe(new ErrorHandleSubscriber<Orderbean>(mErrorHandler) {
                    @Override
                    public void onNext(Orderbean orderbean) {
                        paymentWindow = new PaymentWindow(ConfirmOrderActivity.this, commonModel, mErrorHandler, myId, totalPrice.getText().toString(), orderbean.getData().getOrder_id());
                        paymentWindow.show();
                    }
                });
    }

    private final void getAfterNowTime(ArrayList<PaiDanTimeBean> dayList, ArrayList<PaiDanTimeBean> hourList, ArrayList<PaiDanTimeBean> minateList) {
        String var10000 = TimeSwitchUtil.getNowString((DateFormat) (new SimpleDateFormat("HH:mm")));
        Intrinsics.checkExpressionValueIsNotNull(var10000, "TimeSwitchUtil.getNowStr…impleDateFormat(\"HH:mm\"))");
//        String norDayString = var10000;
        String norDayString = TimeSwitchUtil.getNowString(new SimpleDateFormat("HH:mm"));
//        List times = StringsKt.split$default((CharSequence)norDayString, new String[]{":"}, false, 0, 6, (Object)null);
        List<String> times = Arrays.asList(norDayString.split(":"));
        String hour = (String) times.get(0); //当前是几点
        String minate = (String) times.get(1); //当前是几分钟
        boolean var9 = false;
        int h;
        boolean var10;
        String var11;
        boolean var12;
        int p;
        int var14;
        int var16;
        PaiDanTimeBean item;
        String var10001;
        Set mite;
        Iterator var19;
        String i;
        int position;
        boolean var22;
        ArrayList newList;
        PaiDanTimeBean paiDanTimeBean;
        Iterator var25;
        if (Integer.parseInt(minate) >= 26) {
            var10 = false;
            h = Integer.parseInt(hour) + 1;
            if (h <= 23) {
                var19 = hourList.iterator();

                while (var19.hasNext()) {
                    item = (PaiDanTimeBean) var19.next();
                    var10001 = item.getName();
                    if (var10001 == null) {
                        Intrinsics.throwNpe();
                    }

                    var11 = var10001;
                    var12 = false;
                    var16 = Integer.parseInt(var11);
                    if (h == var16) {
                        position = hourList.indexOf(item);
                        if (position < 23) {
                            newList = new ArrayList();
                            p = position;
                            var14 = hourList.size() - 1;
                            if (position <= var14) {
                                while (true) {
                                    newList.add(hourList.get(p));
                                    if (p == var14) {
                                        break;
                                    }

                                    ++p;
                                }
                            }

                            hourList.clear();
                            hourList.addAll((Collection) newList);
                            break;
                        }
                    }
                }

                label117:
                {
                    mite = SetsKt.setOf(new String[]{"15", "30", "45"});
                    var22 = false;
                    if (Integer.parseInt(minate) >= 26) {
                        var22 = false;
                        if (Integer.parseInt(minate) <= 40) {
                            minateList.clear();
                            mite = SetsKt.setOf(new String[]{"00", "15", "30", "45"});
                            break label117;
                        }
                    }

                    var22 = false;
                    if (Integer.parseInt(minate) >= 41) {
                        var22 = false;
                        if (Integer.parseInt(minate) <= 55) {
                            minateList.clear();
                            mite = SetsKt.setOf(new String[]{"15", "30", "45"});
                            break label117;
                        }
                    }

                    var22 = false;
                    if (Integer.parseInt(minate) >= 56) {
                        var22 = false;
                        if (Integer.parseInt(minate) <= 59) {
                            minateList.clear();
                            mite = SetsKt.setOf(new String[]{"30", "45"});
                        }
                    }
                }

                var25 = mite.iterator();

                while (var25.hasNext()) {
                    i = (String) var25.next();
                    paiDanTimeBean = new PaiDanTimeBean();
                    paiDanTimeBean.setId(i);
                    paiDanTimeBean.setName(i);
                    paiDanTimeBean.setUnit("分");
                    minateList.add(paiDanTimeBean);
                }
            } else {
                Intrinsics.checkExpressionValueIsNotNull(dayList.remove(0), "dayList.removeAt(0)");
            }
        } else {
            var10 = false;
            h = Integer.parseInt(hour);
            var19 = hourList.iterator();

            while (var19.hasNext()) {
                item = (PaiDanTimeBean) var19.next();
                var10001 = item.getName();
                if (var10001 == null) {
                    Intrinsics.throwNpe();
                }

                var11 = var10001;
                var12 = false;
                var16 = Integer.parseInt(var11);
                if (h == var16) {
                    position = hourList.indexOf(item);
                    if (position < 23) {
                        newList = new ArrayList();
                        p = position;
                        var14 = hourList.size() - 1;
                        if (position <= var14) {
                            while (true) {
                                newList.add(hourList.get(p));
                                if (p == var14) {
                                    break;
                                }

                                ++p;
                            }
                        }

                        hourList.clear();
                        hourList.addAll((Collection) newList);
                        break;
                    }
                }
            }

            label65:
            {
                mite = SetsKt.setOf(new String[]{"15", "30", "45"});
                var22 = false;
                if (Integer.parseInt(minate) >= 0) {
                    var22 = false;
                    if (Integer.parseInt(minate) <= 10) {
                        minateList.clear();
                        mite = SetsKt.setOf(new String[]{"30", "45"});
                        break label65;
                    }
                }

                var22 = false;
                if (Integer.parseInt(minate) >= 11) {
                    var22 = false;
                    if (Integer.parseInt(minate) <= 25) {
                        minateList.clear();
                        mite = SetsKt.setOf("45");
                    }
                }
            }

            var25 = mite.iterator();

            while (var25.hasNext()) {
                i = (String) var25.next();
                paiDanTimeBean = new PaiDanTimeBean();
                paiDanTimeBean.setId(i);
                paiDanTimeBean.setName(i);
                paiDanTimeBean.setUnit("分");
                minateList.add(paiDanTimeBean);
            }
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
        if (MILICHONGZHI.equals(tag)) {
            if (paymentWindow != null) {
                paymentWindow.dismiss();
            }
            finish();
        } else if (XUANCOUPON.equals(tag)) {
            zhuanYongBean = event.getZhuanYongBean();
            if (zhuanYongBean != null) {
                discountContent.setText(zhuanYongBean.getName());
                mPos = String.valueOf(zhuanYongBean.getPosition());
                totalPrice.setText(((mPrice - zhuanYongBean.getPrice()) * Integer.parseInt(numberContent.getText().toString())) + "");
            } else {
                discountContent.setText("有" + mCouponData.size() + "张优惠券");
                mPos = "";
                totalPrice.setText((mPrice * Integer.parseInt(numberContent.getText().toString())) + "");
            }
        }
    }

}
