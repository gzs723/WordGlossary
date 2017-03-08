package com.example.myapplication.presenter.implPresenter;

import android.content.Context;


import com.example.myapplication.db.HistoryQuery.HistoryQuerylmpl;
import com.example.myapplication.presenter.IWoreStarPresenter;
import com.example.myapplication.presenter.implView.IWordStarFragment;
import java.util.List;


/**
 * Created by lsy on 2017/2/20.
 */

public class WordStarPresenterImpl extends BasePresenterImpl implements IWoreStarPresenter {

    private IWordStarFragment iwordstar;
    HistoryQuerylmpl historyQuerylmpl;
    private List starList;

    public WordStarPresenterImpl(Context context,IWordStarFragment iwordstar){
        this.iwordstar=iwordstar;
        historyQuerylmpl=HistoryQuerylmpl.newInstance(context);

    }


    @Override
    public List loadStarData() {

        starList=historyQuerylmpl.queryCollection();

        return starList;
    }

    @Override
    public void updateCollect(String word) {

        historyQuerylmpl.updateCollection(word,0);
    }

}
