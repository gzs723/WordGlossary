package com.example.myapplication.presenter;

import com.example.myapplication.bean.youdaobean.YouDaoBean;

import java.util.List;

/**
 * Created by 李思言 on 2016/11/13.
 */

public interface ITranslatePresenter extends BasePresenter {


    void getGlossary(String word);

    void addHistorySql(YouDaoBean youDaoBean);

    void getHistorySql(String word);

    List loadHistoryData();

    void deleteHistorySql(String word);

    void updateCollect(String word);

}
