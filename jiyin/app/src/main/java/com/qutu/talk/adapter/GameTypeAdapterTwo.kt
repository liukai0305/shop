package com.qutu.talk.adapter

import android.content.Context
import com.alibaba.android.vlayout.LayoutHelper
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.qutu.talk.R
import com.qutu.talk.bean.GameItemBean
import com.qutu.talk.bean.SegmentItem
import com.qutu.talk.bean.dashen.ConfirmOrderSkillBean

class GameTypeAdapterTwo : BaseQuickAdapter<ConfirmOrderSkillBean.DataBean, BaseViewHolder> {

    var mContextTh:Context? = null

    var mDataList:MutableList<ConfirmOrderSkillBean.DataBean>? = null

    constructor(context: Context?, layoutResId: Int, data: MutableList<ConfirmOrderSkillBean.DataBean>?):super(layoutResId, data){
        mContextTh = context
        mDataList = data
    }

    override fun convert(holder: BaseViewHolder, item: ConfirmOrderSkillBean.DataBean?) {
        if(holder == null){
            return
        }

        holder?.setText(R.id.tv_segment_name, item?.name)

        holder.addOnClickListener(R.id.tv_segment_name)
    }

}