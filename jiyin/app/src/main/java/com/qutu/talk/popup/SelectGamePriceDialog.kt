package com.qutu.talk.popup

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import com.qutu.talk.R
import com.qutu.talk.adapter.GameAreaAdapter
import com.qutu.talk.adapter.GameGoodAtPositionAdapter
import com.qutu.talk.adapter.GamePriceAdapter
import com.qutu.talk.bean.GameAreaItem
import com.qutu.talk.bean.GameGoodAtPositionItem
import com.qutu.talk.bean.GamePriceItem
import com.qutu.talk.service.CommonModel
import com.qutu.talk.utils.ToastUtil
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import kotlinx.android.synthetic.main.dialog_select_game_price.*
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import java.util.*

class SelectGamePriceDialog: Dialog {

    var mContext:Context? = null

    var mGamePriceAdapter: GamePriceAdapter? = null

    var mGameAreaAdapter: GameAreaAdapter? = null

    var mGameGoodAtPositionAdapter: GameGoodAtPositionAdapter? = null

    var mDataPriceList:ArrayList<GamePriceItem>? = null

    var mDataAreaList:ArrayList<GameAreaItem>? = null

    var mDataGoodAtPositionList:ArrayList<GameGoodAtPositionItem>? = null

    var mGameType:Int = 1//游戏类型

    var mDialogType:Int = 1//dialog类型，1选择价格，2选择大区,3 擅长位置

    var mSpanCount:Int = 3

    var mOnSelctItemListner:onSelectItemListener? = null

    constructor(context: Context,gameType:Int,dialogType:Int):super(context, R.style.myChooseDialog){
        mContext = context

        mGameType = gameType
        mDialogType = dialogType
        if(mDialogType == 1){
            mSpanCount = 3
        } else if(mDialogType == 2){
            mSpanCount = 4
        } else if(mDialogType == 3){
            mSpanCount = 4
        }
    }

    fun setPriceList(data:ArrayList<GamePriceItem>){
        mDataPriceList = data
//        if(mGamePriceAdapter != null){
//            mGamePriceAdapter!!.notifyDataSetChanged()
//        }
    }

    fun setAreaList(data:ArrayList<GameAreaItem>){
        mDataAreaList = data
//        if(mGameAreaAdapter != null){
//            mGameAreaAdapter!!.notifyDataSetChanged()
//        }
    }
    fun setGoodAtPositionList(data:ArrayList<GameGoodAtPositionItem>){
        mDataGoodAtPositionList = data
    }

    fun setOnItemSelectListener(listener:onSelectItemListener){
        mOnSelctItemListner = listener
    }

    open fun setInfo(commonModel: CommonModel,rxErrorHandler: RxErrorHandler){

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.dialog_select_game_price)

        setWidows()

        if(mDialogType == 1){
            tv_price_title.text = "设置价格"
        } else if(mDialogType == 2){
            tv_price_title.text = "可接单大区"
        } else if(mDialogType == 3){
            tv_price_title.text = "擅长位置"
        }

        initRecyclerView()

        tv_cancel.setOnClickListener {
            dismiss()
        }

        tv_confirm.setOnClickListener {
            dismiss()
            if(mDialogType ==1){//价格

                for(m in mDataPriceList!!){
                    if(m.checked==1){
                        if(mOnSelctItemListner != null){
                            mOnSelctItemListner!!.onPriceSelect(m)
                        }
                        break
                    }
                }

            } else if(mDialogType ==2){//大区

                var selectList: ArrayList<GameAreaItem> = ArrayList<GameAreaItem>()

//                var index = 0
                for (i in mDataAreaList!!.indices) {
                    if (mDataAreaList!!.get(i).checked==1) {
                        selectList!!.add(mDataAreaList!!.get(i))
//                        index = i
//                        break
                    }
                }
                mOnSelctItemListner!!.onAreaSelect(selectList)

//                for(m in mDataAreaList!!){
//                    if(m.checked==1){
//                        ToastUtil.showToast(mContext,m.name)
//                        if(mOnSelctItemListner != null){
//                            mOnSelctItemListner!!.onAreaSelect(m)
//                        }
//                        break
//                    }
//                }
            } else if(mDialogType ==3){//擅长位置

                var selectList: ArrayList<GameGoodAtPositionItem> = ArrayList<GameGoodAtPositionItem>()

                for (i in mDataGoodAtPositionList!!.indices) {
                    if (mDataGoodAtPositionList!!.get(i).checked==1) {
                        selectList!!.add(mDataGoodAtPositionList!!.get(i))
                    }
                }
                mOnSelctItemListner!!.onGoodAtPositionSelect(selectList)

//                for(m in mDataGoodAtPositionList!!){
//                    if(m.isSelect){
//                        ToastUtil.showToast(mContext,m.name)
//                        if(mOnSelctItemListner != null){
//                            mOnSelctItemListner!!.onGoodAtPositionSelect(m)
//                        }
//                        break
//                    }
//                }
            }
        }

    }

    private fun initRecyclerView() {
        val layoutManager = VirtualLayoutManager(mContext!!)

        rcv_price.layoutManager = layoutManager

        //设置服用池设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）：
        val viewPool = RecyclerView.RecycledViewPool()

        viewPool.setMaxRecycledViews(0, 10)

        rcv_price.setRecycledViewPool(viewPool)

        var deleAdatper = DelegateAdapter(layoutManager, false)

        rcv_price.setAdapter(deleAdatper)

        val gridLayoutHelper = GridLayoutHelper(mSpanCount)

        gridLayoutHelper.setGap(QMUIDisplayHelper.dpToPx(8))

        gridLayoutHelper.setAutoExpand(false)


        if(mDialogType == 1){

            mGamePriceAdapter = GamePriceAdapter(mContext, R.layout.item_game_price, mDataPriceList, gridLayoutHelper,mGameType)

            deleAdatper.addAdapter(mGamePriceAdapter)

        } else if(mDialogType == 2){

            mGameAreaAdapter = GameAreaAdapter(mContext, R.layout.item_game_area, mDataAreaList, gridLayoutHelper,mGameType)

            deleAdatper.addAdapter(mGameAreaAdapter)

        } else if(mDialogType == 3){

            mGameGoodAtPositionAdapter = GameGoodAtPositionAdapter(mContext, R.layout.item_game_area, mDataGoodAtPositionList, gridLayoutHelper,mGameType)

            deleAdatper.addAdapter(mGameGoodAtPositionAdapter)

        }


    }

    private fun setWidows() {

        val display = window!!.windowManager.defaultDisplay

        val lp = window!!.attributes

        lp.width = display.width

        lp.alpha = 1.0f

        lp.gravity = Gravity.BOTTOM

        window!!.attributes = lp

        this.setCanceledOnTouchOutside(true)

    }

    interface onSelectItemListener{
        fun onPriceSelect(price:GamePriceItem)
        fun onAreaSelect(list: ArrayList<GameAreaItem>)
        fun onGoodAtPositionSelect(list: ArrayList<GameGoodAtPositionItem>)
    }

}