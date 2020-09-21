package com.qutu.talk.popup

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.qutu.talk.R
import com.qutu.talk.adapter.GameSegmentAdapter
import com.qutu.talk.bean.SegmentItem
import com.qutu.talk.service.CommonModel
import kotlinx.android.synthetic.main.dialog_edite_family_user.*
import kotlinx.android.synthetic.main.dialog_select_game_price.tv_cancel
import kotlinx.android.synthetic.main.dialog_select_game_segment.*
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import java.util.*

class FamilyModifyImageDialog : Dialog {

    var mContext: Context? = null


    var mOnSelctItemListner: onItemClickListener? = null

    constructor(context: Context) : super(context, R.style.myChooseDialog) {
        mContext = context
    }

    fun setOnItemSelectListener(listener: onItemClickListener) {
        mOnSelctItemListner = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.family_modif_image_item)

        setWidows()

        tv_cancel.setOnClickListener {
            dismiss()
        }

        tv_add_member.setOnClickListener {
            if (mOnSelctItemListner != null) {
                mOnSelctItemListner!!.onAddMemeber()
            }
        }

    }

    private fun setWidows() {

        val display = window!!.windowManager.defaultDisplay

        val lp = window!!.attributes

        lp.width = display.width

        lp.alpha = 1.0f

        lp.gravity = Gravity.BOTTOM

        window!!.attributes = lp

        this.setCanceledOnTouchOutside(true)

    }

    interface onItemClickListener {
        fun onAddMemeber()
    }

}