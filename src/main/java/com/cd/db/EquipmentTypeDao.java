package com.cd.db;

import com.cd.model.BorrowPeopelType;
import com.cd.model.EquipmentType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentTypeDao {


    //��ɾ�Ĳ�

    // ��
    public static int insert(EquipmentType b) {
        Connection connection = null;
        PreparedStatement statement = null;
        int ret = 0;
        try {
            //1.���õ����ݿ������
            connection = Dao.getConnection();

            //2.ƴ��sql���
            String sql = "insert into equipmenttype values (?,?)";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            // ռλ����ֹsqlע�룬��ò�Ҫֱ��ƴ�ӵ�sql�����
            statement.setInt(1, b.getTypeId());
            statement.setString(2,b.getTypeName());

            //3.ִ��sql���
            ret = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.close(null, statement, connection);
        }
        return ret;
    }

    // ɾ   ��Ϊ�鼮���ͱ����������������Ψһ�ģ����Ծ͸���������ɾ����¼
    public static int delete(int typeid) {
        int ret = 0;
        Connection connection = null;
        PreparedStatement statement = null;


        try {
            //1.�õ����ݿ�����
            connection = Dao.getConnection();
            //2.ƴ��sql���
            String sql = "delete from equipmenttype where typeId =?";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            // ռλ���滻
            statement.setInt(1, typeid);
            // 3.ִ��sql���
            ret = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.close(null, statement, connection);
        }

        return ret;
    }

    // ��  ��Ϊ������Ψһ�ģ����Ը�������id�����޸Ķ���������Ϣ
    public static int update(EquipmentType b,int typeid) {
        Connection connection = null;
        PreparedStatement statement = null;
        int ret = 0;
        try {
            //1.���õ����ݿ������
            connection = Dao.getConnection();
            //2.ƴ��sql���
            String sql = "update equipmenttype set typeName=? where typeid=?";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            // ռλ����ֹsqlע�룬��ò�Ҫֱ��ƴ�ӵ�sql�����
            statement.setString(1, b.getTypeName());
            statement.setInt(2, typeid);

            //3.ִ��sql���
            ret = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.close(null, statement, connection);
        }
        return ret;
    }

    // ��  �������еĶ�������
    public static List<EquipmentType> selectAll(String s) {
        List<EquipmentType> list = new ArrayList<EquipmentType>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            //1.���õ����ݿ������
            connection = Dao.getConnection();
            //2.ƴ��sql���
            String sql = "select *  from equipmenttype where typeName like "+"'%"+s+"%'";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            //3.ִ��sql���
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                EquipmentType b = new EquipmentType();
                b.setTypeId(resultSet.getInt("typeId"));
                b.setTypeName(resultSet.getString("typeName"));
                list.add(b);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.close(null, statement, connection);
        }
        return null;
    }


    // ��  ����ָ���� ���߱�Ž��в��Ҷ�������
    public static List<EquipmentType> selectTypeById(int  typeid) {

        List<EquipmentType> list = new ArrayList<EquipmentType>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;


        try {
            //1.���õ����ݿ������
            connection = Dao.getConnection();
            //2.ƴ��sql���
            String sql = "select *  from equipmenttype where typeId =?";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);
            statement.setInt(1,typeid);

            //3.ִ��sql���
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                EquipmentType b = new EquipmentType();
                b.setTypeId(resultSet.getInt("typeId"));
                b.setTypeName(resultSet.getString("typeName"));
                list.add(b);
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
