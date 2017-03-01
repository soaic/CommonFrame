package com.commonframe.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


import com.commonframe.R;

import java.lang.reflect.Field;

/**
 * h5网页加载页面
 * create by XiaoSai 2016.4.12
 */
public class WebBrowserActivity extends BaseActivity{
    protected WebView web_view;
    private ProgressBar web_view_progress;
    public final static String INTENT_URL = "url";
    public final static String INTENT_TITLE = "title";
    private Handler handler = new Handler();

    @Override
    protected void initVariables(Bundle savedInstanceState){
        if(getIntent().hasExtra(INTENT_URL)){
            web_view.loadUrl(getIntent().getStringExtra(INTENT_URL));
        }
    }

    @Override
    protected void initViews(Bundle savedInstanceState){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_web_browser);
        setConfigCallback((WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE));

        web_view = getViewById(R.id.web_view);
        web_view_progress = getViewById(R.id.web_view_progress);

        initWebView();

        //web_view.loadUrl("file:///android_asset/index/index.html");
    }

    @Override
    protected void loadData(){

    }

    private void initWebView(){
        WebSettings webSettings = web_view.getSettings();
        //支持JS
        webSettings.setJavaScriptEnabled(true);
        //调整到适合webview的大小 
        webSettings.setUseWideViewPort(false);
        //不缩放至屏幕的大小
        webSettings.setLoadWithOverviewMode(false);
        //设置不支持屏幕缩放
        webSettings.setBuiltInZoomControls(false);
        webSettings.setSupportZoom(false);
        //关闭缓存
        webSettings.setAppCacheEnabled(false);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //不显示webview缩放按钮
        webSettings.setDisplayZoomControls(false);
        //支持自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        //取消滚动条  
        web_view.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        //触摸焦点起作用  
        web_view.requestFocus();
        //设置监听进度
        web_view.setWebChromeClient(new MyWebChromeClient());
        //设置不调用系统浏览器
        web_view.setWebViewClient(new MyWebViewClient());
        
    }
    protected void onDestroy() {
        super.onDestroy();
        web_view.removeAllViews();
        web_view.destroy();
    }
    private void setConfigCallback(WindowManager windowManager) {
        try {
            Field field = WebView.class.getDeclaredField("mWebViewCore");
            field = field.getType().getDeclaredField("mBrowserFrame");
            field = field.getType().getDeclaredField("sConfigCallback");
            field.setAccessible(true);
            Object configCallback = field.get(null);

            if (null == configCallback) {
                return;
            }

            field = field.getType().getDeclaredField("mWindowManager");
            field.setAccessible(true);
            field.set(configCallback, windowManager);
        } catch(Exception e) {
        }
    }


    class MyWebChromeClient extends WebChromeClient{
        public boolean onJsAlert(WebView view, String url, String message,JsResult result) {
            return true;
        }

        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view,newProgress);
            web_view_progress.setProgress(newProgress);
            if (newProgress == 100) {
                handler.postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        web_view_progress.setVisibility(View.GONE);
                    }
                },1000);
            } else {
                if (web_view_progress.getVisibility() == View.GONE)
                    web_view_progress.setVisibility(View.VISIBLE);
            }
        }
    }

    class MyWebViewClient extends WebViewClient{
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // 加载网页内链接
            view.loadUrl(url);
            return true;
        }

        public void onReceivedSslError(WebView view, SslErrorHandler handler,android.net.http.SslError error) {
            handler.proceed();
        }

        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return super.shouldOverrideKeyEvent(view, event);
        }

        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && web_view.canGoBack()) {
            //web_view.goBack();
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
