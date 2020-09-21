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
import com.qutu.talk.bean.MyFamilyItem

class MyFamilyListAdapter : BaseQuickAdapter<MyFamilyItem, BaseViewHolder> {

    var mContextTh: Context? = null

    var mDataList: MutableList<MyFamilyItem>? = null

    var mOnclickListener: OnButtonClickListener? = null

    fun setOnClickListener(clickListener: OnButtonClickListener) {
        this.mOnclickListener = clickListener
    }

    constructor(context: Context?, layoutResId: Int, data: MutableList<MyFamilyItem>?) : super(layoutResId, data) {
        mContextTh = context
        mDataList = data
    }

    override fun convert(holder: BaseViewHolder, item: MyFamilyItem?) {
        if (holder == null) {
            return
        }

        holder.setText(R.id.tv_family_name, item!!.name)

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

        holder.setText(R.id.tv_family_count, item!!.num)

        holder.getView<View>(R.id.layout_order_root).setOnClickListener {
            if (mOnclickListener != null) {
                mOnclickListener!!.itemClick(item)
            }
        }

    }

    interface OnButtonClickListener {
        fun itemClick(item: MyFamilyItem)
    }

}