package com.greendao.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.greendao.bean.Role;
import com.greendao.bean.Type;
import com.greendao.bean.User;

import com.greendao.dao.RoleDao;
import com.greendao.dao.TypeDao;
import com.greendao.dao.UserDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig roleDaoConfig;
    private final DaoConfig typeDaoConfig;
    private final DaoConfig userDaoConfig;

    private final RoleDao roleDao;
    private final TypeDao typeDao;
    private final UserDao userDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        roleDaoConfig = daoConfigMap.get(RoleDao.class).clone();
        roleDaoConfig.initIdentityScope(type);

        typeDaoConfig = daoConfigMap.get(TypeDao.class).clone();
        typeDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        roleDao = new RoleDao(roleDaoConfig, this);
        typeDao = new TypeDao(typeDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);

        registerDao(Role.class, roleDao);
        registerDao(Type.class, typeDao);
        registerDao(User.class, userDao);
    }
    
    public void clear() {
        roleDaoConfig.clearIdentityScope();
        typeDaoConfig.clearIdentityScope();
        userDaoConfig.clearIdentityScope();
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public TypeDao getTypeDao() {
        return typeDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

}