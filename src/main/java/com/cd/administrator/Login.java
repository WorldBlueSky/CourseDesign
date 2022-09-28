package com.cd.administrator;

import com.cd.db.AdminDao;
import com.cd.db.UserDao;
import com.cd.model.Administrator;
import com.cd.model.User;
import com.cd.normaluser.NormalMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Login extends JFrame implements ActionListener {

	private JPanel myPanel;

	private JLabel labName,labPassword;

	private JTextField txtName;

	private JPasswordField txtPassword;

	private JComboBox cmbSelect;

	private JButton btnConfirm,btnReset;

	public Login(String name){


		super(name);//框架类设标题
		setSize(300,160);
		setLocationRelativeTo(null);
		myPanel=new JPanel();
		setContentPane(myPanel);

		labName=new JLabel("用户名：");		
		labPassword=new JLabel("密  码：");

		txtName=new JTextField(18);
		txtPassword=new JPasswordField(18);
		txtPassword.setEchoChar('*');

		cmbSelect = new JComboBox();
		cmbSelect.addItem("普通用户");
		cmbSelect.addItem("管理员");

		btnConfirm=new JButton("登录");
		btnConfirm.addActionListener(this);
		btnReset=new JButton("重置");
		btnReset.addActionListener(this);


		myPanel.add(labName);
		myPanel.add(txtName);
		myPanel.add(labPassword);
		myPanel.add(txtPassword);
		myPanel.add(cmbSelect);

		myPanel.add(btnConfirm);
		myPanel.add(btnReset);


		setVisible(true);


	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Login("登录");
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource()==btnConfirm) {
			//比对用户名和密码,应与数据库用户表作比对
			//这里判断用户名和密码是否 和数据库中的用户一致，数据库中两张用户表

			char[] c1 = txtPassword.getPassword();//密码框获取密码
			String pwd = new String(c1);
			String username = txtName.getText().trim();

			if (cmbSelect.getSelectedItem().toString().equals("管理员")) {

//				查询administrator表，进行用户名和密码比对
				Administrator a = AdminDao.selectByName(username);

				System.out.println(a);

				// 比对成功进入 管理员界面
				if(a.getPassword().equals(pwd)){
					new AdminMenu("实验室设备管理系统");
				}


			}else if (cmbSelect.getSelectedItem().toString().equals("普通用户")){

//				查询user表，进行用户名和密码比对
				User user = UserDao.selectUserByName(username);

				if(user.getPassword().equals(pwd)){
					// 比对成功进入 普通用户界面
					new NormalMenu("实验室设备管理系统");
				}

			}

		}

		if(e.getActionCommand().equals("重置")){
			//清除文本框输入的内容
			txtName.setText("");
			txtPassword.setText("");
		}
	}

}
