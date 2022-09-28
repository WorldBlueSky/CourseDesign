package com.cd.administrator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenu extends JFrame implements ActionListener {
	private JMenuBar bar;//�˵���

	private JMenu menuEquipment,menuType,menuUser, menuBorrowEquipment, menuPeopel,menuEquipmentAsset ;//�˵�

	private JMenuItem itemEquipmentAdd, itemEquipmentSelect,
			itemEquipmentTypeManage,itemUserAdd,itemEquipmentScrap,itemEquipmentRepair,itemAgree,
			itemUserDelete,itemPeopelAdd ,itemPeopelSelectModify,itemUserUpdate, itemEquipmentBorrow, itemEquipmentReturn,itemPeopelManage;//�˵���


	public AdminMenu(String s){
		super(s);
		setSize(1000,800);
		setLocationRelativeTo(null);
		bar=new JMenuBar();
		setJMenuBar(bar);

		// ͼ����Ϣ����ģ��

		menuEquipment =new JMenu("�豸��Ϣ����");//�˵�

		itemEquipmentAdd =new JMenuItem("�豸����");//�˵���
		itemEquipmentAdd.addActionListener(this);

		itemEquipmentSelect =new JMenuItem("�豸��ѯ���޸�");
		itemEquipmentSelect.addActionListener(this);

		menuEquipment.add(itemEquipmentAdd);
		menuEquipment.add(itemEquipmentSelect);

       // ���͹���

		menuType = new JMenu("���͹���");

		itemEquipmentTypeManage = new JMenuItem("�豸���͹���");
		itemEquipmentTypeManage.addActionListener(this);

		itemPeopelManage = new JMenuItem("��Ա���͹���");
		itemPeopelManage.addActionListener(this);

		menuType.add(itemEquipmentTypeManage);
		menuType.add(itemPeopelManage);

		// �û�����

		menuUser = new JMenu("�û�����");

		itemUserAdd =new JMenuItem("ע���û�");
		itemUserAdd.addActionListener(this);

		itemUserDelete = new JMenuItem("ɾ���û�");
		itemUserDelete.addActionListener(this);

		itemUserUpdate = new JMenuItem("�޸�����");
		itemUserUpdate.addActionListener(this);

		menuEquipmentAsset = new JMenu("�豸�ʲ�����");
		itemEquipmentScrap = new JMenuItem("�豸���ϴ���");
		itemEquipmentRepair = new JMenuItem("�豸ά������");
		itemAgree = new JMenuItem("�豸ά����׼(�ϼ��쵼)");

       menuEquipmentAsset.add(itemEquipmentScrap);
       menuEquipmentAsset.add(itemEquipmentRepair);
       menuEquipmentAsset.add(itemAgree);

       itemEquipmentScrap.addActionListener(this);
       itemEquipmentRepair.addActionListener(this);
       itemAgree.addActionListener(this);


		menuUser.add(itemUserAdd);
		menuUser.add(itemUserDelete);
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

		itemPeopelAdd = new JMenuItem("��Ա��Ϣ����");
		itemPeopelAdd.addActionListener(this);

		itemPeopelSelectModify = new JMenuItem("��Ա��Ϣ��ѯ���޸�");
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
		new AdminMenu("ʵ�����豸����ϵͳ(����Ա)");
	}


	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==itemEquipmentAdd){
			new EquipmentAdd("�豸����");
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
			new BorrowPeopelAdd("������Ա��Ϣ����");
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
			new UserAdd("�û�ע��");
		}

		if(e.getSource()==itemUserDelete){
			new UserDelete();
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
