package com.example.zy2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.zy2.adapter.FragmentVpAdapter;
import com.example.zy2.fragmemts.CollFragment;
import com.example.zy2.fragmemts.HomeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tab)
    TabLayout tab;
    private ArrayList<Fragment> list;
    private FragmentVpAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new CollFragment());
        adapter = new FragmentVpAdapter(getSupportFragmentManager(), 0, list);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("首页");
        tab.getTabAt(1).setText("首页");
    }
}
