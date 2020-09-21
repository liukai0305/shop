package com.qutu.talk.bean

data class FamilySettingResult(
    var code: Int,
    var `data`: FamilySetting,
    var message: String
)

data class FamilySetting(
    var admin_num: String,//管理员数量
    var closeswitch: Int,//屏蔽家族消息0=关闭 1=开启
    var family_id: String,
    var name: String,
    var speakswitch: Int,//成员禁言 0=关闭 1=开启
    var user_type: Int,//用户身份 0=普通 1=管理 2=族长
    var user_num: String//家族成员数量
)