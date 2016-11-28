package com.example.myapplication.util;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 李思言 on 2016/11/28.
 */

public class HistoryUtil {

    private static HistoryUtil historyUtil=null;

    private  List<HashMap> historylist;


    private HistoryUtil(){

    }


    public HashMap getHistoryMap(int position) {

        return historylist.get(position);
    }

    public List getHistoryList(){

        return historylist;
    }

    public void setHistorylist(List historylist) {
        this.historylist = historylist;
    }


    public static HistoryUtil getInstance(){

        if (historyUtil==null){
            synchronized ((HistoryUtil.class)){
                if (historyUtil==null){
                    historyUtil=new HistoryUtil();

                }
            }
        }
        return historyUtil;
    }

}
