package com.netlibrary.listener;

/**
 * TODO
 *
 * @author XiaoSai
 * @version V1.0
 * @since 2017/3/8.
 */

public class ResponseInfo<T extends ResponseInfo>{
    
    private T data;

    public T getData(){
        return data;
    }

    public void setData(T data){
        this.data = data;
    }

}
