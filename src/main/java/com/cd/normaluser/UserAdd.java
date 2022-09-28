package com.cd.normaluser;

import com.cd.db.UserDao;
import com.cd.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserAdd extends JFrame implements ActionListener {
    private JPanel myPanel;
    private JLabel labName,labPassword;
    private JTextField txtName;
    private JPasswordField txtPassword;
    private JButton btnAdd, btnCancel;

    public UserAdd(String name){
        super(name);//框架类设标题
        setSize(250,150);
        setLocationRelativeTo(null);

        myPanel=new JPanel();
        setContentPane(myPanel);

        labName=new JLabel("用户名：");
        labPassword=new JLabel("密  码：");

        txtName=new JTextField(12);
        txtPassword=new JPasswordField(12);
        txtPassword.setEchoChar('*');

        btnAdd =new JButton("添加");
        btnCancel =new JButton("取消");

        btnAdd.addActionListener(this);
        btnCancel.addActionListener(this);

        myPanel.add(labName);
        myPanel.add(txtName);
        myPanel.add(labPassword);
        myPanel.add(txtPassword);
        myPanel.add(btnAdd);
        myPanel.add(btnCancel);

        setVisible(true);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new UserAdd("添加用户");
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==btnAdd){
            // 添加用户
            // 往数据库中的user表中插入 这个一条用户信息，相当于注册
            String username = txtName.getText();
             char[] c = txtPassword.getPassword();
             String password = new String(c);

             if(username.equals("")|| password.equals("")){
                 JOptionPane.showMessageDialog(null,"用户名或密码不能为空!");
                 return;
             }

             User user = new User();
             user.setUserName(username);
             user.setPassword(password);

             // username不能重复 ,给数据库中的user表中的 username字段设置成not null
            int ret = UserDao.insertUser(user);
            if(ret==1){
                JOptionPane.showMessageDialog(null,"插入成功!");
            }else{
                JOptionPane.showMessageDialog(null,"插入失败!");
            }

        }

        if(e.getSource()==btnCancel){
            // 退出
            System.exit(1);
        }


    }

}
