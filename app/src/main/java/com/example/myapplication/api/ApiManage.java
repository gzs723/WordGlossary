package com.example.myapplication.api;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.HTTP;

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


    private  final OkHttpClient okHttpClient=new OkHttpClient.Builder().build();


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


    public JuHeNewsApi mJuHeNewsApi;

    public JuHeNewsApi getJuHeNewsApi(){
        if (mJuHeNewsApi==null){

            synchronized (JuHeNewsApi.class){
                if (mJuHeNewsApi==null){
                    mJuHeNewsApi=new Retrofit.Builder()
                            .baseUrl("http://v.juhe.cn/toutiao/")
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(okHttpClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(JuHeNewsApi.class);
                }


            }

        }



        return mJuHeNewsApi;
    }



    public JinShanApi mJinShanApi;

    public JinShanApi getJinShanApi(){

        if (mJinShanApi==null){

            synchronized (JinShanApi.class){


                if (mJinShanApi==null){

                    mJinShanApi=new Retrofit.Builder()
                            .baseUrl("http://dict-co.iciba.com/api")
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(okHttpClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(JinShanApi.class);
                }

            }

        }


        return mJinShanApi;

    }




}
