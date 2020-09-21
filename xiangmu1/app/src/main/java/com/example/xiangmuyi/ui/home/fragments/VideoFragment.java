package com.example.xiangmuyi.ui.home.fragments;

import android.content.Intent;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.adapter.homeadapter.VideoAdapter;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.base.BaseFragment;
import com.example.xiangmuyi.bean.homebean.HomeVideoBean;
import com.example.xiangmuyi.common.VideoItemDecoration;
import com.example.xiangmuyi.interfaces.home.IHome;
import com.example.xiangmuyi.persenter.home.VideoPersenter;
import com.example.xiangmuyi.ui.home.activity.VideoActivity;

import java.util.ArrayList;

import butterknife.BindView;


public class VideoFragment extends BaseFragment<IHome.VideoPersenter> implements IHome.VideoView {
    @BindView(R.id.rcy)
    RecyclerView rcy;
    private ArrayList<HomeVideoBean.DataBean.ListBean> list;
    private VideoAdapter adapter;
    private Intent intent;
    private HomeVideoBean.DataBean dataBean;

    @Override
    protected int getLayout() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        adapter = new VideoAdapter(list, context);
        rcy.setLayoutManager(new GridLayoutManager(context,2));
        VideoItemDecoration videoItemDecoration = new VideoItemDecoration();
        videoItemDecoration.setSpace(20);
        rcy.addItemDecoration(videoItemDecoration);
        rcy.setAdapter(adapter);
        /**
         * 点击视频列表条目
         */
        adapter.setOnItemClickLis(new BaseAdapter.OnItemClickLisf() {

            @Override
            public void click(int position) {
                intent = new Intent(getActivity(), VideoActivity.class);
                intent.putExtra("pos",position);
                intent.putExtra("videos",dataBean);
                startActivity(intent);
            }
        });

    }

    @Override
    protected IHome.VideoPersenter initPersenter() {
        return new VideoPersenter();
    }

    @Override
    protected void initData() {
        persenter.getVideo();
    }

    @Override
    public void getVideo(HomeVideoBean result) {
        dataBean=result.getData();
        list.addAll(result.getData().getList());
        adapter.notifyDataSetChanged();
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View inflate = inflater.inflate(R.layout.fragment_video, container, false);
//        return inflate;
//    }
}