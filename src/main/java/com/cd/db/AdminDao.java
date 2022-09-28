package com.cd.db;

import com.cd.model.Administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {

    // ֻ��һ����ѯ�ķ��������бȶԵ�½���û���������
    public static Administrator selectByName(String name){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            //1.��ȡ���ݿ�����
            connection = Dao.getConnection();
            //2.����sql���
            String sql = "select * from administrator";

            statement = connection.prepareStatement(sql);

            //ִ��sql���
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                Administrator a = new Administrator();
                System.out.println("������һ���µĹ���Ա����!");
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
        // 3.�õ���¼

       return null;
    }

}
