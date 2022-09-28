package com.cd.administrator;

import com.cd.db.RepairDao;
import com.cd.model.Repair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Agree extends JFrame implements ActionListener{
    private JPanel panel, ReaderConditionPane,btnPanel,
            centerPanel,selectResultPane,bookPane;

    private JTextField txtEquipmentName,txtWorker, txtNumber,txtTypeName, txtDate, txtId ;

    private JLabel labEquipmentName, labDate, labWorker,labNumber,labTypeName, labId;

    private JButton btnClose, btnAgree,btnSelect ;

    private JTable table;

    private JScrollPane scrollPane;
    private JComboBox cmbChoice;

    public Agree() {

        setTitle("设备维修批准管理");//设置标题
        setSize(900, 590);
        setLocationRelativeTo(null);
        panel = new JPanel(new BorderLayout());
        setContentPane(panel);

        // 设置顶部面板

        ReaderConditionPane = new JPanel();

        JLabel labtitle = new JLabel("设备维修申请记录");

        ReaderConditionPane.add(labtitle);

        panel.add(ReaderConditionPane, BorderLayout.NORTH);

        //中间面板
        centerPanel = new JPanel();
        selectResultPane = new JPanel();
//        table=new JTable();

        Object[][] r1 = getSelect(RepairDao.selectAll(""));
        String[] colName = {"编号", "设备名称", "设备类别", "申请维修数量", "维修工人名字", "维修天数", "批准状态"};

        table = new JTable(r1, colName);

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 400));//设大小
        selectResultPane.add(scrollPane);


        // 底部面板
        bookPane = new JPanel(new GridLayout(3, 2));

        labId = new JLabel("申请编号:");
        txtId = new JTextField(8);


        bookPane.add(labId);
        labId.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(txtId);



        centerPanel.add(selectResultPane);
        centerPanel.add(bookPane);
        panel.add(centerPanel, BorderLayout.CENTER);


        btnPanel = new JPanel();

        btnSelect = new JButton("查询");
        btnSelect.addActionListener(this);

        btnAgree = new JButton("批准");
        btnAgree.addActionListener(this);

        btnClose = new JButton("关闭");
        btnClose.addActionListener(this);


        btnPanel.add(btnSelect);
        btnPanel.add(btnAgree);
        btnPanel.add(btnClose);


        panel.add(btnPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Agree();
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


        if(btnAgree ==e.getSource()){

            int ret = RepairDao.agree(Integer.parseInt(txtId.getText()));
            if(ret==1){
                JOptionPane.showMessageDialog(null,"批准成功!");
            }else{
                JOptionPane.showMessageDialog(null,"批准失败!");
            }
        }

        if(e.getSource()==btnSelect){
                Object[][] r1 = getSelect(RepairDao.selectAll(""));
                String[] colName = {"编号", "设备名称", "设备类别", "申请维修数量", "维修工人名字", "维修天数", "批准状态"};
                table = new JTable(r1, colName);//表中的数据
                scrollPane.setViewportView(table);

        }

    }

}

