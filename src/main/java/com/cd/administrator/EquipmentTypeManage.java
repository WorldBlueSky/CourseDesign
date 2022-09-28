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

        setTitle("设备类型管理");//设置标题
        setSize(500,500);
        setLocationRelativeTo(null);
        panel=new JPanel(new BorderLayout());
        setContentPane(panel);
        selectConditionPane=new JPanel();

        txtSelect=new JTextField(20);
        JLabel labSelect = new JLabel("设备类型编号");

        btnSelect=new JButton("查询");
        btnSelect.addActionListener(this);

        selectConditionPane.add(labSelect);
        selectConditionPane.add(txtSelect);
        selectConditionPane.add(btnSelect);
        panel.add(selectConditionPane,BorderLayout.NORTH);

        //中间面板
        centerPanel=new JPanel();
        selectResultPane=new JPanel();

        List<EquipmentType> list = EquipmentTypeDao.selectAll("");
        String[] colName= {"编号","类别名"};
        Object[][] r = getSelect(list);

        table = new JTable(r,colName);


//        table=new JTable();
        scrollPane=new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400,240));//设大小
        selectResultPane.add(scrollPane);
        scrollPane.setViewportView(table);

        bookPane=new JPanel(new GridLayout(2,4));

        labBookTypeID =new JLabel("设备类型编号:");
        labBookTypeName = new JLabel("设备类型名称:");


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

        btnInsert = new JButton("添加");
        btnInsert.addActionListener(this);

        btnModify=new JButton("修改");
        btnModify.addActionListener(this);

        btnDelete=new JButton("删除");
        btnDelete.addActionListener(this);

        btnExit=new JButton("退出");
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
                JOptionPane.showMessageDialog(null,"增加成功!");
            }else{
                JOptionPane.showMessageDialog(null,"增加失败!");
            }

        }

        if(e.getSource()==btnDelete){
           int typeId = Integer.parseInt(txtEquipmentTypeID.getText());

           int ret = EquipmentTypeDao.delete(typeId);

            if(ret==1){
                JOptionPane.showMessageDialog(null,"删除成功!");
            }else{
                JOptionPane.showMessageDialog(null,"删除失败!");
            }
        }

        if(e.getSource()==btnModify){
            int typeId = Integer.parseInt(txtEquipmentTypeID.getText());

            EquipmentType b = new EquipmentType();
            b.setTypeName(txtEquipmentTypeName.getText());

            int ret = EquipmentTypeDao.update(b,typeId);

            if(ret==1){
                JOptionPane.showMessageDialog(null,"修改成功!");
            }else{
                JOptionPane.showMessageDialog(null,"修改失败!");
            }
        }

        if(e.getSource()==btnExit){
            System.exit(1);
        }

        if(e.getSource()==btnSelect){
             if(txtSelect.getText().equals("")){
                 // 那么查询全部
                 // 放在表单中，进行更新
                 List<EquipmentType> list = EquipmentTypeDao.selectAll("");
                 String[] colName= {"编号","类别名"};
                 Object[][] r = getSelect(list);

                 table = new JTable(r,colName);
                 scrollPane.setViewportView(table);
             }else{
                 // 根据编号进行查询
                 int typeid = Integer.parseInt(txtSelect.getText());
                 List<EquipmentType> list = EquipmentTypeDao.selectTypeById(typeid);
                 String[] colName= {"编号","类别名"};
                 Object[][] r = getSelect(list);

                 table = new JTable(r,colName);
                 scrollPane.setViewportView(table);

             }
        }
    }


    //把查询的结果放到一个二维数组中，目的是要放到JTable中
    Object[][] getSelect(List<EquipmentType> list){

        String[] colName= {"编号","类别名"};
        Object[][] results=new Object[list.size()][colName.length];

        for(int i=0;i<list.size();i++){
            EquipmentType b1 = list.get(i);
            results[i][0]=b1.getTypeId();
            results[i][1]=b1.getTypeName();
        }

        return results;
    }

}
