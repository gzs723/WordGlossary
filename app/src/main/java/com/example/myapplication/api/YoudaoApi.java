package com.example.myapplication.api;

import com.example.myapplication.bean.youdaobean.YouDaoBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 李思言 on 2016/11/13.
 */

public interface YoudaoApi {

//    http://fanyi.youdao.com/openapi.do?keyfrom=cijie1&key=1360449711&type=data&doctype=json&version=1.1&q=good
//    您的key：1360449711
//    您的keyfrom：cijie1
    @GET("openapi.do?keyfrom=cijie1&key=1360449711&type=data&doctype=json&version=1.1")
    Observable<YouDaoBean>getTranslate(@Query("q") String q);

}
