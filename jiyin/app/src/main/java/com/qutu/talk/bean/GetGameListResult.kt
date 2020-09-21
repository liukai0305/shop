package com.qutu.talk.bean

data class GetGameListResult(
    var code: Int,
    var `data`: List<GameItemBean>,
    var message: String
)