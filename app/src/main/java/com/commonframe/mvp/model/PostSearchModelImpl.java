package com.commonframe.mvp.model;

import com.commonframe.common.C;
import com.commonframe.mvp.entity.PostQueryInfo;
import com.commonframe.mvp.model.impl.PostSearchModel;
import com.httplibrary.listener.OnResultListener;
import com.httplibrary.retroft.HttpClient;

/**
 * @author XiaoSai
 * @version V1.0.0
 * @description TODO
 * @date 2016/11/8 17:09
 * @copyright: Copyright (c) 2016
 */
public class PostSearchModelImpl implements PostSearchModel{
    @Override
    public void requestPostSearch(String type, String postid, final PostSearchCallback callback) {
        HttpClient client=new HttpClient.Builder()
                .baseUrl(C.impl.base_url)
                .url(C.impl.search_post)
                .param("type",type)
                .param("postid",postid)
                .bodyType(HttpClient.OBJECT,PostQueryInfo.class) //当要得到已经解析完成的实体类时，添加此方法即可
                .build();
        client.post(new OnResultListener<PostQueryInfo>(){
            @Override
            public void onSuccess(PostQueryInfo object){
                super.onSuccess(object);
                callback.requestPostSearchSuccess(object);
            }

            @Override
            public void onFailure(String message){
                super.onFailure(message);
                callback.requestPostSearchFail(message);
            }
        });
    }
}
