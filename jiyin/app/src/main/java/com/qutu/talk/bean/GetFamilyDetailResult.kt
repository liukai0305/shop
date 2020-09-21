package com.qutu.talk.bean

data class GetFamilyDetailResult(
        var code: Int,
        var `data`: FamilyDetail,
        var message: String
)

data class FamilyDetail(
        var family_id: String,
        var family_user_list: List<FamilyUser>,
        var image: String,
        var is_show: Int,//申请家族按钮是否显示 0=不显示 1=显示
        var name: String,
        var num: String,
        var suctime: String,//创建成功时间 尚未创建成功为空字符串
        var introduce: String, //家族介绍
        var notice: String, //家族公告
        var is_edit: Int, //编辑按钮是否显示
        var user_type: String, //自己的身份
        var paiming:Int,//排名
        var exp:Int,//经验
        var family_pdinfo:List<FamilyPdinfo>,
        var jzid:String,
        var is_success:Int

)

data class FamilyPdinfo(
        var uid:Int,
        var room_name:String,
        var room_cover:String,
        var room_intro:String
)

data class FamilyUser(
        var family_user_id: String,
        var headimgurl: String,
        var nickname: String,
        var user_id: String,
        var sex: Int,// 1男2女
        var user_type: Int//成员身份 0=普通成员 1=管理员 2=族长
)