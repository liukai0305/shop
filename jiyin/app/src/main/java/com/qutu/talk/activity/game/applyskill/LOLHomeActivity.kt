package com.qutu.talk.activity.game.applyskill

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.qutu.talk.R
import com.qutu.talk.app.utils.RxUtils
import com.qutu.talk.base.MyBaseArmActivity
import com.qutu.talk.bean.*
import com.qutu.talk.di.CommonModule
import com.qutu.talk.di.DaggerCommonComponent
import com.qutu.talk.popup.SelectGamePriceDialog
import com.qutu.talk.service.CommonModel
import com.qutu.talk.utils.Constant
import kotlinx.android.synthetic.main.activity_lolhome.*
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*
import javax.inject.Inject

class LOLHomeActivity : MyBaseArmActivity(), View.OnClickListener,SelectGamePriceDialog.onSelectItemListener{

    @Inject
    open lateinit var commonModel: CommonModel

    var mFrom:Int = 1

    var mGoodSetting:GameSetting? = null

    override fun setupActivityComponent(appComponent: AppComponent) {

        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(CommonModule())
                .build()
                .inject(this)

    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_lolhome
    }

    override fun initData(savedInstanceState: Bundle?) {

        var intent:Intent = getIntent()

        if(intent != null && intent.extras != null){

            var from: Int = intent.getIntExtra("game_type",1)

            mFrom = from

            if(from == GameType.GAME_LOL.state){
                setTitle("英雄联盟")
            } else if(from == GameType.GAME_WANG_ZHE.state){
                setTitle("王者荣耀")
            } else if(from == GameType.GAME_HE_PING.state){
                setTitle("和平精英")
                layout_good_at_position.visibility = View.GONE
                view_good_at_position.visibility = View.GONE
            } else if(from == GameType.GAME_JUE_DI.state){
                setTitle("绝地求生")
                layout_good_at_position.visibility = View.GONE
                view_good_at_position.visibility = View.GONE
                layout_area.visibility = View.GONE
                view_area.visibility = View.GONE
            }

        } else {
            finish()
        }
        layout_price.setOnClickListener(this)
        layout_area.setOnClickListener(this)
        layout_good_at_position.setOnClickListener(this)
        layout_people_info.setOnClickListener(this)

        loadGameSettingInfo()

    }

    //获取设置信息
    private fun loadGameSettingInfo() {

        RxUtils.loading(commonModel.getSkillSetInfo(mFrom.toString()))
                .subscribe(object : ErrorHandleSubscriber<GetGameSetResult>(mErrorHandler) {
                    override fun onNext(giftListBean: GetGameSetResult) {

                        if (giftListBean != null && giftListBean.data != null) {

                            val gameSetting:GameSetting = giftListBean.data

                            mGoodSetting = giftListBean.data

                            val priceStr:String = gameSetting.price+"金币/"+gameSetting.unit

                            var areas:String = gameSetting.areas;

                            var areaStr:String = ""

                            if(!TextUtils.isEmpty(areas)){
                                val areaArray: List<String> = areas.split(",")
                                if(areaArray.size>=3){
                                    for(i in 1..2){
                                        areaStr+=areaArray[i]
                                        if(i==1){
                                            areaStr+=","
                                        }
                                    }
                                    areaStr += "..."
                                } else {
                                    areaStr = areas;
                                }
                                tv_select_area.text = areaStr//接单大区
                            }
                            tv_value_one_game.text = priceStr//价格
                            if(TextUtils.isEmpty(gameSetting.positions)){
                                tv_good_at_position.text = "请选择"
                            } else {
                                tv_good_at_position.text = gameSetting.positions//擅长位置
                            }

                        }

                    }
                })

    }

    /**
     * 设置价格
     */
    private fun setPrice(priceId:String) {

        RxUtils.loading(commonModel.actionSetPrice(mFrom.toString(),priceId))
                .subscribe(object : ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    override fun onNext(giftListBean: BaseBean) {

                    }
                })
    }
    /**
     * 设置接单大区
     */
    private fun setArea(ids:String) {

        RxUtils.loading(commonModel.actionSetArea(mFrom.toString(),ids))
                .subscribe(object : ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    override fun onNext(giftListBean: BaseBean) {

                    }
                })
    }
    /**
     * 设置接擅长位置
     */
    private fun setGoodAtPosition(ids:String) {

        RxUtils.loading(commonModel.actionSetPosition(mFrom.toString(),ids))
                .subscribe(object : ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    override fun onNext(giftListBean: BaseBean) {

                    }
                })
    }

    /**
     * 获取价格列表
     */
    private fun loadPriceList() {

        RxUtils.loading(commonModel.getSkillPriceList(mFrom.toString()))
                .subscribe(object : ErrorHandleSubscriber<GetPriceListResult>(mErrorHandler) {
                    override fun onNext(giftListBean: GetPriceListResult) {

                        if (giftListBean != null && giftListBean.data != null) {

                            var priceDialog: SelectGamePriceDialog = SelectGamePriceDialog(this@LOLHomeActivity,mFrom,1)
                            priceDialog.setPriceList(giftListBean.data as ArrayList<GamePriceItem>)
                            priceDialog.setOnItemSelectListener(this@LOLHomeActivity)
                            priceDialog.show()

                        }

                    }
                })
    }

    /**
     * 获取大区列表
     */
    private fun loadAreaList() {

        RxUtils.loading(commonModel.getSkillAreaList(mFrom.toString()))
                .subscribe(object : ErrorHandleSubscriber<GetAreaListResult>(mErrorHandler) {
                    override fun onNext(giftListBean: GetAreaListResult) {

                        if (giftListBean != null && giftListBean.data != null) {

                            var priceDialog: SelectGamePriceDialog = SelectGamePriceDialog(this@LOLHomeActivity,mFrom,2)
                            priceDialog.setAreaList(giftListBean.data as ArrayList<GameAreaItem>)
                            priceDialog.setOnItemSelectListener(this@LOLHomeActivity)
                            priceDialog.show()

                        }

                    }
                })


    }
    /**
     * 获取擅长位置列表
     */
    private fun loadGoodPositionList() {

        RxUtils.loading(commonModel.getSkillPositionList(mFrom.toString()))
                .subscribe(object : ErrorHandleSubscriber<GetGoodAtPositionListResult>(mErrorHandler) {
                    override fun onNext(giftListBean: GetGoodAtPositionListResult) {

                        if (giftListBean != null && giftListBean.data != null) {

                            var priceDialog: SelectGamePriceDialog = SelectGamePriceDialog(this@LOLHomeActivity,mFrom,3)
                            priceDialog.setGoodAtPositionList(giftListBean.data as ArrayList<GameGoodAtPositionItem>)
                            priceDialog.setOnItemSelectListener(this@LOLHomeActivity)
                            priceDialog.show()

                        }

                    }
                })


    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.layout_price -> {//价格
                loadPriceList()
            }
            R.id.layout_area -> {//接单大区

                loadAreaList()
            }
            R.id.layout_good_at_position -> {//擅长位置

                loadGoodPositionList()

            }
            R.id.layout_people_info -> {//个人信息

                if(mGoodSetting == null){
                    return
                } else {

                    var status = mGoodSetting!!.status
                    if(status.equals("1")){//0=可提交审核 1=存在尚未通过申请的审核
                        var intent = Intent(this@LOLHomeActivity, GameApplyingActivity::class.java)
                        intent.putExtra("game_type",mFrom)
                        ArmsUtils.startActivity(intent)
                    } else {
                        var intent = Intent(this@LOLHomeActivity, GameDetailInfoActivity::class.java)
                        intent.putExtra("game_type",mFrom)
                        ArmsUtils.startActivity(intent)
                    }
                }
            }

        }
    }

    override fun onPriceSelect(price: GamePriceItem) {

        tv_value_one_game.text = price.price+"金币/"+price.unit

        setPrice(price.id!!)

    }

    override fun onAreaSelect(list: ArrayList<GameAreaItem>) {
        if(list != null && list.size>0){
            var builder = StringBuilder()
            if(list.size<=2){
                for(item in list){
                    var name = item.name
                    builder.append(name)
                    builder.append(",")
                }
                builder = builder.deleteCharAt(builder.length-1)
            } else {
                for(i in 1..2){
                    var name = list.get(i).name
                    builder.append(name)
                    builder.append(",")
                }
                builder = builder.deleteCharAt(builder.length-1)
                builder.append("...")
            }
            tv_select_area.text = builder.toString()
        } else {
            tv_select_area.text = "请选择"
        }
        var builder = StringBuilder()
        for(item in list){
            builder.append(item.id)
            builder.append(",")
        }
        if(builder.length>0){
            builder = builder.deleteCharAt(builder.length-1)
        }
        setArea(builder.toString())

    }

    override fun onGoodAtPositionSelect(list: ArrayList<GameGoodAtPositionItem>) {
//        tv_good_at_position.text = goodAtPosition.name

        if(list != null && list.size>0){
            var builder = StringBuilder()
//            if(list.size<=2){
                for(item in list){
                    var name = item.name
                    builder.append(name)
                    builder.append(",")
                }
                builder = builder.deleteCharAt(builder.length-1)
//            } else {
//                for(i in 1..2){
//                    var name = list.get(i).name
//                    builder.append(name)
//                    builder.append(",")
//                }
//                builder = builder.deleteCharAt(builder.length-1)
//                builder.append("...")
//            }
            tv_good_at_position.text = builder.toString()
        } else {
            tv_good_at_position.text = "请选择"
        }
        var builder = StringBuilder()
        for(item in list){
            builder.append(item.id)
            builder.append(",")
        }
        if(builder.length>0){
            builder = builder.deleteCharAt(builder.length-1)
        }
        setGoodAtPosition(builder.toString())
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun receiveMsg(event: FirstEvent) {
        val tag = event.tag
//        val msg = event.msg
        if (Constant.COMMIT_GAME_INFO == tag) {//刷新
            loadGameSettingInfo()
        }
    }
}
