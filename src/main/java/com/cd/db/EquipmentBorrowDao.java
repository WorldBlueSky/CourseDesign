package com.cd.db;

import com.cd.model.EquipmentBorrow;

import java.net.DatagramPacket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentBorrowDao {

    // 增删改查

    // 状态默认是未归还

    // 如果点击归还操作，那么把归还状态 修改成  已归还

//    insert into equipmentBorrow(borrowid,name,typename,date,number,peopel) values(0,"桌子","桌椅类","2020-2-2",20,"陈瑞棋");

    // 增
    public static int insert(EquipmentBorrow b){
        PreparedStatement statement = null;
        Connection connection = null;
        int ret =0;


        try {
            // 拿到数据库连接
            connection = Dao.getConnection();

            // 构造sql语句
            String sql = "insert into equipmentBorrow(name,typeName,borrowDate,borrowNumber,peopel) values(?,?,?,?,?)";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            //占位符替换
            statement.setString(1,b.getName());
            statement.setString(2,b.getTypeName());
            statement.setString(3,b.getBorrowDate());
            statement.setInt(4,b.getBorrowNumber());
            statement.setString(5,b.getPeopel());

            // 执行sql语句
            ret = statement.executeUpdate();

           // ======================================================
            //给申请表中添加一条申请记录的同时，一定记得修改原设备表中当前设备数量的信息


            /**
             * 先查找到原目标中的设备数量
             */

            int preNum = selectNum(b.getName());

            String sqlUpdate = "update equipment set number =? where name=?";
            statement = connection.prepareStatement(sqlUpdate);

            // 给原设备数量 设成 申请走之后的数量

            int finalNum = preNum-b.getBorrowNumber();

            statement.setInt(1,finalNum);
            statement.setString(2,b.getName());

            int a =statement.executeUpdate();
            System.out.println("原来的设备信息已经完成修改!"+"目前修改行: "+a);

            //======================================================

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Dao.close(null,statement,connection);
        }

        return ret;
    }

    // 改
    public static int update(EquipmentBorrow b){
        PreparedStatement statement = null;
        Connection connection = null;
        int ret =0;

        try {
            // 拿到数据库连接
            connection = Dao.getConnection();

            // 构造sql语句
            String sql = "update equipmentBorrow set returnDate=?,state=?,returnNumber=? where borrowId=?";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            //占位符替换
            statement.setString(1,b.getReturnDate());
            statement.setString(2,"已归还");
            statement.setInt(3,b.getReturnNumber());
            statement.setInt(4,b.getBorrowId());


            // 执行sql语句
            ret = statement.executeUpdate();
            System.out.println("归还之后的返回值是："+ret);

            // ======================================================
            //给申请表中修改一条申请记录(归还)的同时，一定记得修改原设备表中当前设备数量的信息


            /**
             * 先查找到原目标中的设备数量
             */


            int preNum = selectNum(b.getName());
            System.out.println("申请之后的数量："+preNum);

            String sqlUpdate = "update equipment set number =? where name=?";
            statement = connection.prepareStatement(sqlUpdate);

            // 给原设备数量 设成 申请走之后的数量

            int returnNum =b.getReturnNumber();
            System.out.println("归还的数量："+returnNum);

            int finalNum = preNum+b.getReturnNumber();
            System.out.println("归还后的数量："+finalNum);

            statement.setInt(1,finalNum);
            statement.setString(2,b.getName());

            int a =statement.executeUpdate();
            if(a==1){
                System.out.println("归还后修改原数据成功!");
            }else{
                System.out.println("归还后修改原数据失败!");
            }


            //======================================================
            //======================================================

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Dao.close(null,statement,connection);
        }

        return ret;

    }


    // 查
    public static List<EquipmentBorrow> selectAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<EquipmentBorrow> list = new ArrayList<EquipmentBorrow>();

        try {
            // 拿到数据库连接
            connection = Dao.getConnection();

            // 拼接sql语句
            String sql = "select * from equipmentborrow ";
            statement = connection.prepareStatement(sql);


            // 接收查询记录
            resultSet =  statement.executeQuery();

            // 拿到查询记录中的number信息
           while (resultSet.next()){
               EquipmentBorrow b = new EquipmentBorrow();
               b.setBorrowId(resultSet.getInt("borrowId"));
               b.setName(resultSet.getString("name"));
               b.setTypeName(resultSet.getString("typename"));
               b.setBorrowDate(resultSet.getString("borrowDate"));
               b.setReturnDate(resultSet.getString("returnDate"));
               b.setBorrowNumber(resultSet.getInt("borrowNumber"));
               b.setReturnNumber(resultSet.getInt("returnNumber"));
               b.setPeopel(resultSet.getString("peopel"));
               b.setState(resultSet.getString("state"));
               list.add(b);
           }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Dao.close(resultSet,statement,connection);
        }

        // 如果失败，那么返回null
        return list;
    }



    public static List<EquipmentBorrow> selectById(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<EquipmentBorrow> list = new ArrayList<EquipmentBorrow>();

        try {
            // 拿到数据库连接
            connection = Dao.getConnection();

            // 拼接sql语句
            String sql = "select * from equipmentborrow where borrowId=? ";
            statement = connection.prepareStatement(sql);

             statement.setInt(1,id);
            // 接收查询记录
            resultSet =  statement.executeQuery();

            // 拿到查询记录中的number信息
            while (resultSet.next()){
                EquipmentBorrow b = new EquipmentBorrow();
                b.setBorrowId(resultSet.getInt("borrowId"));
                b.setName(resultSet.getString("name"));
                b.setTypeName(resultSet.getString("typename"));
                b.setBorrowDate(resultSet.getString("borrowDate"));
                b.setReturnDate(resultSet.getString("returnDate"));
                b.setBorrowNumber(resultSet.getInt("borrowNumber"));
                b.setReturnNumber(resultSet.getInt("returnNumber"));
                b.setPeopel(resultSet.getString("peopel"));
                b.setState(resultSet.getString("state"));
                list.add(b);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Dao.close(resultSet,statement,connection);
        }

        // 如果失败，那么返回null
        return list;
    }



    // name 是唯一的，所以根据name查找到当前设备的数量
    public static int selectNum(String name){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int num = 0;

        try {
            // 拿到数据库连接
            connection = Dao.getConnection();

            // 拼接sql语句
            String sql = "select * from equipment where name=?";
            statement = connection.prepareStatement(sql);
            // 占位符替换
            statement.setString(1,name);

            // 接收查询记录
           resultSet =  statement.executeQuery();

            // 拿到查询记录中的number信息
            while(resultSet.next()) {
                num = resultSet.getInt("number");
            }
            return num;


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Dao.close(resultSet,statement,connection);
        }

        // 如果查找失败，那么返回-1
        return -1;
    }

}
