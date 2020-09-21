package com.qutu.talk.activity.game

import android.Manifest
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.text.Editable
import android.text.InputFilter
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import com.jess.arms.di.component.AppComponent
import com.jess.arms.http.imageloader.glide.ImageConfigImpl
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.utils.LogUtils
import com.lzy.imagepicker.ImagePicker
import com.lzy.imagepicker.bean.ImageItem
import com.lzy.imagepicker.ui.ImageGridActivity
import com.qutu.talk.R
import com.qutu.talk.adapter.AddImageAdapter
import com.qutu.talk.adapter.GridImageAdapter
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
import com.qutu.talk.view.FullyGridLayoutManager
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_appeal.*
import kotlinx.android.synthetic.main.activity_appeal.et_paidan_remark
import kotlinx.android.synthetic.main.activity_appeal.img_order_user_head
import kotlinx.android.synthetic.main.activity_appeal.tv_order_number
import kotlinx.android.synthetic.main.activity_appeal.tv_order_price
import kotlinx.android.synthetic.main.activity_appeal.tv_paidan_count
import kotlinx.android.synthetic.main.activity_order_detail.*
import kotlinx.android.synthetic.main.dialog_go_set_paidan.*
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import org.greenrobot.eventbus.EventBus
import java.util.ArrayList
import javax.inject.Inject

class AppealActivity : MyBaseArmActivity() {

    val REQUEST_CODE_SELECT = 100

    var mIsFromDetail:Boolean = false

    var mOrderDetail:OrderDetail? = null

    var mOrderItem: OrderItem? = null

    private var adapter: AddImageAdapter? = null//图片的适配器

    private val maxSelectNum = 3//最多显示的图片数量

    private var imageSize = 0

    private var tempList: ArrayList<ImageItem>? = null

    private val selImageList = ArrayList<ImageItem>()

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
        return R.layout.activity_appeal
    }

    override fun initData(savedInstanceState: Bundle?) {

        setTitle("申诉")

        var intent: Intent = getIntent()

        if(intent != null && intent.extras != null){

            mIsFromDetail = intent.getBooleanExtra("order_from", false)

            if(mIsFromDetail){
                mOrderDetail = intent.getSerializableExtra("order_detail") as OrderDetail
            } else {
                mOrderItem = intent.getSerializableExtra("order_detail") as OrderItem
            }
            setDataInfo()

        }

        initImgRcv()

        setClickListener()

        initEditText()
    }

    private fun initEditText() {

        et_paidan_remark.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence?, i: Int, i1: Int, i2: Int) {

                if (charSequence != null && charSequence.length > 0) {

                    val length = charSequence.length

                    tv_paidan_count.setText("$length/100")

                } else {
                    tv_paidan_count.setText("0/100")
                }

            }

            override fun afterTextChanged(editable: Editable) {

            }
        })

        et_paidan_remark.setFilters(arrayOf<InputFilter>(EditTextMaxLengthFilter(this, 100, "输入文字超过最大长度了")))

    }

    private fun setClickListener() {
        btn_to_appeal.setOnClickListener {
            if(adapter!!.list.size ==0){
                toast("请至少上传一张图片！")
                return@setOnClickListener
            }
            var reasonStr:String = et_paidan_remark.text.toString().trim()

            if(TextUtils.isEmpty(reasonStr)){
                toast("请填写申诉理由！")
                return@setOnClickListener
            }

            showDialogLoding()

            var imgString1:String? = ""
            var imgString2:String? = ""
            var imgString3:String? = ""
            when(adapter!!.list.size){
                1->{
                    imgString1 = "data:image/png;base64," + BaseUtils.file2Base64(adapter!!.list[0].path)
                }
                2->{
                    imgString1 = "data:image/png;base64," + BaseUtils.file2Base64(adapter!!.list[0].path)
                    imgString2 = "data:image/png;base64," + BaseUtils.file2Base64(adapter!!.list[1].path)
                }
                3->{
                    imgString1 = "data:image/png;base64," + BaseUtils.file2Base64(adapter!!.list[0].path)
                    imgString2 = "data:image/png;base64," + BaseUtils.file2Base64(adapter!!.list[1].path)
                    imgString3 = "data:image/png;base64," + BaseUtils.file2Base64(adapter!!.list[2].path)
                }
            }

            var orderId: String? = ""
            if(mIsFromDetail){
                orderId = mOrderDetail!!.id
            } else {
                orderId = mOrderItem!!.id
            }

            RxUtils.loading(commonModel.go_appeal(orderId,reasonStr,imgString1,imgString2,imgString3))
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

            tv_order_price.text = mOrderDetail!!.total_price+"金币"

            tv_order_time_appeal.text = mOrderDetail!!.addtime

            tv_order_number.text = mOrderDetail!!.order_no

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

            tv_order_price.text = mOrderItem!!.total_price+"金币"

            tv_order_time_appeal.text = mOrderItem!!.addtime

            tv_order_number.text = mOrderItem!!.order_no
        }
    }

    private fun initImgRcv() {

        adapter = AddImageAdapter(this, onAddPicClickListener)

        val manager = FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)

        adapter!!.setSelectMax(maxSelectNum)

        sor_release_rv.setLayoutManager(manager)

        sor_release_rv.setAdapter(adapter)

    }


    /*------图片选择回调------*/
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null) {
            return
        }


        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (requestCode == REQUEST_CODE_SELECT) {

                tempList = data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS) as ArrayList<ImageItem>
                if (tempList == null) {
                    return
                }
                imageSize = tempList!!.size
                selImageList.addAll(tempList!!)
                adapter!!.setList(selImageList)
                adapter!!.notifyDataSetChanged()
            }
        }
    }


    private val onAddPicClickListener = AddImageAdapter.onAddPicClickListener {
        val rxPermissions = RxPermissions(this@AppealActivity)
        rxPermissions
                .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe({ granted ->
                    if (granted!!) { // Always true pre-M
                        // 跳转到相册
                        ImagePicker.getInstance().selectLimit = maxSelectNum - adapter!!.getDataSize()
                        ImagePicker.getInstance().isMultiMode = true
                        ImagePicker.getInstance().isCrop = false
                        val intent = Intent(this@AppealActivity, ImageGridActivity::class.java)
                        //显示选中的图片
                        startActivityForResult(intent, REQUEST_CODE_SELECT)
                    }
                })
    }

}
