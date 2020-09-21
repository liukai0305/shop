package com.qutu.talk.activity.game

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.jess.arms.di.component.AppComponent
import com.jess.arms.http.imageloader.glide.ImageConfigImpl
import com.jess.arms.utils.ArmsUtils
import com.qutu.talk.R
import com.qutu.talk.app.utils.RxUtils
import com.qutu.talk.base.MyBaseArmActivity
import com.qutu.talk.bean.BaseBean
import com.qutu.talk.bean.FirstEvent
import com.qutu.talk.bean.OrderDetail
import com.qutu.talk.bean.OrderDetailResult
import com.qutu.talk.di.CommonModule
import com.qutu.talk.di.DaggerCommonComponent
import com.qutu.talk.service.CommonModel
import com.qutu.talk.utils.Constant
import com.qutu.talk.utils.CountTimeUtils
import kotlinx.android.synthetic.main.activity_order_detail.img_order_user_head
import kotlinx.android.synthetic.main.activity_order_detail.layout_bottom
import kotlinx.android.synthetic.main.activity_order_detail.layout_csl_bottom
import kotlinx.android.synthetic.main.activity_order_detail.layout_remark
import kotlinx.android.synthetic.main.activity_order_detail.tv_agree
import kotlinx.android.synthetic.main.activity_order_detail.tv_evaluate
import kotlinx.android.synthetic.main.activity_order_detail.tv_game_cunt
import kotlinx.android.synthetic.main.activity_order_detail.tv_game_name
import kotlinx.android.synthetic.main.activity_order_detail.tv_game_unit
import kotlinx.android.synthetic.main.activity_order_detail.tv_game_user_name
import kotlinx.android.synthetic.main.activity_order_detail.tv_game_value
import kotlinx.android.synthetic.main.activity_order_detail.tv_intro_fee
import kotlinx.android.synthetic.main.activity_order_detail.tv_order_number
import kotlinx.android.synthetic.main.activity_order_detail.tv_order_price
import kotlinx.android.synthetic.main.activity_order_detail.tv_order_time
import kotlinx.android.synthetic.main.activity_order_detail.tv_refuse
import kotlinx.android.synthetic.main.activity_order_detail.tv_remarks
import kotlinx.android.synthetic.main.activity_order_detail.tv_service_time
import kotlinx.android.synthetic.main.activity_order_detail.tv_status_str
import kotlinx.android.synthetic.main.activity_order_god_detail.*
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

/**
 * 我的接单订单详情
 */
class OrderDetailGodActivity : MyBaseArmActivity() {

    var mStatus: Int = 0

    var mOrderId: String = ""

    var mCountTimeUtils:CountTimeUtils? = null

    var mOrderDetail:OrderDetail? = null

    @Inject
    open lateinit var commonModel: CommonModel

    override fun setupActivityComponent(appComponent: AppComponent) {

        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(CommonModule())
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_order_god_detail
    }

    override fun initData(savedInstanceState: Bundle?) {

        setTitle("订单详情")

        var intent: Intent = intent

        if(intent != null && intent.extras != null){

            mOrderId = intent.getStringExtra("order_id")

        } else {
            finish()
        }

        setOnClickListener()

        getDataFromServer()

    }

    private fun setOnClickListener() {

        tv_agree.setOnClickListener {
            godReceipt(mOrderId,"1")

        }//同意

        tv_refuse.setOnClickListener {
            godReceipt(mOrderId,"2")
        }//拒绝

        tv_apply_service.setOnClickListener {
            godApplyService(mOrderId)
        }//确认完成

        tv_evaluate.setOnClickListener {
            if(mOrderDetail == null){
                return@setOnClickListener
            }
            var intent = Intent(this@OrderDetailGodActivity, EvaluateOrderActivity::class.java)

            intent.putExtra("order_detail",mOrderDetail!!)

            intent.putExtra("order_from",true)

            ArmsUtils.startActivity(intent)
        }//评价

    }

    //获取数据
    private fun getDataFromServer() {
        //keyword:订单状态:1待支付2待接单3待服务4进行中8退款/申诉(不传默认所有)
        //type:1我的下单2我的接单
        RxUtils.loading(commonModel.go_order_details(mOrderId,"2"))
                .subscribe(object : ErrorHandleSubscriber<OrderDetailResult>(mErrorHandler) {
                    override fun onNext(orderResult: OrderDetailResult) {

                        if (orderResult != null && orderResult.data != null) {

                            var orderDetail:OrderDetail = orderResult.data

                            mOrderDetail = orderResult.data

                            tv_status_str.text = orderDetail.status_text

                            ArmsUtils.obtainAppComponentFromContext(this@OrderDetailGodActivity)
                                    .imageLoader()
                                    .loadImage(this@OrderDetailGodActivity, ImageConfigImpl
                                            .builder()
                                            .url(orderDetail.headimgurl)
                                            .placeholder(R.mipmap.no_tou)
                                            .imageView(img_order_user_head)
                                            .errorPic(R.mipmap.no_tou)
                                            .build())

                            tv_game_user_name.text = orderDetail.user_name

                            tv_game_name.text = orderDetail.skill_name

                            tv_game_unit.text = "/"+orderDetail.unit

                            tv_game_value.text = orderDetail.price+"金币"

                            tv_game_cunt.text = "x"+orderDetail.num

                            tv_order_price.text = orderDetail.total_price+"金币"

                            tv_order_fee.text = "-"+orderDetail.fee+"金币"

                            tv_order_wait_price.text = orderDetail.real_price+"金币"

                            tv_service_time.text = orderDetail.start_time

                            tv_order_number.text = orderDetail.order_no

                            tv_order_time.text = orderDetail.addtime

                            if(!TextUtils.isEmpty(orderDetail.remarks)){
                                layout_remark.visibility = View.VISIBLE
                                tv_remarks.text = orderDetail.remarks
                            } else {
                                layout_remark.visibility = View.GONE
                            }

                            mStatus = orderDetail.status

                            setBottomInfo(orderDetail)

                        } else {

                        }

                    }
                })



    }

    private fun setBottomInfo(orderDetail: OrderDetail) {

        tv_order_fee.visibility = View.VISIBLE

        tv_order_wait_price.visibility = View.VISIBLE

        tv_order_fee_key.visibility = View.VISIBLE

        tv_order_wait_price_key.visibility = View.VISIBLE

        if(orderDetail.status == 6 || orderDetail.status == 7  || orderDetail.status == 81  || orderDetail.status == 82  || orderDetail.status == 83  || orderDetail.status == 84){

            tv_order_fee.visibility = View.GONE

            tv_order_fee_key.visibility = View.GONE

            tv_order_wait_price.visibility = View.GONE

            tv_order_wait_price_key.visibility = View.GONE
        }

        if(orderDetail.status == 2){

            layout_bottom.visibility = View.VISIBLE

            layout_csl_bottom.visibility = View.VISIBLE

            tv_intro_fee.visibility = View.GONE

            tv_agree.visibility = View.VISIBLE//同意

            tv_refuse.visibility = View.VISIBLE//拒绝

            tv_apply_service.visibility = View.GONE//申请服务

            tv_evaluate.visibility = View.GONE//评价

        } else if(orderDetail.status == 3){

            layout_bottom.visibility = View.VISIBLE

            layout_csl_bottom.visibility = View.VISIBLE

            tv_intro_fee.visibility = View.GONE

            tv_agree.visibility = View.GONE//同意

            tv_refuse.visibility = View.GONE//拒绝

            if(orderDetail.is_first==0){
                tv_apply_service.visibility = View.VISIBLE//申请服务
            } else {
                layout_csl_bottom.visibility = View.GONE
                tv_apply_service.visibility = View.GONE//申请服务
            }

            tv_evaluate.visibility = View.GONE//评价

        }  else if(orderDetail.status == 5){

            layout_bottom.visibility = View.VISIBLE

            layout_csl_bottom.visibility = View.GONE

            tv_intro_fee.visibility = View.GONE

            tv_agree.visibility = View.GONE//同意

            tv_refuse.visibility = View.GONE//拒绝

            tv_apply_service.visibility = View.GONE//申请服务

            tv_evaluate.visibility = View.GONE//评价

        } else if(orderDetail.status == 83){//拒绝退款

            layout_bottom.visibility = View.VISIBLE

            layout_csl_bottom.visibility = View.GONE

            tv_intro_fee.visibility = View.VISIBLE

            tv_agree.visibility = View.GONE//同意

            tv_refuse.visibility = View.GONE//拒绝

            tv_apply_service.visibility = View.GONE//申请服务

            tv_evaluate.visibility = View.GONE//评价

        } else if(orderDetail.status == 81){//用户退款申请

            layout_bottom.visibility = View.VISIBLE

            layout_csl_bottom.visibility = View.GONE

            tv_intro_fee.visibility = View.GONE

            tv_agree.visibility = View.VISIBLE//同意

            tv_refuse.visibility = View.VISIBLE//拒绝

            tv_apply_service.visibility = View.GONE//申请服务

            tv_evaluate.visibility = View.GONE//评价

        }
        else {

            layout_bottom.visibility = View.GONE

        }
    }

    //同意或者拒绝
    private fun godReceipt(orderId:String,type:String) {
        //keyword:订单状态:1待支付2待接单3待服务4进行中8退款/申诉(不传默认所有)
        //type:1我的下单2我的接单
        if(mOrderDetail!!.status == 81){//申请退款

            RxUtils.loading(commonModel!!.go_apply_refund_hand(orderId,type))
                    .subscribe(object : ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                        override fun onNext(orderResult: BaseBean) {

                            EventBus.getDefault().post(FirstEvent("指定发送", Constant.REFRESH_ORDER))

                            getDataFromServer()

                        }
                    })

        } else {//接单
            RxUtils.loading(commonModel!!.go_receipt(orderId,type))
                    .subscribe(object : ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                        override fun onNext(orderResult: BaseBean) {

                            EventBus.getDefault().post(FirstEvent("指定发送", Constant.REFRESH_ORDER))

                            getDataFromServer()

                        }
                    })
        }
    }

    //申请立即服务
    private fun godApplyService(orderId:String) {
        //keyword:订单状态:1待支付2待接单3待服务4进行中8退款/申诉(不传默认所有)
        //type:1我的下单2我的接单
        RxUtils.loading(commonModel!!.go_apply_ljwf(orderId))
                .subscribe(object : ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    override fun onNext(orderResult: BaseBean) {

                        EventBus.getDefault().post(FirstEvent("指定发送", Constant.REFRESH_ORDER))

                        getDataFromServer()

                    }
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        if(mCountTimeUtils != null){
            mCountTimeUtils!!.cancel()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun receiveMsg(event: FirstEvent) {
        val tag = event.tag
        if (Constant.MILICHONGZHI == tag || Constant.REFRESH_ORDER == tag) {

            getDataFromServer()
        }
    }

}
