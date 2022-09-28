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

    //��ɾ�Ĳ�

    // ��
    public static int insert(BorrowPeopelType b) {
        Connection connection = null;
        PreparedStatement statement = null;
        int ret = 0;
        try {
            //1.���õ����ݿ������
            connection = Dao.getConnection();

            //2.ƴ��sql���
            String sql = "insert into borrowpeopeltype values (?,?)";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            // ռλ����ֹsqlע�룬��ò�Ҫֱ��ƴ�ӵ�sql�����
            statement.setString(1, b.getPeopelTypeId());
            statement.setString(2,b.getPeoelTypeName());

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
    public static int delete(String typeid) {
        int ret = 0;
        Connection connection = null;
        PreparedStatement statement = null;


        try {
            //1.�õ����ݿ�����
            connection = Dao.getConnection();
            //2.ƴ��sql���
            String sql = "delete from borrowpeopeltype where peopelTypeId =?";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            // ռλ���滻
            statement.setString(1, typeid);
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
    public static int update(BorrowPeopelType b) {
        Connection connection = null;
        PreparedStatement statement = null;
        int ret = 0;
        try {
            //1.���õ����ݿ������
            connection = Dao.getConnection();
            //2.ƴ��sql���
            String sql = "update borrowpeopeltype set peopelTypeName=? where peopelTypeId= ?";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            // ռλ����ֹsqlע�룬��ò�Ҫֱ��ƴ�ӵ�sql�����
            statement.setString(1, b.getPeoelTypeName());
            statement.setString(2, b.getPeopelTypeId());

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
    public static List<BorrowPeopelType> selectAll() {
        List<BorrowPeopelType> list = new ArrayList<BorrowPeopelType>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            //1.���õ����ݿ������
            connection = Dao.getConnection();
            //2.ƴ��sql���
            String sql = "select *  from borrowpeopeltype";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            //3.ִ��sql���
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


    // ��  ����ָ���� ���߱�Ž��в��Ҷ�������
    public static List<BorrowPeopelType> selectTypeById(String typeid) {

        List<BorrowPeopelType> list = new ArrayList<BorrowPeopelType>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;


        try {
            //1.���õ����ݿ������
            connection = Dao.getConnection();
            //2.ƴ��sql���
            String sql = "select *  from borrowpeopeltype where peopelTypeId =?";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);
            statement.setString(1,typeid);

            //3.ִ��sql���
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
