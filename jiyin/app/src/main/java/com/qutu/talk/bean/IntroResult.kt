package com.qutu.talk.bean

data class IntroResult(
    var code: Int,
    var `data`: IntroFamily,
    var message: String
)

data class IntroFamily(
    var url: String
)