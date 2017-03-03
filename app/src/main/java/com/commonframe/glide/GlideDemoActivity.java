package com.commonframe.glide;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.commonframe.R;
import com.commonframe.base.BaseActivity;
import com.commonlibrary.FileUtils;
import com.imagelibrary.ImageUtils;
import com.imagelibrary.glide.DiskCacheSizeTask;

/**
 * Glide图片框架Demo
 * @author XiaoSai
 */
public class GlideDemoActivity extends BaseActivity{
    private ImageView image_view;
    private TextView glide_cache_tv;

    @Override
    protected void initVariables(Bundle savedInstanceState){

    }

    @Override
    protected void initViews(Bundle savedInstanceState){
        setContentView(R.layout.activity_glide_demo);
        setTopTitleText("Glide Demo");
        image_view = getViewById(R.id.image_view);
        glide_cache_tv = getViewById(R.id.glide_cache_tv);
        //显示图片
        ImageUtils.getInstance().source(this,"https://avatars.githubusercontent.com/u/6779547").display(image_view);
        //获取缓存大小
        ImageUtils.getInstance().getDataDiskCacheSize(getApplicationContext(),new DiskCacheSizeTask.CacheReceiveListener(){
            @Override
            public void onSuccess(Long sizeBytes){
                //格式化
                String str = FileUtils.formatFileSize(getApplicationContext(),sizeBytes);
                glide_cache_tv.setText("缓存大小："+str);
            }

            @Override
            public void onFail(String str){
            }
        });

        image_view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launch();
            }
        });
    }

    @Override
    protected void loadData(){

    }

    private void launch() {
//        Pair<View,String> imagePair = Pair.create((View)image_view, getString(R.string.app_name));
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, image_view, getString(R.string.app_name));
        Intent intent = new Intent(this, PhotoActivity.class);
        ActivityCompat.startActivity(this, intent , compat.toBundle());
    }

    private void scaleUpAnimation(View view) {
        //让新的Activity从一个小的范围扩大到全屏
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeScaleUpAnimation(view, //The View that the new activity is animating from
                        (int)view.getWidth()/2, (int)view.getHeight()/2, //拉伸开始的坐标
                        0, 0);//拉伸开始的区域大小，这里用（0，0）表示从无到全屏
        startNewAcitivity(options);
    }

    private void startNewAcitivity(ActivityOptionsCompat options) {
        Intent intent = new Intent(this,PhotoActivity.class);
        ActivityCompat.startActivity(this, intent, options.toBundle());
    }
}
