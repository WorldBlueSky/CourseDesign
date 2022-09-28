package com.cd.normaluser;

import com.cd.db.EquipmentBorrowDao;
import com.cd.model.EquipmentBorrow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EquipmentReturnManage extends JFrame implements ActionListener {
    private JPanel panel, ReaderConditionPane,btnPanel,
            centerPanel,selectResultPane,bookPane;

    private JTextField  txtEquipmentName, txtNumber,txtTypeName, txtDate, txtPeopel,txtId ;

    private JLabel labEquipmentName, labDate, labNumber,labTypeName, labPeopel,labId;

    private JButton btnClose, btnReturn,btnSelect;

    private JTable table;

    private JScrollPane scrollPane;

    private JComboBox cmbChoice;

    public EquipmentReturnManage(){
        setTitle("设备归还管理");//设置标题
        setSize(900,630);
        setLocationRelativeTo(null);
        panel=new JPanel(new BorderLayout());
        setContentPane(panel);

        // 设置顶部面板

        ReaderConditionPane =new JPanel();

        JLabel labtitle = new JLabel("设备申请记录");

        ReaderConditionPane.add(labtitle);

        panel.add(ReaderConditionPane,BorderLayout.NORTH);

        //中间面板
        centerPanel=new JPanel();
        selectResultPane=new JPanel();
//        table=new JTable();
        Object[][] r1=getSelect(EquipmentBorrowDao.selectAll());
        String[] colName= {"编号","设备名称","设备类别","申请数量","归还数量","当前数量","申请日期","归还日期","操作人员","目前状态"};
        table=new JTable(r1,colName);//表中的数据


        scrollPane=new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800,400));//设大小
        selectResultPane.add(scrollPane);


        // 底部面板
        bookPane=new JPanel(new GridLayout(3,2));

        labEquipmentName =new JLabel("设备名称:");
        txtEquipmentName =new JTextField(8);

        labTypeName=new JLabel("设备类别:");
        cmbChoice=new JComboBox();
        cmbChoice.addItem("网络设备类");
        cmbChoice.addItem("玻璃仪器类");


        labDate =new JLabel("归还日期:");
        txtDate =new JTextField(8);

        labNumber =new JLabel("归还数量:");
        txtNumber =new JTextField(8);

        labPeopel =new JLabel("操作人员:");
        txtPeopel =new JTextField(8);

        labId = new JLabel("申请编号:");
        txtId = new JTextField(8);

        bookPane.add(labId);
        labId.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(txtId);

        bookPane.add(labEquipmentName);
        labEquipmentName.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(txtEquipmentName);

        bookPane.add(labTypeName);
        labTypeName.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(cmbChoice);

        bookPane.add(labDate);
        labDate.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(txtDate);


        bookPane.add(labNumber);
        labNumber.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(txtNumber);

        bookPane.add(labPeopel);
        labPeopel.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(txtPeopel);



        centerPanel.add(selectResultPane);
        centerPanel.add(bookPane);
        panel.add(centerPanel,BorderLayout.CENTER);


        btnPanel=new JPanel();

        btnSelect = new JButton("查询");
        btnSelect.addActionListener(this);

        btnReturn = new JButton("归还");
        btnReturn.addActionListener(this);

        btnClose =new JButton("关闭");
        btnClose.addActionListener(this);

        btnPanel.add(btnSelect);
        btnPanel.add(btnReturn);
        btnPanel.add(btnClose);


        panel.add(btnPanel,BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new EquipmentReturnManage();
    }

    //把查询的结果放到一个二维数组中，目的是要放到JTable中
    Object[][] getSelect(List<EquipmentBorrow> list){

        String[] colName= {"编号","设备名称","设备类别","申请数量","归还数量","当前数量","申请日期","归还日期","操作人员","目前状态"};
        Object[][] results=new Object[list.size()][colName.length];

        for(int i=0;i<list.size();i++){


            EquipmentBorrow b1=list.get(i);

            // 查询总数量
            int finalNum = EquipmentBorrowDao.selectNum(b1.getName());

            results[i][0] = b1.getBorrowId();
            results[i][1]=b1.getName();
            results[i][2]=b1.getTypeName();
            results[i][3]=b1.getBorrowNumber();
            results[i][4]=b1.getReturnNumber();
            results[i][5] = finalNum;
            results[i][6]=b1.getBorrowDate();
            results[i][7] = b1.getReturnDate();
            results[i][8]=b1.getPeopel();
            results[i][9]=b1.getState();
        }

        return results;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnClose){
            System.exit(1);
        }
        if(e.getSource()== btnReturn){

            EquipmentBorrow b = new EquipmentBorrow();

            b.setBorrowId(Integer.parseInt(txtId.getText()));
            b.setReturnNumber(Integer.parseInt(txtNumber.getText()));
            b.setReturnDate(txtDate.getText());

            int ret = EquipmentBorrowDao.update(b);
            if(ret==0)
                JOptionPane.showMessageDialog(null, "归还失败!");
            else
                JOptionPane.showMessageDialog(null, "归还成功!");
        }

        if(e.getSource()==btnSelect){
            if(txtId.getText().equals("")){
                Object[][] r1=getSelect(EquipmentBorrowDao.selectAll());
                String[] colName= {"编号","设备名称","设备类别","申请数量","归还数量","当前数量","申请日期","归还日期","操作人员","目前状态"};
                table=new JTable(r1,colName);//表中的数据
                scrollPane.setViewportView(table);
            }else{
                Object[][] r1=getSelect(EquipmentBorrowDao.selectById(Integer.parseInt(txtId.getText())));
                String[] colName= {"编号","设备名称","设备类别","申请数量","归还数量","当前数量","申请日期","归还日期","操作人员","目前状态"};
                table=new JTable(r1,colName);//表中的数据
                scrollPane.setViewportView(table);
            }


        }
    }

}
