package com.qutu.talk.bean;

import android.animation.TypeEvaluator;

import com.jess.arms.utils.LogUtils;

public class CircleBeanEvaluator implements TypeEvaluator {

    float distance;
    float startW;
    float startAlpha = 200;
    public CircleBeanEvaluator(float distance,float startW){
        this.distance = distance;
        this.startW = startW;
//        LogUtils.debugInfo("距离===="+distance);
    }
    @Override
    public Object evaluate(float v, Object startValue, Object endValue) {

        CircleBean startBean = (CircleBean) startValue;
//
//        CircleBean endBean = (CircleBean) endValue;

        float width = startW+ v*distance;


        double alpha = startAlpha-v*200;
//        double alpha = startAlpha;

        CircleBean newCircleBean = new CircleBean(startBean.id,width, alpha, startBean.getMaxWidth());

        return newCircleBean;
    }
}
