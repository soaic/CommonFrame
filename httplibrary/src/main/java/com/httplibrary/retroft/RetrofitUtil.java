package com.httplibrary.retroft;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.ArrayMap;

import com.google.gson.Gson;
import com.httplibrary.base.JinLib;
import com.httplibrary.cache.ACache;
import com.httplibrary.listener.OnResultListener;
import com.httplibrary.util.AppUtil;
import com.httplibrary.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Http请求类
 * Created by soaic on 2016/10/23.
 */

public class RetrofitUtil{
    private static String BASE_URL="";
    private Builder mBuilder;
    private String cacheKey;
    private String mCache;
    private Retrofit retrofit;
    private Call<ResponseBody> mCall;
    public static final int OBJECT=1;//返回数据为json对象
    public static final int ARRAY=2;//返回数据为数组
    private Gson gson;

    private RetrofitUtil(){
        OkHttpClient client = new OkHttpClient();
        client.newBuilder()
                .connectTimeout(10000L,TimeUnit.MILLISECONDS)       //设置连接超时
                .readTimeout(10000L,TimeUnit.MILLISECONDS)          //设置读取超时
                .writeTimeout(10000L, TimeUnit.MILLISECONDS)         //设置写入超时
                .cache(new Cache(JinLib.getContext().getCacheDir(),10 * 1024 * 1024));   //设置缓存目录和10M缓存
        
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .build();
        
        gson = new Gson();
    }
    
    private RetrofitUtil(Builder builder){
        this.mBuilder = builder;
    }
    
    public static RetrofitUtil getInstance(){
        return SingleLoader.INSTANCE;
    }
    private static class SingleLoader{
        private static final RetrofitUtil INSTANCE = new RetrofitUtil();
    }

    public Builder getBuilder() {
        return mBuilder;
    }

    public void setBuilder(Builder builder) {
        this.mBuilder = builder;
        this.cacheKey = StringUtil.buffer(builder.url, builder.params.toString());
        mCache= ACache.get().getAsString(cacheKey);
    }

    /**
     * POST请求
     *  @param onResultListener
     */
    public void post(final OnResultListener onResultListener){
        
        if (onResultListener.onCache(mCache)) return;
        if (parseCache(mCache,onResultListener)) return;
        if (!AppUtil.isNetworkAvailable()) {
            handlerError("无法连接网络",onResultListener);
            return;
        }

        mCall =retrofit.create(Params.class)
                .params(mBuilder.url, mBuilder.params);

        request(onResultListener);
    }

    /**
     * GET请求
     */
    public void get(final OnResultListener onResultListener){
        if (onResultListener.onCache(mCache)) return;

        if (parseCache(mCache,onResultListener)) return;

        if (!AppUtil.isNetworkAvailable()) {
            handlerError("无法连接网络",onResultListener);
            return;
        }

        if (!mBuilder.params.isEmpty()){
            String value="";
            String span="";
            for (Map.Entry<String, String> entry :
                    mBuilder.params.entrySet()) {
                String key = entry.getKey();
                String val = entry.getValue();
                if (!value.equals(""))span="&";
                String par=StringUtil.buffer(span,key,"=",val);
                value=StringUtil.buffer(value,par);
            }
            mBuilder.url(StringUtil.buffer(mBuilder.url,"?",value));
        }
    }

    /**
     * 请求
     * @param onResultListener
     */
    private void request(final OnResultListener onResultListener){
        mCall.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call,Response<ResponseBody> response){
                System.out.print("成功==>" + response.code());
                if (response.code() == 200) {
                    try {
                        String result=response.body().string();
                        ACache.get().put(cacheKey, result);
                        onResultListener.onSuccess(result);
                        parseJson(result,onResultListener);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if (response.code() > 400){
                    handlerError("请求数据失败！",onResultListener);
                }else if (response.code() > 500){
                    handlerError("服务器繁忙，请稍后重试",onResultListener);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call,Throwable t){
                handlerError("网络繁忙，请稍后重试！",onResultListener);
            }
        });
    }

    private void handlerError(String message,OnResultListener onResultListener){
        onResultListener.onFailure(message);
        onResultListener.onFailure(mCache,message);
        parseFails(mCache,message,onResultListener);
    }
    
    private boolean parseCache(String json,OnResultListener onResultListener){
        switch (mBuilder.bodyType){
            case OBJECT:
                return onResultListener.onCache(gson.fromJson(mCache,mBuilder.clazz));
            case ARRAY:
                return onResultListener.onCache(gson.fromJson(json,mBuilder.clazz));
            default:
                return false;
        }
    }

    private void parseFails(String json,String error,OnResultListener onResultListener){
        switch (mBuilder.bodyType){
            case OBJECT:
                onResultListener.onFailure(gson.fromJson(mCache,mBuilder.clazz),error);
                break;
            case ARRAY:
                onResultListener.onFailure(gson.fromJson(json,mBuilder.clazz),error);
                break;
        }
    }

    private void parseJson(String json,OnResultListener onResultListener){
        switch (mBuilder.bodyType){
            case OBJECT:
                onResultListener.onSuccess(gson.fromJson(json,mBuilder.clazz));
                break;
            case ARRAY:
                onResultListener.onSuccess(gson.fromJson(json,mBuilder.clazz));
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static final class Builder{
        private String baseUrl = "";
        private int bodyType = 0;//返回数据的类型,因为前期返回是字符串，为了避免修改
        private String url;
        private Map<String,String> params = new ArrayMap<>(); //ArrayMap是牺牲了时间换区空间,数据小用HashMap,数据大用ArrayMap
        private Map<String,File> files = new ArrayMap<>();
        private boolean hasShowLoading = false;
        private Class clazz; 
        
        public Builder(){}
        
        public Builder baseUrl(String baseUrl){
            this.baseUrl = url;
            return this;
        }
        
        public Builder url(String url){
            this.url = url;
            return this;
        }
        
        public Builder param(String key,String value){
            params.put(key,value);
            return this;
        }

        public Builder file(String key, File file){
            files.put(key,file);
            return this;
        }

        public <T> Builder bodyType(int bodyType,Class<T> clazz) {
            this.bodyType = bodyType;
            this.clazz = clazz;
            return this;
        }

        public Builder hasShowLoading(boolean hasShowLoading) {
            this.hasShowLoading = hasShowLoading;
            return this;
        }
        
        public RetrofitUtil build(){
            BASE_URL = baseUrl;
            RetrofitUtil retrofitUtil= RetrofitUtil.getInstance();
            retrofitUtil.setBuilder(this);
            return getInstance();
        }
    }


    

}
