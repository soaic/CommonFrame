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
public class Role{

    @Id
    private Long id;
    
    @NotNull
    private String roleName;

    @Generated(hash = 1484200341)
    public Role(Long id, @NotNull String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    @Generated(hash = 844947497)
    public Role() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    
    
}
