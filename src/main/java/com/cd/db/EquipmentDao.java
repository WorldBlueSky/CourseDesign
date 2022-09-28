package com.cd.db;

import com.cd.model.Equipment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EquipmentDao {
    //根据设备名称删除设备信息
    public static int delete(String name) {
        // 先定义数据库连接，预处理
        Connection connection = null;
        PreparedStatement statement = null;
        int ret = 0;

        try{
            //1.获取到数据库连接
            connection = Dao.getConnection();
            //2.拼装sql语句
            String sql="delete from equipment where name=?";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);

            ret = statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            Dao.close(null,statement,connection);
        }
        return ret;
    }


    //增加设备信息
    public static int insertEquipment(Equipment equipment, String typeName) {
        //稍微复杂：图书表中包括的是类型编号，界面设计时是类型的名称，
        //所以，在增加之前，需要先将类型名称转换成类型编号
        //根据类型名查找到相应的类型编号，涉及到图书类型表

        Connection connection = Dao.getConnection();
        PreparedStatement statement1 = null;
        PreparedStatement statement2 = null;
        ResultSet resultSet = null;
        int ret = 0;
        // 根据设备类型的类型名找到类型编号

        try {
            // 拼接sql语句
            String sql1="select typeid from equipmenttype where typename='" +typeName+"'";
            statement1 = connection.prepareStatement(sql1);
            resultSet = statement1.executeQuery();

            int typeID = -1;

            if(resultSet.next()){
                typeID = resultSet.getInt("typeid");
            }
            // 根据类型名拿到设备类型的id了

            System.out.println(typeID);

            String sql2="insert into equipment(id, name, typeid, typename, number, unit, location, purchaseDate, price) values (?,?,?,?,?,?,?,?,?)";
            System.out.println(sql2);

            statement2 = connection.prepareStatement(sql2);

            statement2.setInt(1,0);
            statement2.setString(2,equipment.getName());
            statement2.setInt(3,typeID);
            statement2.setString(4,equipment.getTypeName());
            statement2.setInt(5,equipment.getNumber());
            statement2.setString(6,equipment.getUnit());
            statement2.setString(7,equipment.getLocation());
            statement2.setString(8,equipment.getPurchaseDate());
            statement2.setDouble(9,equipment.getPrice());

            ret = statement2.executeUpdate();
            System.out.println(sql2);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            Dao.close(null,statement1,null);
            Dao.close(resultSet,statement2,connection);
        }

        return ret;
    }

    //查询功能  查询全部是模糊查询
    public static List<Equipment> selectAll(String s){

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Equipment> list =new ArrayList<Equipment>();

        try {
            //1.连接数据库
            connection = Dao.getConnection();

            // 2.拼接sql语句
            String sql = "select id,name,typename,number,unit,location,purchaseDate,price from equipment where name like" + "'%" + s + "%'";
            System.out.println(sql);

            //3.执行sql查询语句
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();

            //可以包括多类的查询条件，根据前面的界面来完善。
            while(resultSet.next()) {
               Equipment equipment = new Equipment();
               equipment.setId(resultSet.getInt("id"));
               equipment.setName(resultSet.getString("name"));
               equipment.setTypeName(resultSet.getString("typename"));
               equipment.setNumber(resultSet.getInt("number"));
               equipment.setUnit(resultSet.getString("unit"));
               equipment.setLocation(resultSet.getString("location"));
               equipment.setPurchaseDate(resultSet.getString("purchaseDate"));
               equipment.setPrice(resultSet.getDouble("price"));
               list.add(equipment);
            }
            return list;

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Dao.close(resultSet,statement,connection);
        }

        return null;

    }


    //查询功能
    public static List<Equipment> selectByName(String name){

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Equipment> list =new ArrayList<Equipment>();

        try {
            //1.连接数据库
            connection = Dao.getConnection();

            // 2.拼接sql语句
            String sql = "select id,name,typename,number,unit,location,purchaseDate,price from equipment where name=?";
            System.out.println(sql);

            //3.执行sql查询语句
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);

            resultSet = statement.executeQuery();

            //可以包括多类的查询条件，根据前面的界面来完善。
            while(resultSet.next()) {
                Equipment equipment = new Equipment();
                equipment.setId(resultSet.getInt("id"));
                equipment.setName(resultSet.getString("name"));
                equipment.setTypeName(resultSet.getString("typename"));
                equipment.setNumber(resultSet.getInt("number"));
                equipment.setUnit(resultSet.getString("unit"));
                equipment.setLocation(resultSet.getString("location"));
                equipment.setPurchaseDate(resultSet.getString("purchaseDate"));
                equipment.setPrice(resultSet.getDouble("price"));
                list.add(equipment);
            }
            return list;

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Dao.close(resultSet,statement,connection);
        }

        return null;

    }


    //修改
    public static int updateEquipment(Equipment equipment){

        Connection connection = Dao.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int ret = 0;
        // 根据设备类型的类型名找到类型编号

        try {
            // 拼接sql 修改语句
            String sqlUpdat="update equipment set typename=?,number=?,unit=?,location=?,purchaseDate=?,price=? where name=?";
            System.out.println(sqlUpdat);

            statement = connection.prepareStatement(sqlUpdat);

            statement.setString(1,equipment.getTypeName());
            statement.setInt(2,equipment.getNumber());
            statement.setString(3,equipment.getUnit());
            statement.setString(4,equipment.getLocation());
            statement.setString(5,equipment.getPurchaseDate());
            statement.setDouble(6,equipment.getPrice());
            statement.setString(7,equipment.getName());

            ret = statement.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }finally {
            Dao.close(resultSet,statement,connection);
        }
        return ret;
    }



}
