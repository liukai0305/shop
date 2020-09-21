package com.example.xiangmuyi.ui.home;

import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.adapter.homeadapter.FragmentsVpAdapter;
import com.example.xiangmuyi.base.BaseFragment;
import com.example.xiangmuyi.interfaces.home.IHome;
import com.example.xiangmuyi.ui.home.fragments.ArticleFragment;
import com.example.xiangmuyi.ui.home.fragments.FollowFragment;
import com.example.xiangmuyi.ui.home.fragments.PhotoFragment;
import com.example.xiangmuyi.ui.home.fragments.RecommendFragment;
import com.example.xiangmuyi.ui.home.fragments.RewardFragment;
import com.example.xiangmuyi.ui.home.fragments.SquareFragment;
import com.example.xiangmuyi.ui.home.fragments.VideoFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;


public class HomeFragment extends BaseFragment<IHome.Persenter> implements IHome.View {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    private ArrayList<Fragment> list;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        list.add(new FollowFragment());
        list.add(new RecommendFragment());
        list.add(new SquareFragment());
        list.add(new VideoFragment());
        list.add(new PhotoFragment());
        list.add(new ArticleFragment());
        list.add(new RewardFragment());
        //tablayout横向滚动
        tab.setTabMode(tab.MODE_SCROLLABLE);
        vp.setAdapter(new FragmentsVpAdapter(getChildFragmentManager(), 0, list));
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("关注");
        tab.getTabAt(1).setText("推荐");
        tab.getTabAt(2).setText("广场");
        tab.getTabAt(3).setText("视频");
        tab.getTabAt(4).setText("摄影");
        tab.getTabAt(5).setText("知识文章");
        tab.getTabAt(6).setText("悬赏");
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView textView =(TextView) LayoutInflater.from(getActivity()).inflate(R.layout.tabtext_item, null);
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
    }

    @Override
    protected IHome.Persenter initPersenter() {
        return null;
    }

    public void setVpSelect(int posi){
        vp.setCurrentItem(posi);
    }

    @Override
    protected void initData() {

    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
//        return inflate;
//    }

}