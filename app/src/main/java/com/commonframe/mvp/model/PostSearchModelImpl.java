package com.commonframe.mvp.model;

import com.commonframe.common.C;
import com.commonframe.mvp.entity.PostQueryInfo;
import com.commonframe.mvp.model.impl.PostSearchModel;
import com.commonframe.net.OkHttpResponseListener;
import com.commonframe.net.RequestClient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

        Map<String,String> maps = new ConcurrentHashMap<>();
        maps.put("type",type);
        maps.put("postid",postid);
        RequestClient.get(C.impl.base_url,C.impl.search_post,maps,new OkHttpResponseListener<PostQueryInfo>(){
            @Override
            public void onSuccess(PostQueryInfo content){
                callback.requestPostSearchSuccess(content);
            }

            @Override
            public void onFailure(Throwable err){
                callback.requestPostSearchFail(err.getMessage());
            }
        });
    }
}
