package com.cd.db;

import com.cd.model.BorrowPeopel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowPeopelDao {

    // 在文本框中填入申请人的具体信息，然后构造成一个BorrowPeopel对象,插入到borrowPeopel对象
    public static int insert(BorrowPeopel b){
        Connection connection = null;
        PreparedStatement statement = null;
        int ret = 0;

        try {

            // 先拿到数据库的连接
            connection = Dao.getConnection();

            // 构造sql语句
            String sql = "insert into borrowpeopel values(?,?,?,?,?,?)";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            // 占位符替换
            statement.setString(1,b.getPeopelId());
            statement.setString(2,b.getPeopelName());
            statement.setString(3,b.getPeopelTypeName());
            statement.setString(4,b.getSex());
            statement.setInt(5, b.getAge());
            statement.setString(6,b.getPhone());

            // 执行sql语句
            ret = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Dao.close(null,statement,connection);
        }

        //返回结果

        return ret;
    }

    // 根据id编号进行删除
    public static int delete(String peopelId){
        Connection connection = null;
        PreparedStatement statement = null;
        int ret = 0;

        try {

            // 先拿到数据库的连接
            connection = Dao.getConnection();

            // 构造sql语句
           String sql = "delete from borrowpeopel where peopelId=?";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            // 占位符替换
            statement.setString(1,peopelId);

            // 执行sql语句
            ret = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Dao.close(null,statement,connection);
        }

        //返回结果

        return ret;
    }

    // 根据 申请人员的编号id 来进行修改
    public static int update(BorrowPeopel b){
        Connection connection = null;
        PreparedStatement statement = null;
        int ret = 0;

        try {

            // 先拿到数据库的连接
            connection = Dao.getConnection();

            // 构造sql语句
            String sql = "update borrowpeopel set peopelName=?,peopelTypeName=?,sex=?,age=?,phone=? where peopelId=?";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);

            // 占位符替换
            statement.setString(1,b.getPeopelName());
            statement.setString(2,b.getPeopelTypeName());
            statement.setString(3,b.getSex());
            statement.setInt(4,b.getAge());
            statement.setString(5, b.getPhone());
            statement.setString(6,b.getPeopelId());

            // 执行sql语句
            ret = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Dao.close(null,statement,connection);
        }

        //返回结果

        return ret;
    }

    //查找  模糊查询查找全部
    public static List<BorrowPeopel> selectAll(String s){
      // 查找全部采用模糊查询

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<BorrowPeopel> list =new ArrayList<BorrowPeopel>();

        try {
            //1.连接数据库
            connection = Dao.getConnection();

            // 2.拼接sql语句
            String sql =  "select * from borrowpeopel where peopelname like" + "'%" + s + "%'" ;
            System.out.println(sql);

            //3.执行sql查询语句
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();

            //可以包括多类的查询条件，根据前面的界面来完善。
            while(resultSet.next()) {
              BorrowPeopel b = new BorrowPeopel();
              b.setPeopelId(resultSet.getString("peopelid"));
              b.setPeopelName(resultSet.getString("peopelName"));
              b.setPeopelTypeName(resultSet.getString("peopelTypeName"));
              b.setSex(resultSet.getString("sex"));
              b.setAge(resultSet.getInt("age"));
              b.setPhone(resultSet.getString("phone"));
              list.add(b);
            }
            return list;

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Dao.close(resultSet,statement,connection);
        }

        return null;

    }


    // 根据 申请人编号进行查找
    public static List<BorrowPeopel> selectById(String id){
        // 查找全部采用模糊查询

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<BorrowPeopel> list =new ArrayList<BorrowPeopel>();

        try {
            //1.连接数据库
            connection = Dao.getConnection();

            // 2.拼接sql语句
            String sql =  "select * from borrowpeopel where peopelId=?" ;
            System.out.println(sql);

            //3.执行sql查询语句
            statement = connection.prepareStatement(sql);
            statement.setString(1,id);

            resultSet = statement.executeQuery();

            //可以包括多类的查询条件，根据前面的界面来完善。
            while(resultSet.next()) {
                BorrowPeopel b = new BorrowPeopel();
                b.setPeopelId(resultSet.getString("peopelid"));
                b.setPeopelName(resultSet.getString("peopelName"));
                b.setPeopelTypeName(resultSet.getString("peopelTypeName"));
                b.setSex(resultSet.getString("sex"));
                b.setAge(resultSet.getInt("age"));
                b.setPhone(resultSet.getString("phone"));
                list.add(b);
            }
            return list;

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Dao.close(resultSet,statement,connection);
        }

        return null;

    }


}
