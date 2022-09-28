package com.cd.db;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao {

    //连接MYSQL数据库的语句	

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/courseDesign?characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true" ;
    private static  final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    // 获取数据库连接
    private static volatile DataSource dataSource = null;

    // 单例模式获得数据库实例
    private static DataSource getDataSource(){
        if (dataSource==null) {
            synchronized (Dao.class) {
                if(dataSource==null){
                    dataSource = new MysqlDataSource();
                    ((MysqlDataSource)dataSource).setURL(URL);
                    ((MysqlDataSource)dataSource).setUser(USERNAME);
                    ((MysqlDataSource)dataSource).setPassword(PASSWORD);
                }
            }
        }
        return dataSource;
    }

    // 获得数据库连接
    public static Connection getConnection(){
        try {
           return getDataSource().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    //回收资源
    public static void close(ResultSet resultSet, PreparedStatement statement ,Connection connection){

        if (resultSet!=null) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (statement!=null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (connection!=null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
