package com.socketlib;

/**
 * TODO 数据接收接口监听
 *
 * @author XiaoSai
 * @version V1.0
 * @since 2017/5/23.
 */

public interface IReceiverListener{
    void onReceiver(byte[] data);
}
