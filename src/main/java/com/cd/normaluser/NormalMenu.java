package com.cd.normaluser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NormalMenu extends JFrame implements ActionListener {
	private JMenuBar bar;//菜单条

	private JMenu menuEquipment,menuUser, menuBorrowEquipment, menuPeopel;//菜单

	private JMenuItem  itemEquipmentSelect, itemUserAdd, itemPeopelAdd ,itemPeopelSelectModify,itemUserUpdate, itemEquipmentBorrow, itemEquipmentReturn,itemPeopelManage;//菜单项


	public NormalMenu(String s){
		super(s);
		setSize(1000,800);
		setLocationRelativeTo(null);
		bar=new JMenuBar();
		setJMenuBar(bar);

		// 图书信息管理模块

		menuEquipment =new JMenu("设备信息管理");//菜单


		itemEquipmentSelect =new JMenuItem("设备信息查询");
		itemEquipmentSelect.addActionListener(this);


		menuEquipment.add(itemEquipmentSelect);

       // 类型管理



		// 用户管理

		menuUser = new JMenu("用户管理");

		itemUserAdd =new JMenuItem("注册用户");
		itemUserAdd.addActionListener(this);



		itemUserUpdate = new JMenuItem("修改密码");
		itemUserUpdate.addActionListener(this);

		menuUser.add(itemUserAdd);


		menuUser.add(itemUserUpdate);

		// 借阅管理

		menuBorrowEquipment = new JMenu("设备申请管理");

		itemEquipmentBorrow = new JMenuItem("设备申请管理");
		itemEquipmentBorrow.addActionListener(this);

		itemEquipmentReturn = new JMenuItem("设备归还管理");
		itemEquipmentReturn.addActionListener(this);

		menuBorrowEquipment.add(itemEquipmentBorrow);
		menuBorrowEquipment.add(itemEquipmentReturn);




		// 申请人员信息管理
		 menuPeopel = new JMenu("申请人员管理");

		itemPeopelAdd = new JMenuItem("人员信息注册");
		itemPeopelAdd.addActionListener(this);

		itemPeopelSelectModify = new JMenuItem("人员信息查询");
		itemPeopelSelectModify.addActionListener(this);

		menuPeopel.add(itemPeopelAdd);
		menuPeopel.add(itemPeopelSelectModify);

		bar.add(menuEquipment);
		bar.add(menuPeopel);
		bar.add(menuUser);
		bar.add(menuBorrowEquipment);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new NormalMenu("实验室设备管理系统(普通用户)");
	}


	public void actionPerformed(ActionEvent e) {


		if(e.getSource()==itemPeopelAdd){
			new BorrowPeopelAdd("申请人员信息增加");
		}



		if(e.getSource()==itemPeopelSelectModify){
			new BorrowPeopelSelect();
		}

		if(e.getSource()==itemEquipmentSelect){
			new EquipmentSelect();
		}



		if(e.getSource()==itemUserAdd){
			new UserAdd("用户注册");
		}


		if(e.getSource()==itemUserUpdate){
			new UpdatePassword("修改密码");
		}

		if(e.getSource()==itemEquipmentBorrow){
			new EquipmentBorrowManage();
		}

		if(e.getSource()==itemEquipmentReturn){
			new EquipmentReturnManage();
		}
	}
}
