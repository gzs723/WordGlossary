package com.example.myapplication.api;

import com.example.myapplication.bean.jinshanbean.JinshanBean;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lisiyan on 2017/4/9.
 */

public interface JinShanApi {


//    http://dict-co.iciba.com/api/dictionary.php?w=go&key=********
    @GET("dictionary.php?key=B668F04B4CD7FB68F286A25DD206DB88&type=json")
    Observable<JinshanBean> getwordVideo (@Query("w") String word);
}
