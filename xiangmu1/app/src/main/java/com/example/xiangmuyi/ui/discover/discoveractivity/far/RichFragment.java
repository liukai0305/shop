package com.example.xiangmuyi.ui.discover.discoveractivity.far;

import android.util.Log;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.xiangmuyi.R;
import com.example.xiangmuyi.adapter.discoveradapter.RichAdapter;
import com.example.xiangmuyi.base.BaseFragment;
import com.example.xiangmuyi.bean.discoverbean.PaiHangBang.EsperienBean;
import com.example.xiangmuyi.bean.discoverbean.PaiHangBang.ListBean;
import com.example.xiangmuyi.bean.discoverbean.PaiHangBang.LocalBean;
import com.example.xiangmuyi.bean.discoverbean.PaiHangBang.QianDaoBean;
import com.example.xiangmuyi.common.Constants;
import com.example.xiangmuyi.interfaces.discover.IDiscover;
import com.example.xiangmuyi.persenter.discover.pai.RichPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RichFragment extends BaseFragment<IDiscover.EsperienPersenter> implements IDiscover.EsperienView {
        @BindView(R.id.recycler_level)
        RecyclerView recyclerLevel;
        private RichAdapter richAdapter;
        List<ListBean> listBeans=new ArrayList<>();

        @Override
        protected void initView() {
            recyclerLevel.setLayoutManager(new LinearLayoutManager(context));
            recyclerLevel.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        }

        @Override
        protected IDiscover.EsperienPersenter initPersenter() {
            return new RichPresenter();
        }

        @Override
        protected void initData() {
            persenter.getLocal();
        }

        @Override
        protected int getLayout() {
            return R.layout.fragment_level;
        }

        private static final String TAG = "LevelFragment";


        @Override
        public void getEsperien(EsperienBean result) {

        }

        @Override
        public void getLocal(LocalBean result) {
            Log.d(TAG, "getLevelReturn: " + result.getData().getTongQianTop().getList().size());
            if (result.getStatus().getStatusCode() == 100) {
                List<ListBean> list = result.getData().getTongQianTop().getList();
                listBeans.addAll(list);
                richAdapter = new RichAdapter(listBeans,context);
                richAdapter.setType(0);
                recyclerLevel.setAdapter(richAdapter);
            }
        }

        @Override
        public void getQian(QianDaoBean result) {



        }
}
