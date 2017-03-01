package com.commonframe.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.commonframe.R;

/**
 * Activity基类
 * 1. 获取view对象自动转换类型 getViewById() 
 * 2. 分解onCreate方法 initVariables() initViews() loadData
 * 3. 提供头部所有操作方法 
 * 4. 提供隐藏头部方法 hideTopView() 默认显示 
 * 5. 提供请求错误点击重试功能 handleRequestError()
 * Created by XiaoSai on 2017/2/22.
 */
public abstract class BaseActivity extends AppCompatActivity{
    /**
     * 主体内容view
     */
    private FrameLayout base_fl_content;
    /**
     * 主题内容加载view
     */
    private LinearLayout base_ll_loading;
    /**
     * 头部view
     */
    private RelativeLayout base_rl_top;
    /**
     * 头部title、头部右边文本按钮
     */
    private TextView base_tv_top_title,base_tv_top_right;
    /**
     * 头部左边图片返回按钮、头部右边图片按钮
     */
    private ImageView base_iv_top_back,base_iv_top_right;

    /**
     * 加载dialog
     */
    private Dialog loadingDialog;
    /**
     * 网络错误提示、点击重试
     */
    private LinearLayout base_ll_net_error;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        
        initVariables(savedInstanceState);
        initBaseViews();
        initViews(savedInstanceState);
        loadData();
    }

    /**
     * 基础view初始化
     */
    private void initBaseViews(){
        
        super.setContentView(R.layout.activity_base);
        
        initTopView();

        base_fl_content = getViewById(R.id.base_fl_content);
        base_ll_loading = getViewById(R.id.base_ll_loading);
        base_ll_net_error = getViewById(R.id.base_ll_net_error);
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

    @Override
    public void setContentView(@LayoutRes int layoutResID){
        base_fl_content.addView(LayoutInflater.from(this).inflate(layoutResID,null));
    }

    @Override
    public void setContentView(View view,ViewGroup.LayoutParams params){
        base_fl_content.addView(view,params);
    }

    /**
     * 根据ID查找View自动转换类型 
     * @param id   控件的id
     * @param <VT> View类型
     * @return VT
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) findViewById(id);
    }

    @Override
    @Deprecated
    public View findViewById(@IdRes int id){
        return super.findViewById(id);
    }

    /**
     * 初始化TopView
     */
    private void initTopView() {
        base_rl_top = getViewById(R.id.base_rl_top);
        base_tv_top_title = getViewById(R.id.base_tv_top_title);
        
        base_iv_top_back = getViewById(R.id.base_iv_top_back);
        base_iv_top_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
        base_iv_top_right = getViewById(R.id.base_iv_top_right);
        base_tv_top_right = getViewById(R.id.base_tv_top_right);
    }

    /**
     * 设置标题
     * @param text 文本
     */
    public void setTopTitleText(String text){
        base_tv_top_title.setText(text);
    }

    /**
     * 设置标题
     * @param rStr 文本资源
     */
    public void setTopTitleText(int rStr){
        base_tv_top_title.setText(rStr);
    }

    /**
     * 设置top返回按钮监听
     * @param listener 点击事件
     */
    public void setTopBackListener(View.OnClickListener listener){
        base_iv_top_back.setOnClickListener(listener);
    }

    /**
     * 设置top返回按钮图片
     * @param rDraw 图片资源
     */
    public void setTopBackImage(int rDraw){
        base_iv_top_back.setImageResource(rDraw);
    }

    /**
     * 设置top右边按钮文本
     * @param text 文本
     */
    public void setTopRightText(String text){
        base_tv_top_right.setVisibility(View.VISIBLE);
        base_iv_top_right.setVisibility(View.GONE);
        base_tv_top_right.setText(text);
    }

    /**
     * 设置top右边按钮文本
     * @param rStr 文本资源
     */
    public void setTopRightText(int rStr){
        base_tv_top_right.setVisibility(View.VISIBLE);
        base_iv_top_right.setVisibility(View.GONE);
        base_tv_top_right.setText(rStr);
    }

    /**
     * 设置top右边按钮图片
     * @param rDraw 图片资源
     */
    public void setTopRightImage(int rDraw){
        base_tv_top_right.setVisibility(View.GONE);
        base_iv_top_right.setVisibility(View.VISIBLE);
        base_iv_top_right.setImageResource(rDraw);
    }

    /**
     * 设置top右边按钮监听
     * @param listener 点击事件
     */
    public void setTopRightListener(View.OnClickListener listener){
        base_iv_top_right.setOnClickListener(listener);
        base_tv_top_right.setOnClickListener(listener);
    }

    /**
     * 隐藏top view
     */
    public void hideTopView(){
        base_rl_top.setVisibility(View.GONE);
    }

    /**
     * 展示LoadingView,隐藏其它view,加载中,重新加载
     */
    protected void showLoadingView(){
        base_fl_content.setVisibility(View.GONE);
        base_ll_loading.setVisibility(View.VISIBLE);
        base_ll_net_error.setVisibility(View.GONE);
    }

    /**
     * 隐藏LoadingView,展示主内容view,成功加载
     */
    protected void hideLoadingView(){
        base_fl_content.setVisibility(View.VISIBLE);
        base_ll_loading.setVisibility(View.GONE);
        base_ll_net_error.setVisibility(View.GONE);
    }

    /**
     * 显示加载对话框
     */
    public void showProgressDialog() {
        if (loadingDialog == null) {
            loadingDialog = new Dialog(this, R.style.LoadingTranslucentDialog);
            loadingDialog.setContentView(R.layout.dialog_base_loading);
            loadingDialog.setCanceledOnTouchOutside(false);
            loadingDialog.setCancelable(true);
        }

        if (!loadingDialog.isShowing())
            loadingDialog.show();
    }

    /**
     * 隐藏加载对话框
     */
    public void hideProgressDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    /**
     * 处理网络请求错误并设置点击监听,加载失败
     * @param listener 监听
     */
    public void handleRequestError(final View.OnClickListener listener){
        base_ll_net_error.setVisibility(View.VISIBLE);
        base_fl_content.setVisibility(View.GONE);
        base_ll_loading.setVisibility(View.GONE);
        base_ll_net_error.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                showLoadingView();
                listener.onClick(view);
            }
        });
    }
}
