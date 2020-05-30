package com.sto.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class InsterService {

    private static String URL = "jdbc:mysql://127.0.0.1:3306/ordertest";
    private static String USERNAME = "root";
    private static String PWD = "root";
    private static int MAX = 10000;
    private static String SQL = "insert into user (`id`, `user_name`, `pass_word`, `age`, `city`, `email`, `nick_name`, `reg_time`) values(?,?,?,?,?,?,?,?)";

    public void testBatchInsert(int size, int batchSize) throws ClassNotFoundException, SQLException {
        if (size > 0) {
            MAX = size;
        }
        Connection con = getConnection();
        con.setAutoCommit(false);
        PreparedStatement pt = con.prepareStatement(SQL);
        int i = 0;
        while (i < MAX) {
            pt.setInt(1, i);
            pt.setString(2, RandomStringUtils.randomAscii(20));
            pt.setString(3, RandomStringUtils.randomAscii(200));
            pt.setInt(4, 1 + i);
            pt.setString(5, "shanghai"+i);
            pt.setString(6, i+"78945@qq.com");
            pt.setString(7, RandomStringUtils.randomAscii(100));
            pt.setDate(8, new Date(System.currentTimeMillis()));
            pt.addBatch();
            if (i % batchSize == 1) {
                pt.executeBatch();
                con.commit();
            }
            i++;
        }
        pt.executeBatch();
        con.commit();
        con.close();
    }

    public void testInsert(int size) throws ClassNotFoundException, SQLException {
        if (size > 0) {
            MAX = size;
        }
        Connection con = getConnection();
        con.setAutoCommit(false);
        PreparedStatement pt = con.prepareStatement(SQL);
        int i = 0;
        while (i < MAX) {
            pt.setInt(1, i);
            pt.setString(2, RandomStringUtils.randomAscii(20));
            pt.setString(3, RandomStringUtils.randomAscii(200));
            pt.setInt(4, 1 + i);
            pt.setString(5, "shanghai"+i);
            pt.setString(6, i+"78945@qq.com");
            pt.setString(7, RandomStringUtils.randomAscii(100));
            pt.setDate(8, new Date(System.currentTimeMillis()));
            pt.executeUpdate();
            con.commit();
            i++;
        }
        con.close();
    }

    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, USERNAME, PWD);
        return con;
    }

}
