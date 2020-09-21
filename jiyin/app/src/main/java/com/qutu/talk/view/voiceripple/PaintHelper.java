package com.qutu.talk.view.voiceripple;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;

import com.qutu.talk.R;


public class PaintHelper {

    public static Paint getArcPaint(Context context,int color) {
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(context, color));
        paint.setStrokeWidth(20);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setStyle(Paint.Style.STROKE);
        return paint;
    }
    public static Paint getDefaultRipplePaint(Context context,int color) {
        Paint ripplePaint = new Paint();
        ripplePaint.setStyle(Paint.Style.FILL);
        ripplePaint.setColor(ContextCompat.getColor(context, color));
        ripplePaint.setAntiAlias(true);

        return ripplePaint;
    }

    public static  Paint getDefaultRippleBackgroundPaint(Context context,int color) {
        Paint rippleBackgroundPaint = new Paint();
        rippleBackgroundPaint.setStyle(Paint.Style.FILL);
        rippleBackgroundPaint.setColor((ContextCompat.getColor(context, color) & 0x00FFFFFF) | 0x40000000);
        rippleBackgroundPaint.setAntiAlias(true);

        return rippleBackgroundPaint;
    }

    public static  Paint getButtonPaint(Context context,int color) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(ContextCompat.getColor(context, color));
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }
}
