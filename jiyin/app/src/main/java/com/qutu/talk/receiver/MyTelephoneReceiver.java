package com.qutu.talk.receiver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

import com.jess.arms.utils.LogUtils;
import com.qutu.talk.bean.MessageEvent;
import com.qutu.talk.bean.StateMessage;

import org.greenrobot.eventbus.EventBus;

public class MyTelephoneReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtils.debugInfo("我的广播==电话状态监听===");
        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {//如果是拨打电话

            LogUtils.debugInfo("拨打电话了===");

        } else {

            TelephonyManager telMgrtelMgr = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);

            switch (telMgrtelMgr.getCallState()) {
                //来电
                case TelephonyManager.CALL_STATE_RINGING:
                    LogUtils.debugInfo("来电话了===");
                    break;
                //摘机
                case TelephonyManager.CALL_STATE_OFFHOOK://摘机状态，至少有个电话活动。该活动或是拨打（dialing）或是通话，或是 on hold。
                    LogUtils.debugInfo("电话摘机了===");
                    break;
                //挂断
                case TelephonyManager.CALL_STATE_IDLE:
                    LogUtils.debugInfo("挂断电话了===");
                    MessageEvent messageBean = new MessageEvent();
                    messageBean.setStateMessage(StateMessage.END_CALL);
                    EventBus.getDefault().post(messageBean);
                    break;

            }

        }
    }
}
