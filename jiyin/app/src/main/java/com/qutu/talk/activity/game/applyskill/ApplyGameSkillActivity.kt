package com.qutu.talk.activity.game.applyskill

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import com.alibaba.fastjson.JSON
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.qutu.talk.R
import com.qutu.talk.adapter.GameItemAdapter
import com.qutu.talk.app.utils.RxUtils
import com.qutu.talk.base.MyBaseArmActivity
import com.qutu.talk.bean.*
import com.qutu.talk.di.CommonModule
import com.qutu.talk.di.DaggerCommonComponent
import com.qutu.talk.service.CommonModel
import com.qutu.talk.utils.Constant
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.tencent.mm.opensdk.constants.ConstantsAPI
import kotlinx.android.synthetic.main.activity_apply_game_skill.*
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*
import javax.inject.Inject

class ApplyGameSkillActivity : MyBaseArmActivity() {

    @Inject
    open lateinit var commonModel: CommonModel

    var mGameList = ArrayList<GameItemBean>()

    var mGameAdapter: GameItemAdapter? = null

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(CommonModule())
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_apply_game_skill
    }

    override fun initData(savedInstanceState: Bundle?) {

        setTitle("选择技能")

        initRecyclerView()

        loadGameList()

    }

    private fun initRecyclerView() {

        val layoutManager = VirtualLayoutManager(this)

        rcv_game.layoutManager = layoutManager

        //设置服用池设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）：
        val viewPool = RecyclerView.RecycledViewPool()

        viewPool.setMaxRecycledViews(0, 10)

        rcv_game.setRecycledViewPool(viewPool)

        var deleAdatper = DelegateAdapter(layoutManager, false)

        rcv_game.setAdapter(deleAdatper)

        val gridLayoutHelper = GridLayoutHelper(3)

        gridLayoutHelper.setGap(QMUIDisplayHelper.dpToPx(10))

        gridLayoutHelper.setAutoExpand(false)

        mGameAdapter = GameItemAdapter(this, R.layout.item_game_list, mGameList, gridLayoutHelper)

        mGameAdapter?.setOnChildClickListener(object: GameItemAdapter.OnChildViewClickListener{
            override fun OnChildClick(gameItemBean: GameItemBean) {
                if (gameItemBean?.status.equals("1")){//已通过
                    var intent = Intent(this@ApplyGameSkillActivity, LOLHomeActivity::class.java)
                    if(gameItemBean.id.equals("1")){
                        intent.putExtra("game_type", GameType.GAME_WANG_ZHE.state)
                    } else if(gameItemBean.id.equals("2")){
                        intent.putExtra("game_type", GameType.GAME_HE_PING.state)
                    } else if(gameItemBean.id.equals("3")){
                        intent.putExtra("game_type", GameType.GAME_LOL.state)
                    } else if(gameItemBean.id.equals("4")){
                        intent.putExtra("game_type", GameType.GAME_JUE_DI.state)
                    }
                    ArmsUtils.startActivity(intent)
                } else if(gameItemBean?.status.equals("2")){//无状态
                    var intent = Intent(this@ApplyGameSkillActivity, GameDetailInfoActivity::class.java)
                    if(gameItemBean.id.equals("1")){
                        intent.putExtra("game_type", GameType.GAME_WANG_ZHE.state)
                    } else if(gameItemBean.id.equals("2")){
                        intent.putExtra("game_type", GameType.GAME_HE_PING.state)
                    } else if(gameItemBean.id.equals("3")){
                        intent.putExtra("game_type", GameType.GAME_LOL.state)
                    } else if(gameItemBean.id.equals("4")){
                        intent.putExtra("game_type", GameType.GAME_JUE_DI.state)
                    }
                    ArmsUtils.startActivity(intent)
                } else if(gameItemBean?.status.equals("0")){//审核中
                    var intent = Intent(this@ApplyGameSkillActivity, GameApplyingActivity::class.java)
                    if(gameItemBean.id.equals("1")){
                        intent.putExtra("game_type", GameType.GAME_WANG_ZHE.state)
                    } else if(gameItemBean.id.equals("2")){
                        intent.putExtra("game_type", GameType.GAME_HE_PING.state)
                    } else if(gameItemBean.id.equals("3")){
                        intent.putExtra("game_type", GameType.GAME_LOL.state)
                    } else if(gameItemBean.id.equals("4")){
                        intent.putExtra("game_type", GameType.GAME_JUE_DI.state)
                    }
                    ArmsUtils.startActivity(intent)
                }

            }

        })

        deleAdatper.addAdapter(mGameAdapter)
    }

    private fun loadGameList() {

        RxUtils.loading(commonModel.selectSkillsList())
                .subscribe(object : ErrorHandleSubscriber<GetGameListResult>(mErrorHandler) {
                    override fun onNext(giftListBean: GetGameListResult) {

                        if (giftListBean != null && giftListBean.data != null) {

                            mGameList.addAll(giftListBean.data)

                            mGameAdapter?.notifyDataSetChanged()

                        }

                    }
                })
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun receiveMsg(event: FirstEvent) {
        val tag = event.tag
//        val msg = event.msg
        if (Constant.COMMIT_GAME_INFO == tag) {
            mGameList.clear()
            loadGameList()
        }
    }

}
