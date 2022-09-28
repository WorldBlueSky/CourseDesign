package com.cd.db;

import com.cd.model.Administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {

    // 只做一个查询的方法，进行比对登陆的用户名和密码
    public static Administrator selectByName(String name){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            //1.获取数据库连接
            connection = Dao.getConnection();
            //2.构造sql语句
            String sql = "select * from administrator";

            statement = connection.prepareStatement(sql);

            //执行sql语句
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                Administrator a = new Administrator();
                System.out.println("创建了一个新的管理员对象!");
                a.setId(resultSet.getInt("id"));
                a.setName(resultSet.getString("name"));
                a.setPassword(resultSet.getString("password"));
                return a;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Dao.close(resultSet,statement,connection);
        }
        // 3.拿到记录

       return null;
    }

}
