package com.commonframe.mvp.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.commonframe.R;
import com.imagelibrary.ImageUtils;

public class ImageActivity extends Activity{
    private ImageView image_view;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        image_view = (ImageView)findViewById(R.id.image_view);

        ImageUtils.getInstance().source(this,"http://www.gongyebangshou.com/images/index_banner.png").display(image_view);
    }
}
