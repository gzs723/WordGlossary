package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activity.WordNoteActivity;
import com.example.myapplication.adapter.StarNoteAdapter;
import com.example.myapplication.adapter.WordListAdapter;
import com.example.myapplication.presenter.implPresenter.WordStarPresenterImpl;
import com.example.myapplication.presenter.implView.IWordStarFragment;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lsy on 2017/2/20.
 */

public class WordNoteFragment extends BaseFragment implements IWordStarFragment {

    private StarNoteAdapter mStarNoteAdapter;
    private WordStarPresenterImpl mStarPresenter;

    @BindView(R.id.star_recycleview)
    RecyclerView mRecyclerView;

    final String DELETE="delete";

    final String VIEW="view";

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
        mStarNoteAdapter=new StarNoteAdapter(getContext(),mStarPresenter.loadStarData());
        mRecyclerView.setAdapter(mStarNoteAdapter);
        mStarNoteAdapter.setOnItemClickListener(new StarNoteAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, String word, int position, String type) {
                switch (type){
                    case DELETE:
                        mStarPresenter.updateCollect(word);
                        mStarNoteAdapter.removelist(position);
                        mStarNoteAdapter.notifyDataSetChanged();
                        break;
                    case VIEW:
                        Intent intent=new Intent(getContext(), WordNoteActivity.class);
                        intent.putExtra("word",word);
                        startActivity(intent);
                        break;
                }
            }
        });

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
