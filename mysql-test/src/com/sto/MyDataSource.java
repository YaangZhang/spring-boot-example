/**
 * Copyright (C), 2018-2019, 申通快递有限公司
 * FileName: MyDataSource
 * Author:   thinkpad
 * Date:     2019-06-14 22:48
 * Description: 简易数据库连接池
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sto;
/**
 * Created by thinkpad on 2019-06-14.
 */

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * 〈一句话功能简述〉<br> 
 * 〈简易数据库连接池〉
 *
 * @author thinkpad
 * @create 2019-06-14
 * @since 1.0.0
 */
public class MyDataSource implements DataSource {
    //链表   实现栈结构
    private LinkedList<Connection> dataSources = new LinkedList<>();
    String url = "jdbc:mysql://47.100.21.53:3306/ordertest";
    String user = "root";
    String pass = "root123";

    //初始化连接数量
    public MyDataSource(){
        try {
            for (int i = 0; i < 10; i ++) {
                //1、加载mysql驱动
                Class.forName("com.mysql.jdbc.Driver");
                //2、通过JDBC建立数据库连接
                Connection connection = DriverManager.getConnection(url, user, pass);
                //3/将连接加入连接池
                dataSources.add(connection);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Connection getConnection() throws SQLException {
        //从连接池中取出一个连接
        Connection connection = dataSources.removeFirst();
        return connection;
    }

    /**
     * 将连接放回连接池
     * @param connection
     */
    public void releaseConnection(Connection connection){
        dataSources.add(connection);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getConnection();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
