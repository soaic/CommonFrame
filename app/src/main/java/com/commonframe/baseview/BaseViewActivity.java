package com.commonframe.baseview;

import android.os.Bundle;

import com.commonframe.R;
import com.commonframe.base.BaseActivity;

public class BaseViewActivity extends BaseActivity{

    @Override
    protected void initVariables(Bundle savedInstanceState){
        
    }

    @Override
    protected void initViews(Bundle savedInstanceState){
        setContentView(R.layout.activity_base_view);
        setTopTitleText("BaseView Demo");
        
        
    }

    @Override
    protected void loadData(){

    }
}
