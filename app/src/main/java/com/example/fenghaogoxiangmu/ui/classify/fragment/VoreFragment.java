package com.example.fenghaogoxiangmu.ui.classify.fragment;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fenghaogoxiangmu.R;
import com.example.fenghaogoxiangmu.adapter.classify.fragment.VoerAdapter;
import com.example.fenghaogoxiangmu.base.BaseFragment;
import com.example.fenghaogoxiangmu.bean.classify.ClassifyBean;
import com.example.fenghaogoxiangmu.interfaces.classify.IClassify;
import com.example.fenghaogoxiangmu.persenter.classify.ClassifyPersenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class VoreFragment extends BaseFragment<IClassify.Persenter> implements IClassify.View {

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.rcy)
    RecyclerView rcy;
    private List<ClassifyBean.DataBean.CurrentCategoryBean> type;

    private List<ClassifyBean.DataBean.CategoryListBean> categoryList;
    private VoerAdapter voerAdapter;
    private ArrayList<ClassifyBean.DataBean.CurrentCategoryBean.SubCategoryListBean> list;

    public VoreFragment(List<ClassifyBean.DataBean.CurrentCategoryBean> type) {
        this.type = type;

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_vore;
    }

    @Override
    protected void initView() {
        rcy.setLayoutManager(new GridLayoutManager(context,3));
        list = new ArrayList<>();
        voerAdapter = new VoerAdapter(list, context);
        rcy.setAdapter(voerAdapter);

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
        categoryList = result.getData().getCategoryList();
        Glide.with(getActivity()).load(categoryList.get(0).getIcon_url());
        name.setText(categoryList.get(0).getName());
        title.setText(categoryList.get(0).getFront_desc());
        list.addAll(result.getData().getCurrentCategory().getSubCategoryList());
        voerAdapter.notifyDataSetChanged();
    }
}

