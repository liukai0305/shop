package com.qutu.talk.activity.family

import android.os.Bundle
import com.jess.arms.di.component.AppComponent
import com.qutu.talk.R
import com.qutu.talk.base.MyBaseArmActivity
import com.qutu.talk.di.CommonModule
import com.qutu.talk.di.DaggerCommonComponent
import com.qutu.talk.fragment.FamilyDetailsFragment

/**
 * 家族详情
 * 老王
 */

class FamilyDetailsActivity : MyBaseArmActivity() {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(CommonModule())
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_family_details
    }

    override fun initData(savedInstanceState: Bundle?) {
        val family_id = intent.getStringExtra("family_id")
        val familyDetailsFragment = FamilyDetailsFragment()
        val bundle = Bundle()
        bundle.putString("family_id", family_id)
        bundle.putBoolean("show_back", true)
        familyDetailsFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
                .add(R.id.fl_parent, familyDetailsFragment, "aa")
                .commit()

    }
}
