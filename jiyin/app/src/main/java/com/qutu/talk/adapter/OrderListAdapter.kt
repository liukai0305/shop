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
import com.qutu.talk.bean.OrderItem

class OrderListAdapter : BaseQuickAdapter<OrderItem, BaseViewHolder> {

    var mContextTh:Context? = null

    var mDataList:MutableList<OrderItem>? = null

    var mOnClickListener:OnButtonClickListener? = null

    constructor(context: Context?, layoutResId: Int, data: MutableList<OrderItem>?):super(layoutResId, data){
        mContextTh = context
        mDataList = data
    }

    fun setOnButtonClickListener(onClickListener:OnButtonClickListener){
        this.mOnClickListener = onClickListener
    }

    override fun convert(holder: BaseViewHolder, item: OrderItem?) {
        if(holder == null){
            return
        }

        holder.setText(R.id.tv_order_time,item!!.start_time)

        holder.setText(R.id.tv_order_status_str,item!!.status_text)

        var imgHead:ImageView = holder.getView(R.id.img_order_user_head)

        ArmsUtils.obtainAppComponentFromContext(mContextTh)
                .imageLoader()
                .loadImage(mContextTh, ImageConfigImpl
                        .builder()
                        .url(item.headimgurl)
                        .placeholder(R.mipmap.no_tou)
                        .imageView(imgHead)
                        .errorPic(R.mipmap.no_tou)
                        .build())

        holder.setText(R.id.tv_order_user_name,item!!.user_name)

        holder.setText(R.id.tv_order_skill,item!!.skill_name)

        holder.setText(R.id.tv_order_game_count,"x"+item!!.num+item.unit)

//        holder.setText(R.id.tv_order_money,item!!.total_price)

        var status = item.status

        if(status == 1){
            holder.setText(R.id.tv_order_money, "待支付：" + item!!.total_price + "金币")
        } else if(status == 2 || status == 3 || status == 31 || status == 4 || status == 5){
            holder.setText(R.id.tv_order_money, "已支付：" + item!!.total_price + "金币")
        } else if(status == 7 || status == 82 || status == 4 || status == 5){
            holder.setText(R.id.tv_order_money, "已退款：" + item!!.total_price + "金币")
        } else if(status == 6){
            if(!TextUtils.isEmpty(item.paytime)){//已经付款了
                holder.setText(R.id.tv_order_money, "已退款：" + item!!.total_price + "金币")
            } else {
                holder.setText(R.id.tv_order_money, "")
            }
        } else if(status == 81 || status == 84){
            holder.setText(R.id.tv_order_money, "待退款：" + item!!.total_price + "金币")
        }


        if(status == 1){//待支付
            holder.setGone(R.id.tv_goto_pay, true)
            holder.setGone(R.id.tv_agree, false)
            holder.setGone(R.id.tv_refuse, false)
            holder.setGone(R.id.tv_order_again, false)
            holder.setGone(R.id.tv_evaluate, false)
            holder.setGone(R.id.tv_confirm_complete, false)
            holder.setGone(R.id.tv_appeal, false)

        } else if(status == 2){//待接单
            holder.setGone(R.id.tv_goto_pay, false)
            holder.setGone(R.id.tv_agree, false)
            holder.setGone(R.id.tv_refuse, false)
            holder.setGone(R.id.tv_order_again, false)
            holder.setGone(R.id.tv_evaluate, false)
            holder.setGone(R.id.tv_confirm_complete, false)
            holder.setGone(R.id.tv_appeal, false)
        } else if(status == 3 || status == 31){//3待服务 31 拒绝了服务
            if(status == 3){
                holder.setGone(R.id.tv_goto_pay, false)
                holder.setGone(R.id.tv_agree, false)
                holder.setGone(R.id.tv_refuse, false)
                holder.setGone(R.id.tv_order_again, false)
                holder.setGone(R.id.tv_evaluate, false)
                holder.setGone(R.id.tv_confirm_complete, false)
                holder.setGone(R.id.tv_appeal, false)
            } else {
                holder.setGone(R.id.tv_goto_pay, false)
                holder.setGone(R.id.tv_agree, true)
                holder.setGone(R.id.tv_refuse, true)
                holder.setGone(R.id.tv_order_again, false)
                holder.setGone(R.id.tv_evaluate, false)
                holder.setGone(R.id.tv_confirm_complete, false)
                holder.setGone(R.id.tv_appeal, false)
            }
        } else if(status == 4){//进行中
            holder.setGone(R.id.tv_goto_pay, false)
            holder.setGone(R.id.tv_agree, false)
            holder.setGone(R.id.tv_refuse, false)
            holder.setGone(R.id.tv_order_again, false)
            holder.setGone(R.id.tv_evaluate, false)
            holder.setGone(R.id.tv_confirm_complete, true)
            holder.setGone(R.id.tv_appeal, false)
        } else if(status == 83){//退款/申诉
            holder.setGone(R.id.tv_goto_pay, false)
            holder.setGone(R.id.tv_agree, false)
            holder.setGone(R.id.tv_refuse, false)
            holder.setGone(R.id.tv_order_again, false)
            holder.setGone(R.id.tv_evaluate, false)
            holder.setGone(R.id.tv_confirm_complete, false)
            holder.setGone(R.id.tv_appeal, true)
        } else if(status == 5){//待评价
            holder.setGone(R.id.tv_goto_pay, false)
            holder.setGone(R.id.tv_agree, false)
            holder.setGone(R.id.tv_refuse, false)
            holder.setGone(R.id.tv_confirm_complete, false)
            holder.setGone(R.id.tv_appeal, false)
            holder.setGone(R.id.tv_order_again, true)
            if(item.is_discuss == 0){
                holder.setGone(R.id.tv_evaluate, true)
            } else {
                holder.setGone(R.id.tv_evaluate, false)
            }
        } else if(status == 6 || status == 7 || status == 82){
            holder.setGone(R.id.tv_goto_pay, false)
            holder.setGone(R.id.tv_agree, false)
            holder.setGone(R.id.tv_refuse, false)
            holder.setGone(R.id.tv_confirm_complete, false)
            holder.setGone(R.id.tv_appeal, false)
            holder.setGone(R.id.tv_order_again, true)
            holder.setGone(R.id.tv_evaluate, false)
        }
        else {
            holder.setGone(R.id.tv_goto_pay, false)
            holder.setGone(R.id.tv_agree, false)
            holder.setGone(R.id.tv_refuse, false)
            holder.setGone(R.id.tv_order_again, false)
            holder.setGone(R.id.tv_evaluate, false)
            holder.setGone(R.id.tv_confirm_complete, false)
            holder.setGone(R.id.tv_appeal, false)
        }

//        holder.addOnClickListener(R.id.layout_order_root)
//        holder.addOnClickListener(R.id.tv_goto_pay)
//        holder.addOnClickListener(R.id.tv_agree)
//        holder.addOnClickListener(R.id.tv_refuse)
//        holder.addOnClickListener(R.id.tv_order_again)
//        holder.addOnClickListener(R.id.tv_evaluate)
//        holder.addOnClickListener(R.id.tv_confirm_complete)
//        holder.addOnClickListener(R.id.tv_appeal)

        holder.getView<View>(R.id.layout_order_root).setOnClickListener {
            var intent: Intent = Intent(mContext, OrderDetailActivity::class.java)
            intent.putExtra("order_id", item.id)
            ArmsUtils.startActivity(intent)
        }

        holder.getView<View>(R.id.tv_goto_pay).setOnClickListener {
            if(mOnClickListener != null){
                mOnClickListener!!.payOrder(item)
            }
        }

        holder.getView<View>(R.id.tv_agree).setOnClickListener {
            if(mOnClickListener != null){
                mOnClickListener!!.agree(item)
            }
        }

        holder.getView<View>(R.id.tv_refuse).setOnClickListener {
            if(mOnClickListener != null){
                mOnClickListener!!.refuse(item)
            }
        }

        holder.getView<View>(R.id.tv_order_again).setOnClickListener {
            if(mOnClickListener != null){
                mOnClickListener!!.againOrder(item)
            }
        }

        holder.getView<View>(R.id.tv_evaluate).setOnClickListener {
            if(mOnClickListener != null){
                mOnClickListener!!.evaluateOrder(item)
            }
        }

        holder.getView<View>(R.id.tv_confirm_complete).setOnClickListener {
            if(mOnClickListener != null){
                mOnClickListener!!.confirmComplete(item)
            }
        }

        holder.getView<View>(R.id.tv_appeal).setOnClickListener {
            if(mOnClickListener != null){
                mOnClickListener!!.appeal(item)
            }
        }

    }

    interface OnButtonClickListener{
        fun payOrder(item:OrderItem)
        fun agree(item:OrderItem)
        fun refuse(item:OrderItem)
        fun confirmComplete(item:OrderItem)
        fun appeal(item:OrderItem)
        fun evaluateOrder(item:OrderItem)
        fun againOrder(item:OrderItem)
    }

}