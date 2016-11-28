package com.example.myapplication.adapter;

import android.content.Context;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.db.HistoryQuery.HistoryQuerylmpl;
import com.example.myapplication.util.HistoryUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 李思言 on 2016/11/25.
 */

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {


    private final Context mContext;

    private final LayoutInflater mInflater;

    HistoryUtil historyUtil=HistoryUtil.getInstance();

    public WordListAdapter(Context context){


        this.mContext=context;

        mInflater = LayoutInflater.from(context);


    }


    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new WordViewHolder(mInflater.inflate(R.layout.textitem,parent,false));
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {

        if (holder instanceof WordViewHolder){

            holder.wordtext.setText(historyUtil.getHistoryMap(position).get("word").toString());
            holder.translatetext.setText(historyUtil.getHistoryMap(position).get("translate").toString());

        }

    }


    @Override
    public int getItemCount() {

        return historyUtil.getHistoryList().size();
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

