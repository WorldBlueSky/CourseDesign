package com.cd.administrator;

import com.cd.db.EquipmentDao;
import com.cd.model.Equipment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EquipmentScrap extends JFrame implements ActionListener {

    private JPanel panel,selectConditionPane,btnPanel,
            centerPanel,selectResultPane,bookPane;

    private JComboBox cmbChoice,cmbType;

    private JTextField txtSelect, txtEquipmentName, txtNumber, txtUnit;

    private JLabel labEquipmentName,labType, labNumber, labUnit;

    private JButton btnSelect,btnModify,btnScrap,btnDelete,btnExit;

    private JTable table;

    private JScrollPane scrollPane;

    public EquipmentScrap(){

        setTitle("�豸��������");//���ñ���
        setSize(700,530);
        setLocationRelativeTo(null);
        panel=new JPanel(new BorderLayout());
        setContentPane(panel);
        selectConditionPane=new JPanel();
        cmbChoice=new JComboBox();
        cmbChoice.addItem("ȫ��");
        cmbChoice.addItem("�豸����");
        txtSelect=new JTextField(20);
        selectConditionPane.add(cmbChoice);
        selectConditionPane.add(txtSelect);
        panel.add(selectConditionPane,BorderLayout.NORTH);

        //�м����
        centerPanel=new JPanel();
        selectResultPane=new JPanel();

        Object[][] r1=getSelect(EquipmentDao.selectAll(""));
        String[] colName= {"���","����","�����","��ǰ����","������λ","��ŵص�","��������","���뵥��"};
        table=new JTable(r1,colName);//���е�����



        scrollPane=new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(650,260));//���С
        selectResultPane.add(scrollPane);
        bookPane=new JPanel(new GridLayout(4,4));


        labEquipmentName =new JLabel("�豸����");
        txtEquipmentName =new JTextField(8);

        labType=new JLabel("�豸����");
        cmbType=new JComboBox();
        cmbType.addItem("�����豸��");
        cmbType.addItem("����������");


        labNumber =new JLabel("��ǰ����");
        txtNumber =new JTextField(8);

        labUnit =new JLabel("������λ");
        txtUnit =new JTextField(8);




        // ��Ӹ������
        bookPane.add(labEquipmentName);
        bookPane.add(txtEquipmentName);
        bookPane.add(labType);
        bookPane.add(cmbType);
        bookPane.add(labNumber);
        bookPane.add(txtNumber);
        bookPane.add(labUnit);
        bookPane.add(txtUnit);

        centerPanel.add(selectResultPane);
        centerPanel.add(bookPane);
        panel.add(centerPanel,BorderLayout.CENTER);

        // ��ť����Ϣ

        btnPanel=new JPanel();

        btnSelect=new JButton("��ѯ");
        btnSelect.addActionListener(this);

        btnScrap=new JButton("����");
        btnScrap.addActionListener(this);



        btnExit=new JButton("�˳�");
        btnExit.addActionListener(this);

        btnPanel.add(btnSelect);

        btnPanel.add(btnScrap);
        btnPanel.add(btnExit);
        panel.add(btnPanel,BorderLayout.SOUTH);

        // ���ڿ��ӻ�
        setVisible(true);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new EquipmentScrap();
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnScrap){//����
            String name=txtEquipmentName.getText().trim();

            int i= EquipmentDao.delete(name);
            if(i==1)
                JOptionPane.showMessageDialog(null, "�������ϴ���!");
            else
                JOptionPane.showMessageDialog(null, "����ʧ��!");
        }


        if(e.getSource()==btnSelect){
            //��ѯ
            String name=cmbChoice.getSelectedItem().toString();
            String value=txtSelect.getText();

            if(name.equals("ȫ��")){

                Object[][] r1=getSelect(EquipmentDao.selectAll(value));
                String[] colName= {"���","����","�����","��ǰ����","������λ","��ŵص�","��������","���뵥��"};
                table=new JTable(r1,colName);//���е�����
                scrollPane.setViewportView(table);//�����������ϵ����

            }else if(name.equals("�豸����")){

                Object[][] r1=getSelect(EquipmentDao.selectByName(value));
                String[] colName= {"���","����","�����", "��ǰ����","������λ","��ŵص�","��������","���뵥��"};
                table=new JTable(r1,colName);//���е�����
                scrollPane.setViewportView(table);//��������ʾ

            }


        }



        if(e.getSource()==btnExit){
            System.exit(1);
        }
    }

    //�Ѳ�ѯ�Ľ���ŵ�һ����ά�����У�Ŀ����Ҫ�ŵ�JTable��
    Object[][] getSelect(List<Equipment> list){

        String[] colName= {"���","����","�����",
                "��ǰ����","������λ","��ŵص�","��������","���뵥��"};

        Object[][] results=new Object[list.size()][colName.length];

        for(int i=0;i<list.size();i++){
            Equipment b1=list.get(i);

            results[i][0]=b1.getId();
            results[i][1]=b1.getName();
            results[i][2]=b1.getTypeName();
            results[i][3]=b1.getNumber();
            results[i][4]=b1.getUnit();
            results[i][5]=b1.getLocation();
            results[i][6]=b1.getPurchaseDate();
            results[i][7]=b1.getPrice();
        }

        return results;
    }

}
