package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.bean.youdaobean.HistoryWord;
import com.example.myapplication.presenter.implPresenter.WordNotePresenterlmpl;
import com.example.myapplication.presenter.implView.IWordNoteActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 李思言 on 2017/2/9.
 */

public class WordNoteActivity extends BaseActivity implements IWordNoteActivity {

    private String word;
    WordNotePresenterlmpl wordNotePresenterlmpl;
    @BindView(R.id.word_text)
    TextView wordtext;
    @BindView(R.id.word_phonetic)
    TextView wordphonetic;
    @BindView(R.id.word_explains)
    TextView wordexplains;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_note);
        ButterKnife.bind(this);
        wordNotePresenterlmpl=new WordNotePresenterlmpl(this);
        initData();


    }

    private void initData(){


        word=getIntent().getStringExtra("word");
        wordNotePresenterlmpl.getWordNote(word);
    }



    @Override
    public void showWordNote(HistoryWord historyWord) {

        wordtext.setText("单词 ："+historyWord.getWord());
        wordphonetic.setText("音标 :"+historyWord.getPhonetic());
        wordexplains.setText("译义 ："+historyWord.getExplains());

    }
}
