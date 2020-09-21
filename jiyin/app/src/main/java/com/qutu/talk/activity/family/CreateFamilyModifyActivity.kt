package com.qutu.talk.activity.family

import android.Manifest
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.jess.arms.base.BaseApplication
import com.jess.arms.di.component.AppComponent
import com.lzy.imagepicker.ImagePicker
import com.lzy.imagepicker.bean.ImageItem
import com.lzy.imagepicker.ui.ImageGridActivity
import com.qutu.talk.R
import com.qutu.talk.app.utils.RxUtils
import com.qutu.talk.base.MyBaseArmActivity
import com.qutu.talk.bean.CommentBean
import com.qutu.talk.bean.FirstEvent
import com.qutu.talk.di.CommonModule
import com.qutu.talk.di.DaggerCommonComponent
import com.qutu.talk.popup.FamilyModifyImageDialog
import com.qutu.talk.service.CommonModel
import com.qutu.talk.utils.BToast
import com.qutu.talk.utils.Constant.FAMILYSHENQING
import com.qutu.talk.utils.Constant.FAMILYXIUGAI
import com.qutu.talk.utils.EditTextMaxLengthFilter
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_create_family_first.*
import kotlinx.android.synthetic.main.activity_create_family_first.et_family_intro
import kotlinx.android.synthetic.main.activity_create_family_first.et_family_name
import kotlinx.android.synthetic.main.activity_create_family_first.et_family_notice
import kotlinx.android.synthetic.main.activity_create_family_first.img_delete_text
import kotlinx.android.synthetic.main.activity_create_family_first.img_family_head
import kotlinx.android.synthetic.main.activity_create_family_first.tv_intro_count
import kotlinx.android.synthetic.main.activity_create_family_first.tv_name_count
import kotlinx.android.synthetic.main.activity_create_family_first.tv_notice_count
import kotlinx.android.synthetic.main.activity_game_detail_info.*
import kotlinx.android.synthetic.main.create_family_modify.*
import kotlinx.android.synthetic.main.dialog_go_set_paidan.*
import kotlinx.android.synthetic.main.include_title.*
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.greenrobot.eventbus.EventBus
import java.io.File
import java.util.ArrayList
import java.util.HashMap
import javax.inject.Inject

open class CreateFamilyModifyActivity : MyBaseArmActivity(), View.OnClickListener {

    @Inject
    open lateinit var commonModel: CommonModel

    val REQUEST_CODE_SELECT = 100

    var mSelectImgPath: String? = ""//选择的图片
    var family_id: String? = ""//家族ID
    var user_type: String? = "" // 自己的权限
    var mFamilyName: String? = ""//房间名称
    var mFamilyIntro: String? = ""//房间介绍
    var mFamilyNotice: String? = ""//房间公告

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(CommonModule())
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.create_family_modify
    }

    override fun initData(savedInstanceState: Bundle?) {

        setTitle("家族资料编辑")

        family_id = intent.getStringExtra("family_id")
        user_type = intent.getStringExtra("user_type")

        mFamilyName = intent.getStringExtra("family_name")
        mFamilyIntro = intent.getStringExtra("family_js")
        mFamilyNotice = intent.getStringExtra("family_gg")

        val options = RequestOptions()
                .centerCrop()
                .placeholder(R.color.white)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(this)
                .load(intent.getStringExtra("head_url"))
                .apply(options)
                .into(img_family_head)
        et_family_notice.setText(intent.getStringExtra("family_gg"))

        tv_bar_right.visibility = View.VISIBLE

        tv_bar_right.setText("保存")

        initEditText()

        if ("2".equals(user_type)) {
            tv_name_count_two.visibility = View.GONE
            et_family_intro_two.visibility = View.GONE
            et_family_name.setText(intent.getStringExtra("family_name"))
            et_family_intro.setText(intent.getStringExtra("family_js"))

            img_family_head.setOnClickListener(this)
        } else {
            tv_name_count.visibility = View.GONE
            family_name_con.visibility = View.GONE
            et_family_intro.visibility = View.GONE
            et_family_name.visibility = View.GONE
            tv_name_count_two.setText(intent.getStringExtra("family_name"))
            et_family_intro_two.setText(intent.getStringExtra("family_js"))
        }

        img_delete_text.setOnClickListener(this)
        tv_bar_right.setOnClickListener(this)
        tv_name_count_two.setOnClickListener(this)
        et_family_intro_two.setOnClickListener(this)

    }

    private fun initEditText() {

        et_family_name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence?, i: Int, i1: Int, i2: Int) {

                if (charSequence != null && charSequence.length > 0) {

                    val length = charSequence.length

                    tv_name_count.setText("($length/20)")

                    img_delete_text.visibility = View.VISIBLE

                } else {
                    tv_name_count.setText("(0/20)")

                    img_delete_text.visibility = View.GONE
                }

            }

            override fun afterTextChanged(editable: Editable) {

            }
        })

        et_family_name.setFilters(arrayOf<InputFilter>(EditTextMaxLengthFilter(this, 20, "输入文字超过最大长度了")))


        et_family_intro.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence?, i: Int, i1: Int, i2: Int) {

                if (charSequence != null && charSequence.length > 0) {

                    val length = charSequence.length

                    tv_intro_count.setText("($length/100)")

                } else {
                    tv_intro_count.setText("(0/100)")
                }

            }

            override fun afterTextChanged(editable: Editable) {

            }
        })

        et_family_intro.setFilters(arrayOf<InputFilter>(EditTextMaxLengthFilter(this, 100, "输入文字超过最大长度了")))

        et_family_notice.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence?, i: Int, i1: Int, i2: Int) {

                if (charSequence != null && charSequence.length > 0) {

                    val length = charSequence.length

                    tv_notice_count.setText("($length/100)")

                } else {
                    tv_notice_count.setText("(0/100)")
                }

            }

            override fun afterTextChanged(editable: Editable) {

            }
        })

        et_family_notice.setFilters(arrayOf<InputFilter>(EditTextMaxLengthFilter(this, 100, "输入文字超过最大长度了")))

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
                var tempList = data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS) as ArrayList<ImageItem>
                if (tempList == null) {
                    return
                }

                var imgItem = tempList.get(0)
                if (imgItem != null) {
                    val path = imgItem.path
                    mSelectImgPath = imgItem.path
                    val options = RequestOptions()
                            .centerCrop()
                            .placeholder(R.color.white)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                    Glide.with(this)
                            .load(path)
                            .apply(options)
                            .into(img_family_head)
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.img_delete_text -> {
                et_family_name.setText("")
            }
            R.id.img_family_head -> {//选择图片
                if ("2".equals(user_type)) {
                    var selectGameSegmentDialog: FamilyModifyImageDialog = FamilyModifyImageDialog(this)
                    selectGameSegmentDialog.setOnItemSelectListener(object : FamilyModifyImageDialog.onItemClickListener {

                        override fun onAddMemeber() {

                            selectGameSegmentDialog.dismiss()
                            val rxPermissions = RxPermissions(this@CreateFamilyModifyActivity)
                            rxPermissions
                                    .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                    .subscribe { granted ->
                                        if (granted!!) { // Always true pre-M
                                            // 跳转到相册
                                            ImagePicker.getInstance().selectLimit = 1
                                            ImagePicker.getInstance().isMultiMode = false
                                            ImagePicker.getInstance().isCrop = false
                                            val intent = Intent(this@CreateFamilyModifyActivity, ImageGridActivity::class.java)
                                            //显示选中的图片
                                            startActivityForResult(intent, REQUEST_CODE_SELECT)
                                        }
                                    }

                        }

//                        override fun onDeleteMemeber() {
//
//                            selectGameSegmentDialog.dismiss()
//
//                            var intent = Intent(this@FamilyUserListActivity,CreateFamilySecondActivity::class.java)
//
//                            intent.putExtra("family_id",mFamily_id)
//
//                            intent.putExtra("is_from_create",2)
//
//                            startActivity(intent)
//
//                        }

                    })
                    selectGameSegmentDialog.show()

                } else {
                    BToast.showText(this, "暂无操作权限")
                }

            }
            R.id.tv_name_count_two -> {
                BToast.showText(this, "暂无操作权限")
            }
            R.id.et_family_intro_two -> {
                BToast.showText(this, "暂无操作权限")
            }

            R.id.tv_bar_right -> {//保存
                if ("2".equals(user_type)) {
                    if (TextUtils.isEmpty(et_family_name.text.toString().trim())) {
                        toast("请填写家族名字")
                        return
                    }
                    mFamilyName = et_family_name.text.toString().trim()
                    mFamilyIntro = et_family_intro.text.toString().trim()
                    mFamilyNotice = et_family_notice.text.toString().trim()
                } else {
                    mFamilyNotice = et_family_notice.text.toString().trim()
                }


                val map = HashMap<String, Any?>()

                map["family_id"] = family_id!!

                map["name"] = mFamilyName

                map["introduce"] = mFamilyIntro

                map["notice"] = mFamilyNotice

                var imgS: MultipartBody.Part? = null

                if (!TextUtils.isEmpty(mSelectImgPath)) {

                    val imgFile: File = File(mSelectImgPath)

                    val requestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), imgFile!!)

                    imgS = MultipartBody.Part.createFormData("image", imgFile!!.getName(), requestBody)

                } else {
                    imgS = MultipartBody.Part.createFormData("image", "");
                }

                showDialogLoding()
                RxUtils.loading(commonModel.actionEditFamily(map, imgS), this)
                        .subscribe(object : ErrorHandleSubscriber<CommentBean>(mErrorHandler) {
                            override fun onNext(baseBean: CommentBean) {

                                disDialogLoding()

                                toast(baseBean.message)

                                EventBus.getDefault().post(FirstEvent("fanhui", FAMILYXIUGAI))

                                finish()
                            }

                            override fun onError(t: Throwable) {
                                super.onError(t)
                                disDialogLoding()
                            }
                        })

//                var intent = Intent(this@CreateFamilyModifyActivity, CreateFamilySecondActivity::class.java)
//                intent.putExtra("img_path", mSelectImgPath)
//                intent.putExtra("family_name", et_family_name.text.toString().trim())
//                intent.putExtra("family_intro", et_family_intro.text.toString().trim())
//                intent.putExtra("family_notice", et_family_notice.text.toString().trim())
//                startActivity(intent)

            }
        }
    }

}
