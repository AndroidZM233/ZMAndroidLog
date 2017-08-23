package com.zm.utilslib.test;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 张明_ on 2017/8/23.
 * Email 741183142@qq.com
 */
@Entity
public class GreenDaoTestBean {
    @Id(autoincrement = false)
    private String id;

    @Generated(hash = 611771553)
    public GreenDaoTestBean(String id) {
        this.id = id;
    }

    @Generated(hash = 1284949051)
    public GreenDaoTestBean() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
