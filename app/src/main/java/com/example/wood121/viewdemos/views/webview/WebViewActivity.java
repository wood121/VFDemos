package com.example.wood121.viewdemos.views.webview;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;

public class WebViewActivity extends BaseActivity {
    private WebView mWebView;
    private String mUrl;

    @Override
    protected void initData(Bundle savedInstanceState) {
        mUrl = "http://baidu.com";
    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initPageViewListener() {
        mWebView = findViewById(R.id.webview);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mWebView.setWebContentsDebuggingEnabled(true);
        }
        mWebView.getSettings().setJavaScriptEnabled(true);  //设置WebView属性，能够执行Javascript脚本
        mWebView.loadUrl(mUrl);               //加载需要显示的网页

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(mUrl);
                return true;
            }
        });
    }
}
