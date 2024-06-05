package com.forest.c3p1.pool;

import java.sql.Connection;

/**
 * 封装的连接对象
 *
 * @author Forest Dong
 * @date 2024年06月05日 19:48
 */
public class MyConnection {
    /**
     * 真正的链接
     */
    private Connection realConnection;
    /**
     * 连接是否被使用
     */
    private boolean isUse;

    public Connection getRealConnection() {
        return realConnection;
    }

    public void setRealConnection(Connection realConnection) {
        this.realConnection = realConnection;
    }

    public boolean isUse() {
        return isUse;
    }

    public void setUse(boolean use) {
        isUse = use;
    }
}
