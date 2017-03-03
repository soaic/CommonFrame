package com.commonframe.base;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.commonframe.R;

import java.lang.reflect.Field;

/**
 * 网页加载页面基类
 * 
 * @version V1.0
 * @since 2017.03.01
 * @author XiaoSai
 */
public abstract class BaseWebActivity extends BaseActivity{
    private FrameLayout web_fragment;
    protected WebView web_view;
    private ProgressBar web_view_progress;
    
    private Handler handler = new Handler();
    private String pageTitle = "";
    private String url = "";

    public void setUrl(String url){
        this.url = url;
    }

    public void setPageTitle(String pageTitle){
        this.pageTitle = pageTitle;
    }

    @Override
    protected void initVariables(Bundle savedInstanceState){
        
        initWebVariables(savedInstanceState);
    }

    @Override
    protected void initViews(Bundle savedInstanceState){
        super.setContentView(R.layout.activity_web_browser);
        setConfigCallback((WindowManager)getApplicationContext().getSystemService(WINDOW_SERVICE));
        web_view = getViewById(R.id.web_view);
        web_view_progress = getViewById(R.id.web_view_progress);
        web_fragment = getViewById(R.id.web_fragment);
        
        if(!TextUtils.isEmpty(pageTitle)){
            setTopTitleText(pageTitle);
        }
        if(!TextUtils.isEmpty(url)){
            web_view.loadUrl(url);
        }

        initWebView();

        initWebViews(savedInstanceState);
    }

    @Override
    protected void loadData(){
        loadWebData();
    }

    /**
     * 变量初始化
     * @param savedInstanceState
     */
    protected abstract void initWebVariables(Bundle savedInstanceState);

    /**
     * view初始化
     * @param savedInstanceState
     */
    protected abstract void initWebViews(Bundle savedInstanceState);

    /**
     * 加载数据
     */
    protected abstract void loadWebData();
    

    @Override
    public void setContentView(@LayoutRes int layoutResID){
        web_fragment.addView(LayoutInflater.from(this).inflate(layoutResID,null));
    }

    @Override
    public void setContentView(View view,ViewGroup.LayoutParams params){
        web_fragment.addView(view,params);
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
        
        //添加h5与原生交互接口
        web_view.addJavascriptInterface(new ShareJavaScriptInterface(), "shareClick");
    }

    /**
     * 原生与h5交互接口
     */
    class ShareJavaScriptInterface {
        ShareJavaScriptInterface() {}
        @JavascriptInterface
        public void winxin(final String shareTitle, final String shareContent,
                           final String shareLogo, final String shareClickLink) {
            handler.post(new Runnable() {
                public void run() {
                }
            });
        }
        @JavascriptInterface
        public void winxinCircle(final String shareTitle, final String shareContent,
                                 final String shareLogo, final String shareClickLink) {
            handler.post(new Runnable() {
                public void run() {
                }
            });
        }
    }


    /**
     * 彻底关闭WebView 防止WebView 造成OOM
     * @param windowManager
     */
    private void setConfigCallback(WindowManager windowManager){
        try{
            Field field = WebView.class.getDeclaredField("mWebViewCore");
            field = field.getType().getDeclaredField("mBrowserFrame");
            field = field.getType().getDeclaredField("sConfigCallback");
            field.setAccessible(true);
            Object configCallback = field.get(null);

            if(null == configCallback){
                return;
            }

            field = field.getType().getDeclaredField("mWindowManager");
            field.setAccessible(true);
            field.set(configCallback,windowManager);
        }catch(Exception e){
        }
    }

    /**
     * 监听网页进度和接收标题内容通知
     */
    class MyWebChromeClient extends WebChromeClient{
        public boolean onJsAlert(WebView view,String url,String message,JsResult result){
            return true;
        }

        public void onProgressChanged(WebView view,int newProgress){
            super.onProgressChanged(view,newProgress);
            web_view_progress.setProgress(newProgress);
            if(newProgress == 100){
                handler.postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        web_view_progress.setVisibility(View.GONE);
                    }
                },1000);
            }else{
                if(web_view_progress.getVisibility() == View.GONE)
                    web_view_progress.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onReceivedTitle(WebView view,String title){
            super.onReceivedTitle(view,title);
            if(TextUtils.isEmpty(pageTitle)){
                setTopTitleText(title);
            }

        }
    }

    /**
     * 设置不调用系统浏览器，只在当前页面跳转
     */
    class MyWebViewClient extends WebViewClient{
        public boolean shouldOverrideUrlLoading(WebView view,String url){
            // 加载网页内链接
            view.loadUrl(url);
            return true;
        }

        public void onReceivedSslError(WebView view,SslErrorHandler handler,android.net.http.SslError error){
            handler.proceed();
        }

        public boolean shouldOverrideKeyEvent(WebView view,KeyEvent event){
            return super.shouldOverrideKeyEvent(view,event);
        }

        public void onLoadResource(WebView view,String url){
            super.onLoadResource(view,url);
        }

        public void onPageStarted(WebView view,String url,Bitmap favicon){
            super.onPageStarted(view,url,favicon);
        }

        public void onPageFinished(WebView view,String url){
            super.onPageFinished(view,url);
        }
    }

    public boolean onKeyDown(int keyCode,KeyEvent event){
        if((keyCode == KeyEvent.KEYCODE_BACK) && web_view.canGoBack()){
            //web_view.goBack();
            finish();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    @Override
    protected void onDestroy(){
        setConfigCallback(null);
        super.onDestroy();
        web_view.removeAllViews();
        web_view.destroy();
    }
}
