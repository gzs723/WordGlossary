package com.example.myapplication.db.HistoryQuery;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myapplication.db.HistoryQuery.HistoryQueryContract;


/**
 * Created by 李思言 on 2016/11/26.
 */

public class HistoryReaderDbHelper extends SQLiteOpenHelper {

    static final String TEXT_TYPE = " TEXT";
    static final String INTEGER=" INTEGER";
    static final String COMMA_SEP = ",";
    static final String EQUAL= "=";
    static final String SINGLE="'";
    static final int TURE=1;
    static final int FALSE=0;

    static final String SQL_CREATE_ENTRIES="CREATE TABLE "+ HistoryQueryContract.HistoryQueryEntry.TABLE_NAME+"("+
            HistoryQueryContract.HistoryQueryEntry._ID+" INTEGER PRIMARY KEY,"+
            HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_WORD+TEXT_TYPE+COMMA_SEP+
            HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_TRANSLATE+TEXT_TYPE+COMMA_SEP+
            HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_PHONETIC+TEXT_TYPE+COMMA_SEP+
            HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_HISTORY+INTEGER+COMMA_SEP+
            HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_EXPLAINS+TEXT_TYPE+COMMA_SEP+
            HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_COLLECTION+INTEGER+
            " )";


    static final String SQL_DELETE="DELETE FROM "+ HistoryQueryContract.HistoryQueryEntry.TABLE_NAME;



    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + HistoryQueryContract.HistoryQueryEntry.TABLE_NAME;


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "HistoryQuery.db";

    public HistoryReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
          db.execSQL(SQL_DELETE_ENTRIES);
          onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        super.onDowngrade(db, oldVersion, newVersion);
    }
}
