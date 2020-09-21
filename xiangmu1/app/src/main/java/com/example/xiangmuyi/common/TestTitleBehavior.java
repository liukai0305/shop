package com.example.xiangmuyi.common;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.appbar.AppBarLayout;

public class TestTitleBehavior extends CoordinatorLayout.Behavior<View> {

    private int titleY;  //标题文本的高度

    public TestTitleBehavior(){}
    public TestTitleBehavior(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    /**
     *
     * @param parent 最外层
     * @param child  behavior相关联类
     * @param dependency 监听的类
     * @return
     */
    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependency instanceof NestedScrollView;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {

        if(titleY == 0){
            titleY = (int) (dependency.getY()-child.getHeight());
        }
        float dy = dependency.getY()-child.getHeight();
        dy = dy < 0 ? 0 :dy;
        float alpha = 1 - (dy/titleY);
        child.setAlpha(alpha);
        return true;
    }
}
