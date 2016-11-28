package com.example.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.WordListAdapter;
import com.example.myapplication.db.HistoryQuery.HistoryQuerylmpl;
import com.example.myapplication.presenter.implPresenter.TranslatePresenterImpl;
import com.example.myapplication.presenter.implView.ITranslateFragment;
import com.example.myapplication.util.HistoryUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 李思言 on 2016/11/12.
 */

public class TranslateFragment extends BaseFragment implements ITranslateFragment {

    private TranslatePresenterImpl mGlossaryPresenter;
    @BindView(R.id.glossary_edit)
    EditText glossary_edit;
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.prograss)
    ProgressBar mProgressBar;
    @BindView(R.id.translate_borde)
    TextView translate_borde;

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
    }

    private void initView(){

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new WordListAdapter(getContext()));

    }

    private void initData(){

        mGlossaryPresenter.loadHistoryData();

    }

    @OnClick(R.id.translate_btn)

    public void translate(){

        mGlossaryPresenter.getGlossary(glossary_edit.getText().toString());
        mRecyclerView.setVisibility(View.VISIBLE);
        translate_borde.setVisibility(View.GONE);

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

        Log.d("Translate",sGlossary.toString());


    }
}
