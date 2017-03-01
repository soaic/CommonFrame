package com.commonframe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.commonframe.base.BaseActivity;
import com.commonlibrary.BarUtils;

public class LoadingActivity extends BaseActivity{
    private RelativeLayout rl_layout;

    @Override
    protected void initVariables(Bundle savedInstanceState){
        
    }

    @Override
    protected void initViews(Bundle savedInstanceState){
        setContentView(R.layout.activity_loading);
        hideTopView();
        rl_layout = getViewById(R.id.rl_layout);
        BarUtils.setTransparentStatusBar(this);
        rl_layout.startAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_enter_alpha));
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                startActivity(new Intent(LoadingActivity.this,IndexActivity.class));
                finish();
            }
        },3000);
    }

    @Override
    protected void loadData(){

    }
}
