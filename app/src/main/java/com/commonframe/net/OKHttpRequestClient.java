package com.commonframe.net;


import android.text.TextUtils;

import com.commonframe.App;
import com.netlibrary.NetClient;
import com.netlibrary.listener.OnResultListener;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO 网络请求接口类
 *
 * @author XiaoSai
 * @version V1.0
 * @since 2017/3/7.
 */

public class OKHttpRequestClient{

    private static class SingleLoader{
        private final static OKHttpRequestClient INSTANCE = new OKHttpRequestClient();
    }

    public static OKHttpRequestClient getInstance(){
        return  SingleLoader.INSTANCE;
    }
    
    private static NetClient.Builder builder;
    
    private NetClient netClient;
    
    public void init(){
        if(builder!=null)
            netClient = builder.build();
    }
    /**
     * get请求接口
     */
    public <T>  void get(final OkHttpResponseListener<T> listener){
        netClient = builder.build()
                .get(new OnResultListener<T>(listener.clazz){
                    @Override
                    public void onSuccess(T content){
                        listener.onSuccess(content);
                    }
                    @Override
                    public void onFailure(Throwable err){
                        listener.onFailure(err);
                    }

                    @Override
                    public void onSuccessJson(String json){
                        listener.onSuccessJson(json);
                    }
                });
    }

    /**
     * post请求接口
     */
    public <T> void post(final OkHttpResponseListener<T> listener){
        netClient = builder.build()
                .post(new OnResultListener<T>(listener.clazz){
                    @Override
                    public void onSuccess(T content){
                        listener.onSuccess(content);
                    }
                    @Override
                    public void onFailure(Throwable err){
                        listener.onFailure(err);
                    }
                    @Override
                    public boolean onCache(T cache){
                        return listener.onCache(cache);
                    }
                    @Override
                    public void onFailure(Throwable err,T cache){
                        super.onFailure(err,cache);
                        listener.onFailure(err,cache);
                    }
                    @Override
                    public void onSuccessJson(String json){
                        listener.onSuccessJson(json);
                    }
                });
    }

    /**
     * 文件上传接口
     */
    public <T> void postUpload(final OkHttpResponseListener<T> listener){
        netClient = builder.build()
                .postUpload(new OnResultListener<T>(listener.clazz){
                    @Override
                    public void onSuccess(T content){
                        listener.onSuccess(content);
                    }
                    @Override
                    public void onFailure(Throwable err){
                        listener.onFailure(err);
                    }
                    @Override
                    public boolean onCache(T cache){
                        return listener.onCache(cache);
                    }
                    @Override
                    public void onFailure(Throwable err,T cache){
                        super.onFailure(err,cache);
                        listener.onFailure(err,cache);
                    }
                    @Override
                    public void onSuccessJson(String json){
                        listener.onSuccessJson(json);
                    }
                });
    }

    /**
     * 取消网络请求
     */
    public void cancelRequest(){
        if(netClient!=null)
            netClient.cancleRequest();
    }

    /**
     * 清除Cookies
     */
    public void cleanCookies(){
        if(netClient!=null){
            netClient.getCookiesManager().cleanCookies();
        }
    }

    /**
     * 获取Cookies大小
     * @return
     */
    public int getCookiesSize(){
        int size = 0;
        if(netClient!=null){
            return netClient.getCookiesManager().getCookies().size();
        }
        return size;
    }
    
    public static class Builder{
        private String baseUrl;
        private String url;
        //线程安全的ConcurrentHashMap key和value都不能为null
        private ConcurrentHashMap<String, String> params = new ConcurrentHashMap<>();
        private ConcurrentHashMap<String, File> files = new ConcurrentHashMap<>();

        public  Builder baseUrl(String baseUrl){
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder url(String  url){
            this.url = url;
            return this;
        }

        public Builder param(String key, String value){
            if(key!=null&&value!=null){
                params.put(key,value);
            }
            return this;
        }

        public Builder param(String key, String value, boolean isAdd){
            if(isAdd&&key!=null&&value!=null){
                params.put(key,value);
            }
            return this;
        }

        public Builder params(Map<String, String> maps){
            if(maps!=null&&!maps.containsKey(null)&&!maps.containsValue(null)){
                params.putAll(maps);
            }
            return this;
        }

        public Builder file(String key, File file){
            if(!TextUtils.isEmpty(key)&&file!=null&&file.exists()){
                files.put(key,file);
            }
            return this;
        }

        public OKHttpRequestClient builder(){
            
            //处理BaseUrl为null
            try{
                if(TextUtils.isEmpty(baseUrl)&&!TextUtils.isEmpty(url)){
                    baseUrl = url.substring(0,url.lastIndexOf("/")+1);
                    url = url.substring(url.lastIndexOf("/")+1);
                }
            }catch(Exception e){ e.printStackTrace(); }
            
            
            builder = new NetClient.Builder(App.getContext())
                    .baseUrl(baseUrl)
                    .url(url)
                    .params(params)
                    .files(files);
            handlerCommonParam(builder);
            return OKHttpRequestClient.getInstance();
        }

        /**
         * 处理公共参数
         */
        private void handlerCommonParam(NetClient.Builder builder){
            
            builder.param("uid", "10000524");
            builder.param("user_id", "10000524");
            builder.param("user_type", "1");
            
            builder.param("os", "2"); //os类型（1 ios 2 android）
            builder.param("app_ver", "35");
            builder.param("push_id", "10010"); // (百度云推送的)推送的channel_id
            builder.param("timestamp", "" + System.currentTimeMillis() / 1000);
        }
        
    }
}
