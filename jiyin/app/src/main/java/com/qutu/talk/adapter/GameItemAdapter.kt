package com.qutu.talk.adapter

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import com.alibaba.android.vlayout.LayoutHelper
import com.jess.arms.http.imageloader.glide.ImageConfigImpl
import com.jess.arms.utils.ArmsUtils
import com.qutu.talk.R
import com.qutu.talk.base.BaseDelegateAdapter
import com.qutu.talk.base.BaseDelegateViewHolder
import com.qutu.talk.bean.GameItemBean

class GameItemAdapter : BaseDelegateAdapter<GameItemBean, BaseDelegateViewHolder> {

    var mContextTh:Context? = null

    var mOnChildViClickListener:OnChildViewClickListener? = null

    constructor(context: Context?,layoutResId: Int, data: MutableList<GameItemBean>?, layoutHelper: LayoutHelper?):super(layoutResId, data, layoutHelper){
        mContextTh = context
    }

    fun setOnChildClickListener(clickListener:OnChildViewClickListener){
        mOnChildViClickListener = clickListener
    }

    override fun convert(holder: BaseDelegateViewHolder?, item: GameItemBean?) {

        if(holder == null){
            return
        }

        holder?.setText(R.id.tv_game_name, item?.name)

        holder.setVisible(R.id.tv_game_status,true)

//        if (item?.status.equals("1")){
//            holder.setText(R.id.tv_game_status, "已通过")
//        } else if(item?.status.equals("2")){//无状态
//            holder.setText(R.id.tv_game_status, "")
//            holder.setGone(R.id.tv_game_status,false)
//        } else if(item?.status.equals("0")){
//            holder.setText(R.id.tv_game_status, "审核中")
//        }
        if(TextUtils.isEmpty(item!!.status_txt)){
            holder.setGone(R.id.tv_game_status,false)
        } else {
            holder.setText(R.id.tv_game_status, item!!.status_txt)
        }

//        holder.setImageResource(R.id.img_game_logo,item!!.resId)

        var imgView: ImageView = holder.getView(R.id.img_game_logo)

      var itemView:View  =  holder.getView<View>(R.id.item_root_game)

        itemView.setOnClickListener {
            if(mOnChildViClickListener != null){
                if (item != null) {
                    mOnChildViClickListener?.OnChildClick(item)
                }
            }
        }

        ArmsUtils.obtainAppComponentFromContext(mContextTh)
                .imageLoader()
                .loadImage(mContextTh, ImageConfigImpl
                        .builder()
                        .url(item!!.image)
                        .placeholder(R.mipmap.no_tu)
                        .imageView(imgView)
                        .errorPic(R.mipmap.no_tu)
                        .build())

    }

    interface OnChildViewClickListener {

        fun OnChildClick(gameItemBean: GameItemBean)

    }
}