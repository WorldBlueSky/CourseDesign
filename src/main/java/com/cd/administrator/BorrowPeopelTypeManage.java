package com.cd.administrator;

import com.cd.db.BorrowPeopelTypeDao;
import com.cd.model.BorrowPeopelType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BorrowPeopelTypeManage extends JFrame  implements ActionListener {
    private JPanel panel,selectConditionPane,btnPanel,
            centerPanel,selectResultPane,bookPane;

    private JComboBox cmbChoice,cmbType;

    private JTextField txtSelect, txtTypeID, txtLimit,txtTypeName,txtMaxBorrowNum;

    private JLabel labTypeID, labMaxBorrowNum, labLimit,labTypeName,labSelect;

    private JButton btnSelect,btnModify,btnDelete,btnExit, btnInsert;

    private JTable table;

    private JScrollPane scrollPane;


    public BorrowPeopelTypeManage(){
        setTitle("������Ա���͹���");//���ñ���
        setSize(500,500);
        setLocationRelativeTo(null);
        panel=new JPanel(new BorderLayout());
        setContentPane(panel);
        selectConditionPane=new JPanel();

        txtSelect=new JTextField(20);
        JLabel labSelect = new JLabel("������Ա���ͱ��");
        btnSelect=new JButton("��ѯ");
        btnSelect.addActionListener(this);

        selectConditionPane.add(labSelect);
        selectConditionPane.add(txtSelect);
        selectConditionPane.add(btnSelect);
        panel.add(selectConditionPane,BorderLayout.NORTH);

        //�м����
        centerPanel=new JPanel();
        selectResultPane=new JPanel();


        Object[][] r1=getSelect(BorrowPeopelTypeDao.selectAll());
        String[] colName= {"���","�����"};
        table=new JTable(r1,colName);//���е�����


        scrollPane=new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400,240));//���С
        selectResultPane.add(scrollPane);

        bookPane=new JPanel(new GridLayout(2,4));

        labTypeID =new JLabel("������Ա���ͱ��:");
        labTypeName = new JLabel("������Ա��������:");


        txtTypeID =new JTextField(8);
        txtTypeName =new JTextField(8);


        bookPane.add(labTypeID);
        labTypeID.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(txtTypeID);

        bookPane.add(labTypeName);
        labTypeName.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(txtTypeName);

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
        new BorrowPeopelTypeManage();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnSelect){
            String s = txtSelect.getText();

            if(s.equals("")){
                // ��ѯȫ��
                Object[][] r1=getSelect(BorrowPeopelTypeDao.selectAll());
                String[] colName= {"���","�����"};
                table=new JTable(r1,colName);//���е�����
                scrollPane.setViewportView(table);//�����������ϵ����

            }else{
                // ָ����ѯ
                List<BorrowPeopelType> list = BorrowPeopelTypeDao.selectTypeById(s);
                Object[][] r1=getSelect(list);
                String[] colName= {"���","�����"};
                table=new JTable(r1,colName);//���е�����
                scrollPane.setViewportView(table);//�����������ϵ����

            }
        }

        if(e.getSource()==btnExit){
            System.exit(1);
        }

        if(e.getSource()==btnDelete){
            // �������ͱ�Ž���ɾ��
            String typeid =txtTypeID.getText();
            int ret = BorrowPeopelTypeDao.delete(typeid);
            if(ret==1){
                JOptionPane.showMessageDialog(null,"ɾ���ɹ�!");
            }else{
                JOptionPane.showMessageDialog(null,"ɾ��ʧ��!");
            }
        }

        if(e.getSource()==btnModify){
            BorrowPeopelType b = new BorrowPeopelType();
            b.setPeopelTypeId(txtTypeID.getText());
            b.setPeoelTypeName(txtTypeName.getText());
            int ret = BorrowPeopelTypeDao.update(b);
            if(ret==1){
                JOptionPane.showMessageDialog(null,"�޸ĳɹ�!");
            }else{
                JOptionPane.showMessageDialog(null,"�޸�ʧ��!");
            }


        }
        if(e.getSource()==btnInsert){
               BorrowPeopelType b = new BorrowPeopelType();
               b.setPeopelTypeId(txtTypeID.getText());
               b.setPeoelTypeName(txtTypeName.getText());
               int ret = BorrowPeopelTypeDao.insert(b);
            if(ret==1){
                JOptionPane.showMessageDialog(null,"���ӳɹ�!");
            }else{
                JOptionPane.showMessageDialog(null,"����ʧ��!");
            }

        }


    }

    //�Ѳ�ѯ�Ľ���ŵ�һ����ά�����У�Ŀ����Ҫ�ŵ�JTable��
    Object[][] getSelect(List<BorrowPeopelType> list){

        String[] colName= {"���","�����"};
        Object[][] results=new Object[list.size()][colName.length];

        for(int i=0;i<list.size();i++){
            BorrowPeopelType b1=list.get(i);
            results[i][0]=b1.getPeopelTypeId();
            results[i][1]=b1.getPeoelTypeName();
        }

        return results;
    }
}
