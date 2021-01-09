package supermarket;
import com.xrtero.utils.DruidConnection;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;
public class findsub extends Frame {
    JFrame frame;
    Container con;//内容窗格
    JTable table;//表格
    JButton ret;//返回按钮
    public static Object data[][];//存放表格数据
    Object title[]={"编号","商品名","商品类别","价格","库存量"};//存放表格标题
    public void findgoodsall()throws SQLException{
        String sql="select * from goods";//SQl语句
        Connection connection= DruidConnection.getConnection();//建立数据库连接
        try {
           Statement ps=connection.createStatement();//获取预处理语句
            ResultSet set=ps.executeQuery(sql);
            int count=0;//统计有多少条数据
            while(set.next()){
                count++;
            }
            set=ps.executeQuery(sql);
            data=new Object[count][5];
            count=0;
            while(set.next()) {//把结果集数据放入data中
                data[count][0] = set.getString(1);
                data[count][1] = set.getString(2);
                data[count][2] = set.getString(3);
                data[count][3] = set.getString(4);
                data[count][4] = set.getString(5);
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.close();

    }
    //建立数据库连接，通过tradename查找，并获取的结果集到data中
    public void findgoodsname(String tradename)throws SQLException{
        String sql="select * from goods where tradename=?";//SQl语句
        Connection connection= DruidConnection.getConnection();//建立数据库连接
        try {
            PreparedStatement ps=connection.prepareStatement(sql);//获取预处理语句
            ps.setString(1,tradename);//tradename替代第一个占位符
            ResultSet set=ps.executeQuery();
            int count=0;//统计有多少条数据
            while(set.next()){
                count++;
            }
            set=ps.executeQuery();
            data=new Object[count][5];
            count=0;
            while(set.next()) {//把结果集数据放入data中
                data[count][0] = set.getString(1);
                data[count][1] = set.getString(2);
                data[count][2] = set.getString(3);
                data[count][3] = set.getString(4);
                data[count][4] = set.getString(5);
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.close();



    }
    public void findgoodssort(String sort)throws SQLException{
        String sql="select * from goods where sort=?";//SQl语句
        Connection connection= DruidConnection.getConnection();//建立数据库连接
        try {
            PreparedStatement ps=connection.prepareStatement(sql);//获取预处理语句
            ps.setString(1,sort);//sort替代第一个占位符
            ResultSet set=ps.executeQuery();
            int count=0;//统计有多少条数据
            while(set.next()){
                count++;
            }
            set=ps.executeQuery();
            data=new Object[count][5];
            count=0;
            while(set.next()) {//把结果集数据放入data中
                data[count][0] = set.getString(1);
                data[count][1] = set.getString(2);
                data[count][2] = set.getString(3);
                data[count][3] = set.getString(4);
                data[count][4] = set.getString(5);
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.close();


    }
    public void findgoodsprice(String price1,String price2) throws SQLException{
        String sql = "select * from goods where price between ? and ?";//SQl语句
        int  p1 = Integer.valueOf(price1);//把price1转化为int型
        int  p2 = Integer.valueOf(price2);//把price1转化为int型
        Connection connection = DruidConnection.getConnection();//建立数据库连接
        try {
            PreparedStatement ps = connection.prepareStatement(sql);//获取预处理语句
            ps.setInt(1,p1);
            ps.setInt(2,p2);
            ResultSet set = ps.executeQuery();
            int count = 0;//统计有多少条数据
            while (set.next()) {
                count++;
            }
            set = ps.executeQuery();
            data = new Object[count][5];
            count = 0;
            while (set.next()) {//把结果集数据放入data中
                data[count][0] = set.getString(1);
                data[count][1] = set.getString(2);
                data[count][2] = set.getString(3);
                data[count][3] = set.getString(4);
                data[count][4] = set.getString(5);
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.close();
    }
        public void findgoodsbetween(String sort,String price1,String price2)throws SQLException{
            String sql="select * from goods where sort=? and price between ? and ?";//SQl语句
            int p1=Integer.valueOf(price1);//把price1转化为int型
            int p2=Integer.valueOf(price2);//把price2转化为int型
            Connection connection= DruidConnection.getConnection();//建立数据库连接
            try {
                PreparedStatement ps=connection.prepareStatement(sql);//获取预处理语句
                ps.setString(1,sort);//tradename替代第一个占位符
                ps.setInt(2,p1);
                ps.setInt(3,p2);
                ResultSet set=ps.executeQuery();
                int count=0;//统计有多少条数据
                while(set.next()){
                    count++;
                }
                set=ps.executeQuery();
                data=new Object[count][5];
                count=0;
                while(set.next()) {//把结果集数据放入data中
                    data[count][0] = set.getString(1);
                    data[count][1] = set.getString(2);
                    data[count][2] = set.getString(3);
                    data[count][3] = set.getString(4);
                    data[count][4] = set.getString(5);
                    count++;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            connection.close();
        }

    public void init(){
        //准备窗口和组件
        frame=new JFrame("数据表");
        con=frame.getContentPane();//取得窗体的内容窗格
        // new UserDao().totalgoods(data);//获得数据库商品表的查询结果
        table=new JTable(data,title);
        ret=new JButton("返回");
        //设置窗体
        frame.setSize(400,300);
        frame.setLocation(200,200);
        con.setLayout(new BorderLayout());
        //准备表格
        table.setRowHeight(20);//设置行高
        table.setFont(new Font("宋体",Font.BOLD,10));//设置表格字体
        table.getTableHeader().setFont(new Font("宋体",Font.BOLD,10));//设置表格标题字体
        con.add(new JScrollPane(table),BorderLayout.CENTER);//加入滚动条，并把表格居中加入内容窗格
        //把组件加入窗口
        con.add(ret,BorderLayout.SOUTH);
        //设置顶层窗口可见
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //表格触发事件
        table.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {//统计表格内容不可修改，修改时弹出“警告”信息对话框
                JOptionPane.showMessageDialog(table,"此统计表不可修改，如需修改请进入修改与删除界面","警告",JOptionPane.INFORMATION_MESSAGE);


            }
        });
        //返回按钮触发事件
        ret.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);//设置当前界面不可见
                new findwindows().init();//返回管理员选择界面
            }
        });

    }
    public void findgoodsfour()throws SQLException{
        String sql="select number,tradename,sort,price from goods";//SQl语句
        Connection connection= DruidConnection.getConnection();//建立数据库连接
        try {
            Statement ps=connection.createStatement();//获取预处理语句
            ResultSet set=ps.executeQuery(sql);//执行SQL语句
            int count=0;//统计有多少条数据
            while(set.next()){
                count++;
            }
            set=ps.executeQuery(sql);
            data=new Object[count][4];
            count=0;
            while(set.next()) {//把结果集数据放入data中
                data[count][0] = set.getString(1);
                data[count][1] = set.getString(2);
                data[count][2] = set.getString(3);
                data[count][3] = set.getString(4);
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.close();//关闭数据库连接
    }
    public static void main(String args[]){

    }
}
