package com.qutu.talk.activity.game.applyskill

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jess.arms.di.component.AppComponent
import com.qutu.talk.R
import com.qutu.talk.base.MyBaseArmActivity
import com.qutu.talk.bean.GameType
import com.qutu.talk.di.CommonModule
import com.qutu.talk.di.DaggerCommonComponent
import com.qutu.talk.service.CommonModel
import kotlinx.android.synthetic.main.activity_game_detail_info.*
import javax.inject.Inject

class GameApplyingActivity : MyBaseArmActivity() {

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
        return R.layout.activity_game_applying
    }

    override fun initData(savedInstanceState: Bundle?) {

        var intent: Intent = intent
        if (intent != null && intent.extras != null) {
            var from: Int = intent.getIntExtra("game_type", 1)

            if (from == GameType.GAME_LOL.state) {
                setTitle("英雄联盟")
            } else if (from == GameType.GAME_WANG_ZHE.state) {
                setTitle("王者荣耀")
            } else if (from == GameType.GAME_HE_PING.state) {
                setTitle("和平精英")
            } else if (from == GameType.GAME_JUE_DI.state) {
                setTitle("绝地求生")
            }
        } else {
            finish()
        }

    }

}
