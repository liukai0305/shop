package com.qutu.talk.activity.game.applyskill

import android.Manifest
import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Rect
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.*
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.InputFilter
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Chronometer
import android.widget.ScrollView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.OnKeyboardListener
import com.jess.arms.di.component.AppComponent
import com.jess.arms.http.imageloader.glide.ImageConfigImpl
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.utils.LogUtils
import com.kongzue.dialog.v3.TipDialog
import com.lzy.imagepicker.ImagePicker
import com.lzy.imagepicker.bean.ImageItem
import com.lzy.imagepicker.ui.ImageGridActivity
import com.qutu.talk.R
import com.qutu.talk.app.utils.RxUtils
import com.qutu.talk.base.MyBaseArmActivity
import com.qutu.talk.bean.*
import com.qutu.talk.di.CommonModule
import com.qutu.talk.di.DaggerCommonComponent
import com.qutu.talk.popup.SelectGameSegmentDialog
import com.qutu.talk.service.CommonModel
import com.qutu.talk.utils.*
import com.qutu.talk.utils.Constant.COMMIT_GAME_INFO
import com.qutu.talk.utils.Constant.FABUCHENGGONG
import com.qutu.talk.view.voiceripple.PaintHelper.*
import com.tbruyelle.rxpermissions2.RxPermissions
import info.kimjihyok.ripplelibrary.Rate
import info.kimjihyok.ripplelibrary.listener.RecordingListener
import info.kimjihyok.ripplelibrary.renderer.CircleRippleRenderer
import info.kimjihyok.ripplelibrary.renderer.Renderer
import info.kimjihyok.ripplelibrary.renderer.TimerCircleRippleRenderer
import kotlinx.android.synthetic.main.activity_game_detail_info.*
import kotlinx.android.synthetic.main.mi_li_item.*
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.greenrobot.eventbus.EventBus
import java.io.File
import java.io.IOException
import java.util.*
import javax.inject.Inject

class GameDetailInfoActivity : MyBaseArmActivity(), View.OnClickListener {

    @Inject
    open lateinit var commonModel: CommonModel

    var mFrom: Int = 1

    var mCurrentRenderer: Renderer? = null

    var mAudioFile: File? = null

    var mSegmentList = ArrayList<SegmentItem>()

    val REQUEST_CODE_SELECT = 100

    var mMaxVoiceDuration = 15500;

    var mMinVoiceDuration = 4;

    var mMediaPlayer: MediaPlayer? = null

    var mSelectSegment: SegmentItem? = null//选择的段位

    var mSelectImgPath: String? = ""//选择的图片

    var mHasVoice: Boolean = false

    var mSkillInfo: SkillDetailInfo? = null

    var mVoiceLength: String? = ""

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(CommonModule())
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_game_detail_info
    }

    override fun initData(savedInstanceState: Bundle?) {


        ImmersionBar.with(this)
                .reset()
                .statusBarColor(R.color.white)
                .autoDarkModeEnable(true)
                .titleBar(layout_can_match)
                .keyboardEnable(true)
                .setOnKeyboardListener(object: OnKeyboardListener {    //软键盘监听回调，keyboardEnable为true才会回调此方法
                     override fun onKeyboardChange(isPopup:Boolean, keyboardHeight:Int) {
                         if(isPopup){
                             Handler().postDelayed(Runnable {
                                 scroll_root.fullScroll(ScrollView.FOCUS_DOWN)
                                 Handler().postDelayed(Runnable {
                                     et_me_intro.requestFocus()
                                 },200)
                             },200)
                         }
                    }
                })
                .init()

        var intent: Intent = intent
        if (intent != null && intent.extras != null) {
            var from: Int = intent.getIntExtra("game_type", 1)

            mFrom = from

            if (from == GameType.GAME_LOL.state) {
                setTitle("英雄联盟")
                tv_segment_intro.text = "要求：男 金币及以上，女 黄金及以上。"
                tv_skill_intro.text = "生涯总览截图清晰显示昵称及段位，严禁盗图"
                img_game_slt_simple.setImageResource(R.mipmap.lol_slt)
            } else if (from == GameType.GAME_WANG_ZHE.state) {
                setTitle("王者荣耀")
                tv_segment_intro.text = "要求：男 金币及以上，女 黄金及以上。"
                tv_skill_intro.text = "需上传个人主页截图，清晰显示ID、当前段位，严禁盗图"
                img_game_slt_simple.setImageResource(R.mipmap.wzry_slt)
            } else if (from == GameType.GAME_HE_PING.state) {
                setTitle("和平精英")
                tv_segment_intro.text = "要求：男铂金及以上，女 黄金及以上。"
                tv_skill_intro.text = "需上传个人主页截图，清晰显示ID、当前段位，严禁盗图"
                img_game_slt_simple.setImageResource(R.mipmap.hpjy_slt)
            } else if (from == GameType.GAME_JUE_DI.state) {
                setTitle("绝地求生")
                tv_segment_intro.text = "要求：男 sp2000以上，女 sp1400以上。"
                tv_skill_intro.text = "需清晰赛季概要截图，严禁盗图"
                img_game_slt_simple.setImageResource(R.mipmap.pubg_slt)
                layout_segment.visibility = View.GONE
            }
        } else {
            finish()
        }

        initVoiceRippleView()

        initEditText()

        layout_segment.setOnClickListener(this)

        img_game_slt.setOnClickListener(this)

        iv_del_img.setOnClickListener(this)

        layout_play_voice.setOnClickListener(this)

        btn_commit.setOnClickListener(this)

        img_delete_voice.setOnClickListener {
            layout_play_voice.visibility = View.GONE
            img_delete_voice.visibility = View.GONE
            voice_view.visibility = View.VISIBLE
            img_center.visibility = View.VISIBLE
            tv_speek_intro.visibility = View.VISIBLE
            tv_speek_intro.text = "按住讲话3-15秒"
            mHasVoice = false

            if (mMediaPlayer != null && mMediaPlayer!!.isPlaying) {
                mMediaPlayer!!.stop()
            }
            img_hold.visibility = View.VISIBLE
            img_play_gif.visibility = View.GONE
        }

        if (mFrom != GameType.GAME_JUE_DI.state) {
            loadSegmentList()
        }

//        addSoftlistener()

        loadSkillDetailInfo()

    }

    private fun addSoftlistener() {
        layout_can_match.getViewTreeObserver().addOnGlobalLayoutListener(object :  ViewTreeObserver.OnGlobalLayoutListener {

            var sc: IntArray? = null
            var scrollHegit:Int = 0
            override fun onGlobalLayout() {

                var r: Rect = Rect()

                layout_can_match.getWindowVisibleDisplayFrame(r); //这样获得的r就是ll_container没有被挡住可见的部分

                if (sc == null) {
                    sc = IntArray(2)
                    layout_bottom_move.getLocationOnScreen(sc); //数组里面两个值sc[0],sc[1]分别是对应控件在xy两轴的距离
                }
                //r.top 是状态栏高度
                var screenHeight:Int = layout_can_match.getRootView().getHeight();
                var softHeight:Int = screenHeight - r.bottom;

                if (softHeight > 140) {//当输入法高度大于140判定为输入法打开了  设置大点，有虚拟键的会超过100
                    scrollHegit = sc!![1] +layout_bottom_move.getHeight() -(screenHeight-softHeight)+10;//可以加个5dp的距离这样不想被挡住的最后一个布局不会挨着输入法
                    if (layout_can_match.getScrollY() != scrollHegit&&scrollHegit>0)
                        scrollToPos(0, scrollHegit);
                } else {//否则判断为输入法隐藏了
                    if (layout_can_match.getScrollY() != 0)
                        scrollToPos(scrollHegit, 0);
                }

            }



        });
    }

    fun scrollToPos(start:Int, end:Int) {
         var animator: ValueAnimator = ValueAnimator.ofInt(start, end);
        animator.setDuration(250);

        animator.addUpdateListener {
            layout_can_match.scrollTo(0, it.getAnimatedValue() as Int);
        }
        animator.start();
    }

    /**
     * 获取详情
     */
    fun loadSkillDetailInfo() {

        RxUtils.loading(commonModel.getSkillInfo(mFrom.toString()))
                .subscribe(object : ErrorHandleSubscriber<GetSkillDetailInfoResult>(mErrorHandler) {
                    override fun onNext(giftListBean: GetSkillDetailInfoResult) {

                        if (giftListBean != null && giftListBean.data != null && giftListBean.data.skill_id != null) {

                            mSkillInfo = giftListBean.data;

                            tv_select_segment.text = mSkillInfo!!.skill_level_name

                            et_me_intro.setText(mSkillInfo!!.introduce)

                            ArmsUtils.obtainAppComponentFromContext(this@GameDetailInfoActivity)
                                    .imageLoader()
                                    .loadImage(this@GameDetailInfoActivity, ImageConfigImpl
                                            .builder()
                                            .url(mSkillInfo!!.image)
                                            .placeholder(R.mipmap.rz_scjt)
                                            .imageView(img_game_slt)
                                            .errorPic(R.mipmap.rz_scjt)
                                            .build())

                            if(!TextUtils.isEmpty(mSkillInfo!!.audio)){

                                layout_play_voice.visibility = View.VISIBLE
                                img_delete_voice.visibility = View.VISIBLE
                                voice_view.visibility = View.GONE
                                img_center.visibility = View.GONE
                                tv_speek_intro.visibility = View.GONE
                                tv_video_duration.setText(mSkillInfo!!.audio_time)
                            }
                            mVoiceLength = mSkillInfo!!.audio_time

//                            Thread(){
//                                var mediaPlayer = MediaPlayer();
//                                try {
//                                    mediaPlayer.setDataSource(mSkillInfo!!.audio);
//                                    mediaPlayer.prepare();
//                                    var duration = mediaPlayer.getDuration();
//                                    if (0 != duration) {
//                                        var total = (duration / 1000);
//                                        runOnUiThread {
//                                            tv_video_duration.setText(total.toString() + "''")
//                                        }
//                                        mediaPlayer.release();
//                                    }
//                                } catch (e: Exception) {
//                                    e.printStackTrace();
//                                }
//                            }.start()

                        }

                    }
                })
    }

    /**
     * 获取段位
     */
    fun loadSegmentList() {
        RxUtils.loading(commonModel.getSkillLevelList(mFrom.toString()))
                .subscribe(object : ErrorHandleSubscriber<SegmentListResult>(mErrorHandler) {
                    override fun onNext(giftListBean: SegmentListResult) {

                        if (giftListBean != null && giftListBean.data != null) {

                            mSegmentList.addAll(giftListBean.data)

                        }

                    }
                })
    }

    fun commitInfo() {

//        var imgBase64 = "data:image/png;base64," + BaseUtils.file2Base64(mSelectImgPath)
//
//        if (TextUtils.isEmpty(imgBase64)) {
//            return
//        }
        val map = HashMap<String, Any>()

        map["skill_id"] = mFrom

        if(!TextUtils.isEmpty(mVoiceLength)){

            map["audio_time"] = mVoiceLength!!

        } else {
            map["audio_time"] = ""
        }

        if (mFrom == GameType.GAME_JUE_DI.state) {
            map["skill_level_id"] = ""
        } else {
            if (mSelectSegment == null) {
                map["skill_level_id"] = mSkillInfo!!.skill_level_id
            } else {
                map["skill_level_id"] = mSelectSegment!!.id
            }
        }

//        map["img"] = imgBase64

        map["introduce"] = et_me_intro.text.toString().trim()


        var audio: MultipartBody.Part? = null

        var imgS: MultipartBody.Part? = null

        if (mHasVoice) {

            val requestBody1 = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), mAudioFile!!)

            audio = MultipartBody.Part.createFormData("audio", mAudioFile!!.getName(), requestBody1)

        } else {
            audio = MultipartBody.Part.createFormData("audio","");
        }

        if (!TextUtils.isEmpty(mSelectImgPath)) {

            val imgFile: File = File(mSelectImgPath)

            val requestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), imgFile!!)

            imgS = MultipartBody.Part.createFormData("img", imgFile!!.getName(), requestBody)

        } else {
            imgS = MultipartBody.Part.createFormData("img","");
        }

        showDialogLoding()

        RxUtils.loading(commonModel.actionApplySkill(map, audio, imgS), this)
                .subscribe(object : ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    override fun onNext(baseBean: BaseBean) {

                        disDialogLoding()

                        if (baseBean.code == 1) {

                            toast(baseBean.message)

                            EventBus.getDefault().post(FirstEvent(baseBean.message, COMMIT_GAME_INFO))

                            finish()

                            var intent = Intent(this@GameDetailInfoActivity, GameApplyingActivity::class.java)
                            intent.putExtra("game_type", mFrom)
                            ArmsUtils.startActivity(intent)
                        } else {
                            TipDialog.show(this@GameDetailInfoActivity, baseBean.message, TipDialog.TYPE.WARNING)
                        }

                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)
                        disDialogLoding()
                    }
                })
    }

    private fun initEditText() {

        et_me_intro.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence?, i: Int, i1: Int, i2: Int) {

                if (charSequence != null && charSequence.length > 0) {

                    val length = charSequence.length

                    tv_count.setText("$length/50")

                } else {
                    tv_count.setText("0/50")
                }

            }

            override fun afterTextChanged(editable: Editable) {

            }
        })

        et_me_intro.setFilters(arrayOf<InputFilter>(EditTextMaxLengthFilter(this, 50, "输入文字超过最大长度了")))

        tv_speek_intro.text = "按住讲话3-15秒"

    }

    private fun initVoiceRippleView() {

        var directory: File? = null

        directory = File(externalCacheDir!!.absolutePath, "AudioCache")

        if (directory.exists()) {
            MyUtil.deleteFilesInDir(directory)
        } else {
            directory.mkdirs()
        }

        mAudioFile = File(directory.absolutePath + "/audio.mp3")

        voice_view.setRecordingListener(object : RecordingListener {
            override fun onRecordingStopped() {
                Log.d(TAG, "onRecordingStopped()")
                voice_view.reset()

                var path: String = mAudioFile!!.getAbsolutePath()
                LogUtils.debugInfo("输出路径==" + path)
                var duration: String = MyUtil.getRingDuring(path)

                var time: Int = Arith.strToInt(duration) / 1000

                if (time > 15) {
                    time = 15
                }

                mVoiceLength = time.toString()

                tv_video_duration.text = time.toString() + "''"

                mHasVoice = true

                if (time < mMinVoiceDuration) {
                    toast("录音内容小于3秒，请重新录制")
                    mHasVoice = false
                    return
                }

                layout_play_voice.visibility = View.VISIBLE
                img_delete_voice.visibility = View.VISIBLE
                voice_view.visibility = View.GONE
                img_center.visibility = View.GONE
                tv_speek_intro.visibility = View.GONE


            }

            override fun onRecordingStarted() {
                Log.d(TAG, "onRecordingStarted()")
            }
        })
        voice_view.setMediaRecorder(MediaRecorder())
        voice_view.setOutputFile(mAudioFile!!.getAbsolutePath())
        voice_view.setAudioSource(MediaRecorder.AudioSource.MIC)
//        voice_view.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        voice_view.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        voice_view.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
//        voice_view.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        // set inner icon
        voice_view.setRecordDrawable(ContextCompat.getDrawable(this, R.drawable.color_cee4ff), ContextCompat.getDrawable(this, R.drawable.color_cee4ff))
        voice_view.setIconSize(18)
        voice_view.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(view: View?, event: MotionEvent?): Boolean {
                when (event!!.action) {
                    MotionEvent.ACTION_DOWN -> {
                        LogUtils.debugInfo("down========")
                        recordVoice()
                    }
                    MotionEvent.ACTION_CANCEL -> {
                        LogUtils.debugInfo("cancel========")
                        try {
                            if (!mIsFinish!!) {
                                isShort = true
                                mHandlerTimer.sendEmptyMessage(0)
                                toast("录制时间过短，请长按录制")
                                return true
                            }
                            if (mIsAutoComplete!!) {
                                return true
                            }
                            if (voice_view.isRecording()) {
                                voice_view.stopRecording()
                                tv_speek_intro.stop()
                                voice_view.setMediaRecorder(MediaRecorder())
                                voice_view.setOutputFile(mAudioFile!!.getAbsolutePath())
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                    }
                    MotionEvent.ACTION_UP -> {
                        LogUtils.debugInfo("up========")
                        try {
                            if (!mIsFinish!!) {
                                isShort = true
                                mHandlerTimer.sendEmptyMessage(0)
                                toast("录制时间过短，请长按录制")
                                return true
                            }
                            if (mIsAutoComplete!!) {
                                return true
                            }
                            if (voice_view.isRecording()) {
                                tv_speek_intro.stop()
                                voice_view.stopRecording()
                            }
                        } catch (e: Exception) {
                            tv_speek_intro.stop()
                            voice_view.setMediaRecorder(MediaRecorder())
                            voice_view.setOutputFile(mAudioFile!!.getAbsolutePath())
                            e.printStackTrace()
                        }
                    }
                }
                return true
            }

        })

        mCurrentRenderer = CircleRippleRenderer(getDefaultRipplePaint(this, R.color.color_cee4ff), getDefaultRippleBackgroundPaint(this, R.color.color_cee4ff), getButtonPaint(this, R.color.color_cee4ff))
        if (mCurrentRenderer is TimerCircleRippleRenderer) {
            (mCurrentRenderer as TimerCircleRippleRenderer).setStrokeWidth(20)
        }

        voice_view.setRenderer(mCurrentRenderer)

        voice_view.setRippleColor(ContextCompat.getColor(this, R.color.color_cee4ff));
        voice_view.setRippleSampleRate(Rate.LOW);
        voice_view.setRippleDecayRate(Rate.MEDIUM);

        voice_view.setBackgroundRippleRatio(1.1);



    }

    fun recordVoice() {
        val rxPermissions = RxPermissions(this@GameDetailInfoActivity)
        rxPermissions
                .request(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE)
                .subscribe({ granted ->
                    if (granted!!) { // Always true pre-M
                        tooShortClick()
                    } else {
                        showToast("请打开录音和存储权限！")
                    }
                })
    }

    private var isShort: Boolean? = false
    var mCountDownTimer: CountDownTimer? = null
    var mIsFinish: Boolean? = false
    var mIsAutoComplete: Boolean? = false
    var mHandlerTimer: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            mCountDownTimer!!.cancel()
            LogUtils.debugInfo("取消了")
        }
    }

    fun tooShortClick() {
        mIsFinish = false
        isShort = false
        mIsAutoComplete = false
        mCountDownTimer = object : CountDownTimer(200, 100) {

            override fun onTick(l: Long) {

            }

            override fun onFinish() {
                LogUtils.debugInfo("间隔完了====")
                mIsFinish = true
                if (!isShort!!) {

                    try{
                        if (voice_view.isRecording()) {
                            voice_view.stopRecording()
                            tv_speek_intro.stop()
                        } else {
                            voice_view.setMediaRecorder(MediaRecorder())
                            voice_view.setOutputFile(mAudioFile!!.getAbsolutePath())
                            voice_view.startRecording()
                            if(!voice_view.isRecording){
                                LogUtils.debugInfo("开始录制失败====")
                                tv_speek_intro.stop()
                                tv_speek_intro.text = "按住讲话3-15秒"
                                return
                            }
                            tv_speek_intro.setBase(SystemClock.elapsedRealtime())
                            tv_speek_intro.start()
                            tv_speek_intro.setOnChronometerTickListener(Chronometer.OnChronometerTickListener { chronometer ->
                                val ss = SystemClock.elapsedRealtime() - chronometer.base
                                LogUtils.debugInfo("last====$ss")
                                tv_speek_intro.setText((ss / 1000).toString() + "秒")
                                if (ss > mMaxVoiceDuration) {//大于15秒自动结束
                                    if (voice_view.isRecording()) {
                                        mIsAutoComplete = true
                                        voice_view.stopRecording()
                                        tv_speek_intro.stop()
                                    }
                                }
//                            soundBtn.setProcessValue(ss.toInt())
                            })
                        }
                    }catch(e:Exception){
                        tv_speek_intro.stop()
                        tv_speek_intro.text = "按住讲话3-15秒"
                        voice_view.reset()
                        e.printStackTrace()
                    }

                }

            }
        }
        mCountDownTimer!!.start()
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.layout_segment -> {//选择段位

                if (mSegmentList == null || mSegmentList.size == 0) {
                    loadSegmentList()
                    return
                }
                var selectGameSegmentDialog: SelectGameSegmentDialog = SelectGameSegmentDialog(this, mSegmentList)

                selectGameSegmentDialog.setOnItemSelectListener(object : SelectGameSegmentDialog.onItemClickListener {
                    override fun onSegmentItemClick(price: SegmentItem) {
                        tv_select_segment.text = price.name
                        mSelectSegment = price
                    }

                })
                selectGameSegmentDialog.show()
            }
            R.id.img_game_slt -> {//选择图片
                val rxPermissions = RxPermissions(this)
                rxPermissions
                        .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe { granted ->
                            if (granted!!) { // Always true pre-M
                                // 跳转到相册
                                ImagePicker.getInstance().selectLimit = 1
                                ImagePicker.getInstance().isMultiMode = false
                                ImagePicker.getInstance().isCrop = false
                                val intent = Intent(this@GameDetailInfoActivity, ImageGridActivity::class.java)
                                //显示选中的图片
                                startActivityForResult(intent, REQUEST_CODE_SELECT)
                            }
                        }
            }
            R.id.iv_del_img -> {//删除图片
                iv_del_img.visibility = View.GONE
                img_game_slt.setImageResource(R.mipmap.rz_scjt)
                img_game_slt.setOnClickListener(this)
                mSelectImgPath = ""

            }
            R.id.layout_play_voice -> {//播放

                var path: String? = ""

                if (mSkillInfo != null && !TextUtils.isEmpty(mSkillInfo!!.skill_id) && !mHasVoice) {//以前有内容,并且没有修改过

                    if (mMediaPlayer != null && mMediaPlayer!!.isPlaying) {
                        return
                    }

                    path = mSkillInfo!!.audio

                } else {

                    if (mAudioFile == null) {
                        return
                    }

                    if (mMediaPlayer != null && mMediaPlayer!!.isPlaying) {
                        return
                    }

                    path = mAudioFile!!.getAbsolutePath()

                    var duration: String = MyUtil.getRingDuring(path)

                    var time: Int = Arith.strToInt(duration) / 1000

                    if (time < mMinVoiceDuration) {
                        toast("录音内容小于3秒，请重新录制")
                        return
                    }

                }

                LogUtils.debugInfo("输出路径==" + path)

                mMediaPlayer = MediaPlayer()
                try {

                    showDialogLoding()
                    if (mSkillInfo != null && !TextUtils.isEmpty(mSkillInfo!!.skill_id)) {//以前有内容
                        mMediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
                        mMediaPlayer!!.setDataSource(path)
                        mMediaPlayer!!.setOnCompletionListener {
                            LogUtils.debugInfo("播完了==" + path)
                            img_hold.visibility = View.VISIBLE
                            img_play_gif.visibility = View.GONE
                        }
                        mMediaPlayer!!.prepareAsync()
                        mMediaPlayer!!.setOnPreparedListener {
                            disDialogLoding()
                            LogUtils.debugInfo("准备好了==" + path)
                            mMediaPlayer!!.start()
                        }
                    } else {

                        mMediaPlayer!!.setDataSource(path)
                        mMediaPlayer!!.setOnCompletionListener {
                            LogUtils.debugInfo("播完了==" + path)
                            img_hold.visibility = View.VISIBLE
                            img_play_gif.visibility = View.GONE
                        }
                        mMediaPlayer!!.prepare()
                        mMediaPlayer!!.setOnPreparedListener {
                            disDialogLoding()
                            LogUtils.debugInfo("准备好了==" + path)
                            mMediaPlayer!!.start()
                        }
                    }
                    img_hold.visibility = View.GONE
                    img_play_gif.visibility = View.VISIBLE
                } catch (e: IOException) {
                    Log.e(TAG, "prepare() failed")
                }
            }
            R.id.btn_commit -> {//提交资料
                if (mFrom == GameType.GAME_LOL.state || mFrom == GameType.GAME_WANG_ZHE.state || mFrom == GameType.GAME_HE_PING.state) {
                    if(mSkillInfo == null || TextUtils.isEmpty(mSkillInfo!!.skill_id)){
                        if (mSelectSegment == null) {
                            toast("请选择段位")
                            return
                        }
                    }
                }
                if(mSkillInfo == null || TextUtils.isEmpty(mSkillInfo!!.skill_id)){

                    if (TextUtils.isEmpty(mSelectImgPath)) {
                        toast("请上传技能截图")
                        return
                    }

                    if (!mHasVoice) {
                        toast("请录制语音")
                        return
                    }

                    if (TextUtils.isEmpty(et_me_intro.text.toString().trim())) {
                        toast("请填写自我介绍")
                        return
                    }

                }

                commitInfo()

            }
        }
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
                iv_del_img.visibility = View.VISIBLE

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
                            .into(img_game_slt)
                    img_game_slt.setOnClickListener(null)
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        if (voice_view != null) {
            try {
                voice_view.onStop()
                if (mMediaPlayer != null) {
                    mMediaPlayer!!.release()
                    mMediaPlayer = null
                    img_hold.visibility = View.VISIBLE
                    img_play_gif.visibility = View.GONE
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if (voice_view != null) {
            try {
                voice_view.onDestroy()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}
