package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.activity.WordNoteActivity;
import com.example.myapplication.bean.newsbean.NewsData;
import com.example.myapplication.bean.youdaobean.HistoryWord;
import com.example.myapplication.ui.SwipeLayout;

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

    final String DELETE="delete";

    final String VIEW="view";

    final String COLLECT="collect";

    private  OnRecyclerViewItemClickListener mItemClickListener=null;


    List<HistoryWord> historyList=new ArrayList<HistoryWord>();

    public WordListAdapter(Context context,List<HistoryWord> historyList){


        this.mContext=context;

        mInflater = LayoutInflater.from(context);

        this.historyList=historyList;


    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View v,String word,int position,String type);
    }

    public void setList(List l){

        this.historyList=l;

    }

    public List<HistoryWord> getList(){

        return historyList;
    }

    public void removelist(int position){

        this.historyList.remove(position);

    }


    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v=mInflater.inflate(R.layout.textitem,parent,false);

        return new WordViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final WordViewHolder holder, final int position) {

        if (holder instanceof WordViewHolder){

            if (historyList.get(historyList.size()-position-1).getCollection().equals("0")){
                holder.star.setImageResource(R.drawable.staroff);
            }else {
                holder.star.setImageResource(R.drawable.staron);
            }
            holder.wordtext.setText(historyList.get(historyList.size()-position-1).getWord());
            holder.translatetext.setText(historyList.get(historyList.size()-position-1).getTranslate());
            holder.phonetictext.setText(historyList.get(historyList.size()-position-1).getPhonetic());
            SwipeLayout.addSwipeView(holder.mSwipeLayout);
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener!=null){
                        SwipeLayout.removeSwipeView(holder.mSwipeLayout);
                        mItemClickListener.onItemClick(v,holder.wordtext.getText().toString(),
                                historyList.size()-position-1,DELETE);

                    }
                }
            });
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener!=null){
                        SwipeLayout.removeSwipeView(holder.mSwipeLayout);
                        mItemClickListener.onItemClick(v,holder.wordtext.getText().toString(),
                                historyList.size()-position-1,VIEW);


                    }
                }
            });

            holder.star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener!=null){

                        Log.d("wordlist","star");
                        SwipeLayout.removeSwipeView(holder.mSwipeLayout);
                        mItemClickListener.onItemClick(v,holder.wordtext.getText().toString(),
                                historyList.size()-position-1,COLLECT);
                    }
                }
            });



        }

    }


    @Override
    public int getItemCount() {

        return historyList==null?0:historyList.size();
    }



    public void setOnItemClickListener(OnRecyclerViewItemClickListener onItemClickListener) {
        mItemClickListener = onItemClickListener;
    }



    public static class WordViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.word_text)
        TextView wordtext;
        @BindView(R.id.phonetic_text)
        TextView phonetictext;
        @BindView(R.id.translate_text)
        TextView translatetext;
        @BindView(R.id.delete_button)
        ImageView delete;
        @BindView(R.id.view_button)
        ImageView view;
        @BindView(R.id.swipe)
        SwipeLayout mSwipeLayout;
        @BindView(R.id.star_button)
        ImageView star;



        public WordViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }

}

