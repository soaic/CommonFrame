package com.socketlib;

import java.io.IOException;

/**
 * TODO Socket接收数据线程
 *
 * @author XiaoSai
 * @version V1.0
 * @since 2017/5/23.
 */

public class SocketReceive implements Runnable{
    /** 数据接收接口 */
    private IReceiverListener mReceiverListener;
    private SocketConnection connection;
    private boolean isRun = true;
    
    public SocketReceive(SocketConnection connection){
         this.connection = connection;
    }

    @Override
    public void run(){
        while (isRun){
            try{
                byte[] b = new byte[1024];
                while(isRun && connection.getInputStream().read(b) > -1){
                    if(mReceiverListener != null){
                        mReceiverListener.onReceiver(b);
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }

        }
//        while (isRun) {
//            // 获取socket的输入流并包装成BufferedInputStream
//            try {
//                if (connection != null && connection.isConnected()) {
//                    byte[] prefixBytes = new byte[9];
//                    int len = connection.getInputStream().read(prefixBytes);
//                    if (len < 9)
//                        continue;
//                    byte[] tempByte = new byte[4];
//                    System.arraycopy(prefixBytes, 1, tempByte, 0, 4);
//                    int headLen = ByteUtil.bytesToInt(tempByte);
//                    System.arraycopy(prefixBytes, 5, tempByte, 0, 4);
//                    int bodyLen = ByteUtil.bytesToInt(tempByte);
//                    int allLen = headLen + bodyLen;
//                    if (allLen > 1000000)
//                        continue;
//                    
//                    byte[] desBuf = new byte[allLen + len];
//                    int offset = 0;
//                    System.arraycopy(prefixBytes, 0, desBuf, offset, len);
//                    offset += len;
//                    int size;
//                    while (offset < (allLen + len)) {
//                        byte[] temp = new byte[1024];
//                        if ((size = connection.getInputStream().read(temp)) != 0) {
//                            System.arraycopy(temp, 0, desBuf, offset, size);
//                            offset += size;
//                        }
//                    }
//                    if (null != mReceiverListener)
//                        mReceiverListener.onReceiver(desBuf);
//                }else{ }
//            } catch (Exception e) {
//            }
//        }
    }

    /**
     * 设置接收数据监听
     * @param receiverListener receiverListener
     */
    public void setReceiverListener(IReceiverListener receiverListener){
        this.mReceiverListener = receiverListener;
    }

    /**
     * 是否运行
     * @return
     */
    public boolean isRun(){
        return isRun;
    }
    
    /**
     * 停止线程
     */
    public void stop(){
        isRun = false;
    }

    /**
     * 开启线程
     */
    public void start(){
        isRun = true;
    }
}
