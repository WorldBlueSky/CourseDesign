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

        setTitle("设备维修申请管理");//设置标题
        setSize(900,630);
        setLocationRelativeTo(null);
        panel=new JPanel(new BorderLayout());
        setContentPane(panel);

        // 设置顶部面板

        ReaderConditionPane =new JPanel();

        JLabel labtitle = new JLabel("设备维修申请记录");

        ReaderConditionPane.add(labtitle);

        panel.add(ReaderConditionPane,BorderLayout.NORTH);

        //中间面板
        centerPanel=new JPanel();
        selectResultPane=new JPanel();
//        table=new JTable();

        Object[][] r1=getSelect(RepairDao.selectAll(""));
        String[] colName= {"编号","设备名称","设备类别","申请维修数量","维修工人名字","维修天数","批准状态"};

        table=new JTable(r1,colName);

        scrollPane=new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800,400));//设大小
        selectResultPane.add(scrollPane);


        // 底部面板
        bookPane=new JPanel(new GridLayout(3,2));

        labId = new JLabel("申请编号:");
        txtId = new JTextField(8);

        labEquipmentName =new JLabel("设备名称:");
        txtEquipmentName =new JTextField(8);

        labTypeName=new JLabel("设备类别:");
        cmbChoice=new JComboBox();
        cmbChoice.addItem("网络设备类");
        cmbChoice.addItem("玻璃仪器类");

        labNumber =new JLabel("维修数量:");
        txtNumber =new JTextField(8);

        labWorker =new JLabel("维修工人:");
        txtWorker =new JTextField(8);

        labDate =new JLabel("维修日期:");
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

        btnSelect = new JButton("查询");
        btnSelect.addActionListener(this);

        btnBorrow = new JButton("申请");
        btnBorrow.addActionListener(this);
        btnClose =new JButton("关闭");
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

    //把查询的结果放到一个二维数组中，目的是要放到JTable中
    Object[][] getSelect(List<Repair> list){

        String[] colName= {"编号","设备名称","设备类别","申请维修数量","维修工人名字","维修天数","批准状态"};
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
                JOptionPane.showMessageDialog(null,"申请操作成功,申请记录已添加，等待领导批复!");
            }else{
                JOptionPane.showMessageDialog(null,"申请操作失败,申请记录添加失败!");
            }
        }

        if(e.getSource()==btnSelect){
            if(txtId.getText().equals("")) {
                Object[][] r1 = getSelect(RepairDao.selectAll(""));
                String[] colName = {"编号", "设备名称", "设备类别", "申请维修数量", "维修工人名字", "维修天数", "批准状态"};
                table = new JTable(r1, colName);//表中的数据
                scrollPane.setViewportView(table);
            }else{
                Object[][] r1=getSelect(RepairDao.selectById(Integer.parseInt(txtId.getText())));
                String[] colName= {"编号","设备名称","设备类别","申请维修数量","维修工人名字","维修天数","批准状态"};
                table=new JTable(r1,colName);//表中的数据
                scrollPane.setViewportView(table);
            }
        }

    }
}
