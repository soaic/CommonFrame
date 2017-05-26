package com.socketlib;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Socket连接封装
 */
public class SocketConnection{
    /** 域名 */
    private String mHostName;
    /** 端口 */
    private int mPort;
    /** socket实例 */
    private Socket mSocket;
    /** 超时时间 默认5秒超时 */
    private int mTimeOut = 5000;
    /** 输出 发送数据*/
    private OutputStream mOutputStream;
    /** 输入 接收数据*/
    private InputStream mInputStream;
    /** 接收数据线程*/
    private SocketReceive mSocketReceive;
    private Thread mReceiveThread;
    /** 发送数据线程*/
    private SocketSendAsync mSocketSendAsync;
    private SocketSendSync mSocketSendSync;
    private Thread mSendAsyncThread;

    public OutputStream getOutputStream(){
        return mOutputStream;
    }

    public InputStream getInputStream(){
        return mInputStream;
    }

    public SocketConnection(String hostName,int port){
        this.mHostName = hostName;
        this.mPort = port;
        mSocket = new Socket();
        init();
    }

    public SocketConnection(String hostName, int port, int timeOut){
        this.mHostName = hostName;
        this.mPort = port;
        mSocket = new Socket();
        this.mTimeOut = timeOut;
        init();
    }
    
    private void init(){
        //初始化接收数据线程
        mSocketReceive = new SocketReceive(this);
        mReceiveThread = new Thread(mSocketReceive);
        //初始化异步发送数据
        mSocketSendAsync = new SocketSendAsync(this);
        mSendAsyncThread = new Thread(mSocketSendAsync);
        //初始化同步发送数据
        mSocketSendSync = new SocketSendSync(this);
    }

    /**
     * socket 连接
     * @return
     */
    public boolean connect(){
        boolean connect;
        try{
            mSocket.connect(new InetSocketAddress(mHostName,mPort),mTimeOut);
            mSocket.setSoTimeout(mTimeOut);
            mOutputStream = mSocket.getOutputStream();
            mInputStream = mSocket.getInputStream();
            if(mReceiveThread!=null){
                mReceiveThread.start();
            }
            if(mSendAsyncThread!=null){
                mSendAsyncThread.start();
            }
            if(mSocketReceive!=null&&!mSocketReceive.isRun()){
                mSocketReceive.start();
            }
            if(mSocketSendAsync!=null&&mSocketSendAsync.isRun()){
                mSocketSendAsync.start();
            }
            connect = true;
        }catch(Exception e){
            e.printStackTrace();
            connect = false;
        }
        return connect;
    }

    /**
     * 是否连接
     * @return
     */
    public boolean isConnected(){
        return mSocket !=null&&mSocket.isConnected()&&!mSocket.isInputShutdown()&&!mSocket.isOutputShutdown();
    }
    
    public boolean isInputShutdown(){
        return mSocket !=null && mSocket.isInputShutdown();
    }

    /**
     * 断开连接
     * @return
     */
    public void disconnect(){
        if(mSocketReceive != null){
            mSocketReceive.stop();
        }
        if(mSendAsyncThread != null){
            mSocketSendAsync.stop();
        }
        try{
            if(mSocket!=null){
                mSocket.close();
            }
            if(mOutputStream!=null){
                mOutputStream.close();
                mOutputStream = null;
            }
            if(mInputStream!=null){
                mInputStream.close();
                mInputStream = null;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 重新连接
     * @return
     */
    public boolean reConnect(){
        if(!isConnected()){
            connect();
        }
        return false;
    }

    /**
     * 异步发送数据
     * @param data
     */
    public void sendDataAsync(byte[] data){
        if(mSocketSendSync!=null){
            mSocketSendSync.sendData(data);
        }
    }

    /**
     * 同步发送数据
     * @param data
     */
    public void sendDataSync(byte[] data){
        if(mSocketSendAsync!=null){
            mSocketSendAsync.sendData(data);
        }
    }

    /**
     * 设置连接监听
     * @param connectListener
     */
    public void setConnectionListener(IConnectListener connectListener){
        if(mSocketSendSync !=null){
            mSocketSendSync.setConnectListener(connectListener);
        }
        if(mSocketSendAsync != null){
            mSocketSendAsync.setConnectListener(connectListener);
        }
    }

    /**
     * 设置接收数据监听
     * @param receiverListener
     */
    public void setReceiverListener(IReceiverListener receiverListener){
        if(mSocketReceive !=null){
            mSocketReceive.setReceiverListener(receiverListener);
        }
    }
}
