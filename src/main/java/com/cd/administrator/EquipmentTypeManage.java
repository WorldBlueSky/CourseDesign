package com.cd.administrator;

import com.cd.db.EquipmentTypeDao;
import com.cd.model.EquipmentType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EquipmentTypeManage extends JFrame implements ActionListener {
    private JPanel panel,selectConditionPane,btnPanel,
            centerPanel,selectResultPane,bookPane;

    private JComboBox cmbChoice,cmbType;

    private JTextField txtSelect, txtEquipmentTypeID, txtEquipmentTypeName;

    private JLabel labBookTypeID,labBookTypeName;

    private JButton btnSelect,btnModify,btnDelete,btnExit, btnInsert;

    private JTable table;

    private JScrollPane scrollPane;


    public EquipmentTypeManage(){

        setTitle("�豸���͹���");//���ñ���
        setSize(500,500);
        setLocationRelativeTo(null);
        panel=new JPanel(new BorderLayout());
        setContentPane(panel);
        selectConditionPane=new JPanel();

        txtSelect=new JTextField(20);
        JLabel labSelect = new JLabel("�豸���ͱ��");

        btnSelect=new JButton("��ѯ");
        btnSelect.addActionListener(this);

        selectConditionPane.add(labSelect);
        selectConditionPane.add(txtSelect);
        selectConditionPane.add(btnSelect);
        panel.add(selectConditionPane,BorderLayout.NORTH);

        //�м����
        centerPanel=new JPanel();
        selectResultPane=new JPanel();

        List<EquipmentType> list = EquipmentTypeDao.selectAll("");
        String[] colName= {"���","�����"};
        Object[][] r = getSelect(list);

        table = new JTable(r,colName);


//        table=new JTable();
        scrollPane=new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400,240));//���С
        selectResultPane.add(scrollPane);
        scrollPane.setViewportView(table);

        bookPane=new JPanel(new GridLayout(2,4));

        labBookTypeID =new JLabel("�豸���ͱ��:");
        labBookTypeName = new JLabel("�豸��������:");


        txtEquipmentTypeID =new JTextField(13);
        txtEquipmentTypeName =new JTextField(13);


        bookPane.add(labBookTypeID);
        labBookTypeID.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(txtEquipmentTypeID);

        bookPane.add(labBookTypeName);
        labBookTypeName.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(txtEquipmentTypeName);



        centerPanel.add(selectResultPane);
        centerPanel.add(bookPane);
        panel.add(centerPanel,BorderLayout.CENTER);

        btnPanel=new JPanel();

        btnInsert = new JButton("���");
        btnInsert.addActionListener(this);

        btnModify=new JButton("�޸�");
        btnModify.addActionListener(this);

        btnDelete=new JButton("ɾ��");
        btnDelete.addActionListener(this);

        btnExit=new JButton("�˳�");
        btnExit.addActionListener(this);

        btnPanel.add(btnInsert);
        btnPanel.add(btnModify);
        btnPanel.add(btnDelete);
        btnPanel.add(btnExit);

        panel.add(btnPanel,BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new EquipmentTypeManage();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnInsert){
            EquipmentType b = new EquipmentType();
            b.setTypeId(Integer.parseInt(txtEquipmentTypeID.getText()));
            b.setTypeName(txtEquipmentTypeName.getText());

            int ret = EquipmentTypeDao.insert(b);
            if(ret==1){
                JOptionPane.showMessageDialog(null,"���ӳɹ�!");
            }else{
                JOptionPane.showMessageDialog(null,"����ʧ��!");
            }

        }

        if(e.getSource()==btnDelete){
           int typeId = Integer.parseInt(txtEquipmentTypeID.getText());

           int ret = EquipmentTypeDao.delete(typeId);

            if(ret==1){
                JOptionPane.showMessageDialog(null,"ɾ���ɹ�!");
            }else{
                JOptionPane.showMessageDialog(null,"ɾ��ʧ��!");
            }
        }

        if(e.getSource()==btnModify){
            int typeId = Integer.parseInt(txtEquipmentTypeID.getText());

            EquipmentType b = new EquipmentType();
            b.setTypeName(txtEquipmentTypeName.getText());

            int ret = EquipmentTypeDao.update(b,typeId);

            if(ret==1){
                JOptionPane.showMessageDialog(null,"�޸ĳɹ�!");
            }else{
                JOptionPane.showMessageDialog(null,"�޸�ʧ��!");
            }
        }

        if(e.getSource()==btnExit){
            System.exit(1);
        }

        if(e.getSource()==btnSelect){
             if(txtSelect.getText().equals("")){
                 // ��ô��ѯȫ��
                 // ���ڱ��У����и���
                 List<EquipmentType> list = EquipmentTypeDao.selectAll("");
                 String[] colName= {"���","�����"};
                 Object[][] r = getSelect(list);

                 table = new JTable(r,colName);
                 scrollPane.setViewportView(table);
             }else{
                 // ���ݱ�Ž��в�ѯ
                 int typeid = Integer.parseInt(txtSelect.getText());
                 List<EquipmentType> list = EquipmentTypeDao.selectTypeById(typeid);
                 String[] colName= {"���","�����"};
                 Object[][] r = getSelect(list);

                 table = new JTable(r,colName);
                 scrollPane.setViewportView(table);

             }
        }
    }


    //�Ѳ�ѯ�Ľ���ŵ�һ����ά�����У�Ŀ����Ҫ�ŵ�JTable��
    Object[][] getSelect(List<EquipmentType> list){

        String[] colName= {"���","�����"};
        Object[][] results=new Object[list.size()][colName.length];

        for(int i=0;i<list.size();i++){
            EquipmentType b1 = list.get(i);
            results[i][0]=b1.getTypeId();
            results[i][1]=b1.getTypeName();
        }

        return results;
    }

}
