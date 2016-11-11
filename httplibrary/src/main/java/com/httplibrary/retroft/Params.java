package com.httplibrary.retroft;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
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
 * 
 * Created by xs on 2016/11/3.
 */
public interface Params{
    @FormUrlEncoded
    @POST("{filePath}")
    Observable<ResponseBody> paramsPost(@Path("filePath") String filePath,@FieldMap Map<String, String> param);
    
    @GET("{filePath}")
    Observable<ResponseBody> paramsGet(@Path("filePath") String filePath,@QueryMap Map<String, String> param);

    @POST("{filePath}")
    @Multipart
    Observable<ResponseBody> paramsUpload(@Path("filePath") String filePath,@QueryMap Map<String,String> options,
                              @PartMap Map<String,RequestBody> files);
}
