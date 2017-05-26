package com.netlibrary;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.netlibrary.cache.ACache;
import com.netlibrary.cookie.CookiesManager;
import com.netlibrary.https.OkHttpSSLSocketFactory;
import com.netlibrary.interceptor.HttpLoggingInterceptor;
import com.netlibrary.listener.OnResultListener;
import com.netlibrary.util.AppUtil;
import com.netlibrary.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
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
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * TODO OkHttp网络请求封装类
 * @author XiaoSai
 * @version V1.0
 * @since 2016/10/23.
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
    private Subscription subscription;
    private CookiesManager cookiesManager;
    private OkHttpClient.Builder okHttpBuilder;
    private HttpLoggingInterceptor interceptor;

    private NetClient(){
        if(mContext == null){
            throw new IllegalArgumentException("context can't be null");
        }
        //日志拦截配置
        interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //cookies管理
        cookiesManager = new CookiesManager(mContext);
        okHttpBuilder = new OkHttpClient.Builder()
                .connectTimeout(10000L,TimeUnit.MILLISECONDS)       //设置连接超时
                .readTimeout(10000L,TimeUnit.MILLISECONDS)          //设置读取超时
                .writeTimeout(10000L, TimeUnit.MILLISECONDS)         //设置写入超时
                .sslSocketFactory(OkHttpSSLSocketFactory.getSocketFactory(),OkHttpSSLSocketFactory.getTrustManager())
                .hostnameVerifier(OkHttpSSLSocketFactory.getHostnameVerifier())
                .cookieJar(cookiesManager)
                .cache(new Cache(mContext.getCacheDir(),10 * 1024 * 1024))   //设置缓存目录和10M缓存
                .addInterceptor(interceptor);    //添加日志拦截器（该方法也可以设置公共参数，头信息）
        mClient = okHttpBuilder.build();
        mGson = new Gson();
    }

    /**
     * 设置日志级别
     */
    public void setLoggingLevel(HttpLoggingInterceptor.Level level){
        interceptor.setLevel(level);
    }

    /**
     * 获取Cookies管理
     */
    public CookiesManager getCookiesManager(){
        return cookiesManager;
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
            try{
                mRetrofit = new Retrofit.Builder()
                        .baseUrl(mBaseUrl)
                        .client(mClient)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())   //添加RxJava
                        .build();
            }catch(Exception ignored){}

        }

    }

    /**
     * GET请求
     */
    public <T> NetClient get(final OnResultListener<T> onResultListener){
        if (onResultListener.onCache((T)mCache)) return this;
        if (parseCache(mCache,onResultListener)) return this;
        if (!AppUtil.isNetworkAvailable(mContext)) {
            handlerError(new ConnectException("无法连接网络!"),onResultListener);
            return this;
        }
        if(mRetrofit!=null){
            mCall = mRetrofit.create(Params.class)
                    .paramsGet(mBuilder.url,mBuilder.params);
            request(onResultListener);
        }
        return this;
    }

    /**
     * POST请求
     * @param onResultListener 响应监听
     */
    public <T> NetClient post(final OnResultListener<T> onResultListener){
        if (onResultListener.onCache((T)mCache)) return this;
        if (parseCache(mCache,onResultListener)) return this;
        if (!AppUtil.isNetworkAvailable(mContext)) {
            handlerError(new ConnectException("无法连接网络!"),onResultListener);
            return this;
        }
        if(mRetrofit!=null){
            mCall = mRetrofit.create(Params.class)
                    .paramsPost(mBuilder.url,mBuilder.params);
            request(onResultListener);
        }
        return this;
    }

    /**
     * post upload上传图片请求
     */
    public <T> NetClient postUpload(final OnResultListener<T> onResultListener){
        if (onResultListener.onCache((T)mCache)) return this;
        if (parseCache(mCache,onResultListener)) return this;
        if (!AppUtil.isNetworkAvailable(mContext)) {
            handlerError(new ConnectException("无法连接网络!"),onResultListener);
            return this;
        }
        //Post传参
        for(String key : mBuilder.params.keySet()){
            RequestBody value = RequestBody.create(MediaType.parse("text/plain"), mBuilder.params.get(key));
            mBuilder.files.put(key,value);
        }
        if(mRetrofit!=null){
            mCall = mRetrofit.create(Params.class)
                    .paramsPostUpload(mBuilder.url,mBuilder.files);
            request(onResultListener);
        }
        return this;
    }

    /**
     * put请求
     * @param onResultListener 响应监听
     */
    public <T> NetClient put(final OnResultListener<T> onResultListener){
        if (onResultListener.onCache((T)mCache)) return this;
        if (parseCache(mCache,onResultListener)) return this;
        if (!AppUtil.isNetworkAvailable(mContext)) {
            handlerError(new ConnectException("无法连接网络!"),onResultListener);
            return this;
        }
        if(mRetrofit!=null){
            mCall = mRetrofit.create(Params.class)
                    .paramsPut(mBuilder.url,mBuilder.params);
            request(onResultListener);
        }
        return this;
    }

    /**
     * put upload上传图片请求
     */
    public <T> NetClient putUpload(final OnResultListener<T> onResultListener){
        if (onResultListener.onCache((T)mCache)) return this;
        if (parseCache(mCache,onResultListener)) return this;
        if (!AppUtil.isNetworkAvailable(mContext)) {
            handlerError(new ConnectException("无法连接网络!"),onResultListener);
            return this;
        }
        //Post传参
        for(String key : mBuilder.params.keySet()){
            RequestBody value = RequestBody.create(MediaType.parse("text/plain"), mBuilder.params.get(key));
            mBuilder.files.put(key,value);
        }
        if(mRetrofit!=null){
            mCall = mRetrofit.create(Params.class)
                    .paramsPutUpload(mBuilder.url,mBuilder.files);
            request(onResultListener);
        }
        return this;
    }

    /**
     * delete请求
     * @param onResultListener 响应监听
     */
    public <T> NetClient delete(final OnResultListener<T> onResultListener){
        if (onResultListener.onCache((T)mCache)) return this;
        if (parseCache(mCache,onResultListener)) return this;
        if (!AppUtil.isNetworkAvailable(mContext)) {
            handlerError(new ConnectException("无法连接网络!"),onResultListener);
            return this;
        }
        if(mRetrofit!=null){
            mCall = mRetrofit.create(Params.class)
                    .paramsDelete(mBuilder.url,mBuilder.params);
            request(onResultListener);
        }
        return this;
    }

    /**
     * 取消网络请求
     */
    public void cancleRequest(){
        if(subscription!=null&&!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }

    /**
     * 请求
     * @param onResultListener 响应监听
     */
    private <T> void request(final OnResultListener<T> onResultListener){
        if(mCall==null){
            return;
        }
        //访问网络切换异步线程
        subscription = mCall.subscribeOn(Schedulers.io())
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
                            if(ResponseBody.class.getName().equals(onResultListener.clazz.getName())){
                                onResultListener.onSuccess((T)response);
                            }else{
                                String result = response.string();
                                ACache.get(mContext).put(mCacheKey, result);
                                parseSuccess(result,onResultListener);
                            }
                        } catch (Exception e) {
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
    private <T> void handlerError(Throwable err,OnResultListener<T> onResultListener){
        parseFails(err, mCache, onResultListener);
    }

    /**
     * 缓存数据转换处理
     * @param json
     * @param onResultListener
     * @return
     */
    private <T> boolean parseCache(String json,OnResultListener<T> onResultListener){
        if(String.class.getName().equals(onResultListener.clazz.getName())){
            return onResultListener.onCache((T)json);
        }else{
            try{
                return onResultListener.onCache(mGson.fromJson(json,onResultListener.clazz));
            }catch(Exception e){
                return false;
            }

        }
    }

    /**
     * 失败数据转换处理
     * @param err
     * @param json
     * @param onResultListener
     * @param <T>
     */
    private <T> void parseFails(Throwable err,String json,OnResultListener<T> onResultListener){
        try{
            if(String.class.getName().equals(onResultListener.clazz.getName())){
                onResultListener.onFailure(err,(T)json);
            }else{
                onResultListener.onFailure(err,mGson.fromJson(json,onResultListener.clazz));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            onResultListener.onFailure(err);
        }
    }

    /**
     * 成功数据转换
     * @param json
     * @param onResultListener
     * @param <T>
     */
    private <T> void parseSuccess(String json,OnResultListener<T> onResultListener){
        if(String.class.getName().equals(onResultListener.clazz.getName())){
            onResultListener.onSuccess((T)json);
            onResultListener.onSuccessJson(json);
        }else{
            try{
                onResultListener.onSuccess(mGson.fromJson(json,onResultListener.clazz));
            }catch(Exception e){
                onResultListener.onFailure(e);
            }
            onResultListener.onSuccessJson(json);
        }
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
            if(key!=null&&value!=null){
                params.put(key,value);
            }
            return this;
        }

        public Builder params(Map<String,String> maps){
            if(maps != null){
                for(String key : maps.keySet()){
                    if(key != null && maps.get(key) != null){
                        params.put(key, maps.get(key));
                    }
                }
            }
            return this;
        }

        public Builder files(Map<String,File> mapFiles){
            if(mapFiles != null){
                for(String key : mapFiles.keySet()){
                    if(key != null && mapFiles.get(key) != null){
                        files.put(key + "\"; filename=\"" + mapFiles.get(key).getName(),RequestBody.create(MediaType.parse("application/octet-stream"),mapFiles.get(key)));
                    }
                }
            }
            return this;
        }

        public Builder file(String key,File file){
            if(key!=null&&file!=null){
                files.put(key + "\"; filename=\"" + file.getName(),RequestBody.create(MediaType.parse("application/octet-stream"),file));
            }
            return this;
        }

        public NetClient build(){
            NetClient.getInstance().initData(this);
            return NetClient.getInstance();
        }
    }
}
