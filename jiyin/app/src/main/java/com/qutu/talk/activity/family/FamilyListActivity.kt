package com.qutu.talk.activity.family

import android.os.Bundle
import com.jess.arms.di.component.AppComponent
import com.qutu.talk.R
import com.qutu.talk.base.MyBaseArmActivity
import com.qutu.talk.di.CommonModule
import com.qutu.talk.di.DaggerCommonComponent
import com.qutu.talk.fragment.FamilyListFragment

class FamilyListActivity : MyBaseArmActivity() {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(CommonModule())
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_family_list
    }

    override fun initData(savedInstanceState: Bundle?) {

        var is_family_show = intent.getIntExtra("is_family_show", 0)

        //var isGod = intent.getStringExtra("is_god")

        val familyListFragment=FamilyListFragment()
        val bundle =Bundle()
        bundle.putInt("is_family_show",is_family_show)
        //bundle.putString("is_god",isGod)
        bundle.putBoolean("show_back",true)
        familyListFragment.arguments=bundle
        supportFragmentManager.beginTransaction()
                .add(R.id.fl_parent,familyListFragment,"aa")
                .commit()

    }


}
