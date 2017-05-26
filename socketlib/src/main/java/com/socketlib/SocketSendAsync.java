package com.socketlib;

import java.util.Vector;

/**
 * TODO Socket异步发送数据线程
 *
 * @author XiaoSai
 * @version V1.0
 * @since 2017/5/23.
 */

public class SocketSendAsync implements Runnable{

    private Vector<byte[]> mVector = new Vector<>();
    private IConnectListener mConnectListener;
    private SocketConnection connection;
    private boolean isRun = true;

    public SocketSendAsync(SocketConnection connection){
        this.connection = connection;
    }

    /**
     * 发送数据
     * @param data
     */
    public void sendData(byte[] data) {
        try {
            if (mVector != null)
                mVector.add(data);
        }catch (Exception e){
            
        }
    }
    
    @Override
    public void run(){
        while(isRun){
            if (mVector != null && mVector.size() > 0) {
                try {
                    byte[] data = mVector.remove(0);
                    if (connection != null && connection.isConnected()) {
                        if (!connection.isInputShutdown()) {
                            connection.getOutputStream().write(data);
                            connection.getOutputStream().flush();
                        }
                    } else {
                        if(mConnectListener!=null)
                            mConnectListener.onConnectStateListener(false);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    if(mConnectListener!=null)
                        mConnectListener.onConnectStateListener(false);
                }
            }
        }
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
    
    /**
     * 设置监听
     * @param connectListener
     */
    public void setConnectListener(IConnectListener connectListener){
        this.mConnectListener = connectListener;
    }
}
