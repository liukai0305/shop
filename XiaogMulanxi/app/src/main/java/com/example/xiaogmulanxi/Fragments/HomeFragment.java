package com.example.xiaogmulanxi.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiaogmulanxi.R;
import com.example.xiaogmulanxi.adapter.RcyAdapter;
import com.example.xiaogmulanxi.bean.JavaBean;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


    private RecyclerView mRcy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        initView(inflate);
        initData();
        return inflate;

    }

    private void initView(View inflate) {
        mRcy = inflate.findViewById(R.id.rcy);
    }


    private void initData() {
        List<JavaBean> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(new JavaBean("张三" + i, "18" + i));
        }
        mRcy.setLayoutManager(new LinearLayoutManager(getActivity()));
        RcyAdapter rcyAdapter = new RcyAdapter(list, getActivity());
        mRcy.setAdapter(rcyAdapter);

    }
}