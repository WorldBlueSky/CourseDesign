package com.cd.normaluser;

import com.cd.db.UserDao;
import com.cd.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UpdatePassword extends JFrame implements ActionListener {

    private JPanel myPanel;
    private JLabel labName,labPassword,labNewPassword,labConfirmPassword;
    private JTextField txtName;
    private JPasswordField txtPassword,txtNewPassword,txtConfirmPassword;
    private JButton btnConfirm, btnCancel;

    public  UpdatePassword(String name){
        super(name);//框架类设标题
        setSize(250,200);
        setLocationRelativeTo(null);
        myPanel=new JPanel();
        setContentPane(myPanel);

         // 定义标签
        labName=new JLabel("用户名：");
        labName.setHorizontalAlignment(SwingConstants.CENTER);

        labPassword=new JLabel("原密码：");
        labPassword.setHorizontalAlignment(SwingConstants.CENTER);

        labNewPassword = new JLabel("新密码：");
        labNewPassword.setHorizontalAlignment(SwingConstants.CENTER);

        labConfirmPassword = new JLabel("确认密码：");
        labConfirmPassword.setHorizontalAlignment(SwingConstants.CENTER);
        // 原用户名
        txtName=new JTextField(12);

        // 原密码
        txtPassword=new JPasswordField(12);
        txtPassword.setEchoChar('*');

        // 新密码
        txtNewPassword = new JPasswordField(12);
        txtPassword.setEchoChar('*');

        //确认新密码
        txtConfirmPassword = new JPasswordField(12);
        txtConfirmPassword.setEchoChar('*');

        btnConfirm=new JButton("确认");
        btnCancel =new JButton("取消");

        btnConfirm.addActionListener(this);
        btnCancel.addActionListener(this);

        // 面板添加组件
        myPanel.add(labName);
        myPanel.add(txtName);

        myPanel.add(labPassword);
        myPanel.add(txtPassword);

        myPanel.add(labNewPassword);
        myPanel.add(txtNewPassword);

        myPanel.add(labConfirmPassword);
        myPanel.add(txtConfirmPassword);

        myPanel.add(btnConfirm);
        myPanel.add(btnCancel);

        setVisible(true);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new UpdatePassword("修改密码");
    }


    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnConfirm){
            String  name= txtName.getText().trim();

            // 原密码
            char[] passwordchs = txtPassword.getPassword();
            String password  =new String(passwordchs);

            // 新密码
            char[] newPasswordchas = txtNewPassword.getPassword();
            String newPassword = new String(newPasswordchas);

            // 确认密码
            char[] confirmPasswordchs = txtConfirmPassword.getPassword();
            String confimPassword = new String(confirmPasswordchs);


            // 我就简单的写了，不加那么多检验了，只写关键的检验

            // 用户名和原密码在数据库中能否查到
            User user1 = new User();
            user1.setUserName(name);
            user1.setPassword(password);


//            List<User> list = UserDao.selectUserByName(name);
//            User user2 = list.get(0);
//
//            System.out.println(user2);
//            if(user2.getUserName()==null){
//                JOptionPane.showMessageDialog(null,"输入的用户名不存在，请重新输入!");
//                return;
//            }
//
//            if(!user1.getPassword().equals(user2.getPassword())){
//                JOptionPane.showMessageDialog(null,"输入原密码错误!");
//                return;
//            }

            // 新密码与确认新密码是否一致，检验
            if(!newPassword.equals(confimPassword)){
                JOptionPane.showMessageDialog(null,"新密码与确认密码不一致，请重新输入");
                txtNewPassword.setText("");
                txtConfirmPassword.setText("");
                return;
            }

            // 经过检验后无错误，那么进行修改
            // 我把数据表中的name字段设置成了unique，所以是唯一的,所以可以根据name拿到这条记录
            int ret = UserDao.updateUser(name,newPassword);
            if(ret==0){
                JOptionPane.showMessageDialog(null,"修改失败!");
            }else{
                JOptionPane.showMessageDialog(null,"修改成功!");
            }
        }

        if(e.getSource()==btnCancel){
            System.exit(1);
        }
    }
}
