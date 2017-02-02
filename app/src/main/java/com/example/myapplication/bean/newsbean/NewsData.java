package com.example.myapplication.bean.newsbean;

/**
 * Created by 李思言 on 2017/1/19.
 */

public class NewsData {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    private String thumbnail_pic_s;

    public String getUrl() {
        return url;
    }

    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public String getAuthor_name() {
        return author_name;
    }

    private String url;

    private String author_name;

    public String getDate() {
        return date;
    }

    private String date;


    @Override
    public String toString() {

        return "title="+getTitle()+"author_name="+getAuthor_name();
    }
}
