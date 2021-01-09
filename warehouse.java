package supermarket;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
public class warehouse extends findsub {
    JFrame frame;
    JLabel number;//编号标签
    JLabel tradename;//商品名标签
    JLabel sort;//商品类别标签
    JLabel addquantity;//进货标签
    JTextField Txtnumber;//编号文本框
    JTextField Txttradename;//商品名文本框
    JTextField Txtsort;//商品类别文本框
    JTextField Txtaddquantity;//进货文本框
    JButton affirm;//增加按钮
    JButton ret;//返回按钮
    Container container;//内容窗格
    JTable table;//表格
    DefaultTableModel tableModel;//表格模型
    Object title[]={"编号","商品名","商品类别","价格","库存量"};//表格标题
    JPanel panelsouth;//南部面板
    public void init(){
        try {
            new findsub().findgoodsall();//获取数据库中的数据
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        frame=new JFrame("商品管理");
        container=frame.getContentPane();//获得内容窗格
        tableModel=new DefaultTableModel(data,title);//用表格模型建立表格
        table=new JTable(tableModel);

        //南部面板
        panelsouth=new JPanel();
        number=new JLabel("编号：");
        tradename=new JLabel("商品名：");
        sort=new JLabel("商品类别：");
        addquantity=new JLabel("进货：");
        Txtnumber=new JTextField();
        Txttradename=new JTextField();
        Txtsort=new JTextField();
        Txtaddquantity=new JTextField();
        affirm=new JButton("确认");
        ret=new JButton("返回");
        Border border=new LineBorder(Color.blue);

        //设置窗体
        frame.setSize(400,400);
        frame.setLocation(100,200);
        //准备表格
        table.setRowHeight(20);
        table.setFont(new Font("宋体",Font.BOLD,10));
        table.getTableHeader().setFont(new Font("宋体",Font.BOLD,10));
        container.add(new JScrollPane(table),BorderLayout.CENTER);//设置表格居中，并加入滚动条

        //设置南部面板布局管理器
        panelsouth.setLayout(new GridLayout(3,4,10,20));
        //向顶层容器加入嵌入式容器，向嵌入式容器加入组件
        frame.add(panelsouth,BorderLayout.SOUTH);

        //南部
        panelsouth.add(number);
        panelsouth.add(Txtnumber);
        panelsouth.add(tradename);
        panelsouth.add(Txttradename);
        panelsouth.add(sort);
        panelsouth.add(Txtsort);
        panelsouth.add(addquantity);
        panelsouth.add(Txtaddquantity);
        panelsouth.add(affirm);
        panelsouth.add(ret);

        //设置顶层窗口可见
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //表格触发鼠标事件
       table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row=table.getSelectedRow();//获取选中行
                //获取选中行，每列的内容
                Object number=table.getValueAt(row,0);
                Object tradename=table.getValueAt(row,1);
                Object sort=table.getValueAt(row,2);
                //将获取内容，放入对应的文本域
                Txtnumber.setText(number.toString());
                Txttradename.setText((tradename.toString()));
                Txtsort.setText(sort.toString());

            }
        });

        //确认按钮触发事件
        affirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取各个文本框内容
                String number = Txtnumber.getText();
                String addquantity = Txtaddquantity.getText();
                try {
                    if(new changedeletesub().addgoodsquantity(number,addquantity)){//执行进货操作，返回true，弹出进货成功显示信息对话框
                        JOptionPane.showMessageDialog(frame,"进货成功","提醒",JOptionPane.INFORMATION_MESSAGE);
                        frame.setVisible(false);//当前界面不可见
                        init();//重新进入当前界面
                    }
                    else{//返回false，弹出进货失败显示信息对话框
                        JOptionPane.showMessageDialog(frame,"进货失败","警告",JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }



        });



        //返回按钮触发事件
        ret.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);//当前界面不可见
                new managerselect().init();//跳转管理员选择界面
            }
        });




    }
    public static void main(String args[]){
        new warehouse().init();

    }
}
