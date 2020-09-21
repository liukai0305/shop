package com.qutu.talk.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.qutu.talk.R;
import com.qutu.talk.bean.PushBean;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import kotlin.jvm.Synchronized;

public class MiniBarrageViewLayout extends LinearLayout {


    Context mContext;

    static final String NOT_ANIMATE = "not_animate";

    static final String IS_ANIMATE = "is_animate";

    int childCount = 1;//子view个数

    long moveTotalDuration = 8000;

    long leftToZeroDuration = 5000;

    int mScreenWidth = 0;

    int mDelayTime = 0;//延迟执行时间

    List<PushBean> mDataList = new Vector<>();

    boolean mStop = false;

    public MiniBarrageViewLayout(Context context) {
        this(context, null);
    }

    public MiniBarrageViewLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MiniBarrageViewLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(context);
    }

    private void init(Context context) {

        setOrientation(VERTICAL);
        setClipChildren(false);

        mScreenWidth = QMUIDisplayHelper.getScreenWidth(context);

        View viewChild;

        for (int i = 0; i < childCount; i++) {

            viewChild = LayoutInflater.from(context).inflate(R.layout.danmu, null);

//            ImageView imgGiftLeft = viewChild.findViewById(R.id.imgGift_left);
//
//            RelativeLayout mLayoutGift = viewChild.findViewById(R.id.layout_gift_notification);
//
//            RelativeLayout mLayoutGemStone = viewChild.findViewById(R.id.layout_gemstone_notification);
//
//            TextView mTvGift = viewChild.findViewById(R.id.tv_gift_info);
//
//            TextView mTvGemStone = viewChild.findViewById(R.id.tv_gemstone_info);

            viewChild.setTag(NOT_ANIMATE);

            LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.topMargin = 15;
            viewChild.setLayoutParams(params);

            this.addView(viewChild);
        }
    }

    public void setDataList(List<PushBean> list) {
        synchronized (this) {
            LogUtils.debugInfo("通知这list长度===" + list.size());
            for (int i = 0; i < list.size(); i++) {
                LogUtils.debugInfo("通知数据===" + list.get(i).getData().getGift_name());
            }
            mDataList.addAll(list);
            LogUtils.debugInfo("通知这长度===" + mDataList.size());
            startScroll();
        }
    }

    public synchronized void setData(PushBean pushBean) {
        mDataList.add(pushBean);
        LogUtils.debugInfo("通知了长度===" + mDataList.size());
        startScroll();
    }

    private void startScroll() {

        View viewChild;

        for (int i = 0; i < childCount; i++) {

            viewChild = getChildAt(i);

            String tag = (String) viewChild.getTag();

            if (IS_ANIMATE.equals(tag)) {
                continue;
            } else {//找到没有移动的view

//                synchronized (viewChild) {
                LogUtils.debugInfo("几次滚动===");
                getDataToAnimate(viewChild);
//                }

            }
        }
    }

    private synchronized void getDataToAnimate(View viewChild) {

        synchronized (mDataList) {

            if (mDataList.size() == 0 || mStop == true) {
                return;
            }

            boolean allAnimate = true;
            for (PushBean pushBean : mDataList) {
                if (!pushBean.isHadAnimate()) {
                    allAnimate = false;
                    viewChild.setX(mScreenWidth);//初始位置
                    pushBean.setHadAnimate(true);
                    if ("gift".equals(pushBean.type)) {

                        setGiftInfo(viewChild, pushBean);

                        startAnimate(viewChild, true);

                    } else if ("award".equals(pushBean.type)) {

                        setGemStoneInfo(viewChild, pushBean);

                        startAnimate(viewChild, false);

                    } else if ("quanmai".equals(pushBean.type)) {

                        setQuanMaiInfo(viewChild, pushBean);
                        LogUtils.debugInfo("====全麦哟", "数量" + pushBean.getData().getNum() + "礼物名称" + pushBean.getData().getGift_name() + "类型" + pushBean.getData().getBoxclass() + "送给谁" + pushBean.getData().getFrom_name() + "送礼的人" + pushBean.getData().getUser_name());

                        startAnimate(viewChild, true);
                    }
                    viewChild.setTag(IS_ANIMATE);
                    break;
                }
            }
            if (allAnimate) {
                mDataList.clear();
            }
        }
    }

    private synchronized void setGiftInfo(View viewChild, PushBean pushBean) {

//        LogUtils.debugInfo("设置礼物====");

        ImageView imgGiftLeft = viewChild.findViewById(R.id.imgGift_left);

        RelativeLayout mLayoutGift = viewChild.findViewById(R.id.layout_gift_notification);

        RelativeLayout mLayoutGemStone = viewChild.findViewById(R.id.layout_gemstone_notification);

        RelativeLayout mLayoutQuanMai = viewChild.findViewById(R.id.layout_quanmai_notification);

        TextView mTvGift = viewChild.findViewById(R.id.tv_gift_info);

        mLayoutGemStone.setVisibility(View.GONE);
        mLayoutQuanMai.setVisibility(View.GONE);
        mLayoutGift.setVisibility(View.VISIBLE);

        LinearLayout.LayoutParams params = (LayoutParams) mLayoutGift.getLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.WRAP_CONTENT;
        mLayoutGift.setLayoutParams(params);

        RelativeLayout.LayoutParams tvParams = (RelativeLayout.LayoutParams) mTvGift.getLayoutParams();
        tvParams.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
        tvParams.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        mTvGift.setLayoutParams(tvParams);

        mTvGift.setText("");

        mTvGift.setTextColor(mContext.getResources().getColor(R.color.white));

        mTvGift.append(new SpannableString("惊现土豪~"));

        SpannableString clickString = new SpannableString(pushBean.getData().getUser_name());

        clickString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {//点击事件

            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#ffde00"));//设置颜色
                ds.setUnderlineText(false);//去掉下划线
            }
        }, 0, clickString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mTvGift.append(clickString);

        mTvGift.append(new SpannableString("赠送给"));

        SpannableString clickString1 = new SpannableString(pushBean.getData().getFrom_name() + pushBean.getData().getGift_name() + "x" + pushBean.getData().getNum());

        clickString1.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {//点击事件

            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#ffde00"));//设置颜色
                ds.setUnderlineText(false);//去掉下划线
            }
        }, 0, clickString1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mTvGift.append(clickString1);

        mTvGift.setMovementMethod(LinkMovementMethod.getInstance());

        //设置超文本点击后的背景色为透明
        mTvGift.setHighlightColor(mContext.getResources().getColor(android.R.color.transparent));

//                mTvGift.setText("惊现土豪~"+pushBean.getData().getFrom_name()+"赠送给"+pushBean.getData().getUser_name()+pushBean.getData().getGift_name()+"x"+pushBean.getData().getNum());
//                wuYongOne.setText("惊现土豪~");
//                textUSer1.setText(pushBean.getData().getFrom_name());
//                textUSer2.setText(pushBean.getData().getUser_name());
////                    textNumber.setText(pushBean.getData().getNum() + "个");
//                box.setText("赠送给");
//                textGiftName.setText(pushBean.getData().getGift_name()+"x"+pushBean.getData().getNum());
        String imgUrls = pushBean.getData().getImg();
        LogUtils.debugInfo("收到开礼物通知了===imgUrls=" + imgUrls);

        if (!TextUtils.isEmpty(imgUrls)) {

            imgGiftLeft.setVisibility(View.VISIBLE);

            ArmsUtils.obtainAppComponentFromContext(mContext)
                    .imageLoader()
                    .loadImage(mContext, ImageConfigImpl
                            .builder()
                            .url(imgUrls)
//                                    .placeholder(R.mipmap.no_tu)
                            .imageView(imgGiftLeft)
//                                    .errorPic(R.mipmap.no_tu)
                            .build());

        } else {
            imgGiftLeft.setVisibility(View.GONE);
        }


    }

    private synchronized void setGemStoneInfo(View viewChild, PushBean pushBean) {


        RelativeLayout mLayoutGift = viewChild.findViewById(R.id.layout_gift_notification);

        RelativeLayout mLayoutGemStone = viewChild.findViewById(R.id.layout_gemstone_notification);

        RelativeLayout mLayoutQuanMai = viewChild.findViewById(R.id.layout_quanmai_notification);

        TextView mTvGemStone = viewChild.findViewById(R.id.tv_gemstone_info);

        mLayoutGemStone.setVisibility(View.VISIBLE);
        mLayoutGift.setVisibility(View.GONE);
        mLayoutQuanMai.setVisibility(View.GONE);

        LinearLayout.LayoutParams params = (LayoutParams) mLayoutGemStone.getLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.WRAP_CONTENT;
        mLayoutGemStone.setLayoutParams(params);

        RelativeLayout.LayoutParams tvParams = (RelativeLayout.LayoutParams) mTvGemStone.getLayoutParams();
        tvParams.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
        tvParams.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        mTvGemStone.setLayoutParams(tvParams);

        mTvGemStone.setText("");

        mTvGemStone.setTextColor(mContext.getResources().getColor(R.color.white));

//        mTvGemStone.append(new SpannableString("哇哦~"));

        SpannableString clickString = new SpannableString(pushBean.getData().getUser_name());

        clickString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {//点击事件

            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#ff3e6d"));//设置颜色
                ds.setUnderlineText(false);//去掉下划线
            }
        }, 0, clickString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mTvGemStone.append(clickString);

        mTvGemStone.append(new SpannableString("在家族贡献中获得了"));
//        if(pushBean.getData().getBoxclass().equals("1")){
//            mTvGemStone.append(new SpannableString("在" + "普通贡献中" + "开出了"));
//        }else{
//            mTvGemStone.append(new SpannableString("在" + "快速中" + "开出了"));
//        }
        SpannableString clickString1 = new SpannableString(pushBean.getData().getGift_name()+"("+pushBean.getData().getBoxclass()+"价值)");

        clickString1.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {//点击事件

            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#ff3e6d"));//设置颜色
                ds.setUnderlineText(false);//去掉下划线
            }
        }, 0, clickString1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mTvGemStone.append(clickString1);

        mTvGemStone.setMovementMethod(LinkMovementMethod.getInstance());

        //设置超文本点击后的背景色为透明
        mTvGemStone.setHighlightColor(mContext.getResources().getColor(android.R.color.transparent));

    }

    private synchronized void setQuanMaiInfo(View viewChild, PushBean pushBean) {

        RelativeLayout mLayoutGift = viewChild.findViewById(R.id.layout_gift_notification);

        RelativeLayout mLayoutGemStone = viewChild.findViewById(R.id.layout_gemstone_notification);

        RelativeLayout mLayoutQuanMai = viewChild.findViewById(R.id.layout_quanmai_notification);

        TextView mTvGemStone = viewChild.findViewById(R.id.tv_quanmai_info);

        mLayoutGemStone.setVisibility(View.GONE);
        mLayoutGift.setVisibility(View.GONE);
        mLayoutQuanMai.setVisibility(View.VISIBLE);

        LinearLayout.LayoutParams params = (LayoutParams) mLayoutQuanMai.getLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.WRAP_CONTENT;
        mLayoutQuanMai.setLayoutParams(params);

        RelativeLayout.LayoutParams tvParams = (RelativeLayout.LayoutParams) mTvGemStone.getLayoutParams();
        tvParams.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
        tvParams.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        mTvGemStone.setLayoutParams(tvParams);

        mTvGemStone.setText("");

        mTvGemStone.setTextColor(mContext.getResources().getColor(R.color.white));

        mTvGemStone.append(new SpannableString("感谢"));

        SpannableString clickString = new SpannableString(pushBean.getData().getUser_name());

        clickString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {//点击事件

            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#ff3e6d"));//设置颜色
                ds.setUnderlineText(false);//去掉下划线
            }
        }, 0, clickString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mTvGemStone.append(clickString);

        mTvGemStone.append(new SpannableString("在" + pushBean.getData().getBoxclass() + " 送出全麦 "));

        SpannableString clickString1 = new SpannableString(pushBean.getData().getGift_name() + "x" + pushBean.getData().getNum());

        clickString1.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {//点击事件

            }

            //
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#ff3e6d"));//设置颜色
                ds.setUnderlineText(false);//去掉下划线
            }
        }, 0, clickString1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mTvGemStone.append(clickString1);

        mTvGemStone.setMovementMethod(LinkMovementMethod.getInstance());

        //设置超文本点击后的背景色为透明
        mTvGemStone.setHighlightColor(mContext.getResources().getColor(android.R.color.transparent));

    }

    private synchronized void startAnimate(View view, boolean isStay) {

        AnimatorSet animatorSet = new AnimatorSet();

        if (isStay) {
            animatorSet.play(ZeroToLeftAnimate(view)).after(leftToZeroDuration + 2000).after(rightToZeroAnimate(view));
        } else {
            animatorSet.play(rightToLeftAnimate(view));
        }
        Random random = new Random();
        int number = (random.nextInt(3) + 1) * 1000;
        if (mDelayTime == number) {//防止和上一个重复
            number = number + 1000;
        }
        mDelayTime = number;
        animatorSet.setStartDelay(number);
        animatorSet.start();

    }

    private Animator rightToLeftAnimate(View view) {//从右往左消失
        view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        int width = view.getMeasuredWidth();
//        LogUtils.debugInfo("移动view宽====" + width);
        if (width > mScreenWidth) {
            moveTotalDuration = 10000;
        } else if (width <= (mScreenWidth / 2)) {
            moveTotalDuration = 5000;
        } else {
            moveTotalDuration = 8000;
        }
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(QMUIDisplayHelper.getScreenWidth(this.getContext()), -width);
        valueAnimator.setDuration(moveTotalDuration);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float curValue = (float) valueAnimator.getAnimatedValue();
//                LogUtils.debugInfo("动了=有到左=" + curValue);
                view.setX(curValue);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                view.setTag(NOT_ANIMATE);
                view.setX(mScreenWidth);
                if (mDataList.size() > 0) {
                    getDataToAnimate(view);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setTag(NOT_ANIMATE);
                view.setX(mScreenWidth);
                if (mDataList.size() > 0) {
                    getDataToAnimate(view);
                }
            }
        });
        return valueAnimator;
    }

    private Animator rightToZeroAnimate(View view) {//右到0
        int width = view.getWidth();
//        LogUtils.debugInfo("移动view宽===="+width);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(QMUIDisplayHelper.getScreenWidth(this.getContext()), 0);
        valueAnimator.setDuration(leftToZeroDuration);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float curValue = (float) valueAnimator.getAnimatedValue();
//                LogUtils.debugInfo("动了=右到0=" + curValue);
                view.setX(curValue);
            }
        });
        return valueAnimator;
    }

    private Animator ZeroToLeftAnimate(View view) {//0到左
        view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        int width = view.getMeasuredWidth();
//        LogUtils.debugInfo("移动view宽====" + width);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, -width);
        valueAnimator.setDuration(leftToZeroDuration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float curValue = (float) valueAnimator.getAnimatedValue();
//                LogUtils.debugInfo("动了=0到左=" + curValue);
                view.setX(curValue);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                view.setTag(NOT_ANIMATE);
                view.setX(mScreenWidth);
                if (mDataList.size() > 0) {
                    getDataToAnimate(view);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setTag(NOT_ANIMATE);
                view.setX(mScreenWidth);
                if (mDataList.size() > 0) {
                    getDataToAnimate(view);
                }
            }
        });
        return valueAnimator;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mStop = true;
    }
}
