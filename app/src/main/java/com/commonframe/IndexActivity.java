package com.commonframe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.commonframe.base.BaseActivity;
import com.commonframe.baseview.BaseViewActivity;
import com.commonframe.baseview.WebActivity;
import com.commonframe.fragment.FragmentActivity;
import com.commonframe.glide.GlideDemoActivity;
import com.commonframe.greendao.GreenDaoDemoActivity;
import com.commonframe.material.MaterialActivity;
import com.commonframe.mvp.ui.activity.MainActivity;
import com.commonframe.recyclerview.RecyclerViewDemoActivity;
import com.commonframe.scroller.ScrollerActivity;
import com.commonlibrary.BarUtils;
import com.commonlibrary.ToastUtils;
import com.socketlib.SocketConnection;

public class IndexActivity extends BaseActivity{
    private TextView index_mvp;
    private TextView index_glide;
    private TextView index_green_dao;
    private TextView index_scroller;
    private TextView index_material_design;
    private TextView index_navigation_bar;
    

    @Override
    protected void initVariables(Bundle savedInstanceState){
        
        
    }

    @Override
    protected void initViews(Bundle savedInstanceState){
        showLoadingView();
        setContentView(R.layout.activity_index);
        setTopTitleText("标题");
        
        //沉浸式颜色设置
        BarUtils.setColorNoTranslucent(this,ContextCompat.getColor(this,R.color.black));
                
        index_mvp = getViewById(R.id.index_mvp);
        index_mvp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        index_glide = getViewById(R.id.index_glide);
        index_glide.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(),GlideDemoActivity.class));
            }
        });


        index_green_dao = getViewById(R.id.index_green_dao);
        index_green_dao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(),GreenDaoDemoActivity.class));
            }
        });

        index_material_design = getViewById(R.id.index_material_design);
        index_material_design.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(),MaterialActivity.class));
            }
        });

        index_scroller = getViewById(R.id.index_scroller);
        index_scroller.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(),ScrollerActivity.class));
            }
        });

        index_navigation_bar = getViewById(R.id.index_navigation_bar);
        index_navigation_bar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(),FragmentActivity.class));
            }
        });
        
        getViewById(R.id.index1).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(),BaseViewActivity.class));
            }
        });        
        
        getViewById(R.id.index2).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),WebActivity.class);
                intent.putExtra(WebActivity.INTENT_URL,"http://xiaosai.me/");
                startActivity(intent);
            }
        });
        
        getViewById(R.id.index3).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //startActivity(new Intent(getApplicationContext(),RecyclerViewDemoActivity.class));
                startActivity(new Intent(getApplicationContext(),com.commonframe.socket.SocketDemoActivity.class));
            }
        });
    }
    
    

    @Override
    protected void loadData(){
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                handleRequestError(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        new Handler().postDelayed(new Runnable(){
                            @Override
                            public void run(){
                                hideLoadingView();  
                            }
                        },1000);
                    }
                });
            }
        },1000);
    }
}
