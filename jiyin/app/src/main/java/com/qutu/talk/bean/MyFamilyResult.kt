package com.qutu.talk.bean

data class MyFamilyResult(
    var code: Int,
    var `data`: MyFamilyItem,
    var message: String
)

data class MyFamilyItem(
    var family_id: String,
    var image: String,
    var name: String,
    var num: String
)