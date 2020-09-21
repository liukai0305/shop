package com.example.fenghaogoxiangmu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.fenghaogoxiangmu.base.BaseActivity;
import com.example.fenghaogoxiangmu.interfaces.IBasePersenter;
import com.example.fenghaogoxiangmu.ui.classify.ClassifyFragment;
import com.example.fenghaogoxiangmu.ui.home.HomeFragment;
import com.example.fenghaogoxiangmu.ui.own.OwnFragment;
import com.example.fenghaogoxiangmu.ui.shopping.ShoppingFragment;
import com.example.fenghaogoxiangmu.ui.special.SpecialFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends BaseActivity {

    private RelativeLayout rly;
    private ClassifyFragment classifyFragment;
    private HomeFragment homeFragment;
    private SpecialFragment specialFragment;
    private OwnFragment ownFragment;
    private TabLayout tab;
    private FragmentManager supportFragmentManager;
    private ShoppingFragment shoppingFragment;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    protected void initView() {
        supportFragmentManager = getSupportFragmentManager();
        rly = (RelativeLayout) findViewById(R.id.rly);
        tab = (TabLayout) findViewById(R.id.tab);
        homeFragment = new HomeFragment();
        specialFragment = new SpecialFragment();
        classifyFragment = new ClassifyFragment();
        shoppingFragment = new ShoppingFragment();
        ownFragment = new OwnFragment();
        initHome();
        tab.addTab(tab.newTab().setText("首页").setIcon(R.drawable.select_home), 0);
        tab.addTab(tab.newTab().setText("专题").setIcon(R.drawable.select_special), 1);
        tab.addTab(tab.newTab().setText("分类").setIcon(R.drawable.select_classify), 2);
        tab.addTab(tab.newTab().setText("购物车").setIcon(R.drawable.select_shopping), 3);
        tab.addTab(tab.newTab().setText("我的").setIcon(R.drawable.select_own), 4);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                initFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initFragment(int position) {
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        switch (position) {
            case 0:
                if (!homeFragment.isAdded()) {
                    fragmentTransaction.add(R.id.rly, homeFragment).hide(classifyFragment).hide(specialFragment).hide(ownFragment).hide(shoppingFragment);
                }
                fragmentTransaction.show(homeFragment).hide(classifyFragment).hide(specialFragment).hide(ownFragment).hide(shoppingFragment);
                fragmentTransaction.commit();
                break;
            case 1:
                if (!specialFragment.isAdded()) {
                    fragmentTransaction.add(R.id.rly, specialFragment).hide(classifyFragment).hide(homeFragment).hide(ownFragment).hide(shoppingFragment);
                }
                fragmentTransaction.show(specialFragment).hide(classifyFragment).hide(homeFragment).hide(ownFragment).hide(shoppingFragment);
                fragmentTransaction.commit();
                break;

            case 2:
                if (!classifyFragment.isAdded()) {
                    fragmentTransaction.add(R.id.rly, classifyFragment).hide(homeFragment).hide(specialFragment).hide(ownFragment).hide(shoppingFragment);
                }
                fragmentTransaction.show(classifyFragment).hide(homeFragment).hide(specialFragment).hide(ownFragment).hide(shoppingFragment);
                fragmentTransaction.commit();
                break;
            case 3:
                if (!shoppingFragment.isAdded()) {
                    fragmentTransaction.add(R.id.rly, shoppingFragment).hide(classifyFragment).hide(specialFragment).hide(homeFragment).hide(ownFragment);
                }
                fragmentTransaction.show(shoppingFragment).hide(classifyFragment).hide(specialFragment).hide(homeFragment).hide(ownFragment);
                fragmentTransaction.commit();
                break;

            case 4:
                if (!ownFragment.isAdded()) {
                    fragmentTransaction.add(R.id.rly, ownFragment).hide(classifyFragment).hide(specialFragment).hide(homeFragment).hide(shoppingFragment);
                }
                fragmentTransaction.show(ownFragment).hide(classifyFragment).hide(specialFragment).hide(homeFragment).hide(shoppingFragment);
                fragmentTransaction.commit();
                break;
        }
    }

    private void initHome() {
        supportFragmentManager.beginTransaction()
                .add(R.id.rly, homeFragment)
                .show(homeFragment)
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1000) {
            tab.getTabAt(3).select();


        }

    }

    @Override
    protected IBasePersenter initPersenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
}