package com.cd.db;

import com.cd.model.BorrowPeopel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowPeopelDao {

    // ���ı��������������˵ľ�����Ϣ��Ȼ�����һ��BorrowPeopel����,���뵽borrowPeopel����
    public static int insert(BorrowPeopel b){
        Connection connection = null;
        PreparedStatement statement = null;
        int ret = 0;

        try {

            // ���õ����ݿ������
            connection = Dao.getConnection();

            // ����sql���
            String sql = "insert into borrowpeopel values(?,?,?,?,?,?)";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            // ռλ���滻
            statement.setString(1,b.getPeopelId());
            statement.setString(2,b.getPeopelName());
            statement.setString(3,b.getPeopelTypeName());
            statement.setString(4,b.getSex());
            statement.setInt(5, b.getAge());
            statement.setString(6,b.getPhone());

            // ִ��sql���
            ret = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Dao.close(null,statement,connection);
        }

        //���ؽ��

        return ret;
    }

    // ����id��Ž���ɾ��
    public static int delete(String peopelId){
        Connection connection = null;
        PreparedStatement statement = null;
        int ret = 0;

        try {

            // ���õ����ݿ������
            connection = Dao.getConnection();

            // ����sql���
           String sql = "delete from borrowpeopel where peopelId=?";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            // ռλ���滻
            statement.setString(1,peopelId);

            // ִ��sql���
            ret = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Dao.close(null,statement,connection);
        }

        //���ؽ��

        return ret;
    }

    // ���� ������Ա�ı��id �������޸�
    public static int update(BorrowPeopel b){
        Connection connection = null;
        PreparedStatement statement = null;
        int ret = 0;

        try {

            // ���õ����ݿ������
            connection = Dao.getConnection();

            // ����sql���
            String sql = "update borrowpeopel set peopelName=?,peopelTypeName=?,sex=?,age=?,phone=? where peopelId=?";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);

            // ռλ���滻
            statement.setString(1,b.getPeopelName());
            statement.setString(2,b.getPeopelTypeName());
            statement.setString(3,b.getSex());
            statement.setInt(4,b.getAge());
            statement.setString(5, b.getPhone());
            statement.setString(6,b.getPeopelId());

            // ִ��sql���
            ret = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Dao.close(null,statement,connection);
        }

        //���ؽ��

        return ret;
    }

    //����  ģ����ѯ����ȫ��
    public static List<BorrowPeopel> selectAll(String s){
      // ����ȫ������ģ����ѯ

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<BorrowPeopel> list =new ArrayList<BorrowPeopel>();

        try {
            //1.�������ݿ�
            connection = Dao.getConnection();

            // 2.ƴ��sql���
            String sql =  "select * from borrowpeopel where peopelname like" + "'%" + s + "%'" ;
            System.out.println(sql);

            //3.ִ��sql��ѯ���
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();

            //���԰�������Ĳ�ѯ����������ǰ��Ľ��������ơ�
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


    // ���� �����˱�Ž��в���
    public static List<BorrowPeopel> selectById(String id){
        // ����ȫ������ģ����ѯ

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<BorrowPeopel> list =new ArrayList<BorrowPeopel>();

        try {
            //1.�������ݿ�
            connection = Dao.getConnection();

            // 2.ƴ��sql���
            String sql =  "select * from borrowpeopel where peopelId=?" ;
            System.out.println(sql);

            //3.ִ��sql��ѯ���
            statement = connection.prepareStatement(sql);
            statement.setString(1,id);

            resultSet = statement.executeQuery();

            //���԰�������Ĳ�ѯ����������ǰ��Ľ��������ơ�
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
