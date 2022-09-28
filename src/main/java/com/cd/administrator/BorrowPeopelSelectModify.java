package com.cd.administrator;

import com.cd.db.BorrowPeopelDao;
import com.cd.model.BorrowPeopel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BorrowPeopelSelectModify extends JFrame implements ActionListener {
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


    public BorrowPeopelSelectModify(){
        setTitle("ʵ������Ա����");//���ñ���
        setSize(500,500);
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
        bookPane=new JPanel(new GridLayout(3,2));
        labID =new JLabel("���:");
        labName = new JLabel("����:");
        labType=new JLabel("���:");
        labSex =new JLabel("�Ա�:");
        labAge =new JLabel("����:");
        labPhone =new JLabel("�绰:");

        txtID =new JTextField(8);

        cmbType=new JComboBox();
        cmbType.addItem("��ʦ");
        cmbType.addItem("ѧ��");

        txtSex =new JTextField(8);
        txtAge =new JTextField(8);
        txtPhone =new JTextField(8);


        txtName = new JTextField(8);


        bookPane.add(labID);
        labID.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(txtID);

        bookPane.add(labName);
        labName.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(txtName);

        bookPane.add(labType);
        labType.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(cmbType);


        bookPane.add(labSex);
        labSex.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(txtSex);


        bookPane.add(labAge);
        labAge.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(txtAge);


        bookPane.add(labPhone);
        labPhone.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(txtPhone);





        centerPanel.add(selectResultPane);
        centerPanel.add(bookPane);
        panel.add(centerPanel,BorderLayout.CENTER);
        btnPanel=new JPanel();

        btnDelete = new JButton("ɾ��");
        btnModify=new JButton("�޸�");
        btnExit=new JButton("�˳�");

        btnPanel.add(btnDelete);
        btnDelete.addActionListener(this);

        btnPanel.add(btnModify);
        btnModify.addActionListener(this);

        btnPanel.add(btnExit);
        btnExit.addActionListener(this);

        panel.add(btnPanel,BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new BorrowPeopelSelectModify();
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

        // ���������˵ı�Ž���ɾ������
        if(e.getSource()==btnDelete){
            String id=txtID.getText().trim();
            //����BorrowPeopelDao�е�delete������������Ӱ�������
            int ret= BorrowPeopelDao.delete(id);
            if(ret==1)
                JOptionPane.showMessageDialog(null, "ɾ���ɹ�!");
            else
                JOptionPane.showMessageDialog(null, "ɾ��ʧ��!");
        }

        if(e.getSource()==btnModify){

            BorrowPeopel b1=new BorrowPeopel();

            b1.setPeopelId(txtID.getText());
            b1.setPeopelName(txtName.getText());
            b1.setSex(txtSex.getText());
            b1.setAge(Integer.parseInt(txtAge.getText()));

            String typeName=cmbType.getSelectedItem().toString().trim();
            b1.setPeopelTypeName(typeName);

            b1.setPhone(txtPhone.getText());

            int ret= BorrowPeopelDao.update(b1);

            if(ret==1)
                JOptionPane.showMessageDialog(null, "�޸ĳɹ�!");
            else
                JOptionPane.showMessageDialog(null, "�޸�ʧ��!");


        }

        if(e.getSource()==btnExit){
              System.exit(1);
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
