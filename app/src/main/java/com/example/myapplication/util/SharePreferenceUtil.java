package com.example.myapplication.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by 李思言 on 2016/11/12.
 */

public class SharePreferenceUtil {

    private SharePreferenceUtil(){}

    public static int getNevigationItem(Context context){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getInt("nav_item",-1);
    }
    public static void putNevigationItem(Context context,int t){
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("nav_item",t);
        editor.commit();
    }

    public static void putUser(Context context,String name,String imgurl){

        SharedPreferences sharedPreferences=context.getSharedPreferences("user",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("uesr_name",name);
        editor.putString("user_img",imgurl);
        editor.commit();
    }

    public static SharedPreferences getUser(Context context){

        SharedPreferences sharedPreferences= context.getSharedPreferences("user",Context.MODE_PRIVATE);
        return sharedPreferences;
    }


    public static void clearUser(Context context){

        SharedPreferences sharedPreferences=context.getSharedPreferences("user",Context.MODE_PRIVATE);

        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.clear();

        editor.commit();

    }

    public static boolean ishasUser(Context context){

        SharedPreferences sharedPreferences=context.getSharedPreferences("user",Context.MODE_PRIVATE);

        String data=sharedPreferences.getString("uesr_name","");

        if (data.equals("")){

            return false;
        }

        return true;
    }




}
