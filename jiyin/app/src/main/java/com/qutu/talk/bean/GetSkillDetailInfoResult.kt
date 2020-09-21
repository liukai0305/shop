package com.qutu.talk.bean

data class GetSkillDetailInfoResult(
    var code: Int,
    var `data`: SkillDetailInfo,
    var message: String
)

data class SkillDetailInfo(
    var audio: String,
    var image: String,
    var introduce: String,
    var skill_id: String,
    var skill_level_id: String,
    var skill_level_name: String,
    var skill_name: String,
    var audio_time: String=""
)