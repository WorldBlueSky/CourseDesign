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

        setTitle("设备报废申请");//设置标题
        setSize(700,530);
        setLocationRelativeTo(null);
        panel=new JPanel(new BorderLayout());
        setContentPane(panel);
        selectConditionPane=new JPanel();
        cmbChoice=new JComboBox();
        cmbChoice.addItem("全部");
        cmbChoice.addItem("设备名称");
        txtSelect=new JTextField(20);
        selectConditionPane.add(cmbChoice);
        selectConditionPane.add(txtSelect);
        panel.add(selectConditionPane,BorderLayout.NORTH);

        //中间面板
        centerPanel=new JPanel();
        selectResultPane=new JPanel();

        Object[][] r1=getSelect(EquipmentDao.selectAll(""));
        String[] colName= {"编号","名字","类别名","当前数量","数量单位","存放地点","购入日期","购入单价"};
        table=new JTable(r1,colName);//表中的数据



        scrollPane=new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(650,260));//设大小
        selectResultPane.add(scrollPane);
        bookPane=new JPanel(new GridLayout(4,4));


        labEquipmentName =new JLabel("设备名称");
        txtEquipmentName =new JTextField(8);

        labType=new JLabel("设备类型");
        cmbType=new JComboBox();
        cmbType.addItem("网络设备类");
        cmbType.addItem("玻璃仪器类");


        labNumber =new JLabel("当前数量");
        txtNumber =new JTextField(8);

        labUnit =new JLabel("数量单位");
        txtUnit =new JTextField(8);




        // 添加各个组件
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

        // 按钮的信息

        btnPanel=new JPanel();

        btnSelect=new JButton("查询");
        btnSelect.addActionListener(this);

        btnScrap=new JButton("报废");
        btnScrap.addActionListener(this);



        btnExit=new JButton("退出");
        btnExit.addActionListener(this);

        btnPanel.add(btnSelect);

        btnPanel.add(btnScrap);
        btnPanel.add(btnExit);
        panel.add(btnPanel,BorderLayout.SOUTH);

        // 窗口可视化
        setVisible(true);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new EquipmentScrap();
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnScrap){//报废
            String name=txtEquipmentName.getText().trim();

            int i= EquipmentDao.delete(name);
            if(i==1)
                JOptionPane.showMessageDialog(null, "已做报废处理!");
            else
                JOptionPane.showMessageDialog(null, "处理失败!");
        }


        if(e.getSource()==btnSelect){
            //查询
            String name=cmbChoice.getSelectedItem().toString();
            String value=txtSelect.getText();

            if(name.equals("全部")){

                Object[][] r1=getSelect(EquipmentDao.selectAll(value));
                String[] colName= {"编号","名字","类别名","当前数量","数量单位","存放地点","购入日期","购入单价"};
                table=new JTable(r1,colName);//表中的数据
                scrollPane.setViewportView(table);//表与滚动条联系起来

            }else if(name.equals("设备名称")){

                Object[][] r1=getSelect(EquipmentDao.selectByName(value));
                String[] colName= {"编号","名字","类别名", "当前数量","数量单位","存放地点","购入日期","购入单价"};
                table=new JTable(r1,colName);//表中的数据
                scrollPane.setViewportView(table);//表单重新显示

            }


        }



        if(e.getSource()==btnExit){
            System.exit(1);
        }
    }

    //把查询的结果放到一个二维数组中，目的是要放到JTable中
    Object[][] getSelect(List<Equipment> list){

        String[] colName= {"编号","名字","类别名",
                "当前数量","数量单位","存放地点","购入日期","购入单价"};

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
