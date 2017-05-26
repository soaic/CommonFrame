package com.netlibrary.listener;

/**
 * 网络请求监听
 * Created by XiaoSai on 2016/11/3.
 */
public abstract class OnResultListener<T> {

    public Class<T> clazz;

    public OnResultListener(Class<T> cs){
        this.clazz = cs;
    }

    /** 是否缓存 true缓存 false不缓存 默认不缓存*/
    public boolean onCache(T cache){
        return false;
    }

    /** 请求成功*/
    public abstract void onSuccess(T content);

    /** 请求成功 返回JSON*/
    public void onSuccessJson(String json){}

    /** 请求失败 根据需求实现*/
    public abstract void onFailure(Throwable err);

    /** 请求失败 根据需求实现，当请求异常时，需要使用缓存时，调用此方法*/
    public void onFailure(Throwable err,T cache){}

}
