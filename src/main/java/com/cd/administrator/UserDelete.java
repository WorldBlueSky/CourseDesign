package com.cd.administrator;

import com.cd.db.UserDao;
import com.cd.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserDelete extends JFrame implements ActionListener {
    private final JPanel ReaderConditionPane;
    private JPanel panel,btnPanel,
            centerPanel,selectResultPane,bookPane;

    private JLabel labUserName;
    private JTextField txtUserName;

    private JButton btnDelete,btnExit,btnSelect;

    private JTable table;

    private JScrollPane scrollPane;


    public UserDelete(){
        setTitle("删除用户");//设置标题
        setSize(500,400);
        setLocationRelativeTo(null);
        panel=new JPanel(new BorderLayout());
        setContentPane(panel);



        // 设置顶部面板

        ReaderConditionPane =new JPanel();

        labUserName = new JLabel("用户名:");
        txtUserName = new JTextField(18);

        ReaderConditionPane.add(labUserName);
        ReaderConditionPane.add(txtUserName);


        panel.add(ReaderConditionPane,BorderLayout.NORTH);


        //中间面板
        centerPanel=new JPanel();
        selectResultPane=new JPanel(); // 查询面板


        String[] colName= {"用户编号","用户名","密码"};
        Object[][] r = getSelect(UserDao.selectUser(""));
        table = new JTable(r,colName);

        scrollPane=new JScrollPane(table);  // 把表单加入查询面板

        scrollPane.setPreferredSize(new Dimension(400,250));//设查询面板的大小
        selectResultPane.add(scrollPane);

        // 给下面的按钮布局
        bookPane=new JPanel(new GridLayout(1,2));

        centerPanel.add(selectResultPane);
        centerPanel.add(bookPane);
        panel.add(centerPanel,BorderLayout.CENTER);

        btnPanel=new JPanel();


        btnDelete=new JButton("删除");
        btnExit=new JButton("退出");
        btnSelect = new JButton("查询");

        btnPanel.add(btnSelect);
        btnPanel.add(btnDelete);
        btnPanel.add(btnExit);

       btnSelect.addActionListener(this);
        btnDelete.addActionListener(this);
        btnExit.addActionListener(this);

        panel.add(btnPanel,BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new UserDelete();
    }

    //把查询的结果放到一个二维数组中，目的是要放到JTable中
    private Object[][] getSelect(List<User> list){

        String[] colName= {"用户编号","用户名","密码"};

        //定义一个包含多行3列的二维数据：行根据list来判断
        Object[][] results=new Object[list.size()][colName.length];

        //将list中的每一个Reader中的各个列放到二维数据的各个行中
        for(int i=0;i<list.size();i++){
            User user = list.get(i);//获取list中的每一个reader
            results[i][0]=user.getUserId();
            results[i][1]=user.getUserName();
            results[i][2]=user.getPassword();

        }

        return results;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnDelete){
            // 读取输入的 用户名，调用UserDao的delete 方法进行删除

           String name = txtUserName.getText();
           int ret = UserDao.deleteUser(name);
           if(ret==1){
                JOptionPane.showMessageDialog(null,"删除成功!");
           }else{
               JOptionPane.showMessageDialog(null,"删除失败!");
           }
        }

        if(e.getSource()==btnExit){
            System.exit(1);
        }

        if(e.getSource()==btnSelect){
            String name = txtUserName.getText();

            // 如果name不为空针对name进行模糊查询
            //TODO
            String[] colName= {"用户编号","用户名","密码"};
            Object[][] r = getSelect(UserDao.selectUser(name));
            table = new JTable(r,colName);
            scrollPane.setViewportView(table);//表单重新显示
        }
    }
}
