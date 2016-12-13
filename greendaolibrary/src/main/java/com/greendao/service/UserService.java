package com.greendao.service;

import com.greendao.bean.User;
import com.greendao.dao.UserDao;
import com.greendao.utils.DbCore;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.rx.RxDao;


/**
 * 缓存数据表服务类 
 * Created by XS on 2016/7/6.
 */
public class UserService extends BaseService<User,Long>{
    
    public UserService() {
        super(DbCore.getDaoSession().getUserDao());
    }
    
    public static UserService getInstance(){
        return SingleLoader.INSTANCE;
    }
    
    private static class SingleLoader{
        final static UserService INSTANCE = new UserService();
    }
    
}
