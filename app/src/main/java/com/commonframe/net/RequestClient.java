package com.commonframe.net;

import com.commonframe.App;
import com.netlibrary.NetClient;
import com.netlibrary.listener.OnResultListener;

import org.json.JSONObject;

import java.io.File;
import java.util.Map;

/**
 * TODO 网络请求接口类
 *
 * @author XiaoSai
 * @version V1.0
 * @since 2017/3/7.
 */

public class RequestClient{
    
    private static NetClient.Builder builder;

    /**
     * get请求接口
     * @param baseUrl url前缀
     * @param url url接口后缀
     * @param maps 请求文本参数
     */
    public static <T>  void get(String baseUrl,String url,Map<String,String> maps, final OkHttpResponseListener<T> listener){
        builder = new NetClient.Builder(App.getContext())
                .baseUrl(baseUrl)
                .url(url)
                .params(maps);
        handlerCommonParam(builder);
        builder.build()
                .get(new OnResultListener<T>(){
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
     * @param baseUrl url前缀
     * @param url url接口后缀
     * @param maps 请求文本参数
     */
    public static <T> void post(String baseUrl, String url, Map<String,String> maps, final OkHttpResponseListener<T> listener){
        builder = new NetClient.Builder(App.getContext())
                .baseUrl(baseUrl)
                .url(url)
                .params(maps);
        handlerCommonParam(builder);
        builder.build()
                .post(new OnResultListener<T>(){
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
     * @param baseUrl url前缀
     * @param url url接口后缀
     * @param maps 请求文本参数
     * @param files 请求文件参数
     */
    public static <T> void postUpload(String baseUrl, String url, Map<String,String> maps,
                                  Map<String,File> files, final OkHttpResponseListener<T> listener){
        builder = new NetClient.Builder(App.getContext())
                .baseUrl(baseUrl)
                .url(url)
                .params(maps)
                .files(files);
        handlerCommonParam(builder);
        builder.build()
                .postUpload(new OnResultListener<T>(){
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
    private static void handlerCommonParam(NetClient.Builder builder){
        builder.param("uid","10000524");
        builder.param("os","2");//1:ios 2:android
        builder.param("app_ver","32");//版本编号
        builder.param("timestamp", "" + System.currentTimeMillis() / 1000);//时间戳
    }

    /**
     * 拦截处理成功回调
     */
    private static <T> void handlerIntercept(T content){
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
}
