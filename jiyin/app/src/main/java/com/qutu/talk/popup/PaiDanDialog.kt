package com.qutu.talk.popup

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextUtils
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import com.qutu.talk.R
import com.qutu.talk.app.utils.RxUtils
import com.qutu.talk.service.CommonModel
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import kotlinx.android.synthetic.main.dialog_go_set_paidan.*
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.view.OptionsPickerView
import com.jess.arms.utils.LogUtils
import com.qutu.talk.bean.*
import com.qutu.talk.utils.*
import org.greenrobot.eventbus.EventBus
import java.text.SimpleDateFormat
import java.util.*


class PaiDanDialog: Dialog {

    var mContext:Context? = null


    var mOnSelctItemListner:onItemClickListener? = null

    var mCommonModel:CommonModel? = null

    var mRxErrorHandler: RxErrorHandler? = null

    var mRoomName: String? = ""

    var mRoomId: String? = ""


    var mSelectGameId: String = ""

    var mSelectDay: String? = ""

    var mSelectHour: String? = ""

    var mSelectMinate:String? = ""

    var mIsCuntDownPaiDan:Boolean = false//是否正在派单

    var mPd_id:String = ""//单子id

    constructor(context: Context,roomName:String,roomId:String,isCountDownPaidan:Boolean):super(context, R.style.myChooseDialog){
        mContext = context
        this.mRoomName = roomName
        this.mRoomId = roomId
        this.mIsCuntDownPaiDan = isCountDownPaidan
    }

    fun setOnItemSelectListener(listener:onItemClickListener){
        mOnSelctItemListner = listener
    }

    fun setInfo(commonModel: CommonModel,rxErrorHandler: RxErrorHandler){
        this.mCommonModel = commonModel
        this.mRxErrorHandler = rxErrorHandler

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.dialog_go_set_paidan)

        setWidows()

        tv_room_name.text = mRoomName

        tv_room_id.text = "ID:"+mRoomId

        initEditText()

        tv_skill_name.setOnClickListener {
            if(mIsCuntDownPaiDan){
                return@setOnClickListener
            }
            loadGameList()
        }
        tv_service_time.setOnClickListener {
            if(mIsCuntDownPaiDan){
                return@setOnClickListener
            }
            showTimeDialog()
        }

        btn_to_paidan.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {

                if(mIsCuntDownPaiDan){
                    stopPaiDan()
                } else {

                    if(TextUtils.isEmpty(mSelectGameId)){
                        ToastUtil.showToast(mContext,"请选择技能类型")
                        return
                    }
                    if(TextUtils.isEmpty(mSelectDay)){
                        ToastUtil.showToast(mContext,"请选择服务时间")
                        return
                    }

//                    if(TextUtils.isEmpty(et_paidan_remark.text.toString().trim())){
//                        ToastUtil.showToast(mContext,"请输入备注")
//                        return
//                    }

                    goCommitInfo()

                }
            }

        })
        if(mIsCuntDownPaiDan){//正在计时

            img_next_service.visibility = View.GONE

            img_next_type.visibility = View.GONE

            et_paidan_remark.isEnabled = false

            et_paidan_remark.setText("暂无")

            btn_to_paidan.setText("截止派单")

            getPaiDanInfo()
        }


    }

    fun stopPaiDan(){
        if(TextUtils.isEmpty(mPd_id)){
            return
        }
        RxUtils.loading(mCommonModel!!.actionEndPd(mPd_id))
                .subscribe(object : ErrorHandleSubscriber<BaseBean>(mRxErrorHandler) {
                    override fun onNext(resultBean: BaseBean) {

                        if(resultBean.code ==1){

                            ToastUtil.showToast(mContext,resultBean.message)

                            var event:MessageEvent = MessageEvent()

                            event.stateMessage = StateMessage.END_PAIDAN

                            EventBus.getDefault().post(event)

                            dismiss()


                        }

                    }
                })
    }

    private fun getPaiDanInfo() {
        //派单信息
        RxUtils.loading(mCommonModel!!.getPdInfo(mRoomId))
                .subscribe(object : ErrorHandleSubscriber<GetPaiDanInfoResult>(mRxErrorHandler) {
                    override fun onNext(resultBean: GetPaiDanInfoResult) {

                        if(resultBean.code ==1 && resultBean.data != null){

                            var info = resultBean.data

                            mPd_id = info.id

                            var skill_name = info.skill_name

                            var timeSpan = info.time

                            var remark = info.remark

                            tv_skill_name.text = skill_name

                            if(!TextUtils.isEmpty(remark)){
                                et_paidan_remark.setText(remark)
                            }

                            if(!TextUtils.isEmpty(timeSpan)){
                                tv_service_time.text = timeSpan
//                                var times = Arith.strToLong(timeSpan)*1000
//                                var timeStr = TimeSwitchUtil.millis2String(times,SimpleDateFormat("yyyy-MM-dd HH:mm"))
//                                tv_service_time.text = timeStr
                            }

                        }

                    }
                })
    }

    private fun goCommitInfo() {

        var norDayTimeSpan:Long = 0

        var norDayString:String = TimeSwitchUtil.getNowString(SimpleDateFormat("yyyy-MM-dd"))

        norDayString= norDayString+" "+mSelectHour+":"+mSelectMinate;

        if(mSelectDay.equals("1")){//今天

            LogUtils.debugInfo("norDayString==" + norDayString)

            norDayTimeSpan = TimeSwitchUtil.string2Millis(norDayString,SimpleDateFormat("yyyy-MM-dd HH:mm"))/1000

            LogUtils.debugInfo("今天的=时间戳=" + norDayTimeSpan)

        } else if(mSelectDay.equals("2")){//明天

            //第一步获取今天对应的时间

            //第二步，获取24小时对应的时间戳
            var twoTimeStr:Long = 24*60*60*1000

            var date:Date = TimeSwitchUtil.getDate(norDayString,SimpleDateFormat("yyyy-MM-dd HH:mm"),twoTimeStr, TimeConstants.MSEC)

            var time = TimeSwitchUtil.date2String(date,SimpleDateFormat("yyyy-MM-dd HH:mm"))

            LogUtils.debugInfo("明天的=时间=" + time)

            norDayTimeSpan = TimeSwitchUtil.date2Millis(date)/1000

            LogUtils.debugInfo("明天的=时间戳=" + norDayTimeSpan)

        } else if(mSelectDay.equals("3")){//后天

            var twoTimeStr:Long = 48*60*60*1000

            var date:Date = TimeSwitchUtil.getDate(norDayString,SimpleDateFormat("yyyy-MM-dd HH:mm"),twoTimeStr, TimeConstants.MSEC)

            var time = TimeSwitchUtil.date2String(date,SimpleDateFormat("yyyy-MM-dd HH:mm"))

            LogUtils.debugInfo("后天的=时间=" + time)

            norDayTimeSpan = TimeSwitchUtil.date2Millis(date)/1000
            LogUtils.debugInfo("后天的=时间戳=" + norDayTimeSpan)

        }


        //开始派单
        RxUtils.loading(mCommonModel!!.actionStartPd(mSelectGameId,mRoomId,norDayTimeSpan.toString(),et_paidan_remark.text.toString().trim()))
                .subscribe(object : ErrorHandleSubscriber<BaseBean>(mRxErrorHandler) {
                    override fun onNext(giftListBean: BaseBean) {

                        if(giftListBean.code ==1){

                            ToastUtil.showToast(mContext,giftListBean.message)

                            var event:MessageEvent = MessageEvent()

                            event.stateMessage = StateMessage.START_PAIDAN

                            EventBus.getDefault().post(event)

                            dismiss()

                        }

                    }
                })

    }

    private fun showTimeDialog(){
        var dayList = ArrayList<PaiDanTimeBean>()
        var hourList = ArrayList<PaiDanTimeBean>()
        var minateList = ArrayList<PaiDanTimeBean>()

        var paiDanTimeBean:PaiDanTimeBean = PaiDanTimeBean()
        paiDanTimeBean.id = "1"
        paiDanTimeBean.name = "今天"
        paiDanTimeBean.unit = ""
        dayList.add(paiDanTimeBean)
        var paiDanTimeBean1:PaiDanTimeBean = PaiDanTimeBean()
        paiDanTimeBean1.id = "2"
        paiDanTimeBean1.name = "明天"
        paiDanTimeBean.unit = ""
        dayList.add(paiDanTimeBean1)
        var paiDanTimeBean2:PaiDanTimeBean = PaiDanTimeBean()
        paiDanTimeBean2.id = "3"
        paiDanTimeBean2.name = "后天"
        paiDanTimeBean.unit = ""
        dayList.add(paiDanTimeBean2)

        for(i in 0..23){
            var paiDanTimeBean:PaiDanTimeBean = PaiDanTimeBean()
            paiDanTimeBean.id = i.toString()
            if(i<10){
                paiDanTimeBean.name = "0"+i.toString()
            } else {
                paiDanTimeBean.name = i.toString()
            }
            paiDanTimeBean.unit = "时"
            hourList.add(paiDanTimeBean)
        }

        var mite = setOf("00","15","30","45")
        for(i in mite){
            var paiDanTimeBean:PaiDanTimeBean = PaiDanTimeBean()
            paiDanTimeBean.id = i
            paiDanTimeBean.name = i
            paiDanTimeBean.unit = "分"
            minateList.add(paiDanTimeBean)
        }

        val hourL = Arrays.asList<PaiDanTimeBean>(*arrayOfNulls(hourList.size))
        val minateL = Arrays.asList<PaiDanTimeBean>(*arrayOfNulls(minateList.size))

        var hourOldList = ArrayList<PaiDanTimeBean>(hourL)
        var minateOldList = ArrayList<PaiDanTimeBean>(minateL)

        Collections.copy<PaiDanTimeBean>(hourOldList, hourList)
        Collections.copy<PaiDanTimeBean>(minateOldList, minateList)

        getAfterNowTime(dayList,hourList,minateList)

        var hourTwo:java.util.ArrayList<java.util.ArrayList<PaiDanTimeBean>> = ArrayList<ArrayList<PaiDanTimeBean>>()

        for(item in 0..dayList.size-1){
            when(item){
                0->{
                    hourTwo.add(hourList)
                }
                else->{
                    hourTwo.add(hourOldList as ArrayList<PaiDanTimeBean>)
                }
            }
        }

        var lastDayList:ArrayList<ArrayList<ArrayList<PaiDanTimeBean>>> = ArrayList<ArrayList<ArrayList<PaiDanTimeBean>>>()

//        var lastHList:ArrayList<ArrayList<PaiDanTimeBean>> = ArrayList<ArrayList<PaiDanTimeBean>>()

//        var lastMList:ArrayList<PaiDanTimeBean> = ArrayList<PaiDanTimeBean>()


        for(item in 0..hourTwo.size-1){
            var lastHList:ArrayList<ArrayList<PaiDanTimeBean>> = ArrayList<ArrayList<PaiDanTimeBean>>()

            lastDayList.add(lastHList)

            var hList:ArrayList<PaiDanTimeBean> = hourTwo[item]

            if(item ==0){
                for(mm in 0..hList.size-1){
//                    var minateLast:java.util.ArrayList<java.util.ArrayList<PaiDanTimeBean>> = ArrayList<ArrayList<PaiDanTimeBean>>()
                    if(mm == 0){
//                        minateLast.add(minateList as ArrayList<PaiDanTimeBean>)
                        lastHList.add(minateList)
                    } else {
//                        minateLast.add(minateOldList as ArrayList<PaiDanTimeBean>)
                        lastHList.add(minateOldList)
                    }
                }
            } else {
                for(mm in 0..hList.size-1){
//                    var minateLast:java.util.ArrayList<java.util.ArrayList<PaiDanTimeBean>> = ArrayList<ArrayList<PaiDanTimeBean>>()
//                    minateLast.add(minateOldList as ArrayList<PaiDanTimeBean>)
                    lastHList.add(minateOldList)
                }
            }

        }

        //条件选择器
        val pvOptions = OptionsPickerBuilder(mContext, object : OnOptionsSelectListener {
            override fun onOptionsSelect(options1: Int, option2: Int, options3: Int, v: View?) {
                //返回的分别是三个级别的选中位置

                var txt = dayList.get(options1).name+hourList.get(option2).name+":"+minateList.get(options3).name

                var selectDay = dayList.get(options1).id
//                var selectHour = hourList.get(option2).name
//                var selectMinate = minateList.get(options3).name
                var selectHour = hourTwo[options1][option2].name
                var selectMinate = lastDayList[options1][option2][options3].name

                //判断是否小于当前时间
                if(selectDay.equals("1")){//是今天的话比较一下是否小于当前时间
                    var small = isSmallCurrentTime(selectDay,selectHour,selectMinate)
                    if(small){
                        return
                    }
                }
                mSelectDay = dayList.get(options1).id
                mSelectHour = hourList.get(option2).name
                mSelectMinate = minateList.get(options3).name

                tv_service_time.setText(txt)
            }
        }).isDialog(true).build<Any>()
//        pvOptions.setNPicker(dayList as List<Any>?, hourList as List<Any>?, minateList as List<Any>?)
        pvOptions.setPicker(dayList as List<Any>?, hourTwo as List<MutableList<Any>>?, lastDayList as List<MutableList<MutableList<Any>>>?)

        pvOptions.dialog.window.setGravity(Gravity.BOTTOM)
        pvOptions.show()
    }

    private fun getAfterNowTime(dayList:ArrayList<PaiDanTimeBean>,hourList:ArrayList<PaiDanTimeBean>,minateList:ArrayList<PaiDanTimeBean>){

        var norDayString:String = TimeSwitchUtil.getNowString(SimpleDateFormat("HH:mm"))

        var times:List<String> = norDayString.split(":")

        var hour = times[0]//当前是几点

        var minate = times[1]//当前是几分钟

        if(minate.toInt()>=26){//分钟大于26分钟，小时往前进1

            var h = hour.toInt()+1

            if(h<=23){
                for(item in hourList){//得到小时
                    if(h == item.name!!.toInt()){
                        var position = hourList.indexOf(item)
                        if(position<23){
                            var newList = ArrayList<PaiDanTimeBean>()
                            for(p in position..hourList.size-1){
                                newList.add(hourList.get(p))
                            }
                            hourList.clear()
                            hourList.addAll(newList)
                            break
                        }
                    }
                }
                var mite = setOf("15","30","45")
                if(minate.toInt()>=26 && minate.toInt()<=40){
                    minateList.clear()
                    mite = setOf("00","15","30","45")
                } else if(minate.toInt()>=41 && minate.toInt()<=55){//得到分钟
                    minateList.clear()
                    mite = setOf("15","30","45")

                } else if(minate.toInt()>=56 && minate.toInt()<=59){
                    minateList.clear()
                    mite = setOf("30","45")
                }
                for(i in mite){
                    var paiDanTimeBean:PaiDanTimeBean = PaiDanTimeBean()
                    paiDanTimeBean.id = i
                    paiDanTimeBean.name = i
                    paiDanTimeBean.unit = "分"
                    minateList.add(paiDanTimeBean)
                }
            } else {//天数加1
                dayList.removeAt(0)
            }
        } else{//分钟
            var h = hour.toInt()
            for(item in hourList){//得到小时
                if(h == item.name!!.toInt()){
                    var position = hourList.indexOf(item)
                    if(position<23){
                        var newList = ArrayList<PaiDanTimeBean>()
                        for(p in position..hourList.size-1){
                            newList.add(hourList.get(p))
                        }
                        hourList.clear()
                        hourList.addAll(newList)
                        break
                    }
                }
            }
            var mite = setOf("15","30","45")
            if(minate.toInt()>=0 && minate.toInt()<=10){
                minateList.clear()
                mite = setOf("30","45")
            } else if(minate.toInt()>=11 && minate.toInt()<=25){
                minateList.clear()
                mite = setOf("45")
            }
            for(i in mite){
                var paiDanTimeBean:PaiDanTimeBean = PaiDanTimeBean()
                paiDanTimeBean.id = i
                paiDanTimeBean.name = i
                paiDanTimeBean.unit = "分"
                minateList.add(paiDanTimeBean)
            }
        }
    }

    //是否小于当前时间
    private fun isSmallCurrentTime(selectDay:String?,selectHour:String?,selectMinate:String?):Boolean{

        var norDayString:String = TimeSwitchUtil.getNowString(SimpleDateFormat("yyyy-MM-dd"))

        norDayString= norDayString+" "+selectHour+":"+selectMinate;

        LogUtils.debugInfo("norDayString==" + norDayString)

        var norDayTimeSpan:Long = TimeSwitchUtil.string2Millis(norDayString,SimpleDateFormat("yyyy-MM-dd HH:mm"))

        LogUtils.debugInfo("norDayString=时间戳=" + norDayTimeSpan)

        var nowTimeString:Long = TimeSwitchUtil.getNowMills()//当前时间戳

        LogUtils.debugInfo("当前时间戳=" + nowTimeString)

        if(nowTimeString-norDayTimeSpan>=0){
            ToastUtil.showToast(mContext,"不能小于当前时间")
            return true
        }
        return false
    }
    /**
     * 游戏类型
     */
    private fun loadGameList() {

        RxUtils.loading(mCommonModel!!.getAllSkillsList())
                .subscribe(object : ErrorHandleSubscriber<GetGameListResult>(mRxErrorHandler) {
                    override fun onNext(giftListBean: GetGameListResult) {

                        if (giftListBean != null && giftListBean.data != null) {

                            var selectGameSegmentDialog: SelectGameTypeDialog = SelectGameTypeDialog(mContext!!, giftListBean.data as ArrayList<GameItemBean>)

                            selectGameSegmentDialog.setOnItemSelectListener(object :SelectGameTypeDialog.onItemClickListener{

                                override fun onSegmentItemClick(price: GameItemBean) {
                                    tv_skill_name.setText(price.name)
                                    mSelectGameId = price.id
                                }

                            })

                            selectGameSegmentDialog.show()



                        }

                    }
                })

    }

    private fun initEditText() {

        et_paidan_remark.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence?, i: Int, i1: Int, i2: Int) {

                if (charSequence != null && charSequence.length > 0) {

                    val length = charSequence.length

                    tv_paidan_count.setText("$length/50")

                } else {
                    tv_paidan_count.setText("0/50")
                }

            }

            override fun afterTextChanged(editable: Editable) {

            }
        })

        et_paidan_remark.setFilters(arrayOf<InputFilter>(EditTextMaxLengthFilter(mContext, 50, "输入文字超过最大长度了")))

    }

    private fun setWidows() {

        val display = window!!.windowManager.defaultDisplay

        val lp = window!!.attributes

        lp.width = display.width - QMUIDisplayHelper.dp2px(mContext, 72)

        lp.alpha = 1.0f

        lp.gravity = Gravity.CENTER

        window!!.attributes = lp

        this.setCanceledOnTouchOutside(true)

    }

    interface onItemClickListener{
        fun onSegmentItemClick(price:SegmentItem)
    }

}