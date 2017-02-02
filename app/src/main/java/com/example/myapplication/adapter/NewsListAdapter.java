package com.example.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.myapplication.R;
import com.example.myapplication.bean.newsbean.NewsData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 李思言 on 2017/1/19.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> implements View.OnClickListener {

    private final LayoutInflater mInflater;
    private final Context mContext;
    List<NewsData> mNewsDataslist=new ArrayList<>();

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;



    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view,NewsData newsData);
    }

    public NewsListAdapter( Context context){

        this.mContext = context;
         mInflater = LayoutInflater.from(context);
    }


    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view=mInflater.inflate(R.layout.juhenewsitem,parent,false);

        view.setOnClickListener(this);

        return  new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, int position) {

        holder.newstitle.setText(mNewsDataslist.get(position).getTitle());
        holder.newsauthor.setText(mNewsDataslist.get(position).getAuthor_name());
        holder.newsdata.setText(mNewsDataslist.get(position).getDate());
        Glide.with(mContext)
             .load(mNewsDataslist.get(position).getThumbnail_pic_s())
             .listener(new RequestListener<String, GlideDrawable>() {
                 @Override
                 public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                     Log.e("NewsListAdapter",e.toString()+" model"+model+" isFirstResource: "+isFirstResource);
                     holder.newsimage.setImageResource(R.mipmap.ic_launcher);
                     return false;
                 }

                 @Override
                 public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                     Log.e("NewsListAdapter","isFromMemoryCache:"+isFromMemoryCache+"  model:"+model+" isFirstResource: "+isFirstResource);
                     return false;
                 }
             })
             .into(holder.newsimage);
        holder.itemView.setTag(mNewsDataslist.get(position));
    }

    @Override
    public int getItemCount() {
        return mNewsDataslist.size();
    }


    public void addItems(ArrayList<NewsData> list) {
        mNewsDataslist.addAll(list);
        notifyDataSetChanged();
    }


    public void setOnItemClickListener(OnRecyclerViewItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View view) {

        if (mOnItemClickListener!=null){

            mOnItemClickListener.onItemClick(view, (NewsData) view.getTag());
        }

    }



    public static class NewsViewHolder extends RecyclerView.ViewHolder{


        @BindView(R.id.news_image)
        ImageView newsimage;
        @BindView(R.id.news_author)
        TextView newsauthor;
        @BindView(R.id.news_title)
        TextView newstitle;
        @BindView(R.id.news_data)
        TextView newsdata;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
