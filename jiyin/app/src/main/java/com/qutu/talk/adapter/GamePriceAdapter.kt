package com.qutu.talk.adapter

import android.content.Context
import android.os.Handler
import android.text.TextUtils
import android.view.View
import android.widget.RadioButton
import com.alibaba.android.vlayout.LayoutHelper
import com.jess.arms.utils.LogUtils
import com.qutu.talk.R
import com.qutu.talk.base.BaseDelegateAdapter
import com.qutu.talk.base.BaseDelegateViewHolder
import com.qutu.talk.bean.GameItemBean
import com.qutu.talk.bean.GamePriceItem
import com.qutu.talk.bean.GameType
import com.qutu.talk.utils.ToastUtil

class GamePriceAdapter : BaseDelegateAdapter<GamePriceItem, BaseDelegateViewHolder> {

    var mContextTh:Context? = null

    internal var mOnCheckedListener: OnCheckedListener? = null

    var mDataList:MutableList<GamePriceItem>? = null

    var mGameType:Int = 1;

    constructor(context: Context?, layoutResId: Int, data: MutableList<GamePriceItem>?, layoutHelper: LayoutHelper?,gameType:Int):super(layoutResId, data, layoutHelper){
        mContextTh = context
        mDataList = data
        mGameType = gameType
    }

    fun setOnCheckedListener(onCheckedListener: OnCheckedListener) {
        this.mOnCheckedListener = onCheckedListener
    }

    override fun convert(holder: BaseDelegateViewHolder?, item: GamePriceItem?) {

        if(holder == null){
            return
        }

        holder!!.setText(R.id.radio_price, item!!.price+"金币/"+item!!.unit)
//        if(mGameType == GameType.GAME_LOL.state || mGameType == GameType.GAME_WANG_ZHE.state){
//
//        } else if(mGameType == GameType.GAME_HE_PING.state){
//            holder?.setText(R.id.radio_price, item?.price+"金币/"+item!!.unit)
//        } else if(mGameType == GameType.GAME_JUE_DI.state){
//            holder?.setText(R.id.radio_price, item?.price+"金币/"+item!!.unit)
//        }

        if (!TextUtils.isEmpty(item?.orders) && !TextUtils.equals(item!!.orders,"0")){
            holder.setText(R.id.tv_lock_intro, item?.orders+"单解锁")
        } else{
            holder.setText(R.id.tv_lock_intro, "")
        }

        val radioButton:RadioButton = holder.getView(R.id.radio_price)

        radioButton.isChecked = item!!.checked==1

        radioButton.setOnClickListener(View.OnClickListener {
            val isCheck = item?.checked==1

            if (isCheck!!) {
                return@OnClickListener
            }

            if(item.is_disabled == 1){
                item.checked = 0
                ToastUtil.showToast(mContext,"暂未解锁")
                Handler().post {
                    // 刷新操作
                    notifyDataSetChanged()
                }
                return@OnClickListener
            }

            for (bean in mDataList!!) {
                bean.checked = 0
            }

            item.checked = 1

            Handler().post {
                // 刷新操作
                notifyDataSetChanged()
            }

            if (mOnCheckedListener != null) {
                var index = 0
                for (i in mDataList!!.indices) {
                    if (mDataList!!.get(i).checked==1) {
                        index = i
                        break
                    }
                }
                mOnCheckedListener!!.onCheck(index)
            }
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