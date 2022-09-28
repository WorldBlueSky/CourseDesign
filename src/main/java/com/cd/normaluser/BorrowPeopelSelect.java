package com.cd.normaluser;

import com.cd.db.BorrowPeopelDao;
import com.cd.model.BorrowPeopel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BorrowPeopelSelect extends JFrame implements ActionListener {
    private JPanel panel,selectConditionPane,btnPanel,
            centerPanel,selectResultPane,bookPane;

    private JComboBox cmbChoice,cmbType;

    private JTextField txtSelect, txtID, txtSex, txtAge,
            txtPhone, txtDept, txtRegisterDate,txtName;

    private JLabel labID,labType, labSex, labAge,
            labPhone, labDept, labRegisterDate,labName;

    private JButton btnSelect,btnModify,btnDelete,btnExit;

    private JTable table;

    private JScrollPane scrollPane;


    public BorrowPeopelSelect(){
        setTitle("�豸������Ա����");//���ñ���
        setSize(500,400);
        setLocationRelativeTo(null);
        panel=new JPanel(new BorderLayout());
        setContentPane(panel);

        // �����Ŀ�
        selectConditionPane=new JPanel();
        cmbChoice=new JComboBox();
        cmbChoice.addItem("ȫ��");
        cmbChoice.addItem("�����˱��");
//        cmbChoice.addItem("��������");
        txtSelect=new JTextField(20);
        btnSelect=new JButton("��ѯ");
        btnSelect.addActionListener(this);

        selectConditionPane.add(cmbChoice);
        selectConditionPane.add(txtSelect);
        selectConditionPane.add(btnSelect);

        panel.add(selectConditionPane,BorderLayout.NORTH);
        //�м����
        centerPanel=new JPanel();
        selectResultPane=new JPanel();

        List<BorrowPeopel> list1 = BorrowPeopelDao.selectAll("");
        Object[][] r1=getSelect(list1);
        String[] colName= {"���","����","���",
                "�Ա�","����","�绰"};
        table=new JTable(r1,colName);//���е�����

        scrollPane=new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400,240));//���С
        selectResultPane.add(scrollPane);


        //4��4�У���Ҫ����ÿ��������ӵ�bookPane�У�����6���κ����

        centerPanel.add(selectResultPane);

        panel.add(centerPanel,BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new BorrowPeopelSelect();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnSelect){
            String selectType =cmbChoice.getSelectedItem().toString().trim();

            if(selectType.equals("ȫ��")){ // ��ѯȫ����ģ����ѯ��
                // ���ò�ѯȫ���ĺ���
                String s = txtSelect.getText();
                List<BorrowPeopel> list1 = BorrowPeopelDao.selectAll(s);
                // ������֮����ӵ���嵱��
                //TODO
                Object[][] r1=getSelect(list1);
                String[] colName= {"���","����","���","�Ա�","����","�绰"};
                table=new JTable(r1,colName);//���е�����
                scrollPane.setViewportView(table);


            }else if(selectType.equals("�����˱��")){// ָ����ѯ
                // ���ø��ݱ�Ž��в�ѯ�ķ���
                String s = txtSelect.getText();
                List<BorrowPeopel> list2 = BorrowPeopelDao.selectById(s);
                // ������֮����ӵ���嵱��
                //TODO
                Object[][] r1=getSelect(list2);
                String[] colName= {"���","����","���", "�Ա�","����","�绰"};
                table=new JTable(r1,colName);//���е�����
                scrollPane.setViewportView(table);

            }


        }


    }

    //�Ѳ�ѯ�Ľ���ŵ�һ����ά�����У�Ŀ����Ҫ�ŵ�JTable��
    Object[][] getSelect(List<BorrowPeopel> list){

        String[] colName= {"���","����","���","�Ա�","����","�绰"};

        Object[][] results=new Object[list.size()][colName.length];

        for(int i=0;i<list.size();i++){
            BorrowPeopel b1=list.get(i);//��ȡlist�е�ÿһ��Book
            results[i][0]=b1.getPeopelId();
            results[i][1]=b1.getPeopelName();
            results[i][2]=b1.getPeopelTypeName();
            results[i][3]=b1.getSex();
            results[i][4]=b1.getAge();
            results[i][5]=b1.getPhone();
        }

        return results;
    }
}
