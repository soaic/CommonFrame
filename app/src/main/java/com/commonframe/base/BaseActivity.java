package com.commonframe.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

/**
 * Created by XiaoSai on 2016/11/10.
 */

public class BaseActivity extends Activity implements BaseView{
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("查询中...");
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