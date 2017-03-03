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
import java.util.HashMap;
import java.util.Map;
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
    private static String BASE_URL = "";
    private Builder mBuilder;
    private String cacheKey;
    private String mCache;
    private Retrofit retrofit;
    private Observable<ResponseBody> mCall;
    public static final int OBJECT = 1;//返回数据为json对象
    public static final int STRING = 0;//返回数据为字符串
    private Gson gson;
    private static Context context;

    private NetClient(){
        if(context == null){
            throw new IllegalArgumentException("context can't be null");
        }

        //日志拦截配置
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10000L,TimeUnit.MILLISECONDS)       //设置连接超时
                .readTimeout(10000L,TimeUnit.MILLISECONDS)          //设置读取超时
                .writeTimeout(10000L, TimeUnit.MILLISECONDS)         //设置写入超时
                .sslSocketFactory(OkHttpSSLSocketFactory.getSocketFactory(),OkHttpSSLSocketFactory.getTrustManager())
                .hostnameVerifier(OkHttpSSLSocketFactory.getHostnameVerifier())
                .cookieJar(new CookiesManager(context))
                .cache(new Cache(context.getCacheDir(),10 * 1024 * 1024))   //设置缓存目录和10M缓存
                .addInterceptor(interceptor)    //添加日志拦截器（该方法也可以设置公共参数，头信息）
                .build();

        gson = new Gson();
        
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())   //添加RxJava
                .build();
    }
    
    private static NetClient getInstance(){
        return SingleLoader.INSTANCE;
    }
    
    private static class SingleLoader{
        public static NetClient INSTANCE = new NetClient();
    }    

    private void setBuilder(Builder builder) {
        this.mBuilder = builder;
        this.cacheKey = StringUtil.buffer(builder.url, builder.params.toString());
        this.mCache= ACache.get(context).getAsString(cacheKey);
    }
    
    /**
     * POST请求
     *  @param onResultListener 响应监听
     */
    public void post(final OnResultListener onResultListener){
        
        if (onResultListener.onCache(mCache)) return;
        if (parseCache(mCache,onResultListener)) return;
        if (!AppUtil.isNetworkAvailable(context)) {
            handlerError("无法连接网络",onResultListener);
            return;
        }

        mCall =retrofit.create(Params.class)
                .paramsPost(mBuilder.url, mBuilder.params);

        request(onResultListener);
    }

    /**
     * GET请求
     */
    public void get(final OnResultListener onResultListener){
        if (onResultListener.onCache(mCache)) return;

        if (parseCache(mCache,onResultListener)) return;

        if (!AppUtil.isNetworkAvailable(context)) {
            handlerError("无法连接网络",onResultListener);
            return;
        }

        mCall =retrofit.create(Params.class)
                .paramsGet(mBuilder.url, mBuilder.params);
        
        request(onResultListener);
    }

    /**
     * upload上传图片请求
     */
    public void upload(final OnResultListener onResultListener){
        if (onResultListener.onCache(mCache)) return;

        if (parseCache(mCache,onResultListener)) return;

        if (!AppUtil.isNetworkAvailable(context)) {
            handlerError("无法连接网络",onResultListener);
            return;
        }

        mCall =retrofit.create(Params.class)
                .paramsUpload(mBuilder.url, mBuilder.params,mBuilder.files);

        request(onResultListener);
    }
    

    /**
     * 请求
     * @param onResultListener 响应监听
     */
    private void request(final OnResultListener onResultListener){
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
                    handlerError("网络繁忙，请稍后重试！",onResultListener);
                }

                @Override
                public void onNext(ResponseBody response){
                    try {
                        String result = response.string();
                        ACache.get(context).put(cacheKey, result);
                        parseJson(result,onResultListener);
                    } catch (IOException e) {
                        e.printStackTrace();
                        handlerError("处理数据失败!",onResultListener);
                    }
                }
            });
    }

    /**
     * 处理网络异常
     * @param message
     * @param onResultListener
     */
    private void handlerError(String message,OnResultListener onResultListener){
        onResultListener.onFailure(message);
        onResultListener.onFailure(mCache,message);
        parseFails(mCache,message,onResultListener);
    }

    /**
     * 缓存处理
     * @param json
     * @param onResultListener
     * @return
     */
    private boolean parseCache(String json,OnResultListener onResultListener){
        switch (mBuilder.bodyType){
            case OBJECT:
                return onResultListener.onCache(gson.fromJson(mCache,mBuilder.clazz));
            case STRING:
                return onResultListener.onCache(json);
            default:
                return false;
        }
    }

    private void parseFails(String json,String error,OnResultListener onResultListener){
        switch (mBuilder.bodyType){
            case OBJECT:
                onResultListener.onFailure(gson.fromJson(mCache,mBuilder.clazz),error);
                break;
            case STRING:
                onResultListener.onFailure(json,error);
                break;
        }
    }

    private void parseJson(String json,OnResultListener onResultListener){
        switch (mBuilder.bodyType){
            case OBJECT:
                onResultListener.onSuccess(gson.fromJson(json,mBuilder.clazz));
                break;
            case STRING:
                onResultListener.onSuccess(json);
                break;
        }
    }

    public static final class Builder{
        private String baseUrl = "";
        private int bodyType = 0;//返回数据的类型,默认字符串
        private String url = "";
        private Map<String,String> params = new HashMap<>(); //ArrayMap是牺牲了时间换区空间,数据小用HashMap,数据大用ArrayMap
        private Map<String,RequestBody> files = new HashMap<>();
        private boolean hasShowLoading = false;
        private Class clazz; 
        
        public Builder(Context bdContext){ context = bdContext; }
        
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

        public Builder file(String key, File file){
            files.put(key+"\"; filename=\""+file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file));
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
        
        public NetClient build(){
            BASE_URL = baseUrl;
            handlerCommonParam();
            NetClient.getInstance().setBuilder(this);
            return NetClient.getInstance();
        }

        /**
         * 处理公共参数
         */
        private void handlerCommonParam(){
            params.put("uid","10000524");
            params.put("os","2");//1:ios 2:android
            params.put("app_ver","32");//版本编号
            params.put("timestamp", "" + System.currentTimeMillis() / 1000);//时间戳
        }
    }


     

}
