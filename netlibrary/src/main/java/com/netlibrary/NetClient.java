package com.netlibrary;

import android.content.Context;

import com.google.gson.Gson;
import com.netlibrary.cache.ACache;
import com.netlibrary.cookie.CookiesManager;
import com.netlibrary.https.OkHttpSSLSocketFactory;
import com.netlibrary.interceptor.HttpLoggingInterceptor;
import com.netlibrary.listener.OnResultListener;
import com.netlibrary.util.AppUtil;
import com.netlibrary.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * OkHttp网络请求封装类
 * 
 * Created by XiaoSai on 2016/10/23.
 */

public class NetClient{
    private String mBaseUrl= "";
    private Builder mBuilder;
    private String mCacheKey;
    private String mCache;
    private Retrofit mRetrofit;
    private Observable<ResponseBody> mCall;
    private Gson mGson;
    private static Context mContext;
    private OkHttpClient mClient;

    private NetClient(){
        if(mContext == null){
            throw new IllegalArgumentException("context can't be null");
        }

        //日志拦截配置
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        mClient = new OkHttpClient.Builder()
                .connectTimeout(10000L,TimeUnit.MILLISECONDS)       //设置连接超时
                .readTimeout(10000L,TimeUnit.MILLISECONDS)          //设置读取超时
                .writeTimeout(10000L, TimeUnit.MILLISECONDS)         //设置写入超时
                .sslSocketFactory(OkHttpSSLSocketFactory.getSocketFactory(),OkHttpSSLSocketFactory.getTrustManager())
                .hostnameVerifier(OkHttpSSLSocketFactory.getHostnameVerifier())
                .cookieJar(new CookiesManager(mContext))
                .cache(new Cache(mContext.getCacheDir(),10 * 1024 * 1024))   //设置缓存目录和10M缓存
                .addInterceptor(interceptor)    //添加日志拦截器（该方法也可以设置公共参数，头信息）
                .build();

        mGson = new Gson();
    }
    
    private static NetClient getInstance(){
        return SingleLoader.INSTANCE;
    }
    private static class SingleLoader{
        public static NetClient INSTANCE = new NetClient();
    }
    private void initData(Builder builder) {
        this.mBuilder = builder;
        this.mCacheKey = StringUtil.buffer(builder.url, builder.params.toString());
        this.mCache= ACache.get(mContext).getAsString(mCacheKey);
        
        //如果和上次baseUrl不同，则重新创建retrofit
        if(!mBaseUrl.equals(builder.baseUrl)){
            mBaseUrl = builder.baseUrl;
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(mBaseUrl)
                    .client(mClient)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())   //添加RxJava
                    .build();
        }
        
    }

    /**
     * GET请求
     */
    public <T> void get(final OnResultListener<T> onResultListener){
        if (onResultListener.onCache((T)mCache)) return;
        if (parseCache(mCache,onResultListener)) return;
        if (!AppUtil.isNetworkAvailable(mContext)) {
            handlerError(new ConnectException("无法连接网络!"),onResultListener);
            return;
        }
        mCall = mRetrofit.create(Params.class)
                .paramsGet(mBuilder.url, mBuilder.params);
        request(onResultListener);
    }
    
    /**
     * POST请求
     * @param onResultListener 响应监听
     */
    public <T> void post(final OnResultListener<T> onResultListener){
        if (onResultListener.onCache((T)mCache)) return;
        if (parseCache(mCache,onResultListener)) return;
        if (!AppUtil.isNetworkAvailable(mContext)) {
            handlerError(new ConnectException("无法连接网络!"),onResultListener);
            return;
        }
        mCall = mRetrofit.create(Params.class)
                .paramsPost(mBuilder.url, mBuilder.params);
        request(onResultListener);
    }

    /**
     * post upload上传图片请求
     */
    public <T> void postUpload(final OnResultListener<T> onResultListener){
        if (onResultListener.onCache((T)mCache)) return;
        if (parseCache(mCache,onResultListener)) return;
        if (!AppUtil.isNetworkAvailable(mContext)) {
            handlerError(new ConnectException("无法连接网络!"),onResultListener);
            return;
        }
        //Post传参
        for(String key : mBuilder.params.keySet()){
            RequestBody value = RequestBody.create(MediaType.parse("text/plain"), mBuilder.params.get(key));
            mBuilder.files.put(key,value);
        }
        mCall = mRetrofit.create(Params.class)
                .paramsPostUpload(mBuilder.url, mBuilder.files);
        request(onResultListener);
    }

    /**
     * put请求
     * @param onResultListener 响应监听
     */
    public <T> void put(final OnResultListener<T> onResultListener){
        if (onResultListener.onCache((T)mCache)) return;
        if (parseCache(mCache,onResultListener)) return;
        if (!AppUtil.isNetworkAvailable(mContext)) {
            handlerError(new ConnectException("无法连接网络!"),onResultListener);
            return;
        }
        mCall = mRetrofit.create(Params.class)
                .paramsPut(mBuilder.url, mBuilder.params);
        request(onResultListener);
    }

    /**
     * put upload上传图片请求
     */
    public <T> void putUpload(final OnResultListener<T> onResultListener){
        if (onResultListener.onCache((T)mCache)) return;
        if (parseCache(mCache,onResultListener)) return;
        if (!AppUtil.isNetworkAvailable(mContext)) {
            handlerError(new ConnectException("无法连接网络!"),onResultListener);
            return;
        }
        //Post传参
        for(String key : mBuilder.params.keySet()){
            RequestBody value = RequestBody.create(MediaType.parse("text/plain"), mBuilder.params.get(key));
            mBuilder.files.put(key,value);
        }
        mCall = mRetrofit.create(Params.class)
                .paramsPutUpload(mBuilder.url, mBuilder.files);
        request(onResultListener);
    }

    /**
     * delete请求
     * @param onResultListener 响应监听
     */
    public <T> void delete(final OnResultListener<T> onResultListener){
        if (onResultListener.onCache((T)mCache)) return;
        if (parseCache(mCache,onResultListener)) return;
        if (!AppUtil.isNetworkAvailable(mContext)) {
            handlerError(new ConnectException("无法连接网络!"),onResultListener);
            return;
        }
        mCall = mRetrofit.create(Params.class)
                .paramsDelete(mBuilder.url, mBuilder.params);
        request(onResultListener);
    }
    

    /**
     * 请求
     * @param onResultListener 响应监听
     */
    private <T> void request(final OnResultListener<T> onResultListener){
        //访问网络切换异步线程
        mCall.subscribeOn(Schedulers.io())
            //响应结果处理切换成主线程
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<ResponseBody>(){
                @Override
                public void onCompleted(){
                }

                @Override
                public void onError(Throwable e){
                    e.printStackTrace();
                    handlerError(e ,onResultListener);
                }

                @Override
                public void onNext(ResponseBody response){
                    try {
                        String result = response.string();
                        ACache.get(mContext).put(mCacheKey, result);
                        parseSuccess(result,onResultListener);
                    } catch (IOException e) {
                        e.printStackTrace();
                        handlerError(e ,onResultListener);
                    }
                }
            });
    }

    /**
     * 处理网络异常
     * @param err
     * @param onResultListener
     */
    private <T> void handlerError(Throwable err, OnResultListener<T> onResultListener){
        parseFails(err, mCache, onResultListener);
    }

    /**
     * 缓存数据转换处理
     * @param json
     * @param onResultListener
     * @return
     */
    private <T> boolean parseCache(String json,OnResultListener<T> onResultListener){
        return onResultListener.onCache((T)mGson.fromJson(mCache,onResultListener.type));
    }

    /**
     * 失败数据转换处理
     * @param err
     * @param json
     * @param onResultListener
     * @param <T>
     */
    private <T> void parseFails(Throwable err, String json, OnResultListener<T> onResultListener){
        onResultListener.onFailure(err,(T)mGson.fromJson(mCache,onResultListener.type));
        onResultListener.onFailure(err);
    }

    /**
     * 成功数据转换
     * @param json
     * @param onResultListener
     * @param <T>
     */
    private <T> void parseSuccess(String json,OnResultListener<T> onResultListener){
        onResultListener.onSuccess((T)mGson.fromJson(json,onResultListener.type));
    }

    /**
     * 建造类 
     */
    public static final class Builder{
        private String baseUrl = "";
        private String url = "";
        private Map<String,String> params = new ConcurrentHashMap<>(); //ConcurrentHashMap线程安全,ArrayMap是牺牲了时间换区空间,数据小用HashMap,数据大用ArrayMap
        private Map<String,RequestBody> files = new ConcurrentHashMap<>();
        
        public Builder(Context bdContext){ mContext = bdContext; }
        
        public Builder baseUrl(String baseUrl){
            this.baseUrl = baseUrl;
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

        public Builder params(Map<String,String> maps){
            params.putAll(maps);
            return this;
        }

        public Builder files(Map<String,File> mapFiles){
            for(String key : mapFiles.keySet()){
                files.put(key+"\"; filename=\""+mapFiles.get(key).getName(), RequestBody.create(MediaType.parse("application/octet-stream"), mapFiles.get(key)));
            }
            return this;
        }

        public Builder file(String key, File file){
            files.put(key+"\"; filename=\""+file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file));
            return this;
        }
        
        public NetClient build(){            
            NetClient.getInstance().initData(this);
            return NetClient.getInstance();
        }
    }
}
