package com.forest.c3p1.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 连接池
 *
 * @author Forest Dong
 * @date 2024年06月05日 19:52
 */
public class PoolDataSource {
    private static final List<Connection> connections;
    private static final List<Connection> inUseConnections = new CopyOnWriteArrayList<>();
    private static final List<Connection> unUseconnections = new CopyOnWriteArrayList<>();
    private static final int coreSize = 8;
    private static final int maxSize = 30;

    static {
        connections = new CopyOnWriteArrayList<>();
        for (int i = 0; i < coreSize; i++) {
            connections.add(newConnection());
        }
    }

    public static Connection getConnection() {
        return connections.getFirst();
    }

    public static void releaseConnection() {

    }

    private static Connection newConnection() {
        String url = "jdbc:mysql://47.119.31.234:3316/campus_imaotai";
        String userName = "imaotai";
        String password = "PZrGWTDPPxneMrnT";
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
