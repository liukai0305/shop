package com.qutu.talk.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.qutu.talk.R
import com.qutu.talk.activity.game.EvaluateOrderActivity
import com.qutu.talk.adapter.OrderListAdapter
import com.qutu.talk.adapter.OrderListGodAdapter
import com.qutu.talk.app.utils.RxUtils
import com.qutu.talk.base.MyBaseArmActivity
import com.qutu.talk.base.MyBaseArmFragment
import com.qutu.talk.bean.*
import com.qutu.talk.di.CommonModule
import com.qutu.talk.di.DaggerCommonComponent
import com.qutu.talk.service.CommonModel
import com.qutu.talk.utils.Constant
import com.qutu.talk.utils.DealRefreshHelper
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_order.*
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*
import javax.inject.Inject

class OrderAcceptFragment:MyBaseArmFragment(),OrderListGodAdapter.OnButtonClickListener {

    var mSelectTab: Int = 0

    internal var mPullRefreshBean = PullRefreshBean()

    var mActivity:MyBaseArmActivity? = null

    var mDataList = ArrayList<OrderItem>()

    var mAdapter: OrderListGodAdapter? = null

    var mStatus = ""

    @Inject
    open lateinit var commonModel: CommonModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mActivity = context as MyBaseArmActivity?
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(CommonModule())
                .build()
                .inject(this)
    }

    override fun initData(savedInstanceState: Bundle?) {

        initTab()

        initRecyclerView()

        initRefreshLayout()

        getDataFromServer()

    }

    override fun getLayoutView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_order);
    }

    private fun initRefreshLayout() {

        refresh_layout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(@NonNull refreshLayout: RefreshLayout) {


                mPullRefreshBean.setLoardMore(mPullRefreshBean, refresh_layout)

                getDataFromServer()

            }

            override fun onRefresh(@NonNull refreshLayout: RefreshLayout) {

                mPullRefreshBean.setRefresh(mPullRefreshBean, refresh_layout)

                getDataFromServer()

            }
        })

    }

    private fun initRecyclerView() {

        val manager = LinearLayoutManager(mActivity)

        manager.setOrientation(RecyclerView.VERTICAL)

        rlv_order.setHasFixedSize(true)

        rlv_order.setLayoutManager(manager)

        mAdapter = OrderListGodAdapter(mActivity, R.layout.item_order_god, mDataList)

        mAdapter!!.setOnClickListener(this)

        rlv_order.setHasFixedSize(true)

        rlv_order.setLayoutManager(manager)

        mAdapter!!.setHasStableIds(true)//防止数据错乱

        rlv_order.adapter = mAdapter

    }

    private fun initTab() {

        //标签集合
        val lstCouPonTabs = ArrayList<CustomTabEntity>()

        //1:全部 2：已付款 3：已评价4：退款
        lstCouPonTabs.add(TabBean(0, "全部"))
        lstCouPonTabs.add(TabBean(2, "待接单"))
        lstCouPonTabs.add(TabBean(3, "待服务"))
        lstCouPonTabs.add(TabBean(4, "进行中"))
        lstCouPonTabs.add(TabBean(8, "退款/申诉"))

        ctl_accompany_tab.setTabData(lstCouPonTabs)

        ctl_accompany_tab.currentTab = mSelectTab

        ctl_accompany_tab.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {


                val data = lstCouPonTabs[position] as TabBean<Int>

                mSelectTab = data.tabKey

                mPullRefreshBean.setRefresh(mPullRefreshBean, refresh_layout)

                when (position) {
                    0 -> mStatus = ""
                    1 -> mStatus = "2"
                    2 -> mStatus = "3"
                    3 -> mStatus = "4"
                    4 -> mStatus = "8"

                }

                getDataFromServer()


            }

            override fun onTabReselect(position: Int) {

            }
        })
    }

    //未读数量
    private fun getUnreadFromServer() {
        //keyword:订单状态:1待支付2待接单3待服务4进行中8退款/申诉(不传默认所有)
        //type:1我的下单2我的接单
        RxUtils.loading(commonModel.go_unread_sum("2"))
                .subscribe(object : ErrorHandleSubscriber<UnReadOrderResult>(mErrorHandler) {
                    override fun onNext(orderResult: UnReadOrderResult) {

                        if (orderResult != null && orderResult.data != null) {

                            var list:List<Int> = orderResult.data;

                            if(list.size == 4){

                                for(i in 1..4){
                                    showUnReadCount(i,list[i-1])
                                }

                            }

                        } else {

                        }

                    }
                })

    }

    //获取数据
    private fun getDataFromServer() {
        //keyword:订单状态:1待支付2待接单3待服务4进行中8退款/申诉(不传默认所有)
        //type:1我的下单2我的接单
        RxUtils.loading(commonModel.go_order_list(mStatus,"2",mPullRefreshBean.pageIndex.toString()))
                .subscribe(object : ErrorHandleSubscriber<OrderListResult>(mErrorHandler) {
                    override fun onNext(orderResult: OrderListResult) {

                        if (orderResult != null && orderResult.data != null) {

                            var list: List<OrderItem> = orderResult.data

                            DealRefreshHelper<OrderItem>().dealDataToUI(refresh_layout,mAdapter!!,ly_view_no_data,list,mDataList,mPullRefreshBean)

                        } else {
                            DealRefreshHelper<OrderItem>().dealDataToUI(refresh_layout,mAdapter!!,ly_view_no_data,ArrayList<OrderItem>(),mDataList,mPullRefreshBean)
                        }
                        getUnreadFromServer()
                    }

                })



    }

    override fun godAgree(item: OrderItem) {
        godReceipt(item,"1")
    }

    override fun godRefuse(item: OrderItem) {
        godReceipt(item,"2")
    }

    override fun godApplyService(item: OrderItem) {
        godApplyService(item.id)
    }

    override fun godEvaluate(item: OrderItem) {
        var intent = Intent(mActivity, EvaluateOrderActivity::class.java)

        intent.putExtra("order_detail",item!!)

        intent.putExtra("order_from",false)

        ArmsUtils.startActivity(intent)
    }

    //同意或者拒绝
    private fun godReceipt(item: OrderItem,type:String) {
        //keyword:订单状态:1待支付2待接单3待服务4进行中8退款/申诉(不传默认所有)
        //type:1我的下单2我的接单
        if(item.status == 81){//申请退款
            RxUtils.loading(commonModel!!.go_apply_refund_hand(item.id,type))
                    .subscribe(object : ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                        override fun onNext(orderResult: BaseBean) {

                            EventBus.getDefault().post(FirstEvent("指定发送", Constant.REFRESH_ORDER))

                        }
                    })
        } else {
            RxUtils.loading(commonModel!!.go_receipt(item.id,type))
                    .subscribe(object : ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                        override fun onNext(orderResult: BaseBean) {

                            EventBus.getDefault().post(FirstEvent("指定发送", Constant.REFRESH_ORDER))

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

                    }
                })
    }

    /**
     * 设置未读消息
     */
    private fun showUnReadCount(tabPosition: Int, unReadCount: Int) {
        if (unReadCount == 0) {
            ctl_accompany_tab.hideMsg(tabPosition)
        } else {
            ctl_accompany_tab.showMsg(tabPosition, unReadCount)
            ctl_accompany_tab.getMsgView(tabPosition).textSize = 6f
            ctl_accompany_tab.setMsgMargin(tabPosition, -10f, 3f)

            var msgView = ctl_accompany_tab.getMsgView (tabPosition)
            val lp = msgView.getLayoutParams() as RelativeLayout.LayoutParams
            if(unReadCount<10){
                lp.width = QMUIDisplayHelper.dpToPx(10)
                lp.height = QMUIDisplayHelper.dpToPx(10)
            } else {
                lp.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
                lp.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
                msgView.setPadding(QMUIDisplayHelper.dpToPx(3), QMUIDisplayHelper.dpToPx(2), QMUIDisplayHelper.dpToPx(3), QMUIDisplayHelper.dpToPx(2))
            }
            msgView.layoutParams =lp
        }

        //        MsgView rtv_2_3 = ctlAccompanyTab.getMsgView(3);//设置消息背景
        //        if (rtv_2_3 != null) {
        //            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
        //        }
    }

    override fun setData(data: Any?) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun receiveMsg(event: FirstEvent) {
        val tag = event.tag
        if (Constant.MILICHONGZHI == tag || Constant.REFRESH_ORDER == tag) {

            mPullRefreshBean.setRefresh(mPullRefreshBean, refresh_layout)

            getDataFromServer()
        }
    }
}