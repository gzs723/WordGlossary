package com.example.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.bean.youdaobean.HistoryWord;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 李思言 on 2016/11/25.
 */

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {


    private final Context mContext;

    private final LayoutInflater mInflater;


    List<HistoryWord> historyList=new ArrayList<HistoryWord>();

    public WordListAdapter(Context context,List<HistoryWord> historyList){


        this.mContext=context;

        mInflater = LayoutInflater.from(context);

        this.historyList=historyList;


    }

    public void setList(List l){

        this.historyList=l;

    }


    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new WordViewHolder(mInflater.inflate(R.layout.textitem,parent,false));
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {

        if (holder instanceof WordViewHolder){


            Log.d("wordadapter","word"+historyList.get(position).getWord());
            holder.wordtext.setText(historyList.get(historyList.size()-position-1).getWord());
            holder.translatetext.setText(historyList.get(historyList.size()-position-1).getTranslate());

        }

    }


    @Override
    public int getItemCount() {

        return historyList==null?0:historyList.size();
    }

    public static class WordViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.word_text)
        TextView wordtext;
        @BindView(R.id.phonetic_text)
        TextView phonetictext;
        @BindView(R.id.translate_text)
        TextView translatetext;

        public WordViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }

}

