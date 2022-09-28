package com.cd.db;//package com.cd.db;
//
//import com.cd.model.User;
//
import com.cd.model.User;
import com.sun.org.apache.xerces.internal.impl.dv.xs.YearDV;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    //增删改查

    // 增  前端界面写入user的所有信息，封装成一个user对象插入
    public static int insertUser(User user) {

        Connection connection = null;
        PreparedStatement statement = null;
        int ret = 0;
        try {
            //1.先拿到数据库的连接
            connection = Dao.getConnection();
            //2.拼接sql语句
            String sql = "insert into user values (null,?,?)";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);


            // 占位符防止sql注入，最好不要直接拼接到sql语句中
           statement.setString(1,user.getUserName());
           statement.setString(2,user.getPassword());
            //3.执行sql语句
            ret = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.close(null, statement, connection);
        }
        return ret;
    }


    // 删  因为用户id是自增主键所以 删除根据id删除
    public static int deleteUser(String name){
        int ret = 0;
        Connection connection = null;
        PreparedStatement statement = null;


        try {
            //1.拿到数据库连接
            connection = Dao.getConnection();
            //2.拼接sql语句
            String sql = "delete from user where username =?";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            // 占位符替换
            statement.setString(1,name);
            // 3.执行sql语句
            ret = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.close(null, statement, connection);
        }

        return ret;
    }

    // 改  前端页面只显示指定用户名的情况下，修改密码
    public static int updateUser(String name,String password){
        Connection connection = null;
        PreparedStatement statement = null;
        int ret = 0;
        try {
            //1.先拿到数据库的连接
            connection = Dao.getConnection();
            //2.拼接sql语句
            String sql = "update user set password=? where username= ?";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            // 占位符防止sql注入，最好不要直接拼接到sql语句中
            statement.setString(1, password);
            statement.setString(2, name);

            //3.执行sql语句
            ret = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.close(null, statement, connection);
        }
        return ret;
    }

    // 查  前端页面会在删除具体用户的时候 展示所有用户信息，所以直接查询所有信息即可
    public static List<User> selectUser(String s){
        List<User> list = new ArrayList<User>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            //1.先拿到数据库的连接
            connection = Dao.getConnection();
            //2.拼接sql语句
            String sql = "select *  from user where username like" + "'%" + s + "%'";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            //3.执行sql语句
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("userid"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                list.add(user);
            }
            for (User user:list) {
                System.out.println(user);
            };
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.close(null, statement, connection);
        }
        return null;
    }

    public static User selectUserByName(String name){

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;


        try {
            //1.先拿到数据库的连接
            connection = Dao.getConnection();
            //2.拼接sql语句
            String sql = "select *  from user where username=?";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            // 占位符替换
            statement.setString(1,name);

            //3.执行sql语句
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("userid"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.close(null, statement, connection);
        }
        return null;
    }

}
