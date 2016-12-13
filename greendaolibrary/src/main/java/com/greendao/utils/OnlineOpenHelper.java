package com.greendao.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.greendao.dao.DaoMaster;
import com.greendao.dao.RoleDao;
import com.greendao.dao.UserDao;

import org.greenrobot.greendao.database.Database;

/**
 * 数据库升级帮助类
 * Created by XiaoSai on 2016/12/13.
 */
public class OnlineOpenHelper extends DaoMaster.OpenHelper{
    public OnlineOpenHelper(Context context,String name) {
        super(context, name);
    }

    public OnlineOpenHelper(Context context,String name,SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db,int oldVersion,int newVersion) {
        Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion );
        switch(oldVersion){
            case 1: MigrationHelper.getInstance().migrate(db,UserDao.class);
            case 2: RoleDao.createTable(db,false);
        }
    }
}
