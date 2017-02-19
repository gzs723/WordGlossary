package com.example.myapplication.activity;

import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.myapplication.R;
import com.example.myapplication.bean.newsbean.JuHeNewsBean;
import com.example.myapplication.presenter.implPresenter.NewsWebPresenterImpl;
import com.example.myapplication.presenter.implView.INewsFragment;
import com.example.myapplication.presenter.implView.INewsWebActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 李思言 on 2017/1/20.
 */

public class NewsWebViewActivity extends BaseActivity implements INewsWebActivity {

    NewsWebPresenterImpl mPresenter;
    private String url;

    @BindView(R.id.juhe_news_webview)
    WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_web);
        ButterKnife.bind(this);

        url=getIntent().getStringExtra("url");

        initData();

    }

    private void initWebView(String html){

        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        mWebView.setVerticalScrollBarEnabled(false);
//        表示不支持js，如果想让java和js交互或者本身希望js完成一定的功能请把false改为true。
        mWebView.getSettings().setJavaScriptEnabled(true);
//        设置是否支持缩放，我这里为false，默认为true。
        mWebView.getSettings().setSupportZoom(false);
//        设置是否显示缩放工具，默认为false。
        mWebView.getSettings().setBuiltInZoomControls(false);

        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        设置默认的字体大小，默认为16，有效值区间在1-72之间。
        mWebView.getSettings().setDefaultFontSize(25);

        mWebView.setWebViewClient(new WebViewClient());

        mWebView.getSettings().setDefaultTextEncodingName("utf-8") ;

        mWebView.loadData(html,"text/html; charset=utf-8",null );


    }

    private void initData(){
        mPresenter=new NewsWebPresenterImpl(this);
        mPresenter.getNews(url);
    }


    @Override
    public void updateNews(String html) {


        //okhttp 线程通过rx切换回主线程
        initWebView(html);

    }
}
