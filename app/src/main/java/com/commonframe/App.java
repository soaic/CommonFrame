package com.commonframe;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.httplibrary.base.JinLib;

/**
 * @author XiaoSai
 * @version V1.0.0
 * @description TODO
 * @date 2016/11/8 19:14
 * @copyright: Copyright (c) 2016
 */
public class App extends MultiDexApplication{
    @Override
    public void onCreate(){
        super.onCreate();

        JinLib.initialize(this);
    }

    /**
     * 分割 Dex 支持
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
