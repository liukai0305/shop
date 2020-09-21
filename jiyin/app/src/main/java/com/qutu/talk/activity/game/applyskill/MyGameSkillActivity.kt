package com.qutu.talk.activity.game.applyskill

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.qutu.talk.R
import com.qutu.talk.adapter.GameItemAdapter
import com.qutu.talk.adapter.MyGameItemAdapter
import com.qutu.talk.app.utils.RxUtils
import com.qutu.talk.base.MyBaseArmActivity
import com.qutu.talk.bean.*
import com.qutu.talk.di.CommonModule
import com.qutu.talk.di.DaggerCommonComponent
import com.qutu.talk.service.CommonModel
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import kotlinx.android.synthetic.main.activity_apply_game_skill.*
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import java.util.ArrayList
import javax.inject.Inject

class MyGameSkillActivity : MyBaseArmActivity() {


    @Inject
    open lateinit var commonModel: CommonModel

    var mGameList = ArrayList<MyGameSkillItemBean>()

    var mGameAdapter: MyGameItemAdapter? = null

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(CommonModule())
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_my_game_skill
    }

    override fun initData(savedInstanceState: Bundle?) {

        setTitle("我的技能")

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

        val gridLayoutHelper = GridLayoutHelper(2)

        gridLayoutHelper.setGap(QMUIDisplayHelper.dpToPx(10))

        gridLayoutHelper.setAutoExpand(false)

        mGameAdapter = MyGameItemAdapter(this, R.layout.item_my_game_skill_list, mGameList, gridLayoutHelper)

        mGameAdapter?.setOnChildClickListener(object: MyGameItemAdapter.OnChildViewClickListener{
            override fun OnStatusClick(gameItemBean: MyGameSkillItemBean) {//开关接单
                if(gameItemBean.is_open.equals("1")){
                    gameItemBean.is_open = "0"
                } else {
                    gameItemBean.is_open = "1"
                }
                mGameAdapter!!.notifyDataSetChanged()
                goSetStatus(gameItemBean)
            }

            override fun OnChildClick(gameItemBean: MyGameSkillItemBean) {
                if(gameItemBean.isAddSkill){//申请添加技能

                    var intent = Intent(this@MyGameSkillActivity, ApplyGameSkillActivity::class.java)
                    ArmsUtils.startActivity(intent)

                } else {
                    var intent = Intent(this@MyGameSkillActivity, LOLHomeActivity::class.java)
                    if(gameItemBean.skill_id.equals("1")){
                        intent.putExtra("game_type", GameType.GAME_WANG_ZHE.state)
                    } else if(gameItemBean.skill_id.equals("2")){
                        intent.putExtra("game_type", GameType.GAME_HE_PING.state)
                    } else if(gameItemBean.skill_id.equals("3")){
                        intent.putExtra("game_type", GameType.GAME_LOL.state)
                    } else if(gameItemBean.skill_id.equals("4")){
                        intent.putExtra("game_type", GameType.GAME_JUE_DI.state)
                    }
                    ArmsUtils.startActivity(intent)
                }
            }

        })

        deleAdatper.addAdapter(mGameAdapter)
    }

    private fun goSetStatus(gameItemBean: MyGameSkillItemBean) {

        RxUtils.loading(commonModel.actionIsJd(gameItemBean.skill_id,gameItemBean.is_open))
                .subscribe(object : ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    override fun onNext(giftListBean: BaseBean) {

                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)
                        toast("设置出错了")
                    }
                })

    }

    private fun loadGameList() {
        mGameList.clear()
        RxUtils.loading(commonModel.getMySkillList())
                .subscribe(object : ErrorHandleSubscriber<MyGameSkillResult>(mErrorHandler) {
                    override fun onNext(giftListBean: MyGameSkillResult) {

                        if (giftListBean != null && giftListBean.data != null) {

                            mGameList.addAll(giftListBean.data)

                            if(giftListBean.data.size != 4){//加号
                                var gameItem4: MyGameSkillItemBean = MyGameSkillItemBean()
                                gameItem4.isAddSkill = true
                                mGameList.add(gameItem4)
                            }

                            mGameAdapter?.notifyDataSetChanged()

                        }

                    }
                })

    }

}
