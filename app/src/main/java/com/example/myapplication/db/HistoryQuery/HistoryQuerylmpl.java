package com.example.myapplication.db.HistoryQuery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.bean.youdaobean.HistoryWord;
import com.example.myapplication.bean.youdaobean.YouDaoBean;

import java.util.ArrayList;
import java.util.List;

import rx.Single;

import static com.umeng.socialize.utils.DeviceConfig.context;

/**
 * Created by 李思言 on 2016/11/26.
 */

public class HistoryQuerylmpl {

    private Context mContext;
    private HistoryReaderDbHelper mDbHelper;

    private static HistoryQuerylmpl instance = null;

    private HistoryQuerylmpl(Context context){

        this.mContext=context;
        mDbHelper=new HistoryReaderDbHelper(context);
    }

    public static synchronized HistoryQuerylmpl newInstance(Context context){

        if (instance==null){

            instance=new HistoryQuerylmpl(context);
            return instance;
        }

         return instance;
    }


    public void insert(YouDaoBean youDaoBean){

        SQLiteDatabase db=mDbHelper.getWritableDatabase();

        ContentValues values=new ContentValues();

        values.put(HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_WORD,youDaoBean.getQuery());
        values.put(HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_TRANSLATE,youDaoBean.getTranslation().get(0));
        values.put(HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_COLLECTION,HistoryReaderDbHelper.FALSE);
        values.put(HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_PHONETIC,youDaoBean.getBasic().getPhonetic());
        values.put(HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_HISTORY,HistoryReaderDbHelper.TURE);
        values.put(HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_EXPLAINS,youDaoBean.getBasic().getExplains().get(0));


        long newRowId=db.insert(HistoryQueryContract.HistoryQueryEntry.TABLE_NAME, null,values);
    }


    public void updateCollection(String word){


        SQLiteDatabase db=mDbHelper.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_COLLECTION,HistoryReaderDbHelper.TURE);

        String selection= HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_WORD +" = ?";
        String[] selectionArgs = { word };
        int count=db.update(
                HistoryQueryContract.HistoryQueryEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

    }

    public void updateHistory(String word){

        SQLiteDatabase db=mDbHelper.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_HISTORY,HistoryReaderDbHelper.FALSE);

        String selection= HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_WORD +" = ?";
        String[] selectionArgs = { word };
        int count=db.update(
                HistoryQueryContract.HistoryQueryEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

    }


    public List queryHistory(){


        List historylist=new ArrayList<HistoryWord>();

        SQLiteDatabase db=mDbHelper.getReadableDatabase();

        String[] projection={

                HistoryQueryContract.HistoryQueryEntry._ID,
                HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_WORD,
                HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_TRANSLATE,
                HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_PHONETIC,
        };

        final String selection= HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_HISTORY+"= ?";
        //读取指定数据
        final String[] selectionArgs ={"1"};
        Cursor c=db.query(
                HistoryQueryContract.HistoryQueryEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );


        try{
            if (c.moveToFirst()){
                do {
                    HistoryWord historyWord=new HistoryWord();
                    historyWord.setId(c.getInt(0));
                    historyWord.setWord(c.getString(1));
                    historyWord.setTranslate(c.getString(2));
                    historyWord.setPhonetic(c.getString(3));
                    historylist.add(historyWord);
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

    public List queryCollection(){

        List historylist=new ArrayList<HistoryWord>();

        SQLiteDatabase db=mDbHelper.getReadableDatabase();

        String[] projection={

                HistoryQueryContract.HistoryQueryEntry._ID,
                HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_WORD,
                HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_TRANSLATE,
                HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_PHONETIC,
        };

        final String selection= HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_COLLECTION+"= ?";
        //读取指定数据
        final String[] selectionArgs ={"1"};
        Cursor c=db.query(
                HistoryQueryContract.HistoryQueryEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );


        try{
            if (c.moveToFirst()){
                do {
                    HistoryWord historyWord=new HistoryWord();
                    historyWord.setId(c.getInt(0));
                    historyWord.setWord(c.getString(1));
                    historyWord.setTranslate(c.getString(2));
                    historyWord.setPhonetic(c.getString(3));
                    historylist.add(historyWord);
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


    public HistoryWord hasWord(String word){

        SQLiteDatabase db=mDbHelper.getReadableDatabase();

        HistoryWord historyWord=new HistoryWord();

        String[] projection={
                HistoryQueryContract.HistoryQueryEntry._ID,
                HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_WORD,
                HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_TRANSLATE,
                HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_PHONETIC,
                HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_EXPLAINS
        };

        final String selection= HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_WORD+"= ? ";


        final String[] selectionArgs=new String[1];
        selectionArgs[0]=word;

        Cursor c=db.query(
                HistoryQueryContract.HistoryQueryEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        try{
            if (c.moveToFirst()){
                historyWord.setId(c.getInt(0));
                historyWord.setWord(c.getString(1));
                historyWord.setTranslate(c.getString(2));
                historyWord.setPhonetic(c.getString(3));
                historyWord.setExplains(c.getString(4));
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
        return historyWord;
    }

    public void deleteById(int id){

        String selection= HistoryQueryContract.HistoryQueryEntry._ID+HistoryReaderDbHelper.EQUAL+id;
        SQLiteDatabase db=mDbHelper.getWritableDatabase();
        db.delete(HistoryQueryContract.HistoryQueryEntry.TABLE_NAME,selection,null);

    }

    public void deleteByWord(String word){

        String selection= HistoryQueryContract.HistoryQueryEntry.COLUMN_NAME_WORD +" = ?";
        String[] selectionArgs = { word };
        SQLiteDatabase db=mDbHelper.getWritableDatabase();
        int count=db.delete(HistoryQueryContract.HistoryQueryEntry.TABLE_NAME,selection,selectionArgs);
        Log.d("HistoryQuerylmpl", String.valueOf(count));

    }

    public void deleteDateall(){

        SQLiteDatabase db=mDbHelper.getReadableDatabase();
        db.execSQL(HistoryReaderDbHelper.SQL_DELETE);

    }

}
