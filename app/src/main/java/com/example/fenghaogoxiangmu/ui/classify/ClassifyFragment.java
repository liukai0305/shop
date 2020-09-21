package com.example.fenghaogoxiangmu.ui.classify;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.fenghaogoxiangmu.R;
import com.example.fenghaogoxiangmu.base.BaseFragment;
import com.example.fenghaogoxiangmu.bean.classify.ClassifyBean;
import com.example.fenghaogoxiangmu.interfaces.classify.IClassify;
import com.example.fenghaogoxiangmu.persenter.classify.ClassifyPersenter;
import com.example.fenghaogoxiangmu.ui.classify.fragment.VoreFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;


public class ClassifyFragment extends BaseFragment<IClassify.Persenter> implements IClassify.View {


    @BindView(R.id.classify_tab)
    VerticalTabLayout classifyTab;
    @BindView(R.id.classify_vp)
    ViewPager classifyVp;

    @Override
    protected int getLayout() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected IClassify.Persenter initPersenter() {
        return new ClassifyPersenter();
    }

    @Override
    protected void initData() {
        persenter.getClassify();
    }

    @Override
    public void getClassifyReturn(ClassifyBean result) {
        ArrayList<Fragment> list = new ArrayList<>();
        List<ClassifyBean.DataBean.CurrentCategoryBean> currentCategoryBeans = new ArrayList<>();
        for (int i = 0; i < result.getData().getCategoryList().size(); i++) {
            currentCategoryBeans.add(result.getData().getCurrentCategory());
            list.add(new VoreFragment(currentCategoryBeans));
        }
        classifyTab.setTabHeight(90);
        classifyVp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return result.getData().getCategoryList().get(position).getName();

            }
        });
        classifyTab.setupWithViewPager(classifyVp);
    }

}