package com.example.xiangmuyi.ui.home.fragments;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.adapter.homeadapter.PhotoAdapter;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.base.BaseFragment;
import com.example.xiangmuyi.bean.homebean.HomePhtotBean;
import com.example.xiangmuyi.common.VideoItemDecoration;
import com.example.xiangmuyi.interfaces.home.IHome;
import com.example.xiangmuyi.persenter.home.PhtotPersenter;
import com.example.xiangmuyi.ui.home.activity.PhotoActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class PhotoFragment extends BaseFragment<IHome.PhtotPersenter> implements IHome.PhtotView {
    @BindView(R.id.rcy)
    RecyclerView rcy;
    private List<HomePhtotBean.DataBean.DynamicsBean> list;
    private PhotoAdapter adapter;


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_photo, container, false);
//    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_photo;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rcy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                staggeredGridLayoutManager.invalidateSpanAssignments();
            }
        });
        rcy.setLayoutManager(staggeredGridLayoutManager);
        VideoItemDecoration videoItemDecoration = new VideoItemDecoration();
        videoItemDecoration.setSpace(20);
        rcy.addItemDecoration(videoItemDecoration);
        adapter = new PhotoAdapter(list, getActivity());
        rcy.setAdapter(adapter);
        adapter.setOnItemClickLis(new BaseAdapter.OnItemClickLisf() {
            @Override
            public void click(int position) {
                Intent intent = new Intent(getActivity(), PhotoActivity.class);
                intent.putExtra("photo", (Serializable) list);
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });
    }

    @Override
    protected IHome.PhtotPersenter initPersenter() {
        return new PhtotPersenter();
    }

    @Override
    protected void initData() {
        persenter.getPhtot();
    }

    @Override
    public void getPhtot(HomePhtotBean result) {
        list.addAll(result.getData().getDynamics());
        adapter.notifyDataSetChanged();
    }
}