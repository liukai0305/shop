package com.qutu.talk.adapter

import android.content.Context
import android.os.Handler
import android.view.View
import android.widget.RadioButton
import com.alibaba.android.vlayout.LayoutHelper
import com.qutu.talk.R
import com.qutu.talk.base.BaseDelegateAdapter
import com.qutu.talk.base.BaseDelegateViewHolder
import com.qutu.talk.bean.GameGoodAtPositionItem

class GameGoodAtPositionAdapter : BaseDelegateAdapter<GameGoodAtPositionItem, BaseDelegateViewHolder> {

    var mContextTh:Context? = null

    internal var mOnCheckedListener: OnCheckedListener? = null

    var mDataList:MutableList<GameGoodAtPositionItem>? = null

    var mGameType:Int = 1;

    constructor(context: Context?, layoutResId: Int, data: MutableList<GameGoodAtPositionItem>?, layoutHelper: LayoutHelper?,gameType:Int):super(layoutResId, data, layoutHelper){
        mContextTh = context
        mDataList = data
        mGameType = gameType
    }

    fun setOnCheckedListener(onCheckedListener: OnCheckedListener) {
        this.mOnCheckedListener = onCheckedListener
    }

    override fun convert(holder: BaseDelegateViewHolder?, item: GameGoodAtPositionItem?) {

        if(holder == null){
            return
        }


        holder?.setText(R.id.radio_area, item?.name)

        val radioButton:RadioButton = holder.getView(R.id.radio_area)

        radioButton.isChecked = item!!.checked==1

        radioButton.setOnClickListener(View.OnClickListener {
//            val isCheck = item?.checked==1
//
//            if (isCheck!!) {
//                return@OnClickListener
//            }
//
//            for (bean in mDataList!!) {
//                bean.checked = 0
//            }
//
//            item.checked = 1

            var isCheck = item!!.checked

            if(isCheck ==1){
                item.checked = 0
            } else {
                item.checked = 1
            }

            Handler().post {
                // 刷新操作
                notifyDataSetChanged()
            }

//            if (mOnCheckedListener != null) {
//                var index = 0
//                for (i in mDataList!!.indices) {
//                    if (mDataList!!.get(i).checked==1) {
//                        index = i
//                        break
//                    }
//                }
//                mOnCheckedListener!!.onCheck(index)
//            }
        })

//        ArmsUtils.obtainAppComponentFromContext(mContextTh)
//                .imageLoader()
//                .loadImage(mContextTh, ImageConfigImpl
//                        .builder()
//                        .url(item.resId)
//                        .placeholder(R.mipmap.no_tou)
//                        .imageView(imageView1)
//                        .errorPic(R.mipmap.no_tou)
//                        .build())

    }

    interface OnCheckedListener {
        fun onCheck(index: Int)
    }
}