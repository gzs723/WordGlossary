package com.example.myapplication.presenter.implPresenter;

import android.util.Log;


import com.example.myapplication.presenter.INewsWebPresenter;
import com.example.myapplication.presenter.implView.INewsWebActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by 李思言 on 2017/1/26.
 */

public class NewsWebPresenterImpl extends BasePresenterImpl implements INewsWebPresenter {

    private INewsWebActivity mINewsWebActivity;


    public NewsWebPresenterImpl(INewsWebActivity iNewsWebActivity){

        this.mINewsWebActivity=iNewsWebActivity;
    }

    @Override
    public void getNews(String url) {

        final OkHttpClient client = new OkHttpClient();

        final Request request=new Request.Builder()
                .url(url)
                .build();




        addSubscription(Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {


                        String body = response.body().string();
                        Document document = Jsoup.parse(body);
                        Elements elements = document.select("[class=J-article article]");
                        final String html = elements.outerHtml();
                        subscriber.onNext(html);


                    }
                });



            }}), new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {

                        mINewsWebActivity.updateNews(s);
                    }

                }
        );



    }

}
