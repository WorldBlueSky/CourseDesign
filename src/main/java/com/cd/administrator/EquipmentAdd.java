package com.cd.administrator;

import com.cd.db.EquipmentDao;
import com.cd.model.Equipment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EquipmentAdd extends JFrame implements ActionListener {
	private JPanel panel,bookPanel,btnPanel;
	private JLabel labEquipmentName,labType, labNumber, labUnit,
			labLocation, labPurchaseDate,labPrice;
	private JTextField txtEquipmentName, txtNumber, txtUnit,
			txtLocation, txtPurchaseDate,txtPrice;
	JComboBox cmbEquipmentType;//��Ͽ�
	private JButton btnAdd,btnReset,btnExit;
	public EquipmentAdd(String s){
		super(s);
		setSize(400,200);
		setLocationRelativeTo(null);
		panel=new JPanel(new BorderLayout());
		setContentPane(panel);
		//ͼ��������Ϣ
		GridLayout grid1=new GridLayout(4,4);//���񲼾�
		grid1.setHgap(5);
		grid1.setVgap(5);
		bookPanel=new JPanel(grid1);

		labEquipmentName =new JLabel("�豸���ƣ�");
		txtEquipmentName =new JTextField(15);

		labType=new JLabel("�豸���");
		cmbEquipmentType=new JComboBox();
		cmbEquipmentType.addItem("�����豸��");
		cmbEquipmentType.addItem("����������");

		labNumber =new JLabel("����������");
		txtNumber =new JTextField(12);


		labUnit =new JLabel("��λ��");
		txtUnit =new JTextField(12);

		labLocation =new JLabel("���λ�ã�");
		txtLocation =new JTextField();

		labPurchaseDate =new JLabel("�������ڣ�");
		txtPurchaseDate =new JTextField(12);

		labPrice=new JLabel("��Ʒ�۸�");
		txtPrice=new JTextField(12);

		//�������ƣ����ߣ������磬���������ڣ��۸񣬿κ��Ҳ��䡣
		bookPanel.add(labEquipmentName);
		bookPanel.add(txtEquipmentName);
		bookPanel.add(labType);
		bookPanel.add(cmbEquipmentType);
		bookPanel.add(labNumber);
		bookPanel.add(txtNumber);
		bookPanel.add(labUnit);
		bookPanel.add(txtUnit);
		bookPanel.add(labLocation);
		bookPanel.add(txtLocation);
		bookPanel.add(labPurchaseDate);
		bookPanel.add(txtPurchaseDate);
		bookPanel.add(labPrice);
		bookPanel.add(txtPrice);
		//����������뵽���
		panel.add(bookPanel,BorderLayout.CENTER);
		btnPanel=new JPanel();


		btnAdd=new JButton("����");
        btnAdd.addActionListener(this);

		btnReset=new JButton("����");
		btnReset.addActionListener(this);

		btnExit=new JButton("�˳�");
		btnExit.addActionListener(this);


		btnPanel.add(btnAdd);
		btnPanel.add(btnReset);
		btnPanel.add(btnExit);
		panel.add(btnPanel,BorderLayout.SOUTH);		
		
		setVisible(true);		
	}
	public static void main(String[] args) {
		new EquipmentAdd("�豸�������");
	}

	public void actionPerformed(ActionEvent e){

		//���ú��˳��κ����

		if(e.getSource()==btnAdd){//����
			Equipment e1=new Equipment();
			e1.setId(0);
			e1.setName(txtEquipmentName.getText());
			e1.setTypeName(cmbEquipmentType.getSelectedItem().toString().trim());
			e1.setNumber(Integer.parseInt(txtNumber.getText()));
			e1.setUnit(txtUnit.getText());
			e1.setLocation(txtLocation.getText());
			e1.setPurchaseDate(txtPurchaseDate.getText());
			e1.setPrice(Double.parseDouble(txtPrice.getText().trim()));
			String typeName=cmbEquipmentType.getSelectedItem().toString().trim();
			int ret= EquipmentDao.insertEquipment(e1, typeName);
			if(ret==1)
				JOptionPane.showMessageDialog(null, "���ӳɹ�!");
			else
				JOptionPane.showMessageDialog(null, "����ʧ��!");
		}

		if(e.getSource()==btnExit){//�˳�
			System.exit(1);
		}
		if(e.getSource()==btnReset) {// ����
			txtEquipmentName.setText("");
			txtNumber.setText("");
			txtUnit.setText("");
			txtLocation.setText("");
			txtPurchaseDate.setText("");
			txtPrice.setText("");
		}
	}

}
