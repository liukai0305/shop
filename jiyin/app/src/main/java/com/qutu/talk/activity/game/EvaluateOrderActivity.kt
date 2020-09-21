package com.qutu.talk.activity.game

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextUtils
import android.text.TextWatcher
import com.jess.arms.di.component.AppComponent
import com.jess.arms.http.imageloader.glide.ImageConfigImpl
import com.jess.arms.utils.ArmsUtils
import com.qutu.talk.R
import com.qutu.talk.app.utils.RxUtils
import com.qutu.talk.base.MyBaseArmActivity
import com.qutu.talk.bean.BaseBean
import com.qutu.talk.bean.FirstEvent
import com.qutu.talk.bean.OrderDetail
import com.qutu.talk.bean.OrderItem
import com.qutu.talk.di.CommonModule
import com.qutu.talk.di.DaggerCommonComponent
import com.qutu.talk.service.CommonModel
import com.qutu.talk.utils.BaseUtils
import com.qutu.talk.utils.Constant
import com.qutu.talk.utils.EditTextMaxLengthFilter
import kotlinx.android.synthetic.main.activity_appeal.*
import kotlinx.android.synthetic.main.activity_appeal.et_paidan_remark
import kotlinx.android.synthetic.main.activity_appeal.img_order_user_head
import kotlinx.android.synthetic.main.activity_appeal.tv_order_game_count
import kotlinx.android.synthetic.main.activity_appeal.tv_order_skill
import kotlinx.android.synthetic.main.activity_appeal.tv_order_user_name
import kotlinx.android.synthetic.main.activity_appeal.tv_paidan_count
import kotlinx.android.synthetic.main.activity_evaluate_order.*
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class EvaluateOrderActivity : MyBaseArmActivity() {

    var mIsFromDetail:Boolean = false

    var mOrderDetail: OrderDetail? = null

    var mOrderItem: OrderItem? = null

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
        return R.layout.activity_evaluate_order
    }

    override fun initData(savedInstanceState: Bundle?) {
        setTitle("评价ta")

        initEditText()

        var intent: Intent = getIntent()

        if(intent != null && intent.extras != null){

            mIsFromDetail = intent.getBooleanExtra("order_from", false)

            if(mIsFromDetail){
                mOrderDetail = intent.getSerializableExtra("order_detail") as OrderDetail
            } else {
                mOrderItem = intent.getSerializableExtra("order_detail") as OrderItem
            }
            setDataInfo()

            setClickListener()

        } else {
            finish()
        }
    }

    private fun setDataInfo() {
        if(mIsFromDetail){

            ArmsUtils.obtainAppComponentFromContext(this)
                    .imageLoader()
                    .loadImage(this, ImageConfigImpl
                            .builder()
                            .url(mOrderDetail!!.headimgurl)
                            .placeholder(R.mipmap.no_tou)
                            .imageView(img_order_user_head)
                            .errorPic(R.mipmap.no_tou)
                            .build())

            tv_order_user_name.text = mOrderDetail!!.user_name

            tv_order_skill.text = mOrderDetail!!.skill_name

            tv_order_game_count.text = "x"+mOrderDetail!!.num+mOrderDetail!!.unit

        } else {

            ArmsUtils.obtainAppComponentFromContext(this)
                    .imageLoader()
                    .loadImage(this, ImageConfigImpl
                            .builder()
                            .url(mOrderItem!!.headimgurl)
                            .placeholder(R.mipmap.no_tou)
                            .imageView(img_order_user_head)
                            .errorPic(R.mipmap.no_tou)
                            .build())

            tv_order_user_name.text = mOrderItem!!.user_name

            tv_order_skill.text = mOrderItem!!.skill_name

            tv_order_game_count.text = "x"+mOrderItem!!.num+mOrderItem!!.unit

        }
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

        et_paidan_remark.setFilters(arrayOf<InputFilter>(EditTextMaxLengthFilter(this, 50, "输入文字超过最大长度了")))

    }

    private fun setClickListener() {
        btn_to_evaluate.setOnClickListener {
            if(star_order.starMark.toInt() ==0){
                toast("请选择评分")
                return@setOnClickListener
            }
            var reasonStr:String = et_paidan_remark.text.toString().trim()

            if(TextUtils.isEmpty(reasonStr)){
                toast("请填写评分理由！")
                return@setOnClickListener
            }

            showDialogLoding()

            var orderId: String? = ""
            if(mIsFromDetail){
                orderId = mOrderDetail!!.id
            } else {
                orderId = mOrderItem!!.id
            }

            RxUtils.loading(commonModel.go_add_gm_com(orderId,reasonStr,star_order.starMark.toInt().toString()))
                    .subscribe(object : ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                        override fun onNext(orderResult: BaseBean) {

                            disDialogLoding()

                            toast(orderResult.message)

                            if(orderResult.code == 1){
                                EventBus.getDefault().post(FirstEvent("指定发送", Constant.REFRESH_ORDER))
                                finish()
                            }

                        }

                        override fun onError(t: Throwable) {
                            super.onError(t)
                            disDialogLoding()
                        }
                    })
        }
    }

}
