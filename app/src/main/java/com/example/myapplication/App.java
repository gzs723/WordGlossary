package com.example.myapplication;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by 李思言 on 2016/11/17.
 */

public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);

    }
    {
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
}
