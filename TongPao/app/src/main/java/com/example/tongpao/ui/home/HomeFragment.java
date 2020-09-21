package com.example.tongpao.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.tongpao.R;
import com.example.tongpao.base.BaseFragment;
import com.example.tongpao.interfaces.IBasePresenter;
import com.example.tongpao.ui.home.homes.ArticleFragment;
import com.example.tongpao.ui.home.homes.PhotoFragment;
import com.example.tongpao.ui.home.homes.RecommendFragment;
import com.example.tongpao.ui.home.homes.RewardFragment;
import com.example.tongpao.ui.home.homes.SquareFragment;
import com.example.tongpao.ui.home.homes.VideoFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment {

    private TabLayout home_tb;
    private ViewPager home_vp;
    String[] tabs = {"推荐","广场","视频","摄影","知识文章","悬赏"};
    private List<Fragment> fragments = new ArrayList<>();
    @Override
    protected IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        home_tb = getActivity().findViewById(R.id.home_tb);
        home_vp = getActivity().findViewById(R.id.home_vp);
        home_tb.setupWithViewPager(home_vp);
        home_tb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView textView =(TextView) LayoutInflater.from(getActivity()).inflate(R.layout.tab_item, null);
                textView.setTextSize(25);
                textView.setText(tab.getText());
                tab.setCustomView(textView);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            tab.setCustomView(null);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        for (int i = 0; i < tabs.length; i++) {
            home_tb.addTab(home_tb.newTab().setText(tabs[i]));
        }
        fragments.add(new RecommendFragment());
        fragments.add(new SquareFragment());
        fragments.add(new VideoFragment());
        fragments.add(new PhotoFragment());
        fragments.add(new ArticleFragment());
        fragments.add(new RewardFragment());
        home_vp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tabs[position];
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }
}