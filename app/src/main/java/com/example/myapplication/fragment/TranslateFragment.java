package com.example.myapplication.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activity.WordNoteActivity;
import com.example.myapplication.adapter.WordListAdapter;
import com.example.myapplication.bean.youdaobean.HistoryWord;
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

public class TranslateFragment extends BaseFragment implements ITranslateFragment, onTapped {

    private TranslatePresenterImpl mGlossaryPresenter;
    @BindView(R.id.glossary_edit)
    ClearableEditText glossary_edit;
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.prograss)
    ProgressBar mProgressBar;
    @BindView(R.id.translate_borde)
    TextView translate_borde;

    private WordListAdapter mWordListAdapter;

    final String DELETE="delete";

    final String VIEW="view";

    final String COLLECT="collect";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.translate_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGlossaryPresenter = new TranslatePresenterImpl(this, getContext());
        initData();
        initView();
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    private void initView() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        mWordListAdapter = new WordListAdapter(getContext(), mGlossaryPresenter.loadHistoryData());
        mRecyclerView.setAdapter(mWordListAdapter);
        glossary_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                //触发按下事件
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        glossary_edit.addFragment(TranslateFragment.this);

                }

                return false;
            }
        });
        mWordListAdapter.setOnItemClickListener(new WordListAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, String word, int position, String type) {

                switch (type){
                    case DELETE:
                        mGlossaryPresenter.deleteHistorySql(word);
                        mWordListAdapter.removelist(position);
                        mWordListAdapter.notifyDataSetChanged();
                        break;
                    case VIEW:
                        Intent intent=new Intent(getContext(), WordNoteActivity.class);
                        intent.putExtra("word",word);
                        startActivity(intent);
                        break;
                    case COLLECT:
                        int n=mGlossaryPresenter.updateCollect(word);
                        mWordListAdapter.getList().get(position).setCollection(String.valueOf(n));
                        mWordListAdapter.notifyDataSetChanged();
                        Toast.makeText(getContext(),"收藏成功",Toast.LENGTH_SHORT).show();
                }
            }
        });

        glossary_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //软键盘回复
                glossary_edit.setFocusable(true);
                glossary_edit.setFocusableInTouchMode(true);
                glossary_edit.requestFocus();

            }
        });

    }



    private void initData() {

//        mGlossaryPresenter.loadHistoryData();

    }


    @OnClick(R.id.translate_btn)

    public void translate() {
        mGlossaryPresenter.getGlossary(glossary_edit.getText().toString());
        mRecyclerView.setVisibility(View.GONE);
        translate_borde.setVisibility(View.VISIBLE);
        //隐藏keyboard
        hideSoftKeyboard(glossary_edit,getActivity());

    }





    @Override
    public void showProgressDialog() {

        mProgressBar.setProgress(View.VISIBLE);

    }

    @Override
    public void hideProgressDialog() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void updateGlossary(String ydexplains) {



        translate_borde.setText(ydexplains);


    }

    //回调事件，点击x清空内容
    @Override
    public void jumpview(Boolean jumo) {
        mRecyclerView.setVisibility(View.VISIBLE);
        translate_borde.setVisibility(View.GONE);
        mWordListAdapter.setList(mGlossaryPresenter.loadHistoryData());
        mWordListAdapter.notifyDataSetChanged();
        glossary_edit.clearFocus();

    }

    ///////////////////////////////////////// 隐藏或显示软键盘 /////////////////////////////////
    public static void hideSoftKeyboard(EditText editText, Context context) {
        if (editText != null && context != null) {
            InputMethodManager imm = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(),0);
        }
    }
    public static void showSoftKeyboard(EditText editText, Context context) {
        if (editText != null && context != null) {
            InputMethodManager imm = (InputMethodManager) context
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, 0);
        }
    }



}


