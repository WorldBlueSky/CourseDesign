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


		super(name);//����������
		setSize(300,160);
		setLocationRelativeTo(null);
		myPanel=new JPanel();
		setContentPane(myPanel);

		labName=new JLabel("�û�����");		
		labPassword=new JLabel("��  �룺");

		txtName=new JTextField(18);
		txtPassword=new JPasswordField(18);
		txtPassword.setEchoChar('*');

		cmbSelect = new JComboBox();
		cmbSelect.addItem("��ͨ�û�");
		cmbSelect.addItem("����Ա");

		btnConfirm=new JButton("��¼");
		btnConfirm.addActionListener(this);
		btnReset=new JButton("����");
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

		new Login("��¼");
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource()==btnConfirm) {
			//�ȶ��û���������,Ӧ�����ݿ��û������ȶ�
			//�����ж��û����������Ƿ� �����ݿ��е��û�һ�£����ݿ��������û���

			char[] c1 = txtPassword.getPassword();//������ȡ����
			String pwd = new String(c1);
			String username = txtName.getText().trim();

			if (cmbSelect.getSelectedItem().toString().equals("����Ա")) {

//				��ѯadministrator�������û���������ȶ�
				Administrator a = AdminDao.selectByName(username);

				System.out.println(a);

				// �ȶԳɹ����� ����Ա����
				if(a.getPassword().equals(pwd)){
					new AdminMenu("ʵ�����豸����ϵͳ");
				}


			}else if (cmbSelect.getSelectedItem().toString().equals("��ͨ�û�")){

//				��ѯuser�������û���������ȶ�
				User user = UserDao.selectUserByName(username);

				if(user.getPassword().equals(pwd)){
					// �ȶԳɹ����� ��ͨ�û�����
					new NormalMenu("ʵ�����豸����ϵͳ");
				}

			}

		}

		if(e.getActionCommand().equals("����")){
			//����ı������������
			txtName.setText("");
			txtPassword.setText("");
		}
	}

}
