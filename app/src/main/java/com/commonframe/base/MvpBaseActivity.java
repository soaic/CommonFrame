package com.commonframe.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by XiaoSai on 2016/11/10.
 */

public abstract class MVPBaseActivity extends Activity implements MVPBaseView{
    private ProgressDialog progressDialog;

    @Override
    public void setContentView(int layoutResID){
        super.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view,ViewGroup.LayoutParams params){
        super.setContentView(view,params);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        
        initCommon();
        initVariables(savedInstanceState);
        initViews(savedInstanceState);
        loadData();
    }

    /**
     * 变量初始化
     * @param savedInstanceState
     */
    protected abstract void initVariables(Bundle savedInstanceState);

    /**
     * view初始化
     * @param savedInstanceState
     */
    protected abstract void initViews(Bundle savedInstanceState);
    
    /**
     * 加载数据
     */
    protected abstract void loadData();



    /**
     * 公共初始化
     */
    private void initCommon(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("查询中...");
    }

    /**
     * 查找View
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) findViewById(id);
    }
    
    
    @Override
    public void showProgressDialog(){
        if(progressDialog!=null){
            progressDialog.show();
        }
    }
    @Override
    public void hideProgressDialog(){
        if(progressDialog!=null&&progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}
