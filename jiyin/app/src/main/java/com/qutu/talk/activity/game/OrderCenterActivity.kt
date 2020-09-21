package com.qutu.talk.activity.game

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.TypedValue
import com.jess.arms.di.component.AppComponent
import com.qutu.talk.R
import com.qutu.talk.adapter.PagerAdapter
import com.qutu.talk.base.MyBaseArmActivity
import com.qutu.talk.bean.FirstEvent
import com.qutu.talk.di.CommonModule
import com.qutu.talk.di.DaggerCommonComponent
import com.qutu.talk.fragment.OrderAcceptFragment
import com.qutu.talk.fragment.OrderBuyFragment
import com.qutu.talk.utils.Constant.PAIDANZHONGXIN
import kotlinx.android.synthetic.main.activity_order_center.*
import org.greenrobot.eventbus.EventBus
import java.util.*

class OrderCenterActivity : MyBaseArmActivity() {

    val listFragment = ArrayList<Fragment>()

    var mCurrentTab = 0

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(CommonModule())
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_order_center
    }

    override fun initData(savedInstanceState: Bundle?) {

        title = "订单中心"
        initPager()
        tv_me_buy.setOnClickListener {

            if(mCurrentTab == 0){
                return@setOnClickListener
            }
            mCurrentTab = 0
            switchTitle(mCurrentTab)

        }

        tv_me_accept.setOnClickListener {

            if(mCurrentTab == 1){
                return@setOnClickListener
            }
            mCurrentTab = 1
            switchTitle(mCurrentTab)

        }


    }

    private fun switchTitle(pageIndex:Int){
        if(pageIndex == 1){
            tv_me_accept.setTextColor(resources.getColor(R.color.font_ff3e6d))

            tv_me_accept.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);

            tv_me_accept.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

            tv_me_buy.setTextColor(resources.getColor(R.color.font_999999))

            tv_me_buy.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);

            tv_me_buy.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));

            view_pager_order.setCurrentItem(mCurrentTab)
        } else if(pageIndex == 0){
            tv_me_buy.setTextColor(resources.getColor(R.color.font_ff3e6d))

            tv_me_buy.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);

            tv_me_buy.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

            tv_me_accept.setTextColor(resources.getColor(R.color.font_999999))

            tv_me_accept.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);

            tv_me_accept.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));

            view_pager_order.setCurrentItem(mCurrentTab)
        }
    }

    private fun initPager() {

        listFragment.add(OrderBuyFragment())
        listFragment.add(OrderAcceptFragment())

        val pagerAdapter = PagerAdapter(supportFragmentManager, listFragment)
        view_pager_order.adapter = pagerAdapter
        view_pager_order.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{

            override fun onPageSelected(page: Int) {

                mCurrentTab = page

                switchTitle(mCurrentTab)

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageScrollStateChanged(page: Int) {

            }

        })
    }

    override fun onDestroy() {
        EventBus.getDefault().post(FirstEvent("派单中心", PAIDANZHONGXIN))
        super.onDestroy()
    }

}
