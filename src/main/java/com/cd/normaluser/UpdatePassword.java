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
        super(name);//����������
        setSize(250,200);
        setLocationRelativeTo(null);
        myPanel=new JPanel();
        setContentPane(myPanel);

         // �����ǩ
        labName=new JLabel("�û�����");
        labName.setHorizontalAlignment(SwingConstants.CENTER);

        labPassword=new JLabel("ԭ���룺");
        labPassword.setHorizontalAlignment(SwingConstants.CENTER);

        labNewPassword = new JLabel("�����룺");
        labNewPassword.setHorizontalAlignment(SwingConstants.CENTER);

        labConfirmPassword = new JLabel("ȷ�����룺");
        labConfirmPassword.setHorizontalAlignment(SwingConstants.CENTER);
        // ԭ�û���
        txtName=new JTextField(12);

        // ԭ����
        txtPassword=new JPasswordField(12);
        txtPassword.setEchoChar('*');

        // ������
        txtNewPassword = new JPasswordField(12);
        txtPassword.setEchoChar('*');

        //ȷ��������
        txtConfirmPassword = new JPasswordField(12);
        txtConfirmPassword.setEchoChar('*');

        btnConfirm=new JButton("ȷ��");
        btnCancel =new JButton("ȡ��");

        btnConfirm.addActionListener(this);
        btnCancel.addActionListener(this);

        // ���������
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
        new UpdatePassword("�޸�����");
    }


    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnConfirm){
            String  name= txtName.getText().trim();

            // ԭ����
            char[] passwordchs = txtPassword.getPassword();
            String password  =new String(passwordchs);

            // ������
            char[] newPasswordchas = txtNewPassword.getPassword();
            String newPassword = new String(newPasswordchas);

            // ȷ������
            char[] confirmPasswordchs = txtConfirmPassword.getPassword();
            String confimPassword = new String(confirmPasswordchs);


            // �Ҿͼ򵥵�д�ˣ�������ô������ˣ�ֻд�ؼ��ļ���

            // �û�����ԭ���������ݿ����ܷ�鵽
            User user1 = new User();
            user1.setUserName(name);
            user1.setPassword(password);


//            List<User> list = UserDao.selectUserByName(name);
//            User user2 = list.get(0);
//
//            System.out.println(user2);
//            if(user2.getUserName()==null){
//                JOptionPane.showMessageDialog(null,"������û��������ڣ�����������!");
//                return;
//            }
//
//            if(!user1.getPassword().equals(user2.getPassword())){
//                JOptionPane.showMessageDialog(null,"����ԭ�������!");
//                return;
//            }

            // ��������ȷ���������Ƿ�һ�£�����
            if(!newPassword.equals(confimPassword)){
                JOptionPane.showMessageDialog(null,"��������ȷ�����벻һ�£�����������");
                txtNewPassword.setText("");
                txtConfirmPassword.setText("");
                return;
            }

            // ����������޴�����ô�����޸�
            // �Ұ����ݱ��е�name�ֶ����ó���unique��������Ψһ��,���Կ��Ը���name�õ�������¼
            int ret = UserDao.updateUser(name,newPassword);
            if(ret==0){
                JOptionPane.showMessageDialog(null,"�޸�ʧ��!");
            }else{
                JOptionPane.showMessageDialog(null,"�޸ĳɹ�!");
            }
        }

        if(e.getSource()==btnCancel){
            System.exit(1);
        }
    }
}
