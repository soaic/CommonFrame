package com.netlibrary.listener;

/**
 * 网络请求监听
 * Created by XiaoSai on 2016/11/3.
 */
public class OnResultListener<T> {
    
    /** 是否缓存 true缓存 false不缓存 默认不缓存*/
    public boolean onCache(String cache) {//返回true,表示不再请求网络
        return false;
    }

    /** 是否缓存 true缓存 false不缓存 默认不缓存*/
    public boolean onCache(T object) {//返回true,表示不再请求网络
        return false;
    }

    /** 请求成功*/
    public void onSuccess(String result) {}

    /** 请求成功*/
    public void onSuccess(T object) {}

    /** 请求失败 根据需求实现*/
    public void onFailure(String message) {} 
    
    /** 请求失败 根据需求实现，当请求异常时，需要使用缓存时，调用此方法*/
    public void onFailure(String cache, String message) {} 

    /** 请求失败 根据需求实现，当请求异常时，需要使用缓存时，调用此方法*/
    public void onFailure(T cache, String message) {} 
    
}
