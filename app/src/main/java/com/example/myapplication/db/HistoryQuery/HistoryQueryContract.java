package com.example.myapplication.db.HistoryQuery;

import android.provider.BaseColumns;

/**
 * Created by 李思言 on 2016/11/26.
 */

public final class HistoryQueryContract {


    private HistoryQueryContract(){}


    public static abstract class HistoryQueryEntry implements BaseColumns{


        public static final String TABLE_NAME="history";

        public static final String COLUMN_NAME_WORD="word";
        public static final String COLUMN_NAME_HISTORY="history";
        public static final String COLUMN_NAME_EXPLAINS="explains";
        public static final String COLUMN_NAME_TRANSLATE="translate";
        public static final String COLUMN_NAME_COLLECTION="collection";
        public static final String COLUMN_NAME_PHONETIC="phonetic";



    }

}
