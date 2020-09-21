package com.qutu.talk.bean

import java.io.Serializable

data class OrderListResult(
    var code: Int,
    var `data`: List<OrderItem>,
    var message: String
)

data class OrderItem(
    var addtime: String,//下单时间
    var fee: String,
    var finishtime: String,//完成时间
    var god_id: String,
    var god_name: String,
    var headimgurl: String,
    var id: String,
    var is_discuss: Int,//是否评论过:1是0不是
    var is_first: Int,//是否申请过提前服务:1是0不是
    var num: String,
    var order_no: String,
    var pay_type: String,//支付方式
    var paytime: String,//支付时间
    var price: String,
    var real_price: String,
    var refund: String,//退还金额
    var refusetime: String,//退款时间
    var remarks: String,
    var skill_apply_id: String,
    var skill_id: String,
    var skill_img: String,//技能图标
    var skill_name: String,
    var start_time: String,//服务时间
    var status: Int,
    var status_text: String,
    var total_price: String,
    var unit: String,
    var user_id: String,
    var user_name: String
): Serializable