package com.example.myapplication.api;

import com.example.myapplication.bean.newsbean.JuHeNewsBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by 李思言 on 2017/1/19.
 */

public interface JuHeNewsApi {

    //http://v.juhe.cn/toutiao/index?key=02524fb2d1344a1c5abf95d513169d44&type=keji
    @FormUrlEncoded
    @POST("index?")
    Observable<JuHeNewsBean>getNews(@Field("type")String type,@Field("key") String key);

}
