package com.qutu.talk.bean

/**
 * 消息类型
 */
enum class GameType private constructor(//发送宝石

        var state: Int) {

    GAME_JUE_DI(4), //绝地求生
    GAME_HE_PING(2), //和平精英
    GAME_WANG_ZHE(1), //王者荣耀
    GAME_LOL(3)//英雄联盟
}
