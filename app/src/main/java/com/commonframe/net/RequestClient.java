package com.commonframe.net;

import com.commonframe.App;
import com.netlibrary.NetClient;
import com.netlibrary.listener.OnResultListener;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO 网络请求接口类
 *
 * @author XiaoSai
 * @version V1.0
 * @since 2017/3/7.
 */

public class RequestClient{

    private static class SingleLoader{
        private final static RequestClient INSTANCE = new RequestClient();
    }

    public static RequestClient getInstance(){
        return  SingleLoader.INSTANCE;
    }
    
    private static NetClient.Builder builder;

    /**
     * get请求接口
     */
    public <T>  void get(final OkHttpResponseListener<T> listener){
        handlerCommonParam(builder);
        builder.build()
                .get(new OnResultListener<T>(listener.clazz){
                    @Override
                    public void onSuccess(T content){
                        handlerIntercept(content);
                        listener.onSuccess(content);
                    }
                    @Override
                    public void onFailure(Throwable err){
                        listener.onFailure(err);
                    }
                   
                });
    }

    /**
     * post请求接口
     */
    public <T> void post(final OkHttpResponseListener<T> listener){
        handlerCommonParam(builder);
        builder.build()
                .post(new OnResultListener<T>(listener.clazz){
                    @Override
                    public void onSuccess(T content){
                        handlerIntercept(content);
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
                });
    }

    /**
     * 文件上传接口
     */
    public <T> void postUpload(final OkHttpResponseListener<T> listener){
        handlerCommonParam(builder);
        builder.build()
                .postUpload(new OnResultListener<T>(listener.clazz){
                    @Override
                    public void onSuccess(T content){
                        handlerIntercept(content);
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
                });
    }

    /**
     * 处理公共参数
     */
    private void handlerCommonParam(NetClient.Builder builder){
        builder.param("uid","10000524");
        builder.param("os","2");//1:ios 2:android
        builder.param("app_ver","32");//版本编号
        builder.param("timestamp", "" + System.currentTimeMillis() / 1000);//时间戳
    }

    /**
     * 拦截处理成功回调
     */
    private <T> void handlerIntercept(T content){
        try {
//            JSONObject jsonObj = new JSONObject(content+"");
//            int ret = jsonObj.getInt("ret");
//            //如果返回20008表示cookie过期，弹出登录框
//            if (ret == 20008) {
//                
//            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static class Builder{
        private String baseUrl;
        private String url;
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
            params.put(key,value);
            return this;
        }

        public Builder file(String key, File file){
            files.put(key,file);
            return this;
        }

        public RequestClient builder(){
            builder = new NetClient.Builder(App.getContext())
                    .baseUrl(baseUrl)
                    .url(url)
                    .params(params)
                    .files(files);

            return RequestClient.getInstance();
        }
    }
}
