package com.example.xiangmuyi.ui.discover.discoveractivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.adapter.homeadapter.FragmentsVpAdapter;
import com.example.xiangmuyi.ui.discover.discoveractivity.far.LevelFragment;
import com.example.xiangmuyi.ui.discover.discoveractivity.far.RichFragment;
import com.example.xiangmuyi.ui.discover.discoveractivity.far.SignInFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class PaiActivity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager vp;
    private ArrayList<Fragment> fragments;
    private FragmentsVpAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pai);
        initView();
    }

    private void initView() {
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
        fragments = new ArrayList<>();
        fragments.add(new RichFragment());
        fragments.add(new LevelFragment());
        fragments.add(new SignInFragment());
        adapter = new FragmentsVpAdapter(getSupportFragmentManager(), 0, fragments);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("土豪帮");
        tab.getTabAt(1).setText("经验榜");
        tab.getTabAt(2).setText("签到帮");
    }
}