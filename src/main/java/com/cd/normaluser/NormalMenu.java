package com.cd.normaluser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NormalMenu extends JFrame implements ActionListener {
	private JMenuBar bar;//�˵���

	private JMenu menuEquipment,menuUser, menuBorrowEquipment, menuPeopel;//�˵�

	private JMenuItem  itemEquipmentSelect, itemUserAdd, itemPeopelAdd ,itemPeopelSelectModify,itemUserUpdate, itemEquipmentBorrow, itemEquipmentReturn,itemPeopelManage;//�˵���


	public NormalMenu(String s){
		super(s);
		setSize(1000,800);
		setLocationRelativeTo(null);
		bar=new JMenuBar();
		setJMenuBar(bar);

		// ͼ����Ϣ����ģ��

		menuEquipment =new JMenu("�豸��Ϣ����");//�˵�


		itemEquipmentSelect =new JMenuItem("�豸��Ϣ��ѯ");
		itemEquipmentSelect.addActionListener(this);


		menuEquipment.add(itemEquipmentSelect);

       // ���͹���



		// �û�����

		menuUser = new JMenu("�û�����");

		itemUserAdd =new JMenuItem("ע���û�");
		itemUserAdd.addActionListener(this);



		itemUserUpdate = new JMenuItem("�޸�����");
		itemUserUpdate.addActionListener(this);

		menuUser.add(itemUserAdd);


		menuUser.add(itemUserUpdate);

		// ���Ĺ���

		menuBorrowEquipment = new JMenu("�豸�������");

		itemEquipmentBorrow = new JMenuItem("�豸�������");
		itemEquipmentBorrow.addActionListener(this);

		itemEquipmentReturn = new JMenuItem("�豸�黹����");
		itemEquipmentReturn.addActionListener(this);

		menuBorrowEquipment.add(itemEquipmentBorrow);
		menuBorrowEquipment.add(itemEquipmentReturn);




		// ������Ա��Ϣ����
		 menuPeopel = new JMenu("������Ա����");

		itemPeopelAdd = new JMenuItem("��Ա��Ϣע��");
		itemPeopelAdd.addActionListener(this);

		itemPeopelSelectModify = new JMenuItem("��Ա��Ϣ��ѯ");
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
		new NormalMenu("ʵ�����豸����ϵͳ(��ͨ�û�)");
	}


	public void actionPerformed(ActionEvent e) {


		if(e.getSource()==itemPeopelAdd){
			new BorrowPeopelAdd("������Ա��Ϣ����");
		}



		if(e.getSource()==itemPeopelSelectModify){
			new BorrowPeopelSelect();
		}

		if(e.getSource()==itemEquipmentSelect){
			new EquipmentSelect();
		}



		if(e.getSource()==itemUserAdd){
			new UserAdd("�û�ע��");
		}


		if(e.getSource()==itemUserUpdate){
			new UpdatePassword("�޸�����");
		}

		if(e.getSource()==itemEquipmentBorrow){
			new EquipmentBorrowManage();
		}

		if(e.getSource()==itemEquipmentReturn){
			new EquipmentReturnManage();
		}
	}
}
