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
        setTitle("申请人员类型管理");//设置标题
        setSize(500,500);
        setLocationRelativeTo(null);
        panel=new JPanel(new BorderLayout());
        setContentPane(panel);
        selectConditionPane=new JPanel();

        txtSelect=new JTextField(20);
        JLabel labSelect = new JLabel("申请人员类型编号");
        btnSelect=new JButton("查询");
        btnSelect.addActionListener(this);

        selectConditionPane.add(labSelect);
        selectConditionPane.add(txtSelect);
        selectConditionPane.add(btnSelect);
        panel.add(selectConditionPane,BorderLayout.NORTH);

        //中间面板
        centerPanel=new JPanel();
        selectResultPane=new JPanel();


        Object[][] r1=getSelect(BorrowPeopelTypeDao.selectAll());
        String[] colName= {"编号","类别名"};
        table=new JTable(r1,colName);//表中的数据


        scrollPane=new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400,240));//设大小
        selectResultPane.add(scrollPane);

        bookPane=new JPanel(new GridLayout(2,4));

        labTypeID =new JLabel("申请人员类型编号:");
        labTypeName = new JLabel("申请人员类型名称:");


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
        new BorrowPeopelTypeManage();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnSelect){
            String s = txtSelect.getText();

            if(s.equals("")){
                // 查询全部
                Object[][] r1=getSelect(BorrowPeopelTypeDao.selectAll());
                String[] colName= {"编号","类别名"};
                table=new JTable(r1,colName);//表中的数据
                scrollPane.setViewportView(table);//表与滚动条联系起来

            }else{
                // 指定查询
                List<BorrowPeopelType> list = BorrowPeopelTypeDao.selectTypeById(s);
                Object[][] r1=getSelect(list);
                String[] colName= {"编号","类别名"};
                table=new JTable(r1,colName);//表中的数据
                scrollPane.setViewportView(table);//表与滚动条联系起来

            }
        }

        if(e.getSource()==btnExit){
            System.exit(1);
        }

        if(e.getSource()==btnDelete){
            // 根据类型编号进行删除
            String typeid =txtTypeID.getText();
            int ret = BorrowPeopelTypeDao.delete(typeid);
            if(ret==1){
                JOptionPane.showMessageDialog(null,"删除成功!");
            }else{
                JOptionPane.showMessageDialog(null,"删除失败!");
            }
        }

        if(e.getSource()==btnModify){
            BorrowPeopelType b = new BorrowPeopelType();
            b.setPeopelTypeId(txtTypeID.getText());
            b.setPeoelTypeName(txtTypeName.getText());
            int ret = BorrowPeopelTypeDao.update(b);
            if(ret==1){
                JOptionPane.showMessageDialog(null,"修改成功!");
            }else{
                JOptionPane.showMessageDialog(null,"修改失败!");
            }


        }
        if(e.getSource()==btnInsert){
               BorrowPeopelType b = new BorrowPeopelType();
               b.setPeopelTypeId(txtTypeID.getText());
               b.setPeoelTypeName(txtTypeName.getText());
               int ret = BorrowPeopelTypeDao.insert(b);
            if(ret==1){
                JOptionPane.showMessageDialog(null,"增加成功!");
            }else{
                JOptionPane.showMessageDialog(null,"增加失败!");
            }

        }


    }

    //把查询的结果放到一个二维数组中，目的是要放到JTable中
    Object[][] getSelect(List<BorrowPeopelType> list){

        String[] colName= {"编号","类别名"};
        Object[][] results=new Object[list.size()][colName.length];

        for(int i=0;i<list.size();i++){
            BorrowPeopelType b1=list.get(i);
            results[i][0]=b1.getPeopelTypeId();
            results[i][1]=b1.getPeoelTypeName();
        }

        return results;
    }
}
