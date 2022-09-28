package com.cd.administrator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenu extends JFrame implements ActionListener {
	private JMenuBar bar;//菜单条

	private JMenu menuEquipment,menuType,menuUser, menuBorrowEquipment, menuPeopel,menuEquipmentAsset ;//菜单

	private JMenuItem itemEquipmentAdd, itemEquipmentSelect,
			itemEquipmentTypeManage,itemUserAdd,itemEquipmentScrap,itemEquipmentRepair,itemAgree,
			itemUserDelete,itemPeopelAdd ,itemPeopelSelectModify,itemUserUpdate, itemEquipmentBorrow, itemEquipmentReturn,itemPeopelManage;//菜单项


	public AdminMenu(String s){
		super(s);
		setSize(1000,800);
		setLocationRelativeTo(null);
		bar=new JMenuBar();
		setJMenuBar(bar);

		// 图书信息管理模块

		menuEquipment =new JMenu("设备信息管理");//菜单

		itemEquipmentAdd =new JMenuItem("设备增加");//菜单项
		itemEquipmentAdd.addActionListener(this);

		itemEquipmentSelect =new JMenuItem("设备查询与修改");
		itemEquipmentSelect.addActionListener(this);

		menuEquipment.add(itemEquipmentAdd);
		menuEquipment.add(itemEquipmentSelect);

       // 类型管理

		menuType = new JMenu("类型管理");

		itemEquipmentTypeManage = new JMenuItem("设备类型管理");
		itemEquipmentTypeManage.addActionListener(this);

		itemPeopelManage = new JMenuItem("人员类型管理");
		itemPeopelManage.addActionListener(this);

		menuType.add(itemEquipmentTypeManage);
		menuType.add(itemPeopelManage);

		// 用户管理

		menuUser = new JMenu("用户管理");

		itemUserAdd =new JMenuItem("注册用户");
		itemUserAdd.addActionListener(this);

		itemUserDelete = new JMenuItem("删除用户");
		itemUserDelete.addActionListener(this);

		itemUserUpdate = new JMenuItem("修改密码");
		itemUserUpdate.addActionListener(this);

		menuEquipmentAsset = new JMenu("设备资产管理");
		itemEquipmentScrap = new JMenuItem("设备报废处理");
		itemEquipmentRepair = new JMenuItem("设备维修申请");
		itemAgree = new JMenuItem("设备维修批准(上级领导)");

       menuEquipmentAsset.add(itemEquipmentScrap);
       menuEquipmentAsset.add(itemEquipmentRepair);
       menuEquipmentAsset.add(itemAgree);

       itemEquipmentScrap.addActionListener(this);
       itemEquipmentRepair.addActionListener(this);
       itemAgree.addActionListener(this);


		menuUser.add(itemUserAdd);
		menuUser.add(itemUserDelete);
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

		itemPeopelAdd = new JMenuItem("人员信息增加");
		itemPeopelAdd.addActionListener(this);

		itemPeopelSelectModify = new JMenuItem("人员信息查询与修改");
		itemPeopelSelectModify.addActionListener(this);

		menuPeopel.add(itemPeopelAdd);
		menuPeopel.add(itemPeopelSelectModify);

		bar.add(menuEquipment);
		bar.add(menuPeopel);
		bar.add(menuType);
		bar.add(menuUser);
		bar.add(menuBorrowEquipment);
		bar.add(menuEquipmentAsset);

		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AdminMenu("实验室设备管理系统(管理员)");
	}


	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==itemEquipmentAdd){
			new EquipmentAdd("设备增加");
		}

		if(e.getSource()==itemAgree){
			new Agree();
		}

		if(e.getSource()==itemEquipmentRepair){
			new EquipmentRepair();
		}

		if(e.getSource()==itemEquipmentScrap){
			new EquipmentScrap();
		}

		if(e.getSource()==itemPeopelAdd){
			new BorrowPeopelAdd("申请人员信息增加");
		}

		if(e.getSource()==itemPeopelManage){
			new BorrowPeopelTypeManage();
		}

		if(e.getSource()==itemPeopelSelectModify){
			new BorrowPeopelSelectModify();
		}

		if(e.getSource()==itemEquipmentSelect){
			new EquipmentSelectModify();
		}



		if(e.getSource()==itemEquipmentTypeManage){
			new EquipmentTypeManage();
		}

		if(e.getSource()==itemUserAdd){
			new UserAdd("用户注册");
		}

		if(e.getSource()==itemUserDelete){
			new UserDelete();
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
