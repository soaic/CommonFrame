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
public class Type{

    @Id
    private Long id;
    
    @NotNull
    private String typeName;

    @Generated(hash = 1257751898)
    public Type(Long id, @NotNull String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    @Generated(hash = 1782799822)
    public Type() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}
