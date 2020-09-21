package com.qutu.talk.bean

data class GetGameSetResult(
    var code: Int,
    var `data`: GameSetting,
    var message: String
)

data class GameSetting(
    var areas: String,
    var positions: String,
    var price: String,
    var status: String,
    var unit: String
)