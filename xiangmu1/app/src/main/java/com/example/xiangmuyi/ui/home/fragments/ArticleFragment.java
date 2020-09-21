package com.example.xiangmuyi.ui.home.fragments;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.adapter.homeadapter.ArticleAdapter;
import com.example.xiangmuyi.base.BaseFragment;
import com.example.xiangmuyi.bean.homebean.HomeArticleBean;
import com.example.xiangmuyi.interfaces.home.IHome;
import com.example.xiangmuyi.persenter.home.ArticlePersenter;
import com.example.xiangmuyi.ui.home.fragments.zheshiwenzhang.ChuanTong2Activity;
import com.example.xiangmuyi.ui.home.fragments.zheshiwenzhang.DaShi4Activity;
import com.example.xiangmuyi.ui.home.fragments.zheshiwenzhang.RenShi1Activity;
import com.example.xiangmuyi.ui.home.fragments.zheshiwenzhang.YanJin3Activity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;

public class ArticleFragment extends BaseFragment<IHome.ArticlePersenter> implements IHome.ArticleView {


    @BindView(R.id.banner_articles)
    Banner bannerArticles;
    @BindView(R.id.content_article)
    LinearLayout contentArticle;
    @BindView(R.id.article_recycler)
    RecyclerView articleRecycler;
    @BindView(R.id.ren1)
    TextView ren1;
    @BindView(R.id.chuan2)
    TextView chuan2;
    @BindView(R.id.yan3)
    TextView yan3;
    @BindView(R.id.da4)
    TextView da4;
    private ArrayList<HomeArticleBean.DataBean.ArticlesBean> list;
    private ArticleAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_article;
    }

    @Override
    protected void initView() {
        articleRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new ArticleAdapter(list, getActivity());
        articleRecycler.setAdapter(adapter);
        ren1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RenShi1Activity.class);
                startActivity(intent);
            }
        });
        chuan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ChuanTong2Activity.class));
            }
        });
        yan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), YanJin3Activity.class));
            }
        });
        da4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DaShi4Activity.class));
            }
        });

    }

    @Override
    protected IHome.ArticlePersenter initPersenter() {
        return new ArticlePersenter();
    }

    @Override
    protected void initData() {
        persenter.getArticle();
    }

    @Override
    public void getArticle(HomeArticleBean result) {
        list.addAll(result.getData().getArticles());
        bannerArticles.setImages(result.getData().getWeekArticles())
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        RoundedCorners roundedCorners = new RoundedCorners(10);
                        RequestOptions override = RequestOptions.bitmapTransform(roundedCorners).override(100, 100);
                        HomeArticleBean.DataBean.WeekArticlesBean bean= (HomeArticleBean.DataBean.WeekArticlesBean) path;
                        Glide.with(context).load(bean.getCover()).apply(override).into(imageView);
                    }
                })
                .setDelayTime(2000)
                .start();
        adapter.notifyDataSetChanged();
    }
}
