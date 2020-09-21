package com.qutu.talk.activity.game

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import com.jess.arms.di.component.AppComponent
import com.jess.arms.http.imageloader.glide.ImageConfigImpl
import com.jess.arms.utils.ArmsUtils
import com.qutu.talk.R
import com.qutu.talk.activity.order.ConfirmOrderActivity
import com.qutu.talk.app.utils.RxUtils
import com.qutu.talk.base.MyBaseArmActivity
import com.qutu.talk.base.UserManager
import com.qutu.talk.bean.*
import com.qutu.talk.di.CommonModule
import com.qutu.talk.di.DaggerCommonComponent
import com.qutu.talk.popup.PaymentWindow
import com.qutu.talk.popup.PuTongWindow
import com.qutu.talk.popup.SelectCancelOrderDialog
import com.qutu.talk.popup.SelectGameTypeDialog
import com.qutu.talk.service.CommonModel
import com.qutu.talk.utils.Arith
import com.qutu.talk.utils.Constant
import com.qutu.talk.utils.CountTimeUtils
import com.qutu.talk.utils.TimeSwitchUtil
import kotlinx.android.synthetic.main.activity_order_detail.*
import kotlinx.android.synthetic.main.activity_order_detail.tv_service_time
import kotlinx.android.synthetic.main.dialog_go_set_paidan.*
import kotlinx.android.synthetic.main.include_title.*
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.ArrayList
import javax.inject.Inject

class OrderDetailActivity : MyBaseArmActivity() {

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
        return R.layout.activity_order_detail
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

        tv_goto_pay.setOnClickListener {
            payOrder(mOrderDetail!!)
        }

        tv_agree.setOnClickListener {
            godReceipt(mOrderId,"1")

        }//同意

        tv_refuse.setOnClickListener {
            godReceipt(mOrderId,"2")
        }//拒绝

        tv_confirm_complete.setOnClickListener {
            go_finish(mOrderId)
        }//确认完成

        tv_order_again.setOnClickListener {
            if(mOrderDetail == null){
                return@setOnClickListener
            }
            var mIntent = Intent(this@OrderDetailActivity, ConfirmOrderActivity::class.java)
            mIntent.putExtra("skillId", mOrderDetail!!.skill_id)
            mIntent.putExtra("id", mOrderDetail!!.skill_apply_id)
            mIntent.putExtra("userId", mOrderDetail!!.god_id)
            mIntent.putExtra("img_1", mOrderDetail!!.headimgurl)
            mIntent.putExtra("naicName", mOrderDetail!!.god_name)
            mIntent.putExtra("price", mOrderDetail!!.price)
            mIntent.putExtra("unit", mOrderDetail!!.unit)
            mIntent.putExtra("skillName", mOrderDetail!!.skill_name)
            startActivity(mIntent)
        }//再次下单

        tv_evaluate.setOnClickListener {
            if(mOrderDetail == null){
                return@setOnClickListener
            }
            var intent = Intent(this@OrderDetailActivity, EvaluateOrderActivity::class.java)

            intent.putExtra("order_detail",mOrderDetail!!)

            intent.putExtra("order_from",true)

            ArmsUtils.startActivity(intent)

        }//评价

        tv_appeal.setOnClickListener {
            if(mOrderDetail == null){
                return@setOnClickListener
            }
            var intent = Intent(this@OrderDetailActivity, AppealActivity::class.java)
            intent.putExtra("order_detail",mOrderDetail)
            intent.putExtra("order_from",true)
            ArmsUtils.startActivity(intent)

        }//申诉
    }

    fun setRightTitle(){
        when(mStatus){
            1,2,3,31->{
                rightTitle.visibility = View.VISIBLE
                rightTitle.setText("取消订单")
                rightTitle.setTextColor(resources.getColor(R.color.font_333333))
                rightTitle.setOnClickListener {

                    var list: ArrayList<GameItemBean> =  ArrayList<GameItemBean>()

                    var item:GameItemBean = GameItemBean()
                    item.name = "发错订单"
                    list.add(item)

                    var item1:GameItemBean = GameItemBean()
                    item1.name = "大神长时间未接单"
                    list.add(item1)

                    var item2:GameItemBean = GameItemBean()
                    item2.name = "大神要求取消订单"
                    list.add(item2)

                    var item3:GameItemBean = GameItemBean()
                    item3.name = "临时有事，不想玩了"
                    list.add(item3)

                    var item4:GameItemBean = GameItemBean()
                    item4.name = "随便试试"
                    list.add(item4)

                    var selectGameSegmentDialog: SelectCancelOrderDialog = SelectCancelOrderDialog(this@OrderDetailActivity!!, list)

                    selectGameSegmentDialog.setOnItemSelectListener(object : SelectCancelOrderDialog.onItemClickListener{

                        override fun onSegmentItemClick(price: GameItemBean) {

                            selectGameSegmentDialog.dismiss()

                            price.name?.let { it1 -> go_cancel(mOrderId, it1) }
                        }

                    })

                    selectGameSegmentDialog.show()

                }
            }
            4->{
                rightTitle.visibility = View.VISIBLE
                rightTitle.setText("申请退款")
                rightTitle.setTextColor(resources.getColor(R.color.font_333333))
                rightTitle.setOnClickListener {

                    val puTongWindow1 = PuTongWindow(this)
                    puTongWindow1.showAtLocation(rightTitle, Gravity.CENTER, 0, 0)
                    puTongWindow1.titText.text = "确认申请退款？"
                    puTongWindow1.cancel.setOnClickListener { v -> puTongWindow1.dismiss() }
                    puTongWindow1.sure.setOnClickListener { v ->
                        puTongWindow1.dismiss()
                        go_apply_refund(mOrderId)
                    }
//                    MaterialDialog.Builder(this)
//                            .title("")
//                            .content("确认申请退款？")
//                            .positiveText("确认")
//                            .negativeText("取消")
//                            .onPositive { dialog, which ->
//                                go_apply_refund(mOrderId)
//                            }
//                            .show()
                }
            }
            else ->{
                rightTitle.visibility = View.GONE
            }
        }
    }

    //获取数据
    private fun getDataFromServer() {
        //keyword:订单状态:1待支付2待接单3待服务4进行中8退款/申诉(不传默认所有)
        //type:1我的下单2我的接单
        RxUtils.loading(commonModel.go_order_details(mOrderId,"1"))
                .subscribe(object : ErrorHandleSubscriber<OrderDetailResult>(mErrorHandler) {
                    override fun onNext(orderResult: OrderDetailResult) {

                        if (orderResult != null && orderResult.data != null) {

                            var orderDetail:OrderDetail = orderResult.data

                            mOrderDetail = orderResult.data

                            tv_status_str.text = orderDetail.status_text

                            ArmsUtils.obtainAppComponentFromContext(this@OrderDetailActivity)
                                    .imageLoader()
                                    .loadImage(this@OrderDetailActivity, ImageConfigImpl
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

                            setRightTitle()

                            setBottomInfo(orderDetail)

                        } else {

                        }

                    }
                })



    }

    private fun setBottomInfo(orderDetail: OrderDetail) {

        if(orderDetail.status == 1){//待支付

            layout_bottom.visibility = View.VISIBLE

            layout_csl_bottom.visibility = View.VISIBLE

            tv_intro_fee.visibility = View.GONE

            tv_timer_pay.visibility = View.VISIBLE

            tv_goto_pay.visibility = View.VISIBLE

            tv_agree.visibility = View.GONE//同意

            tv_refuse.visibility = View.GONE//拒绝

            tv_confirm_complete.visibility = View.GONE//确认完成

            tv_order_again.visibility = View.GONE//再次下单

            tv_evaluate.visibility = View.GONE//评价

            tv_appeal.visibility = View.GONE//申诉

            tv_appeal_intro.visibility = View.GONE//申诉等待中

            if(!TextUtils.isEmpty(orderDetail.sysj) && !TextUtils.equals("0",orderDetail.sysj)){

                var times = Arith.strToLong(orderDetail.sysj)

                mCountTimeUtils = object : CountTimeUtils(times) {
                    override fun onTick(millisUntilFinished: Long) {
//                        tv_timer_pay.setText("支付剩余时间："+millisUntilFinished.toString())
                        tv_timer_pay.setText("支付剩余时间："+TimeSwitchUtil.getTimeFormatMillis(millisUntilFinished));
                    }

                    override fun onFinish() {

                        EventBus.getDefault().post(FirstEvent("指定发送", Constant.REFRESH_ORDER))

                        getDataFromServer()

                    }
                }

                mCountTimeUtils!!.start()

            }

        } else if(orderDetail.status == 31){//同意拒绝

            layout_bottom.visibility = View.VISIBLE

            layout_csl_bottom.visibility = View.VISIBLE

            tv_intro_fee.visibility = View.GONE

            tv_timer_pay.visibility = View.GONE

            tv_goto_pay.visibility = View.GONE

            tv_agree.visibility = View.VISIBLE//同意

            tv_refuse.visibility = View.VISIBLE//拒绝

            tv_confirm_complete.visibility = View.GONE//确认完成

            tv_order_again.visibility = View.GONE//再次下单

            tv_evaluate.visibility = View.GONE//评价

            tv_appeal.visibility = View.GONE//申诉

            tv_appeal_intro.visibility = View.GONE//申诉等待中

        } else if(orderDetail.status == 4){//确认完成

            layout_bottom.visibility = View.VISIBLE

            layout_csl_bottom.visibility = View.VISIBLE

            tv_intro_fee.visibility = View.GONE

            tv_timer_pay.visibility = View.GONE

            tv_goto_pay.visibility = View.GONE

            tv_agree.visibility = View.GONE//同意

            tv_refuse.visibility = View.GONE//拒绝

            tv_confirm_complete.visibility = View.VISIBLE//确认完成

            tv_order_again.visibility = View.GONE//再次下单

            tv_evaluate.visibility = View.GONE//评价

            tv_appeal.visibility = View.GONE//申诉

            tv_appeal_intro.visibility = View.GONE//申诉等待中

        } else if(orderDetail.status == 5){//评价

            layout_bottom.visibility = View.VISIBLE

            layout_csl_bottom.visibility = View.VISIBLE

            tv_intro_fee.visibility = View.GONE

            tv_timer_pay.visibility = View.GONE

            tv_goto_pay.visibility = View.GONE

            tv_agree.visibility = View.GONE//同意

            tv_refuse.visibility = View.GONE//拒绝

            tv_confirm_complete.visibility = View.GONE//确认完成

            tv_order_again.visibility = View.VISIBLE//再次下单

            if(orderDetail.is_discuss == 1){
                tv_evaluate.visibility = View.GONE//评价
            } else {
                tv_evaluate.visibility = View.VISIBLE//评价
            }

            tv_appeal.visibility = View.GONE//申诉

            tv_appeal_intro.visibility = View.GONE//申诉等待中

        } else if(orderDetail.status == 83){//退款失败
            layout_bottom.visibility = View.VISIBLE

            layout_csl_bottom.visibility = View.VISIBLE

            tv_intro_fee.visibility = View.VISIBLE

            tv_timer_pay.visibility = View.GONE

            tv_goto_pay.visibility = View.GONE

            tv_agree.visibility = View.GONE//同意

            tv_refuse.visibility = View.GONE//拒绝

            tv_confirm_complete.visibility = View.GONE//确认完成

            tv_order_again.visibility = View.GONE//再次下单

            tv_evaluate.visibility = View.GONE//评价

            tv_appeal.visibility = View.VISIBLE//申诉

            tv_appeal_intro.visibility = View.GONE//申诉等待中

        }else if(orderDetail.status == 84){//申诉中

            layout_bottom.visibility = View.VISIBLE

            layout_csl_bottom.visibility = View.VISIBLE

            tv_intro_fee.visibility = View.GONE

            tv_timer_pay.visibility = View.GONE

            tv_goto_pay.visibility = View.GONE

            tv_agree.visibility = View.GONE//同意

            tv_refuse.visibility = View.GONE//拒绝

            tv_confirm_complete.visibility = View.GONE//确认完成

            tv_order_again.visibility = View.GONE//再次下单

            tv_evaluate.visibility = View.GONE//评价

            tv_appeal.visibility = View.GONE//申诉

            tv_appeal_intro.visibility = View.VISIBLE//申诉等待中

        } else if(orderDetail.status == 6 || orderDetail.status == 7 || orderDetail.status == 82){

            layout_bottom.visibility = View.VISIBLE

            layout_csl_bottom.visibility = View.VISIBLE

            tv_intro_fee.visibility = View.GONE

            tv_timer_pay.visibility = View.GONE

            tv_goto_pay.visibility = View.GONE

            tv_agree.visibility = View.GONE//同意

            tv_refuse.visibility = View.GONE//拒绝

            tv_confirm_complete.visibility = View.GONE//确认完成

            tv_order_again.visibility = View.VISIBLE//再次下单

            tv_evaluate.visibility = View.GONE//评价

            tv_appeal.visibility = View.GONE//申诉

            tv_appeal_intro.visibility = View.GONE//申诉等待中
        }
        else {

            layout_bottom.visibility = View.GONE

        }
    }

    //同意或者拒绝
    private fun godReceipt(orderId:String,type:String) {
        //keyword:订单状态:1待支付2待接单3待服务4进行中8退款/申诉(不传默认所有)
        //type:1我的下单2我的接单
        showDialogLoding()
        RxUtils.loading(commonModel!!.go_ljwf_hand(orderId,type))
                .subscribe(object : ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    override fun onNext(orderResult: BaseBean) {

                        disDialogLoding()

                        EventBus.getDefault().post(FirstEvent("指定发送", Constant.REFRESH_ORDER))

                        getDataFromServer()

                    }
                    override fun onError(t: Throwable) {
                        super.onError(t)
                        disDialogLoding()
                    }
                })
    }

    //申请退款
    private fun go_apply_refund(orderId:String) {
        //keyword:订单状态:1待支付2待接单3待服务4进行中8退款/申诉(不传默认所有)
        //type:1我的下单2我的接单
        showDialogLoding()
        RxUtils.loading(commonModel!!.go_apply_refund(orderId))
                .subscribe(object : ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    override fun onNext(orderResult: BaseBean) {
                        disDialogLoding()

                        EventBus.getDefault().post(FirstEvent("指定发送", Constant.REFRESH_ORDER))

                        getDataFromServer()

                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)
                        disDialogLoding()
                    }
                })
    }
    //取消订单
    private fun go_cancel(orderId:String,reason:String) {
        //keyword:订单状态:1待支付2待接单3待服务4进行中8退款/申诉(不传默认所有)
        //type:1我的下单2我的接单
        showDialogLoding()
        RxUtils.loading(commonModel!!.go_cancel(orderId,reason))
                .subscribe(object : ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    override fun onNext(orderResult: BaseBean) {

                        disDialogLoding()

                        EventBus.getDefault().post(FirstEvent("指定发送", Constant.REFRESH_ORDER))

                        getDataFromServer()

                    }
                    override fun onError(t: Throwable) {
                        super.onError(t)
                        disDialogLoding()
                    }
                })
    }
    //确认完成订单
    private fun go_finish(orderId:String) {
        //keyword:订单状态:1待支付2待接单3待服务4进行中8退款/申诉(不传默认所有)
        //type:1我的下单2我的接单
        showDialogLoding()
        RxUtils.loading(commonModel!!.go_finish(orderId))
                .subscribe(object : ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    override fun onNext(orderResult: BaseBean) {

                        disDialogLoding()

                        EventBus.getDefault().post(FirstEvent("指定发送", Constant.REFRESH_ORDER))

                        getDataFromServer()

                    }
                    override fun onError(t: Throwable) {
                        super.onError(t)
                        disDialogLoding()
                    }
                })
    }

    //支付
    fun payOrder(item: OrderDetail) {

        var paymentWindow = PaymentWindow(this, commonModel, mErrorHandler, UserManager.getUser().userId.toString(), item.total_price, item.id)

        paymentWindow.show()

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
