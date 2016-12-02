package com.example.myapplication.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by 李思言 on 2016/11/29.
 */

public class TextWatcherAdapter implements TextWatcher {

    public interface TextWatcherListener {

        void onTextChanged(EditText view, String text);

    }

    private final EditText view;
    private final TextWatcherListener listener;

    public TextWatcherAdapter(EditText editText, TextWatcherListener listener) {
        this.view = editText;
        this.listener = listener;
    }

    @Override
    public void beforeTextChanged(CharSequence sequence, int i, int i1, int i2) {



    }

    @Override
    public void onTextChanged(CharSequence sequence, int i, int i1, int i2) {

        listener.onTextChanged(view,sequence.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
