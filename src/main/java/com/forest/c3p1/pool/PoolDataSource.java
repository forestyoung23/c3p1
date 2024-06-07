package com.forest.c3p1.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 连接池
 *
 * @author Forest Dong
 * @date 2024年06月05日 19:52
 */
public class PoolDataSource {
//    private static final Map<Integer, List<Connection>> connections;
    private static final List<Connection> connections;
    private static final List<Connection> inUseConnections = new CopyOnWriteArrayList<>();
    private static final List<Connection> unUseconnections = new CopyOnWriteArrayList<>();
    private static final int coreSize = 8;
    private static final int maxSize = 30;

    static {
//        connections = new ConcurrentHashMap<>();
        connections = new ArrayList<>(8);
        for (int i = 0; i < coreSize; i++) {
            Connection connection = newConnection();
            connections.add(connection);
            unUseconnections.add(connection);
        }
    }

    public static Connection getConnection() {
        if (unUseconnections.isEmpty()) {
            throw new RuntimeException("Not Connection can be use");
        } else {
            Connection connection = unUseconnections.getFirst();
            unUseconnections.removeFirst();
            inUseConnections.add(connection);
            return connection;
        }
    }

    public static void releaseConnection(Connection connection) {
        if (inUseConnections.isEmpty()) {
            throw new RuntimeException("Not Use Connection, do not need release");
        } else {
            inUseConnections.removeIf(connection::equals);
            unUseconnections.add(connection);
        }
    }

    private static Connection newConnection() {
        String url = "jdbc:mysql://47.119.31.234:3316/campus_imaotai";
        String userName = "xxx";
        String password = "xxx";
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
