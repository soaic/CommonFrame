package com.commonframe.glide;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.commonframe.R;
import com.commonframe.base.BaseActivity;
import com.commonlibrary.ScreenUtils;
import com.imagelibrary.ImageUtils;

public class PhotoActivity extends BaseActivity{
    
    private ImageView photo_iv_photo;
    
    @Override
    protected void initVariables(Bundle savedInstanceState){
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void initViews(Bundle savedInstanceState){
        setContentView(R.layout.activity_photo);
        hideTopView();

        photo_iv_photo = getViewById(R.id.photo_iv_photo);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenUtils.getScreenWidth(this),ScreenUtils.getScreenWidth(this));
        photo_iv_photo.setLayoutParams(params);

        ImageUtils.getInstance().source(this,"https://avatars.githubusercontent.com/u/6779547").display(photo_iv_photo);
    }

    @Override
    protected void loadData(){

    }
}
