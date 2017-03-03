package com.commonframe.baseview;

import android.os.Bundle;

import com.commonframe.base.BaseWebActivity;

public class WebActivity extends BaseWebActivity{
    
    public final static String INTENT_URL = "url";
    public final static String INTENT_TITLE = "title";
    
    @Override
    protected void initWebVariables(Bundle savedInstanceState){
        if(getIntent().hasExtra(INTENT_TITLE)){
            String pageTitle = getIntent().getStringExtra(INTENT_TITLE);
            setPageTitle(pageTitle);
        }
        if(getIntent().hasExtra(INTENT_URL)){
            String url = getIntent().getStringExtra(INTENT_URL);
            setUrl(url);
        }
    }

    @Override
    protected void initWebViews(Bundle savedInstanceState){
        
    }

    @Override
    protected void loadWebData(){

    }
}
