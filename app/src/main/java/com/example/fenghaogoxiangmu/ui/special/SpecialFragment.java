package com.example.fenghaogoxiangmu.ui.special;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fenghaogoxiangmu.R;
import com.example.fenghaogoxiangmu.adapter.special.SpecialAdapter;
import com.example.fenghaogoxiangmu.base.BaseFragment;
import com.example.fenghaogoxiangmu.bean.special.SpecialBean;
import com.example.fenghaogoxiangmu.interfaces.special.ISpecial;
import com.example.fenghaogoxiangmu.persenter.special.SpecialPersenter;

import java.util.ArrayList;

import butterknife.BindView;


public class SpecialFragment extends BaseFragment<ISpecial.Persenter> implements ISpecial.SpecialView {


    @BindView(R.id.rcy)
    RecyclerView rcy;
    private ArrayList<SpecialBean.DataBeanX.DataBean> list;
    private SpecialAdapter specialAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_special;
    }

    @Override
    protected void initView() {
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        specialAdapter = new SpecialAdapter(list, context);
        rcy.setAdapter(specialAdapter);
    }

    @Override
    protected ISpecial.Persenter initPersenter() {
        return new SpecialPersenter();
    }

    @Override
    protected void initData() {
        persenter.getSpecia();
    }

    @Override
    public void getSpecialReturn(SpecialBean result) {
        list.addAll(result.getData().getData());
        specialAdapter.notifyDataSetChanged();
    }
}