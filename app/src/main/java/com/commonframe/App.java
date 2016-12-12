package com.commonframe;

import android.app.Application;

import com.greendao.utils.DbCore;
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
        //初始化imageLibrary
        JinLib.initialize(this);
        //初始化greenDaoLibrary
        DbCore.init(this);
        //启用sql日志
        DbCore.enableQueryBuilderLog();
    }
}
