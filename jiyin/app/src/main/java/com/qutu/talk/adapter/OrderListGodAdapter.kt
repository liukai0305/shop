package com.qutu.talk.adapter

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jess.arms.http.imageloader.glide.ImageConfigImpl
import com.jess.arms.utils.ArmsUtils
import com.qutu.talk.R
import com.qutu.talk.activity.game.OrderDetailActivity
import com.qutu.talk.activity.game.OrderDetailGodActivity
import com.qutu.talk.app.utils.RxUtils
import com.qutu.talk.bean.BaseBean
import com.qutu.talk.bean.OrderItem
import com.qutu.talk.bean.OrderListResult
import com.qutu.talk.service.CommonModel
import com.qutu.talk.utils.DealRefreshHelper
import kotlinx.android.synthetic.main.fragment_order.*
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import java.util.ArrayList

class OrderListGodAdapter : BaseQuickAdapter<OrderItem, BaseViewHolder> {

    var mContextTh: Context? = null

    var mDataList: MutableList<OrderItem>? = null

    var mOnclickListener: OnButtonClickListener? = null

    fun setOnClickListener(clickListener: OnButtonClickListener) {
        this.mOnclickListener = clickListener
    }

    constructor(context: Context?, layoutResId: Int, data: MutableList<OrderItem>?) : super(layoutResId, data) {
        mContextTh = context
        mDataList = data
    }

    override fun convert(holder: BaseViewHolder, item: OrderItem?) {
        if (holder == null) {
            return
        }

        holder.setText(R.id.tv_order_time, item!!.start_time)

        holder.setText(R.id.tv_order_status_str, item!!.status_text)

        var imgHead: ImageView = holder.getView(R.id.img_order_user_head)

        ArmsUtils.obtainAppComponentFromContext(mContextTh)
                .imageLoader()
                .loadImage(mContextTh, ImageConfigImpl
                        .builder()
                        .url(item.headimgurl)
                        .placeholder(R.mipmap.no_tou)
                        .imageView(imgHead)
                        .errorPic(R.mipmap.no_tou)
                        .build())

        holder.setText(R.id.tv_order_user_name, item!!.user_name)

        holder.setText(R.id.tv_order_skill, item!!.skill_name)

        holder.setText(R.id.tv_order_game_count, "x" + item!!.num + item.unit)

        holder.setText(R.id.tv_order_money, item!!.real_price)

        holder.setText(R.id.tv_order_remark, item!!.remarks)

        var status = item.status

        holder.setGone(R.id.tv_order_money,true)
        if (status == 2 || status == 3 || status == 31 || status == 4) {
            holder.setText(R.id.tv_order_money, "待入账：" + item!!.real_price + "金币")
        } else if (status == 5) {
            holder.setText(R.id.tv_order_money, "已入账：" + item!!.real_price + "金币")
        } else if (status == 7 || status == 83 || status == 81 || status == 82 || status == 84 || status == 6) {
            holder.setGone(R.id.tv_order_money,false)
        } else {
            holder.setGone(R.id.tv_order_money,false)
        }

        if (status == 2 || status == 81) {//待接单
            holder.setGone(R.id.tv_agree, true)
            holder.setGone(R.id.tv_refuse, true)
            holder.setGone(R.id.tv_evaluate, false)
            holder.setGone(R.id.tv_apply_service, false)
        } else if (status == 3) {//3待服务
            holder.setGone(R.id.tv_agree, false)
            holder.setGone(R.id.tv_refuse, false)
            holder.setGone(R.id.tv_evaluate, false)
            if(item.is_first==0){
                holder.setGone(R.id.tv_apply_service, true)
            } else {
                holder.setGone(R.id.tv_apply_service, false)
            }
        } else if (status == 5) {//进行中
            holder.setGone(R.id.tv_agree, false)
            holder.setGone(R.id.tv_refuse, false)
            holder.setGone(R.id.tv_evaluate, false)
            holder.setGone(R.id.tv_apply_service, false)
        } else {
            holder.setGone(R.id.tv_agree, false)
            holder.setGone(R.id.tv_refuse, false)
            holder.setGone(R.id.tv_evaluate, false)
            holder.setGone(R.id.tv_apply_service, false)
        }


        holder.getView<View>(R.id.layout_order_root).setOnClickListener {
            var intent: Intent = Intent(mContext, OrderDetailGodActivity::class.java)
            intent.putExtra("order_id", item.id)
            ArmsUtils.startActivity(intent)
        }
        holder.getView<View>(R.id.tv_agree).setOnClickListener {
            if (mOnclickListener != null) {
                mOnclickListener!!.godAgree(item)
            }
        }
        holder.getView<View>(R.id.tv_refuse).setOnClickListener {
            if (mOnclickListener != null) {
                mOnclickListener!!.godRefuse(item)
            }
        }

        holder.getView<View>(R.id.tv_evaluate).setOnClickListener {
            if (mOnclickListener != null) {
                mOnclickListener!!.godEvaluate(item)
            }
        }

        holder.getView<View>(R.id.tv_apply_service).setOnClickListener {
            if (mOnclickListener != null) {
                mOnclickListener!!.godApplyService(item)
            }
        }

    }

    interface OnButtonClickListener {
        fun godAgree(item: OrderItem)
        fun godRefuse(item: OrderItem)
        fun godApplyService(item: OrderItem)
        fun godEvaluate(item: OrderItem)
    }

}