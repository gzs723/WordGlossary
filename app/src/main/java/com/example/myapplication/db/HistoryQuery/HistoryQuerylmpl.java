package com.example.myapplication.db.HistoryQuery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 李思言 on 2016/11/26.
 */

public class HistoryQuerylmpl {

    private Context mContext;
    private HistoryReaderDbHelper mDbHelper;

    public HistoryQuerylmpl(Context context){

        this.mContext=context;
        mDbHelper=new HistoryReaderDbHelper(context);

    }






    public void insert(String word,String translate){

        SQLiteDatabase db=mDbHelper.getWritableDatabase();

        ContentValues values=new ContentValues();

        values.put(HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_WORD,word);
        values.put(HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_TRANSLATE,translate);


        long newRowId=db.insert(HistoryQueryContract.HistoryQueryEntry.TABLE_NAME, null,values);
    }

    public List query(){


        List historylist=new ArrayList<>();

        SQLiteDatabase db=mDbHelper.getReadableDatabase();

        String[] projection={

                HistoryQueryContract.HistoryQueryEntry._ID,
                HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_WORD,
                HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_TRANSLATE

        };

        Cursor c=db.query(
                HistoryQueryContract.HistoryQueryEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );


        try{
            if (c.moveToFirst()){
                do {
                    Map historymap=new HashMap();
                    historymap.put("id", c.getInt(0));
                    historymap.put("word",c.getString(1));
                    historymap.put("translate",c.getString(2));
                    historylist.add(historymap);
                }while (c.moveToNext());
            }
        }finally {
            if (c!=null){
                try {
                    c.close();
                }catch (Exception e){
                    Log.e("err",e.getMessage());
                }
            }

        }
        return  historylist;
    }

    public boolean hasWord(String name){

        SQLiteDatabase db=mDbHelper.getReadableDatabase();
        boolean exist=false;

        String[] projection={
                HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_WORD,
        };

        Cursor c=db.query(
                HistoryQueryContract.HistoryQueryEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        try{
            if (c.moveToFirst()){
                do {
                   if (name.equals(c.getString(0))){
                       exist=true;
                   }
                }while (c.moveToNext());
            }
        }finally {
            if (c!=null){
                try {
                    c.close();
                }catch (Exception e){
                    Log.e("err",e.getMessage());
                }
            }
        }


        return exist;
    }

    public void deleteById(int id){

        String selection= HistoryQueryContract.HistoryQueryEntry._ID+HistoryReaderDbHelper.EQUAL+id;
        SQLiteDatabase db=mDbHelper.getWritableDatabase();
        db.delete(HistoryQueryContract.HistoryQueryEntry.TABLE_NAME,selection,null);

    }

    private void deleteByName(String name){

        String selection= HistoryQueryContract.HistoryQueryEntry._ID+HistoryReaderDbHelper.EQUAL+name;
        SQLiteDatabase db=mDbHelper.getWritableDatabase();
        db.delete(HistoryQueryContract.HistoryQueryEntry.TABLE_NAME,selection,null);

    }

    public void deleteDateall(){

        SQLiteDatabase db=mDbHelper.getReadableDatabase();
        db.execSQL(HistoryReaderDbHelper.SQL_DELETE);

    }

}
