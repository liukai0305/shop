package com.qutu.talk.activity.message

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import com.blankj.utilcode.util.ActivityUtils
import com.jess.arms.di.component.AppComponent
import com.qutu.talk.R
import com.qutu.talk.activity.MainActivity
import com.qutu.talk.activity.family.FamilyUserListActivity
import com.qutu.talk.activity.family.SetFamilyAdminActivity
import com.qutu.talk.app.utils.RxUtils
import com.qutu.talk.base.MyBaseArmActivity
import com.qutu.talk.bean.BaseBean
import com.qutu.talk.bean.FamilySetting
import com.qutu.talk.bean.FamilySettingResult
import com.qutu.talk.bean.FirstEvent
import com.qutu.talk.di.CommonModule
import com.qutu.talk.di.DaggerCommonComponent
import com.qutu.talk.popup.PuTongWindow
import com.qutu.talk.service.CommonModel
import com.qutu.talk.utils.Constant.TUICHUFAMILY
import kotlinx.android.synthetic.main.activity_message_set_group.*
import kotlinx.android.synthetic.main.include_title.*
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

/**
 * 群聊设置
 */
class MessageSetGroupActivity : MyBaseArmActivity(), View.OnClickListener {

    @Inject
    open lateinit var commonModel: CommonModel


    var mFamilyId: String = ""

    var mUserType = 0;

    var mFamilySetting: FamilySetting? = null

    var mFamilyName: String? = ""

    override fun setupActivityComponent(appComponent: AppComponent) {

        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(CommonModule())
                .build()
                .inject(this)

    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_message_set_group
    }

    override fun initData(savedInstanceState: Bundle?) {

        setTitle("设置")

        mFamilyId = intent.getStringExtra("family_id")

        getDataFromServer()

        img_forbidden_words.setOnClickListener(this)

        img_limit_msg.setOnClickListener(this)

        layout_administrator.setOnClickListener(this)

        layout_family_user.setOnClickListener(this)

        tv_quiet_family.setOnClickListener(this)

    }

    //获取数据
    private fun getDataFromServer() {

        RxUtils.loading(commonModel.getFamilyMoreList(mFamilyId))
                .subscribe(object : ErrorHandleSubscriber<FamilySettingResult>(mErrorHandler) {
                    override fun onNext(orderResult: FamilySettingResult) {

                        if (orderResult != null && orderResult.data != null) {

                            var familySetting: FamilySetting = orderResult.data;

                            mFamilySetting = familySetting

                            mFamilyName = familySetting.name

                            mUserType = familySetting.user_type

                            if (mUserType == 0) {//用户身份 0=普通 1=管理 2=族长

                                layout_forbidden_words.visibility = View.GONE

                                layout_administrator.visibility = View.GONE

                                view_head_line.visibility = View.GONE

                                layout_quiet_family.visibility = View.VISIBLE

                                tv_quiet_family.setText("退出家族")

                            } else if (mUserType == 1) {

                                layout_forbidden_words.visibility = View.VISIBLE

                                layout_quiet_family.visibility = View.VISIBLE

                                tv_quiet_family.setText("退出家族")


                            } else {

                                layout_forbidden_words.visibility = View.VISIBLE

                                layout_administrator.visibility = View.VISIBLE

                                layout_quiet_family.visibility = View.VISIBLE

                                tv_quiet_family.setText("解散家族")
                            }

                            if (familySetting.speakswitch == 0) {//成员禁言 0=关闭 1=开启
                                img_forbidden_words.isSelected = false
                            } else {
                                img_forbidden_words.isSelected = true
                            }
                            if (familySetting.closeswitch == 0) {//屏蔽家族消息0=关闭 1=开启
                                img_limit_msg.isSelected = false
                            } else {
                                img_limit_msg.isSelected = true
                            }

                            tv_administrator_count.setText(familySetting.admin_num + "人")

                            tv_family_user_count.setText(familySetting.user_num + "人")


                        } else {
                            finish()

                        }
                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)
                    }

                })

    }

    override fun onClick(v: View?) {
        when (v!!.id) {

            R.id.img_forbidden_words -> {//群成员禁言

                if (mFamilySetting == null) {
                    return
                }

                if (mUserType == 1 || mUserType == 2) {
                    toForbidenWords()
                }

            }
            R.id.img_limit_msg -> {//屏蔽群消息

                if (mFamilySetting == null) {
                    return
                }
                toLimitMsg()

            }
            R.id.layout_administrator -> {//管理员

                if (mFamilySetting == null) {
                    return
                }

                if(mUserType == 2){

                    ActivityUtils.startActivity(SetFamilyAdminActivity::class.java)

                }

            }
            R.id.layout_family_user -> {//群成员

                if (mFamilySetting == null) {
                    return
                }

                var intent = Intent(this@MessageSetGroupActivity, FamilyUserListActivity::class.java)

                intent.putExtra("family_id",mFamilyId)

                intent.putExtra("user_type",mUserType)

                intent.putExtra("family_name",mFamilyName)

                startActivity(intent)

            }
            R.id.tv_quiet_family -> {//退出、解散家族

                if(mUserType == 2){//族长
                    val puTongWindow1 = PuTongWindow(this)
                    puTongWindow1.showAtLocation(rightTitle, Gravity.CENTER, 0, 0)
                    puTongWindow1.titText.text = "确定要解散该家族吗？"
                    puTongWindow1.cancel.setOnClickListener { v -> puTongWindow1.dismiss() }
                    puTongWindow1.sure.setOnClickListener { v ->
                        puTongWindow1.dismiss()
                        toDissulationFamily()
                    }
                } else {
                    val puTongWindow1 = PuTongWindow(this)
                    puTongWindow1.showAtLocation(rightTitle, Gravity.CENTER, 0, 0)
                    puTongWindow1.titText.text = "确定要退出该家族吗？"
                    puTongWindow1.cancel.setOnClickListener { v -> puTongWindow1.dismiss() }
                    puTongWindow1.sure.setOnClickListener { v ->
                        puTongWindow1.dismiss()
                        toQuitFamily()
                    }
                }

            }

        }
    }

    //退出群
    private fun toQuitFamily() {

        RxUtils.loading(commonModel.actionQuitFamily(mFamilyId))
                .subscribe(object : ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    override fun onNext(orderResult: BaseBean) {

                        toast(orderResult.message)
                        if(orderResult.code == 1){
                            ActivityUtils.finishToActivity(MainActivity::class.java,false)
                            EventBus.getDefault().post(FirstEvent(mFamilyId, TUICHUFAMILY))
                        }
                    }
                })
    }

    //解散群
    private fun toDissulationFamily() {

        RxUtils.loading(commonModel.actionDisFamily(mFamilyId))
                .subscribe(object : ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    override fun onNext(orderResult: BaseBean) {

                        toast(orderResult.message)
                        if(orderResult.code == 1){
                            ActivityUtils.finishToActivity(MainActivity::class.java,false)
                            EventBus.getDefault().post(FirstEvent(mFamilyId, TUICHUFAMILY))
                        }

                    }
                })
    }
    //屏蔽群消息
    private fun toLimitMsg() {

        var speakStatus = 0

        if (mFamilySetting!!.closeswitch == 0) {
            speakStatus = 1
        } else {
            speakStatus = 0
        }

        RxUtils.loading(commonModel.actionSetClose(mFamilyId, speakStatus.toString()))
                .subscribe(object : ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    override fun onNext(orderResult: BaseBean) {

                        getDataFromServer()

                    }
                })
    }

    //禁言
    private fun toForbidenWords() {

        var speakStatus = 0

        if (mFamilySetting!!.speakswitch == 0) {//成员禁言 0=关闭 1=开启
            speakStatus = 1
        } else {
            speakStatus = 0
        }

        RxUtils.loading(commonModel.actionSetSpeak(mFamilyId, speakStatus.toString()))
                .subscribe(object : ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    override fun onNext(orderResult: BaseBean) {

                        getDataFromServer()

                    }

                })
    }

}
