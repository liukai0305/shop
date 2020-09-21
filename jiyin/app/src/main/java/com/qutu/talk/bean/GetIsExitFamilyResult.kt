package com.qutu.talk.bean

data class GetIsExitFamilyResult(
    var code: Int,
    var `data`: IsFamilyExit,
    var message: String
)

data class IsFamilyExit(
    var is_family: Int
)