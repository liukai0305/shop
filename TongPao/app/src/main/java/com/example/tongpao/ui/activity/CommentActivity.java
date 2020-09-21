package com.example.tongpao.ui.activity;


import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tongpao.R;
import com.example.tongpao.adapter.AllCommentAdapter;
import com.example.tongpao.adapter.CommentAdapter;
import com.example.tongpao.base.BaseActivity;
import com.example.tongpao.bean.HomeRecommendBean;
import com.example.tongpao.common.Constants;
import com.example.tongpao.interfaces.IBasePresenter;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends BaseActivity {

    private RecyclerView comment_rv;
    private Toolbar toolbar;
    private ImageView comend_return;
    private RecyclerView allcomment_rv;

    @Override
    protected IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    private static final String TAG = "CommentActivity";

    @Override
    protected void initView() {
        comment_rv = findViewById(R.id.comment_rv);

        allcomment_rv = findViewById(R.id.allcomment_rv);
        allcomment_rv.setLayoutManager(new LinearLayoutManager(this));
        allcomment_rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        List<HomeRecommendBean.DataBean.CommentsBean.AllCommentsBean> allComments = Constants.commentsBean.getAllComments();
        Log.d(TAG, "initView: "+allComments.size());
        AllCommentAdapter allCommentAdapter = new AllCommentAdapter(this, allComments);
        allcomment_rv.setAdapter(allCommentAdapter);
        allCommentAdapter.notifyDataSetChanged();

        HomeRecommendBean.DataBean.PostDetailBean curClickPost = Constants.curClickPost;
        comment_rv.setLayoutManager(new LinearLayoutManager(this));
        List<HomeRecommendBean.DataBean.PostDetailBean> postDetailBeans = new ArrayList<>();
        postDetailBeans.add(curClickPost);
        Log.d(TAG, "initView: "+postDetailBeans.size());
        CommentAdapter commentAdapter = new CommentAdapter(this, postDetailBeans);
        comment_rv.setAdapter(commentAdapter);
        commentAdapter.notifyDataSetChanged();

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_comment;
    }

}