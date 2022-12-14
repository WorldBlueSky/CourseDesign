package com.cd.db;

import com.cd.model.BorrowPeopel;
import com.cd.model.BorrowPeopelType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowPeopelTypeDao {

    //增删改查

    // 增
    public static int insert(BorrowPeopelType b) {
        Connection connection = null;
        PreparedStatement statement = null;
        int ret = 0;
        try {
            //1.先拿到数据库的连接
            connection = Dao.getConnection();

            //2.拼接sql语句
            String sql = "insert into borrowpeopeltype values (?,?)";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            // 占位符防止sql注入，最好不要直接拼接到sql语句中
            statement.setString(1, b.getPeopelTypeId());
            statement.setString(2,b.getPeoelTypeName());

            //3.执行sql语句
            ret = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.close(null, statement, connection);
        }
        return ret;
    }

    // 删   因为书籍类型编号是主键，所以是唯一的，所以就根据主键来删除记录
    public static int delete(String typeid) {
        int ret = 0;
        Connection connection = null;
        PreparedStatement statement = null;


        try {
            //1.拿到数据库连接
            connection = Dao.getConnection();
            //2.拼接sql语句
            String sql = "delete from borrowpeopeltype where peopelTypeId =?";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            // 占位符替换
            statement.setString(1, typeid);
            // 3.执行sql语句
            ret = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.close(null, statement, connection);
        }

        return ret;
    }

    // 改  因为主键是唯一的，所以根据主键id进行修改读者类型信息
    public static int update(BorrowPeopelType b) {
        Connection connection = null;
        PreparedStatement statement = null;
        int ret = 0;
        try {
            //1.先拿到数据库的连接
            connection = Dao.getConnection();
            //2.拼接sql语句
            String sql = "update borrowpeopeltype set peopelTypeName=? where peopelTypeId= ?";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            // 占位符防止sql注入，最好不要直接拼接到sql语句中
            statement.setString(1, b.getPeoelTypeName());
            statement.setString(2, b.getPeopelTypeId());

            //3.执行sql语句
            ret = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.close(null, statement, connection);
        }
        return ret;
    }

    // 查  查找所有的读者类型
    public static List<BorrowPeopelType> selectAll() {
        List<BorrowPeopelType> list = new ArrayList<BorrowPeopelType>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            //1.先拿到数据库的连接
            connection = Dao.getConnection();
            //2.拼接sql语句
            String sql = "select *  from borrowpeopeltype";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            //3.执行sql语句
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                BorrowPeopelType peopelType = new BorrowPeopelType();
                peopelType.setPeopelTypeId(resultSet.getString("peopelTypeId"));
                peopelType.setPeoelTypeName(resultSet.getString("peopelTypeName"));
                list.add(peopelType);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.close(null, statement, connection);
        }
        return null;
    }


    // 查  按照指定的 读者编号进行查找读者类型
    public static List<BorrowPeopelType> selectTypeById(String typeid) {

        List<BorrowPeopelType> list = new ArrayList<BorrowPeopelType>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;


        try {
            //1.先拿到数据库的连接
            connection = Dao.getConnection();
            //2.拼接sql语句
            String sql = "select *  from borrowpeopeltype where peopelTypeId =?";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);
            statement.setString(1,typeid);

            //3.执行sql语句
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                BorrowPeopelType peopelType = new BorrowPeopelType();
                peopelType.setPeopelTypeId(resultSet.getString("peopelTypeId"));
                peopelType.setPeoelTypeName(resultSet.getString("peopelTypeName"));
                list.add(peopelType);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.close(null, statement, connection);
        }
        return null;
    }


}
