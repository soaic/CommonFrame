package com.commonframe.glide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.commonframe.R;
import com.commonframe.greendao.GreenDaoDemoActivity;
import com.commonlibrary.FileUtils;
import com.imagelibrary.ImageUtils;
import com.imagelibrary.glide.DiskCacheSizeTask;

/**
 * Glide图片框架Demo
 * @author XiaoSai
 */
public class GlideDemoActivity extends Activity{
    private ImageView image_view;
    private TextView glide_cache_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_demo);

        image_view = (ImageView)findViewById(R.id.image_view);
        glide_cache_tv = (TextView)findViewById(R.id.glide_cache_tv);
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
    }
}
