package com.commonframe.testui;

import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 利用Looper机制
 *
 * @author XiaoSai
 * @version V1.0
 * @since 2017/3/2.
 */

public class BlockDetectByLooper {
    private static final String FIELD_mQueue = "mQueue";
    private static final String METHOD_next = "next";

    public static void start() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                try {
                    Looper mainLooper = Looper.getMainLooper();
                    final Looper me = mainLooper;
                    final MessageQueue queue;
                    Field fieldQueue = me.getClass().getDeclaredField(FIELD_mQueue);
                    fieldQueue.setAccessible(true);
                    queue = (MessageQueue) fieldQueue.get(me);
                    Method methodNext = queue.getClass().getDeclaredMethod(METHOD_next);
                    methodNext.setAccessible(true);
                    Binder.clearCallingIdentity();
                    for (; ; ) {
                        Message msg = (Message) methodNext.invoke(queue);
                        if (msg == null) {
                            return;
                        }
                        LogMonitor.getInstance().startMonitor();
                        msg.getTarget().dispatchMessage(msg);
                        msg.recycle();
                        LogMonitor.getInstance().removeMonitor();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
