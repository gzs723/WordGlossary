package com.example.myapplication.ui;

/**
 * Created by 李思言 on 2016/11/29.
 */

public class StringU {

    public static boolean isNotEmpty(CharSequence str) {

        return !isEmpty(str);

    }

    public static boolean isEmpty(CharSequence str) {

        return str == null || str.length() == 0;

    }
}
