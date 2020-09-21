package com.qutu.talk.activity.family

import android.os.Bundle
import com.jess.arms.di.component.AppComponent
import com.qutu.talk.R
import com.qutu.talk.base.MyBaseArmActivity

/**
 * 家族规则介绍
 */
class FamilyIntroActivity : MyBaseArmActivity() {
    override fun setupActivityComponent(appComponent: AppComponent) {
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_family_intro
    }

    override fun initData(savedInstanceState: Bundle?) {
        setTitle("家族介绍")
    }

}
