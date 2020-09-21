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
import com.example.zy2.adapter.HomeAdapter;
import com.example.zy2.bean.CollBean;
import com.example.zy2.bean.HomeBean;
import com.example.zy2.presenter.FragmentspPresenterImp;
import com.example.zy2.view.IView;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements IView {



    RecyclerView teHome;
    private FragmentspPresenterImp presenterImp;
    private ArrayList<HomeBean.DataBean> list;
    private HomeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        initView(inflate);
        initData();

        return inflate;
    }

    private void initData() {
        presenterImp = new FragmentspPresenterImp(this);
        presenterImp.setHomeData();
    }

    private void initView(View view) {
        teHome=view.findViewById(R.id.te_Home);
        teHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new HomeAdapter(list, getActivity());
        teHome.setAdapter(adapter);
    }


    @Override
    public void getHomeData(HomeBean homeBean) {
        list.addAll(homeBean.getData());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void getCollData(CollBean collBean) {

    }

    @Override
    public void showToast(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }
}