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

    //��ɾ�Ĳ�

    // ��  ǰ�˽���д��user��������Ϣ����װ��һ��user�������
    public static int insertUser(User user) {

        Connection connection = null;
        PreparedStatement statement = null;
        int ret = 0;
        try {
            //1.���õ����ݿ������
            connection = Dao.getConnection();
            //2.ƴ��sql���
            String sql = "insert into user values (null,?,?)";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);


            // ռλ����ֹsqlע�룬��ò�Ҫֱ��ƴ�ӵ�sql�����
           statement.setString(1,user.getUserName());
           statement.setString(2,user.getPassword());
            //3.ִ��sql���
            ret = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.close(null, statement, connection);
        }
        return ret;
    }


    // ɾ  ��Ϊ�û�id�������������� ɾ������idɾ��
    public static int deleteUser(String name){
        int ret = 0;
        Connection connection = null;
        PreparedStatement statement = null;


        try {
            //1.�õ����ݿ�����
            connection = Dao.getConnection();
            //2.ƴ��sql���
            String sql = "delete from user where username =?";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            // ռλ���滻
            statement.setString(1,name);
            // 3.ִ��sql���
            ret = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.close(null, statement, connection);
        }

        return ret;
    }

    // ��  ǰ��ҳ��ֻ��ʾָ���û���������£��޸�����
    public static int updateUser(String name,String password){
        Connection connection = null;
        PreparedStatement statement = null;
        int ret = 0;
        try {
            //1.���õ����ݿ������
            connection = Dao.getConnection();
            //2.ƴ��sql���
            String sql = "update user set password=? where username= ?";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            // ռλ����ֹsqlע�룬��ò�Ҫֱ��ƴ�ӵ�sql�����
            statement.setString(1, password);
            statement.setString(2, name);

            //3.ִ��sql���
            ret = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.close(null, statement, connection);
        }
        return ret;
    }

    // ��  ǰ��ҳ�����ɾ�������û���ʱ�� չʾ�����û���Ϣ������ֱ�Ӳ�ѯ������Ϣ����
    public static List<User> selectUser(String s){
        List<User> list = new ArrayList<User>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            //1.���õ����ݿ������
            connection = Dao.getConnection();
            //2.ƴ��sql���
            String sql = "select *  from user where username like" + "'%" + s + "%'";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            //3.ִ��sql���
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
            //1.���õ����ݿ������
            connection = Dao.getConnection();
            //2.ƴ��sql���
            String sql = "select *  from user where username=?";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            // ռλ���滻
            statement.setString(1,name);

            //3.ִ��sql���
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
