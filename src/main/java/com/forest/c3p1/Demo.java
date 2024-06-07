package com.forest.c3p1;

import com.forest.c3p1.pool.PoolDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Forest Dong
 * @date 2024年06月05日 19:36
 */
public class Demo {
    public static void main(String[] args) throws SQLException {
        for (int i = 0; i < 100; i++) {
            test();
        }
    }

    private static void test() throws SQLException {
        Connection connection = PoolDataSource.getConnection();
        System.err.println("获取连接:" + connection.hashCode());
        PreparedStatement statement = connection.prepareStatement("select * from i_item where item_code = 10941");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String content = rs.getString("content");
            System.err.println(content);
        }
        rs.close();
        statement.close();
        PoolDataSource.releaseConnection(connection);
    }
}
