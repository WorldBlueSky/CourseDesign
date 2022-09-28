package com.cd.normaluser;

import com.cd.db.EquipmentBorrowDao;
import com.cd.model.EquipmentBorrow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EquipmentBorrowManage extends JFrame implements ActionListener {
    private JPanel panel, ReaderConditionPane,btnPanel,
            centerPanel,selectResultPane,bookPane;

    private JTextField txtEquipmentName, txtNumber,txtTypeName, txtDate, txtPeopel,txtId ;

    private JLabel labEquipmentName, labDate, labNumber,labTypeName, labPeopel,labId;

    private JButton btnClose, btnBorrow,btnSelect ;

    private JTable table;

    private JScrollPane scrollPane;
    private JComboBox cmbChoice;

    public EquipmentBorrowManage(){

        setTitle("�豸�������");//���ñ���
        setSize(900,630);
        setLocationRelativeTo(null);
        panel=new JPanel(new BorderLayout());
        setContentPane(panel);

        // ���ö������

        ReaderConditionPane =new JPanel();

        JLabel labtitle = new JLabel("�豸�����¼");

        ReaderConditionPane.add(labtitle);

        panel.add(ReaderConditionPane,BorderLayout.NORTH);

        //�м����
        centerPanel=new JPanel();
        selectResultPane=new JPanel();
//        table=new JTable();

        Object[][] r1=getSelect(EquipmentBorrowDao.selectAll());
        String[] colName= {"������","�豸����","�豸���","��������","�黹����","��ǰ����","��������","�黹����","������Ա","Ŀǰ״̬"};

        table=new JTable(r1,colName);//���е�����

        scrollPane=new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800,400));//���С
        selectResultPane.add(scrollPane);


        // �ײ����
        bookPane=new JPanel(new GridLayout(3,2));

        labId = new JLabel("������:");
        txtId = new JTextField(8);

        labEquipmentName =new JLabel("�豸����:");
        txtEquipmentName =new JTextField(8);

        labTypeName=new JLabel("�豸���:");
        cmbChoice=new JComboBox();
        cmbChoice.addItem("�����豸��");
        cmbChoice.addItem("����������");

        labDate =new JLabel("��������:");
        txtDate =new JTextField(8);

        labNumber =new JLabel("��������:");
        txtNumber =new JTextField(8);

        labPeopel =new JLabel("������Ա:");
        txtPeopel =new JTextField(8);

        bookPane.add(labId);
        labId.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(txtId);

        bookPane.add(labEquipmentName);
        labEquipmentName.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(txtEquipmentName);

        bookPane.add(labTypeName);
        labTypeName.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(cmbChoice);

        bookPane.add(labDate);
        labDate.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(txtDate);


        bookPane.add(labNumber);
        labNumber.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(txtNumber);

        bookPane.add(labPeopel);
        labPeopel.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(txtPeopel);



        centerPanel.add(selectResultPane);
        centerPanel.add(bookPane);
        panel.add(centerPanel,BorderLayout.CENTER);


        btnPanel=new JPanel();

        btnSelect = new JButton("��ѯ");
        btnSelect.addActionListener(this);

        btnBorrow = new JButton("����");
        btnBorrow.addActionListener(this);
        btnClose =new JButton("�ر�");
        btnClose.addActionListener(this);


        btnPanel.add(btnSelect);
        btnPanel.add(btnBorrow);
        btnPanel.add(btnClose);


        panel.add(btnPanel,BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new EquipmentBorrowManage();
    }

    //�Ѳ�ѯ�Ľ���ŵ�һ����ά�����У�Ŀ����Ҫ�ŵ�JTable��
    Object[][] getSelect(List<EquipmentBorrow> list){

        String[] colName= {"������","�豸����","�豸���","��������","�黹����","��ǰ����","��������","�黹����","������Ա","Ŀǰ״̬"};

        Object[][] results=new Object[list.size()][colName.length];

        for(int i=0;i<list.size();i++){

            EquipmentBorrow b1=list.get(i);

            // ��ѯ������
            int finalNum = EquipmentBorrowDao.selectNum(b1.getName());

            results[i][0] = b1.getBorrowId();
            results[i][1]=b1.getName();
            results[i][2]=b1.getTypeName();
            results[i][3]=b1.getBorrowNumber();
            results[i][4]=b1.getReturnNumber();
            results[i][5] = finalNum;
            results[i][6]=b1.getBorrowDate();
            results[i][7] = b1.getReturnDate();
            results[i][8]=b1.getPeopel();
            results[i][9]=b1.getState();
        }

        return results;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnClose){
            System.exit(1);
        }
        if(e.getSource()==btnBorrow){

            EquipmentBorrow b = new EquipmentBorrow();

            b.setName(txtEquipmentName.getText());

            String typeName = cmbChoice.getSelectedItem().toString();
            b.setTypeName(typeName);

            b.setBorrowNumber(Integer.parseInt(txtNumber.getText()));
            b.setBorrowDate(txtDate.getText());
            b.setPeopel(txtPeopel.getText());

            int ret = EquipmentBorrowDao.insert(b);
            if(ret==1)
                JOptionPane.showMessageDialog(null, "����ɹ�!");
            else
                JOptionPane.showMessageDialog(null, "����ʧ��!");
        }

        if(e.getSource()==btnSelect){
            if(txtId.getText().equals("")){
                Object[][] r1=getSelect(EquipmentBorrowDao.selectAll());
                String[] colName= {"���","�豸����","�豸���","��������","�黹����","��ǰ����","��������","�黹����","������Ա","Ŀǰ״̬"};
                table=new JTable(r1,colName);//���е�����
                scrollPane.setViewportView(table);
            }else{
                Object[][] r1=getSelect(EquipmentBorrowDao.selectById(Integer.parseInt(txtId.getText())));
                String[] colName= {"���","�豸����","�豸���","��������","�黹����","��ǰ����","��������","�黹����","������Ա","Ŀǰ״̬"};
                table=new JTable(r1,colName);//���е�����
                scrollPane.setViewportView(table);
            }
        }

    }
}
