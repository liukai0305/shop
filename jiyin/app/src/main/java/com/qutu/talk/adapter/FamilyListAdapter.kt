package com.qutu.talk.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jess.arms.http.imageloader.glide.ImageConfigImpl
import com.jess.arms.utils.ArmsUtils
import com.qutu.talk.R
import com.qutu.talk.bean.FamilyItem

class FamilyListAdapter : BaseQuickAdapter<FamilyItem, BaseViewHolder> {

    var mContextTh: Context? = null

    var mDataList: MutableList<FamilyItem>? = null

    var mOnclickListener: OnButtonClickListener? = null

    fun setOnClickListener(clickListener: OnButtonClickListener) {
        this.mOnclickListener = clickListener
    }

    constructor(context: Context?, layoutResId: Int, data: MutableList<FamilyItem>?) : super(layoutResId, data) {
        mContextTh = context
        mDataList = data
    }

    override fun convert(holder: BaseViewHolder, item: FamilyItem?) {
        if (holder == null) {
            return
        }

        holder.setText(R.id.tv_name, "${item!!.name}  ID：${item!!.family_id}")
        holder.setText(R.id.tv_number,"${holder.layoutPosition+1}")

        var imgHead: ImageView = holder.getView(R.id.img_family_head)

        ArmsUtils.obtainAppComponentFromContext(mContextTh)
                .imageLoader()
                .loadImage(mContextTh, ImageConfigImpl
                        .builder()
                        .url(item.image)
                        .placeholder(R.mipmap.no_tou)
                        .imageView(imgHead)
                        .errorPic(R.mipmap.no_tou)
                        .build())

        holder.setText(R.id.tv_person_num, "${item!!.num}人")
        holder.setText(R.id.tv_experience,"${item.exp}")

        holder.getView<View>(R.id.layout_root).setOnClickListener {
            if (mOnclickListener != null) {
                mOnclickListener!!.itemClick(item)
            }
        }

    }

    interface OnButtonClickListener {
        fun itemClick(item: FamilyItem)
    }

}