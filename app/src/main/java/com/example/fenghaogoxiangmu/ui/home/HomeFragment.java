package com.example.fenghaogoxiangmu.ui.home;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.fenghaogoxiangmu.R;
import com.example.fenghaogoxiangmu.adapter.home.HomeListAdapter;
import com.example.fenghaogoxiangmu.base.BaseFragment;
import com.example.fenghaogoxiangmu.bean.home.HomeBean;
import com.example.fenghaogoxiangmu.interfaces.home.IHome;
import com.example.fenghaogoxiangmu.persenter.home.HomePersenter;
import com.example.fenghaogoxiangmu.ui.home.activitys.HomeHotActivity;
import com.example.fenghaogoxiangmu.ui.home.activitys.HomeNewActivity;
import com.example.fenghaogoxiangmu.ui.home.activitys.HomeTopicActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class HomeFragment extends BaseFragment<IHome.Persenter> implements IHome.View {


    @BindView(R.id.rcy)
    RecyclerView rcy;
    private ArrayList<HomeBean.HomeListBean> list;
    private HomeListAdapter adapter;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        adapter = new HomeListAdapter(list, context);
        gridLayoutManager = new GridLayoutManager(context, 2);
        adapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                int type = list.get(i).currentType;
                switch (type) {
                    case HomeBean.ITEM_TYPE_BANNER:
                    case HomeBean.ITEM_TYPE_TITLE:
                    case HomeBean.ITEM_TYPE_TITLETOP:
                    case HomeBean.ITEM_TYPE_TOPIC:
                    case HomeBean.ITEM_TYPE_HOT:
                    case HomeBean.ITEM_TYPE_TAB:

                        return 2;
                    case HomeBean.ITEM_TYPE_LIVING:
                    case HomeBean.ITEM_TYPE_KITCHEN:
                    case HomeBean.ITEM_TYPE_BRAND:
                    case HomeBean.ITEM_TYPE_NEW:
                        return 1;
                }
                return 0;

            }
        });
        rcy.setLayoutManager(gridLayoutManager);
        adapter.bindToRecyclerView(rcy);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int type = list.get(position).currentType;
                Intent intent = new Intent();
                switch (type) {
                    case HomeBean.ITEM_TYPE_BANNER:
                        break;
                    case HomeBean.ITEM_TYPE_BRAND:
                        HomeBean.DataBean.BrandListBean brandListBean=(HomeBean.DataBean.BrandListBean)list.get(position).data;
                        intent.putExtra("id",brandListBean.getId());
                        intent.setClass(context, HomeNewActivity.class);
                        context.startActivity(intent);
                        break;
                    case HomeBean.ITEM_TYPE_TITLE:
                        break;
                    case HomeBean.ITEM_TYPE_NEW:
                        HomeBean.DataBean.NewGoodsListBean Newbean = (HomeBean.DataBean.NewGoodsListBean) list.get(position).data;
                        intent.putExtra("id", Newbean.getId());
                        intent.setClass(context, HomeHotActivity.class);
                        startActivityForResult(intent, 2000);
                        break;
                    case HomeBean.ITEM_TYPE_HOT:
                        HomeBean.DataBean.HotGoodsListBean bean = (HomeBean.DataBean.HotGoodsListBean) list.get(position).data;
                        intent.putExtra("id", bean.getId());
                        intent.setClass(context, HomeHotActivity.class);
                        startActivityForResult(intent, 2000);
                        break;
                    case HomeBean.ITEM_TYPE_TOPIC:

                        break;
                    case HomeBean.ITEM_TYPE_LIVING:
                        HomeBean.DataBean.CategoryListBean.GoodsListBean goodListBean = (HomeBean.DataBean.CategoryListBean.GoodsListBean) list.get(position).data;
                        intent.putExtra("id", goodListBean.getId());
                        intent.setClass(context, HomeHotActivity.class);
                        startActivityForResult(intent, 2000);
                        break;
                    case HomeBean.ITEM_TYPE_TITLETOP:
                        break;
                }
            }
        });

    }

    @Override
    protected IHome.Persenter initPersenter() {
        return new HomePersenter();
    }

    @Override
    protected void initData() {
        persenter.getHome();
    }

    @Override
    public void getHomeReturn(List<HomeBean.HomeListBean> result) {
        list.addAll(result);
        adapter.notifyDataSetChanged();
    }
}