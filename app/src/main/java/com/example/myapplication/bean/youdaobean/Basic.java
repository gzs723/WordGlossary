package com.example.myapplication.bean.youdaobean;

import com.google.gson.annotations.SerializedName;

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

    public String getUk_phonetic() {
        return uk_phonetic;
    }

    public void setUk_phonetic(String uk_phonetic) {
        this.uk_phonetic = uk_phonetic;
    }

    @SerializedName("uk-phonetic")
    private String uk_phonetic;

    public String getUs_phonetic() {
        return us_phonetic;
    }


    public void setUs_phonetic(String us_phonetic) {
        this.us_phonetic = us_phonetic;
    }


    @SerializedName("us-phonetic")
    private String us_phonetic;

    public List<String> getExplains() {
        return explains;
    }

    public void setExplains(List<String> explains) {
        this.explains = explains;
    }


}


