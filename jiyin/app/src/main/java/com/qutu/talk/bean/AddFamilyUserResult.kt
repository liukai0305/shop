package com.qutu.talk.bean

data class AddFamilyUserResult(
    var code: Int,
    var `data`: List<UserAddItem>,
    var message: String
)

data class UserAddItem(
    var user_id: String,

    var family_user_id: String,
    var user_type: Int,

    var nickname: String,
    var headimgurl: String,//完成时间
    var is_god: Int,//是否大神 0=非大神 1=大神
    var is_friend: Int,//是否好友0=非好友 1=好友
    var sex: Int,//1男2女
    var checked: Boolean


)