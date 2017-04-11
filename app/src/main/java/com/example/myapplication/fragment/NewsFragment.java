package com.example.myapplication.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.myapplication.R;
import com.example.myapplication.activity.NewsWebViewActivity;
import com.example.myapplication.adapter.NewsListAdapter;
import com.example.myapplication.bean.newsbean.JuHeNewsBean;
import com.example.myapplication.bean.newsbean.NewsData;
import com.example.myapplication.presenter.implPresenter.NewsPresenterlmpl;
import com.example.myapplication.presenter.implView.INewsFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 李思言 on 2016/12/7.
 */

public class  NewsFragment extends BaseFragment implements INewsFragment{

    private NewsPresenterlmpl mNewsPresenterlmpl;
    private NewsListAdapter mNewsListAdapter;

    @BindView(R.id.news_recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.prograss)
    ProgressBar mProgressBar;
    @BindView(R.id.news_refresh)
    SwipeRefreshLayout mRefreshLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.juhenews_fragment,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        initdata();
        initView();
        initPullRefresh();

    }




    @Override
    public void updateNews(JuHeNewsBean juHeNewsBean) {

        hideProgressDialog();
        mNewsListAdapter.addItems((ArrayList<NewsData>) juHeNewsBean.getResult().getData());

    }

    @Override
    public void showProgressDialog() {

       mProgressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressDialog() {

       mProgressBar.setVisibility(View.GONE);
       mRefreshLayout.setRefreshing(false);

    }

    @Override
    public void showError(String error) {

    }

    private void initdata(){

        mNewsPresenterlmpl=new NewsPresenterlmpl(this,getContext());


    }

    private void initView(){


        mRefreshLayout.setColorSchemeColors(Color.RED);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mNewsListAdapter=new NewsListAdapter(getContext());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mNewsListAdapter);
        mNewsListAdapter.setOnItemClickListener(new NewsListAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, NewsData newsData) {
                Intent intent=new Intent(getContext(),NewsWebViewActivity.class);
                intent.putExtra("title",newsData.getTitle());
                intent.putExtra("url",newsData.getUrl());
                startActivity(intent);
            }
        });

        showProgressDialog();
        loadData();

    }

    private void initPullRefresh(){
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
    }


    private void loadData(){

        mNewsPresenterlmpl.getNews();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mNewsPresenterlmpl.unsubcrible();

    }
}
