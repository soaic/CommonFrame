package com.httplibrary;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.ArrayMap;

import com.httplibrary.cache.ACache;
import com.httplibrary.util.StringUtil;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by soaic on 2016/10/23.
 */

public class HttpRequest {
    private Builder mBuilder;
    private String cacheKey;
    private String mCache;
    private Retrofit retrofit;
    private static String BASE_URL="";

    private HttpRequest(){
        OkHttpClient client = new OkHttpClient();
        client.newBuilder().connectTimeout(7000, TimeUnit.MILLISECONDS);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .build();
    }
    
    private HttpRequest(Builder builder){
        this.mBuilder = builder;
    }
    
    public static HttpRequest getInstance(){
        return SingleLoader.INSTANCE;
    }
    private static class SingleLoader{
        private static final HttpRequest INSTANCE = new HttpRequest();
    }

    public Builder getBuilder() {
        return mBuilder;
    }

    public void setBuilder(Builder builder) {
        this.mBuilder = builder;
        this.cacheKey = StringUtil.buffer(builder.url, builder.params.toString());
        mCache= ACache.get().getAsString(cacheKey);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static final class Builder{
        private String baseUrl = "";
        
        private String url;
        private Map<String,String> params = new ArrayMap<>(); //ArrayMap是牺牲了时间换区空间,数据小用HashMap,数据大用ArrayMap
        private Map<String,File> files = new ArrayMap<>();
        private boolean hasShowLoadding=false;
        
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
        
        public HttpRequest build(){
            BASE_URL = baseUrl;
            HttpRequest httpRequest= HttpRequest.getInstance();
            httpRequest.setBuilder(this);
            return getInstance();
        }
    }


    

}
