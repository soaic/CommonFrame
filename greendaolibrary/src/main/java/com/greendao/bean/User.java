package com.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * 
 * Created by XS on 2016/8/24.
 */
@Entity
public class User{

    @Id
    private Long id;
    
    @NotNull
    private String userName;

    @Generated(hash = 1542876495)
    public User(Long id, @NotNull String userName) {
        this.id = id;
        this.userName = userName;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    
}
