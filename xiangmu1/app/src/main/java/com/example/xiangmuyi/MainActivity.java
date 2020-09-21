package com.example.xiangmuyi;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.xiangmuyi.adapter.homeadapter.FragmentsVpAdapter;
import com.example.xiangmuyi.ui.discover.DiscoverFragment;
import com.example.xiangmuyi.ui.home.HomeFragment;
import com.example.xiangmuyi.ui.own.OwnFragment;
import com.example.xiangmuyi.ui.own.ownactivity.LoginActivity;
import com.example.xiangmuyi.ui.shop.ShopFragment;
import com.example.xiangmuyi.utils.SpUtil;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private TabLayout tab;
    private static ViewPager vp;
    private ArrayList<Fragment> list;
    private FragmentsVpAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
        list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new DiscoverFragment());
        list.add(new ShopFragment());
        list.add(new OwnFragment());
        adapter = new FragmentsVpAdapter(getSupportFragmentManager(), 0, list);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("首页").setIcon(R.drawable.select_home);
        tab.getTabAt(1).setText("发现").setIcon(R.drawable.select_discover);
        tab.getTabAt(2).setText("商城").setIcon(R.drawable.select_own);
        tab.getTabAt(3).setText("我的").setIcon(R.drawable.select_shop);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 3:
                        boolean isok = (boolean) SpUtil.getParam("isok", false);
                        if (!isok){
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//        Intent intent = getIntent();
//        if (intent.hasExtra("result")){
//            int result = intent.getIntExtra("result", 0);
//            if (result == 1){
//                tab.getTabAt(0).select();
//                HomeFragment homeFragment = (HomeFragment) list.get(0);
//                homeFragment.setVpSelect(1);
//            }

      //  }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 230 && resultCode == 230){
//            vp.setCurrentItem(0);
//            HomeFragment homeFragment = (HomeFragment) list.get(0);
//            homeFragment.setVpSelect(1);
//        }
//    }

    public static void setVp(){
        vp.setCurrentItem(0);
    }
}