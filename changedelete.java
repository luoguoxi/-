package supermarket;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
public class changedelete extends findsub {
    JFrame frame;
    JLabel number;//编号标签
    JLabel tradename;//商品名标签
    JLabel sort;//商品类别标签
    JLabel price;//价格标签
    JLabel quantity;//数量标签
    JLabel operate;//操作标签
    JTextField Txtnumber;//编号文本框
    JTextField Txttradename;//商品名文本框
    JTextField Txtsort;//商品类别文本框
    JTextField Txtprice;//价格文本框
    JTextField Txtquantity;//数量文本框
    JButton add;//增加按钮
    JButton change;//修改按钮
    JButton delete;//删除按钮
    JButton ret;//返回按钮
    JButton resert;//重置按钮
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
        price=new JLabel("价格：");
        quantity=new JLabel("库存量");
        operate=new JLabel("操作: ");
        Txtnumber=new JTextField();
        Txttradename=new JTextField();
        Txtsort=new JTextField();
        Txtprice=new JTextField();
        Txtquantity=new JTextField();
        add=new JButton("增加");
        change=new JButton("修改");
        delete=new JButton("删除");
        ret=new JButton("返回");
        resert=new JButton("重置");
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
        panelsouth.setLayout(new GridLayout(4,4,10,20));
        //向顶层容器加入嵌入式容器，向嵌入式容器加入组件
        frame.add(panelsouth,BorderLayout.SOUTH);

        //南部
        panelsouth.add(number);
        panelsouth.add(Txtnumber);
        panelsouth.add(tradename);
        panelsouth.add(Txttradename);
        panelsouth.add(sort);
        panelsouth.add(Txtsort);
        panelsouth.add(price);
        panelsouth.add(Txtprice);
        panelsouth.add(quantity);
        panelsouth.add(Txtquantity);
        panelsouth.add(operate);
        panelsouth.add(add);
        panelsouth.add(change);
        panelsouth.add(delete);
        panelsouth.add(resert);
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
                Object price=table.getValueAt(row,3);
                Object quantity=table.getValueAt(row,4);
                //将获取内容，放入对应的文本域
                Txtnumber.setText(number.toString());
                Txttradename.setText((tradename.toString()));
                Txtsort.setText(sort.toString());
                Txtprice.setText(price.toString());
                Txtquantity.setText(quantity.toString());
            }
        });

        //添加按钮触发事件
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取各个文本框内容
                String number = Txtnumber.getText();
                String tradename = Txttradename.getText();
                String sort = Txtsort.getText();
                String price = Txtprice.getText();
                String quantity = Txtquantity.getText();
                if (number.length() != 0 && tradename.length() != 0 && sort.length() != 0 && price.length() != 0 && quantity.length() != 0) {//当各个文本框内容完整时，才添加商品
                    int count =0;
                    try {
                        count = new changedeletesub().insert(number, tradename, sort, price, quantity);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    if (count == 0) {//当count=0时，商品编号已存在，弹出警告显示对话框
                        JOptionPane.showMessageDialog(add, "添加商品编号已存在", "警告", JOptionPane.INFORMATION_MESSAGE);
                    } else if (count == 1) {//当count=1时，添加商品已存在，弹出警告显示对话框
                        JOptionPane.showMessageDialog(add, "添加商品已存在", "警告", JOptionPane.INFORMATION_MESSAGE);
                    } else if (count == 2) {//当count=2时，添加商品失败，弹出警告显示对话框
                        JOptionPane.showMessageDialog(add, "添加商品失败", "警告", JOptionPane.INFORMATION_MESSAGE);

                    } else if (count == 3) {//当count=3时，弹出添加商品成功显示信息对话框，并在表格增加一行内容为增加商品信息
                        JOptionPane.showMessageDialog(frame, "添加商品成功", "提醒", JOptionPane.INFORMATION_MESSAGE);
                        frame.setVisible(false);
                        new changedelete().init();

                    }


                }
                else {//各个文本框内容不完整，弹出警告显示对话框
                    JOptionPane.showMessageDialog(add,"请补全完整添加商品信息","警告",JOptionPane.INFORMATION_MESSAGE);

                }
            }



        });

        //修改按钮触发事件
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取各个文本框内容
                String number = Txtnumber.getText();
                String tradename = Txttradename.getText();
                String sort = Txtsort.getText();
                String price = Txtprice.getText();
                String quantity = Txtquantity.getText();
                if(new changedeletesub().change(number,tradename,sort,price,quantity)){//执行修改操作，返回true，弹出修改成功显示信息对话框
                    JOptionPane.showMessageDialog(add, "修改成功", "提醒", JOptionPane.INFORMATION_MESSAGE);
                    frame.setVisible(false);
                    new changedelete().init();

                }
                else{
                    JOptionPane.showMessageDialog(frame, "修改失败", "警告", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        //删除按钮触发事件
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取Txtnumber文本框内容
                String number = Txtnumber.getText();
                if(new changedeletesub().delete(number)){//执行删除操作，返回true，弹出删除成功显示信息对话框
                    JOptionPane.showMessageDialog(delete,"删除成功","提醒",JOptionPane.INFORMATION_MESSAGE);
                    frame.setVisible(false);
                    init();
                }
                else{//返回false，弹出删除失败显示信息对话框
                    JOptionPane.showMessageDialog(frame,"删除失败","警告",JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        //重置按钮触发事件
        resert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //设置各个文本框的内容为空
                Txtnumber.setText("");
                Txttradename.setText("");
                Txtsort.setText("");
                Txtprice.setText("");
                Txtquantity.setText("");

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
        new changedelete().init();

    }
}
