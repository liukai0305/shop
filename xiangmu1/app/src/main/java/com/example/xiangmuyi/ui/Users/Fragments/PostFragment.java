package com.example.xiangmuyi.ui.Users.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.adapter.homeadapter.PersonalPostAdapter;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.bean.homebean.PersonalPostBean;
import com.example.xiangmuyi.common.Constants;
import com.example.xiangmuyi.interfaces.ILoadData;
import com.example.xiangmuyi.ui.Users.Activitys.HuoXiangQiangActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PostFragment extends Fragment {
    @BindView(R.id.rcy)
    RecyclerView rcy;
    private List<PersonalPostBean.DataBean.ActivitysBean> list;
    private PersonalPostAdapter personalPostAdapter;
    private int type;
    public ILoadData loadData;
    private PersonalPostBean data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_post, container, false);
        ButterKnife.bind(this, inflate);
        return inflate;
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        personalPostAdapter = new PersonalPostAdapter(getContext(), list);
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.setAdapter(personalPostAdapter);
        personalPostAdapter.setOnItemClickLis(new BaseAdapter.OnItemClickLisf() {
            @Override
        public void click(int position) {
            Constants.activitysBean =list;
                Intent intent = new Intent(getActivity(), HuoXiangQiangActivity.class);
                intent.putExtra("pos",position);
                startActivity(intent);
        }
    });

            Bundle bundle = getArguments();
        if(bundle != null && bundle.containsKey("type")){
            type = bundle.getInt("type");
        }
        if(loadData != null){
            loadData.loadData(type);
        }

    }
    public static PostFragment getFragment(int type, ILoadData loadData){
        PostFragment postFragment = new PostFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type",type);
        postFragment.loadData = loadData;
        postFragment.setArguments(bundle);
        return postFragment;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser && rcy != null){
            if(data == null){
                if(loadData != null){
                    loadData.loadData(type);
                }
            }
        }
    }

    /**
     * 初始化数据
     * @param data
     */
    public void initData(PersonalPostBean data){
        this.data = data;
        list.addAll(data.getData().getActivitys());
        updateView();
    }

    /**
     * 填充数据
     */
    private void updateView(){
        personalPostAdapter.notifyDataSetChanged();
    }
}