package com.qutu.talk.bean

data class GetGoodAtPositionListResult(
    var code: Int,
    var `data`: List<GameGoodAtPositionItem>,
    var message: String
)