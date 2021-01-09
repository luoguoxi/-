package supermarket;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
public class findwindows {
    JFrame frame;
    JLabel tradename;//商品名标签
    JLabel sort;//商品类别标签
    JLabel price;//价格标签
    JLabel line;//“——”标签
    JLabel warn;//提示标签
    JTextField Txtname;//存放商品名单行文本
    JTextField Txtsort;//存放商品类别单行文本
    JTextField Txtprice;//存放最低价单行文本
    JTextField Txtprice2;//存放最高价单行文本
    JButton ret;//返回按钮
    JButton affirm;//确认按钮
    JButton resert;//重置按钮

    public void init(){
        frame=new JFrame("查找");
        tradename=new JLabel("商品名：");
        sort=new JLabel("商品类别：");
        price=new JLabel("价格区间：");
        line=new JLabel("－");
        Txtname=new JTextField();
        Txtsort=new JTextField();
        Txtprice=new JTextField();
        Txtprice2=new JTextField();
        ret=new JButton("返回");
        affirm=new JButton("确认");
        resert=new JButton("重置");
        //准备窗口
        frame.setSize(400,300);
        frame.setLocation(200,200);
        //窗口的布局方式
        frame.setLayout(null);
        //组件的大小和位置以及加入容器
        tradename.setBounds(70,50,50,30);
        frame.add(tradename);
        Txtname.setBounds(150,50,100,30);
        frame.add(Txtname);
        sort.setBounds(70,90,60,30);
        frame.add(sort);
        Txtsort.setBounds(150,90,100,30);
        frame.add(Txtsort);
        price.setBounds(70,130,60,30);
        frame.add(price);
        Txtprice.setBounds(150,130,40,30);
        frame.add(Txtprice);
        line.setBounds(190,130,20,30);
        frame.add(line);
        Txtprice2.setBounds(210,130,40,30);
        frame.add(Txtprice2);
        affirm.setBounds(50,190,80,30);
        frame.add(affirm);
        resert.setBounds(140,190,80,30);
        frame.add(resert);
        ret.setBounds(230,190,80,30);
        frame.add(ret);

        //设置顶层窗口为可见
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //确认按钮触发事件
        affirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = Txtname.getText();//获取商品名文本框内容
                String sort = Txtsort.getText();//获取商品类别文本框内容
                String price1 = Txtprice.getText();//获取最低价文本框内容
                String price2 = Txtprice2.getText();//获取最高价文本框内容
                if (name.length() == 0 && sort.length() == 0 && price1.length() == 0 && price2.length() == 0) {
                    try {
                        new findsub().findgoodsall();//在数据库查找全部
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    new findsub().init();//建立表格
                    frame.setVisible(false);//当前窗口不可见
                }
                else if(name.length() != 0 && sort.length() == 0 && price1.length() == 0 && price2.length() == 0){
                    try {
                        new findsub().findgoodsname(name);//通过商品名在数据库查找
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    new findsub().init();//建立表格
                    frame.setVisible(false);//当前窗口不可见
                }
                else if(name.length() == 0 && sort.length() != 0 && price1.length() == 0 && price2.length() == 0){

                    try {
                        new findsub().findgoodssort(sort);//通过商品类别在数据库查找
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    new findsub().init();//建立表格
                    frame.setVisible(false);//当前窗口不可见

                }
                else if(name.length() == 0 && sort.length() == 0 && price1.length() != 0 && price2.length() != 0){
                    try {
                        new findsub().findgoodsprice(price1,price2);//通过价格区间在数据库查找
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    new findsub().init();//建立表格
                    frame.setVisible(false);//当前窗口不可见

                }
                else if(name.length() == 0 && sort.length() != 0 && price1.length() != 0 && price2.length() != 0){
                    try {
                        new findsub().findgoodsbetween(sort,price1,price2);//通过分类与价格区间在数据库查找
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    new findsub().init();//建立表格
                    frame.setVisible(false);//当前窗口不可见

                }
            }


        });
        //重置按钮触发事件
        resert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Txtname.setText("");//商品名为空
                Txtsort.setText("");//商品类别为空
                Txtprice.setText("");//初始价格为空
                Txtprice2.setText("");//结束价格为空
            }
        });
        //返回按钮触发事件
        ret.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new managerselect().init();
            }
        });

    }

    public static void main(String args[]){
        new findwindows().init();

    }
}
