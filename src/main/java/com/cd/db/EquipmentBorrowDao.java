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

    // ��ɾ�Ĳ�

    // ״̬Ĭ����δ�黹

    // �������黹��������ô�ѹ黹״̬ �޸ĳ�  �ѹ黹

//    insert into equipmentBorrow(borrowid,name,typename,date,number,peopel) values(0,"����","������","2020-2-2",20,"������");

    // ��
    public static int insert(EquipmentBorrow b){
        PreparedStatement statement = null;
        Connection connection = null;
        int ret =0;


        try {
            // �õ����ݿ�����
            connection = Dao.getConnection();

            // ����sql���
            String sql = "insert into equipmentBorrow(name,typeName,borrowDate,borrowNumber,peopel) values(?,?,?,?,?)";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            //ռλ���滻
            statement.setString(1,b.getName());
            statement.setString(2,b.getTypeName());
            statement.setString(3,b.getBorrowDate());
            statement.setInt(4,b.getBorrowNumber());
            statement.setString(5,b.getPeopel());

            // ִ��sql���
            ret = statement.executeUpdate();

           // ======================================================
            //������������һ�������¼��ͬʱ��һ���ǵ��޸�ԭ�豸���е�ǰ�豸��������Ϣ


            /**
             * �Ȳ��ҵ�ԭĿ���е��豸����
             */

            int preNum = selectNum(b.getName());

            String sqlUpdate = "update equipment set number =? where name=?";
            statement = connection.prepareStatement(sqlUpdate);

            // ��ԭ�豸���� ��� ������֮�������

            int finalNum = preNum-b.getBorrowNumber();

            statement.setInt(1,finalNum);
            statement.setString(2,b.getName());

            int a =statement.executeUpdate();
            System.out.println("ԭ�����豸��Ϣ�Ѿ�����޸�!"+"Ŀǰ�޸���: "+a);

            //======================================================

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Dao.close(null,statement,connection);
        }

        return ret;
    }

    // ��
    public static int update(EquipmentBorrow b){
        PreparedStatement statement = null;
        Connection connection = null;
        int ret =0;

        try {
            // �õ����ݿ�����
            connection = Dao.getConnection();

            // ����sql���
            String sql = "update equipmentBorrow set returnDate=?,state=?,returnNumber=? where borrowId=?";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);

            //ռλ���滻
            statement.setString(1,b.getReturnDate());
            statement.setString(2,"�ѹ黹");
            statement.setInt(3,b.getReturnNumber());
            statement.setInt(4,b.getBorrowId());


            // ִ��sql���
            ret = statement.executeUpdate();
            System.out.println("�黹֮��ķ���ֵ�ǣ�"+ret);

            // ======================================================
            //����������޸�һ�������¼(�黹)��ͬʱ��һ���ǵ��޸�ԭ�豸���е�ǰ�豸��������Ϣ


            /**
             * �Ȳ��ҵ�ԭĿ���е��豸����
             */


            int preNum = selectNum(b.getName());
            System.out.println("����֮���������"+preNum);

            String sqlUpdate = "update equipment set number =? where name=?";
            statement = connection.prepareStatement(sqlUpdate);

            // ��ԭ�豸���� ��� ������֮�������

            int returnNum =b.getReturnNumber();
            System.out.println("�黹��������"+returnNum);

            int finalNum = preNum+b.getReturnNumber();
            System.out.println("�黹���������"+finalNum);

            statement.setInt(1,finalNum);
            statement.setString(2,b.getName());

            int a =statement.executeUpdate();
            if(a==1){
                System.out.println("�黹���޸�ԭ���ݳɹ�!");
            }else{
                System.out.println("�黹���޸�ԭ����ʧ��!");
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


    // ��
    public static List<EquipmentBorrow> selectAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<EquipmentBorrow> list = new ArrayList<EquipmentBorrow>();

        try {
            // �õ����ݿ�����
            connection = Dao.getConnection();

            // ƴ��sql���
            String sql = "select * from equipmentborrow ";
            statement = connection.prepareStatement(sql);


            // ���ղ�ѯ��¼
            resultSet =  statement.executeQuery();

            // �õ���ѯ��¼�е�number��Ϣ
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

        // ���ʧ�ܣ���ô����null
        return list;
    }



    public static List<EquipmentBorrow> selectById(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<EquipmentBorrow> list = new ArrayList<EquipmentBorrow>();

        try {
            // �õ����ݿ�����
            connection = Dao.getConnection();

            // ƴ��sql���
            String sql = "select * from equipmentborrow where borrowId=? ";
            statement = connection.prepareStatement(sql);

             statement.setInt(1,id);
            // ���ղ�ѯ��¼
            resultSet =  statement.executeQuery();

            // �õ���ѯ��¼�е�number��Ϣ
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

        // ���ʧ�ܣ���ô����null
        return list;
    }



    // name ��Ψһ�ģ����Ը���name���ҵ���ǰ�豸������
    public static int selectNum(String name){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int num = 0;

        try {
            // �õ����ݿ�����
            connection = Dao.getConnection();

            // ƴ��sql���
            String sql = "select * from equipment where name=?";
            statement = connection.prepareStatement(sql);
            // ռλ���滻
            statement.setString(1,name);

            // ���ղ�ѯ��¼
           resultSet =  statement.executeQuery();

            // �õ���ѯ��¼�е�number��Ϣ
            while(resultSet.next()) {
                num = resultSet.getInt("number");
            }
            return num;


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Dao.close(resultSet,statement,connection);
        }

        // �������ʧ�ܣ���ô����-1
        return -1;
    }

}
