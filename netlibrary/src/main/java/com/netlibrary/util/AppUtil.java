package com.netlibrary.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 
 * Created by XiaoSai on 2016/11/3.
 */
public class AppUtil {
    /**
     * 描述：判断网络是否有效.
     *
     * @return true, if is network available
     */
    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo!=null&&networkInfo.isConnected()) {
                // 当前网络是否已经连接  
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    //网络可用
//                    int type = networkInfo.getType();
//                    if (type == ConnectivityManager.TYPE_WIFI) {
//                        //wifi网络
//                        
//                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
//                        //移动网络
//                        
//                    } else{
//                        //未知网络
//                    }

                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}