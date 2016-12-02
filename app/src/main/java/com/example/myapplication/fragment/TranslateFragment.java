package com.example.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.WordListAdapter;
import com.example.myapplication.presenter.implPresenter.TranslatePresenterImpl;
import com.example.myapplication.presenter.implView.ITranslateFragment;
import com.example.myapplication.ui.ClearableEditText;
import com.example.myapplication.ui.onTapped;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 李思言 on 2016/11/12.
 */

public class TranslateFragment extends BaseFragment implements ITranslateFragment,onTapped{

    private TranslatePresenterImpl mGlossaryPresenter;
    @BindView(R.id.glossary_edit)
    ClearableEditText glossary_edit;
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.prograss)
    ProgressBar mProgressBar;
    @BindView(R.id.translate_borde)
    TextView translate_borde;


    private ClearableEditText mClearableEditText;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.translate_fragment_test,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mGlossaryPresenter=new TranslatePresenterImpl(this,getContext());

        initData();
        initView();


    }


    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initView(){

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new WordListAdapter(getContext()));
        glossary_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {


                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:
                        glossary_edit.addFragment(TranslateFragment.this);
                }

                return false;
            }
        });


    }



    private void initData(){

        mGlossaryPresenter.loadHistoryData();

    }

    @OnClick(R.id.translate_btn)

    public void translate(){

        mGlossaryPresenter.getGlossary(glossary_edit.getText().toString());
        mRecyclerView.setVisibility(View.GONE);
        translate_borde.setVisibility(View.VISIBLE);


    }

    @Override
    public void showProgressDialog() {

        mProgressBar.setProgress(View.VISIBLE);

    }

    @Override
    public void hideProgressDialog() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void updateGlossary(List<String> ydArrayList) {

        StringBuffer sGlossary=new StringBuffer("");
        int i=1;
        for (String s:ydArrayList) {

            sGlossary.append(s+"\n").toString();
        }

        translate_borde.setText(sGlossary);


    }


    @Override
    public void jumpview(Boolean jumo) {

        mRecyclerView.setVisibility(View.VISIBLE);
        translate_borde.setVisibility(View.GONE);


    }
}


