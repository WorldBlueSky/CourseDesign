package com.cd.administrator;

import com.cd.db.BorrowPeopelDao;
import com.cd.model.BorrowPeopel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BorrowPeopelAdd extends JFrame implements ActionListener {
    private JPanel panel,bookPanel,btnPanel;

    // 标签
    private JLabel labPeopelID, labName,labType, labSex, labAge,
            labPhone;

    // 文本属性
    private JTextField txtPeopelID,txtName, txtSex,
            txtAge, txtPhone;

    JComboBox cmbType;//组合框

    // 组件按钮
    private JButton btnAdd,btnReset,btnExit;


    public BorrowPeopelAdd(String s){
        super(s);
        setSize(400,200);
        setLocationRelativeTo(null);
        panel=new JPanel(new BorderLayout());
        setContentPane(panel);
        //图书面板的信息
        GridLayout grid1=new GridLayout(3,1);//网格布局
        grid1.setHgap(5);
        grid1.setVgap(5);
        bookPanel=new JPanel(grid1);

        labPeopelID =new JLabel("编号:");
        labPeopelID.setHorizontalAlignment(SwingConstants.CENTER);//居中
        txtPeopelID =new JTextField(15);

        labName = new JLabel("姓名:");
        labName.setHorizontalAlignment(SwingConstants.CENTER);
        txtName=new JTextField(12);

        labType =new JLabel("类别：");
        labType.setHorizontalAlignment(SwingConstants.CENTER);
        cmbType =new JComboBox();
        cmbType.addItem("教师");
        cmbType.addItem("学生");


        labSex =new JLabel("性别:");
        labSex.setHorizontalAlignment(SwingConstants.CENTER);
        txtSex =new JTextField(12);



        labAge =new JLabel("年龄:");
        labAge.setHorizontalAlignment(SwingConstants.CENTER);
        txtAge =new JTextField();

        labPhone =new JLabel("电话:");
        labPhone.setHorizontalAlignment(SwingConstants.CENTER);
        txtPhone =new JTextField(12);


        // 注册日期
        //TODO

        //其余类似，作者，出版社，出版社日期，价格，课后大家补充。
        bookPanel.add(labPeopelID);
        bookPanel.add(txtPeopelID);
        bookPanel.add(labName);
        bookPanel.add(txtName);
        bookPanel.add(labType);
        bookPanel.add(cmbType);
        bookPanel.add(labSex);
        bookPanel.add(txtSex);
        bookPanel.add(labAge);
        bookPanel.add(txtAge);
        bookPanel.add(labPhone);
        bookPanel.add(txtPhone);


        // 注册日期
        //TODO

        //将各组件加入到面板
        panel.add(bookPanel,BorderLayout.CENTER);
        btnPanel=new JPanel();


        btnAdd=new JButton("增加");
        btnAdd.addActionListener(this);

        btnReset=new JButton("重置");
        btnReset.addActionListener(this);

        btnExit=new JButton("退出");
        btnExit.addActionListener(this);

        btnPanel.add(btnAdd);
        btnPanel.add(btnReset);
        btnPanel.add(btnExit);
        panel.add(btnPanel,BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new BorrowPeopelAdd("申请人员信息添加");
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnAdd){//增加

            BorrowPeopel b1=new BorrowPeopel();

            b1.setPeopelId(txtPeopelID.getText());
            b1.setPeopelName(txtName.getText());
            b1.setSex(txtSex.getText());
            b1.setAge(Integer.parseInt(txtAge.getText()));

            String typeName=cmbType.getSelectedItem().toString().trim();
            b1.setPeopelTypeName(typeName);

            b1.setPhone(txtPhone.getText());

            int i= BorrowPeopelDao.insert(b1);

            if(i==1)
                JOptionPane.showMessageDialog(null, "增加成功!");
            else
                JOptionPane.showMessageDialog(null, "增加失败!");
        }

        if(e.getSource()==btnExit){//退出
            System.exit(1);
        }

        if(e.getSource()==btnReset) {// 重置
            //清除文本框输入的内容
            txtPeopelID.setText("");
            txtAge.setText("");
            txtName.setText("");
            txtPhone.setText("");
            txtSex.setText("");
        }

    }
}
