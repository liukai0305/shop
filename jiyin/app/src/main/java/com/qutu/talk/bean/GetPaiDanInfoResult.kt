package com.qutu.talk.bean

data class GetPaiDanInfoResult(
    var code: Int,
    var `data`: Data,
    var message: String
)

data class Data(
    var remark: String,
    var room_name: String,
    var skill_id: String,
    var skill_name: String,
    var time: String,
    var id: String,
    var uid: String
)