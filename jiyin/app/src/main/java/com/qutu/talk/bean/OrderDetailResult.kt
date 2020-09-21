package com.qutu.talk.bean

import java.io.Serializable

data class OrderDetailResult(
    var code: Int,
    var `data`: OrderDetail,
    var message: String
)

data class OrderDetail(
    var addtime: String,
    var fee: String,
    var finishtime: String,
    var god_id: String,
    var god_name: String,
    var id: String,
    var images: String,
    var is_discuss: Int,
    var is_first: Int,
    var num: String,
    var order_no: String,
    var out_refund_no: String,
    var pay_type: Int,
    var paytime: String,
    var price: String,
    var real_price: String,
    var reason: String,
    var refund: String,
    var refusetime: String,
    var remarks: String,
    var skill_apply_id: String,
    var skill_id: String,
    var skill_img: String,
    var skill_name: String,
    var start_time: String,
    var status: Int,
    var status_text: String,
    var total_price: String,
    var unit: String,
    var user_id: String,
    var headimgurl: String,
    var user_name: String,
    var sysj: String
):Serializable