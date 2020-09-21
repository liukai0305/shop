package com.qutu.talk.bean

data class FamilyListResult(
    var code: Int,
    var `data`: List<FamilyItem>,
    var message: String
)

data class FamilyItem(
    var family_id: String,
    var image: String,
    var is_show: Int,
    var name: String,
    var nickname: String,
    var num: String,
    var user_id: String,
    var exp:Int
)