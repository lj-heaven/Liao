package com.example.base.algorithm;

import java.sql.*;

/**
 * JDBC连接mysql
 */
public class demo6 {

    //数据库连接
    private static final String url = "jdbc:mysql://127.0.0.1:3306/xxx?useUnicode=true&characterEncoding=utf8";
    private static final String user = "liao";
    private static final String password = "xxx";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            //1.加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.建立数据库连接
            connection = DriverManager.getConnection(url, user, password);
            //3.创建statement对象
            statement = connection.createStatement();

            String sql = "SELECT id, name, age FROM user";

            //4.执行sql语句
            resultSet = statement.executeQuery(sql);
            //5.处理结果集
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
                // 执行插入操作示例
                String insertSql = "INSERT INTO users(name, age) VALUES('John', 25)";
                int rowsAffected = statement.executeUpdate(insertSql);
                System.out.println("插入行数: " + rowsAffected);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            // 6. 关闭资源
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
