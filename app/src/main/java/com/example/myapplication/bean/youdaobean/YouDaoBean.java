package com.example.myapplication.bean.youdaobean;

import java.util.List;

/**
 * Created by 李思言 on 2016/11/13.
 */

public class YouDaoBean {

    private List<String> translation;
    private Basic basic;
    private String query;
    private int errorcode;

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }
    public List<String> getTranslation() {
        return translation;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }
    public Basic getBasic() {
        return basic;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    public String getQuery() {
        return query;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }
    public int getErrorcode() {
        return errorcode;
    }

    @Override
    public String toString() {
        return getBasic().getExplains().toString();
    }







}


