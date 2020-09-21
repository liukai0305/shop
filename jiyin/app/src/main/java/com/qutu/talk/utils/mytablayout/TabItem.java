package com.qutu.talk.utils.mytablayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.qutu.talk.R;

public class TabItem extends View {
    public final CharSequence text;
    public final Drawable icon;
    public final int customLayout;

    public TabItem(Context context) {
        this(context, (AttributeSet)null);
    }

    @SuppressLint("RestrictedApi")
    public TabItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        @SuppressLint("RestrictedApi") TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.TabItem);
        this.text = a.getText(R.styleable.TabItem_android_text);
        this.icon = a.getDrawable(R.styleable.TabItem_android_icon);
        this.customLayout = a.getResourceId(R.styleable.TabItem_android_layout, 0);
        a.recycle();
    }
}
