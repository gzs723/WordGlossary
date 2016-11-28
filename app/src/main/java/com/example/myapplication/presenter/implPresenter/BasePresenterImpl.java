package com.example.myapplication.presenter.implPresenter;

import com.example.myapplication.presenter.BasePresenter;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by 李思言 on 2016/11/13.
 */

public class BasePresenterImpl implements BasePresenter {


    private CompositeSubscription mCompositeSubscription;


    protected void addSubscription(Observable observable,Subscriber subscriber){

        if (this.mCompositeSubscription==null){
            mCompositeSubscription=new CompositeSubscription();
        }

        this.mCompositeSubscription.add(observable
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(subscriber));

    }


    @Override
    public void unsubcrible() {

        //onCompleted() 调用后会释放
        if (!mCompositeSubscription.isUnsubscribed()){

            mCompositeSubscription.unsubscribe();
        }

    }
}
