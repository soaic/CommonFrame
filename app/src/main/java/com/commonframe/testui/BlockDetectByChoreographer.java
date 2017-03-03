package com.commonframe.testui;

import android.view.Choreographer;

/**
 * TODO 利用Choreographer
 *
 * @author XiaoSai
 * @version V1.0
 * @since 2017/3/2.
 */

public class BlockDetectByChoreographer{
    public static void start(){
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback(){
            @Override
            public void doFrame(long l){
                if(LogMonitor.getInstance().isMonitor()){
                    LogMonitor.getInstance().removeMonitor();
                }
                LogMonitor.getInstance().startMonitor();
                Choreographer.getInstance().postFrameCallback(this);
            }
        });
    }
}
