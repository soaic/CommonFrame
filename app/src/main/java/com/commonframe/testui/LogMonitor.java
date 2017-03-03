package com.commonframe.testui;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

/**
 * TODO
 *
 * @author XiaoSai
 * @version V1.0
 * @since 2017/3/2.
 */

public class LogMonitor{
    private static LogMonitor sInstance = new LogMonitor();
    private HandlerThread mLogThread= new HandlerThread("log");
    private Handler mIoHandler;
    private static final long TIME_BLOCK = 1000L;
    private boolean isMonitor = false;
    private LogMonitor(){
        mLogThread.start();
        mIoHandler = new Handler(mLogThread.getLooper());
    }
    private static Runnable mLogRunnable = new Runnable(){
        @Override
        public void run(){
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = Looper.getMainLooper().getThread().getStackTrace();
            for (StackTraceElement s : stackTrace){
                sb.append(s.toString() + "\n");
            }
            Log.e("TAG",sb.toString());
        }
    };
    
    public static LogMonitor getInstance(){
        return sInstance;
    }
    public boolean isMonitor(){
        return isMonitor;
    }
    public void startMonitor(){
        mIoHandler.postDelayed(mLogRunnable,TIME_BLOCK);
        isMonitor = true;
    }
    public void removeMonitor() {
        mIoHandler.removeCallbacks(mLogRunnable);
        isMonitor = false;
    }
}
