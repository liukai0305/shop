package com.qutu.talk.bean

data class CreatFamilyResult(
    var code: Int,
    var `data`: CreateFamilySuccess,
    var message: String
)

data class CreateFamilySuccess(
    var family_id: String
)