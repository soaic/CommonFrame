package com.commonframe;

import android.app.Application;

import com.httplibrary.base.JinLib;

/**
 * @author XiaoSai
 * @version V1.0.0
 * @description TODO
 * @date 2016/11/8 19:14
 * @copyright: Copyright (c) 2016
 */
public class App extends Application{
    @Override
    public void onCreate(){
        super.onCreate();

        JinLib.initialize(this);
    }
}
