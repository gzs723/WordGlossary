package com.example.myapplication.presenter.implPresenter;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.api.ApiManage;
import com.example.myapplication.bean.newsbean.JuHeNewsBean;
import com.example.myapplication.fragment.NewsFragment;
import com.example.myapplication.presenter.INewsPresenter;

import rx.Subscriber;

/**
 * Created by 李思言 on 2016/12/7.
 */

public class NewsPresenterlmpl extends BasePresenterImpl implements INewsPresenter {


    private NewsFragment mNewsFragment;

    private Context mContext;

    public  NewsPresenterlmpl(NewsFragment newsFragment, Context context){

        this.mNewsFragment=newsFragment;
        this.mContext=context;
    }


    @Override
    public void getNews() {

        addSubscription(ApiManage.getInstance().getJuHeNewsApi().getNews("guoji", "02524fb2d1344a1c5abf95d513169d44"),
                new Subscriber<JuHeNewsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.d("NewsPresenterlmpl",e.getMessage());
                    }

                    @Override
                    public void onNext(JuHeNewsBean bean) {

                        mNewsFragment.updateNews(bean);

                    }


                });

    }
}
