package com.qutu.talk.bean

data class GetPriceListResult(
    var code: Int,
    var `data`: List<GamePriceItem>,
    var message: String
)
