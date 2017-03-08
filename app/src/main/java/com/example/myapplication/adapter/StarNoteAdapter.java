package com.example.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.bean.youdaobean.HistoryWord;
import com.example.myapplication.ui.SwipeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by lsy on 2017/2/20.
 */

public class StarNoteAdapter extends RecyclerView.Adapter<StarNoteAdapter.StarNoteHolder>{

    private final Context mContext;

    private final LayoutInflater mInflater;

    final String DELETE="delete";

    final String VIEW="view";


    private OnRecyclerViewItemClickListener mItemClickListener=null;


    List<HistoryWord> historyList=new ArrayList<HistoryWord>();

    public  StarNoteAdapter(Context context,List<HistoryWord> historyList) {

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

    public void removelist(int position){

        this.historyList.remove(position);

    }



    @Override
    public StarNoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v=mInflater.inflate(R.layout.star_list,parent,false);

        return new StarNoteHolder(v);
    }

    @Override
    public void onBindViewHolder(final StarNoteHolder holder, final int position) {

        if (holder instanceof StarNoteHolder){

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
        }

    }


    @Override
    public int getItemCount() {
        return historyList==null?0:historyList.size();
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener onItemClickListener) {
        mItemClickListener = onItemClickListener;
    }

    public static class StarNoteHolder extends RecyclerView.ViewHolder{

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


        public StarNoteHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
