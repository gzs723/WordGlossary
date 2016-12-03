package com.example.myapplication.api;

import android.util.Log;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 李思言 on 2016/11/14.
 */

public class ApiManage {

    private static final ApiManage apiManage=new ApiManage();

    private ApiManage(){}


    public static ApiManage getInstance(){

        return apiManage;
    }


    public YoudaoApi mYoudaoApi;

    private OkHttpClient okHttpClient=new OkHttpClient.Builder().build();


    public YoudaoApi getYoudaoApiSeivice(){

        if (mYoudaoApi==null){


            synchronized (YoudaoApi.class){

                if (mYoudaoApi==null){

                    mYoudaoApi=new Retrofit.Builder()
                            .baseUrl("http://fanyi.youdao.com")
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(okHttpClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(YoudaoApi.class);

                    }
                }
            }


        return mYoudaoApi;
    }



}
