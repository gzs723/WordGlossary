package com.example.myapplication.presenter.implPresenter;

import android.content.Context;
import android.os.Looper;
import android.util.Log;

import com.example.myapplication.api.ApiManage;
import com.example.myapplication.bean.youdaobean.HistoryWord;
import com.example.myapplication.bean.youdaobean.YouDaoBean;
import com.example.myapplication.db.HistoryQuery.HistoryQuerylmpl;
import com.example.myapplication.presenter.ITranslatePresenter;
import com.example.myapplication.presenter.implView.ITranslateFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by 李思言 on 2016/11/13.
 */

public class TranslatePresenterImpl extends BasePresenterImpl implements ITranslatePresenter {


    private ITranslateFragment mITranslateFragment;
    private Context mContext;
    private List historylist;
    HistoryQuerylmpl historyQuerylmpl;

    public TranslatePresenterImpl(ITranslateFragment translateFragment, Context context){
        this.mITranslateFragment =translateFragment;
        mContext=context;
        historyQuerylmpl=HistoryQuerylmpl.newInstance(context);
    }

    @Override
    public void getGlossary(final String word) {

       mITranslateFragment.showProgressDialog();

        final HistoryWord historyWord=historyQuerylmpl.hasWord(word);

        if (historyWord.getWord()!=null){

            mITranslateFragment.hideProgressDialog();
            historyQuerylmpl.queryHistory();
            addHistorySql(historyWord);
            mITranslateFragment.updateGlossary(historyWord.getExplains());
            Log.d("be","old");

        }else {

            addSubscription(ApiManage.getInstance().getYoudaoApiSeivice().getTranslate(word).
                    map(new Func1<YouDaoBean,HistoryWord>() {
                @Override
                public HistoryWord call(YouDaoBean bean) {

                    HistoryWord dbhistoryWord =new HistoryWord();
                    dbhistoryWord.setWord(bean.getQuery());
                    dbhistoryWord.setTranslate(bean.getTranslation().get(0));
                    dbhistoryWord.setPhonetic(bean.getBasic().getPhonetic());
                    StringBuffer sGlossary = new StringBuffer("");

                    for (String s : bean.getBasic().getExplains()) {

                        sGlossary.append(s + "\n").toString();
                    }
                    dbhistoryWord.setExplains(sGlossary.toString());



                    return dbhistoryWord;
                }

            }), new Subscriber<HistoryWord>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                    Log.d("TF",e.toString());

                }

                @Override
                public void onNext(HistoryWord youdaoword) {

                    mITranslateFragment.hideProgressDialog();
                    addHistorySql(youdaoword);
                    mITranslateFragment.updateGlossary(youdaoword.getExplains());
                    Log.d("be","new");
                }


            });
        }




    }

    @Override
    public void addHistorySql(HistoryWord historyWord) {

        historyQuerylmpl.deleteByWord(historyWord.getWord());
        historyQuerylmpl.insert(historyWord);

    }

    @Override
    public void getHistorySql(String word) {

    }

    @Override
    public List loadHistoryData() {

        historylist=historyQuerylmpl.queryHistory();

        return historylist;
    }


    @Override
    public void deleteHistorySql(String word) {

        historyQuerylmpl.updateHistory(word);
    }

    @Override
    public int updateCollect(String word) {

         HistoryWord historyWord= historyQuerylmpl.hasWord(word);

         if (historyWord.getCollection().equals("0")){
             historyQuerylmpl.updateCollection(word,1);
             Log.d("collection","false");
             return 1;
         }else {
             historyQuerylmpl.updateCollection(word,0);
             Log.d("collection","ture");
             return 0;
         }

    }
}
