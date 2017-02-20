package com.example.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapter.WordListAdapter;
import com.example.myapplication.presenter.implPresenter.WordStarPresenterImpl;
import com.example.myapplication.presenter.implView.IWordStarFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lsy on 2017/2/20.
 */

public class WordNoteFragment extends BaseFragment implements IWordStarFragment {

    private WordListAdapter mWordListAdapter;
    private WordStarPresenterImpl mStarPresenter;

    @BindView(R.id.star_recycleview)
    RecyclerView mRecyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.star_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mStarPresenter=new WordStarPresenterImpl(getContext(),this);
        initView();

    }

    private void initView(){

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        mWordListAdapter = new WordListAdapter(getContext(),mStarPresenter.loadStarData());
        mRecyclerView.setAdapter(mWordListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override


    public void updateGlossary(List<String> ydArrayList) {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showError(String error) {

    }
}
