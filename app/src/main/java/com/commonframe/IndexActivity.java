package com.commonframe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.commonframe.glide.GlideDemoActivity;
import com.commonframe.greendao.GreenDaoDemoActivity;
import com.commonframe.material.MaterialActivity;
import com.commonframe.mvp.ui.activity.MainActivity;

public class IndexActivity extends Activity{
    private TextView index_mvp;
    private TextView index_glide;
    private TextView index_green_dao;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        index_mvp = (TextView)findViewById(R.id.index_mvp);
        index_glide = (TextView)findViewById(R.id.index_glide);
        index_green_dao = (TextView)findViewById(R.id.index_green_dao);

        index_mvp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        index_glide.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(),GlideDemoActivity.class));
            }
        });
        index_green_dao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(),GreenDaoDemoActivity.class));
            }
        });
        
        findViewById(R.id.index_material_design).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(),MaterialActivity.class));
            }
        });
    }
}
