package com.commonframe.base;

/**
 * Created by XiaoSai on 2016/11/10.
 */

public abstract class MVPBasePresenter<T>{
    public T mView;
    public void attach(T view){
        this.mView = view;
    }
    public void detach(){
        mView = null;
    }
}
