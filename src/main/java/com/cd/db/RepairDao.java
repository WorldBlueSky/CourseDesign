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
        repair.setName("路由器");
        repair.setTypeName("网络设备类");
        repair.setNumber(10);
        repair.setWorkerName("李四");
        repair.setWorkDate("3天");
        int ret = insert(repair);
        if(ret==1){
            System.out.println("插入成功!");
        }else{
            System.out.println("插入失败!");
        }
    }

    // 增加一条维修记录
    public static int insert(Repair repair) {
        Connection connection = Dao.getConnection();
        PreparedStatement statement = null;

        ResultSet resultSet = null;
        int ret = 0;
        // 根据设备类型的类型名找到类型编号

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

    // 删除
    //根据设备名称删除设备信息
    public static int delete(int id) {
        // 先定义数据库连接，预处理
        Connection connection = null;
        PreparedStatement statement = null;
        int ret = 0;

        try{
            //1.获取到数据库连接
            connection = Dao.getConnection();
            //2.拼装sql语句
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



    // 修改
    public static int update(Repair repair) {
        Connection connection = Dao.getConnection();
        PreparedStatement statement = null;

        ResultSet resultSet = null;
        int ret = 0;
        // 根据设备类型的类型名找到类型编号

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

    // 查询所有
    public static List<Repair> selectAll(String s){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Repair> list = new ArrayList<Repair>();
        try {

            //1.获取数据库连接
            connection = Dao.getConnection();
            //2.构造sql语句
            String sql = "select * from repair where name like"+" '%"+s+"%'"  ;

            statement = connection.prepareStatement(sql);

            //执行sql语句
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Repair repair = new Repair();
                System.out.println("创建了一个维修记录对象!");
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
        // 3.拿到记录

        return null;
    }

    // 按编号id进行查询
    public static List<Repair> selectById(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Repair> list = new ArrayList<Repair>();
        try {

            //1.获取数据库连接
            connection = Dao.getConnection();
            //2.构造sql语句
            String sql = "select * from repair where id=?";

            statement = connection.prepareStatement(sql);

            statement.setInt(1,id);
            //执行sql语句
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                Repair repair = new Repair();
                System.out.println("创建了一个维修记录对象!");
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
        // 3.拿到记录

        return null;
    }

    public static int agree(int id){
        Connection connection = Dao.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int ret = 0;
        // 根据设备类型的类型名找到类型编号

        try {

            String sql="update repair set agree=? where id=?";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);

            statement.setString(1,"已批准");
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


