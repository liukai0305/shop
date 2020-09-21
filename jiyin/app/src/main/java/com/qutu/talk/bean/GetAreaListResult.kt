package com.qutu.talk.bean

data class GetAreaListResult(
    var code: Int,
    var `data`: List<GameAreaItem>,
    var message: String
)