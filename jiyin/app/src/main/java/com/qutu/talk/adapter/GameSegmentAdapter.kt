package com.qutu.talk.adapter

import android.content.Context
import com.alibaba.android.vlayout.LayoutHelper
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.qutu.talk.R
import com.qutu.talk.bean.SegmentItem

class GameSegmentAdapter : BaseQuickAdapter<SegmentItem, BaseViewHolder> {

    var mContextTh:Context? = null

    var mDataList:MutableList<SegmentItem>? = null

    constructor(context: Context?, layoutResId: Int, data: MutableList<SegmentItem>?):super(layoutResId, data){
        mContextTh = context
        mDataList = data
    }

    override fun convert(holder: BaseViewHolder, item: SegmentItem?) {
        if(holder == null){
            return
        }

        holder?.setText(R.id.tv_segment_name, item?.name)

        holder.addOnClickListener(R.id.tv_segment_name)
    }

}