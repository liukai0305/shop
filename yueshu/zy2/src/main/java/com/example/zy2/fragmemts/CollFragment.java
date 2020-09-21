package com.example.zy2.fragmemts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zy2.R;
import com.example.zy2.adapter.CollAdapter;
import com.example.zy2.bean.CollBean;
import com.example.zy2.bean.HomeBean;
import com.example.zy2.presenter.FragmentspPresenterImp;
import com.example.zy2.view.IView;

import java.util.ArrayList;


public class CollFragment extends Fragment implements IView {



    RecyclerView tvColl;
    private FragmentspPresenterImp imp;
    private ArrayList<CollBean.DataBean.DatasBean> list;
    private CollAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_coll, container, false);
        initView(inflate);
        initData();
        return inflate;

    }

    private void initData() {
        imp = new FragmentspPresenterImp(this);
        imp.setCollData();
    }

    private void initView(View view) {
        tvColl=view.findViewById(R.id.tv_Coll);
        tvColl.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new CollAdapter(list, getActivity());
        tvColl.setAdapter(adapter);

    }


    @Override
    public void getHomeData(HomeBean homeBean) {

    }

    @Override
    public void getCollData(CollBean collBean) {
        list.addAll(collBean.getData().getDatas());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }
}