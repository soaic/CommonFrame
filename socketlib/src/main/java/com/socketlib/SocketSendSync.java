package com.socketlib;

/**
 * TODO Socket 同步发送数据线程
 *
 * @author XiaoSai
 * @version V1.0
 * @since 2017/5/23.
 */

public class SocketSendSync{
    private IConnectListener mConnectListener;
    private SocketConnection connection;

    public SocketSendSync(SocketConnection connection){
        this.connection = connection;
    }
    
    /**
     * 发送数据
     * @param data
     */
    public void sendData(byte[] data) {
        try {
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

    /**
     * 设置监听
     * @param connectListener
     */
    public void setConnectListener(IConnectListener connectListener){
        this.mConnectListener = connectListener;
    }
}
