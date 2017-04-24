package com.netlibrary;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Retrofit URL封装
 * Created by xs on 2016/11/3.
 */
public interface Params{
    
    /**
     * 查询资料 如登陆 等请求操作
     * @param url 请求URL地址
     * @param param 请求参数
     * @return rxJava对象
     */
    @GET()
    Observable<ResponseBody> paramsGet(@Url String url, @QueryMap Map<String, String> param);

    /**
     * 
     * 创建新资源文件 如注册 等请求操作
     * @param url 请求URL地址
     * @param param 请求参数
     * @return 处理请求的rxJava事件源对象
     */
    @FormUrlEncoded  //不包含文件上传 对应FieldMap注解
    @POST()
    Observable<ResponseBody> paramsPost(@Url String url, @FieldMap Map<String, String> param);

    /**
     * 创建新资源文件 如上传头像 等请求操作
     * @param url 请求URL地址
     * @param param 请求参数
     * @return 处理请求的rxJava事件源对象
     */
    @Multipart  //文件上传注解 对应PartMap注解
    @POST()
    Observable<ResponseBody> paramsPostUpload(@Url String url, @PartMap Map<String,RequestBody> param);

    /**
     * 资源修改 如修改个人资料 等请求操作
     * @param url 请求URL地址
     * @return 处理请求的rxJava事件源对象
     */
    @FormUrlEncoded
    @PUT
    Observable<ResponseBody> paramsPut(@Url String url, @FieldMap Map<String, String> param);
    
    /**
     * 资源修改 如修改个人资料 等请求操作
     * @param url 请求URL地址
     * @return 处理请求的rxJava事件源对象
     */
    @Multipart
    @PUT
    Observable<ResponseBody> paramsPutUpload(@Url String url, @PartMap Map<String,RequestBody> param);

    /**
     * 资源删除 如退出登录 等请求操作
     * @param url 请求URL地址
     * @return 处理请求的rxJava事件源对象
     */
    @FormUrlEncoded
    @DELETE
    Observable<ResponseBody> paramsDelete(@Url String url, @FieldMap Map<String, String> param);
    
    
}
