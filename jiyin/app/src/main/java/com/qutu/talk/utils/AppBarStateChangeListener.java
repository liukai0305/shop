package com.qutu.talk.utils;

import android.support.design.widget.AppBarLayout;

import com.jess.arms.utils.LogUtils;

public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {
    public enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    private State mCurrentState = State.IDLE;

    @Override
    public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i == 0) {
//            if (mCurrentState != State.EXPANDED) {
            mCurrentState = State.EXPANDED;
            onStateChanged(appBarLayout, State.EXPANDED, i);
//            }

        } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
//            if (mCurrentState != State.COLLAPSED) {
            mCurrentState = State.COLLAPSED;
            onStateChanged(appBarLayout, State.COLLAPSED, i);
//            }

        } else {
//            if (mCurrentState != State.IDLE) {
            mCurrentState = State.IDLE;
            onStateChanged(appBarLayout, State.IDLE, i);
//            }
            LogUtils.debugInfo("====中间++++++++++" + i);

        }
    }

    public abstract void onStateChanged(AppBarLayout appBarLayout, State state, int verticalOffset);
}
