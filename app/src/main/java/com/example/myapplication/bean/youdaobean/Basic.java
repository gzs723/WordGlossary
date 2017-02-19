package com.example.myapplication.bean.youdaobean;

import java.util.List;

/**
 * Created by 李思言 on 2016/11/15.
 */

public class Basic {

    private List<String> explains;

    public String getPhonetic() {
        return "["+phonetic+"]";
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    private String phonetic;

    public List<String> getExplains() {
        return explains;
    }

    public void setExplains(List<String> explains) {
        this.explains = explains;
    }


}


