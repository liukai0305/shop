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
import com.qutu.talk.base.MyBaseArmActivity
import com.qutu.talk.utils.EditTextMaxLengthFilter
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_create_family_first.*
import kotlinx.android.synthetic.main.activity_game_detail_info.*
import kotlinx.android.synthetic.main.dialog_go_set_paidan.*
import kotlinx.android.synthetic.main.include_title.*
import java.util.ArrayList

open class CreateFamilyFirstActivity : MyBaseArmActivity(),View.OnClickListener {

    val REQUEST_CODE_SELECT = 100

    var mSelectImgPath: String? = ""//选择的图片

    override fun setupActivityComponent(appComponent: AppComponent) {

    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_create_family_first
    }

    override fun initData(savedInstanceState: Bundle?) {

        setTitle("家族资料编辑")

        tv_bar_right.visibility = View.VISIBLE

        tv_bar_right.setText("下一步")

        initEditText()

        img_family_head.setOnClickListener(this)
        img_delete_text.setOnClickListener(this)
        tv_bar_right.setOnClickListener(this)

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
        when(v!!.id){
            R.id.img_delete_text->{
                et_family_name.setText("")
            }
            R.id.img_family_head->{//选择图片
                val rxPermissions = RxPermissions(this)
                rxPermissions
                        .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe { granted ->
                            if (granted!!) { // Always true pre-M
                                // 跳转到相册
                                ImagePicker.getInstance().selectLimit = 1
                                ImagePicker.getInstance().isMultiMode = false
                                ImagePicker.getInstance().isCrop = false
                                val intent = Intent(this@CreateFamilyFirstActivity, ImageGridActivity::class.java)
                                //显示选中的图片
                                startActivityForResult(intent, REQUEST_CODE_SELECT)
                            }
                        }
            }

            R.id.tv_bar_right->{//下一步

                if(TextUtils.isEmpty(mSelectImgPath)){
                    toast("请选择头像")
                    return
                }
                if(TextUtils.isEmpty(et_family_name.text.toString().trim())){
                    toast("请输入家族名称")
                    return
                }

                var intent = Intent(this@CreateFamilyFirstActivity,CreateFamilySecondActivity::class.java)

                intent.putExtra("img_path",mSelectImgPath)
                intent.putExtra("family_name",et_family_name.text.toString().trim())
                intent.putExtra("family_intro",et_family_intro.text.toString().trim())
                intent.putExtra("family_notice",et_family_notice.text.toString().trim())
                startActivity(intent)

            }
        }
    }

}
