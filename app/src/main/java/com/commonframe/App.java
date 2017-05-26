package com.commonframe;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.greendao.utils.DbCore;

/**
 * @author XiaoSai
 * @version V1.0.0
 * @description TODO
 * @date 2016/11/8 19:14
 * @copyright: Copyright (c) 2016
 */
public class App extends Application{
    
    private static App app;
    
    @Override
    public void onCreate(){
        super.onCreate();
        
        app = this;
        
        //检测UI性能
        //BlockDetectByPrinter.start();
        
        //初始化greenDaoLibrary
        DbCore.init(this);
        //启用sql日志
        DbCore.enableQueryBuilderLog();
        
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //解决65535
        MultiDex.install(this);
    }

    public static Context getContext(){
        return app.getApplicationContext();
    }
}
