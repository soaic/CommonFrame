package com.netlibrary;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Retroft URL封装
 * Created by xs on 2016/11/3.
 */
public interface Params{
    
    @FormUrlEncoded
    @POST("{urlPath}")
    Observable<ResponseBody> paramsPost(@Path("urlPath") String filePath,@FieldMap Map<String, String> param);
    
    @GET("{urlPath}")
    Observable<ResponseBody> paramsGet(@Path("urlPath") String filePath,@QueryMap Map<String, String> param);

    @POST("{urlPath}")
    @Multipart
    Observable<ResponseBody> paramsUpload(@Path("urlPath") String filePath,@QueryMap Map<String,String> options,
                              @PartMap Map<String,RequestBody> files);
}
