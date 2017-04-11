package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.bean.youdaobean.HistoryWord;
import com.example.myapplication.presenter.implPresenter.WordNotePresenterlmpl;
import com.example.myapplication.presenter.implView.IWordNoteActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lisiyan on 2017/4/5.
 */

public class WordNoteActivity extends BaseActivity implements IWordNoteActivity {

    WordNotePresenterlmpl wordNotePresenterlmpl;
    @BindView(R.id.word_text)
    TextView wordtext;
    @BindView(R.id.word_phonetic)
    TextView wordphonetic;
    @BindView(R.id.word_explains)
    TextView wordexplains;
    private String word;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_note2);
        ButterKnife.bind(this);
        wordNotePresenterlmpl = new WordNotePresenterlmpl(this);
        initData();
    }

    private void initData() {

        word = getIntent().getStringExtra("word");
        wordNotePresenterlmpl.getWordNote(word);
    }

    @Override
    public void showWordNote(HistoryWord historyWord) {

        wordtext.setText(historyWord.getWord());
        wordphonetic.setText("音标 :" + historyWord.getPhonetic());
        wordexplains.setText(historyWord.getExplains());

    }
}
