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
        setTitle("设备申请人员管理");//设置标题
        setSize(500,400);
        setLocationRelativeTo(null);
        panel=new JPanel(new BorderLayout());
        setContentPane(panel);

        // 顶部的框
        selectConditionPane=new JPanel();
        cmbChoice=new JComboBox();
        cmbChoice.addItem("全部");
        cmbChoice.addItem("申请人编号");
//        cmbChoice.addItem("读者名字");
        txtSelect=new JTextField(20);
        btnSelect=new JButton("查询");
        btnSelect.addActionListener(this);

        selectConditionPane.add(cmbChoice);
        selectConditionPane.add(txtSelect);
        selectConditionPane.add(btnSelect);

        panel.add(selectConditionPane,BorderLayout.NORTH);
        //中间面板
        centerPanel=new JPanel();
        selectResultPane=new JPanel();

        List<BorrowPeopel> list1 = BorrowPeopelDao.selectAll("");
        Object[][] r1=getSelect(list1);
        String[] colName= {"编号","名字","类别",
                "性别","年龄","电话"};
        table=new JTable(r1,colName);//表中的数据

        scrollPane=new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400,240));//设大小
        selectResultPane.add(scrollPane);


        //4行4列，需要创建每个组件，加到bookPane中，另外6个课后完成

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

            if(selectType.equals("全部")){ // 查询全部（模糊查询）
                // 调用查询全部的函数
                String s = txtSelect.getText();
                List<BorrowPeopel> list1 = BorrowPeopelDao.selectAll(s);
                // 查找完之后添加到面板当中
                //TODO
                Object[][] r1=getSelect(list1);
                String[] colName= {"编号","名字","类别","性别","年龄","电话"};
                table=new JTable(r1,colName);//表中的数据
                scrollPane.setViewportView(table);


            }else if(selectType.equals("申请人编号")){// 指定查询
                // 调用根据编号进行查询的方法
                String s = txtSelect.getText();
                List<BorrowPeopel> list2 = BorrowPeopelDao.selectById(s);
                // 查找完之后添加到面板当中
                //TODO
                Object[][] r1=getSelect(list2);
                String[] colName= {"编号","名字","类别", "性别","年龄","电话"};
                table=new JTable(r1,colName);//表中的数据
                scrollPane.setViewportView(table);

            }


        }


    }

    //把查询的结果放到一个二维数组中，目的是要放到JTable中
    Object[][] getSelect(List<BorrowPeopel> list){

        String[] colName= {"编号","名字","类别","性别","年龄","电话"};

        Object[][] results=new Object[list.size()][colName.length];

        for(int i=0;i<list.size();i++){
            BorrowPeopel b1=list.get(i);//获取list中的每一个Book
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
