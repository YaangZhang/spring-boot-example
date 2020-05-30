/**
 * Copyright (C), 2018-2019, 申通快递有限公司
 * FileName: MysqlTest
 * Author:   thinkpad
 * Date:     2019-06-13 23:09
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sto;
/**
 * Created by thinkpad on 2019-06-13.
 */

import java.sql.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author thinkpad
 * @create 2019-06-13
 * @since 1.0.0
 */
public class MysqlTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://47.100.21.53:3306/ordertest";
        String user = "root";
        String pass = "root123";
        //1、加载MySQL驱动
//        Class.forName("com.mysql.jdbc.Driver");
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//        DriverManager.registerDriver(DriverManager.getDriver("com.mysql.jdbc.Driver"));

        //1、使用连接池获取数据库连接
        MyDataSource dataSource = new MyDataSource();
        Connection connection = dataSource.getConnection();
        //2、获取连接
//        Connection connection = DriverManager.getConnection(url, user, pass);
        //3、创建发送SQL的statement对象
        Statement statement = connection.createStatement();
        //4、发送数据到数据库执行|
        String sql = "select * from User";
        ResultSet resultSet = statement.executeQuery(sql);

        //遍历结果集
        while (resultSet.next()) {
            String userName = resultSet.getString("UserName");
            String email = resultSet.getString("email");
            int id = resultSet.getInt("id");
            System.out.println(id +"==="+userName+"==="+email);
        }
        //6、释放资源
        resultSet.close();
        statement.close();
        //归还数据库连接给连接池
        dataSource.releaseConnection(connection);
    }


}
