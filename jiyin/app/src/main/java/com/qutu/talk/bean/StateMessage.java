package com.qutu.talk.bean;

/**
 * 消息类型
 */
public enum StateMessage {


    END_PAIDAN(6),//截止派单
    START_PAIDAN(5),//开始派单
    END_CALL(4),//挂断电话
    MUSIC_CHANGE(3),//音乐列表改变
    CLOSE_GIFT_WINDOW(2),//关闭礼物弹窗
    PEOPLE_OPEN_GEMSTONE(1),//有人开宝箱
    SEND_GEMSTONE(0);//发送宝石

    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    StateMessage(int state) {
        this.state = state;
    }
}
