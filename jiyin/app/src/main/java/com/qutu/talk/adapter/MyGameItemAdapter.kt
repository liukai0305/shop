package com.qutu.talk.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.alibaba.android.vlayout.LayoutHelper
import com.jess.arms.http.imageloader.glide.ImageConfigImpl
import com.jess.arms.utils.ArmsUtils
import com.qutu.talk.R
import com.qutu.talk.base.BaseDelegateAdapter
import com.qutu.talk.base.BaseDelegateViewHolder
import com.qutu.talk.bean.GameItemBean
import com.qutu.talk.bean.MyGameSkillItemBean

class MyGameItemAdapter : BaseDelegateAdapter<MyGameSkillItemBean, BaseDelegateViewHolder> {

    var mContextTh:Context? = null

    var mOnChildViClickListener:OnChildViewClickListener? = null

    constructor(context: Context?,layoutResId: Int, data: MutableList<MyGameSkillItemBean>?, layoutHelper: LayoutHelper?):super(layoutResId, data, layoutHelper){
        mContextTh = context
    }

    fun setOnChildClickListener(clickListener:OnChildViewClickListener){
        mOnChildViClickListener = clickListener
    }

    override fun convert(holder: BaseDelegateViewHolder?, item: MyGameSkillItemBean?) {

        if(holder == null){
            return
        }

        var imgStatus: ImageView = holder.getView(R.id.img_jiedan_status)

        var imgLogo:ImageView = holder.getView(R.id.img_my_game)

        if(item!!.isAddSkill){
            holder.setGone(R.id.layout_add_skill,true)
            holder.setVisible(R.id.img_my_game,false)
            holder.setVisible(R.id.tv_game_name,false)
            holder.setVisible(R.id.tv_game_value,false)
            holder.setVisible(R.id.view_line,false)
            holder.setVisible(R.id.tv_jiedan_status,false)
            holder.setGone(R.id.img_jiedan_status,false)
        } else {
            holder.setGone(R.id.layout_add_skill,false)
            holder.setVisible(R.id.img_my_game,true)
            holder.setVisible(R.id.tv_game_name,true)
            holder.setVisible(R.id.tv_game_value,true)
            holder.setVisible(R.id.view_line,true)
            holder.setVisible(R.id.tv_jiedan_status,true)
            holder.setGone(R.id.img_jiedan_status,true)

            holder?.setText(R.id.tv_game_name, item?.name)

            if (item?.is_open.equals("1")){
                holder.setText(R.id.tv_jiedan_status, "接单中")
                imgStatus.isSelected = true
            } else if(item?.is_open.equals("0")){
                holder.setText(R.id.tv_jiedan_status, "未接单")
                imgStatus.isSelected = false
            }

//            holder.setImageResource(R.id.img_my_game,item!!.resId)

            holder.setText(R.id.tv_game_value, item.price+"金币/"+item.unit)

            ArmsUtils.obtainAppComponentFromContext(mContextTh)
                    .imageLoader()
                    .loadImage(mContextTh, ImageConfigImpl
                            .builder()
                            .url(item.image)
                            .placeholder(R.mipmap.no_tu)
                            .imageView(imgLogo)
                            .errorPic(R.mipmap.no_tu)
                            .build())

        }

      var itemView:View  =  holder.getView<View>(R.id.item_root_game)

        itemView.setOnClickListener {
            if(mOnChildViClickListener != null){
                if (item != null) {
                    mOnChildViClickListener?.OnChildClick(item)
                }
            }
        }
        imgStatus.setOnClickListener {
            if(mOnChildViClickListener != null){
                if (item != null) {
                    mOnChildViClickListener?.OnStatusClick(item)
                }
            }
        }


    }

    interface OnChildViewClickListener {

        fun OnChildClick(gameItemBean: MyGameSkillItemBean)

        fun OnStatusClick(gameItemBean: MyGameSkillItemBean)

    }
}