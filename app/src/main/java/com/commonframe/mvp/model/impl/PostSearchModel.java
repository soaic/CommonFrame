package com.commonframe.mvp.model.impl;

import com.commonframe.mvp.entity.PostQueryInfo;

/**
 * @author XiaoSai
 * @version V1.0.0
 * @Description TODO
 * @date 2016/11/8 17:03
 * @Copyright: Copyright (c) 2016
 */
public interface PostSearchModel{
    /**
     * 请求快递信息
     * @param type 快递类型
     * @param postid 快递单号
     * @param callback 结果回调
     */
    void requestPostSearch(String type,String postid,PostSearchCallback callback);
    interface PostSearchCallback{
        void requestPostSearchSuccess(PostQueryInfo postQueryInfo);
        void requestPostSearchFail(String failStr);
    }
}
