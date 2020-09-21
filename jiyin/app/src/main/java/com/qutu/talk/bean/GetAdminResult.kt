package com.qutu.talk.bean

data class GetAdminResult(
    var code: Int,
    var `data`: RoomUserList,
    var message: String
)

data class RoomUserList(
    var admin_list: List<FamilyUser>,
    var user_list: List<FamilyUser>
)