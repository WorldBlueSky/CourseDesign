package com.cd.administrator;

import com.cd.db.RepairDao;
import com.cd.model.Repair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EquipmentRepair extends JFrame implements ActionListener {
    private JPanel panel, ReaderConditionPane,btnPanel,
            centerPanel,selectResultPane,bookPane;

    private JTextField txtEquipmentName,txtWorker, txtNumber,txtTypeName, txtDate, txtId ;

    private JLabel labEquipmentName, labDate, labWorker,labNumber,labTypeName, labId;

    private JButton btnClose, btnBorrow,btnSelect ;

    private JTable table;

    private JScrollPane scrollPane;
    private JComboBox cmbChoice;

    public EquipmentRepair(){

        setTitle("�豸ά���������");//���ñ���
        setSize(900,630);
        setLocationRelativeTo(null);
        panel=new JPanel(new BorderLayout());
        setContentPane(panel);

        // ���ö������

        ReaderConditionPane =new JPanel();

        JLabel labtitle = new JLabel("�豸ά�������¼");

        ReaderConditionPane.add(labtitle);

        panel.add(ReaderConditionPane,BorderLayout.NORTH);

        //�м����
        centerPanel=new JPanel();
        selectResultPane=new JPanel();
//        table=new JTable();

        Object[][] r1=getSelect(RepairDao.selectAll(""));
        String[] colName= {"���","�豸����","�豸���","����ά������","ά�޹�������","ά������","��׼״̬"};

        table=new JTable(r1,colName);

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

        labNumber =new JLabel("ά������:");
        txtNumber =new JTextField(8);

        labWorker =new JLabel("ά�޹���:");
        txtWorker =new JTextField(8);

        labDate =new JLabel("ά������:");
        txtDate =new JTextField(8);

        bookPane.add(labId);
        labId.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(txtId);

        bookPane.add(labEquipmentName);
        labEquipmentName.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(txtEquipmentName);

        bookPane.add(labTypeName);
        labTypeName.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(cmbChoice);


        bookPane.add(labNumber);
        labNumber.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(txtNumber);

        bookPane.add(labWorker);
        labWorker.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(txtWorker);

        bookPane.add(labDate);
        labDate.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(txtDate);





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
        new EquipmentRepair();
    }

    //�Ѳ�ѯ�Ľ���ŵ�һ����ά�����У�Ŀ����Ҫ�ŵ�JTable��
    Object[][] getSelect(List<Repair> list){

        String[] colName= {"���","�豸����","�豸���","����ά������","ά�޹�������","ά������","��׼״̬"};
        Object[][] results=new Object[list.size()][colName.length];

        for(int i=0;i<list.size();i++){
            Repair repair = list.get(i);
            results[i][0]=repair.getId();
            results[i][1]=repair.getName();
            results[i][2]=repair.getTypeName();
            results[i][3]=repair.getNumber();
            results[i][4]=repair.getWorkerName();
            results[i][5] =repair.getWorkDate();
            results[i][6] = repair.getAgree();
        }

        for (int i = 0;i<list.size();i++){
            for(int j =0;j<colName.length;j++){
                System.out.println(results[i][j]);
            }
        }

        return results;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnClose){
            System.exit(1);
        }


        if(btnBorrow==e.getSource()){
            Repair repair = new Repair();
            repair.setName(txtEquipmentName.getText());
            repair.setTypeName(cmbChoice.getSelectedItem().toString());
            repair.setNumber(Integer.parseInt(txtNumber.getText()));
            repair.setWorkerName(txtWorker.getText());
            repair.setWorkDate(txtDate.getText());

            int ret = RepairDao.insert(repair);
            if(ret==1){
                JOptionPane.showMessageDialog(null,"��������ɹ�,�����¼����ӣ��ȴ��쵼����!");
            }else{
                JOptionPane.showMessageDialog(null,"�������ʧ��,�����¼���ʧ��!");
            }
        }

        if(e.getSource()==btnSelect){
            if(txtId.getText().equals("")) {
                Object[][] r1 = getSelect(RepairDao.selectAll(""));
                String[] colName = {"���", "�豸����", "�豸���", "����ά������", "ά�޹�������", "ά������", "��׼״̬"};
                table = new JTable(r1, colName);//���е�����
                scrollPane.setViewportView(table);
            }else{
                Object[][] r1=getSelect(RepairDao.selectById(Integer.parseInt(txtId.getText())));
                String[] colName= {"���","�豸����","�豸���","����ά������","ά�޹�������","ά������","��׼״̬"};
                table=new JTable(r1,colName);//���е�����
                scrollPane.setViewportView(table);
            }
        }

    }
}
