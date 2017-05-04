package com.commonframe.mvp.model;

import com.commonframe.common.C;
import com.commonframe.mvp.entity.PostQueryInfo;
import com.commonframe.mvp.model.impl.PostSearchModel;
import com.commonframe.net.OkHttpResponseListener;
import com.commonframe.net.OKHttpRequestClient;

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
        new OKHttpRequestClient.Builder()
                .baseUrl(C.impl.base_url)
                .url(C.impl.search_post)
                .param("type",type)
                .param("postid",postid)
                .builder()
                .get(new OkHttpResponseListener<PostQueryInfo>(){
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
