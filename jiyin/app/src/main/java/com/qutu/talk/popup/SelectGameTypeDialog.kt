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
import com.qutu.talk.adapter.GameTypeAdapter
import com.qutu.talk.app.utils.RxUtils
import com.qutu.talk.bean.*
import com.qutu.talk.service.CommonModel
import kotlinx.android.synthetic.main.dialog_select_game_price.tv_cancel
import kotlinx.android.synthetic.main.dialog_select_game_segment.*
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import java.util.*

class SelectGameTypeDialog: Dialog {

    var mContext:Context? = null

    var mDataPriceList:ArrayList<GameItemBean>? = null

    var mAdapter: GameTypeAdapter? = null

    var mOnSelctItemListner:onItemClickListener? = null

    constructor(context: Context,data:ArrayList<GameItemBean>):super(context, R.style.myChooseDialog){
        mContext = context
        mDataPriceList = data
    }

    fun setOnItemSelectListener(listener:onItemClickListener){
        mOnSelctItemListner = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.dialog_select_game_segment)

        tv_price_title.setText("技能类型")

        setWidows()

        initRecyclerView()

        tv_cancel.setOnClickListener {
            dismiss()
        }


    }




    private fun initRecyclerView() {

        var layoutManager: LinearLayoutManager = LinearLayoutManager(mContext)

        layoutManager.orientation = LinearLayoutManager.VERTICAL

        rcv_segment.layoutManager = layoutManager

        mAdapter = GameTypeAdapter(mContext, R.layout.item_select_segment, mDataPriceList)

        mAdapter!!.setOnItemChildClickListener(object : BaseQuickAdapter.OnItemChildClickListener{
            override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                when(view!!.id){
                    R.id.tv_segment_name->{
                        dismiss()
                        if(mOnSelctItemListner != null){
                            mOnSelctItemListner!!.onSegmentItemClick(mDataPriceList!!.get(position))
                        }
                    }
                }
            }

        })

        rcv_segment.adapter = mAdapter

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

    interface onItemClickListener{
        fun onSegmentItemClick(price:GameItemBean)
    }

}