package com.example.myapplication.presenter.implPresenter;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.api.ApiManage;
import com.example.myapplication.bean.youdaobean.YouDaoBean;
import com.example.myapplication.db.HistoryQuery.HistoryQuerylmpl;
import com.example.myapplication.presenter.ITranslatePresenter;
import com.example.myapplication.presenter.implView.ITranslateFragment;

import java.util.List;

import rx.Subscriber;

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
    }

    @Override
    public void getGlossary(final String word) {

       mITranslateFragment.showProgressDialog();


        addSubscription(ApiManage.getInstance().getYoudaoApiSeivice().getTranslate(word), new Subscriber<YouDaoBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                Log.d("TF",e.toString());

            }

            @Override
            public void onNext(YouDaoBean bean) {


                mITranslateFragment.hideProgressDialog();

                addHistorySql(word,bean.getBasic().getExplains().get(0));
                mITranslateFragment.updateGlossary(bean.getBasic().getExplains());



            }


        });


    }

    @Override
    public void addHistorySql(String word,String translate) {

        HistoryQuerylmpl historyQuerylmpl=new HistoryQuerylmpl(mContext);
        historyQuerylmpl.insert(word, translate);

    }

    @Override
    public void getHistorySql(String word) {

    }

    @Override
    public List loadHistoryData() {

        historyQuerylmpl=new HistoryQuerylmpl(mContext);
        historylist=historyQuerylmpl.query();
        
        return historylist;
    }


    @Override
    public void deleteHistorySql() {

        historyQuerylmpl=new HistoryQuerylmpl(mContext);
        historyQuerylmpl.deleteDateall();

    }
}
