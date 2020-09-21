package com.qutu.talk.bean

data class SegmentListResult(
    var code: Int,
    var `data`: List<SegmentItem>,
    var message: String
)

data class SegmentItem(
    var id: String,
    var level: String,
    var name: String
)