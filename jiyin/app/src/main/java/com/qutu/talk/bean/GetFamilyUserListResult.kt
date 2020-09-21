package com.qutu.talk.bean

data class GetFamilyUserListResult(
    var code: Int,
    var `data`: List<FamilyUser>,
    var message: String
)
