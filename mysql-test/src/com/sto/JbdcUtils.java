/**
 * Copyright (C), 2018-2019, 申通快递有限公司
 * FileName: JbdcUtils
 * Author:   thinkpad
 * Date:     2019-06-15 21:48
 * Description: JDBC 工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sto;
/**
 * Created by thinkpad on 2019-06-15.
 */


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * 〈一句话功能简述〉<br> 
 * 〈JDBC 工具类〉
 *
 * @author thinkpad
 * @create 2019-06-15
 * @since 1.0.0
 */
public class JbdcUtils {

    private static String driverClass="com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://47.100.21.53:3306/ordertest";
    private static String user = "root";
    private static String password = "root123";

    static {
        String path = "src/jdbc.properties";
        try {
            //首先创建一个Properties对象
            Properties properties = new Properties();
            //直接加载properties文件
            properties.load(new FileInputStream(path));
            //获取properties文件中的参数 通过key获取value
            driverClass = properties.getProperty("driverClass");
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            System.out.println(driverClass+"==="+url+"==="+user+"==="+password);
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //获取连接
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //释放连接
    public static void release(ResultSet rs, Statement st, Connection con){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        String path = "src/jdbc.properties";
        try {
            //首先创建一个Properties对象
            Properties properties = new Properties();
            //直接加载properties文件
            properties.load(new FileInputStream(path));
            //获取properties文件中的参数 通过key获取value
            String driverClass = properties.getProperty("driverClass");
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            System.out.println(driverClass+"==="+url+"==="+user+"==="+password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
