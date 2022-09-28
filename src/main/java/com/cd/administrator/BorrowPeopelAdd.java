package com.cd.administrator;

import com.cd.db.BorrowPeopelDao;
import com.cd.model.BorrowPeopel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BorrowPeopelAdd extends JFrame implements ActionListener {
    private JPanel panel,bookPanel,btnPanel;

    // ��ǩ
    private JLabel labPeopelID, labName,labType, labSex, labAge,
            labPhone;

    // �ı�����
    private JTextField txtPeopelID,txtName, txtSex,
            txtAge, txtPhone;

    JComboBox cmbType;//��Ͽ�

    // �����ť
    private JButton btnAdd,btnReset,btnExit;


    public BorrowPeopelAdd(String s){
        super(s);
        setSize(400,200);
        setLocationRelativeTo(null);
        panel=new JPanel(new BorderLayout());
        setContentPane(panel);
        //ͼ��������Ϣ
        GridLayout grid1=new GridLayout(3,1);//���񲼾�
        grid1.setHgap(5);
        grid1.setVgap(5);
        bookPanel=new JPanel(grid1);

        labPeopelID =new JLabel("���:");
        labPeopelID.setHorizontalAlignment(SwingConstants.CENTER);//����
        txtPeopelID =new JTextField(15);

        labName = new JLabel("����:");
        labName.setHorizontalAlignment(SwingConstants.CENTER);
        txtName=new JTextField(12);

        labType =new JLabel("���");
        labType.setHorizontalAlignment(SwingConstants.CENTER);
        cmbType =new JComboBox();
        cmbType.addItem("��ʦ");
        cmbType.addItem("ѧ��");


        labSex =new JLabel("�Ա�:");
        labSex.setHorizontalAlignment(SwingConstants.CENTER);
        txtSex =new JTextField(12);



        labAge =new JLabel("����:");
        labAge.setHorizontalAlignment(SwingConstants.CENTER);
        txtAge =new JTextField();

        labPhone =new JLabel("�绰:");
        labPhone.setHorizontalAlignment(SwingConstants.CENTER);
        txtPhone =new JTextField(12);


        // ע������
        //TODO

        //�������ƣ����ߣ������磬���������ڣ��۸񣬿κ��Ҳ��䡣
        bookPanel.add(labPeopelID);
        bookPanel.add(txtPeopelID);
        bookPanel.add(labName);
        bookPanel.add(txtName);
        bookPanel.add(labType);
        bookPanel.add(cmbType);
        bookPanel.add(labSex);
        bookPanel.add(txtSex);
        bookPanel.add(labAge);
        bookPanel.add(txtAge);
        bookPanel.add(labPhone);
        bookPanel.add(txtPhone);


        // ע������
        //TODO

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
        new BorrowPeopelAdd("������Ա��Ϣ���");
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnAdd){//����

            BorrowPeopel b1=new BorrowPeopel();

            b1.setPeopelId(txtPeopelID.getText());
            b1.setPeopelName(txtName.getText());
            b1.setSex(txtSex.getText());
            b1.setAge(Integer.parseInt(txtAge.getText()));

            String typeName=cmbType.getSelectedItem().toString().trim();
            b1.setPeopelTypeName(typeName);

            b1.setPhone(txtPhone.getText());

            int i= BorrowPeopelDao.insert(b1);

            if(i==1)
                JOptionPane.showMessageDialog(null, "���ӳɹ�!");
            else
                JOptionPane.showMessageDialog(null, "����ʧ��!");
        }

        if(e.getSource()==btnExit){//�˳�
            System.exit(1);
        }

        if(e.getSource()==btnReset) {// ����
            //����ı������������
            txtPeopelID.setText("");
            txtAge.setText("");
            txtName.setText("");
            txtPhone.setText("");
            txtSex.setText("");
        }

    }
}
