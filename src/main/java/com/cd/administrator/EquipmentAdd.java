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
	JComboBox cmbEquipmentType;//组合框
	private JButton btnAdd,btnReset,btnExit;
	public EquipmentAdd(String s){
		super(s);
		setSize(400,200);
		setLocationRelativeTo(null);
		panel=new JPanel(new BorderLayout());
		setContentPane(panel);
		//图书面板的信息
		GridLayout grid1=new GridLayout(4,4);//网格布局
		grid1.setHgap(5);
		grid1.setVgap(5);
		bookPanel=new JPanel(grid1);

		labEquipmentName =new JLabel("设备名称：");
		txtEquipmentName =new JTextField(15);

		labType=new JLabel("设备类别：");
		cmbEquipmentType=new JComboBox();
		cmbEquipmentType.addItem("网络设备类");
		cmbEquipmentType.addItem("玻璃仪器类");

		labNumber =new JLabel("增加数量：");
		txtNumber =new JTextField(12);


		labUnit =new JLabel("单位：");
		txtUnit =new JTextField(12);

		labLocation =new JLabel("存放位置：");
		txtLocation =new JTextField();

		labPurchaseDate =new JLabel("购买日期：");
		txtPurchaseDate =new JTextField(12);

		labPrice=new JLabel("单品价格：");
		txtPrice=new JTextField(12);

		//其余类似，作者，出版社，出版社日期，价格，课后大家补充。
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
		//将各组件加入到面板
		panel.add(bookPanel,BorderLayout.CENTER);
		btnPanel=new JPanel();


		btnAdd=new JButton("增加");
        btnAdd.addActionListener(this);

		btnReset=new JButton("重置");
		btnReset.addActionListener(this);

		btnExit=new JButton("退出");
		btnExit.addActionListener(this);


		btnPanel.add(btnAdd);
		btnPanel.add(btnReset);
		btnPanel.add(btnExit);
		panel.add(btnPanel,BorderLayout.SOUTH);		
		
		setVisible(true);		
	}
	public static void main(String[] args) {
		new EquipmentAdd("设备仪器添加");
	}

	public void actionPerformed(ActionEvent e){

		//重置和退出课后完成

		if(e.getSource()==btnAdd){//增加
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
				JOptionPane.showMessageDialog(null, "增加成功!");
			else
				JOptionPane.showMessageDialog(null, "增加失败!");
		}

		if(e.getSource()==btnExit){//退出
			System.exit(1);
		}
		if(e.getSource()==btnReset) {// 重置
			txtEquipmentName.setText("");
			txtNumber.setText("");
			txtUnit.setText("");
			txtLocation.setText("");
			txtPurchaseDate.setText("");
			txtPrice.setText("");
		}
	}

}
