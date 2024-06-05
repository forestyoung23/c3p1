package com.forest.c3p1;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Forest Dong
 * @date 2024年06月05日 19:36
 */
public class Demo {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://47.119.31.234:3316/campus_imaotai";
        String userName = "imaotai";
        String password = "PZrGWTDPPxneMrnT";
        Connection connection = DriverManager.getConnection(url, userName, password);
        System.err.println(connection);
    }
}
