package supermarket;
import com.xrtero.dao.UserDao;
import com.xrtero.utils.DruidConnection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
public class totalwindows {
    JFrame frame;
    Container con;
    JTable table;
    JButton ret;
    Object data[][];
    Object title[]={"种类","种类数量","库存量"};
    public void init(){
        //统计数据库表goods商品类别分类，商品的数量，各种商品库存量相加的总数量
        String sql="select sort,count(*),sum(stock) from goods group by sort order by count(*)";
        Connection connection= DruidConnection.getConnection();//建立数据库连接

        try {
            Statement stat=connection.createStatement();//取得语句对象
            ResultSet set=stat.executeQuery(sql);//执行SQL语句
            int count=0;//统计有多少条数据
            while(set.next()){
                count++;
            }
            set=stat.executeQuery(sql);
            data=new Object[count][3];//建立一个行为count，列为3的二维数组
            count=0;
            while(set.next()){//把结果集向前移动
                data[count][0]=set.getString(1);//把结果集第一列数据放入data中
                data[count][1]=set.getString(2);//把结果集第二列数据放入data中
                data[count][2]=set.getString(3);//把结果集第三列数据放入data中
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //关闭数据库连接
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //准备窗口和组件
        frame=new JFrame("统计");
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
                new managerselect().init();//返回管理员选择界面
            }
        });






    }
    public static void main(String args[]){
        new totalwindows().init();

    }
}
