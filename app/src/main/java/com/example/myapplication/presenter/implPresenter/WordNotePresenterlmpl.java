package com.example.myapplication.presenter.implPresenter;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.bean.youdaobean.HistoryWord;
import com.example.myapplication.db.HistoryQuery.HistoryQuerylmpl;
import com.example.myapplication.presenter.BasePresenter;
import com.example.myapplication.presenter.IWordNotePresenter;
import com.example.myapplication.presenter.implView.IWordNoteActivity;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by 李思言 on 2017/2/10.
 */

public class WordNotePresenterlmpl extends BasePresenterImpl implements IWordNotePresenter {

    private Context mContext;
    HistoryQuerylmpl mHistoryQuerylmpl;
    IWordNoteActivity mIWordNoteActivity;


    public WordNotePresenterlmpl(Context context){
        mContext=context;
        mIWordNoteActivity= (IWordNoteActivity) context;
        mHistoryQuerylmpl=HistoryQuerylmpl.newInstance(context);
    }

    @Override
    public void getWordNote(final String word) {

        addSubscription(Observable.create(new Observable.OnSubscribe<HistoryWord>() {

            @Override
            public void call(Subscriber<? super HistoryWord> subscriber) {

                subscriber.onNext(mHistoryQuerylmpl.hasWord(word));

            }
        }), new Subscriber<HistoryWord>() {
            @Override
            public void onCompleted() {


            }

            @Override
            public void onError(Throwable e) {

                Log.d("123",e.toString());

            }

            @Override
            public void onNext(HistoryWord word) {

                Log.d("123",word.getWord());
                mIWordNoteActivity.showWordNote(word);
            }
        });
    }


}
