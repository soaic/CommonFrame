package com.commonframe.testui;

import android.os.Looper;
import android.util.Printer;

/**
 * TODO 利用UI线程Looper打印的日志
 *
 * @author XiaoSai
 * @version V1.0
 * @since 2017/3/2.
 */

public class BlockDetectByPrinter{
    public static void start(){
        Looper.getMainLooper().setMessageLogging(new Printer(){
            private static final String START = ">>>>> Dispatching";
            private static final String END = ">>>>> Finished";
            @Override
            public void println(String s){
                if(s.startsWith(START)){
                    LogMonitor.getInstance().startMonitor();
                }
                if(s.startsWith(END)){
                    LogMonitor.getInstance().removeMonitor();
                }
            }
        });
    }
}
