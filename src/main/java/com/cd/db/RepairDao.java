package com.cd.db;
import com.cd.model.Repair;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepairDao {

    public static void main(String[] args) {
        Repair repair =new Repair();
        repair.setName("·����");
        repair.setTypeName("�����豸��");
        repair.setNumber(10);
        repair.setWorkerName("����");
        repair.setWorkDate("3��");
        int ret = insert(repair);
        if(ret==1){
            System.out.println("����ɹ�!");
        }else{
            System.out.println("����ʧ��!");
        }
    }

    // ����һ��ά�޼�¼
    public static int insert(Repair repair) {
        Connection connection = Dao.getConnection();
        PreparedStatement statement = null;

        ResultSet resultSet = null;
        int ret = 0;
        // �����豸���͵��������ҵ����ͱ��

        try {

            String sql="insert into repair(id,name,typename,number,workerName,workDate) values (null,?,?,?,?,?)";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            statement.setString(1,repair.getName());
            statement.setString(2,repair.getTypeName());
            statement.setInt(3,repair.getNumber());
            statement.setString(4,repair.getWorkerName());
            statement.setString(5,repair.getWorkDate());

            ret = statement.executeUpdate();
            System.out.println(sql);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            Dao.close(resultSet,statement,connection);
        }

        return ret;
    }

    // ɾ��
    //�����豸����ɾ���豸��Ϣ
    public static int delete(int id) {
        // �ȶ������ݿ����ӣ�Ԥ����
        Connection connection = null;
        PreparedStatement statement = null;
        int ret = 0;

        try{
            //1.��ȡ�����ݿ�����
            connection = Dao.getConnection();
            //2.ƴװsql���
            String sql="delete from repair where id=?";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);

            ret = statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            Dao.close(null,statement,connection);
        }
        return ret;
    }



    // �޸�
    public static int update(Repair repair) {
        Connection connection = Dao.getConnection();
        PreparedStatement statement = null;

        ResultSet resultSet = null;
        int ret = 0;
        // �����豸���͵��������ҵ����ͱ��

        try {

            String sql="update repair set name=?,typename=?,number=?,workerName=?,workDate=? where id=?";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            statement.setString(1,repair.getName());
            statement.setString(2,repair.getTypeName());
            statement.setInt(3,repair.getNumber());
            statement.setString(4,repair.getWorkerName());
            statement.setString(5,repair.getWorkDate());
            statement.setInt(6,repair.getId());

            ret = statement.executeUpdate();
            System.out.println(sql);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            Dao.close(resultSet,statement,connection);
        }

        return ret;
    }

    // ��ѯ����
    public static List<Repair> selectAll(String s){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Repair> list = new ArrayList<Repair>();
        try {

            //1.��ȡ���ݿ�����
            connection = Dao.getConnection();
            //2.����sql���
            String sql = "select * from repair where name like"+" '%"+s+"%'"  ;

            statement = connection.prepareStatement(sql);

            //ִ��sql���
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Repair repair = new Repair();
                System.out.println("������һ��ά�޼�¼����!");
                repair.setId(resultSet.getInt("id"));
                repair.setName(resultSet.getString("name"));
                repair.setTypeName(resultSet.getString("typeName"));
                repair.setNumber(resultSet.getInt("number"));
                repair.setWorkerName(resultSet.getString("workerName"));
                repair.setWorkDate(resultSet.getString("workDate"));
                repair.setAgree(resultSet.getString("agree"));
                System.out.println(repair);
                list.add(repair);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Dao.close(resultSet,statement,connection);
        }
        // 3.�õ���¼

        return null;
    }

    // �����id���в�ѯ
    public static List<Repair> selectById(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Repair> list = new ArrayList<Repair>();
        try {

            //1.��ȡ���ݿ�����
            connection = Dao.getConnection();
            //2.����sql���
            String sql = "select * from repair where id=?";

            statement = connection.prepareStatement(sql);

            statement.setInt(1,id);
            //ִ��sql���
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                Repair repair = new Repair();
                System.out.println("������һ��ά�޼�¼����!");
                repair.setId(resultSet.getInt("id"));
                repair.setName(resultSet.getString("name"));
                repair.setTypeName(resultSet.getString("typeName"));
                repair.setNumber(resultSet.getInt("number"));
                repair.setWorkerName(resultSet.getString("workerName"));
                repair.setWorkDate(resultSet.getString("workDate"));
                repair.setAgree(resultSet.getString("agree"));
                list.add(repair);
            }
        return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Dao.close(resultSet,statement,connection);
        }
        // 3.�õ���¼

        return null;
    }

    public static int agree(int id){
        Connection connection = Dao.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int ret = 0;
        // �����豸���͵��������ҵ����ͱ��

        try {

            String sql="update repair set agree=? where id=?";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);

            statement.setString(1,"����׼");
            statement.setInt(2,id);

            ret = statement.executeUpdate();
            System.out.println(sql);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            Dao.close(resultSet,statement,connection);
        }

        return ret;
    }


}


