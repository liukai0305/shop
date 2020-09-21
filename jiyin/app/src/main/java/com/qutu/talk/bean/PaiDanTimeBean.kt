package com.qutu.talk.bean

import com.contrarywind.interfaces.IPickerViewData

class PaiDanTimeBean: IPickerViewData {
    override fun getPickerViewText(): String {
        return (name+unit)
    }

    var id: String? = ""
    var name: String? = ""
    var unit:String? = ""
}