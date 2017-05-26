package com.commonframe.net;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;

/**
 * TODO 接口拦截器
 *
 * @author XiaoSai
 * @version V1.0
 * @since 2017/3/8.
 */

public class OkHttpResponseListenerIntercept<T> extends OkHttpResponseListener<T>{
    public Class <T> clazz;
    
    public OkHttpResponseListenerIntercept(){
        clazz = (Class <T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void onSuccess(T content){
    }

    @Override
    public void onFailure(Throwable err){
    }

    @Override
    public void onSuccessJson(String json){
        super.onSuccessJson(json);
        //数据拦截
        try {
            JSONObject jsonObj = new JSONObject(json);
            if(jsonObj.has("ret")){
                int ret = jsonObj.getInt("ret");
                
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
